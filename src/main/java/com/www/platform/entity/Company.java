package com.www.platform.entity;

import java.util.Date;
import java.util.List;

public class Company {
    private Integer comid;

    private Integer pid;

    private String comaddr;

    private String comemail;

    private String comname;

    private String comcontact;

    private String comcontactname;

    private Date createtime;

    private Integer typeid;

    private Date modtime;

    private Integer statusid;

    private Integer comtype;

    private List<Log> logs;

    private List<Item> items;

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getComaddr() {
        return comaddr;
    }

    public void setComaddr(String comaddr) {
        this.comaddr = comaddr == null ? null : comaddr.trim();
    }

    public String getComemail() {
        return comemail;
    }

    public void setComemail(String comemail) {
        this.comemail = comemail == null ? null : comemail.trim();
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }

    public String getComcontact() {
        return comcontact;
    }

    public void setComcontact(String comcontact) {
        this.comcontact = comcontact == null ? null : comcontact.trim();
    }

    public String getComcontactname() {
        return comcontactname;
    }

    public void setComcontactname(String comcontactname) {
        this.comcontactname = comcontactname == null ? null : comcontactname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Integer getComtype() {
        return comtype;
    }

    public void setComtype(Integer comtype) {
        this.comtype = comtype;
    }
}