package com.demo.main.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ParameterConvert {
    public static <T> T convert(HttpServletRequest request, Class<T> clazz) {
        T entity;
        try {
            entity = clazz.getDeclaredConstructor().newInstance();
            for (Field field : getAllFields(clazz)) {
                field.setAccessible(true);

                String parameter = request.getParameter(field.getName());
                Object value;
                Class<?> fieldType = field.getType();

                if (parameter == null || parameter.trim().isEmpty()) {
                    if (!fieldType.isPrimitive()) {
                        field.set(entity, null);
                    }
                    continue;
                }

                value = convertStringToType(parameter, fieldType);
                field.set(entity, value);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static Object convertStringToType(String value, Class<?> type) {
        if (type.equals(String.class)) {
            return value.trim();
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return Short.parseShort(value);
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return Byte.parseByte(value);
        } else if (type.equals(Character.class) || type.equals(char.class)) {
            if (value.length() == 1) {
                return value.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid character value: " + value);
            }
        } else if (type.equals(BigDecimal.class)) {
            return new BigDecimal(value);
        } else if (type.equals(Timestamp.class)) {
            return DateUtil.stringToTimestamp(value);
        } else if (type.equals(Date.class)) {
            return DateUtil.stringToDate(value);
        } else if (type.equals(LocalDateTime.class)) {
            return DateUtil.stringToLocalDateTime(value);
        } else if (type.equals(LocalDate.class)) {
            return DateUtil.stringToLocalDate(value);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        }
    }
}
