package com.www.platform.service.contract;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.dao.CfileMapper;
import com.www.platform.dao.ContractMapper;
import com.www.platform.dao.FileMapper;
import com.www.platform.dao.ProjectMapper;
import com.www.platform.entity.Cfile;
import com.www.platform.entity.Contract;
import com.www.platform.service.other.FileService;
import com.www.platform.util.DateUtil;
import com.www.platform.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午5:29
 */
@Service public class ContractServiceImpl implements ContractService {
    private static Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    @Autowired private ContractMapper contractMapper;
    @Autowired private CfileMapper cfileMapper;
    @Autowired private FileMapper fileMapper;
    @Autowired private ProjectMapper projectMapper;
    @Autowired private FileService fileService;
    @Autowired private CFileService cFileService;

    //    @Override public Object saleContractList() {
    //        // 需获取登陆销售员的id
    //        // Subject subject = SecurityUtils.getSubject();
    //        // Session session = subject.getSession();
    //        // int uid = (int) session.getAttribute(GlobalConstants.UID);
    //        int uid = 1;
    //        int empid = this.comempRepository.findByUid(uid).getEmpid();
    //        List<ContractDto> contracts =
    //            this.contractConvertor.toDtos(this.contractRepository.findByEmpid(empid));
    //        if (null == contracts) {
    //            return null;
    //        }
    //        List<Map<String, Object>> results = new ArrayList<>();
    //        for (ContractDto con : contracts) {
    //            Map<String, Object> result = new HashMap<>();
    //            try {
    //                result.put("cid", con.getCid());
    //                result.put("cnum", con.getCnum());
    //                result.put("cname", con.getCname());
    //                result.put("camt", con.getCamt());
    //                result.put("comname",
    //                    this.clientCompanyRepository.findByComid(con.getComid()).getComname());
    //                result.put("cstarttime", con.getCstarttime());
    //                result.put("cendtime", con.getCendtime());
    //            } catch (Exception e) {
    //                result.clear();
    //                logger.error("销售员获取合同列表异常");
    //                e.printStackTrace();
    //            }
    //            results.add(result);
    //        }
    //        return results;
    //    }

    @Override public Object contractList() {
        List<Contract> contracts =this.contractMapper.selectAll();
            //            this.contractConvertor.toDtos(this.contractRepository.findAll());

        if (null == contracts) {
            return null;
        }
        List<Map<String, Object>> results = new ArrayList<>();
        for (Contract con : contracts) {
            Map<String, Object> result = new HashMap<>();
            try {
                result.put("cid", con.getCid());
                //                result.put("cnum", con.getCnum());
                result.put("cname", con.getCname());
                DecimalFormat df = new DecimalFormat("###,###,###.##");
                String m = df.format(con.getCamt());
                result.put("camt", m);
                //                result.put("comname",
                //                    this.clientCompanyRepository.findByComid(con.getComid()).getComname());
                result.put("cstarttime", con.getCstarttime());
                String a= con.getCstarttime().toString();
                result.put("cendtime", con.getCendtime());
            } catch (Exception e) {
                result.clear();
                logger.error("获取全部合同列表异常");
                e.printStackTrace();
            }
            results.add(result);
        }
//        return results;
        return contracts;
    }

    //    @Override public Object sortContractList(String startOrEnd){
    //
    //        List<ContractDto> contracts = null;
    //        if(startOrEnd.equals("开始")) {
    //            contracts = this.contractConvertor.toDtos(this.contractRepository.sortbycreatetime());
    //        } else {
    //            contracts = this.contractConvertor.toDtos(this.contractRepository.sortbycendtime());
    //        }
    //
    //        if (null == contracts) {
    //            return null;
    //        }
    //        List<Map<String, Object>> results = new ArrayList<>();
    //        for (ContractDto con : contracts) {
    //            Map<String, Object> result = new HashMap<>();
    //            try {
    //                result.put("cid", con.getCid());
    //                result.put("cnum", con.getCnum());
    //                result.put("cname", con.getCname());
    //                result.put("camt", con.getCamt());
    //                result.put("comname",
    //                    this.clientCompanyRepository.findByComid(con.getComid()).getComname());
    //                result.put("cstarttime", con.getCstarttime());
    //                result.put("cendtime", con.getCendtime());
    //            } catch (Exception e) {
    //                result.clear();
    //                logger.error("获取全部合同列表异常");
    //                e.printStackTrace();
    //            }
    //            results.add(result);
    //        }
    //        return results;
    //    }

