package com.kt.simpleWallPaper.api.Sll;

import com.kt.simpleWallPaper.api.Sll.base.SllBase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SllHttpInterface {

    @GET("index.php?c=WallPaper&a=getAppsByCategory")
    Call<SllBase> getSslInfoData(@Query("cid") int cid, @Query("start") int start, @Query("count") int count,@Query("from") String from);
}
