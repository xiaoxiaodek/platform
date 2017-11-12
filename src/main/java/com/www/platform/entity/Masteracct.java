package com.www.platform.entity;

import java.util.Date;

public class Masteracct {
    private Integer maid;

    private String maname;

    private Long maamt;

    private Date createtime;

    private Integer comid;

    public Integer getMaid() {
        return maid;
    }

    public void setMaid(Integer maid) {
        this.maid = maid;
    }

    public String getManame() {
        return maname;
    }

    public void setManame(String maname) {
        this.maname = maname == null ? null : maname.trim();
    }

    public Long getMaamt() {
        return maamt;
    }

    public void setMaamt(Long maamt) {
        this.maamt = maamt;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }
}