package cn.taobao.vo;

/**
 * @ClassName TaoBaoResult
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/7 9:20
 * @Version 1.0
 **/
public class TaoBaoResult {

    private Integer category_id;//商品分类ID
    private String coupon_click_url;//二合一链接
    private String coupon_end_time;//优惠券有效期截止日期  没有优惠券，则此项不显示
    private String coupon_info;//优惠券信息  没有优惠券，则此项不显示
    private String coupon_remain_count;//优惠券剩余量  没有优惠券，则此项不显示
    private String coupon_total_count;//优惠券总量 没有优惠券，则此项不显示
    private String coupon_start_time;//优惠券开始时间 没有优惠券，则此项不显示
    private String coupon_type;//	优惠券类型 1 公开券，2 私有券，3 妈妈券
    private Integer item_id;//商品ID
    private boolean has_coupon;//是否有优惠券 true，有；false，没有
    private String max_commission_rate;//高佣金比例 计划中的最高佣金，如果阿里妈妈帐户为初级，则会走通用佣金
    private String item_url;//商品淘客链接 同样为高佣，has_coupon为false时，建议使用此链接
    private String tpwd;//淘口令 商品有优惠券时，口令打开为二合一链接页面，无优惠券时，口令打开为商品页面（注：均为高佣金淘客链接，购买之后均有佣金）
    private String short_url;//	短链接 商品有优惠券时，短链接打开为二合一链接页面，无优惠券时，口令打开为商品页面（注：均为高佣金淘客链接，购买之后均有佣金）
    private String youhuiquan;//优惠券面额 没有优惠券则不显示此字段
    private String quanlimit;//优惠券使用条件 	优惠券使用限制条件，即满xx元使用
    private ItemInfo item_info;//	商品信息列表




    public String getCoupon_click_url() {
        return coupon_click_url;
    }

    public void setCoupon_click_url(String coupon_click_url) {
        this.coupon_click_url = coupon_click_url;
    }

    public String getCoupon_end_time() {
        return coupon_end_time;
    }

    public void setCoupon_end_time(String coupon_end_time) {
        this.coupon_end_time = coupon_end_time;
    }

    public String getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(String coupon_info) {
        this.coupon_info = coupon_info;
    }

    public String getCoupon_remain_count() {
        return coupon_remain_count;
    }

    public void setCoupon_remain_count(String coupon_remain_count) {
        this.coupon_remain_count = coupon_remain_count;
    }

    public String getCoupon_total_count() {
        return coupon_total_count;
    }

    public void setCoupon_total_count(String coupon_total_count) {
        this.coupon_total_count = coupon_total_count;
    }

    public String getCoupon_start_time() {
        return coupon_start_time;
    }

    public void setCoupon_start_time(String coupon_start_time) {
        this.coupon_start_time = coupon_start_time;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }



    public String getMax_commission_rate() {
        return max_commission_rate;
    }

    public void setMax_commission_rate(String max_commission_rate) {
        this.max_commission_rate = max_commission_rate;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getTpwd() {
        return tpwd;
    }

    public void setTpwd(String tpwd) {
        this.tpwd = tpwd;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getYouhuiquan() {
        return youhuiquan;
    }

    public void setYouhuiquan(String youhuiquan) {
        this.youhuiquan = youhuiquan;
    }

    public String getQuanlimit() {
        return quanlimit;
    }

    public void setQuanlimit(String quanlimit) {
        this.quanlimit = quanlimit;
    }

    public ItemInfo getItem_info() {
        return item_info;
    }

    public void setItem_info(ItemInfo item_info) {
        this.item_info = item_info;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public boolean isHas_coupon() {
        return has_coupon;
    }

    public void setHas_coupon(boolean has_coupon) {
        this.has_coupon = has_coupon;
    }
}
