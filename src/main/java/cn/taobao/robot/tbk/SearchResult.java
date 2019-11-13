package cn.taobao.robot.tbk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 搜索结果
 *
 * @author joe
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    //搜索是否有结果，如果有结果那么该值为true，否则为false
    private boolean success;
    //搜索到的商品
    private Goods goods;
    //转换的链接
    private Link link;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
