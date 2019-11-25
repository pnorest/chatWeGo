package cn.taobao.entity.order.vo;

/**
 * @ClassName OrderVo
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/21 14:36
 * @Version 1.0
 **/
public class OrderVo {
    private String user_remark_name;
    private String lastsix;
    private String tk_status;
    private String pub_share_pre_fee;
    private String trade_id;
    private String trade_parent_id;
    private String tk_create_time;
    private String item_title;
    private String order_status;//0为未结算，1为结算
    private String order_balance_fee;


    public String getUser_remark_name() {
        return user_remark_name;
    }

    public void setUser_remark_name(String user_remark_name) {
        this.user_remark_name = user_remark_name;
    }

    public String getLastsix() {
        return lastsix;
    }

    public void setLastsix(String lastsix) {
        this.lastsix = lastsix;
    }

    public String getTk_status() {
        return tk_status;
    }

    public void setTk_status(String tk_status) {
        this.tk_status = tk_status;
    }

    public String getPub_share_pre_fee() {
        return pub_share_pre_fee;
    }

    public void setPub_share_pre_fee(String pub_share_pre_fee) {
        this.pub_share_pre_fee = pub_share_pre_fee;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getTrade_parent_id() {
        return trade_parent_id;
    }

    public void setTrade_parent_id(String trade_parent_id) {
        this.trade_parent_id = trade_parent_id;
    }

    public String getTk_create_time() {
        return tk_create_time;
    }

    public void setTk_create_time(String tk_create_time) {
        this.tk_create_time = tk_create_time;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_balance_fee() {
        return order_balance_fee;
    }

    public void setOrder_balance_fee(String order_balance_fee) {
        this.order_balance_fee = order_balance_fee;
    }
}
