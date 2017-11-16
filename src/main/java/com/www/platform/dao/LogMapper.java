package com.www.platform.dao;

import com.www.platform.entity.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectByComid(int comid);
}