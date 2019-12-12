package cn.zhouyafeng.itchat4j.beans;

/**
 * @ClassName GroupContact
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/12/12 10:54
 * @Version 1.0
 **/
public class GroupContact {
    private String ChatRoomId;
    private String Sex;
    private String AttrStatus;
    private String Statues;
    private String EncryChatRoomId;//月儿群测试时候的值  @5b017cca2110d92276aaeea136574f09
    private String UserName;//"UserName" -> "@@c69c6edaeddb7ba9a293d78df15c2f5d0897ac1774a548c46e47c437c2eef674"
    private String City;
    private String NickName;//"NickName" -> "<span class="emoji emoji1f338"></span> 月儿福利群🈲 互加"
    private String Province;
    private String Alias;
    private String UniFriend;
    private String ContactFlag;
    private String HeadImgUrl;
    private String Signature;
    private String RemarkName;//没有remarkname，用nickname来


    public String getChatRoomId() {
        return ChatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        ChatRoomId = chatRoomId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(String attrStatus) {
        AttrStatus = attrStatus;
    }

    public String getStatues() {
        return Statues;
    }

    public void setStatues(String statues) {
        Statues = statues;
    }

    public String getEncryChatRoomId() {
        return EncryChatRoomId;
    }

    public void setEncryChatRoomId(String encryChatRoomId) {
        EncryChatRoomId = encryChatRoomId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getUniFriend() {
        return UniFriend;
    }

    public void setUniFriend(String uniFriend) {
        UniFriend = uniFriend;
    }

    public String getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(String contactFlag) {
        ContactFlag = contactFlag;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        HeadImgUrl = headImgUrl;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }
}
