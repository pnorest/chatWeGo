package cn.taobao.robot.tbk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 搜索出来的商品信息
 *
 * @author joe
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Goods {
    //当前销售价格，单位元
    private double zkPrice;
    //佣金，单位为元
    private double tkCommFee;
    //可使用优惠券金额，单位为元
    private double couponAmount;
    //优惠券剩余数量
    private long couponLeftCount;
    //用于后续转换使用
    private long auctionId;
    //月销量
    private int biz30day;
    //标题
    private String title;
    //店名
    private String shopTitle;

    public double getZkPrice() {
        return zkPrice;
    }

    public void setZkPrice(double zkPrice) {
        this.zkPrice = zkPrice;
    }

    public double getTkCommFee() {
        return tkCommFee;
    }

    public void setTkCommFee(double tkCommFee) {
        this.tkCommFee = tkCommFee;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public long getCouponLeftCount() {
        return couponLeftCount;
    }

    public void setCouponLeftCount(long couponLeftCount) {
        this.couponLeftCount = couponLeftCount;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public int getBiz30day() {
        return biz30day;
    }

    public void setBiz30day(int biz30day) {
        this.biz30day = biz30day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }
}
