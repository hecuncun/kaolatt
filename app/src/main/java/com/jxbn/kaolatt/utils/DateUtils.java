package com.jxbn.kaolatt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hecuncun on 2018/4/24
 */

public class DateUtils {
    private static SimpleDateFormat sf_all = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式

    /**
     * 日期的加减,向后算int为正数,向前为负数
     *
     * @param day 当天
     * @param Num 加的天数
     * @return
     */
    public static String getDatePlusStr(String day, int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() + (long) (Num - 1) * 24 * 60 * 60 * 1000);
        String dateOk = df.format(newDate2);
        return dateOk;
    }

    /**
     * 把 time  long格式化后的Date转化成String
     *
     * @param time
     * @return
     */
    public static String getLongToDateString(long time) {
        Date d = new Date(time);
        return sf_all.format(d);
    }

    /**
     * 把 time格式化后的Date转化成String
     *
     * @param time
     * @return
     */
    public static String dateToTime(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
        Date d = new Date(Long.parseLong(time));
        return sf.format(d);
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 计算时间差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 0是相同时间
     */
    public static int getTimeCompareSize(String startTime, String endTime) {
        int i = 0;
        //年-月-日 时-分
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            //开始时间
            Date date1 = dateFormat.parse(startTime);
            //结束时间
            Date date2 = dateFormat.parse(endTime);
            // 结束时间小于开始时间，计算天数
            if (date2.getTime() < date1.getTime()) {
                //结束时间小于开始时间.
                i = (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
            } else if (date2.getTime() > date1.getTime()) {
                i = -1;
            } else if (date2.getTime() == date1.getTime()) {
                //正常情况下的逻辑操作.
                i = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 获取明天的日期
     */
    public static Date getTomorrowDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();


    }
}
