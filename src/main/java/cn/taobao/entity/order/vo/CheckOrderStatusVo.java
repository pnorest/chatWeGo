package cn.taobao.entity.order.vo;

/**
 * @ClassName CheckOrderStatusVo
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/12/4 9:36
 * @Version 1.0
 **/
public class CheckOrderStatusVo {
    private String tk_paid_time;//订单付款的时间  该时间同步淘宝，可能会略晚于买家在淘宝的订单创建时间  2019-11-13 10:15:19
    private String tk_status; //订单状态 已付款：指订单已付款，但还未确认收货 已收货：指订单已确认收货，但商家佣金未支付 已结算：指订单已确认收货，且商家佣金已支付成功 已失效：指订单关闭/订单佣金小于0.01元，订单关闭主要有：1）买家超时未付款； 2）买家付款前，买家/卖家取消了订单； 3）订单付款后发起售中退款成功；3：订单结算，12：订单付款， 13：订单失效，14：订单成功  12
    private String user_remark_name;//好友备注名
    private String trade_id;
    private String item_title;
    private String alipay_total_price;
    private String seller_shop_title;
    private Integer new_send_flag;




    public String getTk_paid_time() {
        return tk_paid_time;
    }

    public void setTk_paid_time(String tk_paid_time) {
        this.tk_paid_time = tk_paid_time;
    }

    public String getTk_status() {
        return tk_status;
    }

    public void setTk_status(String tk_status) {
        this.tk_status = tk_status;
    }

    public String getUser_remark_name() {
        return user_remark_name;
    }

    public void setUser_remark_name(String user_remark_name) {
        this.user_remark_name = user_remark_name;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }


    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getAlipay_total_price() {
        return alipay_total_price;
    }

    public void setAlipay_total_price(String alipay_total_price) {
        this.alipay_total_price = alipay_total_price;
    }


    public String getSeller_shop_title() {
        return seller_shop_title;
    }

    public void setSeller_shop_title(String seller_shop_title) {
        this.seller_shop_title = seller_shop_title;
    }

    public Integer getNew_send_flag() {
        return new_send_flag;
    }

    public void setNew_send_flag(Integer new_send_flag) {
        this.new_send_flag = new_send_flag;
    }
}
