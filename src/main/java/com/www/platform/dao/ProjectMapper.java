package com.www.platform.dao;

import com.www.platform.entity.Project;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer pid);

    List<Project> selectProjectBySuppid(Integer suppid);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKeyWithBLOBs(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> selectByauditstatidOrSuppid(Project record);
}