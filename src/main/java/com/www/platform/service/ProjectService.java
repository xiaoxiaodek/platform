package com.www.platform.service;

import com.www.platform.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 * *
 * @author zjl
 * @version 0.0
 */
public interface ProjectService {
    String addProject(Map<String,Object> map);

    String deleteProjectByPid(Map<String,Object> map);

    String updateProject(Map<String,Object> map);

    List<Project> selectProjectBySuppid(Map<String,Object> map);

    List<Project> selectProjectByAuditstatid(Map<String,Object> map);
}
