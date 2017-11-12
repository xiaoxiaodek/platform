package com.www.platform.dao;

import com.www.platform.entity.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer acctid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer acctid);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}