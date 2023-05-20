package com.kt.simpleWallPaper.api.One.base;

import java.io.Serializable;
import java.util.List;

public class OneDataBase implements Serializable {

    private List<OneContentBase> content_list;

    public List<OneContentBase> getContent_list() {
        return content_list;
    }
}
