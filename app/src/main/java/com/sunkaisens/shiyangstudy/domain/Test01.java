package com.sunkaisens.shiyangstudy.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author:shiyang
 * @date:2019-08-30
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class Test01 implements Parcelable {
    private String aa;
    private String bb;
    private int cc;

    public String getAa() {
        return aa;
    }

    public String getBb() {
        return bb;
    }

    public int getCc() {
        return cc;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    protected Test01(Parcel in) {
        aa = in.readString();
        bb = in.readString();
        cc = in.readInt();
    }

    public Test01(){}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aa);
        dest.writeString(bb);
        dest.writeInt(cc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Test01> CREATOR = new Creator<Test01>() {
        @Override
        public Test01 createFromParcel(Parcel in) {
            return new Test01(in);
        }

        @Override
        public Test01[] newArray(int size) {
            return new Test01[size];
        }
    };
}
