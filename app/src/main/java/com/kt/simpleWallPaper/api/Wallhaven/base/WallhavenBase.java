package com.kt.simpleWallPaper.api.Wallhaven.base;

import com.kt.simpleWallPaper.api.bing.base.BingDataBase;

import java.io.Serializable;
import java.util.List;

public class WallhavenBase implements Serializable {


    private int code;
    private List<WallhavenDataBase> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public List<WallhavenDataBase> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
