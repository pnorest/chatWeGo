package cn.taobao.entity.order;

import java.util.Date;

/**
 * @ClassName UserOrder
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/19 15:27
 * @Version 1.0
 **/
public class UserOrder {
    private Integer id;//主键
    private String user_remark_name;//好友备注名
    private String user_sex;//好友性别
    private String user_signature;//好友签名
    private String trade_id;//订单号
    private String trade_parent_id;//父订单号
    private Date create_time;//创建时间
    private Date update_time;//更新时间
    private Integer order_status;//订单状态


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_remark_name() {
        return user_remark_name;
    }

    public void setUser_remark_name(String user_remark_name) {
        this.user_remark_name = user_remark_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_signature() {
        return user_signature;
    }

    public void setUser_signature(String user_signature) {
        this.user_signature = user_signature;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }
}
