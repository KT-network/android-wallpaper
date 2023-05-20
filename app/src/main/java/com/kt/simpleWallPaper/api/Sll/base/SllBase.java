package com.kt.simpleWallPaper.api.Sll.base;

import java.io.Serializable;
import java.util.List;

public class SllBase implements Serializable {

    private String errno;
    private String errmsg;
    private String consume;
    private String total;
    private List<SllDataBase> data;

    public List<SllDataBase> getData() {
        return data;
    }

    public String getTotal() {
        return total;
    }

    public String getConsume() {
        return consume;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public String getErrno() {
        return errno;
    }
}
