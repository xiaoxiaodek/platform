package com.www.platform.entity;

import java.util.Date;

public class Contract {
    private Integer cid;

    private Double camt;

    private Date cstarttime;

    private Date cendtime;

    private String cname;

    private String appreason;

    private Integer auditstatid;

    private Integer suppid;

    private Date signdate;

    private Integer cstat;

    private Integer pid;

    private Integer appusrid;

    private Date modtime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Double getCamt() {
        return camt;
    }

    public void setCamt(Double camt) {
        this.camt = camt;
    }

    public Date getCstarttime() {
        return cstarttime;
    }

    public void setCstarttime(Date cstarttime) {
        this.cstarttime = cstarttime;
    }

    public Date getCendtime() {
        return cendtime;
    }

    public void setCendtime(Date cendtime) {
        this.cendtime = cendtime;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getAppreason() {
        return appreason;
    }

    public void setAppreason(String appreason) {
        this.appreason = appreason == null ? null : appreason.trim();
    }

    public Integer getAuditstatid() {
        return auditstatid;
    }

    public void setAuditstatid(Integer auditstatid) {
        this.auditstatid = auditstatid;
    }

    public Integer getSuppid() {
        return suppid;
    }

    public void setSuppid(Integer suppid) {
        this.suppid = suppid;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Integer getCstat() {
        return cstat;
    }

    public void setCstat(Integer cstat) {
        this.cstat = cstat;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAppusrid() {
        return appusrid;
    }

    public void setAppusrid(Integer appusrid) {
        this.appusrid = appusrid;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
}