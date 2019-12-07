package cn.taobao.robot;

import cn.taobao.utils.DateUtil;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.beans.Contact;
import cn.zhouyafeng.itchat4j.beans.RecommendInfo;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private static Core simpleCore = Core.getInstance();





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
//		String dateString=DateUtil.getCurrentDateString();
//		String remarkName = recommendInfo.getNickName()+dateString;//预计设置的备注：remarkName
//		//加好友之后就设置备注名称
//		WechatTools.remarkNameByNickName(nickName, remarkName);//这里方法改写来，不会设置错备注
//        //加好友并设置好备注后，将simpleCore(单例模式，修改指向core)，中的好有列表更新
//		updateContactList(nickName,remarkName,msg.getFromUserName());
		String text = nickName + "小伙伴你好,很荣幸与你成为好友/:rose";
		return text;
	}

	private void updateContactList(String inNickName,String inRemarkName,String inUserName){
		List<Contact> contactList = JSON.parseArray(JSON.toJSONString(simpleCore.getContactList()), Contact.class);
		List<JSONObject> jsonObjectList =new ArrayList<>();
		for(Contact contact:contactList){//相对无序的，与好友列表循环匹配，如果匹配到发消息者的id（fromUserName）则可以得到发消息者的信息
			String userName=contact.getUserName();
			if(userName.equals(inUserName)){//匹配到发消息者，则得到发消息者的信息,并退出当前循环
				contact.setRemarkName(inRemarkName);
			}//若没有匹配到发消息者，则不做任何处理，走下面逻辑就行(不可能)
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(contact);
			jsonObjectList.add(jsonObject);
		}
		simpleCore.setContactList(jsonObjectList);
	}

	@Override
	public String mediaMsgHandle(BaseMsg msg) {
		String fileName = msg.getFileName();
		String fileSavePath = filePath + File.separator + fileName; // 这里是需要保存收到的文件路径，文件可以是任何格式如PDF，WORD，EXCEL等。
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.MEDIA.getType(), fileSavePath);
		return "文件" + fileName + "保存成功";
	}

}
