package com.www.platform.controller.interfaces;

import com.www.platform.entity.Interface;
import com.www.platform.service.interfaces.InterfacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by upsmart on 17-11-12.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:28
 */
@Controller @RequestMapping("interfaces") public class InterFacesController {

    @Autowired private InterfacesService interfacesService;

    @RequestMapping(value = "/", method = RequestMethod.GET) @ResponseBody public String search() {
        return "chenggon";
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET) @ResponseBody
    //    public ArrayList<Interface>serchByProject(List ids){
    public String serchByProject() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        //注意不能有重复元素
        list.add(43);
        ArrayList<Interface> result = interfacesService.searchByList(list);
        return result.toString();
    }
}
