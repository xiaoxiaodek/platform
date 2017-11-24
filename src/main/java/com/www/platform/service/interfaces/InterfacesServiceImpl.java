package com.www.platform.service.interfaces;

import com.www.platform.dao.ExinterfaceMapper;
import com.www.platform.dao.InterfaceMapper;
import com.www.platform.dao.PinterfaceMapper;
import com.www.platform.entity.Interface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by upsmart on 17-11-12.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:31
 */
@Service public class InterfacesServiceImpl implements InterfacesService {
    private static Logger logs = LoggerFactory.getLogger(InterfacesService.class);

    @Autowired private InterfaceMapper interfaceMapper;
    @Autowired private ExinterfaceMapper exinterface;
    @Autowired private PinterfaceMapper pinterfaceMapper;

    /**
     * @param ids：接口id数组
     * @return 接口对象数组
     */
    @Override public Interface[] searchByList(Integer[] ids) {
        Interface[] result;
        try {
            result = interfaceMapper.selectByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    @Override public Interface[] searchAll() {
        Interface[] result;
        try {
            result = interfaceMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * @param id :接口id
     * @return 接口对象
     */
    @Override public Interface searchById(int id) {
        Interface result;
        try {
            result = interfaceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * @param id :项目id
     * @return 接口对象数组
     */
    @Override public Interface[] searchByProject(int id) {
        Interface[] result;
        Integer[] a;
        try {
            a = pinterfaceMapper.selectByProject(id);
            if (a != null && a.length != 0) {
                result = interfaceMapper.selectByIds(a);
            } else {
                result = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    //    @Override
    //    ArrayList<Interface> searchByCompany(int companyId){
    //
    //    }

    /**
     * @param interfaces 接口对象
     * @return Boolean
     */
    @Override public Boolean add(Interface interfaces) {
        try {
            interfaceMapper.insert(interfaces);
            return true;
        } catch (ClassCastException e) {
            logs.error("类型不正确");
            return false;
        } catch (Exception e) {
            logs.error("新增出错");
            return false;
        }
    }

    //    @Override
    //    String add(@Param("interfaces") LinkedList<Interface> interfaces){
    //
    //    }

    /**
     * @param intefaces 接口对象
     * @return Boolean
     */
    @Override public Boolean update(Interface intefaces) {
        Interface i;
        try {
            i = interfaceMapper.selectByPrimaryKey(intefaces.getIdfid());
        } catch (NullPointerException e) {
            logs.error("接口不存在");
            return false;
        }
        try {
            if (i != null) {
                interfaceMapper.updateByPrimaryKeySelective(intefaces);
            }
        } catch (Exception e) {
            logs.error("更新出错");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param interfaces 接口对象
     * @return Boolean
     */
    @Override public Boolean delete(int[] interfaces) {
        try {
            for (int s : interfaces) {
                interfaceMapper.deleteByPrimaryKey(s);
            }
            return true;
        } catch (Exception e) {
            logs.error("数据删除失败");
            e.printStackTrace();
        }
        return false;
    }
    //    @Override
    //    String delete(@Param("interfaces") LinkedList<Interface> interfaces){}
}
