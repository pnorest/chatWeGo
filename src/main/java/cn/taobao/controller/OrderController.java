package cn.taobao.controller;

import cn.taobao.entity.Result;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.Order;
import cn.taobao.entity.order.vo.CheckOrderStatusVo;
import cn.taobao.robot.wx.RobotService;
import cn.taobao.service.order.OrderService;
import cn.taobao.utils.DateUtil;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.beans.Contact;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.core.MsgCenter;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO  订单相关接口
 * @Author Pnorest
 * @Date 2019/11/7 17:55
 * @Version 1.0
 **/

@RestController
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static Core orderCore = Core.getInstance();


    @Autowired
    private RobotService robotService;

    @Autowired
    private OrderService orderService;


    //开启微信登陆
    @RequestMapping("/oderQueryByParentId")
    public List<OrderInfo> oderQueryByParentId(String trade_parent_id) {
        return orderService.oderQueryByParentId(trade_parent_id);

    }

    //开启微信登陆
    @RequestMapping("/dayDateSplit")
    public void dayDateSplit() {
        try {
            Date YesStartDate = DateUtil.getYesStartDate();//YesStartDate 昨日00:00:00
            Date YesEndDate = DateUtil.getYesEndDate();//YesEndDate 昨日23:59:59
            Date YesMonStartDate = DateUtil.getYesMonStartDate();//上个月1号 00:00:00
            Date YesMonEndDate = DateUtil.getYesEndDate();//上个月最后一天 23:59:59
            Date TwtMinAgoDate = DateUtil.getTwtMinAgoDate();//20分钟前  Date格式
            Date TodayStartDate = DateUtil.getTodayStartDate();//今日开始时间 00:00:00
            //getYesMonStartDate 上个月1号 00:00:00
            //getYesMonEndDate   上个月最后一天 23:59:59
            //getTwtMinAgoDateTimeString 20分钟前
            //getTodayStartDate//今日开始时间 00:00:00
            //List<Date> dateList = DateUtil.dateSplit(TodayStartDate, TwtMinAgoDate);//得到昨日间隔1个小时的时间段列表，共24段
            List<Date> dateList = DateUtil.dateSplit(YesMonStartDate, YesEndDate);//得到昨日间隔1个小时的时间段列表，共24段
            for (int i = 0; i < dateList.size(); i++) {//
                System.out.println("i:" + i + "." + DateUtil.convertDateToDateString(dateList.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    i:0.2019-11-19 23:59:59
//    i:1.2019-11-19 23:39:59
//    i:2.2019-11-19 23:19:59
//    i:3.2019-11-19 22:59:59
//    i:4.2019-11-19 22:39:59
//    i:5.2019-11-19 22:19:59
//    i:6.2019-11-19 21:59:59
//    i:7.2019-11-19 21:39:59
//    i:8.2019-11-19 21:19:59
//    i:9.2019-11-19 20:59:59
//    i:10.2019-11-19 20:39:59
//    i:11.2019-11-19 20:19:59
//    i:12.2019-11-19 19:59:59
//    i:13.2019-11-19 19:39:59
//    i:14.2019-11-19 19:19:59
//    i:15.2019-11-19 18:59:59
//    i:16.2019-11-19 18:39:59
//    i:17.2019-11-19 18:19:59
//    i:18.2019-11-19 17:59:59
//    i:19.2019-11-19 17:39:59
//    i:20.2019-11-19 17:19:59
//    i:21.2019-11-19 16:59:59
//    i:22.2019-11-19 16:39:59
//    i:23.2019-11-19 16:19:59
//    i:24.2019-11-19 15:59:59
//    i:25.2019-11-19 15:39:59
//    i:26.2019-11-19 15:19:59
//    i:27.2019-11-19 14:59:59
//    i:28.2019-11-19 14:39:59
//    i:29.2019-11-19 14:19:59
//    i:30.2019-11-19 13:59:59
//    i:31.2019-11-19 13:39:59
//    i:32.2019-11-19 13:19:59
//    i:33.2019-11-19 12:59:59
//    i:34.2019-11-19 12:39:59
//    i:35.2019-11-19 12:19:59
//    i:36.2019-11-19 11:59:59
//    i:37.2019-11-19 11:39:59
//    i:38.2019-11-19 11:19:59
//    i:39.2019-11-19 10:59:59
//    i:40.2019-11-19 10:39:59
//    i:41.2019-11-19 10:19:59
//    i:42.2019-11-19 09:59:59
//    i:43.2019-11-19 09:39:59
//    i:44.2019-11-19 09:19:59
//    i:45.2019-11-19 08:59:59
//    i:46.2019-11-19 08:39:59
//    i:47.2019-11-19 08:19:59
//    i:48.2019-11-19 07:59:59
//    i:49.2019-11-19 07:39:59
//    i:50.2019-11-19 07:19:59
//    i:51.2019-11-19 06:59:59
//    i:52.2019-11-19 06:39:59
//    i:53.2019-11-19 06:19:59
//    i:54.2019-11-19 05:59:59
//    i:55.2019-11-19 05:39:59
//    i:56.2019-11-19 05:19:59
//    i:57.2019-11-19 04:59:59
//    i:58.2019-11-19 04:39:59
//    i:59.2019-11-19 04:19:59
//    i:60.2019-11-19 03:59:59
//    i:61.2019-11-19 03:39:59
//    i:62.2019-11-19 03:19:59
//    i:63.2019-11-19 02:59:59
//    i:64.2019-11-19 02:39:59
//    i:65.2019-11-19 02:19:59
//    i:66.2019-11-19 01:59:59
//    i:67.2019-11-19 01:39:59
//    i:68.2019-11-19 01:19:59
//    i:69.2019-11-19 00:59:59
//    i:70.2019-11-19 00:39:59
//    i:71.2019-11-19 00:19:59
//    i:72.2019-11-19 00:00:00


    @RequestMapping("/mothDateSplit")
    public void mothDateSplit() {
        try {

            Date startDate = DateUtil.getYesMonStartDate();
            Date endDate = DateUtil.getYesMonEndDate();
            List<Date> dateList = DateUtil.dateSplit(startDate, endDate);//得到昨日间隔1个小时的时间段列表，共24段
            for (int i = 0; i < dateList.size(); i++) {//
                System.out.println("i:" + i + "." + DateUtil.convertDateToDateString(dateList.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    定时查询订单数据，并存储到mysql
    @Scheduled(cron = "0 0/2 * * * ?")
    public void orderDumps() {
        try {//2分钟查一次
//            1、每1-5分钟查询前20分钟的订单：这一步主要是发现客户领券后下单，第一时间保存，微信机器人常用这一步发现客户订单。    //            String endTime="2019-11-16 18:28:22";
//            但是这一步可能因为阿里官网数据延迟，导致有的订单因延迟而没有查到，出现漏单现象。需要第二步复查。 //    //            String startTime="2019-11-16 17:00:22";
            logger.info("执行orderDumps，检查前20分钟订单");
            String endTime = DateUtil.getCurrentDateTimeString();//"2019-11-17 13:28:22"
            String startTime = DateUtil.getTwtMinAgoDateTimeString();//"2019-11-17 13:28:22"
            logger.info("执行时间：startTime"+startTime+"endTime"+endTime);
            Result result = robotService.orderDetailsGet(startTime, endTime);
            logger.info("result：" + result.getMessage() + "data：" + result.getData());
            dealOrders(result);
        } catch (Exception e) {
            e.printStackTrace();
            //java.util.LinkedHashMap cannot be cast to cn.taobao.entity.order.OrderInfo
        }
    }


    @Scheduled(cron = "0 0 */1 * * ?")
    public void yesDayOrderCheck() {//每个小时一次吧  0 0 */1 * * ?      半个小时一次吧  0 0/30 * * * ?
        try {
            logger.info("执行yesDayOrderCheck，检查今日至现在的订单");
//每天定时查询昨天的订单（1-5分钟查一次，=>这里每半小时更新一次昨日订单信息，每次个时间段按间隔1个小时算）：这一步主要是防止第一步的客户领券没有在20分钟内下单，做复查用。
//以上两步，目的是告诉用户你已检测到他的订单，结算时不会漏掉，让用户放心即可。
            Date TodayStartDate = DateUtil.getTodayStartDate();
            Date TwtMinAgoDate = DateUtil.getTwtMinAgoDate();
            logger.info("执行时间：TodayStartDate"+TodayStartDate+"TwtMinAgoDate"+TwtMinAgoDate);
            List<Date> dateList = DateUtil.dateSplit(TodayStartDate, TwtMinAgoDate);
            for (int i = 0; i < dateList.size(); i++) {//
                //这里需要i+1的时间为开始时间
                if (i+1>=dateList.size()){//若72+1到73，则超过数据了，就return
                    return;
                }
                Result result = robotService.orderDetailsGet(DateUtil.convertDateToDateString(dateList.get(i + 1)), DateUtil.convertDateToDateString(dateList.get(i)));
                dealOrders(result);
                Thread.sleep(2000);//休眠3秒钟接着请求接口并更新
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 0/2 * * * ?")
    public void checkOrderStatus() {//检查所有未收货订单状态，2分钟一次？
        try {
            logger.info("执行checkOrderStatus，检查所有未收货订单状态");
            List<CheckOrderStatusVo> orderStatusVoList=orderService.checkOrderStatus();
            if(orderStatusVoList.size()<1){
                return;
            }
            for (CheckOrderStatusVo checkOrderStatusVo:orderStatusVoList){//找到所有未收货的订单
                String tk_paid_time=checkOrderStatusVo.getTk_paid_time();//该订单下单时间
                //找到下单时间前后3分钟左右所有的数据
                Date tk_paid_time_date=DateUtil.convertStringToDate(tk_paid_time);
                String onMinAgo=DateUtil.onMinAgo(tk_paid_time_date);
                String onMinAft=DateUtil.onMinAft(tk_paid_time_date);
                Result result = robotService.orderDetailsGet(onMinAgo, onMinAft);
                Order order = (Order) result.getData();
                if (order == null) {
                    logger.info("checkOrderStatus此时间段无定单信息,order为空");
                    continue;
                }
                List<OrderInfo> orderList = order.getData();
                if(orderList.size()<1){
                    logger.info("checkOrderStatus此时间段无定单信息时，orderList不存在数据");
                    continue;
                }
                for(OrderInfo orderInfo:orderList){//这里至少有一条数据
                    if (checkOrderStatusVo.getTrade_id().equals(orderInfo.getTrade_id())){//如果未收货状态为12的订单匹配到这个时间段的这个订单
                        if (orderInfo.getTk_status().equals("3")){//如果这个订单状态由12变为确认收货3，则推送给对应小伙伴消息
                            orderService.receipt(orderInfo.getTrade_id());
                            logger.info("确认收货，自动更新状态的订单号为："+orderInfo.getTrade_id());
                            receipt(checkOrderStatusVo);
                            logger.info("推送消息成功");
                        }
                    }//
                }
                Thread.sleep(1000);
            }

//            String remarkName="薛娟小号";
//            receipt(remarkName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dealOrders(Result result) {
        Order order = (Order) result.getData();
        if (order == null) {
            logger.info("此时间段无定单信息,order为空");
            return;
        }
        List<OrderInfo> orderList = order.getData();
        List<String> existGoodsInfoList = orderService.findExistGoodsInfoList();
        if (existGoodsInfoList.size() < 1) {//size=0 不为空  一般不会这样
            logger.info("existGoodsInfoList为空，orderInfo这个表不存在数据");
            return;
        }
        if(orderList.size()<1){
            logger.info("orderList不存在数据");
        }
        for (OrderInfo orderInfo : orderList) {
            String trade_id = orderInfo.getTrade_id();
            if (existGoodsInfoList.contains(trade_id)) {//如果数据库中存在该订单信息则更新
                orderService.updateGoodsInfoStatus(orderInfo);//这里是对的，不能加break
                logger.info("更新订单数据"+orderInfo.getTrade_id());
            } else {
                //如果数据库中没有该订单信息，则直接新增
                orderService.addGoodsInfo(orderInfo);
                logger.info("新增订单数据"+orderInfo.getTrade_id());
            }
        }
    }





    @Scheduled(cron = "0 0 1 * * ?")//0 15 10 * * ? 每天凌晨1点
    public void yesMonthOrderCheck() {//每个月20号开始查询上个月订单  每月10号9点15分钟执行任务 ：0 15 9 10 * ?
        try {
            logger.info("执行yesMonthOrderCheck，检查上月至昨日订单");
// 3每个月20-25号，再定时查询上个月的订单：因为20号是淘宝联盟和你结算的时间，这时用户的订单基本固定了，你跟客户结算很安全，可以用3秒1次的频率，查询上个月的订单，再和你的客户结算返利或佣金。
// 这步查询时，最好直接查询结算过的订单，也就是把参数 query_type 设置为“结算时间 3”，  //            参考：http://wsd.591hufu.com/taokelianmeng/329.html
            Date YesMonStartDate = DateUtil.getYesMonStartDate();
            Date YesEndDate = DateUtil.getYesEndDate();
            logger.info("执行时间：YesMonStartDate"+YesMonStartDate+"YesEndDate"+YesEndDate);
            List<Date> dateList = DateUtil.dateSplit(YesMonStartDate, YesEndDate);
            for (int i = 0; i < dateList.size(); i++) {//
                if (i+1>=dateList.size()){//若72+1到73，则超过数据了，就return
                    return;
                }
                Result result = robotService.orderDetailsGet(DateUtil.convertDateToDateString(dateList.get(i + 1)), DateUtil.convertDateToDateString(dateList.get(i)));
                dealOrders(result);
                Thread.sleep(2000);//休眠3秒钟接着请求接口并更新
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    private void receipt(CheckOrderStatusVo checkOrderStatusVo){//抽离出给某个好友单独发送消息的部分
        Contact senMsgContact=new Contact();
        List<Contact> contactList = JSON.parseArray(JSON.toJSONString(orderCore.getContactList()), Contact.class);
        for(Contact contact:contactList){//与好友列表循环匹配，如果匹配到发消息者的id（fromUserName）则可以得到发消息者的信息
            String contactRemarkName=contact.getRemarkName();
            if(checkOrderStatusVo.getUser_remark_name().equals(contactRemarkName)){//使用remarkName匹配到对应的人，fromUserName
                String userName=contact.getUserName();
                System.out.println("对应的人的userName为："+userName+",备注名称为"+contactRemarkName);//发消息的人为：薛娟小号
                senMsgContact.setUserName(userName);
                senMsgContact.setRemarkName(checkOrderStatusVo.getUser_remark_name());
            }
            //若没有匹配到发消息者，则不做任何处理，走下面逻辑就行(不可能)
        }
        if (senMsgContact.getRemarkName()==null){//senMsgContact里面是否有值
            return;
        }
        MessageTools.sendMsgById("亲爱的小伙伴，您的商品确认收货成功,可以找机器人提现噢/:rose\n"+"订单编号："+checkOrderStatusVo.getTrade_id()+"\n付款金额："+checkOrderStatusVo.getAlipay_total_price()+" ￥\n店铺名称："+checkOrderStatusVo.getSeller_shop_title()+"\n商品信息："+checkOrderStatusVo.getItem_title()+"\n-----------------------------------------"+"\n发送<个人信息>指令可查询所有订单情况/:heart", senMsgContact.getUserName());
    }



    private void dealTime(){//抽离出某订单号下单时间的处理
        String tk_paid_time="2019-11-30 21:53:09";//该订单下单时间
        //找到下单时间前后1分钟左右所有的数据
        Date tk_paid_time_date=DateUtil.convertStringToDate(tk_paid_time);
        String onMinAgo=DateUtil.onMinAgo(tk_paid_time_date);
        String onMinAft=DateUtil.onMinAft(tk_paid_time_date);//因为上一个处理减了一分钟，这里需要加两分钟
        System.out.println("onMinAgo"+onMinAgo);
        System.out.println("onMinAft"+onMinAft);

    }

}

