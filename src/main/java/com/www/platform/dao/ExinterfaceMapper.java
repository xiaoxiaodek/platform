package com.www.platform.dao;

import com.www.platform.entity.Exinterface;

public interface ExinterfaceMapper {
    int deleteByPrimaryKey(Integer exinfid);

    int insert(Exinterface record);

    int insertSelective(Exinterface record);

    Exinterface selectByPrimaryKey(Integer exinfid);

    int updateByPrimaryKeySelective(Exinterface record);

    int updateByPrimaryKey(Exinterface record);
}