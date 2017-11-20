package com.www.platform.service.item;

import com.www.platform.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:24 on 17-11-15.
 * @Modified By:
 */
public interface ItemService {

    List<Item> findSelective(Item item);
    Boolean addAndUpdateItem(Map<String, Object> map,int comid);
}
