package com.www.platform.service.company;

import com.www.platform.entity.Company;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-12.
 *
 * @desc 客户和供应商的服务层接口
 */
public interface CompanyService {

    List<Company> selectAll(int typeId, String serachWord, String searchType, String uname, int role);
    String deleteCompanies(int[] comid);
    Boolean updateCompany(Map<String, Object> map);
    Boolean insertCompany(Map<String, Object> map);
}
