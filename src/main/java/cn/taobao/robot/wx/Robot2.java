package cn.taobao.robot.wx;

import cn.taobao.robot.Robot;
import cn.taobao.robot.comment.Msg;
import cn.taobao.robot.dto.LoginEvent;
import cn.taobao.robot.exception.RobotException;
import cn.taobao.robot.ext.LoginListener;
import cn.taobao.robot.ext.MsgListener;
import cn.taobao.robot.ext.QrCallback;
import cn.taobao.robot.tbk.Goods;
import cn.taobao.robot.tbk.Link;
import cn.taobao.robot.tbk.SearchResult;
import cn.taobao.robot.tbk.TbkClient;
import cn.taobao.vo.ItemInfo;
import cn.taobao.vo.TaoBaoResult;
import com.alibaba.fastjson.JSON;
import com.joe.http.IHttpClientUtil;
import com.joe.http.client.IHttpClient;
import com.joe.utils.concurrent.ThreadUtil;
import com.joe.utils.img.IQRCode;
import com.joe.utils.parse.json.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Robot2
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/6 10:36
 * @Version 1.0
 **/

public class Robot2 {
    protected static final Logger logger = LoggerFactory.getLogger(Robot2.class);


    private  String tbname="happy月儿弯弯";


    private  String pid="mm_54175988_1005850130_109645300226";


    private  String apkey="dd8fa81a-bb23-5026-254a-1f146e46f08d";

    private static final JsonParser parser = JsonParser.getInstance();
    //将淘口令转换成长链接
    private static final String TKL_PARSER = "http://api.chaozhi.hk/tb/tklParse";


    //执行二维码状态查询使用的线程池
    private static final ExecutorService POOL = ThreadUtil.createPool(ThreadUtil.PoolType.IO);
    //匹配获取二维码结果中url和token的正则
    private static Pattern QRURLPATTERN = Pattern.compile(".*\"url\":\"(.*)\".*\"lgToken\":\"([0-9a-zA-Z]*)\".*");
    //获取二维码链接的地址，其中有一个%d参数（时间戳）需要动态设置
    private static final String GETQRURL = "https://qrlogin.taobao.com/qrcodelogin/generateQRCode4Login" +
            ".do?from=alimama&_ksTS=%d_30&callback=jsonp31";
    //检查二维码链接的地址，其中有一个token需要替换为获取二维码地址时
    private static final String CHECKQR = "https://qrlogin.taobao.com/qrcodelogin/qrcodeLoginCheck" +
            ".do?lgToken=token&defaulturl=http%3A%2F%2Fwww.alimama.com";
    //地址转换链接，其中有两个%d参数，第一个%d参数是对应的auctionid，该参数从搜索商品中获取，第二个%d对应的是时间戳
    private static final String CONVERT = "https://pub.alimama.com/common/code/getAuctionCode" +
            ".json?auctionid=%d&adzoneid=68372906&siteid=19878387&scenes=1&t=%d";
    //搜索链接，其中有三个参数，第一个%s是要搜索的链接，第二个和第三个%d都是时间戳
    private static final String SEARCH = "https://pub.alimama.com/items/search" +
            ".json?q=%s&_t=%d&auctionTag=&perPageSize=40&shopTag=yxjh&t=%d";
    //登录状态，-1表示上次登录过程中发生异常，0表示未开始登录，1表示已经登录（二维码扫描成功并确认），2表示等待扫描二维码，3表示二维码扫描成功未确认，4表示二维码失效
    private volatile LoginEvent event;

    //喵有券，解析短链接转长链接
    private static final String CONVERT_LINK ="https://api.open.21ds.cn/apiv2/doTpwdCovert?apkey=%s&pid=%s&content=%s&tbname=%s";//mm_54175988_1005850130_109645300226 happy月儿弯弯

    private static final String CONVERT_TO_TAO_TOKEN ="http://api.gw.21ds.cn/api/taoke/createTaoPwd?apkey=%s&url=%s";

    private static final String FIND_INFO ="https://api.open.21ds.cn/apiv2/getitemgyurl?apkey=%s&itemid=%s&pid=%s&tbname=%s&tpwd=1&extsearch=1&shorturl=1&hasiteminfo=1";


    //网络请求客户端
    protected IHttpClientUtil clientUtil ;

