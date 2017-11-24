package com.www.platform.service.item;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.dao.ItemMapper;
import com.www.platform.entity.Item;
import com.www.platform.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:状态服务层
 * @Date: Created by 下午3:24 on 17-11-15.
 * @Modified By:
 */
@Service
public class ItemServiceImpl implements ItemService{

    private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private ItemMapper itemMapper;
    /**
     * @desc 根据条件查询状态表
     * @param item
     * @return
     */
    @Override
    public Item findSelective(Item item) {
        Item items = itemMapper.selectSelective(item);
        return items;
    }

    /**
     * @desc 根据条件查询状态表
     * @param comid
     * @return List<Item>
     */
    @Override
    public List<Item> findByComid(int comid) {
        List<Item> items = itemMapper.selectByComid(comid);
        return items;
    }
    /**
     * @desc 添加或者更新
     * @param map,comid
     * @return Boolean
     */
    public Boolean addAndUpdateItem(Map<String, Object> map, int comid){

        List<Item> items = new ArrayList<>();

        if(map.get("comid") == null) {
            items = addAndUpdate(map,items,comid);
            for(Item item:items){
                int insertTrue = itemMapper.insertSelective(item);
                if(insertTrue == 0)
                    return false;
            }
        }else{
            items = addAndUpdate(map,items,Integer.parseInt((String) map.get("comid")));
            for(Item item:items) {
                Item iidItem = itemMapper.selectSelective(item);
                item.setIid(iidItem.getIid());
                int updateTrue = itemMapper.updateByPrimaryKeySelective(item);
                if(updateTrue == 0)
                    return false;
            }
        }
        return true;
    }

//    /**
//     *
//     * @param map
//     * @param item
//     * @param comid
//     * @return
//     */
//    public Item addAndUpdate(Map<String, Object> map,Item item,int comid){
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            item.setPid((Integer)map.get("pid"));
//            item.setPtypeid((Integer)map.get("pTypeid"));
//            item.setPstatus((Integer)map.get("pStatus"));
//            item.setComid(comid);
//            item.setUname((String)map.get("uname"));
//            item.setStarttime(sdf.parse((String)map.get("starttime")));
//            item.setEndtime(sdf.parse((String)map.get("endtime")));
//            item.setRemark((String)map.get("remark"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("新增项目失败，空指针");
//            return null;
//        }
//        return item;
//    }


    /**
     * @desc 代码重构
     * @param map
     * @param items
     * @param comid
     * @return items
     */
    public List<Item> addAndUpdate(Map<String, Object> map, List<Item> items, int comid){

        Item commerceItem = new Item();
        Item techItem = new Item();
        Item onlineItem = new Item();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            commerceItem.setPid((Integer)map.get("pid"));
            commerceItem.setPid(1);
            commerceItem.setPtypeid(GlobalConstants.COMMERCE_STATUS);
//            commerceItem.setPstatus((Integer)map.get("commerceStatus"));
            commerceItem.setPstatus(Integer.parseInt((String)map.get("commerceStatus")));
            commerceItem.setComid(comid);
            commerceItem.setUname((String)map.get("commerceUname"));
            commerceItem.setTime(sdf.parse((String)map.get("commerceEndtime")));
            commerceItem.setRemark((String)map.get("remark"));

            techItem.setPid(1);
            techItem.setPtypeid(GlobalConstants.TECH_STATUS);
            techItem.setPstatus(Integer.parseInt((String)map.get("techStatus")));
            techItem.setComid(comid);
            techItem.setUname((String)map.get("techUname"));
            techItem.setTime(sdf.parse((String)map.get("techEndtime")));
            techItem.setRemark((String)map.get("remark"));

            onlineItem.setPid(1);
            onlineItem.setPtypeid(GlobalConstants.ONLINE_STATUS);
            onlineItem.setPstatus(Integer.parseInt((String)map.get("onlineStatus")));
            onlineItem.setComid(comid);
            onlineItem.setUname((String)map.get("onlineUname"));
            onlineItem.setTime(sdf.parse((String)map.get("onlineStarttime")));
            onlineItem.setRemark((String)map.get("remark"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增项目失败，空指针");
            return null;
        }
        items.add(commerceItem);
        items.add(techItem);
        items.add(onlineItem);
        return items;
    }

    /**
     * @desc 删除状态
     * @param comid
     * @return
     */
    public Boolean deleteByComid(int comid){
        return itemMapper.deleteByComid(comid) !=0 ? true : false;
    }
}
