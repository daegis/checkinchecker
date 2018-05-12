package cn.aegisa.project.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * Using IntelliJ IDEA.
 * JDK8 新时间类 工具类
 *
 * @author HNAyd.xian
 * @date 2018/2/5 9:05
 */
public abstract class LocalDateTimeUtil {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 将date类型转化为localDateTime
     *
     * @param date 要转化的date
     * @return 对应的localDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将日期或者日期时间转化为字符串表示
     *
     * @param time 要转换的时间
     * @return 时间对应的字符串
     */
    public static String timeToString(Temporal time) {
        if (time instanceof LocalDate) {
            return ((LocalDate) time).format(dateFormatter);
        } else if (time instanceof LocalDateTime) {
            return ((LocalDateTime) time).format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * 将字符串转化为日期
     *
     * @param time 传入的日期时间字符串
     * @return 返回的时间对象
     */
    public static LocalDateTime fromString(String time) {
        if (time.length() > 12) {
            // 日期时间
            return LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            // 日期
            LocalDate localDate = LocalDate.parse(time, dateFormatter);
            return localDate.atStartOfDay();
        }
    }
}
