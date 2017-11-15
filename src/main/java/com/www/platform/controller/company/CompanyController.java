package com.www.platform.controller.company;

import com.www.platform.message.BaseMessage;
import com.www.platform.message.MessageCode;
import com.www.platform.message.StatusCode;
import com.www.platform.service.company.CompanyService;
import com.www.platform.util.ResponseUtil;
import com.www.platform.util.SystemLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * @desc 删除客户和供应商
     * @param comids 客户和供应商的id
     * @return BaseMessage
     */
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module="公司管理",methods="删除公司")
    public BaseMessage deleteCompany(@RequestBody int[] comids) {
        BaseMessage message = new BaseMessage();
        try {
            if ("删除公司成功" == this.companyService.deleteCompany(comids)) {
                ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
                message.setData("删除公司成功");
            } else if("不存在该公司" == this.companyService.deleteCompany(comids)){
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                message.setData("不存在该公司");
            }else{
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                message.setData("有与公司相关的项目");
            }
        } catch (Exception e) {
            logger.error("获取公司异常");
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return message;
    }


    /**
     * @desc 获取客户和供应商列表
     * @param serachWord 搜索关键词
     * @return BaseMessage
     */
    @RequestMapping(value = "/companySelective", method = RequestMethod.GET)
    @ResponseBody
    @SystemLog(module="公司管理",methods="查询客户或者供应商")
    public BaseMessage companySelective(@RequestParam(value="searchWord") String serachWord,
                                        @RequestParam(value = "type") String type) {
        BaseMessage message = new BaseMessage();
        try {
            if (null != this.companyService.findSelective(serachWord,type)) {
                ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
                message.setData(this.companyService.findSelective(serachWord,type));
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
     * 添加和修改客户
     * @param map 前端参数
     * @return BaseMessage
     * @throws Exception
     */
    @RequestMapping(value = "/modifyCompany",method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module="公司管理",methods="添加和删除客户")
    public BaseMessage modifyCompany(@RequestBody Map<String, Object> map) throws Exception{

        BaseMessage message = new BaseMessage();
        Boolean b = this.companyService.modifyCompany(map);
        if(b)
            ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
        else
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        return message;

    }

    /**
     * 修改客户或者供应商状态
     * @param map 前端参数
     * @return BaseMessage
     * @throws Exception
     */
    @RequestMapping(value = "/modifyCompanyStatus",method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module="公司管理",methods="修改客户或者供应商状态")
    public BaseMessage modifyCompanyStatus(@RequestBody Map<String, Object> map) throws Exception{

        BaseMessage message = new BaseMessage();
        Boolean b = this.companyService.modifyCompanyStatus(map);
        if(b)
            ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
        else
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        return message;

    }
}

