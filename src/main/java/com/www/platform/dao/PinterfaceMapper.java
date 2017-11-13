package com.www.platform.dao;

import com.www.platform.entity.Pinterface;

public interface PinterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pinterface record);

    int insertSelective(Pinterface record);

    Pinterface selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pinterface record);

    int updateByPrimaryKey(Pinterface record);

    Integer[] selectByProject(Integer projectId);
}