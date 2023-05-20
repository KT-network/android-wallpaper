package com.kt.simpleWallPaper.api.My.base;

import java.io.Serializable;


public class ClickBase implements Serializable {
    private int id;
    private String tag;
    private Integer click;
    private String lastTime;
    private String creationTime;

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public Integer getClick() {
        return click;
    }

    public String getTag() {
        return tag;
    }

    public int getId() {
        return id;
    }
}
