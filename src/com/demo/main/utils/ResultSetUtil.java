package com.demo.main.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class ResultSetUtil {

    public interface ResultTransformer<E> {
        E transform(E e) throws Exception;
    }

    private static void mapFieldValue(Field field, ResultSet resultSet, Object obj, int columnIndex) throws Exception {
        Class<?> fieldType = field.getType();
        Object value = null;
        if (fieldType.equals(String.class)) {
            value = resultSet.getString(columnIndex);
        } else if (fieldType.equals(Timestamp.class)) {
            value = resultSet.getTimestamp(columnIndex);
        } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
            value = resultSet.getInt(columnIndex);
        } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
            value = resultSet.getLong(columnIndex);
        } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
            value = resultSet.getDouble(columnIndex);
        } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
            value = resultSet.getFloat(columnIndex);
        } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
            value = resultSet.getShort(columnIndex);
        } else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
            value = resultSet.getByte(columnIndex);
        } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
            value = resultSet.getBoolean(columnIndex);
        } else if (fieldType.equals(Date.class)) {
            Timestamp timestamp = resultSet.getTimestamp(columnIndex);
            if (timestamp != null) {
                value = new Date(timestamp.getTime());
            }
        } else if (fieldType.equals(BigDecimal.class)) {
            value = resultSet.getBigDecimal(columnIndex);
        } else if (fieldType.equals(byte[].class)) {
            value = resultSet.getBytes(columnIndex);
        } else if (fieldType.isEnum()) {
            String enumValue = resultSet.getString(columnIndex);
            if (enumValue != null) {
                value = Enum.valueOf((Class<Enum>) fieldType, enumValue);
            }
        } else if (fieldType.equals(List.class)) {
            String setValue = resultSet.getString(columnIndex);
            if (setValue != null) {
                value = Arrays.asList(setValue.split(","));
            }
        } else if (fieldType.equals(LocalDateTime.class)) {
            Timestamp timestamp = resultSet.getTimestamp(columnIndex);
            if (timestamp != null) {
                value = timestamp.toLocalDateTime();
            }
        } else {
            value = resultSet.getObject(columnIndex);
        }
        if (value != null) {
            field.set(obj, value);
        }
    }

    public static <T> List<T> mapResultSetToListWithCustomTransform(ResultSet resultSet, Class<T> targetClass, ResultTransformer<T> transformer) {
        List<T> resultList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = targetClass.getConstructor().newInstance();
                int columnIndex = 1;
                List<Field> fields = new ArrayList<>();
                Collections.addAll(fields, targetClass.getDeclaredFields());
                Class<?> superClass = targetClass.getSuperclass();
                if (!superClass.equals(Object.class)) {
                    fields.addAll(0, Arrays.asList(superClass.getDeclaredFields()));
                }
                for (Field field : fields) {
                    if ("serialVersionUID".equals(field.getName())) {
                        continue;
                    }
                    field.setAccessible(true);
                    mapFieldValue(field, resultSet, instance, columnIndex);
                    columnIndex++;
                }
                if (transformer != null) {
                    instance = transformer.transform(instance);
                }
                resultList.add(instance);
            }
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> mapToList(ResultSet resultSet, Class<T> targetClass) {
        return mapResultSetToListWithCustomTransform(resultSet, targetClass, null);
    }

    public static <T> T mapToSingle(ResultSet resultSet, Class<T> targetClass) {
        List<T> list = mapToList(resultSet, targetClass);
        return list.isEmpty() ? null : list.get(0);
    }
}