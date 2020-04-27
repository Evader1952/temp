package com.mp.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tuchuan
 * @description
 * @date 2017/7/6 14:41
 */
public class DateUtil {
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_YYYYMMDDHHMMSS_WITH_MILLSECOND = "yyyyMMddHHmmssS";
    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MM = "yyyy-MM";


    /**
     *@description(将 date 转换成指定格式的字符串)
     *@return java.lang.String
     */
    public static String formatDate(Date date, String format){
        LocalDateTime localDateTime=LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(format);

        return localDateTime.format(formatter);
    }

    /**
     *@description(是否闰年)
     *@return boolean
     */
    public static boolean isLeapYear(Date date){
        LocalDate localDate= date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.isLeapYear();
    }

    /**
     * string 转date
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = df.parse(dateStr);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    /**
     *@description(间隔天数)
     *@return long
     */
    public static long betweenDays(Date start,Date end){
        LocalDateTime nowDateTime= LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
        LocalDateTime thirdDateTime= LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
        long between = ChronoUnit.DAYS.between(nowDateTime, thirdDateTime);
        return between;
    }

    /**
     *@description(几天前的日期)
     *@return Date
     */
    public static Date getBeforeDate(Date date,Integer day){
        LocalDateTime localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime beforeDate = localDate.plusDays(day * -1);
        return Date.from(beforeDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *@description(几月前的日期)
     *@return Date
     */
    public static Date getBeforeMonth(Date date,Integer month){
        LocalDateTime localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDate.plusMonths(month * -1);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *@description(几年前的日期)
     *@return Date
     */
    public static Date getBeforeYear(Date date,Integer year){
        LocalDateTime localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDate.plusMonths(year * -1);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     *@Description(将带T 的字符串，转成 date类型)
     *@return java.util.Date
     * 请使用 org.apache.commons.lang3.time.FastDateFormat ,更加方便
     */
    @Deprecated
    public static Date parseDate(String str){
        TemporalAccessor date = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault()).parse(str);

        return Date.from(Instant.from(date));
    }

    /**
     * 加月数
     *
     * @param date 初始时间
     * @param num  数值
     * @param type 类型，年月日
     * @return
     */
    public static Date addTime(Date date, Integer num, Integer type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        return date;
    }

    public static String getDateStringFromToday(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return sdf.format(calendar.getTime());
    }

    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static String getMonthFirstDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (DataUtil.isNotEmpty(date)) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.DATE, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间前3分钟
     *
     * @param
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -3);
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    protected static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 获取过去时间
     *
     * @param type 1-过去七天 2-过去一个月 3-过去三个月 任意为过去一年
     * @return
     */
    public static Boolean getBeforeWeekDate(String type, Date date) {
        Calendar c = Calendar.getInstance();
        if (type == "1") {
            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -6);
            Long time1 = c.getTime().getTime();
            Long time2 = System.currentTimeMillis();
            if (date.getTime() >= time1 && date.getTime() <= time2) {
                return true;
            }
            return false;
        } else if (type == "2") {
            //过去一月
            c.setTime(new Date());
            c.add(Calendar.DATE, -29);
            Long time1 = c.getTime().getTime();
            Long time2 = System.currentTimeMillis();
            if (date.getTime() >= time1 && date.getTime() <= time2) {
                return true;
            }
            return false;
        } else if (type == "3") {
            //过去三个月
            c.setTime(new Date());
            c.add(Calendar.DATE, -89);
            Long time1 = c.getTime().getTime();
            Long time2 = System.currentTimeMillis();
            if (date.getTime() >= time1 && date.getTime() <= time2) {
                return true;
            }
            return false;
        } else {
            //过去n天
            c.setTime(new Date());
            c.add(Calendar.YEAR, -1);
            Long time1 = c.getTime().getTime();
            Long time2 = System.currentTimeMillis();
            if (date.getTime() >= time1 && date.getTime() <= time2) {
                return true;
            }
            return false;
        }
    }

    /**
     * 判断一个日期是星期几
     *
     * @param
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取一个日期的前in天
     *
     * @param
     */
    public static Date getBeforeDay(Date date, Integer in) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -in);
        return c.getTime();
    }

    /**
     * 获取一个日期的后in天
     *
     * @param
     */
    public static Date getAfterDay(Date date, Integer in) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, +in);
        return c.getTime();
    }

    /**
     * 获取一个日期的后in月
     *
     * @param
     */
    public static Date getAfterMonth(Date date, Integer in) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, +in);
        return c.getTime();
    }

    /**
     * 获取一个日期的前in月
     *
     * @param
     */
    public static Date getBeforeMonty(Date date, Integer in) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -in);
        return c.getTime();
    }



    /**
     * 判断两个日期是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    /**
     * 两个String类型数字相加
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        int days = Integer.parseInt(String.valueOf(between_days));
        return days < 0 ? (0 - days) : days;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween2(Date smdate, Date bdate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
        } catch (Exception e) {
            e.printStackTrace();
            return 10000;
        }
        try {
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        int days = Integer.parseInt(String.valueOf(between_days));
        return days;
    }

    /**
     * 得到一个日期是几号
     *
     * @param
     */
    public static String dayOfDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        String s1 = s.substring(8, 10);
        if (s1.substring(0).equals("0")) {
            return s1.substring(1);
        } else {
            return s1;
        }
    }

    /**
     * 得到一个日期是几月
     *
     * @param
     */
    public static String dayOfMonth(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        String s1 = s.substring(5, 7);
        if (s1.substring(0, 1).equals("0")) {
            return s1.substring(1);
        } else {
            return s1;
        }
    }

    /**
     * 得到一个日期是几年
     */
    public static String dayOfYear(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        return s.substring(0, 4);
    }

    /**
     * 得到一个月份有多少天
     *
     * @param
     */
    public static String monthOfDays(String year, String month) {
        if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8") || month.equals("10") || month.equals("12")) {
            return "31";
        } else if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
            return "30";
        } else {
            if (Long.valueOf(year) % 4 == 0 && Long.valueOf(year) % 100 != 0 || Long.valueOf(year) % 400 == 0) {
                return "29";
            } else {
                return "28";
            }
        }
    }

    /**
     * 获取格式化时间
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return sdf.format(new Date());
    }

    public static String[] getSevenDays() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String ym = sdf.format(date);
        String[] days = new String[7];
        int day = date.getDate();
        for (int i = 0; i < days.length; i++) {
            days[i] = new StringBuffer(ym + '-').append(day - days.length + i).toString();
        }
        return days;
    }

    public static String[] getSixMonths() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String[] months = new String[6];
        for (int i = 0; i < months.length; i++) {
            months[i] = simpleDateFormat.format(getBeforeMonty(date, i)).toString();
        }
        String[] result_months = new String[6];
        int n = 0;
        for (int i = months.length - 1; i > -1; i--) {
            result_months[n] = months[i];
            n++;
        }
        return result_months;
    }

    /**
     * 验证时间格式(yyyy-MM-dd)
     */
    public static boolean verifyTime(String date) {
        if (date == null) {
            return false;
        }
        String reg = "^\\d{4}\\D+\\d{2}\\D+\\d{2}$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(date);
        return m.matches();
    }

    /**
     * 得到当前月份的上n个月
     *
     * @param
     */
    public static String getBeforeMonth(Integer i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -i);    //得到前一个月
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.valueOf(month);
    }

    /**
     * double类型保留两个小数
     *
     * @param
     */
    public static String doubleOfString(String s) {
        double f = Double.parseDouble(s);
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(f1);
    }

    /**
     * 两个时间相减
     * @param
     * @throws IOException
     */
