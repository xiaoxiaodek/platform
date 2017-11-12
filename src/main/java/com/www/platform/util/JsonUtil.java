package com.www.platform.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by upsmart on 17-8-3.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午3:57
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    /**
     * 对象转为JSON字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String str = null;
        try {
            str = JSON.toJSONString(object);
        } catch (Exception e) {
            logger.info("对象转为JSON字符串失败" + e.getMessage(), e);
        }
        return str;
    }

    /**
     * JSON字符串转为对象
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T toObject(String json, Class<T> valueType) throws IOException {
        T object = null;
        try {
            object =JSON.parseObject(json, valueType);
        } catch (Exception e) {
            logger.info("JSON字符串转为对象" + e.getMessage());
            throw e;
        }
        return object;
    }

    public static Map<String, Object> jsonToMap(String jsonStr) throws Exception {
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        Iterator<String> nameItr =(Iterator<String>) jsonObj.keySet();
        String name;
        Map<String, Object> outMap = new HashMap<String, Object>();
        while (nameItr.hasNext()) {
            name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));
        }
        return outMap;
    }
}
