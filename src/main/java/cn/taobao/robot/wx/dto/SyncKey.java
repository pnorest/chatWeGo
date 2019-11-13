package cn.taobao.robot.wx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SyncKey {
	/*
	 * 消息类型
	 */
	@JsonProperty(value = "Key")
	private Integer key;
	/*
	 * 消息数目
	 */
	@JsonProperty(value = "Val")
	private Integer val;
	/**
	 * 消息数目加上value
	 * @param value
	 */
	public void add(int value){
		this.val += value;
	}


	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}
}
