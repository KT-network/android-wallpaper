package com.kt.simpleWallPaper.api.My.base;

import java.io.Serializable;

public class UpDateBase implements Serializable {
    private int id;
    private String versionsNum;
    private String title;
    private String text;
    private float size;
    private String urlType;
    private String url;
    private String must;
    private String creationTime;


    public int getId() {
        return id;
    }

    public String getVersionsNum() {
        return versionsNum;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public float getSize() {
        return size;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getUrl() {
        return url;
    }

    public String getMust() {
        return must;
    }

    public String getCreationTime() {
        return creationTime;
    }
}