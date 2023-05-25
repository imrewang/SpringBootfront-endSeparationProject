package cn.lanqiao.springboot3.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 格式化date
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat是一个用于以区域设置敏感的方式格式化和解析日期的具体类。
        return formatter.format(date);
        //将Date格式化为日期时间字符串。
    }
}
