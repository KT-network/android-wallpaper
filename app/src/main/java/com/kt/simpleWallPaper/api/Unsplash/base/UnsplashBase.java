package com.kt.simpleWallPaper.api.Unsplash.base;


import java.io.Serializable;
import java.util.List;

public class UnsplashBase implements Serializable {

    private int code;
    private List<UnsplashDataBase> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public List<UnsplashDataBase> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

}
