package cn.taobao.entity.order;

import java.util.List;

/**
 * @ClassName Order
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/13 19:22
 * @Version 1.0
 **/
public class Order {
    private Integer code;//返回码
    private List <OrderInfo> data;
    private Boolean has_pre;//是否还有上一页
    private String position_index;//位点字段，由调用方原样传递
    private Boolean has_next;//是否还有下一页
    private Integer page_no;//页码
    private Integer page_size;//页大小
    private String msg;//返回提示


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<OrderInfo> getData() {
        return data;
    }

    public void setData(List<OrderInfo> data) {
        this.data = data;
    }

    public Boolean getHas_pre() {
        return has_pre;
    }

    public void setHas_pre(Boolean has_pre) {
        this.has_pre = has_pre;
    }

    public String getPosition_index() {
        return position_index;
    }

    public void setPosition_index(String position_index) {
        this.position_index = position_index;
    }

    public Boolean getHas_next() {
        return has_next;
    }

    public void setHas_next(Boolean has_next) {
        this.has_next = has_next;
    }

    public Integer getPage_no() {
        return page_no;
    }

    public void setPage_no(Integer page_no) {
        this.page_no = page_no;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
