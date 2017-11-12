package com.www.platform.dao;

import com.www.platform.entity.Operate;

public interface OperateMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Operate record);

    int insertSelective(Operate record);

    Operate selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Operate record);

    int updateByPrimaryKey(Operate record);
}