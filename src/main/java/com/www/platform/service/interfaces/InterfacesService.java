package com.www.platform.service.interfaces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.www.platform.entity.Interface;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;



/**
 * Created by upsmart on 17-11-12.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:30
 */
@Service public interface InterfacesService {
    //searchByProject
    ArrayList<Interface> searchByList(@Param("ids") List ids);

    Integer searchById(@Param("id") int id);

    ArrayList<Interface> searchByProject(@Param("projectId") int projectId);

    //    ArrayList<Interface> searchByCompany(@Param("companyId") int companyId);
    //    String add(@Param("interfaces") Interface interfaces);
    //    String add(@Param("interfaces") LinkedList<Interface> interfaces);
    //    String edit(@Param("interfaces") Interface interfaces);
    //    String delete(@Param("interfaces") Interface interfaces);
    //    String delete(@Param("interfaces") LinkedList<Interface> interfaces);

}
