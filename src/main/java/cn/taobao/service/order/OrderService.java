package cn.taobao.service.order;

import cn.taobao.entity.Result;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.UserOrder;
import cn.taobao.entity.order.vo.CheckOrderStatusVo;
import cn.taobao.entity.order.vo.OrderVo;
import cn.taobao.mapper.order.OrderMapper;
import cn.zhouyafeng.itchat4j.beans.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/17 12:14
 * @Version 1.0
 **/
@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);


    @Resource
    private OrderMapper orderMapper;


    public List<OrderInfo> validOrderNum(String trade_parent_id) {//定时任务会把有效订单转储到数据库中,从数据库中查，如果有则有效，如果无则提示
        return orderMapper.validOrderNum(trade_parent_id);


    }

    public UserOrder  checkFirstOrder(String remarkName) {//到这里，这个订单号肯定是个有效订单
        return orderMapper.checkFirstOrder(remarkName);
    }





    public List<String> findExistGoodsInfoList() {
        return orderMapper.findExistGoodsInfoList();
    }

    public void updateGoodsInfoStatus(OrderInfo goodsInfo) {
        orderMapper.updateGoodsInfoStatus(goodsInfo);
    }

    public void addGoodsInfo(OrderInfo goodsInfo) {
        orderMapper.addGoodsInfo(goodsInfo);

    }

    public List<OrderInfo> oderQueryByParentId(String trade_parent_id) {
        return orderMapper.oderQueryByParentId(trade_parent_id);
    }

    public List<UserOrder> hasBindOrders() {
        List<UserOrder> userOrderList=orderMapper.hasBindOrders();
        return userOrderList;
    }

    public void bindUserAndOrder(Contact senMsgContact, List<OrderInfo> orderInfoList) {
        List<UserOrder> userOrderList=new ArrayList<>();
        UserOrder userOrder=new UserOrder();
        for (OrderInfo orderInfo:orderInfoList){
                userOrder.setTrade_id(orderInfo.getTrade_id());
                userOrder.setTrade_parent_id(orderInfo.getTrade_parent_id());
                userOrder.setUser_remark_name(senMsgContact.getRemarkName());
                userOrder.setUser_sex(senMsgContact.getSex());
                userOrder.setUser_signature(senMsgContact.getSignature());
                userOrderList.add(userOrder);
        }
        orderMapper.bindUserAndOrder(userOrderList);//批量插入需绑定数据
    }


    public Result balanceByRemarkName(String remarkName) {//提现，结算方法
//        List<OrderVo> orderVoList=orderMapper.balanceByRemarkName(remarkName);
        Map<String, Double> map=userInfo(remarkName);
        double canCashOutFeeReturn=map.get("canCashOutFeeReturn");
        double canCashOutCount=map.get("canCashOutCount");
        String canCashOutFee=formatDouble(canCashOutFeeReturn);

        if (canCashOutFeeReturn<0.1){//如果可提现金额小于1，则提示用户申请失败，金额>1元时才可以提现
            return new Result(Result.CODE.FAIL.getCode(),"------申请失败------\n","提现金额需大于0.1￥时操作。注意：淘宝官方数据可能有一定延时,商品确认收货后第二日提现最优\n"+"----------------------------------\n"+"有相关问题请联系管理员噢/:rose");
        }
        //提现成功前，需要把本好友remarkName，对应的tk_status状态为3且order_status=0的订单更新状态，把order_status变为1
        String lastSix=orderMapper.findLastSixByRemarkName(remarkName);//找到对应好友后6位
        orderMapper.balanceByLastSix(lastSix);//把订单状态变为结算状态
        return new Result(Result.CODE.SUCCESS.getCode(),"------申请成功------\n","提现金额"+canCashOutFee+"￥，涉及订单数量"+canCashOutCount+"单,将于24小时内发放到您的微信\n"+"----------------------------------\n"+"有相关问题请联系管理员噢/:rose");
    }

    public Map<String,Double> userInfo(String remarkName){
        List<OrderVo> orderVoList=orderMapper.userInfo(remarkName);
        Map<String,Double> map=new HashMap();
        double predictBalanceCount=0;//未收货订单数
        double canCashOutCount=0;//可提现订单数
        double hadBalanceCount=0;//已提现订单数
        double predictBalanceFee=0.00;//未收货,预测可提现金额
        double canCashOutFee=0.00;//可提现金额
        double hadBalanceFee=0.00;//已提现过的总金额
        for(OrderVo orderVo:orderVoList){
            String Pub_share_pre_fee=orderVo.getPub_share_pre_fee();//待提现金额
            double   balanceCount   =   Double.parseDouble(Pub_share_pre_fee);
            if (balanceCount <= 0) {
                balanceCount = 0.0;
            }
            if(balanceCount<3){
                balanceCount= balanceCount*0.85;

            }
            if(balanceCount>=3 &&balanceCount<10){
                balanceCount= balanceCount*0.72;
            }
            if(balanceCount>=10){
                balanceCount= balanceCount*0.65;
            }
            //结算金额要更新到对应到订单上
            orderVo.setOrder_balance_fee(formatDouble(balanceCount));
            orderMapper.updateOrderBalanceFee(orderVo);
            if(orderVo.getOrder_status().equals("1")){//状态为1时，为结算过的总金额 当为1但tk_status不为3的状态时，可提现金额需要-d
                hadBalanceFee=hadBalanceFee+balanceCount;
                hadBalanceCount=hadBalanceCount+1;
                if(!"3".equals(orderVo.getTk_status())){//如果tk_status不为3时，说明结算后退货
                    canCashOutFee=canCashOutFee-balanceCount;
                }
            }
            if(orderVo.getTk_status().equals("3")&&orderVo.getOrder_status().equals("0")){//1.可提现金额为状态为3且订单状态未结算的 （这里存在买了退货的问题，需减去结算过但状态为不为3的） //2.未收货佣金（状态为12的）
               canCashOutFee=canCashOutFee+balanceCount;//若状态Tk_status为3说明为收货订单，且Order_status为未结算时，计入可提现金额
                canCashOutCount=canCashOutCount+1;
            }
            if(orderVo.getTk_status().equals("12")){//如果未收货则为待返金额
                predictBalanceFee=predictBalanceFee+balanceCount;
                predictBalanceCount=predictBalanceCount+1;
            }
        }
//        BigDecimal bg = new BigDecimal(hadBalanceFee).setScale(2, RoundingMode.DOWN);
//        BigDecimal bgd = new BigDecimal(canCashOutFee).setScale(2, RoundingMode.DOWN);//这里2块2毛8变成2块2毛7
//        BigDecimal bgdl = new BigDecimal(predictBalanceFee).setScale(2, RoundingMode.DOWN);
//        double hadBalanceFeeReturn=Math.floor(hadBalanceFee*100)/100;
//        double canCashOutFeeReturn=Math.floor(canCashOutFee*100)/100;
//        double predictBalanceFeeReturn=Math.floor(predictBalanceFee*100)/100;
//        double hadBalanceFeeReturn=bg.doubleValue();
//        double canCashOutFeeReturn=bgd.doubleValue();
//        double predictBalanceFeeReturn=bgdl.doubleValue();
//        if (returnNumber <= 0) {
//            returnNumber = 0.0;
//        }
//        if(returnNumber<2){
//            returnNumber= returnNumber*0.85;
//
//        }
//        if(returnNumber>=2 &&returnNumber<10){
//            returnNumber= returnNumber*0.75;
//        }
//        if(returnNumber>=10){
//            returnNumber= returnNumber*0.68;
//        }

        map.put("hadBalanceFeeReturn",hadBalanceFee);
        map.put("canCashOutFeeReturn",canCashOutFee);
        map.put("predictBalanceFeeReturn",predictBalanceFee);
        map.put("hadBalanceCount",hadBalanceCount);
        map.put("canCashOutCount",canCashOutCount);
        map.put("predictBalanceCount",predictBalanceCount);

        return map;

    }



    public   String formatDouble(double d) {
        return String.format("%.2f", d);
    }

    public String findxsjc() {
        return orderMapper.findxsjc();
    }

    public List<CheckOrderStatusVo> checkOrderStatus() {
        return  orderMapper.checkOrderStatus();
    }

    public void receipt(String trade_id) {
        orderMapper.receipt(trade_id);
    }
}
