package com.www.platform.constant;

/**

 * Copyright (C), 2017, 银联智惠信息服务（上海）有限公司
 *
 * @author upsmart
 * @version 0.0.1
 */

public class GlobalConstants {

    public static final String EMPTY_STR = "";


    public static final String USERNAME = "uname";
    public static final String UID = "uid";
    public static final String UEMAIL = "uemail";
    public static final String RID = "rid";
    public static final String ROLE = "role";

    public static final String SALT = "upsmart";

    // 标点符号
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String MIDDLE_LINE = "-";
    public static final String EQUAL = "=";
    public static final String GREATER = ">";
    public static final String GREATER_EQUAL = ">=";
    public static final String LESS = "<";
    public static final String LESS_EQUAL = "<=";
    public static final String LEFT_S_BRACKETS = "(";
    public static final String RIGHT_S_BRACKETS = ")";
    public static final String APOSTROPHE = "'";
    public static final String ASTERISK = "*";
    public static final String L_PERCENT = "'%";
    public static final String R_PERCENT = "%'";
    public static final String COLON = ":";

    //group
    public static final String GROUP = "group";

    //脚本运行及启动状态参数
    public static final Boolean STATUS_RUNNING = true;
    public static final Boolean STATUS_NOT_RUNNING = false;
    public static final Boolean CONCURRENT_IS = true;
    public static final Boolean CONCURRENT_NOT = false;

    //消息平台访问路径
    public static final String MSG_LOCATION = "http://192.168.88.237:9875/message/send";

    //查询remind表的定时启动cron表达式"0 0/10 * * * ? *"
    public static final String REMIND_CRON = "0 0/10 * * * ? *";

    //合同文件存取路径
    public static final String CONTRACT = "src/main/webapp/uploadFiles";

    //脚本文件存取路径
    public static final String SCRIPT_SCRIPT = "file/script/script";
    //脚本文件结果存取路径
    public static final String SCRIPT_RESULT = "file/script/result/";

    //查询公司的类型
    public static final int COMPANY_ALL = 0;
    public static final int COMPANY_COMMID = 1;
    public static final int COMPANY_COMMADDR = 2;
    public static final int COMPANY_COMMEMAIL = 3;
    public static final int COMPANY_COMCONTACT = 4;
    public static final int COMPANY_COMNAME = 5;

    //状态类型
    public static final int COMMERCE_STATUS = 0;
    public static final int TECH_STATUS = 1;
    public static final int ONLINE_STATUS = 2;
}
