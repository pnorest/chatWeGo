package cn.taobao.vo;

/**
 * @ClassName ItemInfo
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/7 9:27
 * @Version 1.0
 **/
public class ItemInfo {
    private String cat_leaf_name;
    private String cat_name;
    private String item_url;
    private String material_lib_type;
    private String nick;//商品店名
    private Integer num_iid;//商品id
    private String provcity;//省份
    private String title;//	商品标题
    private String zk_final_price;//商品定价


    public String getCat_leaf_name() {
        return cat_leaf_name;
    }

    public void setCat_leaf_name(String cat_leaf_name) {
        this.cat_leaf_name = cat_leaf_name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getMaterial_lib_type() {
        return material_lib_type;
    }

    public void setMaterial_lib_type(String material_lib_type) {
        this.material_lib_type = material_lib_type;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public Integer getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(Integer num_iid) {
        this.num_iid = num_iid;
    }

    public String getProvcity() {
        return provcity;
    }

    public void setProvcity(String provcity) {
        this.provcity = provcity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZk_final_price() {
        return zk_final_price;
    }

    public void setZk_final_price(String zk_final_price) {
        this.zk_final_price = zk_final_price;
    }
}
