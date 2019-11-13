package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

/**
 * 响应消息
 *
 * @author qiao9
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WxMsg {
    private BaseResponse baseResponse;
    @JsonProperty(value = "AddMsgCount")
    private Integer addMsgCount;
    @JsonProperty(value = "AddMsgList")
    private List<WxMsgBody> addMsgList;
    @JsonProperty(value = "SyncKey")
    private SyncKeyList syncKeyList;
    //删除好友列表，当在手机端删除某个好友时会收到通知
    @JsonProperty(value = "DelContactList")
    private List<DelContact> delContactList;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DelContact {
        @JsonProperty(value = "UserName")
        private String userName;
        @JsonProperty(value = "ContactFlag")
        private int contactFlag;
    }


    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public Integer getAddMsgCount() {
        return addMsgCount;
    }

    public void setAddMsgCount(Integer addMsgCount) {
        this.addMsgCount = addMsgCount;
    }

    public List<WxMsgBody> getAddMsgList() {
        return addMsgList;
    }

    public void setAddMsgList(List<WxMsgBody> addMsgList) {
        this.addMsgList = addMsgList;
    }

    public SyncKeyList getSyncKeyList() {
        return syncKeyList;
    }

    public void setSyncKeyList(SyncKeyList syncKeyList) {
        this.syncKeyList = syncKeyList;
    }

    public List<DelContact> getDelContactList() {
        return delContactList;
    }

    public void setDelContactList(List<DelContact> delContactList) {
        this.delContactList = delContactList;
    }
}