    protected IHttpClient client;
    public Robot2(IHttpClient client){

        this.client = client;
        this.clientUtil = new IHttpClientUtil(this.client);
    }



//    public TbkClient(MsgListener msgListener, LoginListener loginListener, QrCallback callback, IHttpClient client) {
//        super(msgListener, loginListener, callback, client);
//    }
//
//    @Override
//    protected boolean sendMsg0(String to, String msg) {
//        logger.warn("淘宝客没有发送消息的接口");
//        return false;
//    }
//
//    @Override
//    protected void shutdown0() {
//    }
//
//    /**
//     * 使用二维码登录
//     *
//     * @throws Exception 一般不会抛出异常
//     */
//    @Override
//    protected synchronized void loginByQr() {
//        try {
//            //必须使用独立的IHttpClient，因为同一个client只能维持一个淘宝账号
//            //同时只有在登录时才创建client
//            logger.debug("准备使用二维码登录");
//            //获取二维码链接的URL
//            String getQrUrl = String.format(GETQRURL, System.currentTimeMillis());
//
//            logger.debug("初始化cookie2");
//            //请求首页，请求的时候回设置一个cookie2，用于后续认证
//            clientUtil.executeGet("https://www.alimama.com/index.htm");
//            logger.debug("cookie2初始化完毕，准备获取二维码链接");
//
//            String qrResult = clientUtil.executeGet(getQrUrl);
//            logger.debug("二维码链接获取结果为：{}", qrResult);
//            //提取URL
//            Matcher matcher = QRURLPATTERN.matcher(qrResult);
//
//            String imgUrl;
//            String lgToken;
//            if (matcher.matches()) {
//                imgUrl = "http:" + matcher.group(1);
//                lgToken = matcher.group(2);
//            } else {
//                throw new RuntimeException("未获取到二维码");
//            }
//
//            logger.debug("获取到的二维码路径为：{}；lgToken为：{}", imgUrl, lgToken);
//
//            //读取二维码信息
//            String qrinfo = IQRCode.read(new URL(imgUrl));
//            super.callback.call(qrinfo);
//            //检查二维码状态
//            String checkQr = CHECKQR.replace("token", String.valueOf(lgToken));
//
//            //异步检查用户登录状态，该线程池的数量限制了并发登录的用户数，同时登陆的用户不能超过该线程池的最大线程数
//            POOL.submit(() -> {
//                try {
//                    TbkClient.CheckQrResult checkQrResult;
//                    while (true) {
//                        logger.debug("检查二维码状态");
//                        String checkQrResultStr = clientUtil.executeGet(checkQr);
//                        logger.debug("二维码状态为：{}", checkQrResultStr);
//                        checkQrResult = parser.readAsObject(checkQrResultStr, TbkClient.CheckQrResult.class);
//                        switch (checkQrResult.getCode()) {
//                            case "10000":
//                                //未扫描二维码，并且二维码未过期
//                                logger.debug("请扫描二维码");
//                                updateStatus(LoginEvent.CREATE);
//                                continue;
//                            case "10001":
//                                logger.debug("请在手机上确认");
//                                updateStatus(LoginEvent.WAIT);
//                                continue;
//                            case "10004":
//                                logger.warn("二维码已经失效，请重新获取");
//                                updateStatus(LoginEvent.TIMEOUT);
//                                return;
//                            case "10006":
//                                logger.debug("登录成功");
//                                updateStatus(LoginEvent.SUCCESS);
//                                //刷新cookie
//                                String url = checkQrResult.getUrl();
//                                logger.debug("刷新cookie2");
//                                String refreshResult = clientUtil.executeGet(url);
//                                logger.debug("cookie刷新结果是：{}", refreshResult);
//                                updateStatus(LoginEvent.LOGIN);
//                                return;
//                        }
//                    }
//                } catch (Exception e) {
//                    updateStatus(LoginEvent.LOGINFAIL);
//                    logger.error("登录过程中发生异常，登录失败", e);
//                }
//
//            });
//        } catch (Exception e) {
//            logger.debug("淘宝客机器人登录时发生异常", e);
//            throw new RobotException(e);
//        }
//    }
//
//    /**
//     * 更新登录状态，更新的同时会调用
//     *
//     * @param event 要更新的登录状态
//     */
//    private void updateStatus(LoginEvent event) {
//        if (this.event != event) {
//            super.loginListener.listen(event);
//            this.event = event;
//        }
//    }

