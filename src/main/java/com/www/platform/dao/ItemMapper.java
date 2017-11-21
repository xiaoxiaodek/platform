package com.www.platform.dao;

import com.www.platform.entity.Item;

import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer iid);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByComid(Integer comid);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    Item selectSelective(Item record);

    int deleteByComid(Integer comid);
}