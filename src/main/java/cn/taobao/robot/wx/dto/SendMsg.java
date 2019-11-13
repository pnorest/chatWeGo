package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发送的消息体
 * 
 * @author qiao9
 *
 */

public class SendMsg {
	@JsonProperty(value = "Type")
	private int type;
	@JsonProperty(value = "Content")
	private String content;
	@JsonProperty(value = "ToUserName")
	private String to;
	@JsonProperty(value = "FromUserName")
	private String from;
	@JsonProperty(value = "LocalID")
	private String localId;
	@JsonProperty(value = "ClientMsgId")
	private String clientMsgId;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getClientMsgId() {
		return clientMsgId;
	}

	public void setClientMsgId(String clientMsgId) {
		this.clientMsgId = clientMsgId;
	}
}
