package com.www.platform.service.company;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-12.
 *
 * @desc 客户和供应商的服务层接口
 */
public interface CompanyService {

    String deleteCompany(int[] comid);
    Boolean modifyCompany(Map<String, Object> map,HttpSession session);
    Map<String, List> findSelective(String serachWord,String type,int typeId);
}
