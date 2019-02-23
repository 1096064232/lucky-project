package com.lucky.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeUtils {

    /**
     * 获取秒数
     *
     * @return
     */
    public static Long getSecond() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取毫秒数
     *
     * @return
     */
    public static Long getMilli() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取两个时间相差的毫秒数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getMillisBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return getDuration(startTime, endTime).toMillis();
    }

    /**
     * 获取两个时间相差的秒数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getSecondBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return getMinutesdBetween(startTime, endTime) * 60;
    }


    /**
     * 获取两个时间相差的分钟数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getMinutesdBetween(LocalDateTime startTime, LocalDateTime endTime) {

        return getDuration(startTime, endTime).toMinutes();
    }

    /**
     * 获取两个时间相差的小时数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long Hours(LocalDateTime startTime, LocalDateTime endTime) {
        return getDuration(startTime, endTime).toHours();
    }

    /**
     * 获取两个时间相差的天数数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getDaysBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return getDuration(startTime, endTime).toDays();
    }

    /**
     * 获取两个时间的时差
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Duration getDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }

    /**
     * 时间转字符串格式化
     *
     * @return
     */
    public static String dateToString() {
        return dateToString("yyyyMMddHHmmssSSS");
    }


    /**
     * 时间转字符串格式化
     *
     * @return
     */
    public static String dateToString(String dateTimeFormatter) {
        return dateToString(LocalDateTime.now(), dateTimeFormatter);
    }

    /**
     * 时间转字符串格式化
     *
     * @return
     */
    public static String dateToString(LocalDateTime localDateTime, String dateTimeFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);
        return localDateTime.format(formatter);
    }


    /**
     * 字符串转时间
     *
     * @param dateTimeStr
     * @param dateTimeFormatter
     * @return
     */
    public static LocalDateTime stringToDate(String dateTimeStr, String dateTimeFormatter) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFormatter);
        return LocalDateTime.parse(dateTimeStr, df);
    }

    /**
     * 字符串转时间
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime stringToDate(String dateTimeStr) {
        return stringToDate(dateTimeStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取毫秒数
     *
     * @param localDateTime
     * @return
     */
    public static Long getMilliSecond(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取毫秒数
     *
     * @param localDateTime
     * @return
     */
    public static Long getSecond(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }


    /**
     * 将Date转为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 将Date转为LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }


    /**
     * 将Date转为LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }


    /**
     * 将LocalDateTime转为Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将LocalDate转为Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 将LocalTime转为Date
     *
     * @param localTime
     * @return
     */
    public static Date localTimeToDate(LocalTime  localTime) {
        return localDateTimeToDate(LocalDateTime.of(LocalDate.now(), localTime));
    }

}
