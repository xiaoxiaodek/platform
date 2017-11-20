package com.www.platform.service.other;

import com.www.platform.dao.FileMapper;
import com.www.platform.entity.File;
import com.www.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by upsmart on 17-11-14.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午1:34
 */
@Service public class FileServiceImpl implements FileService {
    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired private FileMapper fileMapper;

    @Override public int addFile(String fname, int pid, String flocal, String fsummary) {
        int fid = 1;
        File file = new File();
        file.setFname(fname);
                file.setPid(pid);
                file.setFsummary(fsummary);
        //TODO
        file.setFlocal("/file/contractfile/" + fname);
        Date date = DateUtil.getNowDate();
        file.setCreatetime(date);
        file.setModtime(date);
        //        file.setEmpid(this.comempRepository.findByUid(uid).getEmpid());
        //        file.setBrid(this.comempRepository.findByUid(uid).getBid());
        try {
            fid = this.fileMapper.insert(file);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新建文件表失败");
        }
        System.out.println("fid " + fid);
        return fid;
    }

    @Override public String editFile(String fname, int comid, String flocal, String fsummary) {
        //TODO
        //      Subject subject = SecurityUtils.getSubject();
        //      Session session = subject.getSession();
        //      int uid = (int) session.getAttribute(GlobalConstants.UID);
        int uid = 1;
        File file = new File();
        file.setFname(fname);
        //        file.setComid(comid);
        //        file.setFremarks(fremarks);
        //TODO
        file.setFlocal("/file/contractfile/" + fname);
        Date date = DateUtil.getNowDate();
        file.setModtime(date);
        file.setCreatetime(new Date());
        //        file.setEmpid(this.comempRepository.findByUid(uid).getEmpid());
        //        file.setBrid(this.comempRepository.findByUid(uid).getBid());
        try {
            this.fileMapper.insert(file);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新建文件表失败");
        }
        return "success";
    }
}
