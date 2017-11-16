package com.www.platform.service.company;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.dao.CompanyMapper;
import com.www.platform.dao.ProjectMapper;
import com.www.platform.entity.Company;
import com.www.platform.entity.Item;
import com.www.platform.entity.Log;
import com.www.platform.entity.Project;
import com.www.platform.service.item.ItemService;
import com.www.platform.service.operateLog.OperateLogService;
import com.www.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by upsmart on 17-11-12.
 * @desc 客户和供应商的服务层实现
 */
@Service
public class CompanyServiceImpl implements CompanyService{

    private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private OperateLogService operateLogService;
    @Autowired
    private ItemService itemService;

    /**
     * 删除客户
     * @param comids
     * @return String
     */
    @Override
    public String deleteCompany(int[] comids) {

        if (null == comids || comids.length == 0) {
            return "不存在该公司";
        }
        for(int comid:comids) {
            Company company = companyMapper.selectByPrimaryKey(comid);
            List<Project> projects = null;
            if (company != null) {
                Project project1 = new Project();
                project1.setSuppid(new Integer(comid));
                projects = projectMapper.selectByauditstatidOrSuppid(project1);
                if (projects.size() == 0) {
                    Project project = new Project();
                    project.setAuditstatid(new Integer(comid));
                    projects = projectMapper.selectByauditstatidOrSuppid(project);
                    if (projects.size() == 0) {
                        companyMapper.deleteByPrimaryKey(comid);
                    }else {
                        return "有与公司相关的项目";
                    }
                }else {
                    return "有与公司相关的项目";
                }
            } else {
                return "不存在该公司";
            }
        }
        return "删除公司成功";
    }

    /**
     * @param serachWord
     * @param type
     * @param typeId
     * @desc 根据条件查询符合条件的客户
     * @return Map<String,List>
     */
    @Override
    public Map<String, List> findSelective(String serachWord,String type,int typeId){

        Map<String,List> map = new HashMap<>();
        List<Company> companies = null;
        Company company = new Company();
        company.setTypeid(new Integer(typeId));
        int types = 0;
        if(type!="")
            types = new Integer(type);
        switch(types){
            case GlobalConstants.COMPANY_ALL:
                companies = companyMapper.selectSelective(company);
                break;
            case GlobalConstants.COMPANY_COMMID:
                company.setComid(new Integer(serachWord));
                companies = companyMapper.selectSelective(company);
                break;
            case GlobalConstants.COMPANY_COMMADDR:
                company.setComaddr(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case GlobalConstants.COMPANY_COMMEMAIL:
                company.setComemail(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case GlobalConstants.COMPANY_COMCONTACT:
                company.setComcontact(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case GlobalConstants.COMPANY_COMNAME:
                company.setComname(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            default:
                break;
        }
        if(companies.size() == 0){
            return null;
        }else {
            map.put("companies", companies);
            map = putOtherModule(map,companies,"log");
            map = putOtherModule(map,companies,"item");
            return map;
        }
    }
    /**
     * 添加和修改公司信息不包括状态
     * @param map 前端参数
     * @param session
     * @return
     */
    @Override
    public Boolean modifyCompany(Map<String, Object> map,HttpSession session){

        Company company = new Company();
        int comid = 0;
        if(map.get("comid") == null) {
            company = addAndUpdate(map,company,comid);
            companyMapper.insertSelective(company);
            Boolean itemTrue = itemService.addAndUpdateItem(map,company.getComid());
            session.setAttribute("comid",company.getComid());
            return itemTrue;
        }else{
            company.setComid((Integer) map.get("comid"));
            company = addAndUpdate(map,company,(Integer) map.get("comid"));
            Boolean itemTrue = itemService.addAndUpdateItem(map,(Integer) map.get("comid"));
            session.setAttribute("comid",map.get("comid"));
            return companyMapper.updateByPrimaryKeySelective(company) != 0 ? true : false && itemTrue;
        }
    }

    /**
     * 添加和修改公司信息不包括状态
     * @param map 前端参数
     * @param session
     * @return
     */
    @Override
    public Boolean modifyCompanyStatus(Map<String, Object> map,HttpSession session){

        Company company = new Company();
        company.setComid((Integer) map.get("comid"));
        session.setAttribute("comid",map.get("comid"));
        company = addAndUpdate(map,company,(Integer) map.get("comid"));
        return companyMapper.updateByPrimaryKeySelective(company) != 0 ? true : false;

    }

    /**
     * 添加和修改公司代码复用
     * @param map 前端参数
     * @param company 需要添加和修改的公司
     * @param comid 前端参数用来判断是否添加还是修改
     * @return
     */
    public Company addAndUpdate(Map<String, Object> map,Company company,int comid){

        Date t = DateUtil.getNowDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date c = sdf.parse(sdf.format(t));
            company.setPid((Integer)map.get("pid"));
            company.setComaddr((String) map.get("comaddr"));
            company.setComemail((String) map.get("comemail"));
            company.setComname((String) map.get("comname"));
            company.setComcontact((String) map.get("comcontact"));
            company.setTypeid((Integer)map.get("typeId"));
            company.setStatusid((Integer)map.get("statusId"));
            company.setCommercestatus((Integer)map.get("commercestatus"));
            company.setTechstatus((Integer)map.get("techstatus"));
            company.setAccountstatus((Integer)map.get("accountstatus"));
            company.setOnlinestatus((Integer)map.get("onlinestatus"));
            if(comid == 0){
                company.setCreatetime(c);
                company.setModtime(c);
            }else{
                company.setModtime(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增公司失败，空指针");
            return null;
        }
        return company;
    }


    /**
     * @desc 重构代码
     * @param map
     * @param companies
     * @param type
     * @return Map<String, List>
     */
    public Map<String, List>  putOtherModule(Map<String, List> map,List<Company> companies,String type){

        List<Log> logs = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        for(Company c : companies) {
            if(type == "log") {
                if (operateLogService.selectByComid(c.getComid()) != null)
                    for (Log l : operateLogService.selectByComid(c.getComid())) {
                        logs.add(l);
                        map.put("logs", logs);
                    }
            }
            else {
                    Item item = new Item();
                    item.setComid(c.getComid());
                    if (itemService.findSelective(item) != null)
                        for (Item i : itemService.findSelective(item)) {
                            items.add(i);
                            map.put("items",items);
                        }
                }
        }
        return map;
    }
}
