package com.lixw.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author lixw
 * @date 2021/04/21
 */
public class JsonUtils {

    public static <T> String object2Json(T t) throws JsonProcessingException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(dateFormat);
        return objectMapper.writeValueAsString(t);
    }

    public static <T> T json2Pojo(String jsonString, Class<T> clazz) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(dateFormat);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(jsonString, clazz);
    }

    public static Map<String,String> parseResponseEntity(String responseEntityStr) {
        Map<String, String> map = new HashMap<>();
        String[] strs = responseEntityStr.split("\\&");
        for (String str : strs) {
            String[] mapStrs = str.split("=");
            String value = null;
            String key = mapStrs[0];
            if (mapStrs.length > 1) {
                value = mapStrs[1];
            }
            map.put(key, value);
        }
        return map;
    }

}
