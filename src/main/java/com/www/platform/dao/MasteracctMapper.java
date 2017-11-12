package com.www.platform.dao;

import com.www.platform.entity.Masteracct;

public interface MasteracctMapper {
    int deleteByPrimaryKey(Integer maid);

    int insert(Masteracct record);

    int insertSelective(Masteracct record);

    Masteracct selectByPrimaryKey(Integer maid);

    int updateByPrimaryKeySelective(Masteracct record);

    int updateByPrimaryKey(Masteracct record);
}