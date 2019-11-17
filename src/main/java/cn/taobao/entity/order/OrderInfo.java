package cn.taobao.entity.order;

import java.util.Date;

/**
 * @ClassName OrderInfo
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/7 9:23
 * @Version 1.0
 **/
public class OrderInfo {//订单接口中的商品信息
    private Integer id;//主键
    private String adzoneId;//推广位管理下的推广位名称对应的ID，同时也是pid=mm_1_2_3中的“3”这段数字 （109645300226）
    private String adzoneName;//推广位管理下的自定义推广位名称  （爱分享）
    private String alimamaRate;//推广者赚取佣金后支付给阿里妈妈的技术服务费用的比率 (0.00)
    private String alimamaShareFee;//技术服务费=结算金额*收入比率*技术服务费率。推广者赚取佣金后支付给阿里妈妈的技术服务费用 (0.00)
    private String alipayTotalPrice;//买家拍下付款的金额（不包含运费金额）(0.00)
    private String clickTime;//通过推广链接达到商品、店铺详情页的点击时间  2019-11-13 10:07:55
    private String depositPrice;//预售时期，用户对预售商品支付的定金金额 0.00
    private String flowSource;//产品类型 --
    private String incomeRate;//订单结算的佣金比率+平台的补贴比率  1.35
    private String itemCategoryName;//商品所属的根类目，即一级类目的名称 其他
    private String itemId;//商品id  605166441855
    private String itemImg;//商品图片 "//img.alicdn.com/bao/uploaded/i4/892006832/O1CN01lmZGeR20L6UYIAekP_!!2-item_pic.png"
    private String itemLink;//商品链接 "http://item.taobao.com/item.htm?id=605166441855"
    private String itemNum;//商品数量  1
    private String itemPrice;//商品单价 9999.00
    private String itemTitle;//商品标题 【赠品】好学卡 百门好课限时免费学
    private String orderType;//订单所属平台类型，包括天猫、淘宝、聚划算等  (天猫)
    private String pubId;//推广者的会员id  (54175988)
    private String pubShareFee;//结算预估收入=结算金额*提成  以买家确认收货的付款金额为基数，预估您可能获得的收入。因买家退款、您违规推广等原因，可能与您最终收入不一致。最终收入以月结后您实际收到的为准  0.00
    private String pubSharePreFee;//付款预估收入=付款金额*提成。指买家付款金额为基数，预估您可能获得的收入。因买家退款等原因，可能与结算预估收入不一致  0.00
    private String pubShareRate;//从结算佣金中分得的收益比率  100.00
    private String refundTag;//维权标签，0 含义为非维权 1 含义为维权订单  0
    private String sellerNick;//掌柜旺旺   强泽图书专营店
    private String sellerShopTitle;//店铺名称  强泽图书专营店
    private String siteId;//媒体管理下的ID，同时也是pid=mm_1_2_3中的“2”这段数字   1005850130
    private String siteName;//媒体管理下的对应ID的自定义名称  爱分享(手机客户端专享)_54175988
    private String subsidyFee;//补贴金额=结算金额*补贴比率  0.00
    private String subsidyRate;//平台给与的补贴比率，如天猫、淘宝、聚划算等  0.00
    private String subsidyType;//平台出资方，如天猫、淘宝、或聚划算等  --
    private String tbDepositTime;//预售时期，用户对预售商品支付定金的付款时间  --
    private String tbPaidTime;//订单在淘宝拍下付款的时间  2019-11-13 10:15:09
    private String terminalType;//成交平台  无线
    private String tkCommissionFeeForMediaPlatform;//预估专项服务费 0.00
    private String tkCommissionPreFeeForMediaPlatform;//结算专项服务费  0.00
    private String tkCommissionRateForMediaPlatform;//专项服务费率  0.00
    private String tkCreateTime;//订单创建的时间，该时间同步淘宝，可能会略晚于买家在淘宝的订单创建时间  2019-11-13 10:15:12
    private String tkDepositTime;//预售时期，用户对预售商品支付定金的付款时间，可能略晚于在淘宝付定金时间  --
    private String tkOrderRole;//二方：佣金收益的第一归属者； 三方：从其他淘宝客佣金中进行分成的推广者 2
    private String tkPaidTime;//订单付款的时间  该时间同步淘宝，可能会略晚于买家在淘宝的订单创建时间  2019-11-13 10:15:19
    private String tkStatus; //订单状态 已付款：指订单已付款，但还未确认收货 已收货：指订单已确认收货，但商家佣金未支付 已结算：指订单已确认收货，且商家佣金已支付成功 已失效：指订单关闭/订单佣金小于0.01元，订单关闭主要有：1）买家超时未付款； 2）买家付款前，买家/卖家取消了订单； 3）订单付款后发起售中退款成功；3：订单结算，12：订单付款， 13：订单失效，14：订单成功  12
    private String tkTotalRate;//提成=收入比率*分成比率。指实际获得收益的比率 1.35
    private String totalCommissionFee;//佣金金额=结算金额*佣金比率 0.00
    private String totalCommissionRate;//佣金比率 1.35
    private String tradeId;//买家通过购物车购买的每个商品对应的订单编号，此订单编号并未在淘宝买家后台透出  713009185277031338
    private String tradeParentId;//买家在淘宝后台显示的订单编号  713009185275031338

    private Date orderCreateTime;//订单创建时间
    private String orderStatus;//订单状态


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdzoneId() {
        return adzoneId;
    }

