package com.www.platform.service.company;

import com.www.platform.dao.CompanyMapper;
import com.www.platform.entity.Company;
import com.www.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 查询所有客户
     * @return
     */
    @Override
    public List<Company> findByAll() {

        if(null == companyMapper.selectAll()){
            return null;
        }else {
            return companyMapper.selectAll();
        }
    }


    @Override
    public Boolean addCompany(Map<String, Object> map){

        Company company = new Company();

        Date createtime = DateUtil.getNowDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {
            company.setPid(Integer.parseInt((String) map.get("pid")));
            company.setComaddr((String) map.get("comaddr"));
            company.setComemail((String) map.get("comemail"));
            company.setComname((String) map.get("comname"));
            company.setComcontact((String) map.get("comcontact"));
            Date c = sdf.parse(sdf.format(createtime));
            company.setCreatetime(c);
            company.setModtime(c);
            company.setTypeid(Integer.parseInt((String) map.get("typeid")));

        }catch(Exception e){
            e.printStackTrace();
            logger.error("新增项目失败，空指针");
            return false;
        }
        return companyMapper.insert(company)!=0?true:false;
    }
}
