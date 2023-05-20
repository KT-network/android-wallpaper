package com.kt.simpleWallPaper.api.Unsplash.base;

import java.io.Serializable;

public class UnsplashDataBase implements Serializable {

    private int h;
    private String id;
    private String raw;
    private String thumb;
    private int w;

    public int getW() {
        return w;
    }

    public String getThumb() {
        return thumb;
    }

    public String getRaw() {
        return raw;
    }

    public String getId() {
        return id;
    }

    public int getH() {
        return h;
    }
}
