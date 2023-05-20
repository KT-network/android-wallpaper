package com.kt.simpleWallPaper.api.Sll.base;

import java.io.Serializable;

public class SllDataBase implements Serializable {

    private String id;
    private String class_id;
    private String resolution;
    /*private String url;
    private String url_thumb;
    private String url_mid;*/
    private String tag;
    private String utag;
    private String img_1600_900;
    private String img_1024_768;

    /*public String getUrl_thumb() {
        return url_thumb;
    }*/

    public String getResolution() {
        return resolution;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getId() {
        return id;
    }

    public String getImg_1600_900() {
        return img_1600_900;
    }

    public String getUtag() {
        return utag;
    }

    public String getTag() {
        return tag;
    }

    /*public String getUrl_mid() {
        return url_mid;
    }

    public String getUrl() {
        return url;
    }*/

    public String getImg_1024_768() {
        return img_1024_768;
    }
}
