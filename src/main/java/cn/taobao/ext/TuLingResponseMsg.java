package cn.taobao.ext;



/**
 * 图灵机器人响应消息
 * @author joe
 *
 */

public class TuLingResponseMsg {
	/*
	 * 100000：文本类消息
	 * 40001：参数key错误
	 * 40002：请求info为空
	 * 40004：请求次数用完
	 * 40007：数据格式异常
	 */
	private int code;
	private String text;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
