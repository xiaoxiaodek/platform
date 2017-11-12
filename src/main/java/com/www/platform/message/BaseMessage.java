package com.unionpaysmart.drip.message;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 包装接口最终返回的对象
 * @date 6/6/16
 */
public class BaseMessage extends Message<Object> {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Object data;

    public BaseMessage() {
        super();
    }

    public BaseMessage(MessageCode messageCode) {
        this.resCode = messageCode.getCode();
        this.resMsg = messageCode.getMsg();
    }

    public BaseMessage(MessageCode messageCode, StatusCode statusCode) {
        this(messageCode);
        this.statCode = statusCode.getCode();
        this.statMsg = statusCode.getMsg();
    }

    public BaseMessage(MessageCode messageCode, StatusCode statusCode, String appendMessage) {
        this(messageCode);
        this.statCode = statusCode.getCode();
        if (null != appendMessage) {
            this.statMsg = statusCode.getMsg() + "," + appendMessage;
        } else {
            this.statMsg = statusCode.getMsg();
        }
    }

    public BaseMessage(MessageCode messageCode, StatusCode statusCode, Object data) {
        this(messageCode, statusCode);
        this.data = data;
    }

    public BaseMessage(MessageCode messageCode, StatusCode statusCode, String appendMessage, Object data) {
        this(messageCode, statusCode, appendMessage);
        this.data = data;
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }


}
