package cn.taobao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //代支付订单返回信息记录
    @RequestMapping("/addOder")
    public void addOder(String oderId){
        System.out.println("待支付返回数据"+oderId);
    }


    //代支付订单返回信息记录
    @RequestMapping("/updateOder")
    public void updateOder(String oderId){
        System.out.println("待支付返回数据"+oderId);
    }


    //代支付订单返回信息记录
    @RequestMapping("/deleteOder")
    public void deleteOder(String oderId){
        System.out.println("待支付返回数据"+oderId);
    }


    //代支付订单返回信息记录
    @RequestMapping("/pay")
    public void pay(String callback){
        System.out.println("待支付返回数据"+callback);
    }


}
