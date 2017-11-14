package com.www.platform.controller;

import com.www.platform.message.BaseMessage;
import com.www.platform.service.ProjectService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 * @author zjl
 * @version 0.0
 */
@Controller
@RequestMapping("project")
public class ProjectController {
    private static Logger logger= LoggerFactory.getLogger(ProjectController.class);
  @Autowired private ProjectService projectService;



  @RequestMapping(value="/addProject",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage addProject(@RequestBody Map<String,Object> map){
      BaseMessage msg=new BaseMessage();
      msg.setData(projectService.addProject(map));

      return msg;
    }

  @RequestMapping(value="/updateProject",method = RequestMethod.POST)
  @ResponseBody
  public BaseMessage updateProject(@RequestBody Map<String,Object> map){
    BaseMessage msg=new BaseMessage();
    msg.setData(projectService.updateProject(map));

    return msg;
  }

  @RequestMapping(value="/deleteProject",method = RequestMethod.POST)
  @ResponseBody
  public BaseMessage deleteProject(@RequestBody Map<String,Object> map){
    BaseMessage msg=new BaseMessage();
    msg.setData(projectService.deleteProjectByPid(map));

    return msg;
  }

  @RequestMapping(value="/selectProjectBySuppid",method = RequestMethod.POST)
  @ResponseBody

  public BaseMessage selectProjectBySuppid(@RequestBody Map<String,Object> map){

    BaseMessage msg=new BaseMessage();
    System.out.println("controller suppid"+map.get("suppid").toString());
    msg.setData(projectService.selectProjectBySuppid(map));

    return msg;
  }




}
