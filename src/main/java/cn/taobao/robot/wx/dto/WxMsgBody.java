package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WxMsgBody {
    @JsonProperty("MsgId")
    private String msgId;
    /**
     * 格式：
     *
     * @@1236ff1781b5ae3a7009cd9216db293bc6779587a79896d8fec45771bab61fa9
     * 或者@1236ff1781b5ae3a7009cd9216db293bc6779587a79896d8fec45771bab61fa9 如果是群消息就是第一种
     * ，
     * 如果是个人消息就是第二种
     */
    @JsonProperty("FromUserName")
    private String fromUserName;
    /**
     * 格式：@1236ff1781b5ae3a7009cd9216db293bc6779587a79896d8fec45771bab61fa9
     * 注意：fromUserName是两个@，而toUserName是一个@
     */
    @JsonProperty("ToUserName")
    private String toUserName;
    /**
     * 1：文本消息 3：图片 34：语音 37：好友添加 43：视频 47：表情符号 10000：系统提示（已知红包消息是这个） 10002：消息撤回
     */
    @JsonProperty("MsgType")
    private Integer msgType;
    /**
     * 文本消息的格式： @7565bd3dd47d15972f58eb8bdb6bb695:<br/>
     * 出来报个信，或者是 其中@7565bd3dd47d15972f58eb8bdb6bb695是用户的ID，:<br/>
     * 是固定格式，出来报个信是消息内容，QQ表情属于文本消息，例如[微笑]
     */
    @JsonProperty("Content")
    private String content;
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("ImgStatus")
    private Integer imgStatus;
    @JsonProperty("CreateTime")
    private Long createTime;
    @JsonProperty("VoiceLength")
    private Integer voiceLength;
    @JsonProperty("PlayLength")
    private Integer playLength;
    @JsonProperty("FileName")
    private String fileName;
    @JsonProperty("FileSize")
    private String fileSize;
    @JsonProperty("MediaId")
    private String mediaId;
    @JsonProperty("Url")
    private String url;
    @JsonProperty("AppMsgType")
    private Integer appMsgType;
    @JsonProperty("StatusNotifyCode")
    private Integer statusNotifyCode;
    @JsonProperty("StatusNotifyUserName")
    private String statusNotifyUserName;
    @JsonProperty("ForwardFlag")
    private Integer forwardFlag;
    @JsonProperty("ImgHeight")
    private Integer imgHeight;
    @JsonProperty("ImgWidth")
    private Integer imgWidth;
    @JsonProperty("NewMsgId")
    private Long newMsgId;
    //消息是添加好友时有该值
    @JsonProperty("RecommendInfo")
    private RecommendInfo recommendInfo;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecommendInfo {
        //对应微信ID，例如：@5ede763ab0295296290a122cf016f2700d3eaf5a80dc625acff0ac6e8dce0c25
        @JsonProperty("UserName")
        private String userName;
        //对应昵称
        @JsonProperty("NickName")
        private String nickName;
        //对应QQ号
        @JsonProperty("QQNum")
        private String qqNum;
        //添加好友时填写的附加内容
        @JsonProperty("Content")
        private String content;
        //对应签名
        @JsonProperty("Signature")
        private String signature;
        //对应微信号，例如：qiao1213812243
        @JsonProperty("Alias")
        private String alias;
        @JsonProperty("Ticket")
        private String ticket;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getQqNum() {
            return qqNum;
        }

        public void setQqNum(String qqNum) {
            this.qqNum = qqNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }
    }


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getVoiceLength() {
        return voiceLength;
    }

    public void setVoiceLength(Integer voiceLength) {
        this.voiceLength = voiceLength;
    }

    public Integer getPlayLength() {
        return playLength;
    }

    public void setPlayLength(Integer playLength) {
        this.playLength = playLength;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAppMsgType() {
        return appMsgType;
    }

    public void setAppMsgType(Integer appMsgType) {
        this.appMsgType = appMsgType;
    }

    public Integer getStatusNotifyCode() {
        return statusNotifyCode;
    }

    public void setStatusNotifyCode(Integer statusNotifyCode) {
        this.statusNotifyCode = statusNotifyCode;
    }

    public String getStatusNotifyUserName() {
        return statusNotifyUserName;
    }

    public void setStatusNotifyUserName(String statusNotifyUserName) {
        this.statusNotifyUserName = statusNotifyUserName;
    }

    public Integer getForwardFlag() {
        return forwardFlag;
    }

    public void setForwardFlag(Integer forwardFlag) {
        this.forwardFlag = forwardFlag;
    }

    public Integer getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    public Integer getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Long getNewMsgId() {
        return newMsgId;
    }

    public void setNewMsgId(Long newMsgId) {
        this.newMsgId = newMsgId;
    }

    public RecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }
}
