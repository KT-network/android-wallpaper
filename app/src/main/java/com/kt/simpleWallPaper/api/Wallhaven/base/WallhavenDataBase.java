package com.kt.simpleWallPaper.api.Wallhaven.base;

import java.io.Serializable;

public class WallhavenDataBase implements Serializable {

    private String id;
    private String path;
    private thumbsBase thumbs;

    public thumbsBase getThumbs() {
        return thumbs;
    }

    public String getPath() {
        return path;
    }

    public String getId() {
        return id;
    }

    public class thumbsBase{

        private String small;


        public String getSmall() {
            return small;
        }
    }

}
