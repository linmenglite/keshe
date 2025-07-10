package com.demo.main.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    private static final DateTimeFormatter SIMPLE_DATE_FORMATTER = DateTimeFormatter.ofPattern(SIMPLE_DATE_FORMAT);

    private static final String[] DATE_FORMATS = {
            "yyyy-MM-dd'T'HH:mm:ss",
            DEFAULT_DATE_FORMAT,
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd HH:mm:ss.SSS",
            SIMPLE_DATE_FORMAT,
            "HH:mm:ss"
    };

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date stringToDate(String dateString) {
        LocalDateTime localDateTime = parseLocalDateTime(dateString);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Timestamp stringToTimestamp(String dateString) {
        LocalDateTime localDateTime = parseLocalDateTime(dateString);
        return Timestamp.valueOf(localDateTime);
    }

    public static LocalDateTime stringToLocalDateTime(String dateString) {
        return parseLocalDateTime(dateString);
    }

    public static String dateToString(Date date, String format) {
        if (date == null) return null;
        return getFormatter(format).format(date.toInstant().atZone(ZoneId.systemDefault()));
    }

    public static String timestampToString(Timestamp timestamp, String format) {
        if (timestamp == null) return null;
        return getFormatter(format).format(timestamp.toLocalDateTime());
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) return null;
        return getFormatter(format).format(localDateTime);
    }

    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATE_FORMAT);
    }

    public static String timestampToString(Timestamp timestamp) {
        return timestampToString(timestamp, DEFAULT_DATE_FORMAT);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTimeToString(localDateTime, DEFAULT_DATE_FORMAT);
    }

    public static Date timestampToDate(Timestamp timestamp) {
        return timestamp == null ? null : new Date(timestamp.getTime());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Timestamp dateToTimestamp(Date date) {
        return date == null ? null : new Timestamp(date.getTime());
    }

    public static Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

    public static Date stringToDateDefaultFormat(String dateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, DEFAULT_DATE_TIME_FORMATTER);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + DEFAULT_DATE_FORMAT, e);
        }
    }

    public static Timestamp stringToTimestampDefaultFormat(String dateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, DEFAULT_DATE_TIME_FORMATTER);
            return Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + DEFAULT_DATE_FORMAT, e);
        }
    }

    public static LocalDateTime stringToLocalDateTimeDefaultFormat(String dateString) {
        try {
            return LocalDateTime.parse(dateString, DEFAULT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + DEFAULT_DATE_FORMAT, e);
        }
    }

    public static Date stringToDate(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + format, e);
        }
    }

    public static Timestamp stringToTimestamp(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
            return Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + format, e);
        }
    }

    public static LocalDateTime stringToLocalDateTime(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + format, e);
        }
    }

    // 新添加的LocalDate相关方法

    public static LocalDate stringToLocalDate(String dateString) {
        LocalDateTime localDateTime = parseLocalDateTime(dateString);
        return localDateTime.toLocalDate();
    }

    public static String localDateToString(LocalDate localDate, String format) {
        if (localDate == null) return null;
        return getFormatter(format).format(localDate);
    }

    public static String localDateToString(LocalDate localDate) {
        return localDateToString(localDate, SIMPLE_DATE_FORMAT);
    }

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

    public static LocalDate timestampToLocalDate(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime().toLocalDate();
    }

    public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Timestamp localDateToTimestamp(LocalDate localDate) {
        return localDate == null ? null : Timestamp.valueOf(localDate.atStartOfDay());
    }

    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return localDate == null ? null : localDate.atStartOfDay();
    }

    public static LocalDate stringToLocalDateDefaultFormat(String dateString) {
        try {
            return LocalDate.parse(dateString, SIMPLE_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + SIMPLE_DATE_FORMAT, e);
        }
    }

    public static LocalDate stringToLocalDate(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse date string: " + dateString + ". Expected format: " + format, e);
        }
    }

    // 私有辅助方法

    private static LocalDateTime parseLocalDateTime(String parameter) {
        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter formatter = getFormatter(format);
                if (format.equals(SIMPLE_DATE_FORMAT)) {
                    return LocalDate.parse(parameter, formatter).atStartOfDay();
                } else if (format.equals("HH:mm:ss")) {
                    return LocalTime.parse(parameter, formatter).atDate(LocalDate.now());
                }
                return LocalDateTime.parse(parameter, formatter);
            } catch (DateTimeParseException e) {
                // 当前格式解析失败，循环会自动继续尝试下一个格式
            }
        }

        // 如果所有预定义格式都失败，尝试使用ISO格式
        try {
            return LocalDateTime.parse(parameter);
        } catch (DateTimeParseException e) {
            // 如果ISO格式也失败，尝试解析为LocalDate
            try {
                return LocalDate.parse(parameter).atStartOfDay();
            } catch (DateTimeParseException e2) {
                // 如果LocalDate解析也失败，抛出异常
                String errorMsg = "无法解析日期时间字符串: " + parameter +
                        "。尝试了以下格式: " + Arrays.toString(DATE_FORMATS) + " 和 ISO 格式";
                throw new IllegalArgumentException(errorMsg, e2);
            }
        }
    }

    private static DateTimeFormatter getFormatter(String format) {
        if (format.equals(DEFAULT_DATE_FORMAT)) {
            return DEFAULT_DATE_TIME_FORMATTER;
        } else if (format.equals(SIMPLE_DATE_FORMAT)) {
            return SIMPLE_DATE_FORMATTER;
        } else {
            return DateTimeFormatter.ofPattern(format);
        }
    }
}