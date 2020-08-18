package com.sunkaisens.shiyangstudy.domain;

import android.os.Parcel;

import java.io.Serializable;

/**
 * @author:shiyang
 * @date:2019-08-30
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class Test02 implements Serializable {
    private static final long serialVersionUID = 871189434384339534L;
    private long dd;
    private String ee;
    private int ff;


    public void setDd(long dd) {
        this.dd = dd;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }

    public void setFf(int ff) {
        this.ff = ff;
    }

    public long getDd() {
        return dd;
    }

    public String getEe() {
        return ee;
    }

    public int getFf() {
        return ff;
    }
}
