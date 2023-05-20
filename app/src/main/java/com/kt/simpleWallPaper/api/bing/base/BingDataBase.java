package com.kt.simpleWallPaper.api.bing.base;

import java.io.Serializable;

public class BingDataBase implements Serializable {

    private String _id;
    private String enddate;
    private String fullSrc;
    private String urlbase;

    public String getUrlbase() {
        return urlbase;
    }

    public String getFullSrc() {
        return fullSrc;
    }

    public String getEnddate() {
        return enddate;
    }

    public String get_id() {
        return _id;
    }
}
