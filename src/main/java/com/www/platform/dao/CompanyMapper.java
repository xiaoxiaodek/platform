package com.www.platform.dao;

import com.www.platform.entity.Company;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer comid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectSelective(Company record);

    Company selectByAll(Company record);
}