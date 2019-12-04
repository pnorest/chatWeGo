package cn.taobao.mapper.order;

import cn.taobao.entity.Result;
import cn.taobao.entity.order.Order;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.UserOrder;
import cn.taobao.entity.order.vo.CheckOrderStatusVo;
import cn.taobao.entity.order.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<String> findExistGoodsInfoList();

    void updateGoodsInfoStatus(OrderInfo goodsInfo);

    void addGoodsInfo(OrderInfo goodsInfo);

    List<OrderInfo> oderQueryByParentId(String trade_parent_id);




    List<OrderInfo> validOrderNum(String trade_parent_id);//trade_parent_id=#{order_num}根据父id查有效无效

    UserOrder checkFirstOrder(String remarkName);

    void bindUserAndOrder(@Param("userOrderList") List<UserOrder> userOrderList);

    List<UserOrder> hasBindOrders();


    List<OrderVo> userInfo(String remarkName);

    String findLastSixByRemarkName(String remarkName);

    void balanceByLastSix(String lastSix);

    void updateOrderBalanceFee(OrderVo orderVo);

    String findxsjc();

    List<CheckOrderStatusVo> checkOrderStatus();

    void receipt(String trade_id);
}
