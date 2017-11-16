package com.www.platform.service.interfaces;

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
    Interface[] searchByList(@Param("ids") Integer[] ids);

    Interface searchById(@Param("id") int id);

    Interface[] searchAll();

    Interface[] searchByProject(@Param("projectId") int projectId);

    //    ArrayList<Interface> searchByCompany(@Param("companyId") int companyId);
    Boolean add(@Param("interfaces") Interface interfaces);

    //    String add(@Param("interfaces") LinkedList<Interface> interfaces);
    Boolean update(@Param("interfaces") Interface interfaces);

    //    String delete(@Param("interfaces") Interface interfaces);
    Boolean delete(@Param("interfaces") int[] ids);

}
