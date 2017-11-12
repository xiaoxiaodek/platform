package com.www.platform.service.interfaces;

import com.www.platform.controller.interfaces.InterFacesController;
import com.www.platform.dao.ExinterfaceMapper;
import com.www.platform.dao.InterfaceMapper;
import com.www.platform.entity.Interface;
import com.www.platform.entity.Intfaceexinf;
import com.www.platform.entity.Pinterface;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by upsmart on 17-11-12.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:31
 */
@Service public class InterfacesServiceImpl implements InterfacesService {
    @Autowired private InterfaceMapper interfaces;
    @Autowired private ExinterfaceMapper exinterface;
    @Autowired private Pinterface pinterface;
    @Autowired private Intfaceexinf intfaceexinf;

    /**
     *
     * @param ids
     * @return
     */
    @Override public ArrayList<Interface> searchByList(List ids) {
        return interfaces.selectByIds(ids);
    }

    @Override public Interface searchById() {
        return
    }
    //    @Override
    //    ArrayList<Interface> searchByProject(int projectId){
    //
    //    }
    //    @Override
    //    ArrayList<Interface> searchByCompany(int companyId){
    //
    //    }
    //    @Override
    //    String add(@Param("interfaces") Interface interfaces){
    //
    //    }
    //    @Override
    //    String add(@Param("interfaces") LinkedList<Interface> interfaces){
    //
    //    }
    //    @Override
    //    String ed
    //    it(@Param("interfaces") Interface interfaces){}
    //    @Override
    //    String delete(@Param("interfaces") Interface interfaces){}
    //    @Override
    //    String delete(@Param("interfaces") LinkedList<Interface> interfaces){}
}
