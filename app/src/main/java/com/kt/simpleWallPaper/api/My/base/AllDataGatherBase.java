package com.kt.simpleWallPaper.api.My.base;

import java.io.Serializable;
import java.util.List;

public class AllDataGatherBase implements Serializable {

    private UpDateBase update;
    private List<TypeBase> sanliuling;
    private List<TypeBase> Unsplash;
    private List<TypeBase> Wallhaven;
    private List<TypeBase> Phone;

    public List<TypeBase> getPhone() {
        return Phone;
    }

    public List<TypeBase> getWallhaven() {
        return Wallhaven;
    }

    public List<TypeBase> getUnsplash() {
        return Unsplash;
    }

    public List<TypeBase> getSanliuling() {
        return sanliuling;
    }

    public UpDateBase getUpdate() {
        return update;
    }
}
