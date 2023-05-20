package com.kt.simpleWallPaper.api.My.base;

import java.io.Serializable;

public class BaseResponseEntity<T> implements Serializable {

    private int code;
    private String msg;
    private T data;
    private String beFrom;
    private String time;

    public String getTime() {
        return time;
    }

    public String getBeFrom() {
        return beFrom;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
