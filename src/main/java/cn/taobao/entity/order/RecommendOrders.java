package cn.taobao.entity.order;

import java.util.Date;

/**
 * @ClassName RecommendOrders
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/12/16 17:13
 * @Version 1.0
 **/
public class RecommendOrders {
    private Integer id;
    //好券直播 ：综合: 3756  女装3767 家居家装3758 数码家电3759 鞋包配饰3762 美妆个护3763 男装3764 内衣3765  母婴3760  食品3761  运动户外3766
    private String material_id;
    private String coupon_amount;
    private String shop_title;
    private String category_id;
    private String coupon_start_fee;
    private String item_id;
    private String coupon_total_count;
    private String user_type;
    private String zk_final_price;
    private String coupon_remain_count;
    private String commission_rate;
    private String coupon_start_time;
    private String title;
    private String item_description;
    private String seller_id;
    private String volume;
    private String coupon_end_time;
    private String pict_url;
    private String level_one_category_name;
    private String level_one_category_id;
    private String category_name;
    private String short_title;
    private Date create_time;
    private String tao_tonken;
    private Integer is_recommend;//0表示不推荐（默认），1表示推荐


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(String coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public String getShop_title() {
        return shop_title;
    }

    public void setShop_title(String shop_title) {
        this.shop_title = shop_title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCoupon_start_fee() {
        return coupon_start_fee;
    }

    public void setCoupon_start_fee(String coupon_start_fee) {
        this.coupon_start_fee = coupon_start_fee;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getCoupon_total_count() {
        return coupon_total_count;
    }

    public void setCoupon_total_count(String coupon_total_count) {
        this.coupon_total_count = coupon_total_count;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getZk_final_price() {
        return zk_final_price;
    }

    public void setZk_final_price(String zk_final_price) {
        this.zk_final_price = zk_final_price;
    }

    public String getCoupon_remain_count() {
        return coupon_remain_count;
    }

    public void setCoupon_remain_count(String coupon_remain_count) {
        this.coupon_remain_count = coupon_remain_count;
    }

    public String getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(String commission_rate) {
        this.commission_rate = commission_rate;
    }

    public String getCoupon_start_time() {
        return coupon_start_time;
    }

    public void setCoupon_start_time(String coupon_start_time) {
        this.coupon_start_time = coupon_start_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getCoupon_end_time() {
        return coupon_end_time;
    }

    public void setCoupon_end_time(String coupon_end_time) {
        this.coupon_end_time = coupon_end_time;
    }

    public String getPict_url() {
        return pict_url;
    }

    public void setPict_url(String pict_url) {
        this.pict_url = pict_url;
    }

    public String getLevel_one_category_name() {
        return level_one_category_name;
    }

    public void setLevel_one_category_name(String level_one_category_name) {
        this.level_one_category_name = level_one_category_name;
    }

    public String getLevel_one_category_id() {
        return level_one_category_id;
    }

    public void setLevel_one_category_id(String level_one_category_id) {
        this.level_one_category_id = level_one_category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getTao_tonken() {
        return tao_tonken;
    }

    public void setTao_tonken(String tao_tonken) {
        this.tao_tonken = tao_tonken;
    }

    public Integer getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(Integer is_recommend) {
        this.is_recommend = is_recommend;
    }
}