    /**
     * 获取当前登录状态
     *
     * @return 当前的登录状态，有可能不是实时的（用户已经退出登陆但是该客户端并不知道）
     */
    public LoginEvent getStatus() {
        return this.event;
    }

    /**
     * 转换链接
     *
     * @param url 要搜索的链接
     * @return 搜索结果，如果搜索异常（例如当前用户登录信息丢失，目前暂时只有这一种情况），那么直接抛出异常，如果搜索
     * 为空那么返回的结果中success字段为false，否则为true
     */
    public SearchResult convert(String url) throws Exception {
        logger.info("开始搜索链接：{}", url);
        String search = String.format(SEARCH, URLEncoder.encode(url, "UTF8"), System.currentTimeMillis(), System
                .currentTimeMillis());
        String searchResultStr = clientUtil.executeGet(search);
        logger.debug("链接[{}]的搜索结果为：{}", url, searchResultStr);
        Map searchMap = (Map) parser.readAsObject(searchResultStr, Map.class).get("data");
        SearchResult searchResult = new SearchResult();

        List<Map> goodsList;//40

        if (searchMap.get("pageList") == null || (goodsList = (List<Map>) searchMap.get("pageList")).isEmpty()) {
            logger.warn("搜索[{}]的结果为空，本次没有搜索到内容", url);
            searchResult.setSuccess(false);
        } else {
            logger.debug("本次搜索到了商品：{}", goodsList);
            //转换商品
            Map goodsMap = goodsList.get(0);//goosMap=59 goodsList=40
            Goods goods = parser.readAsObject(parser.toJson(goodsMap), Goods.class);
            searchResult.setGoods(goods);

            if (goods.getAuctionId() == 0) {
                logger.warn("搜索到的商品{}中不包含auctionid", goods);
                searchResult.setSuccess(false);
            } else {
                logger.debug("开始获取商品的链接");
                searchResult.setSuccess(true);
                searchResult.setLink(convert(goods.getAuctionId()));
                logger.debug("获取到的链接为：{}", searchResult.getLink());
            }
        }
        System.out.println("searchResult:link"+searchResult.getLink()+"goods:"+searchResult.getGoods()+"result"+searchResult.isSuccess());
        return searchResult;
    }


    /**
     * 转换链接
     *
     * @param url 要搜索的链接
     * @return 搜索结果，如果搜索异常（例如当前用户登录信息丢失，目前暂时只有这一种情况），那么直接抛出异常，如果搜索
     * 为空那么返回的结果中success字段为false，否则为true
     */
    public SearchResult convertUrl(String url) throws Exception {
        logger.info("开始搜索链接：{}", url);
        String search = String.format(SEARCH, URLEncoder.encode(url, "UTF8"), System.currentTimeMillis(), System
                .currentTimeMillis());
        String searchResultStr = clientUtil.executeGet(search);
        logger.debug("链接[{}]的搜索结果为：{}", url, searchResultStr);
        Map searchMap = (Map) parser.readAsObject(searchResultStr, Map.class).get("data");
        SearchResult searchResult = new SearchResult();

        List<Map> goodsList;

        if (searchMap.get("pageList") == null || (goodsList = (List<Map>) searchMap.get("pageList")).isEmpty()) {
            logger.warn("搜索[{}]的结果为空，本次没有搜索到内容", url);
            searchResult.setSuccess(false);
        } else {
            logger.debug("本次搜索到了商品：{}", goodsList);
            //转换商品
            Map goodsMap = goodsList.get(0);
            Goods goods = parser.readAsObject(parser.toJson(goodsMap), Goods.class);
            searchResult.setGoods(goods);

            if (goods.getAuctionId() == 0) {
                logger.warn("搜索到的商品{}中不包含auctionid", goods);
                searchResult.setSuccess(false);
            } else {
                logger.debug("开始获取商品的链接");
                searchResult.setSuccess(true);
                searchResult.setLink(convert(goods.getAuctionId()));
                logger.debug("获取到的链接为：{}", searchResult.getLink());
            }
        }
        System.out.println("searchResult:link"+searchResult.getLink()+"goods:"+searchResult.getGoods()+"result"+searchResult.isSuccess());
        return searchResult;
    }