    public void setAdzoneId(String adzoneId) {
        this.adzoneId = adzoneId;
    }

    public String getAdzoneName() {
        return adzoneName;
    }

    public void setAdzoneName(String adzoneName) {
        this.adzoneName = adzoneName;
    }

    public String getAlimamaRate() {
        return alimamaRate;
    }

    public void setAlimamaRate(String alimamaRate) {
        this.alimamaRate = alimamaRate;
    }

    public String getAlimamaShareFee() {
        return alimamaShareFee;
    }

    public void setAlimamaShareFee(String alimamaShareFee) {
        this.alimamaShareFee = alimamaShareFee;
    }

    public String getAlipayTotalPrice() {
        return alipayTotalPrice;
    }

    public void setAlipayTotalPrice(String alipayTotalPrice) {
        this.alipayTotalPrice = alipayTotalPrice;
    }

    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

    public String getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(String depositPrice) {
        this.depositPrice = depositPrice;
    }

    public String getFlowSource() {
        return flowSource;
    }

    public void setFlowSource(String flowSource) {
        this.flowSource = flowSource;
    }

    public String getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(String incomeRate) {
        this.incomeRate = incomeRate;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getPubShareFee() {
        return pubShareFee;
    }

    public void setPubShareFee(String pubShareFee) {
        this.pubShareFee = pubShareFee;
    }

    public String getPubSharePreFee() {
        return pubSharePreFee;
    }

    public void setPubSharePreFee(String pubSharePreFee) {
        this.pubSharePreFee = pubSharePreFee;
    }

    public String getPubShareRate() {
        return pubShareRate;
    }

    public void setPubShareRate(String pubShareRate) {
        this.pubShareRate = pubShareRate;
    }

    public String getRefundTag() {
        return refundTag;
    }

    public void setRefundTag(String refundTag) {
        this.refundTag = refundTag;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getSellerShopTitle() {
        return sellerShopTitle;
    }

    public void setSellerShopTitle(String sellerShopTitle) {
        this.sellerShopTitle = sellerShopTitle;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSubsidyFee() {
        return subsidyFee;
    }

    public void setSubsidyFee(String subsidyFee) {
        this.subsidyFee = subsidyFee;
    }

    public String getSubsidyRate() {
        return subsidyRate;
    }

    public void setSubsidyRate(String subsidyRate) {
        this.subsidyRate = subsidyRate;
    }

    public String getSubsidyType() {
        return subsidyType;
    }

    public void setSubsidyType(String subsidyType) {
        this.subsidyType = subsidyType;
    }

    public String getTbDepositTime() {
        return tbDepositTime;
    }

    public void setTbDepositTime(String tbDepositTime) {
        this.tbDepositTime = tbDepositTime;
    }

    public String getTbPaidTime() {
        return tbPaidTime;
    }

    public void setTbPaidTime(String tbPaidTime) {
        this.tbPaidTime = tbPaidTime;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTkCommissionFeeForMediaPlatform() {
        return tkCommissionFeeForMediaPlatform;
    }

    public void setTkCommissionFeeForMediaPlatform(String tkCommissionFeeForMediaPlatform) {
        this.tkCommissionFeeForMediaPlatform = tkCommissionFeeForMediaPlatform;
    }

    public String getTkCommissionPreFeeForMediaPlatform() {
        return tkCommissionPreFeeForMediaPlatform;
    }

    public void setTkCommissionPreFeeForMediaPlatform(String tkCommissionPreFeeForMediaPlatform) {
        this.tkCommissionPreFeeForMediaPlatform = tkCommissionPreFeeForMediaPlatform;
    }

    public String getTkCommissionRateForMediaPlatform() {
        return tkCommissionRateForMediaPlatform;
    }

    public void setTkCommissionRateForMediaPlatform(String tkCommissionRateForMediaPlatform) {
        this.tkCommissionRateForMediaPlatform = tkCommissionRateForMediaPlatform;
    }

    public String getTkCreateTime() {
        return tkCreateTime;
    }

    public void setTkCreateTime(String tkCreateTime) {
        this.tkCreateTime = tkCreateTime;
    }

    public String getTkDepositTime() {
        return tkDepositTime;
    }

    public void setTkDepositTime(String tkDepositTime) {
        this.tkDepositTime = tkDepositTime;
    }

    public String getTkOrderRole() {
        return tkOrderRole;
    }

    public void setTkOrderRole(String tkOrderRole) {
        this.tkOrderRole = tkOrderRole;
    }

    public String getTkPaidTime() {
        return tkPaidTime;
    }

    public void setTkPaidTime(String tkPaidTime) {
        this.tkPaidTime = tkPaidTime;
    }

    public String getTkStatus() {
        return tkStatus;
    }

    public void setTkStatus(String tkStatus) {
        this.tkStatus = tkStatus;
    }

    public String getTkTotalRate() {
        return tkTotalRate;
    }

    public void setTkTotalRate(String tkTotalRate) {
        this.tkTotalRate = tkTotalRate;
    }

    public String getTotalCommissionFee() {
        return totalCommissionFee;
    }

    public void setTotalCommissionFee(String totalCommissionFee) {
        this.totalCommissionFee = totalCommissionFee;
    }

    public String getTotalCommissionRate() {
        return totalCommissionRate;
    }

    public void setTotalCommissionRate(String totalCommissionRate) {
        this.totalCommissionRate = totalCommissionRate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeParentId() {
        return tradeParentId;
    }

    public void setTradeParentId(String tradeParentId) {
        this.tradeParentId = tradeParentId;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
