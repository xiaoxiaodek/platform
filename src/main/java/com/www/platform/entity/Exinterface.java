package com.www.platform.entity;

import java.util.Date;

public class Exinterface {
    private Integer exinfid;

    private String exinfname;

    private Integer exinftype;

    private Long payprice;

    private Long discount;

    private Date exinterfacecol;

    private Date modtime;

    public Integer getExinfid() {
        return exinfid;
    }

    public void setExinfid(Integer exinfid) {
        this.exinfid = exinfid;
    }

    public String getExinfname() {
        return exinfname;
    }

    public void setExinfname(String exinfname) {
        this.exinfname = exinfname == null ? null : exinfname.trim();
    }

    public Integer getExinftype() {
        return exinftype;
    }

    public void setExinftype(Integer exinftype) {
        this.exinftype = exinftype;
    }

    public Long getPayprice() {
        return payprice;
    }

    public void setPayprice(Long payprice) {
        this.payprice = payprice;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Date getExinterfacecol() {
        return exinterfacecol;
    }

    public void setExinterfacecol(Date exinterfacecol) {
        this.exinterfacecol = exinterfacecol;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
}