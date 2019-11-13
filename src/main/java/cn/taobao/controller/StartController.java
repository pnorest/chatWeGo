package cn.taobao.controller;

import cn.taobao.robot.SimpleMsgHandler;
import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StartController
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/7 15:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/chat")
public class StartController {

    @Value("${qrPath}")
    private  String qrPath;

    //开启微信登陆
    @RequestMapping("/start")
    public void start(){
//        String qrPath = "/home/taochat/itchat4j/login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
        IMsgHandlerFace msgHandler = new SimpleMsgHandler(); // 实现IMsgHandlerFace接口的类
        Wechat wechat = new Wechat(msgHandler, qrPath); // 【注入】
        wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片
    }








}