    /**
     * 根据auctionid获取淘客链接
     *
     * @param auctionid auctionid，搜索转换链接时得到的
     * @return 淘客链接
     * @throws Exception 未登录时将抛出异常
     */
    private Link convert(long auctionid) throws Exception {
        logger.debug("开始转换{}", auctionid);
        String convert = String.format(CONVERT, auctionid, System.currentTimeMillis());
        String convertResult = clientUtil.executeGet(convert);
        //这里返回的值总是不对，都是页面
        logger.debug("转换结果为：{}", convertResult);
//        {"invalidKey":null,"ok":true,
//         "data":{"clickUrl":"https://s.click.taobao.com/t?e=m%3D2%26s%3DAR078Xui0KIcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67qDhJtvklRROVNjKoH%2FaCQPn5EtD3ktdAHYbhJnNLmUKthHtT79ACiFFYlcRLpWWU01tZYVbaaQWC6COBoYsMtUkbedrtHIOqGSgT6KvtfMLxgxdTc00KD8%3D",
//                 "couponLink":"https://uland.taobao.com/coupon/edetail?e=pQhNRbmVV9ND3FSiAPfS1B9cMCec5B3uuGdcIL%2FU3XA%2FMowFPQbu6zG2M4HR4efxpySzcc%2FT2p4kaC99jQkxJ582OFTqOHHZ5PdvjO4eOnOie%2FpBy9wBFg%3D%3D&af=1&pid=mm_678590149_208910108_129200277",
//                 "tkCommonRate":"5.30",
//                 "taoToken":"￥5aOtYtIuec4￥",
//                 "couponLinkTaoToken":"￥pKwkYtIGqrR￥",
//                 "qrCodeUrl":"//gqrcode.alicdn.com/img?type=hv&text=https%3A%2F%2Fs.click.taobao.com%2Fjp7Lcwv%3Faf%3D3&h=300&w=300",
//                 "type":"auction","couponShortLinkUrl":"https://s.click.taobao.com/W79Lcwv",
//                 "shortLinkUrl":"https://s.click.taobao.com/jp7Lcwv"},"info":{"ok":true,"message":null}}
        Link link = parser.readAsObject(parser.toJson(parser.readAsObject(convertResult, Map.class).get("data")),
                Link.class);
        logger.debug("得到的链接为：{}", link);
        return link;
    }



    /**
     * 二维码状态检查结果
     */

    private static class CheckQrResult {
        private String message;
        private String code;
        private boolean success;
        private String url;
    }






