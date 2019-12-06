package cn.taobao.robot;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.beans.RecommendInfo;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单示例程序，收到文本信息自动回复原信息，收到图片、语音、小视频后根据路径自动保存
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年4月25日 上午12:18:09
 * @version 1.0
 *
 */
public class SimpleMsgHandler implements IMsgHandlerFace {
	Logger LOG = Logger.getLogger(SimpleMsgHandler.class);


	@Value("${qrPath}")
	private  String qrPath;

	@Value("{picPath}")
	private String picPath;

	@Value("{voicePath}")
	private String voicePath;

	@Value("{videoPath}")
	private String videoPath;

	@Value("{filePath}")
	private String filePath;



	@Override
	public String textMsgHandle(BaseMsg msg) {
		// String docFilePath = "D:/itchat4j/pic/1.jpg"; // 这里是需要发送的文件的路径
		if (!msg.isGroupMsg()) { // 群消息不处理
			// String userId = msg.getString("FromUserName");
			// MessageTools.sendFileMsgByUserId(userId, docFilePath); // 发送文件
			// MessageTools.sendPicMsgByUserId(userId, docFilePath);
			String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
			String result="";
			LOG.info(text);
			if (text.equals("提返")) {
				result= "提返:单号";
			}
//			if (text.equals("222")) {
//				WechatTools.remarkNameByNickName("pnorest", "Hello");
//			}

			return result;
		}
		return null;
	}

	@Override
	public String picMsgHandle(BaseMsg msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
		String picSavePath = picPath + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picSavePath); // 保存图片的路径
		return "图片保存成功";
	}

	@Override
	public String voiceMsgHandle(BaseMsg msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String voiceSavePath = voicePath + File.separator + fileName + ".mp3";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voiceSavePath);
		return "声音保存成功";
	}

	@Override
	public String viedoMsgHandle(BaseMsg msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String videoSavePath = videoPath + File.separator + fileName + ".mp4";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), videoSavePath);
		return "视频保存成功";
	}

	@Override
	public String nameCardMsgHandle(BaseMsg msg) {
		return "收到名片消息";
	}

	@Override
	public void sysMsgHandle(BaseMsg msg) { // 收到系统消息
		String text = msg.getContent();
		LOG.info(text);
	}

	@Override
	public String verifyAddFriendMsgHandle(BaseMsg msg) {
		MessageTools.addFriend(msg, true); // 同意好友请求，false为不接受好友请求
		RecommendInfo recommendInfo = msg.getRecommendInfo();//走的这个
		String nickName = recommendInfo.getNickName();
		String province = recommendInfo.getProvince();
		String city = recommendInfo.getCity();
		String text = nickName + "小伙伴你好,很荣幸与你成为好友/:rose";
		return text;
	}

	@Override
	public String mediaMsgHandle(BaseMsg msg) {
		String fileName = msg.getFileName();
		String fileSavePath = filePath + File.separator + fileName; // 这里是需要保存收到的文件路径，文件可以是任何格式如PDF，WORD，EXCEL等。
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.MEDIA.getType(), fileSavePath);
		return "文件" + fileName + "保存成功";
	}

}
