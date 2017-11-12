package com.www.platform.entity;

import java.util.Date;

public class File {
    private Integer fid;

    private Integer uid;

    private Integer pid;

    private Integer cid;

    private Date createtime;

    private String flocal;

    private String fname;

    private Date modtime;

    private String fsummary;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFlocal() {
        return flocal;
    }

    public void setFlocal(String flocal) {
        this.flocal = flocal == null ? null : flocal.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public String getFsummary() {
        return fsummary;
    }

    public void setFsummary(String fsummary) {
        this.fsummary = fsummary == null ? null : fsummary.trim();
    }
}