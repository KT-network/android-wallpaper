package com.kt.simpleWallPaper.api.My.base;

import java.io.Serializable;

public class TypeBase implements Serializable {

    private int id;
    private String nameType;
    private String name;
    private String tag;
    private String coverPicUrl;
    private String lastTime;
    private String creationTime;

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getNameType() {
        return nameType;
    }

    public int getId() {
        return id;
    }
}
