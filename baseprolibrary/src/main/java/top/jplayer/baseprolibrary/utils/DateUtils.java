package top.jplayer.baseprolibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018/1/30.
 */

public class DateUtils {
    /*
    * 将时间转换为时间戳
    */
    public static long dateToStamp(String s) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Date date = null;
            date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int dayComparePrecise(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);

        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear - fromYear;
        int month = toMonth - fromMonth;
        int day = toDay - fromDay;
        return year;
    }
}
