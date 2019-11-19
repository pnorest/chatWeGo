package cn.taobao.service.order;

import cn.taobao.entity.Result;
import cn.taobao.entity.order.Order;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.UserOrder;
import cn.taobao.mapper.order.OrderMapper;
import cn.zhouyafeng.itchat4j.beans.Contact;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/17 12:14
 * @Version 1.0
 **/
@Service
public class OrderService {


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

//    public UserOrder selectUserOrder(String remarkName) {
//        return orderMapper.selectUserOrder(remarkName);
//    }
}
