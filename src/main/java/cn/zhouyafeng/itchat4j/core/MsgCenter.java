package cn.zhouyafeng.itchat4j.core;

import cn.taobao.entity.Result;
import cn.taobao.entity.item.TaoBaoResult;
import cn.taobao.entity.order.OrderInfo;
import cn.taobao.entity.order.UserOrder;
import cn.taobao.entity.study.Study;
import cn.taobao.robot.wx.RobotService;
import cn.taobao.service.order.OrderService;
import cn.taobao.service.study.StudyService;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.beans.Contact;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgCodeEnum;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.CommonTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joe.http.client.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 消息处理中心
 *
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 创建时间：2017年5月14日 下午12:47:50
 */
@Component
public class MsgCenter {
    private static Logger LOG = LoggerFactory.getLogger(MsgCenter.class);

    private static StudyService studyService;
    private static OrderService orderService;
    private static RobotService robotService;



    @Autowired
    public MsgCenter(StudyService studyService,OrderService orderService,RobotService robotService) {
        MsgCenter.studyService = studyService;
        MsgCenter.orderService = orderService;
        MsgCenter.robotService = robotService;
    }




    private static Core core = Core.getInstance();


//    private static IHttpClient client;

    private static String regex = "\\w{11}";//淘宝口令匹配，也会匹配到订单号 ，故应该先匹配订单号
    private static String orderRegex = "^[0-9]{18}";//^[0-9]{18}

