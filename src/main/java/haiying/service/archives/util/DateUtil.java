package haiying.service.archives.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    public static final String pattern_ym = "yyyy-MM"; // pattern_ym
    public static final int pattern_ym_length = 7;
    public static final String pattern_ymd = "yyyy-MM-dd"; // pattern_ymd
    public static final int pattern_ymd_length = 10;
    public static final String pattern_ymd_hm = "yyyy-MM-dd HH:mm"; // pattern_ymd hm
    public static final int pattern_ymd_hm_length = 16;
    public static final String pattern_ymd_hms = "yyyy-MM-dd HH:mm:ss"; // pattern_ymd time
    public static final int pattern_ymd_hms_length = 19;
    public static final String pattern_ymd_hms_s = "yyyy-MM-dd HH:mm:ss:SSS"; // pattern_ymd timeMillisecond
    public static final int pattern_ymd_hms_s_length = 23;

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getDate() {
        return new Date();
    }

    //获取指定格式的当前日期字符串
    public static String GetNowDateString(String pattern) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String temp_str = sdf.format(dt);
        return temp_str;
    }

    /**
     * 获取指定日期
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */
    public static Date getDate(int date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        DateFormat dfm = new SimpleDateFormat(pattern_ymd); // 设置时间格式

        return dfm.format(new Date());
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    public static long getDateByTime() {
        return new Date().getTime();
    }

    /**
     * 得到某一天是星期几
     *
     * @param strDate 日期字符串
     * @return String 星期几
     */
    @SuppressWarnings("static-access")
    public static String getDateInWeek(String dateStr) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar calendar = Calendar.getInstance();
        Date date = parse(dateStr, "yyyy-MM-dd");
        calendar.setTime(date);
        int dayIndex = calendar.get(calendar.DAY_OF_WEEK) - calendar.SUNDAY;
        if (dayIndex < 0) {
            dayIndex = 0;
        }
        return weekDays[dayIndex];
    }

    /**
     * 解析
     *
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr, String pattern) {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 格式�?
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 格式�?
     *
     * @param date
     * @param pattern
     * @param timeZone
     * @return
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(timeZone);
        return format.format(date);
    }

    /**
     * 解析
     *
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr) {
        Date date = null;
        try {
            date = DateFormat.getDateTimeInstance().parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 两个日期的时间差，返�?"X天X小时X分X�?"
     *
     * @param start
     * @param end
     * @return
     */
    public static String getBetween(Date start, Date end) {
        long between = (end.getTime() - start.getTime()) / 1000;// 除以1000是为了转换成�?
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long minute = between % 3600 / 60;
        long second = between % 60 / 60;

        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append("�?");
        sb.append(hour);
        sb.append("小时");
        sb.append(minute);
        sb.append("�?");
        sb.append(second);
        sb.append("�?");

        return sb.toString();
    }

    /**
     * 返回两个日期之间隔了多少分钟
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDateMinuteSpace(Date start, Date end) {
        int hour = (int) ((end.getTime() - start.getTime()) / (60 * 1000));
        return hour;
    }

    /**
     * 返回两个日期之间隔了多少小时
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDateHourSpace(Date start, Date end) {
        int hour = (int) ((end.getTime() - start.getTime()) / (60 * 60 * 1000));
        return hour;
    }

    /**
     * 返回两个日期之间隔了多少�?
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDateDaySpace(Date start, Date end) {
        int day = (int) ((end.getTime() - start.getTime()) / (60 * 60 * 24 * 1000));
        return day;
    }

    /**
     * 得到某一天是星期�?
     *
     * @param strDate 日期字符�?
     * @return String 星期�?
     */
    @SuppressWarnings("static-access")
    public static String getDateInWeek(Date date) {
        String[] weekDays = {"星期�?", "星期�?", "星期�?", "星期�?", "星期�?", "星期�?", "星期�?"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(calendar.DAY_OF_WEEK) - calendar.SUNDAY;
        if (dayIndex < 0) {
            dayIndex = 0;
        }
        return weekDays[dayIndex];
    }

    /**
     * 日期减去多少个小�?
     *
     * @param date
     * @param hourCount 多少个小�?
     * @return
     */
    public static Date getDateReduceHour(Date date, long hourCount) {
        long time = date.getTime() - 3600 * 1000 * hourCount;
        Date dateTemp = new Date();
        dateTemp.setTime(time);
        return dateTemp;
    }

    /**
     * 日期区间分割
     *
     * @param start
     * @param end
     * @param splitCount
     * @return
     */
    public static List<Date> getDateSplit(Date start, Date end, long splitCount) {
        long startTime = start.getTime();
        long endTime = end.getTime();
        long between = endTime - startTime;

        long count = splitCount - 1L;
        long section = between / count;

        List<Date> list = new ArrayList<Date>();
        list.add(start);

        for (long i = 1L; i < count; i++) {
            long time = startTime + section * i;
            Date date = new Date();
            date.setTime(time);
            list.add(date);
        }

        list.add(end);

        return list;
    }

    /**
     * 返回两个日期之间隔了多少天，包含�?始�?�结束时�?
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getDaySpaceDate(Date start, Date end) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(start);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(end);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        List<String> dateList = new LinkedList<String>();

        long dayCount = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
        if (dayCount < 0) {
            return dateList;
        }

        dateList.add(format(fromCalendar.getTime(), pattern_ymd));

        for (int i = 0; i < dayCount; i++) {
            fromCalendar.add(Calendar.DATE, 1);// 增加�?�?
            dateList.add(format(fromCalendar.getTime(), pattern_ymd));
        }

        return dateList;
    }

    /**
     * 获取�?始时�?
     *
     * @param start
     * @param end
     * @return
     */
    public static Date startDateByDay(Date start, int end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, end);// 明天1，昨�?-1
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取结束时间
     *
     * @param start
     * @param end
     * @return
     */
    public static Date endDateByDay(Date start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取�?始时�?
     *
     * @param start
     * @param end
     * @return
     */
    public static Date startDateByHour(Date start, int end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.MINUTE, end);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取结束时间
     *
     * @param start
     * @param end
     * @return
     */
    public static Date endDateByHour(Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 根据年份和周得到周的�?始和结束日期
     *
     * @param year
     * @param week
     * @return
     */
    public static Map<String, Date> getStartEndDateByWeek(int year, int week) {
        Calendar weekCalendar = new GregorianCalendar();
        weekCalendar.set(Calendar.YEAR, year);
        weekCalendar.set(Calendar.WEEK_OF_YEAR, week);
        weekCalendar.set(Calendar.DAY_OF_WEEK, weekCalendar.getFirstDayOfWeek());

        Date startDate = weekCalendar.getTime(); // 得到周的�?始日�?

        weekCalendar.roll(Calendar.DAY_OF_WEEK, 6);
        Date endDate = weekCalendar.getTime(); // 得到周的结束日期

        // �?始日期往前推�?�?
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        startCalendar.add(Calendar.DATE, 1);// 明天1，昨�?-1
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        startDate = startCalendar.getTime();

        // 结束日期�?前推�?�?
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.add(Calendar.DATE, 1);// 明天1，昨�?-1
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        endDate = endCalendar.getTime();

        Map<String, Date> map = new HashMap<String, Date>();
        map.put("start", startDate);
        map.put("end", endDate);
        return map;
    }

    /**
     * 根据日期月份，获取月份的�?始和结束日期
     *
     * @param date
     * @return
     */
    public static Map<String, Date> getMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 得到前一个月的第�?�?
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date start = calendar.getTime();

        // 得到前一个月的最后一�?
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = calendar.getTime();

        Map<String, Date> map = new HashMap<String, Date>();
        map.put("start", start);
        map.put("end", end);
        return map;
    }

    /**
     * 分割List
     *
     * @param list     待分割的list
     * @param pageSize 每段list的大�?
     * @return List<< List < T>>
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size(); // list的大�?
        int page = (listSize + (pageSize - 1)) / pageSize; // 页数

        List<List<T>> listArray = new ArrayList<List<T>>();// 创建list数组
        // ,用来保存分割后的list

        for (int i = 0; i < page; i++) { // 按照数组大小遍历
            List<T> subList = new ArrayList<T>(); // 数组每一位放入一个分割后的list
            for (int j = 0; j < listSize; j++) { // 遍历待分割的list
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize; // 当前记录的页�?(第几�?)
                if (pageIndex == (i + 1)) { // 当前记录的页码等于要放入的页码时
                    subList.add(list.get(j)); // 放入list中的元素到分割后的list(subList)
                }

                if ((j + 1) == ((j + 1) * pageSize)) { // 当放满一页时�?出当前循�?
                    break;
                }
            }
            listArray.add(subList); // 将分割后的list放入对应的数组的位中
        }
        return listArray;
    }

    /**
     * 描述：�?�过年�?�周数和周几获得日期 作�?�：张兆�? 时间�?2017-11-02
     *
     * @param year
     * @param numofweek
     * @param weekday
     * @return
     */
    public static String getDateByYearAndWeekAndDay(String year, String numofweek, String weekday) {
        int numofweektemp = Integer.parseInt(numofweek);
        if (weekday.equals("7")) {
            numofweektemp = numofweektemp + 1;
            weekday = "0";
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.WEEK_OF_YEAR, numofweektemp);
        cal.set(Calendar.DAY_OF_WEEK, Integer.parseInt(weekday) + 1);
        Date date = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 描述：�?�过指定日期，获得指定日期所在周的周�? 作�?�：张兆�? 时间�?2017-11-03
     *
     * @param time
     * @return
     */
    public static String convertWeekDate(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下�?周去�?
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几�?
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日�?
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置�?个星期的第一天，按中国的习惯�?个星期的第一天是星期�?
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几�?
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第�?天的差�??
        return sdf.format(cal.getTime()); // 周一时间 ;
    }

    /**
     * 描述：�?�过日期获得�? 作�?�：张兆�? 时间�?2017-11-03
     *
     * @param time
     * @return
     */
    public static int dayForWeek(String time) {
        int dayForWeek = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(time));
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayForWeek;
    }

    /**
     * 描述：获得当前的整点 作�?�：张兆�? 时间�?2017-11-27
     *
     * @return
     */
    public static Date getHour() {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
            date = format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 描述：比较时�?
     * 作�?�：张兆�?
     * 时间�?2017-11-01
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compare_time(String time1, String time2) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date dt1 = df.parse(time1);
            Date dt2 = df.parse(time2);
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else if (dt1.getTime() <= dt2.getTime()) {
                return false;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }


    /**
     * 描述：格式化斜杠日期
     * 作�?�：张兆�?
     * 日期�?2017-11-13
     *
     * @param date
     * @return
     */
    public static String formtSlashDate(String date) {
        try {
            if (date != null && !"".equals(date)) {
                boolean isexist = date.contains("/");
                if (isexist) {
                    date = date.replace("/", "-");
                }
                SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dddate = SimpleDateFormat.parse(date);
                return SimpleDateFormat.format(dddate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 描述：获得年
     * 作�?�：张兆�?
     * 时间�?2017-12-19
     *
     * @param date
     * @return
     */
    public static String getYear(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date formdate = dateFormat.parse(date);
            DateFormat yearFormat = new SimpleDateFormat("yyyy");
            return yearFormat.format(formdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 描述：获得月
     * 作�?�：张兆�?
     * 时间�?2017-12-19
     *
     * @param date
     * @return
     */
    public static String getMonth(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date formdate = dateFormat.parse(date);
            DateFormat monthFormat = new SimpleDateFormat("MM");
            return monthFormat.format(formdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 描述：获得日
     * 作�?�：张兆�?
     * 时间�?2017-12-19
     *
     * @param date
     * @return
     */
    public static String getDay(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date formdate = dateFormat.parse(date);
            DateFormat dayFormat = new SimpleDateFormat("dd");
            return dayFormat.format(formdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 描述：获得当月天�?
     * 作�?�：张兆�?
     * 时间�?2017-12-19
     *
     * @return
     */
    public static int getCurrentMonthLastDay(String year, String month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(year + "-" + month));
            c.set(Calendar.DATE, 1);//把日期设置为当月第一�?
            c.roll(Calendar.DATE, -1);//日期回滚�?天，也就是最后一�?
            int maxDate = c.get(Calendar.DATE);
            return maxDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 描述：指定日期增加一个月
     * 作�?�：张兆�?
     * 时间�?2017-12-20
     *
     * @param date
     * @return
     */
    public static String addYiGeMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, 1);//把日期往后增加一个月.整数�?后推,负数�?前移�?
        date = calendar.getTime();   //这个时间就是日期�?后推�?天的结果
        return format(date, pattern_ymd);
    }

    /**
     * 描述：计算详细的年龄
     * 作�?�：张兆�?
     * 时间�?2018-01-11
     *
     * @param growDevelpRecord
     */
    public static String getDetailYear(String birthdate) {
        //获得出生日期-�?
        String brith_year = DateUtil.getYear(birthdate);
        //获得出生日期-�?
        String brith_month = DateUtil.getMonth(birthdate);
        //获得出生日期-�?
        String brith_day = DateUtil.getDay(birthdate);
        //获得当前日期
        String currentDate = DateUtil.format(new Date(), DateUtil.pattern_ymd);
        //获得当前日期-�?
        String curr_year = DateUtil.getYear(currentDate);
        //获得year日期-�?
        String curr_month = DateUtil.getMonth(currentDate);
        //获得year日期-�?
        String curr_day = DateUtil.getDay(currentDate);
        Integer year = 0;
        Integer month = 0;
        Integer day = 0;
        day = Integer.parseInt(curr_day) - Integer.parseInt(brith_day);
        if (day < 0) {
            curr_month = String.valueOf((Integer.parseInt(curr_month) - 1));
            day = DateUtil.getCurrentMonthLastDay(curr_year, curr_month) + day;
        }
        month = Integer.parseInt(curr_month) - Integer.parseInt(brith_month);
        if (month < 0) {
            month = month + 12;
            curr_year = String.valueOf((Integer.parseInt(curr_year) - 1));
        }
        year = Integer.parseInt(curr_year) - Integer.parseInt(brith_year);

        return year + "�?" + month + "�?" + day + "�?";

    }

    /**
     * 描述：获得月�?
     * 作�?�：张兆�?
     * 时间�?2017-12-26
     *
     * @param birthdate
     * @param flag
     * @return
     */
    public static String getMonthOfAge(String birthdate) {
        Integer monthofage = 0;
        //获得出生日期-�?
        String brith_year = DateUtil.getYear(birthdate);
        //获得出生日期-�?
        String brith_month = DateUtil.getMonth(birthdate);
        //获得出生日期-�?
        String brith_day = DateUtil.getDay(birthdate);
        //获得当前日期
        String currentDate = DateUtil.format(new Date(), DateUtil.pattern_ymd);
        //获得当前日期-�?
        String curr_year = DateUtil.getYear(currentDate);
        //获得year日期-�?
        String curr_month = DateUtil.getMonth(currentDate);
        //获得year日期-�?
        String curr_day = DateUtil.getDay(currentDate);
        Integer year = 0;
        Integer month = 0;
        Integer day = 0;
        day = Integer.parseInt(curr_day) - Integer.parseInt(brith_day);
        if (day < 0) {
            curr_month = String.valueOf((Integer.parseInt(curr_month) - 1));
            day = DateUtil.getCurrentMonthLastDay(curr_year, curr_month) + day;
        }
        month = Integer.parseInt(curr_month) - Integer.parseInt(brith_month);
        if (month < 0) {
            month = month + 12;
            curr_year = String.valueOf((Integer.parseInt(curr_year) - 1));
        }
        year = Integer.parseInt(curr_year) - Integer.parseInt(brith_year);
        monthofage = year * 12;
        if (month > 0) {
            monthofage = monthofage + month;
        }
        return monthofage + "";
    }

    public static String getNextDate(String d) {
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nextDay = "";
        try {
            Date temp = dft.parse(d);
            Calendar cld = Calendar.getInstance();
            cld.setTime(temp);
            cld.add(Calendar.DATE, 1);
            temp = cld.getTime();
            //获得下一天日期字符串
            nextDay = dft.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextDay;
    }
}
