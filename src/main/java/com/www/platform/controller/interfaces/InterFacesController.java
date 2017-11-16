package com.www.platform.controller.interfaces;

import com.www.platform.entity.Interface;
import com.www.platform.message.BaseMessage;
import com.www.platform.message.MessageCode;
import com.www.platform.message.StatusCode;
import com.www.platform.service.interfaces.InterfacesService;
import com.www.platform.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by upsmart on 17-11-12.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:28
 */
@Controller @RequestMapping("interfaces") public class InterFacesController {
    private static Logger logger = LoggerFactory.getLogger(InterFacesController.class);

    @Autowired private InterfacesService interfacesService;

    @RequestMapping(value = "/", method = RequestMethod.GET) @ResponseBody
    public BaseMessage SearchAll() {
        BaseMessage msg = new BaseMessage();
        try {
            Interface[] a = interfacesService.searchAll();
            msg.setData(a);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
        }
        return msg;
    }



    @RequestMapping(value = "/project", method = RequestMethod.GET) @ResponseBody
    public BaseMessage searchByProject(@RequestParam int projectId) {
        BaseMessage msg = new BaseMessage();
        try {
            Interface[] a = interfacesService.searchByProject(projectId);
            if (a == null || a.length == 0) {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
                return msg;
            } else {
                msg.setData(a);
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
        }
        return msg;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET) @ResponseBody
    public BaseMessage searchByCompany(@RequestParam int companyId) {
        BaseMessage msg = new BaseMessage();
        return msg;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) @ResponseBody
    public BaseMessage add(@RequestBody Map<String, Object> params) throws Exception {
        BaseMessage msg = new BaseMessage();
        Interface in = new Interface();
        try {
            in.setInfname((String) params.get("infname"));
            in.setInftype((Integer) params.get("inftype"));
            in.setExinf(Boolean.valueOf(params.get("exinf").toString()));
            in.setBasprice(Long.valueOf(params.get("basprice").toString()));
            in.setDicount(BigDecimal.valueOf((Long) params.get("dicount")));

            Date createtime = new Date();
            in.setCreatetime(createtime);
            //            in.setCreatetime((Date) params.get("createtime"));
            //            in.setModtime((Date) params.get("modtime"));
            Boolean result = interfacesService.add(in);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        } catch (Exception e) {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
            e.printStackTrace();
        }


        //        Pattern p = Pattern.compile("^(\\d{4})/(\\d{2})/(\\d{2})$");
        //        Matcher matcher1 = p.matcher(cstarttime);
        //        Matcher matcher2 = p.matcher(cendtime);
        //        boolean rs1 = matcher1.matches();
        //        boolean rs2 = matcher2.matches();
        //
        //
        //        if (rs1 == false || rs2 == false) {
        //            return "日期格式不正确";
        //        }
        //        int comid = Integer.parseInt((String) map.get("comid"));
        //        Boolean isremind = Boolean.parseBoolean((String) map.get("isremind"));
        //
        //        Contract contract = new Contract();
        //        Date createtime = DateUtil.getNowDate();
        //        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMMddHHmmss");
        //        Random random = new Random();
        //        contract.setCnum(sdf0.format(createtime)+ random.nextInt(10));
        //        contract.setCname(cname);
        //        contract.setCtype(ctype);
        //        contract.setCamt(camt);
        //        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        //        Date cstarttime1;
        //        Date cendtime1;
        //        try {
        //            cstarttime1 = sdf1.parse(cstarttime);
        //            contract.setCstarttime(cstarttime1);
        //            cendtime1 = sdf1.parse(cendtime);
        //            contract.setCendtime(cendtime1);
        //        } catch (ParseException e1) {
        //            result = "时间处理失败";
        //            e1.printStackTrace();
        //        }



        return msg;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST) @ResponseBody
    public BaseMessage delete(@RequestBody int[] ids) throws Exception {
        BaseMessage msg = new BaseMessage();
        try {
            interfacesService.delete(ids);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        } catch (Exception e) {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
        }
        return msg;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST) @ResponseBody
    public BaseMessage edit(@RequestBody Map<String, Object> params) throws Exception {
        BaseMessage msg = new BaseMessage();
        Interface in = new Interface();
        in.setIdfid((Integer) params.get("idfid"));

        in.setInfname((String) params.get("infname"));
        in.setInftype((Integer) params.get("inftype"));

        in.setExinf(Boolean.valueOf(params.get("exinf").toString()));
        in.setBasprice(Long.valueOf(params.get("basprice").toString()));
        in.setDicount(BigDecimal.valueOf((Long) params.get("dicount")));

        Date modtime = new Date();
        in.setModtime(modtime);
        try {
            interfacesService.update(in);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        } catch (Exception e) {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
        }
        return msg;
    }
}
