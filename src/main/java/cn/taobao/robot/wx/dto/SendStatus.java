package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SendStatus {
	@JsonProperty(value = "BaseResponse")
	private BaseResponse baseResponse;
	@JsonProperty(value = "MsgID")
	private String msgId;
	@JsonProperty(value = "LocalID")
	private String localId;

	public BaseResponse getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse(BaseResponse baseResponse) {
		this.baseResponse = baseResponse;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}
}
