package cn.taobao.robot.comment;


/**
 * 通用消息
 *
 * @author joe
 */

public class Msg {
    //消息ID
    private String msgid;
    //消息发送人（接受消息时有该值）
    private String from;
    //消息内容
    private String content;
    //消息接收人（发送消息时有该值）
    private String to;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
}
