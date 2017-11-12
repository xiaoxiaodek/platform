package com.www.platform.dao;

import com.www.platform.entity.Contract;

public interface ContractMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}