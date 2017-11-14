package com.www.platform.service.contract;

import com.www.platform.dao.CfileMapper;
import com.www.platform.entity.Cfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午5:29
 */
@Service
public class CFileServiceImpl implements CFileService{

    private static Logger logger = LoggerFactory.getLogger(CFileService.class);
    @Autowired
    private CfileMapper cfileMapper;

    @Override
    public void addCFile(int cid, int fid) {
        System.out.println("cid:" + cid +", fid:" + fid);
        Cfile cFile = new Cfile();
        cFile.setCid(cid);
        cFile.setFid(fid);
        try {
            this.cfileMapper.insert(cFile);
        } catch (Exception e) {
            logger.error("新增合同文件关联表出错");
            e.printStackTrace();
        }
    }
}
