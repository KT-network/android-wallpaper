package com.kt.simpleWallPaper.api.My.base;

import java.io.Serializable;

public class PictureBase implements Serializable {

    private int id;
    private String url;
    private String md5Name;
    private String md5ZipName;
    private String uploadUser;
    private String auditUser;
    private String creationTime;


    public String getCreationTime() {
        return creationTime;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public String getMd5ZipName() {
        return md5ZipName;
    }

    public String getMd5Name() {
        return md5Name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }
}
