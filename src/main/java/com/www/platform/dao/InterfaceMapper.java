package com.www.platform.dao;

import com.www.platform.entity.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface InterfaceMapper {
    int deleteByPrimaryKey(Integer idfid);

    int insert(Interface record);

    int insertSelective(Interface record);

    Interface selectByPrimaryKey(Integer idfid);

    int updateByPrimaryKeySelective(Interface record);

    int updateByPrimaryKey(Interface record);

    ArrayList<Interface> selectByIds(List ids);//考虑set
}