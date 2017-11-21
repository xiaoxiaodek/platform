package com.www.platform.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午5:28
 */
@Service
public interface ContractService {
    // 所有的合同列表
     Object contractList();

    //获取按照时间排序的合同
//      Object sortContractList(String startOrEnd);


    //根据客户公司comid获取合同列表
     Object queryContractByComid(int comid);

    // 销售员的合同列表
//     Object saleContractList();

    // 合同详情
     Map<String , Object> contractDetail(int cid);

    // 删除合同
     Object deleteContract(int[] cids);

    // 新增合同
     String addContract(String s, List<String> fnames);

    // 编辑合同
     String editContract(String path, String s, MultipartFile[] file);

}
