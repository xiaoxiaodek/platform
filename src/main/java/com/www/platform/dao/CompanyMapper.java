package com.www.platform.dao;

import com.www.platform.entity.Company;

import java.util.List;
import java.util.Map;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer comid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> queryCompanyList(Map<String, Object> map);
    int deleteCompany(int[] comids);
    int updateCompany(Map<String, Object> map);
    Company selectSelective(Map<String, Object> map);
    int insertCompany(Map<String, Object> map);
}