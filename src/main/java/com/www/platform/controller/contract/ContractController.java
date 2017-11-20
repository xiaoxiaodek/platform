package com.www.platform.controller.contract;

import com.www.platform.dao.ContractMapper;
import com.www.platform.message.BaseMessage;
import com.www.platform.message.MessageCode;
import com.www.platform.message.StatusCode;
import com.www.platform.service.contract.ContractService;
import com.www.platform.util.FileUtil;
import com.www.platform.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午5:25
 */
@Controller @RequestMapping("/contract") public class ContractController {
    private static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired private ContractService contractService;
    @Autowired private ContractMapper contractMapper;

    //获取所有的合同列表
    @RequestMapping(value = "contractList", method = RequestMethod.GET) @ResponseBody

    public BaseMessage contractList() {
        BaseMessage msg = new BaseMessage();
        try {
            if (null != this.contractService.contractList()) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
                msg.setData(this.contractService.contractList());
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                msg.setData("未获取到合同数据");
            }
        } catch (Exception e) {
            logger.error("获取所有的合同列表异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

//    //获取销售员的合同列表
//
//    @RequestMapping(value = "salecontractList", method = RequestMethod.GET) @ResponseBody
//
//    public BaseMessage saleContractList() {
//        BaseMessage msg = new BaseMessage();
//        try {
//            if (null != this.contractService.saleContractList()) {
//                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
//                msg.setData(this.contractService.saleContractList());
//            } else {
//                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
//                msg.setData("未获取到合同数据");
//            }
//        } catch (Exception e) {
//            logger.error("获取销售员的合同列表异常");
//            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
//            e.printStackTrace();
//        }
//        return msg;
//    }

    //合同详情

    @RequestMapping(value = "/detail", method = RequestMethod.GET) @ResponseBody

    public BaseMessage detail(@RequestParam(value = "cid", required = true) Integer cid) {
        BaseMessage msg = new BaseMessage();
        try {
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            msg.setData(this.contractService.contractDetail(cid));
        } catch (Exception e) {
            logger.error("获取合同详情异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

    //删除合同

    @RequestMapping(value = "delete", method = RequestMethod.POST) @ResponseBody
    public BaseMessage delete(@RequestBody int[] cids) {
        BaseMessage msg = new BaseMessage();
        try {
            String result = (String) this.contractService.deleteContract(cids);
            if (result == null) {
                System.out.println("=============result:  " + result);
                msg.setData("未选中数据");
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
                System.out.println("======是否执行=======");
                msg.setData(result);
                System.out.println(result);

                System.out.println("========msg.setData===" + msg.getData());
            }

        } catch (Exception e) {
            logger.error("删除合同异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }



    // 新增合同，并在cfile表添加目录
    @RequestMapping(value = "add", method = RequestMethod.POST) @ResponseBody
    public BaseMessage addContract(HttpServletRequest request, HttpSession sesssion,
        @RequestParam(value = "info", required = false) String s,
        @RequestParam(value = "file", required = false) MultipartFile[] files) {

        BaseMessage msg = new BaseMessage();
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info("Controller中上传文件： " + files);
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                String fname = files[i].getOriginalFilename();
                logger.info("Controller中原始文件名称： " + fname);
            }
        }
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            List<String> fnames =null;
            if (null != files) {
                //上传文件
                 fnames = FileUtil.addFiles(files);
            }

            System.out.println("====fnames======" + fnames);
            // 写入合同表


            String result = this.contractService.addContract(s, fnames);
            System.out.println("==========result" + result);
            if (null != result) {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
                msg.setData(result);

            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
                msg.setData(result);
            }
        } catch (Exception e) {
            logger.error("上传文件失败: " + e);
            msg.setData("系统出错");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

    // 编辑合同

    @RequestMapping(value = "edit", method = RequestMethod.POST) @ResponseBody
    public BaseMessage editContract(HttpServletRequest request, HttpSession sesssion,
        @RequestParam("info") String s,
        @RequestParam(value = "file", required = false) MultipartFile[] file1) {

        BaseMessage msg = new BaseMessage();
        try {
            // 编辑合同表
            String path = request.getSession().getServletContext().getRealPath("/file/contract/");
            String result = this.contractService.editContract(path, s, file1);

            if (null != result) {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.DATA_ERROR);
                msg.setData(result);
            } else {

                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
                msg.setData(result);
            }
        } catch (Exception e) {
            logger.error("上传文件失败");
            msg.setData("修改合同失败");
            e.printStackTrace();
        }
        return msg;
    }

//    @RequestMapping(value = "/findbycomid", method = RequestMethod.GET) @ResponseBody
//
//    public BaseMessage findbycomid(@RequestParam(value = "comid", required = true) Integer comid) {
//        BaseMessage msg = new BaseMessage();
//        try {
//            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
//
//            System.out.println(this.contractRepository.findByComid(comid) == null);
//            msg.setData(this.contractRepository.findByComid(comid));
//        } catch (Exception e) {
//            logger.error("获取合同列表异常");
//            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
//            e.printStackTrace();
//        }
//        return msg;
//    }


    //根据客户公司id获取合同列表

    @RequestMapping(value = "queryContractByComid", method = RequestMethod.GET) @ResponseBody
    public BaseMessage queryContractByComid(
        @RequestParam(value = "comid", required = true) Integer comid) {
        BaseMessage msg = new BaseMessage();
        try {
            Object result = this.contractService.queryContractByComid(comid);
            if (null != result) {
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
                msg.setData(result);
            } else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
                msg.setData("未获取到合同数据");
            }
        } catch (Exception e) {
            logger.error("获取所有的合同列表异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

//    @RequestMapping(value = "sort", method = RequestMethod.GET) @ResponseBody
//    public BaseMessage sort(@RequestParam(value = "data", required = true) String startOrEnd) {
//        BaseMessage msg = new BaseMessage();
//        try {
//            if (null != this.contractService.sortContractList(startOrEnd)) {
//                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
//                msg.setData(this.contractService.sortContractList(startOrEnd));
//            } else {
//                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
//                msg.setData("未获取到合同数据");
//            }
//        } catch (Exception e) {
//            logger.error("获取所有的合同列表异常");
//            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
//            e.printStackTrace();
//        }
//        return msg;
//    }

}
