package com.www.platform.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Interface {
    private Integer idfid;

    private String infname;

    private Integer inftype;

    private Boolean exinf;

    private Long basprice;

    private BigDecimal dicount;

    private Date createtime;

    private Date modtime;

    public Integer getIdfid() {
        return idfid;
    }

    public void setIdfid(Integer idfid) {
        this.idfid = idfid;
    }

    public String getInfname() {
        return infname;
    }

    public void setInfname(String infname) {
        this.infname = infname == null ? null : infname.trim();
    }

    public Integer getInftype() {
        return inftype;
    }

    public void setInftype(Integer inftype) {
        this.inftype = inftype;
    }

    public Boolean getExinf() {
        return exinf;
    }

    public void setExinf(Boolean exinf) {
        this.exinf = exinf;
    }

    public Long getBasprice() {
        return basprice;
    }

    public void setBasprice(Long basprice) {
        this.basprice = basprice;
    }

    public BigDecimal getDicount() {
        return dicount;
    }

    public void setDicount(BigDecimal dicount) {
        this.dicount = dicount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
}