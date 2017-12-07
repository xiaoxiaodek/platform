package com.www.platform.service.item;

import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:24 on 17-11-15.
 * @Modified By:
 */
public interface ItemService {

    int updateItem(Map<String, Object> map);
    int updateItemByTypeId(Map<String, Object> map);
    Boolean insertItem(Map<String, Object> map);
}
