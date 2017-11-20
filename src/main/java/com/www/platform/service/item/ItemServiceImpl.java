package com.www.platform.service.item;

import com.www.platform.dao.ItemMapper;
import com.www.platform.entity.Item;
import com.www.platform.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
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
    public List<Item> findSelective(Item item) {
        List<Item> items = itemMapper.selectSelective(item);
        return items;
    }


    /**
     * @desc 添加或者更新
     * @param map,comid
     * @return Boolean
     */
    public Boolean addAndUpdateItem(Map<String, Object> map, int comid){

        Item item = new Item();

        if(map.get("comid") == null) {
            item = addAndUpdate(map,item,comid);
            return itemMapper.insertSelective(item) != 0 ? true : false;
        }else{
            item = addAndUpdate(map,item,(Integer) map.get("comid"));
            return itemMapper.updateByPrimaryKeySelective(item) != 0 ? true : false;
        }
    }

    /**
     *
     * @param map
     * @param item
     * @param comid
     * @return
     */
    public Item addAndUpdate(Map<String, Object> map,Item item,int comid){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            item.setPid((Integer)map.get("pid"));
            item.setPtypeid((Integer)map.get("pTypeid"));
            item.setPstatus((Integer)map.get("pStatus"));
            item.setComid(comid);
            item.setUname((String)map.get("uname"));
            item.setStarttime(sdf.parse((String)map.get("starttime")));
            item.setEndtime(sdf.parse((String)map.get("endtime")));
            item.setRemark((String)map.get("remark"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增项目失败，空指针");
            return null;
        }
        return item;
    }

    public Boolean deleteByComid(int comid){
        return itemMapper.deleteByComid(comid) !=0 ? true : false;
    }
}
