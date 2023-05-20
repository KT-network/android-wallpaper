package com.kt.simpleWallPaper.api.One.base;

import java.io.Serializable;

public class OneBase implements Serializable {

    private int res;
    private OneDataBase data;


    public OneDataBase getData() {
        return data;
    }

    public int getRes() {
        return res;
    }
}
