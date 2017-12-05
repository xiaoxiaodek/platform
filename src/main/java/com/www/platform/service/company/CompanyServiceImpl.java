package com.www.platform.service.company;

import com.www.platform.dao.CompanyMapper;
import com.www.platform.dao.LogMapper;
import com.www.platform.entity.Company;
import com.www.platform.entity.Log;
import com.www.platform.service.item.ItemService;
import com.www.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by upsmart on 17-11-12.
 * @desc 客户和供应商的服务层实现
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LogMapper logMapper;

    /**
     * @desc platform2.0版本的查询条件
     * @param typeId
     * @param serachWord
     * @param searchType
     * @return
     */
    @Transactional
    public List<Company> selectAll(int typeId, String serachWord, String searchType, String uname, int role){
        // TODO: 17-12-5 角色暂时（1，2，3，4）分别为管理员，商务，运营，技术，考虑添加角色表
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("typeId",typeId);
        parameter.put("searchType",searchType);
        if(searchType.equals("commid"))
            parameter.put("searchWord",Integer.valueOf(serachWord));
        else
            parameter.put("searchWord",serachWord);
        List<Company> companies = companyMapper.queryCompanyList(parameter);
        List<Company> list2 = companies.stream().filter(s -> s.getItems().get(1).getUname() != uname).collect(Collectors.toList());
        if(searchType == "") {
            List<Log> logs = logMapper.selectByNoComid(parameter);
            Company company = new Company();
            company.setLogs(logs);
            companies.add(company);
            list2 = companies.stream().filter(s -> s.getItems().get(1).getUname() != uname).collect(Collectors.toList());
            return list2;
        }
        return list2;
    }


    /**
     * 删除客户
     * @param comids
     * @return String
     */
    @Transactional
    public String deleteCompanies(int[] comids){
        if (null == comids || comids.length == 0) {
            return "不存在该公司";
        }
        int result = companyMapper.deleteCompany(comids);
        if(result !=0)
            return "删除成功";
        return "删除失败";
    }


    /**
     * @desc 更新
     * @param map
     * @return Boolean
     */
    @Transactional
    public Boolean updateCompany(Map<String, Object> map){

        map = addAndUpdate(map,"update");

        int companyResult = companyMapper.updateCompany(map);
        int itemResult = itemService.updateItem(map);
        if(companyResult !=0 && itemResult!=0) {
            return true;
        }
        return false;
    }

    /**
     * @desc 添加客户
     * @param map
     * @return
     */
    @Transactional
    public Boolean insertCompany(Map<String, Object> map){

        map = addAndUpdate(map,"add");
        Company company = companyMapper.selectSelective(map);
        if(company != null)
            return false;
        int insertCompanyTrue = companyMapper.insertCompany(map);
        if(insertCompanyTrue != 0){
            Company company1 = companyMapper.selectSelective(map);
            int comid = company1.getComid();
            map.put("comid",comid);
        }
        Boolean insertItemTrue = itemService.insertItem(map);
        return insertItemTrue;
    }


    /**
     * @desc 添加和修改公司代码复用
     * @param map 前端参数
     * @return Company
     */
    public Map<String, Object> addAndUpdate(Map<String, Object> map,String type){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date t = DateUtil.getNowDate();
        try {
            map.put("typeId",Integer.parseInt((String)map.get("typeId")));
            map.put("pid",Integer.parseInt((String)map.get("pid")));

            map.put("commerceStatus",Integer.parseInt((String)map.get("commerceStatus")));
            map.put("commerceEndtime",sdf.parse((String) map.get("commerceEndtime")));
            map.put("techStatus",Integer.parseInt((String)map.get("techStatus")));
            map.put("techEndtime",sdf.parse((String) map.get("techEndtime")));
            map.put("onlineStatus",Integer.parseInt((String)map.get("onlineStatus")));
            map.put("onlineStarttime",sdf.parse((String) map.get("onlineStarttime")));

            if(type.equals("update")) {
                map.put("modtime", t);
            }
            else{
                map.put("createtime",t);
                map.put("modtime",t);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }
}
