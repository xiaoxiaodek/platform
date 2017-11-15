package com.www.platform.service.company;

import com.www.platform.dao.CompanyMapper;
import com.www.platform.dao.ProjectMapper;
import com.www.platform.entity.Company;
import com.www.platform.entity.Project;
import com.www.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-12.
 *
 * @desc 客户和供应商的服务层实现
 */
@Service
public class CompanyServiceImpl implements CompanyService{

    private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ProjectMapper projectMapper;

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
     * @param serachWord,type
     * @desc 根据条件查询符合条件的客户
     * @return Map<String, List>
     */
    @Override
    public Map<String, List> findSelective(String serachWord,String type){

        Map<String,List> map = new HashMap<>();
        List<Company> companies = null;
        List<Project> projects = null;
        Company company = new Company();
        Project project = new Project();
        int typeId = 0;
        if(type!="")
            typeId = new Integer(type);
        switch(typeId){
            case 6:
                project.setAuditstatid(new Integer(serachWord));
                projects = projectMapper.selectByauditstatidOrSuppid(project);
                break;
            case 1:
                project.setSuppid(new Integer(serachWord));
                projects = projectMapper.selectByauditstatidOrSuppid(project);
                break;
            case 2:
                company.setComaddr(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case 3:
                company.setComemail(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case 4:
                company.setComcontact(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case 5:
                company.setComname(serachWord);
                companies = companyMapper.selectSelective(company);
                break;
            case 7:
                company.setComid(new Integer(serachWord));
                companies = companyMapper.selectSelective(company);
                break;
            case 0:
                companies = companyMapper.selectSelective(company);
                break;
            default:
                break;
        }
        if(companies.size() == 0 && projects == null){
            return null;
        }else {
            map.put("companies", companies);
            map.put("projects", projects);
            return map;
        }
    }
    /**
     * 添加和修改公司信息不包括状态
     * @param map 前端参数
     * @return
     */
    @Override
    public Boolean modifyCompany(Map<String, Object> map){

        Company company = new Company();
        int comid = 0;

        if(map.get("comid") == null) {
            company = addAndUpdate(map,company,comid);
            return companyMapper.insertSelective(company) != 0 ? true : false;
        }else{
            company.setComid((Integer) map.get("comid"));
            company = addAndUpdate(map,company,(Integer) map.get("comid"));
            return companyMapper.updateByPrimaryKeySelective(company) != 0 ? true : false;
        }
    }

    /**
     * 添加和修改公司信息不包括状态
     * @param map 前端参数
     * @return
     */
    @Override
    public Boolean modifyCompanyStatus(Map<String, Object> map){

        Company company = new Company();

        company.setComid((Integer) map.get("comid"));
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
            company.setTypeid((Integer)map.get("typeid"));
            company.setStatusid((Integer)map.get("statusid"));
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
}
