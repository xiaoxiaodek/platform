package com.www.platform.controller;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.message.BaseMessage;
import com.www.platform.message.MessageCode;
import com.www.platform.message.StatusCode;
import com.www.platform.service.ProjectService;
import com.www.platform.util.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author zjl
 * @version 0.0
 */
@Controller @RequestMapping("project") public class ProjectController {
    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired private ProjectService projectService;


    /**
     * 新增项目
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST) @ResponseBody
    public BaseMessage addProject(@RequestBody Map<String, Object> map, HttpSession session) {
        BaseMessage msg = new BaseMessage();
        int uid = Integer.parseInt(session.getAttribute(GlobalConstants.UID).toString());
        map.put("uid", uid);
        try {
            msg.setData(projectService.addProject(map));
            if ("添加成功".equals(msg.getData())) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        } catch (Exception e) {
            logger.error("增加项目异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }



    /**
     * 修改项目
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST) @ResponseBody
    public BaseMessage updateProject(@RequestBody Map<String, Object> map) {
        BaseMessage msg = new BaseMessage();
        try {
            msg.setData(projectService.updateProject(map));
            if ("修改成功".equals(msg.getData())) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);

            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        } catch (Exception e) {
            logger.error("更新项目异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }


    /**
     * 删除项目
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST) @ResponseBody
    public BaseMessage deleteProject(@RequestBody Map<String, Object> map) {
        BaseMessage msg = new BaseMessage();
        try {
            msg.setData(projectService.deleteProjectByPid(map));
            if ("删除成功".equals(msg.getData())) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        } catch (Exception e) {
            logger.error("删除项目异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }



    /**
     * 根据供应商编号查项目
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectProjectBySuppid", method = RequestMethod.POST) @ResponseBody
    public BaseMessage selectProjectBySuppid(@RequestBody Map<String, Object> map) {
        BaseMessage msg = new BaseMessage();
        try {
            msg.setData(projectService.selectProjectBySuppid(map));
            if (null != msg.getData()) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        } catch (Exception e) {
            logger.error("查询项目异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 根据客户编号查项目
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectProjectByAuditstatid", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage selectProjectByAuditstatid(@RequestBody Map<String, Object> map) {
        BaseMessage msg = new BaseMessage();
        try {
            msg.setData(projectService.selectProjectByAuditstatid(map));
            if (null != msg.getData()) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        } catch (Exception e) {
            logger.error("查询项目异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }


}
