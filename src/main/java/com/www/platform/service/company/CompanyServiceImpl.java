package com.www.platform.service.company;

import com.www.platform.dao.CompanyMapper;
import com.www.platform.dao.ItemMapper;
import com.www.platform.dao.LogMapper;
import com.www.platform.entity.Company;
import com.www.platform.entity.Item;
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
    @Autowired
    private ItemMapper itemMapper;

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
        List<Company> list2 = companies;
        switch (role){
            //管理员
            case 1: break;
            //商务
            case 2: list2 = companies.stream().filter(s -> s.getItems().get(0).getUname().equals(uname)).collect(Collectors.toList());
                    break;
            //运营
            case 3: list2 = companies.stream().filter(s -> s.getItems().get(2).getUname().equals(uname)).collect(Collectors.toList());
                    break;
            //技术
            case 4: list2 = companies.stream().filter(s -> s.getItems().get(1).getUname().equals(uname)).collect(Collectors.toList());
                    break;
            default:list2 = null;
        }
//        list2 = companies.stream().filter(s -> s.getItems().get(1).getUname().equals(uname)).collect(Collectors.toList());
        if(searchType.equals("")) {
            List<Log> logs = logMapper.selectByNoComid(parameter);
            Company company = new Company();
            company.setLogs(logs);
            list2.add(company);
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
    public String updateCompany(Map<String, Object> map,HttpSession session){

        map = addAndUpdate(map,"update");
        Boolean authority = judgeRole(map,(int)session.getAttribute("role"));
        if(!authority){
            return "没有权限";
        }
        Map<String, Object> change = new HashMap<>();
        change.put("comid",map.get("comid"));
        change.put("typeId",map.get("typeId"));
        if (session.getAttribute("role") != null) {
            switch ((int)session.getAttribute("role")){
                case 1: change = map;break;
                case 2:change.put("commerceStatus",map.get("commerceStatus"));change.put("pTypeid",0);break;
                case 3:change.put("onlineStatus",map.get("onlineStatus"));change.put("pTypeid",2);break;
                case 4:change.put("techStatus",map.get("techStatus"));change.put("pTypeid",1);break;
                default: break;
            }
        }
        int companyResult, itemResult;
        try{
            if((int)session.getAttribute("role") == 1) {
                itemResult = itemService.updateItem(change);
                companyResult = companyMapper.updateCompany(change);
            }else{
                companyResult = companyMapper.updateCompany(change);
                itemResult = itemService.updateItemByTypeId(change);
            }
            if(companyResult !=0 && itemResult!=0) {
                return "更新成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "更新失败";
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
            if(map.get("typeId")!=null&&map.get("pid")!=null&&map.get("commerceStatus")!=null &&
                map.get("commerceEndtime")!=null&&map.get("techStatus")!=null&&map.get("techEndtime")!=null&&
                map.get("onlineStatus")!=null&&map.get("onlineStarttime")!=null) {

                map.put("typeId", Integer.parseInt((String) map.get("typeId")));
                map.put("pid", Integer.parseInt((String) map.get("pid")));

                map.put("commerceStatus", Integer.parseInt((String) map.get("commerceStatus")));
                map.put("commerceEndtime", sdf.parse((String) map.get("commerceEndtime")));
                map.put("techStatus", Integer.parseInt((String) map.get("techStatus")));
                map.put("techEndtime", sdf.parse((String) map.get("techEndtime")));
                map.put("onlineStatus", Integer.parseInt((String) map.get("onlineStatus")));
                map.put("onlineStarttime", sdf.parse((String) map.get("onlineStarttime")));
            }else {

            }
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

    /**
     * 更新时判断是否有权限修改
     * @param map
     * @param role
     * @return
     */
    private Boolean judgeRole(Map<String,Object> map,int role){
        Company company = companyMapper.selectByPrimaryKey(Integer.parseInt((String) map.get("comid")));
        List<Item> items = itemMapper.selectByComid(Integer.parseInt((String) map.get("comid")));

        if(!map.get("comname").equals(company.getComname()) ||
                !map.get("comaddr").equals(company.getComaddr()) ||
                !map.get("comcontact").equals(company.getComcontact()) ||
                !map.get("comemail").equals(company.getComemail()) ||
                !map.get("pid").equals(company.getPid()) ||
                !map.get("comcontactName").equals(company.getComcontactname())
                )
            return false;
        switch (role) {
            case 2:
                for (Item item : items) {
                    if (item.getPtypeid() == 1 && !map.get("techStatus").equals(item.getPstatus())
                            || !map.get("techEndtime").equals(item.getTime())
                            || !map.get("techUname").equals(item.getUname()))
                        return false;
                    if (item.getPtypeid() == 2 && !map.get("onlineStatus").equals(item.getPstatus())
                            || !map.get("onlineStarttime").equals(item.getTime())
                            || !map.get("onlineUname").equals(item.getUname()))
                        return false;
                }
                break;
            case 3:
                for (Item item : items) {
                    System.out.println(map.get("commerceStatus").equals(item.getPstatus()));
                    if (item.getPtypeid() == 0 && !map.get("commerceStatus").equals(item.getPstatus())
                            || !map.get("commerceEndtime").equals(item.getTime())
                            || !map.get("commerceUname").equals(item.getUname()))
                        return false;
                    if (item.getPtypeid() == 1 && !map.get("techStatus").equals(item.getPstatus())
                            || !map.get("techEndtime").equals(item.getTime())
                            || !map.get("techUname").equals(item.getUname()))
                        return false;
                }
                break;
            case 4:
                for (Item item : items) {
                    if (item.getPtypeid() == 0 && !map.get("commerceStatus").equals(item.getPstatus())
                            || !map.get("commerceEndtime").equals(item.getTime())
                            || !map.get("commerceUname").equals(item.getUname()))
                        return false;
                    if (item.getPtypeid() == 2 && !map.get("onlineStatus").equals(item.getPstatus())
                            || !map.get("onlineStarttime").equals(item.getTime())
                            || !map.get("onlineUname").equals(item.getUname()))
                        return false;
                }
                break;
        }
        return true;
    }
}
