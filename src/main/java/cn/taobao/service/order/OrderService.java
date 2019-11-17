package cn.taobao.service.order;

import cn.taobao.entity.Result;
import cn.taobao.mapper.order.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    public Result validOrderNum(String order_num) {//定时任务会把有效订单转储到数据库中
        //pub_share_pre_fee;//付款预估收入
        return new Result(Result.CODE.SUCCESS.getCode(),"111",order_num);


    }

    public Result checkFirstOrder(String remarkName, String order_num) {
        return new Result(Result.CODE.SUCCESS.getCode(),remarkName,order_num);
    }

    public Result bindPeopleAndOrder(String remarkName, String order_num) {
        return new Result(Result.CODE.SUCCESS.getCode(),remarkName,order_num);
    }

    public Result selectLastSixNum(String remarkName, String order_num) {
        return new Result(Result.CODE.SUCCESS.getCode(),remarkName,order_num);
    }

    public List<String> findExistGoodsInfoList() {
        return orderMapper.findExistGoodsInfoList();
    }
}
