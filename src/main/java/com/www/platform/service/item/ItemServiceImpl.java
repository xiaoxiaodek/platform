package com.www.platform.service.item;

import com.www.platform.dao.ItemMapper;
import com.www.platform.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: upsmart
 * @Description:状态服务层
 * @Date: Created by 下午3:24 on 17-11-15.
 * @Modified By:
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private ItemMapper itemMapper;

    /**
     * @desc 修改状态
     * @param map
     * @return int
     */
    public int updateItem(Map<String, Object> map){
        int resutl = itemMapper.updateItem(map);
        return resutl;
    }

    /**
     * @desc 添加状态
     * @param map
     * @return Boolean
     */
    public Boolean insertItem(Map<String, Object> map) {
        int result = itemMapper.insertItem(map);
        return result==0?false:true;
    }
}