    @Override public Map<String, Object> contractDetail(int cid) {
        //        Contract dto = this.contractConvertor.toDto(this.contractRepository.findByCid(cid));
        Contract dto = this.contractMapper.selectByPrimaryKey(cid);
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("cid", dto.getCid());
            //            result.put("cnum", dto.getCnum());
            result.put("cname", dto.getCname());
            //            result.put("ctype", dto.getCtype());
            result.put("camt", dto.getCamt());
            result.put("cstarttime", dto.getCstarttime());
            result.put("cendtime", dto.getCendtime());
            //            result.put("cremarks", dto.getCremarks());

            //            result.put("comid", dto.getComid());
            //            result.put("comname", this.clientCompanyRepository.findByComid(dto.getComid()));
            //            result.put("createtime", dto.getCreatetime());
            result.put("modtime", dto.getModtime());
            //            result.put("empname", this.comempRepository.findByEmpid(dto.getEmpid()).getEmpname());
            //            result.put("brname", this.brandRepository
            //                .findByBrid(this.comempRepository.findByEmpid(dto.getEmpid()).getBid())
            //                .getBrname());
            //            result.put("isremind", dto.isIsremind());
            //            if (null == this.projectRepository.findByCinfid(cid)) {
            //                result.put("projects", null);
            //            } else {
            //                result.put("projects", this.projectRepository.findByCinfid(cid));
            //            }
        } catch (Exception e) {
            result.clear();
            logger.error("获取合同详情异常");
            e.printStackTrace();
        }
        return result;
    }

    @Override public String addContract(String s, List<String> fnames) {
        String result = null;
        //        Subject subject = SecurityUtils.getSubject();
        //        Session session = subject.getSession();
        //        int uid = (int) session.getAttribute(GlobalConstants.UID);
        Contract contract1;
        Map<String, Object> map = new HashMap<>();
        try {
            map = JsonUtil.toObject(s, Map.class);
        } catch (Exception e1) {
            logger.error("获取新增合同表单数据失败");
            result = "获取前台数据失败";
            e1.printStackTrace();
        }

        String cname;
        String cendtime;
        String appreason;
        int pid;
        Double camt;
        String cstarttime;
        if (map.get("camt") != null && map.get("cstarttime") != null
            &&  map.get("cendtime") != null &&  map.get("cname") != null
            && map.get("appreason") != null &&  map.get("auditstatid") != null
            &&  map.get("suppid") != null &&  map.get("cstat") != null
            &&  map.get("pid") != null &&  map.get("appusrid") != null ) {
            cname = (String) map.get("cname");
            camt = Double.parseDouble(map.get("camt").toString());
            String amtstr =  map.get("camt").toString();
            Boolean strResult = amtstr.matches("^[0-9]+\\.?[0-9]?[0-9]?$");
            if (!strResult) {
                return "请检查金额是否正确";
            }
            cstarttime =  map.get("cstarttime").toString();
            cendtime =  map.get("cendtime").toString();
            appreason =  map.get("appreason").toString();
            pid = (int) map.get("pid");
        } else {
            return result = "表单不完整";
        }

//        Pattern p = Pattern.compile("^(\\d{4})/(\\d{2})/(\\d{2})$");
//        Matcher matcher1 = p.matcher(cstarttime);
//        Matcher matcher2 = p.matcher(cendtime);
//        boolean rs1 = matcher1.matches();
//        boolean rs2 = matcher2.matches();


//        if (rs1 == false || rs2 == false) {
//            return "日期格式不正确";
//        }
//        int comid = Integer.parseInt((String) map.get("comid"));
//        Boolean isremind = Boolean.parseBoolean((String) map.get("isremind"));
//
        Contract contract = new Contract();
//        Date signdate = DateUtil.getNowDate();
//        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMMddHHmmss");
//        Random random = new Random();
//        //        contract.setCnum(sdf0.format(signdate)+ random.nextInt(10));
//        contract.setCname(cname);
//        //        contract.setCtype(ctype);
//        contract.setCamt(camt);
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
//        Date cstarttime1;
//        Date cendtime1;
//        try {
//            cstarttime1 = sdf1.parse(cstarttime);
////            contract.setCstarttime(cstarttime1);
////            cendtime1 = sdf1.parse(cendtime);
////            contract.setCendtime(cendtime1);
//        } catch (ParseException e1) {
//            result = "时间处理失败";
//            e1.printStackTrace();
//        }

//        if ((contract.getCendtime().compareTo(contract.getCstarttime())) == 1) {
            //            contract.setCremarks(cremarks);
            //            contract.setComid(comid);
            //            contract.setIsremind(isremind);
            //            contract.setCreatetime(createtime);
//            contract.setModtime(createtime);
            //            contract.setEmpid(this.comempRepository.findByUid(uid).getEmpid());
            //            contract.setBid(this.comempRepository.findByUid(uid).getBid());

//        } else {
//            return result = "请查看日期是否准确";
//        }

        try {
            if (null != fnames && 0 != fnames.size()) {

                int cid = this.contractMapper.insert(contract);
                //            if (null != fnames && 0 != fnames.size()) {
                for (int i = 0; i < fnames.size(); i++) {
                    int fid = this.fileService
                        .addFile(fnames.get(i), pid, GlobalConstants.CONTRACT, appreason);
                    System.out.println("fid = " + fid);
                    this.cFileService.addCFile(cid, fid);
                }
            } else {
                return "无合同附件";
            }
        } catch (Exception e) {
            result = "新建合同失败";
            logger.error("新增合同失败");
            e.printStackTrace();
        }

        return result;
    }

    // 编辑合同
    @Override public String editContract(String path, String s, MultipartFile[] files) {
        // TODO Auto-generated method stub
        String result = null;
        int isremind1;
        //        Subject subject = SecurityUtils.getSubject();
        //        Session session = subject.getSession();
        //        int uid = (int) session.getAttribute(GlobalConstants.UID);
        Map<String, Object> map = new HashMap<>();
        try {
            map = JsonUtil.toObject(s, Map.class);
        } catch (Exception e1) {
            logger.error("获取新增合同表单数据失败");
            result = "获取前台数据失败";
            e1.printStackTrace();
        }
        int cid = Integer.parseInt((String) map.get("cid"));
        String cname = (String) map.get("cname");
        int ctype = Integer.parseInt((String) map.get("ctype"));
        Double camt = Double.parseDouble((String) map.get("camt"));
        Date createtime = DateUtil.getNowDate();
        String cstarttime = (String) map.get("cstarttime");
        String cendtime = (String) map.get("cendtime");
        String cremarks = (String) map.get("cremarks");
        int comid = Integer.parseInt((String) map.get("comid"));
        Boolean isremind = Boolean.parseBoolean((String) map.get("isremind"));
        Contract contract = this.contractMapper.selectByPrimaryKey(cid);
        contract.setCname(cname);
        //        contract.setCtype(ctype);
        contract.setCamt(camt);

        Date cstarttime1;
        Date cendtime1;
        if (cstarttime.indexOf("/") != -1) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
            try {
                cstarttime1 = sdf1.parse(cstarttime);

//                contract.setCstarttime(cstarttime1);
            } catch (ParseException e1) {
                result = "时间处理失败";
                e1.printStackTrace();
            }
        } else {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                cstarttime1 = sdf1.parse(cstarttime);

//                contract.setCstarttime(cstarttime1);

            } catch (ParseException e1) {
                result = "时间处理失败";
                e1.printStackTrace();
            }
        }

        if (cendtime.indexOf("/") != -1) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
            try {
                cendtime1 = sdf1.parse(cendtime);
                contract.setCendtime(cendtime1);
            } catch (ParseException e1) {
                result = "时间处理失败";
                e1.printStackTrace();
            }
        } else {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {

                cendtime1 = sdf1.parse(cendtime);

                contract.setCendtime(cendtime1);

            } catch (ParseException e1) {
                result = "时间处理失败";
                e1.printStackTrace();
            }
        }

        //        contract.setCremarks(cremarks);
        //        contract.setComid(comid);
        //        contract.setIsremind(isremind);
        contract.setModtime(createtime);
        //        contract.setEmpid(this.comempRepository.findByUid(uid).getEmpid());
        //        contract.setBid(this.comempRepository.findByUid(uid).getBid());
        try {
            this.contractMapper.insert(contract);
        } catch (Exception e) {
            result = "编辑合同失败";
            logger.error("编辑合同失败");
            e.printStackTrace();
        }
        //        if (null != this.cFileRepository.findByCid(cid)) {
        //            List<CFile> cfiles = this.cFileRepository.findByCid(cid);
        //            for (CFile cfile : cfiles) {
        //                this.fileRepository.delete(cfile.getFid());
        //                this.cFileRepository.delete(cfile);
        //            }
        //        }
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                String fname = files[i].getOriginalFilename();
                File targetFile = new File(path, fname);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    files[i].transferTo(targetFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.fileService.editFile(fname, comid, path, cremarks);
            }
        }
        return result;
    }



    //删除合同
    @Override public Object deleteContract(int[] cids) {
        // TODO Auto-generated method stub
        String result = "1";
        if (null == cids || cids.length == 0) {
            return null;
        }
        try {
            for (int i = 0; i < cids.length; i++) {
                Contract contract = this.contractMapper.selectByPrimaryKey(cids[i]);
                if (0 != this.projectMapper.selectByCid(cids[i]).size()) {
                    return contract.getCname() + "有关联项目,不允许删除";
                } else {
                    this.contractMapper.deleteByPrimaryKey(cids[i]);
                    if (null != this.cfileMapper.selectByCid(cids[i])) {
                        List<Cfile> cfiles = this.cfileMapper.selectByCid(cids[i]);
                        for (Cfile cfile : cfiles) {
                            this.fileMapper.deleteByPrimaryKey(cfile.getFid());
                            this.cfileMapper.deleteByPrimaryKey(cfile.getCid());
                        }
                    }
                }
            }
            result = "删除合同成功 ";
            logger.info("删除合同成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除合同异常");
        }
        return result;
    }

    @Override public Object queryContractByComid(int comid) {
        List<Map<String, Object>> results = new ArrayList<>();
        List<Contract> dtos =
            this.contractMapper.selectByCompany(comid);
        if (null == dtos || dtos.size() == 0) {
            return null;
        } else {
            for (Contract dto : dtos) {
                Map<String, Object> map = new HashMap<>();
                map.put("cid", dto.getCid());
                map.put("cname", dto.getCname());
                results.add(map);
            }
            return results;
        }
    }

}
