package com.www.platform.controller.company;

import com.www.platform.message.BaseMessage;
import com.www.platform.message.MessageCode;
import com.www.platform.message.StatusCode;
import com.www.platform.service.company.CompanyService;
import com.www.platform.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Copyright (C), 2017, 银联智惠信息服务（上海）有限公司
 * @desc 客户和供应商的相关操作
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    /**
     * @desc 获取客户和供应商列表
     * @return
     */
    @RequestMapping(value = "/companyList", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage companyList() {
        BaseMessage message = new BaseMessage();
        try {
            if (null != this.companyService.findByAll()) {
                ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
                message.setData(this.companyService.findByAll());
            } else {
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                message.setData("未获取公司数据");
            }
        } catch (Exception e) {
            logger.error("获取公司异常");
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 添加客户
     * @param map
     * @return message
     * @throws Exception
     */
    @RequestMapping(value = "/addCompany",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage addCompany(@RequestBody Map<String, Object> map) throws Exception{

        BaseMessage message = new BaseMessage();
        Boolean b = this.companyService.addCompany(map);
        return message;

    }
}
