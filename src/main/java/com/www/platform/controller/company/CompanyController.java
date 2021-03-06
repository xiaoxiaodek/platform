package com.www.platform.controller.company;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.entity.Company;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/queryCompany", method = RequestMethod.GET)
    @ResponseBody
    @SystemLog(module = "公司管理", methods = "查询")
    public BaseMessage selectAll(@RequestParam(value = "typeId") int typeId,
                                 @RequestParam(value = "searchWord") String serachWord,
                                 @RequestParam(value = "type") String searchType,
                                 HttpSession session)throws Exception {
        BaseMessage message = new BaseMessage();
        if (session.getAttribute("role") == null) {
            session.setAttribute("operateResult",MessageCode.FAILED);
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            return message;
        }else {
            // TODO: 17-12-5 角色暂时（1，2，3，4）分别为管理员，商务，运营，技术，考虑添加角色表
            int role =(int) session.getAttribute("role");
            String  uname = (String)session.getAttribute(GlobalConstants.USERNAME);
            List<Company> companies = this.companyService.selectAll(typeId, serachWord, searchType, uname, role);
            if (companies != null) {
                ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
                message.setData(companies);
            } else {
                session.setAttribute("operateResult",MessageCode.FAILED);
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
            return message;
        }
    }


    /**
     * @param comids 客户和供应商的id
     * @return BaseMessage
     * @desc 删除客户和供应商2.0
     */
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module = "公司管理", methods = "删除")
    public BaseMessage deleteCompanies(@RequestBody int[] comids, HttpSession session)throws Exception {

        BaseMessage message = new BaseMessage();
        if(session.getAttribute("role")!= null && (int)session.getAttribute("role")==1) {
            String result = this.companyService.deleteCompanies(comids);
            if (result.equals("删除成功")) {
                ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
                message.setData("删除成功");
            } else if (result.equals("不存在该公司")) {
                session.setAttribute("operateResult",MessageCode.FAILED);
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                message.setData("不存在该公司");
            } else {
                session.setAttribute("operateResult",MessageCode.FAILED);
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                message.setData("删除失败");
            }
        }else{
            session.setAttribute("operateResult",MessageCode.FAILED);
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.USER_LIMITED);
            message.setData("没有权限");
        }
        return message;
    }

    /**
     * @desc 修改
     * @param map
     * @return BaseMessage
     * @throws Exception
     */
    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module = "公司管理", methods = "修改")
    public BaseMessage updateCompany(@RequestBody Map<String, Object> map, HttpSession session)throws Exception {
        BaseMessage message = new BaseMessage();
        String result = this.companyService.updateCompany(map,session);
        if (result.equals("更新成功")) {
            ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
        }
        else if(result.equals("没有权限")) {
            session.setAttribute("operateResult",MessageCode.FAILED);
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.USER_LIMITED);
        }
        else {
            session.setAttribute("operateResult",MessageCode.FAILED);
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }
        return message;
    }

    /**
     * @desc 添加
     * @param map
     * @return BaseMessage
     * @throws Exception
     */
    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module = "公司管理", methods = "添加")
    public BaseMessage insertCompany(@RequestBody Map<String, Object> map, HttpSession session)throws Exception {
        BaseMessage message = new BaseMessage();
        if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 1) {
            Boolean result = this.companyService.insertCompany(map);
            if (result)
                ResponseUtil.buildResMsg(message, MessageCode.SUCCESS, StatusCode.SUCCESS);
            else {
                session.setAttribute("operateResult", MessageCode.FAILED);
                ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        }else{
            session.setAttribute("operateResult", MessageCode.FAILED);
            ResponseUtil.buildResMsg(message, MessageCode.FAILED, StatusCode.USER_LIMITED);
        }
        return message;
    }

}

