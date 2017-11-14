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
     * @param ids
     * @return
     */
    @Override public Interface[] searchByList(Integer[] ids) {
        return interfaceMapper.selectByIds(ids);
    }

    @Override public Interface[] searchAll() {
        Interface[] result;
        result = interfaceMapper.selectAll();
        return result;
    }

    @Override public Interface searchById(int id) {
        return interfaceMapper.selectByPrimaryKey(id);
    }

    @Override public Interface[] searchByProject(int id) {
        Interface[] result;
        Integer[] a = pinterfaceMapper.selectByProject(id);
        result = interfaceMapper.selectByIds(a);
        return result;
    }

    //    @Override
    //    ArrayList<Interface> searchByProject(int projectId){
    //
    //    }
    //    @Override
    //    ArrayList<Interface> searchByCompany(int companyId){
    //
    //    }
    @Override public Boolean add(Interface interfaces) {
        try {
            interfaceMapper.insert(interfaces);
            return true;
        } catch (ClassCastException e) {
            logs.error("类型不正确");
            return false;
        }
    }

    //    @Override
    //    String add(@Param("interfaces") LinkedList<Interface> interfaces){
    //
    //    }
    @Override public Boolean update(Interface intefaces) {
        Interface i = new Interface();
        try {
            i = interfaceMapper.selectByPrimaryKey(intefaces.getIdfid());
        } catch (NullPointerException e) {
            logs.error("接口不存在");
        }
        try {
            interfaceMapper.updateByPrimaryKey(intefaces);
        } catch (Exception e) {
            logs.error("更新出错");
        }
        return false;

    }

    @Override public Boolean delete(int[] interfaces) {
        try {
            for (int s : interfaces) {
                interfaceMapper.deleteByPrimaryKey(s);
            }
            return true;
        } catch (Exception e) {
            logs.error("数据删除失败");
        }
        return false;
    }
    //    @Override
    //    String delete(@Param("interfaces") LinkedList<Interface> interfaces){}
}
