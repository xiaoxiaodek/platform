package com.www.platform.dao;

import com.www.platform.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer iid);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByComid(Integer comid);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    Item selectSelective(Item record);

    int updateItem(Map<String, Object> map);
    int updateItemByTypeId(Map<String, Object> map);
    int insertItem(Map<String, Object> map);
}