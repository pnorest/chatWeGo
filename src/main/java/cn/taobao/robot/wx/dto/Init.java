package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 初始化能得到该对象，包含用户信息（现在只取了当前有用的信息，还有许多当前无用的信息没有写进来）
 * @author joe
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Init {
	@JsonProperty(value = "SyncKey")
	private SyncKeyList syncKeyList;
	@JsonProperty(value = "User")
	private WxUser user;
	@JsonProperty(value = "BaseResponse")
	private BaseResponse baseResponse;

	public SyncKeyList getSyncKeyList() {
		return syncKeyList;
	}

	public void setSyncKeyList(SyncKeyList syncKeyList) {
		this.syncKeyList = syncKeyList;
	}

	public WxUser getUser() {
		return user;
	}

	public void setUser(WxUser user) {
		this.user = user;
	}

	public BaseResponse getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse(BaseResponse baseResponse) {
		this.baseResponse = baseResponse;
	}
}
