package cn.taobao.ext;



/**
 * 图灵机器人消息
 * @author joe
 *
 */

public class TuLingMsg {
	/**
	 * 内容
	 */
	private String info;
	/**
	 * 图灵机器人APIkey
	 */
	private String key;
	/**
	 * 聊天用户的唯一标示，方便上下文联想
	 */
	private String userid;


	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
