package com.sunkaisens.shiyangstudy;

import android.os.Parcel;
import android.os.Parcelable;

import com.sunkaisens.shiyangstudy.domain.Test01;
import com.sunkaisens.shiyangstudy.domain.Test02;

import java.io.Serializable;

/**
 * @author:shiyang
 * @date:2019-08-30
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class DemoTest implements Parcelable {
    private String name;
    private String hobby;
    private int age;
    private Test01 test01;
    private Test02 test02;


    protected DemoTest(Parcel in) {
        name = in.readString();
        hobby = in.readString();
        age = in.readInt();
        test01 = in.readParcelable(Thread.currentThread().getContextClassLoader());
        test02 = (Test02) in.readSerializable();
    }

    public static final Creator<DemoTest> CREATOR = new Creator<DemoTest>() {
        @Override
        public DemoTest createFromParcel(Parcel in) {
            return new DemoTest(in);
        }

        @Override
        public DemoTest[] newArray(int size) {
            return new DemoTest[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHobby() {
        return hobby;
    }

    public Test01 getTest01() {
        return test01;
    }

    public Test02 getTest02() {
        return test02;
    }

    private DemoTest(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.test01 = builder.test01;
        this.test02 = builder.test02;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(hobby);
        parcel.writeInt(age);
        parcel.writeParcelable(test01, 0);
        parcel.writeSerializable(test02);
    }


    public static class Builder {
        private String name;
        private int age;
        private Test01 test01;
        private Test02 test02;

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Age(int age) {
            this.age = age;
            return this;
        }

        public Builder Test01(Test01 test01) {
            this.test01 = test01;
            return this;
        }

        public Builder Test02(Test02 test02) {
            this.test02 = test02;
            return this;
        }

        public DemoTest builder() {
            return new DemoTest(this);
        }
    }


}
