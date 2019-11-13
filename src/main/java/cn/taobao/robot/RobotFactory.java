package cn.taobao.robot;

import cn.taobao.robot.exception.RobotException;
import cn.taobao.robot.ext.LoginListener;
import cn.taobao.robot.ext.MsgListener;
import cn.taobao.robot.ext.QrCallback;
import cn.taobao.robot.tbk.TbkClient;
import cn.taobao.robot.wx.WxRobot;
import com.joe.http.client.IHttpClient;


/**
 * 机器人工厂
 *
 * @author joe
 */
public class RobotFactory {
    public static Robot create(MsgListener msgListener, LoginListener loginListener, QrCallback callback, IHttpClient
            client,
                               Robot.RobotType type) {
        switch (type) {
            case WX:
                return new WxRobot(msgListener, loginListener, callback, client);
            case TBK:
                return new TbkClient(msgListener, loginListener, callback, client);
            default:
                throw new RobotException("当前没有指定机器人：" + type);
        }
    }
}
