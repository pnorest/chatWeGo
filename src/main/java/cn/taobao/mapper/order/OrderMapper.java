package cn.taobao.mapper.order;

import cn.taobao.entity.order.Order;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.UserOrder;
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

    UserOrder selectUserOrder(String remarkName);
}
