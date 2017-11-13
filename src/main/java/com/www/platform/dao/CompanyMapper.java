package com.www.platform.dao;

import com.www.platform.entity.Company;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc @desc 客户和供应商的接口层
 */

@Repository
public interface CompanyMapper {

    int deleteByPrimaryKey(Integer comid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectAll();
    List<Company> selectSelective(Company record);
}