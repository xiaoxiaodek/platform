package com.www.platform.dao;

import com.www.platform.entity.Contract;

import java.util.List;

public interface ContractMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(Integer cid);

    List<Contract> selectAll();

    List<Contract> selectByCompany(Integer comId);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}