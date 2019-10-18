package com.mockproject.du1.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

public class DataUtil {
    private static final Logger logger = LoggerFactory.getLogger(DataUtil.class);

    public static boolean nonEmpty(String text) {
        return !nullOrEmpty(text);
    }

    public static boolean nonEmpty(Collection collection) {
        return !nullOrEmpty(collection);
    }

    public static boolean notNullOrEmpty(String text) {
        return !nullOrEmpty(text);
    }

    public static boolean notNullOrEmpty(Collection collection) {
        return !nullOrEmpty(collection);
    }

    public static boolean nullOrEmpty(Collection objects) {
        return objects == null || objects.isEmpty();
    }

    public static boolean nullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean notNull(Collection objects) {
        return objects != null;
    }

    public static boolean notNull(Map<?, ?> map) {
        return map != null;
    }

    public static boolean notNull(Object object) {
        return !nullObject(object);
    }

    public static boolean nullObject(Object object) {
        return object == null;
    }

    public static boolean nullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    public static boolean nullOrZero(String value) {
        return value == null || "0".equals(value);
    }

    public static boolean nullOrZero(Integer value) {
        return (value == null || value.equals(0));
    }

    public static boolean equalsObj(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) return false;
        return obj1.equals(obj2);
    }

    public static Integer parseToInt(String value, Integer defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return defaultVal;
        }
    }

    public static Integer parseToInt(String value) {
        return parseToInt(value, null);
    }

    public static Integer parseToInt(Object value) {
        return parseToInt(parseToString(value), null);
    }

    public static boolean isNullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    public static Long parseToLong(Object value, Long defaultVal) {
        try {
            String str = parseToString(value);
            if (nullOrEmpty(str)) {
                return null;
            }
            return Long.parseLong(str);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return defaultVal;
        }
    }

    public static Long parseToLong(Object value) {
        return parseToLong(value, null);
    }

    public static Double parseToDouble(Object value, Double defaultVal) {
        try {
            String str = parseToString(value);
            if (nullOrEmpty(str)) {
                return defaultVal;
            }
            return Double.parseDouble(str);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return defaultVal;
        }
    }

    public static Double parseToDouble(Object value) {
        return parseToDouble(value, null);
    }

    public static String parseToString(Object value, String defaultVal) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return defaultVal;
        }
    }

    public static String parseToString(Object value) {
        return parseToString(value, "");
    }

    public static boolean matchByPattern(String value, String regex) {
        if (nullOrEmpty(regex) || nullOrEmpty(value)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static void throwIfFailed(boolean test, String message) throws Exception {
        if (!test) throw new Exception(message);
    }

    public static <X extends Throwable> void throwIfFailed(boolean test, Supplier<? extends X> exceptionSupplier) throws X {
        if (!test) throw exceptionSupplier.get();
    }

    public static void throwIf(boolean test, String message) throws Exception {
        if (test) throw new Exception(message);
    }


    public static <X extends Throwable> void throwIf(boolean test, Supplier<? extends X> exceptionSupplier) throws X {
        if (test) throw exceptionSupplier.get();
    }

    public static boolean nullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(CharSequence cs) {
        return nullOrEmpty(cs);
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T deepCloneObject(T source) {
        try {
            if (source == null) {
                return null;
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(source);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            T dto = (T) in.readObject();
            in.close();
            return dto;
        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


}
