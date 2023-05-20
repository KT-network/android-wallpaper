package com.kt.simpleWallPaper.ui;

import android.content.Intent;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Adapter.ListAdapter.LittleAdapter;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.My.base.TypeBase;
import com.kt.simpleWallPaper.api.NCallBack;

import java.util.List;

public class PicTypeActivity extends PicTypeBaseActivity{

    private Toolbar picTool;
    private SwipeRefreshLayout picRefresh;
    private RecyclerView picList;
    @Override
    protected void initView() {

        picTool = findViewById(R.id.picTool);
        picRefresh = findViewById(R.id.picRefresh);
        picList = findViewById(R.id.picList);

        picList.setLayoutManager(this.gridLayoutManager);


        picRefresh.setRefreshing(true);
        picTool.setNavigationOnClickListener(this.ToolOnClickListener);

        picRefresh.setOnRefreshListener(this.onRefreshListener);
        Tool.setStatusTextColor(true,this);

    }
    @Override
    public int initLayoutId() {
        return R.layout.activict_pic_info;
    }

    @Override
    public void setTitle() {
        super.setTitle();
        String title = "";
        if (Config.PAGETYPESIGN == 0){
            title = "360壁纸";
        }
        if (Config.PAGETYPESIGN == 1){
            title = "Unsplash壁纸";
        }
        if (Config.PAGETYPESIGN == 2){
            title = "Wallhaven壁纸";
        }

        picTool.setTitle(title);

    }

    @Override
    public int initLayoutManagerLine() {
        return 2;
    }

    private LittleAdapter sllTypeAdapter;
    private LittleAdapter unsplashTypeAdapter;
    private LittleAdapter wallhavenTypeAdapter;

    @Override
    protected void init360Data() {

        Config.myNetWorkBusiness.getData("sll", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.SllTypeData = response.getData();
                sllTypeAdapter = new LittleAdapter(getApplicationContext());
                sllTypeAdapter.setLittleRecyclerItemClickListener(onSllLittleRecyclerItemClickListener);
                picList.setAdapter(sllTypeAdapter);
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });

    }

    @Override
    protected void initUnsplashData() {
        Config.myNetWorkBusiness.getData("Unsplash", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.UnsplashTypeData = response.getData();
                unsplashTypeAdapter = new LittleAdapter(getApplicationContext());
                unsplashTypeAdapter.setLittleRecyclerItemClickListener(onUnsplashLittleRecyclerItemClickListener);
                picList.setAdapter(unsplashTypeAdapter);
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initWallhavenData() {
        Config.myNetWorkBusiness.getData("Wallhaven", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.WallhavenTypeData = response.getData();
                wallhavenTypeAdapter = new LittleAdapter(getApplicationContext());
                wallhavenTypeAdapter.setLittleRecyclerItemClickListener(onWallhavenLittleRecyclerItemClickListener);
                picList.setAdapter(wallhavenTypeAdapter);
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initUnsplashRefreshData() {

        Config.myNetWorkBusiness.getData("Unsplash", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.UnsplashTypeData = response.getData();
                if (unsplashTypeAdapter == null){
                    unsplashTypeAdapter = new LittleAdapter(getApplicationContext());
                    unsplashTypeAdapter.setLittleRecyclerItemClickListener(onUnsplashLittleRecyclerItemClickListener);
                    picList.setAdapter(unsplashTypeAdapter);
                }else unsplashTypeAdapter.refresh();

                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }


    @Override
    protected void initWallhavenRefreshData() {
        Config.myNetWorkBusiness.getData("Wallhaven", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.WallhavenTypeData = response.getData();
                if (wallhavenTypeAdapter == null){
                    wallhavenTypeAdapter = new LittleAdapter(getApplicationContext());
                    wallhavenTypeAdapter.setLittleRecyclerItemClickListener(onWallhavenLittleRecyclerItemClickListener);
                    picList.setAdapter(wallhavenTypeAdapter);
                }else wallhavenTypeAdapter.refresh();
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void init360RefreshData() {
        Config.myNetWorkBusiness.getData("sll", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.SllTypeData = response.getData();

                if (sllTypeAdapter == null){
                    sllTypeAdapter = new LittleAdapter(getApplicationContext());
                    sllTypeAdapter.setLittleRecyclerItemClickListener(onSllLittleRecyclerItemClickListener);
                    picList.setAdapter(sllTypeAdapter);
                }else sllTypeAdapter.refresh();
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    // sll监听
    private LittleAdapter.OnLittleRecyclerItemClickListener onSllLittleRecyclerItemClickListener = new LittleAdapter.OnLittleRecyclerItemClickListener() {
        @Override
        public void onLittleRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),PicInfoActivity.class);
            Config.PAGEINFOSIGN = 1;
            Config.SllTypeItem = Config.SllTypeData.get(position);
            startActivity(intent);
        }
    };


    // Wallhaven监听
    private LittleAdapter.OnLittleRecyclerItemClickListener onWallhavenLittleRecyclerItemClickListener = new LittleAdapter.OnLittleRecyclerItemClickListener() {
        @Override
        public void onLittleRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),PicInfoActivity.class);
            Config.PAGEINFOSIGN = 4;
            Config.WallhavenTypeItem = Config.WallhavenTypeData.get(position);
            startActivity(intent);
        }
    };


    // Unsplash监听
    private LittleAdapter.OnLittleRecyclerItemClickListener onUnsplashLittleRecyclerItemClickListener = new LittleAdapter.OnLittleRecyclerItemClickListener() {
        @Override
        public void onLittleRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),PicInfoActivity.class);
            Config.PAGEINFOSIGN = 5;
            Config.UnsplashTypeItem = Config.UnsplashTypeData.get(position);
            startActivity(intent);
        }
    };

}
