package com.www.platform.entity;

import java.util.Date;

public class Interface {
    private Integer idfid;

    private String infname;

    private Integer inftype;

    private Byte exinf;

    private Long basprice;

    private Long dicount;

    private Date createtime;

    private Date interfacecol;

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

    public Byte getExinf() {
        return exinf;
    }

    public void setExinf(Byte exinf) {
        this.exinf = exinf;
    }

    public Long getBasprice() {
        return basprice;
    }

    public void setBasprice(Long basprice) {
        this.basprice = basprice;
    }

    public Long getDicount() {
        return dicount;
    }

    public void setDicount(Long dicount) {
        this.dicount = dicount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getInterfacecol() {
        return interfacecol;
    }

    public void setInterfacecol(Date interfacecol) {
        this.interfacecol = interfacecol;
    }
}