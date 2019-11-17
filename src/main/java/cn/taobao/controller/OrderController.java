package cn.taobao.controller;

import cn.taobao.entity.Result;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.Order;
import cn.taobao.robot.wx.RobotService;
import cn.taobao.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO  订单相关接口
 * @Author Pnorest
 * @Date 2019/11/7 17:55
 * @Version 1.0
 **/

@RestController
@RequestMapping("/chat")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RobotService robotService;

    @Autowired
    private OrderService orderService;
    //定时查询订单数据，并存储到mysql
    @Scheduled(cron = "0 0/1 * * * ?")
    public void orderDumps(){
          String endTime="2019-11-17 18:13:00";
          String startTime="2019-11-17 17:43:00";
//          String endTime=DateUtil.getCurrentDateTimeString();//"2019-11-17 13:28:22"
//          String startTime=DateUtil.getHalfHourAgoDateTimeString();//"2019-11-17 13:28:22"
          Result result=robotService.orderDetailsGet(startTime,endTime);
          logger.info("result："+result.getMessage()+"data："+result.getData());
          Order order=(Order)result.getData();
          if (order==null){
              logger.info("此时间段无定单信息");
          }
          List<OrderInfo> orderList=order.getData();
          List<String> existGoodsInfoList=orderService.findExistGoodsInfoList();
          for(OrderInfo goodsInfo: orderList){
              String trade_id=goodsInfo.getTradeId();
              if(existGoodsInfoList.contains(trade_id)){//如果数据库中存在该订单信息则更新（逻辑删除后新增）

              }else {
                  //如果数据库中没有该订单信息，则新增
              }
          }




    }


}