//    public static Long twoDatesSubtract(Date date1) {
//        DateFormat df = new SimpleDateFormat("HH:mm:ss");
//        try{
//            Date d1 = df.parse(df.format(date1));
//            Date d2 = df.parse("23:59:59");
//            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
//            long days = diff / (1000 * 60 * 60 * 24);
//
//            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
//            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");
//        } catch (Exception e) {
//
//        }
//    }

    /**
     * 两个时间相减
     *
     * @param date1 大日期
     * @param date2 小日期
     * @return 分钟
     */
    public static long twoDateSubtract(Date date1, Date date2, Boolean second) {
        if (date1.getTime() < date2.getTime()) {
            return 0;
        }
        if (second) {
            return (date1.getTime() - date2.getTime()) / (1000);
        } else {
            return (date1.getTime() - date2.getTime()) / (1000 * 60);
        }
    }

    /**
     * 给时间加上几个小时
     *
     * @param day  当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    public static String addDateMinut(String day, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
//        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
//        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    /**
     * 获取一个时间的之前时间
     *
     * @param date   当前时间
     * @param minute 分钟
     * @return
     */
    public static Date getBeforeTime(Date date, int minute) {
        long time = date.getTime();
        long milliseconds = time - minute * 60 * 1000;
        Date millisecondDate = new Date(milliseconds);
        return millisecondDate;
    }

    /**
     * 获取一个时间的之后时间
     *
     * @param date   当前时间
     * @param minute 分钟
     * @return
     */
    public static Date getAfterTime(Date date, int minute) {
        long time = date.getTime();
        long milliseconds = time + minute * 60 * 1000;
        Date millisecondDate = new Date(milliseconds);
        return millisecondDate;
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
//        return days + " days " + hours + " hours " + minutes + " minutes "
//                + seconds + " seconds ";
        String str = "";
        if (days > 0) {
            str += " " + days + " 天";
        }
        if (hours > 0) {
            str += " " + hours + " 小时";
        }
        if (minutes > 0) {
            str += " " + minutes + " 分钟";
        }
        if (DataUtil.isEmpty(str)) {
            if (seconds > 0) {
                str += " " + seconds + " 秒";
            }
        }
        return str;
    }

    /**
     * 获取一个日期map用以统计金额数据
     *
     * @param type 统计类型 0=天，1=月，2=年
     * @param num  统计多久
     * @return
     */
    public static Map<String, String> getTimeMap(Integer type, Integer num) {
        if (type == null || (!type.equals(0) && !type.equals(1) && !type.equals(2))) {
            return null;
        }
        if (num == null || num < 1) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat;
        Date nowDate = new Date();
        if (type.equals(0)) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < num; i++) {
                map.put(simpleDateFormat.format(getBeforeDay(nowDate, i)).toString(), "0");
            }
        }
        if (type.equals(1)) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            for (int i = 0; i < num; i++) {
                map.put(simpleDateFormat.format(getBeforeMonty(nowDate, i)).toString(), "0");
            }
        }
        if (type.equals(2)) {
            simpleDateFormat = new SimpleDateFormat("yyyy");
            for (int i = 0; i < num; i++) {
                map.put(simpleDateFormat.format(getBeforeYear(nowDate, i)).toString(), "0");
            }
        }
        return map;
    }

    public static List<Map<String, String>> sortTimeMap(Map<String, String> map) {
        if (DataUtil.isEmpty(map)) {
            return null;
        }
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> list_map;
        for (String str : map.keySet()) {
            list_map = new HashMap<>();
            list_map.put("key_str", str);
            list_map.put("value_str", map.get(str));
            list.add(list_map);
        }
        Collections.sort(list, new Comparator<Map<String, String>>() {
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return o2.get("key_str").compareTo(o1.get("key_str"));
            }
        });
        return list;
    }

    /**
     * 获取日期字符串
     *
     * @param date 时间
     * @param type 字符串格式（null=年月日时分秒 1=年 2=年月 3=年月日）
     * @return
     */
    public static String getDateString(Date date, Integer type) {
        if (DataUtil.isEmpty(type)) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date).toString();
        }
        if (type.equals(1)) {
            return new SimpleDateFormat("yyyy").format(date).toString();
        }
        if (type.equals(2)) {
            return new SimpleDateFormat("yyyy-MM").format(date).toString();
        }
        if (type.equals(3)) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date).toString();
        }
        return null;
    }

    public static Date getDateStringAfterYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }





    public static Long twoDatesSubtractByStr(String time1, String time2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(time1);
            Date d2 = df.parse(time2);
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            return diff;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Date getDateMinut(Date day, int hour) {
        if (day == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.HOUR, hour);// 24小时制
        Date date = cal.getTime();
        return date;
    }

    /**
     * 计算时间差
     */
    public static String timeDifference(Date beforeTime, Date afterTime) {
        long number = afterTime.getTime() - beforeTime.getTime();
        return formatDurings(number);
    }

    public static List<String> getTimeList(Date beforeTime, Date afterTime) {
        long number = afterTime.getTime() - beforeTime.getTime();
        return getTime(number);
    }

    public static String formatDurings(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);

        String str = "";
        if (days > 0) {
            str += "" + days + " 天";
        }
        if (hours > 0) {
            str += "" + hours + " 小时";
        }
        if (minutes > 0) {
            str += "" + minutes + " 分钟";
        }
        return str;
    }

    private static List<String> getTime(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        List<String> list = new ArrayList<>();
        if (days > 0) {
            list.add(String.valueOf(days));
        }
        if (hours > 0) {
            list.add(String.valueOf(hours));
        }
        if (minutes > 0) {
            list.add(String.valueOf(minutes));
        }
        return list;
    }

    /**
     * 获取今天的日期
     */
    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取今天还剩的时间，秒数
     */
    public static int longTime() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String endTime = simpleDateFormat.format(new Date()) + " 23:59:59";
            return (int) ((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime).getTime() - new Date().getTime()) / 1000);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis() / 1000;
    }

    public static long getMonthLastDay() {
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        ca.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        ca.set(Calendar.MINUTE, 0);
        //将秒至0
        ca.set(Calendar.SECOND, 0);
        //将毫秒至0
        ca.set(Calendar.MILLISECOND, 0);
        // 获取本月最后一天的时间戳
        return ca.getTimeInMillis() / 1000;
    }

    public static long getDayFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getDayLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 将Unix时间戳转换成指定格式日期字符串
     *
     * @param timestampString 时间戳 如："1473048265";
     * @param formats         要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (DataUtil.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 获取上一个月 的时间
     * 格式 (yyyy-MM)
     *
     * @return
     */
    public static String getPreviousMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }




    /**
     * 计算时间差
     *
     * @param date
     */
    public static Long nowTimeDifference(Date date) {
        Calendar calendar = Calendar.getInstance();
        Long currentTime = calendar.getTimeInMillis();
        calendar.setTime(date);
        Long targetTme = calendar.getTimeInMillis();
        return (currentTime - targetTme) / 1000;
    }



    static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM");

    /**
     * 加月数
     *
     * @param date 初始时间
     * @param month 月数
     */
    public static String addMonth(String date, Integer month) {
        try {
            Date initTime = yyyyMMdd.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(initTime);
            calendar.add(Calendar.MONTH,month);
            return yyyyMMdd.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据String型时间，获取long型时间，单位毫秒
     * @param inVal 时间字符串
     * @return long型时间
     */
    public static long fromDateStringToLong(String inVal) {
        Date date = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date = inputFormat.parse(inVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取时间差  精确到毫秒
     * @param time1 大
     * @param time2 小
     * @return
     */
    public static double getTimeDiffer(Date time1,Date time2) {
        long time = time1.getTime()-time2.getTime();
        DecimalFormat df = new DecimalFormat("0.0000");
        String format = df.format((float) time / (3600000 * 24));
        return Double.valueOf(format);
    }


    public static void main(String[] args) throws ParseException {
      //  System.err.println(daysBetween2(new Date(),new Date()));
//        System.out.println(strToDate("2019-12-15 16:48:49"));

//        double timeDiffer = getTimeDiffer(new Date(), strToDate("2019-12-05 18:0:01"));
//        System.out.println(timeDiffer);
//        if (timeDiffer>14.0){
//            System.out.println("xxx");
//        }
        // System.out.println((time-fromDateStringToLong("2019-12-15 16:48:49"))/(3600000 * 24));
    }
}