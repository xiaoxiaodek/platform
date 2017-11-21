package com.www.platform.util;

import com.www.platform.constant.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (C), 2017, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc
 * @date 17-3-31
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    private static String userDir = System.getProperty("user.dir");

    /**
     * 新增多个文件
     * @param files
     * @return
     */
    public static List<String> addFiles(MultipartFile[] files) {
        List<String> fnames = new ArrayList<>();
        logger.info("用户路径： " + userDir);
        logger.info("########################################");
        logger.info("addFiles中上传文件： " + files);
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                String fname = files[i].getOriginalFilename();
                logger.info("addFiles中原始文件名称： " + fname);
            }
        }
        logger.info("##########################################");
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                String fname = files[i].getOriginalFilename();
                logger.info("原始文件名称： " + fname);
                fnames.add(fname);
                File targetFile = new File(userDir + "/" + GlobalConstants.CONTRACT, fname);
                logger.info("文件上传路径： " + targetFile);
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
            }
        }
        return fnames;
    }

    /**
     * 删除文件
     * @param paths
     * @return
     */
    public static void deleteFiles(List<String> paths) {
        for (String path : paths) {
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }
    }
}