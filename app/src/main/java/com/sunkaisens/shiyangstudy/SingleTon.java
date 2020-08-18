package com.sunkaisens.shiyangstudy;

import java.io.Serializable;

/**
 * @author:shiyang
 * @date:2019-12-23
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class SingleTon implements Serializable {

    private SingleTon() {
        if (null != singleTon) {
            //防止反射的时候生成对象
            throw new RuntimeException("singleTon is not null...");
        }
    }

    private static SingleTon singleTon;

    public static SingleTon getInstance() {
        if (null == singleTon) {
            synchronized (SingleTon.class) {
                if (null == singleTon) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }


    private Object readResolve() {
        //防止反序列化的时候重新生成对象
        return singleTon;
    }

}
