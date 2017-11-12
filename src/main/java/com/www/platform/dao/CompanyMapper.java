package com.www.platform.dao;

import com.www.platform.entity.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer comid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}