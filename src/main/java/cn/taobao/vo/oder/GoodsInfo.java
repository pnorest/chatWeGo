package cn.taobao.vo.oder;

/**
 * @ClassName GoodsInfo
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/7 9:23
 * @Version 1.0
 **/
public class GoodsInfo {//订单接口中的商品信息
    private String adzone_id;//推广位管理下的推广位名称对应的ID，同时也是pid=mm_1_2_3中的“3”这段数字 （109645300226）
    private String adzone_name;//推广位管理下的自定义推广位名称  （爱分享）
    private String alimama_rate;//推广者赚取佣金后支付给阿里妈妈的技术服务费用的比率 (0.00)
    private String alimama_share_fee;//技术服务费=结算金额*收入比率*技术服务费率。推广者赚取佣金后支付给阿里妈妈的技术服务费用 (0.00)
    private String alipay_total_price;//买家拍下付款的金额（不包含运费金额）(0.00)
    private String click_time;//通过推广链接达到商品、店铺详情页的点击时间  2019-11-13 10:07:55
    private String deposit_price;//预售时期，用户对预售商品支付的定金金额 0.00
    private String flow_source;//产品类型 --
    private String income_rate;//订单结算的佣金比率+平台的补贴比率  1.35
    private String item_category_name;//商品所属的根类目，即一级类目的名称 其他
    private String item_id;//商品id  605166441855
    private String item_img;//商品图片 "//img.alicdn.com/bao/uploaded/i4/892006832/O1CN01lmZGeR20L6UYIAekP_!!2-item_pic.png"
    private String item_link;//商品链接 "http://item.taobao.com/item.htm?id=605166441855"
    private String item_num;//商品数量  1
    private String item_price;//商品单价 9999.00
    private String item_title;//商品标题 【赠品】好学卡 百门好课限时免费学
    private String order_type;//订单所属平台类型，包括天猫、淘宝、聚划算等  (天猫)
    private String pub_id;//推广者的会员id  (54175988)
    private String pub_share_fee;//结算预估收入=结算金额*提成  以买家确认收货的付款金额为基数，预估您可能获得的收入。因买家退款、您违规推广等原因，可能与您最终收入不一致。最终收入以月结后您实际收到的为准  0.00
    private String pub_share_pre_fee;//付款预估收入=付款金额*提成。指买家付款金额为基数，预估您可能获得的收入。因买家退款等原因，可能与结算预估收入不一致  0.00
    private String pub_share_rate;//从结算佣金中分得的收益比率  100.00
    private String refund_tag;//维权标签，0 含义为非维权 1 含义为维权订单  0
    private String seller_nick;//掌柜旺旺   强泽图书专营店
    private String seller_shop_title;//店铺名称  强泽图书专营店
    private String site_id;//媒体管理下的ID，同时也是pid=mm_1_2_3中的“2”这段数字   1005850130
    private String site_name;//媒体管理下的对应ID的自定义名称  爱分享(手机客户端专享)_54175988
    private String subsidy_fee;//补贴金额=结算金额*补贴比率  0.00
    private String subsidy_rate;//平台给与的补贴比率，如天猫、淘宝、聚划算等  0.00
    private String subsidy_type;//平台出资方，如天猫、淘宝、或聚划算等  --
    private String tb_deposit_time;//预售时期，用户对预售商品支付定金的付款时间  --
    private String tb_paid_time;//订单在淘宝拍下付款的时间  2019-11-13 10:15:09
    private String terminal_type;//成交平台  无线
    private String tk_commission_fee_for_media_platform;//预估专项服务费 0.00
    private String tk_commission_pre_fee_for_media_platform;//结算专项服务费  0.00
    private String tk_commission_rate_for_media_platform;//专项服务费率  0.00
    private String tk_create_time;//订单创建的时间，该时间同步淘宝，可能会略晚于买家在淘宝的订单创建时间  2019-11-13 10:15:12
    private String tk_deposit_time;//预售时期，用户对预售商品支付定金的付款时间，可能略晚于在淘宝付定金时间  --
    private String tk_order_role;//二方：佣金收益的第一归属者； 三方：从其他淘宝客佣金中进行分成的推广者 2
    private String tk_paid_time;//订单付款的时间  该时间同步淘宝，可能会略晚于买家在淘宝的订单创建时间  2019-11-13 10:15:19
    private String tk_status; //订单状态 已付款：指订单已付款，但还未确认收货 已收货：指订单已确认收货，但商家佣金未支付 已结算：指订单已确认收货，且商家佣金已支付成功 已失效：指订单关闭/订单佣金小于0.01元，订单关闭主要有：1）买家超时未付款； 2）买家付款前，买家/卖家取消了订单； 3）订单付款后发起售中退款成功；3：订单结算，12：订单付款， 13：订单失效，14：订单成功  12
    private String tk_total_rate;//提成=收入比率*分成比率。指实际获得收益的比率 1.35
    private String total_commission_fee;//佣金金额=结算金额*佣金比率 0.00
    private String total_commission_rate;//佣金比率 1.35
    private String trade_id;//买家通过购物车购买的每个商品对应的订单编号，此订单编号并未在淘宝买家后台透出  713009185277031338
    private String trade_parent_id;//买家在淘宝后台显示的订单编号  713009185275031338


    public String getAdzone_id() {
        return adzone_id;
    }

    public void setAdzone_id(String adzone_id) {
        this.adzone_id = adzone_id;
    }

    public String getAdzone_name() {
        return adzone_name;
    }

    public void setAdzone_name(String adzone_name) {
        this.adzone_name = adzone_name;
    }

    public String getAlimama_rate() {
        return alimama_rate;
    }

