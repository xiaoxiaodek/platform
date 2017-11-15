package com.www.platform.dao;

import com.www.platform.entity.Cfile;

import java.util.List;

public interface CfileMapper {
    int deleteByPrimaryKey(Integer cfid);

    int insert(Cfile record);

    int insertSelective(Cfile record);

    Cfile selectByPrimaryKey(Integer cfid);

    List<Cfile> selectByCid(Integer cid);

    int updateByPrimaryKeySelective(Cfile record);

    int updateByPrimaryKey(Cfile record);
}