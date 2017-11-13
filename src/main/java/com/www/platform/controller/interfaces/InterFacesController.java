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

    @RequestMapping(value = "/project", method = RequestMethod.GET) @ResponseBody
    public BaseMessage searchByProject(@RequestParam int projectId) {
        BaseMessage msg = new BaseMessage();
        try {
            Interface[] a = interfacesService.searchByProject(projectId);
            msg.setData(a);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
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
    public BaseMessage add(@RequestBody Map<String, Object> params) {
        BaseMessage msg = new BaseMessage();
        Interface in = new Interface();
        try {
            in.setInfname((String) params.get("infname"));
            in.setInftype((Integer) params.get("inftype"));
            in.setExinf((Byte) params.get("exinf"));
            in.setBasprice((Long) params.get("basprice"));
            in.setDicount((Long) params.get("dicount"));
            in.setCreatetime((Date) params.get("createtime"));
            in.setInterfacecol((Date) params.get("modtime"));
            Boolean result = interfacesService.add(in);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        } catch (Exception e) {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
        }
        return msg;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST) @ResponseBody
    public BaseMessage delete(@RequestParam int[] ids) {
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
    public BaseMessage edit(@RequestBody Map<String, Object> params) {
        BaseMessage msg = new BaseMessage();
        Interface in = new Interface();
        in.setIdfid((Integer) params.get("idfid"));
        in.setInfname((String) params.get("infname"));
        in.setInftype((Integer) params.get("inftype"));
        in.setExinf((Byte) params.get("exinf"));
        in.setBasprice((Long) params.get("basprice"));
        in.setDicount((Long) params.get("dicount"));
        in.setCreatetime((Date) params.get("createtime"));
        in.setInterfacecol((Date) params.get("modtime"));
        try{
            interfacesService.update(in);
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        }catch (Exception e){
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
        }
        return msg;
    }
}
