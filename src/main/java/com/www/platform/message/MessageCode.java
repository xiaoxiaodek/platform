package com.unionpaysmart.drip.message;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 消息码
 * @date 6/6/16
 */
public enum MessageCode {

    SUCCESS("0000", "提交成功"),
    FAILED("0001", "提交失败");

    private String code;
    private String msg;

    MessageCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
