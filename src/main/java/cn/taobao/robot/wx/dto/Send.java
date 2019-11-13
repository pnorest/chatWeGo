package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 要发送的消息对象
 * 
 * @author qiao9
 *
 */

public class Send {
	@JsonProperty(value = "BaseRequest")
	private BaseRequest request;
	@JsonProperty(value = "Msg")
	private SendMsg msg;
	@JsonProperty(value = "Scene")
	private int scene;

	public BaseRequest getRequest() {
		return request;
	}

	public void setRequest(BaseRequest request) {
		this.request = request;
	}

	public SendMsg getMsg() {
		return msg;
	}

	public void setMsg(SendMsg msg) {
		this.msg = msg;
	}

	public int getScene() {
		return scene;
	}

	public void setScene(int scene) {
		this.scene = scene;
	}
}
