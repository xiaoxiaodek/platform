package com.www.platform.dao;

import com.www.platform.entity.Intfaceexinf;

public interface IntfaceexinfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Intfaceexinf record);

    int insertSelective(Intfaceexinf record);

    Intfaceexinf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Intfaceexinf record);

    int updateByPrimaryKey(Intfaceexinf record);
}