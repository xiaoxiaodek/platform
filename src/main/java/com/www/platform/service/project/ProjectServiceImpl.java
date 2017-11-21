
package com.www.platform.service.project;

import com.www.platform.dao.ProjectMapper;
import com.www.platform.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author zjl
 * @version 0.0
 */
@Service
public class ProjectServiceImpl implements ProjectService {
@Autowired private ProjectMapper projectMapper;

    @Override public String addProject(Map<String, Object> map) {
        String result=null;
        Project project=new Project();
        project.setPname(map.get("pname").toString());
        project.setAuditstatid(Integer.parseInt(map.get("auditstatid").toString()));
        project.setAppreason(map.get("appreason").toString());
        project.setApptype(Integer.parseInt(map.get("suppid").toString()));
        project.setCid(Integer.parseInt(map.get("cid").toString()));
        project.setUid(Integer.parseInt(map.get("uid").toString()));
        project.setPsid(Integer.parseInt(map.get("psid").toString()));
        project.setAid(Integer.parseInt(map.get("aid").toString()));
        project.setSuppid(Integer.parseInt(map.get("suppid").toString()));
        project.setModtime(new Date());
        project.setCreatetime(new Date());
        String apptime=map.get("apptime").toString();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = sdf.parse(apptime);
            project.setApptime(date);
            result="添加成功";
        }catch ( ParseException e){
            e.printStackTrace();
            result="添加失败";
        }
        this.projectMapper.insertSelective(project);
        return result;
    }

    @Override public String deleteProjectByPid(Map<String, Object> map) {
        String result=null;
        Project project=new Project();
        int pid=Integer.parseInt(map.get("pid").toString());
        try {
            this.projectMapper.deleteByPrimaryKey(pid);
            result="删除成功";
        }catch(Exception e){
            result="删除失败";
            e.printStackTrace();
            return  result;
        }

        return result;
    }

    @Override public String updateProject(Map<String, Object> map) {
        String result=null;
        Project project=new Project();
        int pid=Integer.parseInt(map.get("pid").toString());
        project=this.projectMapper.selectByPrimaryKey(pid);

        project.setPname(map.get("pname").toString());
        project.setAuditstatid(Integer.parseInt(map.get("auditstatid").toString()));
        project.setAppreason(map.get("appreason").toString());
        project.setApptype(Integer.parseInt(map.get("suppid").toString()));
        project.setCid(Integer.parseInt(map.get("cid").toString()));
        project.setUid(Integer.parseInt(map.get("uid").toString()));
        project.setPsid(Integer.parseInt(map.get("psid").toString()));
        project.setAid(Integer.parseInt(map.get("aid").toString()));
        project.setSuppid(Integer.parseInt(map.get("suppid").toString()));
        project.setModtime(new Date());
//        project.setCreatetime(new Date());
        String apptime=map.get("apptime").toString();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = sdf.parse(apptime);
            project.setApptime(date);

        }catch ( ParseException e){
            e.printStackTrace();
            result="修改失败";
            return result;
        }
        try {
            this.projectMapper.updateByPrimaryKeySelective(project);
            result="修改成功";
        }catch (Exception e){
            result="修改失败";
            e.printStackTrace();
            return result;
        }

        return result;
    }

    @Override public List<Project> selectProjectBySuppid(Map<String,Object> map) {
        String result=null;
        Project project=new Project();
        List<Project> pros=new ArrayList<Project>();
        int suppid=Integer.parseInt(map.get("suppid").toString());
        System.out.println("suppid"+suppid);
        try {
            pros = this.projectMapper.selectProjectBySuppid(suppid);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("pros =  "+pros.size());

        if(pros.size()==0) {
//            for(int i=0;i<size;i++){
////            for (Project pro : pros) {
//                project.setPname(pros.get(i).getPname());
//                project.setAuditstatid(pros.get(i).getAuditstatid());
//                project.setAppreason(pros.get(i).getAppreason());
//                project.setApptype(pros.get(i).getApptype());
//                project.setCid(pros.get(i).getCid());
//                project.setUid(pros.get(i).getUid());
//                project.setPsid(pros.get(i).getPsid());
//                project.setAid(pros.get(i).getAid());
//                project.setSuppid(pros.get(i).getSuppid());
//                project.setModtime(pros.get(i).getModtime());
//                project.setApptime(pros.get(i).getApptime());
//
//
//                pros.add(project);
//                System.out.println("pro " + pros.get(i).getPid());
//            }
            return null;
        }
        return pros;
    }

    @Override public List<Project> selectProjectByAuditstatid(Map<String, Object> map) {

        String result=null;
        Project project=new Project();
        List<Project> pros=new ArrayList<Project>();
        int auditstatid=Integer.parseInt(map.get("auditstatid").toString());
        System.out.println("auditstatid"+auditstatid);
        try {
            pros = this.projectMapper.selectProjectByAuditstatid(auditstatid);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("pros =  "+pros.size());
        if(pros.size()==0) {
            return null;
        }
        return pros;
    }
}
