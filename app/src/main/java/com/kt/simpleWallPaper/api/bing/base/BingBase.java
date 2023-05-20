package com.kt.simpleWallPaper.api.bing.base;


import java.io.Serializable;
import java.util.List;

public class BingBase implements Serializable {

    private int code;
    private List<BingDataBase> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public List<BingDataBase> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
