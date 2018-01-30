package top.jplayer.baseprolibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018/1/30.
 */

public class DateUtils {
    /*
    * 将时间转换为时间戳
    */
    public static long dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }
}
