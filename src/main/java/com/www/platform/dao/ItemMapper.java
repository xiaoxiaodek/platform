package com.www.platform.dao;

import com.www.platform.entity.Item;

import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer iid);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer iid);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectSelective(Item record);

}