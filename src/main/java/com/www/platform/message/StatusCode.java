package com.www.platform.message;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 状态码, 具体的失败消息
 * @date 6/6/16
 */
public enum StatusCode {

    // success
    SUCCESS("00000", "提交成功"),
    NO_RESPONSE("00001", "没有查询到结果"),
    // fail
    ACCOUNT_PWD_INVALID_NOT_EXISTS("10110", "账号名密码不匹配"),
    ACCOUNT_INVALID_NOT_EXISTS("10104", "账号不存在"),
    INVALID_PARAM("10105", "请求参数不合法"),

    USER_LIMITED("10106", "没有权限"),
    SYSTEM_ERROR("20101", "系统异常"),
    DATA_ERROR("20102", "数据异常"),

    //公司状态
    COMMERCE_COMMUNICATION_NO("0","未进行"),
    COMMERCE_COMMUNICATION_NOW("1","正在"),
    COMMERCE_COMMUNICATION_FINISH("2","完成"),
    COMMERCE_CONTRACT_NO("3","拟定中"),
    COMMERCE_CONTRACT_FINISH("4","拟定完毕"),

    TECH_TEST("5","测试"),
    TECH_COMMit("6","正式对接"),

    ACCOUNT_NO("7","尚未到账"),
    ACCOUNT_FINISH("8","已经到账"),

    ONLINE_NO("9","未运营"),
    ONLINE_FINISH("10","未运营");

    private String code;
    private String msg;

    private StatusCode(String code, String msg) {
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
