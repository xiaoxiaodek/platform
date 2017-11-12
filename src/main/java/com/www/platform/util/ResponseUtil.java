package com.unionpaysmart.drip.util;

import com.unionpaysmart.drip.constant.GlobalConstants;
import com.unionpaysmart.drip.message.BaseMessage;
import com.unionpaysmart.drip.message.MessageCode;
import com.unionpaysmart.drip.message.StatusCode;

import java.util.*;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 接口返回结果工具类
 * @date 6/6/16
 */
public class ResponseUtil {

    public static boolean IS_SIGN = true;

    /**
     * 构造返回message
     * @param msg
     * @param res
     * @param sta
     */
    public static void buildResMsg(BaseMessage msg, MessageCode res, StatusCode sta) {
        if (null != msg) {
            msg.setResCode(res.getCode());
            msg.setResMsg(res.getMsg());
            msg.setStatCode(sta.getCode());
            msg.setStatMsg(sta.getMsg());
        }
    }

    /**
     * 构造返回message
     * @param msg
     * @param res
     * @param sta
     * @param appendMessage
     */
    public static void buildResMsg(BaseMessage msg, MessageCode res, StatusCode sta, String appendMessage) {
        if (null != msg) {
            msg.setResCode(res.getCode());
            msg.setResMsg(res.getMsg());
            msg.setStatCode(sta.getCode());
            msg.setStatMsg(sta.getMsg() + GlobalConstants.COMMA + appendMessage);
        }
    }

    /**
     * 
     * 通过code获取制定枚举
     * 
     * @param code
     * @return
     */
    public static StatusCode getStatusCode(String code) {
        StatusCode[] statusCodes = StatusCode.values();
        for (StatusCode status : statusCodes) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getValue(Map<String, String> map, String key, String defaultValue) {
        String value = map.get(key);
        return isBlank(value) ? defaultValue : value;
    }

}
