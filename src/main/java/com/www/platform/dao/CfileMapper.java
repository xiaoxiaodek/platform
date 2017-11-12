package com.www.platform.dao;

import com.www.platform.entity.Cfile;

public interface CfileMapper {
    int deleteByPrimaryKey(Integer cfid);

    int insert(Cfile record);

    int insertSelective(Cfile record);

    Cfile selectByPrimaryKey(Integer cfid);

    int updateByPrimaryKeySelective(Cfile record);

    int updateByPrimaryKey(Cfile record);
}