package cn.taobao.test;


import cn.taobao.robot.Robot;
import cn.taobao.robot.RobotFactory;
import com.joe.http.client.IHttpClient;
import com.joe.utils.img.IQRCode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 *
 * @author joe
 * @version 2017.12.22 15:08
 */
public class TestTaoBao {
    public static void main(String[] args) {
        try{
            testTBKRobot();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

//    /**
//     * 测试微信机器人
//     */
//    private static void testWXRobot() {
//        System.setProperty("jsse.enableSNIExtension", "false");//解决linux 中，httpClient 异常问题
//        Map<String, Msg> msgHistory = new HashMap<>();
//        TlMsgService service = new TlMsgService();
//        Robot robot = RobotFactory.create(new MsgListener() {
//            private Map<String, Boolean> robotStart = new HashMap<>();
//
//            @Override
//            public void friendMsg(Msg msg, Robot wxRobot) {
//                System.out.println("好友消息是：" + msg);
//                msgHistory.put(msg.getMsgid(), msg);
//                Boolean start = robotStart.computeIfAbsent(msg.getFrom(), k -> false);
//                if ("机器人".equals(msg.getContent())) {
//                    robotStart.put(msg.getFrom(), true);
//                    wxRobot.sendMsg(msg.getFrom(), "进入机器人聊天模式，发送[exit]退出");
//                    return;
//                } else if ("菜单".equals(msg.getContent())) {
//                    wxRobot.sendMsg(msg.getFrom(), "发送[机器人]进入机器人聊天模式，发送[exit]退出");
//                    return;
//                } else if ("exit".equals(msg.getContent())) {
//                    robotStart.put(msg.getFrom(), false);
//                } else if (start) {
//                    wxRobot.sendMsg(msg.getFrom(), "机器人：" + service.talk(msg.getContent(), msg.getFrom()));
//                }
//            }
//
//            @Override
//            public void groupMsg(Msg msg, Robot wxRobot) {
//
//            }
//
//            @Override
//            public void revokeMsg(String msgid, Robot wxRobot) {
//                System.out.println("撤回的消息是：" + msgid);
//                Msg msg = msgHistory.get(msgid);
//                if (msg != null) {
//                    wxRobot.sendMsg(msg.getFrom(), "哈哈，还想撤回，我已经看见了你发的消息[" + msg.getContent() + "]了");
//                }
//            }
//
//            @Override
//            public boolean addMsg(String account, String nikeName, String content, Robot wxRobot) {
//                return false;
//            }
//
//            @Override
//            public String remarke(String account, String nikeName, String content, Robot wxRobot) {
//                return null;
//            }
//        }, System.out::println, TestTaoBao::saveQR, IHttpClient.builder().build(), Robot
//                .RobotType.WX);
//        robot.login();
//    }


    /**
     * 测试淘宝客
     */
    private static void testTBKRobot() {
        Robot robot = RobotFactory.create(null, System.out::println, TestTaoBao::saveQR, IHttpClient.builder().build(), Robot
                .RobotType.TBK);
        robot.login();
    }

    /**
     * 保存二维码到本地
     *
     * @param data 二维码数据
     */
    private static void saveQR(String data) {//data https://login.weixin.qq.com/l/QaG77NQ_Iw==
//        try {
//            System.out.println("保存二维码");
//            IQRCode.create(data, new FileOutputStream("/home/taochat/itchat4j/a.jpg"), 200, 200);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