    public void setAlimama_rate(String alimama_rate) {
        this.alimama_rate = alimama_rate;
    }

    public String getAlimama_share_fee() {
        return alimama_share_fee;
    }

    public void setAlimama_share_fee(String alimama_share_fee) {
        this.alimama_share_fee = alimama_share_fee;
    }

    public String getAlipay_total_price() {
        return alipay_total_price;
    }

    public void setAlipay_total_price(String alipay_total_price) {
        this.alipay_total_price = alipay_total_price;
    }

    public String getClick_time() {
        return click_time;
    }

    public void setClick_time(String click_time) {
        this.click_time = click_time;
    }

    public String getDeposit_price() {
        return deposit_price;
    }

    public void setDeposit_price(String deposit_price) {
        this.deposit_price = deposit_price;
    }

    public String getFlow_source() {
        return flow_source;
    }

    public void setFlow_source(String flow_source) {
        this.flow_source = flow_source;
    }

    public String getIncome_rate() {
        return income_rate;
    }

    public void setIncome_rate(String income_rate) {
        this.income_rate = income_rate;
    }

    public String getItem_category_name() {
        return item_category_name;
    }

    public void setItem_category_name(String item_category_name) {
        this.item_category_name = item_category_name;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public String getItem_link() {
        return item_link;
    }

    public void setItem_link(String item_link) {
        this.item_link = item_link;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }

    public String getPub_share_fee() {
        return pub_share_fee;
    }

    public void setPub_share_fee(String pub_share_fee) {
        this.pub_share_fee = pub_share_fee;
    }

    public String getPub_share_pre_fee() {
        return pub_share_pre_fee;
    }

    public void setPub_share_pre_fee(String pub_share_pre_fee) {
        this.pub_share_pre_fee = pub_share_pre_fee;
    }

    public String getPub_share_rate() {
        return pub_share_rate;
    }

    public void setPub_share_rate(String pub_share_rate) {
        this.pub_share_rate = pub_share_rate;
    }

    public String getRefund_tag() {
        return refund_tag;
    }

    public void setRefund_tag(String refund_tag) {
        this.refund_tag = refund_tag;
    }

    public String getSeller_nick() {
        return seller_nick;
    }

    public void setSeller_nick(String seller_nick) {
        this.seller_nick = seller_nick;
    }

    public String getSeller_shop_title() {
        return seller_shop_title;
    }

    public void setSeller_shop_title(String seller_shop_title) {
        this.seller_shop_title = seller_shop_title;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSubsidy_fee() {
        return subsidy_fee;
    }

    public void setSubsidy_fee(String subsidy_fee) {
        this.subsidy_fee = subsidy_fee;
    }

    public String getSubsidy_rate() {
        return subsidy_rate;
    }

    public void setSubsidy_rate(String subsidy_rate) {
        this.subsidy_rate = subsidy_rate;
    }

    public String getSubsidy_type() {
        return subsidy_type;
    }

    public void setSubsidy_type(String subsidy_type) {
        this.subsidy_type = subsidy_type;
    }

    public String getTb_deposit_time() {
        return tb_deposit_time;
    }

    public void setTb_deposit_time(String tb_deposit_time) {
        this.tb_deposit_time = tb_deposit_time;
    }

    public String getTb_paid_time() {
        return tb_paid_time;
    }

    public void setTb_paid_time(String tb_paid_time) {
        this.tb_paid_time = tb_paid_time;
    }

    public String getTerminal_type() {
        return terminal_type;
    }

    public void setTerminal_type(String terminal_type) {
        this.terminal_type = terminal_type;
    }

    public String getTk_commission_fee_for_media_platform() {
        return tk_commission_fee_for_media_platform;
    }

    public void setTk_commission_fee_for_media_platform(String tk_commission_fee_for_media_platform) {
        this.tk_commission_fee_for_media_platform = tk_commission_fee_for_media_platform;
    }

    public String getTk_commission_pre_fee_for_media_platform() {
        return tk_commission_pre_fee_for_media_platform;
    }

    public void setTk_commission_pre_fee_for_media_platform(String tk_commission_pre_fee_for_media_platform) {
        this.tk_commission_pre_fee_for_media_platform = tk_commission_pre_fee_for_media_platform;
    }

    public String getTk_commission_rate_for_media_platform() {
        return tk_commission_rate_for_media_platform;
    }

    public void setTk_commission_rate_for_media_platform(String tk_commission_rate_for_media_platform) {
        this.tk_commission_rate_for_media_platform = tk_commission_rate_for_media_platform;
    }

    public String getTk_create_time() {
        return tk_create_time;
    }

    public void setTk_create_time(String tk_create_time) {
        this.tk_create_time = tk_create_time;
    }

    public String getTk_deposit_time() {
        return tk_deposit_time;
    }

    public void setTk_deposit_time(String tk_deposit_time) {
        this.tk_deposit_time = tk_deposit_time;
    }

    public String getTk_order_role() {
        return tk_order_role;
    }

    public void setTk_order_role(String tk_order_role) {
        this.tk_order_role = tk_order_role;
    }

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

    public String getTk_total_rate() {
        return tk_total_rate;
    }

    public void setTk_total_rate(String tk_total_rate) {
        this.tk_total_rate = tk_total_rate;
    }

    public String getTotal_commission_fee() {
        return total_commission_fee;
    }

    public void setTotal_commission_fee(String total_commission_fee) {
        this.total_commission_fee = total_commission_fee;
    }

    public String getTotal_commission_rate() {
        return total_commission_rate;
    }

    public void setTotal_commission_rate(String total_commission_rate) {
        this.total_commission_rate = total_commission_rate;
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
}
