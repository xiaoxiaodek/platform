package com.www.platform.dao;

import com.www.platform.entity.Interface;

public interface InterfaceMapper {
    int deleteByPrimaryKey(Integer idfid);

    int insert(Interface record);

    int insertSelective(Interface record);

    Interface selectByPrimaryKey(Integer idfid);

    int updateByPrimaryKeySelective(Interface record);

    int updateByPrimaryKey(Interface record);

    Interface[] selectByIds(Integer[] ids);//考虑set
}