    /**
     * 接收消息，放入队列
     *
     * @param msgList
     * @return
     * @author https://github.com/yaphone
     * @date 2017年4月23日 下午2:30:48
     */
    public static JSONArray produceMsg(JSONArray msgList) {
        JSONArray result = new JSONArray();
        for (int i = 0; i < msgList.size(); i++) {
            JSONObject msg = new JSONObject();
            JSONObject m = msgList.getJSONObject(i);
            m.put("groupMsg", false);// 是否是群消息
            if (m.getString("FromUserName").contains("@@") || m.getString("ToUserName").contains("@@")) { // 群聊消息
                if (m.getString("FromUserName").contains("@@")
                        && !core.getGroupIdList().contains(m.getString("FromUserName"))) {
                    core.getGroupIdList().add((m.getString("FromUserName")));
                } else if (m.getString("ToUserName").contains("@@")
                        && !core.getGroupIdList().contains(m.getString("ToUserName"))) {
                    core.getGroupIdList().add((m.getString("ToUserName")));
                }
                // 群消息与普通消息不同的是在其消息体（Content）中会包含发送者id及":<br/>"消息，这里需要处理一下，去掉多余信息，只保留消息内容
                if (m.getString("Content").contains("<br/>")) {
                    String content = m.getString("Content").substring(m.getString("Content").indexOf("<br/>") + 5);
                    m.put("Content", content);
                    m.put("groupMsg", true);
                }
            } else {
                CommonTools.msgFormatter(m, "Content");
            }
            if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_TEXT.getCode())) { // words
                // 文本消息
                if (m.getString("Url").length() != 0) {
                    String regEx = "(.+?\\(.+?\\))";
                    Matcher matcher = CommonTools.getMatcher(regEx, m.getString("Content"));
                    String data = "Map";
                    if (matcher.find()) {
                        data = matcher.group(1);
                    }
                    msg.put("Type", "Map");
                    msg.put("Text", data);
                } else {
                    msg.put("Type", MsgTypeEnum.TEXT.getType());
                    msg.put("Text", m.getString("Content"));
                }
                m.put("Type", msg.getString("Type"));
                m.put("Text", msg.getString("Text"));
            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_IMAGE.getCode())
                    || m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_EMOTICON.getCode())) { // 图片消息
                m.put("Type", MsgTypeEnum.PIC.getType());
            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_VOICE.getCode())) { // 语音消息
                m.put("Type", MsgTypeEnum.VOICE.getType());
            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_VERIFYMSG.getCode())) {// friends
                // 好友确认消息
                // MessageTools.addFriend(core, userName, 3, ticket); // 确认添加好友
                m.put("Type", MsgTypeEnum.VERIFYMSG.getType());

            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_SHARECARD.getCode())) { // 共享名片
                m.put("Type", MsgTypeEnum.NAMECARD.getType());

            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_VIDEO.getCode())
                    || m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_MICROVIDEO.getCode())) {// viedo
                m.put("Type", MsgTypeEnum.VIEDO.getType());
            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_MEDIA.getCode())) { // 多媒体消息
                m.put("Type", MsgTypeEnum.MEDIA.getType());
            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_STATUSNOTIFY.getCode())) {// phone
                // init
                // 微信初始化消息

            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_SYS.getCode())) {// 系统消息
                m.put("Type", MsgTypeEnum.SYS.getType());
            } else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_RECALLED.getCode())) { // 撤回消息

            } else {
                LOG.info("Useless msg");
            }
            LOG.info("收到消息一条，来自: " + m.getString("FromUserName"));
            result.add(m);
        }
        return result;
    }

    /**
     * 消息处理
     *
     * @param msgHandler
     * @author https://github.com/yaphone
     * @date 2017年5月14日 上午10:52:34
     */
    public static void handleMsg(IMsgHandlerFace msgHandler) {
        try {
            while (true) {
                if (core.getMsgList().size() > 0 && core.getMsgList().get(0).getContent() != null) {
                    if (core.getMsgList().get(0).getContent().length() > 0) {
                        BaseMsg msg = core.getMsgList().get(0);
                        if (msg.isGroupMsg())//List<String> groupIdList = core.getGroupIdList(); //也可以针对群id和群备注名称，控制单个群消息
                        {//如果是群消息
                            if (msg.getType() != null) {
                                if (msg.getType().equals(MsgTypeEnum.TEXT.getType()))
                                {
                                    String content = msg.getContent();
                                    String TAO_TOKEN = getMatchers(regex, content);
                                    if (TAO_TOKEN.equals("") || TAO_TOKEN == "") {//没有匹配到淘口令时,处理资料信息
								  	   dealResource(msg);
                                    } else {
                                        dealTaoToken(TAO_TOKEN);//当匹配到淘口令时，对消息作出处理
                                    }
                                }
                                //dealOtherMsg(msgHandler,msg);//处理文本消息以外的其他消息
                            }
                        }
                        else
                        {//如果是个人消息
                            if (msg.getType() != null) {
                                String fromUserName=msg.getFromUserName();//@52e109067156338bd344f2a4ba5e4bfa8a5afd54655638f918546e3102e8e6fe
                                try {
                                    if (msg.getType().equals(MsgTypeEnum.TEXT.getType())) {//先匹配订单号，再匹配淘口令
                                        String content = msg.getContent();
                                        String trade_parent_id = getMatchers(orderRegex, content);//先匹配订单号
                                        if (!trade_parent_id.equals("")){//如果没匹配到订单号，则走淘口令的检测
                                            //匹配到订单号，走订单的逻辑
                                            String lastSix=trade_parent_id.substring(12,18);
                                            List<Contact> contactList = JSON.parseArray(JSON.toJSONString(core.getContactList()), Contact.class);
                                            Contact senMsgContact=new Contact();
                                            for(Contact contact:contactList){//与好友列表循环匹配，如果匹配到发消息者的id（fromUserName）则可以得到发消息者的信息
                                                String userName=contact.getUserName();
                                                if(userName.equals(fromUserName)){//匹配到发消息者，则得到发消息者的信息,并退出当前循环
                                                    String remarkName=contact.getRemarkName();
                                                    System.out.println("发消息的人为："+remarkName);//发消息的人为：薛娟小号
                                                    senMsgContact.setRemarkName(remarkName);
                                                    senMsgContact.setNickName(contact.getNickName());
                                                    senMsgContact.setSignature(contact.getSignature());
                                                    senMsgContact.setSex(contact.getSex());
                                                }
                                                //若没有匹配到发消息者，则不做任何处理，走下面逻辑就行(不可能)
                                            }
                                            String remarkName=senMsgContact.getRemarkName();//得到备注名称
                                            List<OrderInfo> orderInfoList=orderService.validOrderNum(trade_parent_id);//判断有效订单
                                            if(orderInfoList.size()>0)//如果为有效订单号
                                            {
                                                UserOrder isFirstOrder=orderService.checkFirstOrder(remarkName);//先判断是否为该好友的第一个订单号
                                                if (isFirstOrder==null){//如果为第一个订单(因为第一个订单，所以后台返回空)
                                                    //需判断这个订单跟以前的订单数据是否冲突后再绑定人和订单
                                                    //如果这个订单后6位数与其他绑定的订单后6位数不重复=》则绑定该订单
                                                    List<UserOrder> hasBindOrders=orderService.hasBindOrders();
                                                    UserOrder userOrderContains=new UserOrder();
                                                    if (hasBindOrders.size()>0){//如果绑定表中有数据（刚开始要放个数据在表中）
                                                        for(UserOrder userOrder:hasBindOrders){
                                                            if(userOrder.getTrade_id().substring(12,18).equals(lastSix)){
                                                                userOrderContains.setTrade_id(userOrder.getTrade_id());//如果这个订单后6位已被其他人绑定
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (userOrderContains.getTrade_id()==null){//这里需要测试，是否为空的情况(测试通过)
                                                        //若绑定表中无此订单数据，则绑定该订单
                                                        orderService.bindUserAndOrder(senMsgContact,orderInfoList);//则绑定人和订单关系
                                                        MessageTools.sendMsgById("第一次绑定订单成功："+trade_parent_id, core.getMsgList().get(0).getFromUserName());
                                                    }else {//若有尾号6位数的订单，则给用户提示，该订单号有异议，请联系管理员
                                                        MessageTools.sendMsgById("此订单有异议，请联系管理员,单号："+trade_parent_id, core.getMsgList().get(0).getFromUserName());
                                                    }

                                                }
                                                else {//如果不为该好友的第一个订单 则提示已经绑定过订单，且绑定账号与此订单号校验无误，无需操作 （isFirstOrder有值，根据它判断）
                                                    String lastNum=isFirstOrder.getTrade_id().substring(12,18);
                                                    if(lastNum.equals(lastSix)){//订单号后6位是否与已绑定订单号一致，一致则提示账号已绑定订单，无需操作，不一致则提示，订单号与被绑定数据不一致，请联系管理员
                                                        //订单号后6位与已绑定订单号一致，则绑定
                                                        MessageTools.sendMsgById("账号与订单已进行过关联，无需操作", core.getMsgList().get(0).getFromUserName());
                                                    }else {//若好友输入订单号与绑定订单号不一致则提示
                                                        MessageTools.sendMsgById("订单号与已绑定订单号不一致，请联系管理员。单号："+trade_parent_id, core.getMsgList().get(0).getFromUserName());
                                                    }

                                                }
                                            }else {
                                                //如果为无效订单号或未同步数据的号，则返回提示
                                                MessageTools.sendMsgById("订单数据暂未同步，请5分钟后再试", core.getMsgList().get(0).getFromUserName());
                                            }

                                        }else
                                        {//如果没匹配到订单号，则走淘口令的检测
                                            String TAO_TOKEN = getMatchers(regex, content);
                                            if (TAO_TOKEN.equals("") || TAO_TOKEN == "") {
                                                dealResource(msg);//当个人消息没有匹配到淘口令时，处理资料消息
                                            } else {
                                                dealTaoToken(TAO_TOKEN);//当匹配到淘口令时，对消息作出处理
                                            }
                                        }

                                    }
//                                    dealOtherMsg( msgHandler, msg);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    core.getMsgList().remove(0);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static String getMatchers(String regex, String content) {//正则匹配与结果
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }
        return stringBuilder.toString();
    }



    public static void dealTaoToken(String TAO_TOKEN) {//正则匹配与结果
        String taoToken = "￥" + TAO_TOKEN + "￥";
        System.out.println("收到淘口令 => " + taoToken);
//        RobotService robot2 = new RobotService(client);
        Map searchMap = robotService.convertLink(taoToken);//转取淘口令，得到click_url  商品id num_iid
        if (searchMap == null) {
            MessageTools.sendMsgById("此商品无相关信息噢", core.getMsgList().get(0).getFromUserName());
        } else {
            String num_iid = (String) searchMap.get("num_iid");
            TaoBaoResult taoBaoResult = robotService.findInfo(num_iid);//通过商品id得到该商品的具体信息，佣金比例，价格和自己的二合一淘口令
            StringBuilder str = new StringBuilder();
            String price = taoBaoResult.getItem_info().getZk_final_price();//原价
            String rate = taoBaoResult.getMax_commission_rate();//佣金率 4.5
            String tpwd = taoBaoResult.getTpwd();//淘口令
            String title = taoBaoResult.getItem_info().getTitle();

            Double priceNumber = Double.valueOf(price);//原价
            Double rateNumber = Double.valueOf(rate);//15% 以结算价格算佣金
//            DecimalFormat df = new DecimalFormat(".##");
            if (taoBaoResult.isHas_coupon()) {
                String coupon = taoBaoResult.getYouhuiquan();//优惠大小

                Double couponNumber = Double.valueOf(coupon);

                Double couponPrice = priceNumber - couponNumber;//券后价

                Double returnNumber = couponPrice * (rateNumber / 100) * 0.75;//返约 返佣大约多少  返佣率一般为0.65  我们返0.75 然后抽取0.25
                if (returnNumber <= 0) {
                    returnNumber = 0.0;
                }
//                String returnPrice = df.format(returnNumber);
                BigDecimal bg = new BigDecimal(returnNumber).setScale(2, RoundingMode.DOWN);
                double returnPrice=bg.doubleValue();
                str.append(title).append("\n").append("原   价: ").append(priceNumber).append(" ￥\n").append("券   后: ").append(couponPrice).append(" ￥\n").append("预计返: ").append(returnPrice).append(" ￥  /:rose\n").append("———————————————").append("\n").append("复制此消息:").append(tpwd).append("\n").append("打开TaoBao使用,实际返以官方返为准,少部分原因会有偏差,后续功能依然只为本群小仙女提供服务");

            } else {
                Double returnNumber = priceNumber * (rateNumber / 100) * 0.75;//返约 返佣大约多少  返佣率一般为0.65  我们0.75 抽0.25
                if (returnNumber <= 0) {
                    returnNumber = 0.0;
                }
//                String returnPrice = df.format(bg.doubleValue());
                BigDecimal bg = new BigDecimal(returnNumber).setScale(2, RoundingMode.DOWN);
                double returnPrice = bg.doubleValue();
                str.append(title).append("\n").append("原   价: ").append(priceNumber).append(" ￥\n").append("券   后: ").append(priceNumber).append(" ￥\n").append("预计返: ").append(returnPrice).append(" ￥  /:rose\n").append("———————————————").append("\n").append("复制此消息:").append(tpwd).append("\n").append("打开TaoBao使用,实际返以官方返为准,少部分原因会有偏差,后续功能依然只为本群小仙女提供服务");
            }
            MessageTools.sendMsgById(str.toString(), core.getMsgList().get(0).getFromUserName());
        }
    }



    private static void dealResource(BaseMsg msg){//没有匹配到淘口令时,处理资料信息
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList("四级资料", "六级资料", "普通话考试", "计算机二级", "教师资格证小学", "教师资格证中学", "会计初级", "会计中级", "注册会计", "公务员考试", "考研规划", "考研数学", "考研英语", "证券从业资格", "计算机学习"));
        if (msg.getContent().equals("资料共享")) {
            StringBuilder stringBuilder = new StringBuilder();
            if (studyService == null) {
                System.out.println("studyService空");
            }
            List<Study> studyList = studyService.findStudyInfoList();
            if (studyList == null) {
                System.out.println("空");
            }
            for (Study study : studyList) {
                Integer id = study.getId();
                String name = study.getName();//资料名称
                String url = study.getUrl();//资料链接
                String pass = study.getPass();//资料密码
                stringBuilder.append(id).append(".").append(name).append("\n");
            }
            stringBuilder.append("------------------------\n").append("发送资料<名称>获取噢");
            MessageTools.sendMsgById(stringBuilder.toString(), core.getMsgList().get(0).getFromUserName());
        }
        if (msg.getContent().equals("帮助")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("------系统提示------\n").append("发送“帮助”查看相关指令\n").append("发送“提现”提取当前余额\n").append("发送“个人信息”查看订单\n").append("发送“资料共享”查看资料\n").append("----------------------------------\n").append("更多功能会放入指令中噢");
            MessageTools.sendMsgById(stringBuilder.toString(), core.getMsgList().get(0).getFromUserName());
        }
        if (msg.getContent().equals("提现")) {
            StringBuilder stringBuilder = new StringBuilder();

            if(msg.isGroupMsg()){//如果是群消息
                stringBuilder.append("本指令不支持群消息，请添加robot后发送指令");
            }else {//如果是个人
                stringBuilder.append("支持提现");
            }
            MessageTools.sendMsgById(stringBuilder.toString(), core.getMsgList().get(0).getFromUserName());
        }

        if (msg.getContent().equals("个人信息")) {
            StringBuilder stringBuilder = new StringBuilder();
            if(msg.isGroupMsg()){//如果是群消息
                stringBuilder.append("本指令不支持群消息，请添加robot后发送指令");
            }else {//如果是个人
                stringBuilder.append("支持查询个人信息");
            }
            MessageTools.sendMsgById(stringBuilder.toString(), core.getMsgList().get(0).getFromUserName());
        }
        if (arrayList.contains(msg.getContent())) {
            StringBuilder stringBuilder = new StringBuilder();
            List<Study> studyList = studyService.findStudyInfo(msg.getContent());
            Study study = studyList.get(0);//取第一行
            Integer id = study.getId();
            String name = study.getName();//资料名称
            String url = study.getUrl();//资料链接
            String pass = study.getPass();//资料密码
            stringBuilder.append(id).append(":").append(name).append(";\n").append("链接：").append(url).append(";     密码： ").append(pass).append("\n").append("链接请快速保存以免失效噢");
            MessageTools.sendMsgById(stringBuilder.toString(), core.getMsgList().get(0).getFromUserName());
        }
    }


private static void dealOtherMsg(IMsgHandlerFace msgHandler,BaseMsg msg){//处理除文本消息以外的其他消息
        if (msg.getType().equals(MsgTypeEnum.PIC.getType())) {
            String result = msgHandler.picMsgHandle(msg);
            MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
        } else if (msg.getType().equals(MsgTypeEnum.VOICE.getType())) {
            String result = msgHandler.voiceMsgHandle(msg);
            MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
        } else if (msg.getType().equals(MsgTypeEnum.VIEDO.getType())) {
            String result = msgHandler.viedoMsgHandle(msg);
            MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
        } else if (msg.getType().equals(MsgTypeEnum.NAMECARD.getType())) {
            String result = msgHandler.nameCardMsgHandle(msg);
            MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
        } else if (msg.getType().equals(MsgTypeEnum.SYS.getType())) { // 系统消息
            msgHandler.sysMsgHandle(msg);
        } else if (msg.getType().equals(MsgTypeEnum.VERIFYMSG.getType())) { // 确认添加好友消息
            String result = msgHandler.verifyAddFriendMsgHandle(msg);
            MessageTools.sendMsgById(result,
                    core.getMsgList().get(0).getRecommendInfo().getUserName());
        } else if (msg.getType().equals(MsgTypeEnum.MEDIA.getType())) { // 多媒体消息
            String result = msgHandler.mediaMsgHandle(msg);
            MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
        }

      }

}
