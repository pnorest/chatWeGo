package cn.zhouyafeng.itchat4j.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.taobao.robot.tbk.SearchResult;
import cn.taobao.robot.tbk.TbkClient;
import cn.taobao.robot.wx.Robot2;
import cn.taobao.vo.TaoBaoResult;
import com.joe.http.client.IHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgCodeEnum;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.CommonTools;

/**
 * 消息处理中心
 *
 * @author https://github.com/yaphone
 * @date 创建时间：2017年5月14日 下午12:47:50
 * @version 1.0
 *
 */
public class MsgCenter {
	private static Logger LOG = LoggerFactory.getLogger(MsgCenter.class);

	private static Core core = Core.getInstance();


	private static IHttpClient client;

	/**
	 * 接收消息，放入队列
	 *
	 * @author https://github.com/yaphone
	 * @date 2017年4月23日 下午2:30:48
	 * @param msgList
	 * @return
	 */
	public static JSONArray produceMsg(JSONArray msgList) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < msgList.size(); i++) {
			JSONObject msg = new JSONObject();
			JSONObject m = msgList.getJSONObject(i);
			m.put("groupMsg", false);// 是否是群消息
			if (m.getString("FromUserName").contains("@@") || m.getString("ToUserName").contains("@@")) { // 群聊消息
				if (m.getString("FromUserName").contains("@@")
						&& !core.getGroupIdList().contains(m.getString("FromUserName"))) {
					core.getGroupIdList().add((m.getString("FromUserName")));
				} else if (m.getString("ToUserName").contains("@@")
						&& !core.getGroupIdList().contains(m.getString("ToUserName"))) {
					core.getGroupIdList().add((m.getString("ToUserName")));
				}
				// 群消息与普通消息不同的是在其消息体（Content）中会包含发送者id及":<br/>"消息，这里需要处理一下，去掉多余信息，只保留消息内容
				if (m.getString("Content").contains("<br/>")) {
					String content = m.getString("Content").substring(m.getString("Content").indexOf("<br/>") + 5);
					m.put("Content", content);
					m.put("groupMsg", true);
				}
			} else {
				CommonTools.msgFormatter(m, "Content");
			}
			if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_TEXT.getCode())) { // words
				// 文本消息
				if (m.getString("Url").length() != 0) {
					String regEx = "(.+?\\(.+?\\))";
					Matcher matcher = CommonTools.getMatcher(regEx, m.getString("Content"));
					String data = "Map";
					if (matcher.find()) {
						data = matcher.group(1);
					}
					msg.put("Type", "Map");
					msg.put("Text", data);
				} else {
					msg.put("Type", MsgTypeEnum.TEXT.getType());
					msg.put("Text", m.getString("Content"));
				}
				m.put("Type", msg.getString("Type"));
				m.put("Text", msg.getString("Text"));
			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_IMAGE.getCode())
					|| m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_EMOTICON.getCode())) { // 图片消息
				m.put("Type", MsgTypeEnum.PIC.getType());
			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_VOICE.getCode())) { // 语音消息
				m.put("Type", MsgTypeEnum.VOICE.getType());
			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_VERIFYMSG.getCode())) {// friends
				// 好友确认消息
				// MessageTools.addFriend(core, userName, 3, ticket); // 确认添加好友
				m.put("Type", MsgTypeEnum.VERIFYMSG.getType());

			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_SHARECARD.getCode())) { // 共享名片
				m.put("Type", MsgTypeEnum.NAMECARD.getType());

			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_VIDEO.getCode())
					|| m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_MICROVIDEO.getCode())) {// viedo
				m.put("Type", MsgTypeEnum.VIEDO.getType());
			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_MEDIA.getCode())) { // 多媒体消息
				m.put("Type", MsgTypeEnum.MEDIA.getType());
			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_STATUSNOTIFY.getCode())) {// phone
				// init
				// 微信初始化消息

			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_SYS.getCode())) {// 系统消息
				m.put("Type", MsgTypeEnum.SYS.getType());
			} else if (m.getInteger("MsgType").equals(MsgCodeEnum.MSGTYPE_RECALLED.getCode())) { // 撤回消息

			} else {
				LOG.info("Useless msg");
			}
			LOG.info("收到消息一条，来自: " + m.getString("FromUserName"));
			result.add(m);
		}
		return result;
	}

	/**
	 * 消息处理
	 *
	 * @author https://github.com/yaphone
	 * @date 2017年5月14日 上午10:52:34
	 * @param msgHandler
	 */
	public static void handleMsg(IMsgHandlerFace msgHandler) {
		try {

			while (true) {
				if (core.getMsgList().size() > 0 && core.getMsgList().get(0).getContent() != null) {
					if (core.getMsgList().get(0).getContent().length() > 0) {
						BaseMsg msg = core.getMsgList().get(0);
						if(msg.isGroupMsg()){//如果是群消息
							List<String> groupIdList=core.getGroupIdList();
							//for (String groupId :groupIdList){//遍历群名  测试群id： "@@e3c20c252fac4ff1129dc5dcc2190218ee417cd47fd28c958ad6f26347bda7cb"
							//if(groupId.equals("@@e3c20c252fac4ff1129dc5dcc2190218ee417cd47fd28c958ad6f26347bda7cb")) {//如果是测试群
						  if (msg.getType() != null ) {
							  if (msg.getType().equals(MsgTypeEnum.TEXT.getType())) {
								  String regex = "\\w{11}";
								  String content = msg.getContent();

								  String TAO_TOKEN = getMatchers(regex, content);
								  if (TAO_TOKEN.equals("") || TAO_TOKEN == "") {
									  //当群消息没有匹配到出淘口令时
								  } else {
									  String taoToken = "￥" + TAO_TOKEN + "￥";
									  System.out.println("收到淘口令 => " + taoToken);
									  Robot2 robot2 = new Robot2(client);
									  Map searchMap = robot2.convertLink(taoToken);//转取淘口令，得到click_url  商品id num_iid
									  if (searchMap == null) {
										  MessageTools.sendMsgById("本商品无优惠券噢", core.getMsgList().get(0).getFromUserName());

									  } else {
										  String num_iid = (String) searchMap.get("num_iid");
										  TaoBaoResult taoBaoResult = robot2.findInfo(num_iid);//通过商品id得到该商品的具体信息，佣金比例，价格和自己的二合一淘口令
										  StringBuilder str = new StringBuilder();


										  String price = taoBaoResult.getItem_info().getZk_final_price();//原价
										  String rate = taoBaoResult.getMax_commission_rate();//佣金率 4.5
										  String tpwd = taoBaoResult.getTpwd();//淘口令
										  String title = taoBaoResult.getItem_info().getTitle();

										  Double priceNumber = Double.valueOf(price);//原价
										  Double rateNumber = Double.valueOf(rate);//15% 以结算价格算佣金
										  DecimalFormat df = new DecimalFormat(".##");

										  if (taoBaoResult.isHas_coupon()) {
											  String coupon = taoBaoResult.getYouhuiquan();//优惠大小

											  Double couponNumber = Double.valueOf(coupon);

											  Double couponPrice = priceNumber - couponNumber;//券后价

											  Double returnNumber = couponPrice * (rateNumber / 100) * 0.8;//返约 返佣大约多少  返佣率一般为0.06  然后抽取0.2
											  if (returnNumber <= 0) {
												  returnNumber = 0.0;
											  }
											  String returnPrice = df.format(returnNumber);

											  str.append(title).append("\n").append("原    价: ").append(priceNumber).append(" 元 \n").append("券后价: ").append(couponPrice).append(" 元 \n").append("———————————————").append("\n").append("复制此消息:").append(tpwd).append("\n").append("打开TaoBao使用,群友们以后可根据购物车商品自主查券省钱噢,以后可以支撑更多功能,欢迎广大群友提意见");

										  } else {
											  Double returnNumber = priceNumber * (rateNumber / 100) * 0.8;//返约 返佣大约多少  返佣率一般为0.06  然后抽取0.2
											  if (returnNumber <= 0) {
												  returnNumber = 0.0;
											  }
											  String returnPrice = df.format(returnNumber);
											  str.append(title).append("\n").append("原    价: ").append(priceNumber).append(" 元 \n").append("券后价: ").append(priceNumber).append(" 元 \n").append("———————————————").append("\n").append("复制此消息:").append(tpwd).append("\n").append("打开TaoBao使用,群友们以后可根据购物车商品自主查券省钱噢,以后可以支撑更多功能,欢迎广大群友提意见");
										  }


										  MessageTools.sendMsgById(str.toString(), core.getMsgList().get(0).getFromUserName());
									  }
								  }
							  }
						  }

						}else//个人消息
						{
							if (msg.getType() != null ) {//对个人回复所有消息，对群只回复文本消息
								try {
									if (msg.getType().equals(MsgTypeEnum.TEXT.getType())) {
										String regex="\\w{11}";
										String content=msg.getContent();

										String TAO_TOKEN=getMatchers(regex,content);
										if(TAO_TOKEN.equals("")||TAO_TOKEN==""){
											//当群消息没有匹配到出淘口令时
										}else {
											String taoToken = "￥" + TAO_TOKEN + "￥";
											System.out.println("收到淘口令 => " + taoToken);
											Robot2 robot2=new Robot2(client);
											Map searchMap = robot2.convertLink(taoToken);//转取淘口令，得到click_url  商品id num_iid
											if(searchMap==null){
												MessageTools.sendMsgById("本商品无优惠券噢", core.getMsgList().get(0).getFromUserName());

											}else {
												String num_iid=(String) searchMap.get("num_iid");
												TaoBaoResult taoBaoResult=robot2.findInfo(num_iid);//通过商品id得到该商品的具体信息，佣金比例，价格和自己的二合一淘口令
												StringBuilder str=new StringBuilder();


												String price=taoBaoResult.getItem_info().getZk_final_price();//原价
												String rate=taoBaoResult.getMax_commission_rate();//佣金率 4.5
												String tpwd=taoBaoResult.getTpwd();//淘口令
												String title=taoBaoResult.getItem_info().getTitle();

												Double  priceNumber  = Double.valueOf(price);//原价
												Double  rateNumber  = Double.valueOf(rate);//15% 以结算价格算佣金
												DecimalFormat df   = new DecimalFormat(".##");

												if (taoBaoResult.isHas_coupon()){
													String coupon=taoBaoResult.getYouhuiquan();//优惠大小

													Double  couponNumber  = Double.valueOf(coupon);

													Double  couponPrice= priceNumber-couponNumber;//券后价

													Double   returnNumber=couponPrice*(rateNumber/100)*0.8;//返约 返佣大约多少  返佣率一般为0.06  然后抽取0.2
													if(returnNumber<=0){
														returnNumber=0.0;
													}
													String returnPrice=df.format(returnNumber);

													str.append(title).append("\n").append("原    价: ").append(priceNumber).append(" 元 \n").append("券后价: ").append(couponPrice).append(" 元 \n").append("———————————————").append("\n").append("复制此消息:").append(tpwd).append("\n").append("打开TaoBao使用,群友们以后可根据购物车商品自主查券省钱噢,以后可以支撑更多功能,欢迎广大群友提意见");

												}else {
													Double   returnNumber=priceNumber*(rateNumber/100)*0.8;//返约 返佣大约多少  返佣率一般为0.06  然后抽取0.2
													if(returnNumber<=0){
														returnNumber=0.0;
													}
													String returnPrice=df.format(returnNumber);
													str.append(title).append("\n").append("原    价: ").append(priceNumber).append(" 元 \n").append("券后价: ").append(priceNumber).append(" 元 \n").append("———————————————").append("\n").append("复制此消息:").append(tpwd).append("\n").append("打开TaoBao使用,群友们以后可根据购物车商品自主查券省钱噢,以后可以支撑更多功能,欢迎广大群友提意见");
												}


												MessageTools.sendMsgById(str.toString(), core.getMsgList().get(0).getFromUserName());
											}
										}
									}
//									if (msg.getType().equals(MsgTypeEnum.TEXT.getType())) {
//										String result = msgHandler.textMsgHandle(msg);
//										MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
//									} else if (msg.getType().equals(MsgTypeEnum.PIC.getType())) {
//
//										String result = msgHandler.picMsgHandle(msg);
//										MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
//									} else if (msg.getType().equals(MsgTypeEnum.VOICE.getType())) {
//										String result = msgHandler.voiceMsgHandle(msg);
//										MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
//									} else if (msg.getType().equals(MsgTypeEnum.VIEDO.getType())) {
//										String result = msgHandler.viedoMsgHandle(msg);
//										MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
//									} else if (msg.getType().equals(MsgTypeEnum.NAMECARD.getType())) {
//										String result = msgHandler.nameCardMsgHandle(msg);
//										MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
//									} else if (msg.getType().equals(MsgTypeEnum.SYS.getType())) { // 系统消息
//										msgHandler.sysMsgHandle(msg);
//									} else if (msg.getType().equals(MsgTypeEnum.VERIFYMSG.getType())) { // 确认添加好友消息
//										String result = msgHandler.verifyAddFriendMsgHandle(msg);
//										MessageTools.sendMsgById(result,
//												core.getMsgList().get(0).getRecommendInfo().getUserName());
//									} else if (msg.getType().equals(MsgTypeEnum.MEDIA.getType())) { // 多媒体消息
//										String result = msgHandler.mediaMsgHandle(msg);
//										MessageTools.sendMsgById(result, core.getMsgList().get(0).getFromUserName());
//									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					core.getMsgList().remove(0);
				}
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}


	}



	public static  String getMatchers(String regex, String content){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		StringBuilder stringBuilder=new StringBuilder();
		while (matcher.find()) {
			stringBuilder.append(matcher.group());
		}
		return stringBuilder.toString();
	}
}
