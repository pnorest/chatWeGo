package cn.taobao.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hzz on 2018/12/20
 */
public class DateUtil {
    /**
     * @description 获取当前时间日期
     * @author hzz
     * @date 2019/1/3 10:01
    **/
    public static Date getCurrentDate() {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(date);
            return sdf.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @description 获取当前时间日期
     * @author zl
     * @date 2019/8/15 10:01
     **/
    public static Date getCurrentDateTime() {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(date);
            return sdf.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description 获取当前时间日期
     * @author zl
     * @date 2019/8/15 10:01
     **/
    public static String getCurrentDateTimeString() {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(date);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    public static Date getYesStartDate() {
        try {
            Date date = new Date(System.currentTimeMillis()-1000*60*60*24);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(date);//2019-11-18 11:17:08
            s=s.substring(0,10)+" 00:00:00";//字符串是对的
            return sdf.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getYesEndDate() {//yyyy-MM-dd HH:mm:ss
        try {
            Date date = new Date(System.currentTimeMillis()-1000*60*60*24);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(date);
            s=s.substring(0,10)+" 23:59:59";
            return sdf.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date getYesMonStartDate() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //获取前月的第一天
            Calendar   cal_1=Calendar.getInstance();//获取当前日期
            cal_1.add(Calendar.MONTH, -1);
            cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            String firstDay = format.format(cal_1.getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String s = sdf.format(date);//2019-11-18 11:17:08
            firstDay=firstDay.substring(0,10)+" 00:00:00";//字符串是对的
            return sdf.parse(firstDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static Date getYesMonEndDate() {//yyyy-MM-dd HH:mm:ss
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //获取前月的最后一天
            Calendar cale = Calendar.getInstance();
            cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
            String lastDay = format.format(cale.getTime());


//            Date date = new Date(System.currentTimeMillis()-1000*60*60*24);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String s = sdf.format(date);
            lastDay=lastDay.substring(0,10)+" 23:59:59";
            return sdf.parse(lastDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //将短时间格式转化为Date yyyy-MM-dd
    public static Date convertStringToDate(String operateDate) {
        try {
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
//            ParsePosition parsePosition=new ParsePosition(0);  ,parsePosition
            Date strToDate=simpleDateFormat.parse(operateDate);
            return strToDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static Date getTheDateAfter(String operateDate) {
        try {

            Calendar calendar=Calendar.getInstance();
            calendar.setTime(convertStringToDate(operateDate));
            calendar.add(Calendar.DAY_OF_MONTH,1);
            Date afterDate=calendar.getTime();
            return  afterDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    /**
     * @description date日期转string
     * @author hzz
     * @date 2018/12/21 9:26
     **/
//    public static String convertDateToString(Date date) {
//        if (date == null) {
//            return null;
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        return sdf.format(date);
//    }

    /**
     * @description date规定日期转string
     * @author hzz
     * @date 2018/12/21 9:26
     **/
//    public static String convertDateToStringDate(Date date) {
//        if (date == null) {
//            return null;
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//        return sdf.format(date);
//    }

////服务请求方提交服务请求的时间 "YYYY-MM-DDTHH:mm:ss.SSS"  "2018-04-17T12:38:19.219"
    public static String getReqTime() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss.SSS");//Illegal pattern character 'T'
        return sdf.format(date);
    }


    //得到现在时间的前20分钟时间
    public static String getTwtMinAgoDateTimeString() {
        try {
            long currentTime = System.currentTimeMillis() - 20 * 60 * 1000;//当前时间的20分钟之前
            Date date = new Date(currentTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(date);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static List<Date> dateSplit(Date startDate, Date endDate)
            throws Exception {
//        if (!startDate.before(endDate))
//            throw new Exception("开始时间应该在结束时间之后");
        Long spi = endDate.getTime() - startDate.getTime();//时间戳
//        Long step = spi / (24 * 60 * 60 * 1000 );// 相隔天数  1小时数（）
        Long step = (spi) / ( 60 * 60 * 1000*1);// 相隔1小时数
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(endDate);
        for (int i = 1; i <= step; i++) {
//            dateList.add(new Date(dateList.get(i - 1).getTime()
//                    - (24 * 60 * 60 * 1000)));// 比上一天减一
            Date date=new Date(dateList.get(i - 1).getTime()
                    - 60 * 60 * 1000*1);
            dateList.add(date);// 比上个半小时减1小时
        }
        dateList.add(startDate);
        return dateList;
    }


//时间与字符串的转换

    /**
     * @description date规定日期转string yyyy-MM-dd HH:mm:ss
     * @author hzz
     * @date 2018/12/21 9:26
     **/
    public static String convertDateToDateString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