    /**
     * @param taoToken 淘口令
     * @return 将淘口令转换成长链接
     */
    public static String tklParser(String taoToken) {
        Map<String, String> map = new HashMap<>(4);
        map.put("tkl", taoToken);

        try {
//            HttpResult result = Https.ofPost(TKL_PARSER, map);
//            log.info("httpResult => " + result);
//            if (200 == result.getCode() && null != result.getContent()) {
//                TklCode tklCode = JSON.parseObject(result.getContent(), TklCode.class);
//                if (0 == tklCode.getError_code() && null != tklCode.getData()) {
//                    TklCode.TklUrl tklUrl = JSON.parseObject(JSON.toJSONString(tklCode.getData()), TklCode.TklUrl.class);
//                    if (tklUrl.isSuc()) {
//                        return tklUrl.getUrl();
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 表示没有请求到数据
        return null;
    }


    /**
     * @param taoToken 淘口令
     * @return 使用喵有券将淘口令转换成长链接+商品id(暂时无用)
     */

    public  Map convertLink(String taoToken) {
        try {
            String convertLink = String.format(CONVERT_LINK,apkey,pid,taoToken,tbname);
            String convertResult = clientUtil.executeGet(convertLink);
//            {
//                "data": {
//                "click_url": "https://s.click.taobao.com/t?e=m%3D2%26s%3DyVWHU6Lg4YccQipKwQzePOeEDrYVVa64yK8Cckff7TVRAdhuF14FMYlZk0W5VTRP8sviUM61dt3txjDKKwzMqiyObRCAGeWuv2Xo1FcoH6RbAavvauJSlikFS%2FH8C2lDIWLb4DzFlElwMk5pfidPEeQBKw54kkLDGKFSnMVzg486SQhXWAB0%2BWMqMI5xWHmC1cy%2FnSh2vJGCwbhHwBx5jzzEtIYmXKPgNRMybQuH%2FfDGDmntuH4VtA%3D%3D&union_lens=lensId:0b14e162_11bb_16e40eb4d37_41e3",
//                        "num_iid": "599200217795"
//            },
//                "request_id": "5rfy1k118i84",
//                    "code": 200,
//                    "msg": "success"
//            }
            Map searchMap = (Map) parser.readAsObject(convertResult, Map.class).get("data");
            return searchMap;


        } catch (Exception e) {
            e.printStackTrace();
        }

        // 表示没有请求到数据
        return null;
    }


    /**
     * @param
     * @return 使用喵有券将解析出来的淘宝链接解析成为淘口令
     */
    public String convertToTaoToken(String click_url) {
        try {
// https://s.click.taobao.com/t?e=m%3D2%26s%3DOua0uzJu9oUcQipKwQzePOeEDrYVVa64yK8Cckff7TVRAdhuF14FMZiP9bF55eyLRitN3%2FurF3ztxjDKKwzMqiyObRCAGeWuv2Xo1FcoH6RbAavvauJSlikFS%2FH8C2lDIWLb4DzFlElwMk5pfidPEeQBKw54kkLDGKFSnMVzg4%2BlV%2FRptmtqXksB6%2Ftl8rFe1FX0grNL16FwdKsi3%2Bx5UH9tQc8eOsLf6zeN9GIIRwg%3D&union_lens=lensId:0b588f4b_2c36_16e4356c7a4_1982
            String convertTaoToken = String.format(CONVERT_TO_TAO_TOKEN, apkey,URLEncoder.encode(click_url, "UTF8"));
            String taoToken = clientUtil.executeGet(convertTaoToken);
            return taoToken;
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }


    /**
     * @param
     * @return 使用喵有券使用商品id查询商品优惠信息和淘口令
     */
    public TaoBaoResult findInfo(String num_iid) {
        try {
            TaoBaoResult taoBaoResult=new TaoBaoResult();
            ItemInfo itemInfo=new ItemInfo();
            taoBaoResult.setItem_info(itemInfo);
            String findInfo = String.format(FIND_INFO, apkey,num_iid,pid,tbname);
            String info = clientUtil.executeGet(findInfo);

            Map resultMap = (Map) parser.readAsObject(info, Map.class).get("result");
            LinkedHashMap dataMap=(LinkedHashMap) resultMap.get("data");
            LinkedHashMap itemInfoMap=(LinkedHashMap)dataMap.get("item_info");

            boolean has_coupon= (boolean) dataMap.get("has_coupon");//有无优惠券
            String max_commission_rate= (String) dataMap.get("max_commission_rate");//最大佣金率
            String tpwd= (String) dataMap.get("tpwd");//淘口令
            String youhuiquan= (String) dataMap.get("youhuiquan");//优惠券大小
            String quanlimit= (String) dataMap.get("quanlimit");//优惠券条件，满多少使用
            String nick= (String)itemInfoMap.get("nick");//店名
            String title= (String)itemInfoMap.get("title");//商品标题
            String zk_final_price= (String)itemInfoMap.get("zk_final_price");//商品定价
            taoBaoResult.setTpwd(tpwd);
            taoBaoResult.setHas_coupon(has_coupon);
            taoBaoResult.setMax_commission_rate(max_commission_rate);
            taoBaoResult.setYouhuiquan(youhuiquan);
            taoBaoResult.setQuanlimit(quanlimit);
            taoBaoResult.getItem_info().setNick(nick);
            taoBaoResult.getItem_info().setTitle(title);
            taoBaoResult.getItem_info().setZk_final_price(zk_final_price);

//            Map dataMap=parser.readAsObject(data, Map.class);
//
//
//            Map itemInfoMap = (Map) parser.readAsObject(data, Map.class).get("item_info");
//
//            String Info=(String) itemInfoMap.get("0");
//
//            Map infoMap = (Map) parser.readAsObject(Info, Map.class);//直接得到info这一层
//

            return taoBaoResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
