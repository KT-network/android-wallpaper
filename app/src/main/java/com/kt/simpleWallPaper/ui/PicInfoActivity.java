package com.kt.simpleWallPaper.ui;

import android.content.Intent;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Adapter.ListAdapter.WinAdapter;
import com.kt.simpleWallPaper.Adapter.ListAdapter.PhoneAdapter;
import com.kt.simpleWallPaper.Adapter.ListAdapter.SingleAdapter;
import com.kt.simpleWallPaper.Dialog.LongAlertDialog;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.One.base.OneBase;
import com.kt.simpleWallPaper.api.Phone.base.BaseWallInfo;
import com.kt.simpleWallPaper.api.Sll.base.SllBase;

import com.kt.simpleWallPaper.api.Unsplash.base.UnsplashBase;
import com.kt.simpleWallPaper.api.Wallhaven.base.WallhavenBase;
import com.kt.simpleWallPaper.api.bing.base.BingBase;

public class PicInfoActivity extends PicInfoBaseActivity1{

    @Override
    public int initLayoutId() {
        return R.layout.activict_pic_info;
    }

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
        picList.addOnScrollListener(this.onScrollListener);
        picRefresh.setOnRefreshListener(this.onRefreshListener);
        Tool.setStatusTextColor(true,this);

    }

    @Override
    public void setTitle() {
        super.setTitle();
        String title = "";

        // phone
        if (Config.PAGEINFOSIGN == 0){
            title = Config.PhoneTypeItem.getName();
        }

        // 360
        if (Config.PAGEINFOSIGN == 1){
            title = Config.SllTypeItem.getName();
        }

        // one
        if (Config.PAGEINFOSIGN == 2){
            title = "ONE•一个";
        }

        // Bing
        if (Config.PAGEINFOSIGN == 3){
            title = "Bing 美图";
        }

        // Wallhaven
        if (Config.PAGEINFOSIGN == 4){
            title = Config.WallhavenTypeItem.getName();
        }

        // Unsplash
        if (Config.PAGEINFOSIGN == 5){
            title = Config.UnsplashTypeItem.getName();
        }
        picTool.setTitle(title);
    }

    private PhoneAdapter phoneAdapter;
    private WinAdapter sllAdapter;
    private WinAdapter wallhavenAdapter;
    private WinAdapter unsplashAdapter;
    private SingleAdapter oneAdapter;
    private SingleAdapter bingAdapter;

    @Override
    protected void onRestart() {
        super.onRestart();
        switch (Config.PAGEINFOSIGN){
            case 0:
                phoneAdapter.addPhone(phoneAdapter.getItemCount(),null);
                break;
            case 1:
                sllAdapter.addSll(sllAdapter.getItemCount(),null);
                break;
            case 2:
                oneAdapter.addOne(oneAdapter.getItemCount(),null);
                break;
            case 3:
                bingAdapter.addBing(bingAdapter.getItemCount(),null);
                break;
            case 4:
                wallhavenAdapter.addWallhaven(wallhavenAdapter.getItemCount(),null);
                break;
            case 5:
                unsplashAdapter.addUnsplash(unsplashAdapter.getItemCount(),null);
                break;
        }
        picList.smoothScrollToPosition(Config.numPicInfo);

    }

    @Override
    public int initLayoutManagerLine() {

        switch (Config.PAGEINFOSIGN){
            case 0:
                return 3;
            case 1:
            case 4:
            case 5:
                return 2;
            case 2:
            case 3:
                return 1;
        }
        return 1;
    }


    @Override
    protected void init360Data() {
        Config.sllNetWorkBusiness.getSslInfoData(Integer.parseInt(Config.SllTypeItem.getTag()), 0, Config.SllPage = 15, "360chrome", new NCallBack<SllBase>(getApplicationContext()) {
            @Override
            protected void onResponse(SllBase response) {
                Config.SllPicInfo = response.getData();
                sllAdapter = new WinAdapter(getApplicationContext());
                sllAdapter.setWinPicRecyclerItemClickListener(onSllPicDataRecyclerItemClickListener);
                sllAdapter.setWinPicRecyclerItemLongClickListener(onSllPicDataRecyclerItemLongClickListener);
                picList.setAdapter(sllAdapter);
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initPhoneData() {
        Config.phoneNetWorkBusiness.getInfoPhoneData(Config.PhoneTypeItem.getId(), Config.PhonePage = 0, new NCallBack<BaseWallInfo>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseWallInfo response) {
                Config.PhonePicInfo = response.getres().getVertical();
                phoneAdapter = new PhoneAdapter(getApplicationContext());
                phoneAdapter.setRecyclerItemClickListener(onPhonePicDataRecyclerItemClickListener);
                phoneAdapter.setRecyclerItemLongClickListener(onPhonePicDataRecyclerItemLongClickListener);
                picList.setAdapter(phoneAdapter);
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

        Config.unsplashNetWorkBusiness.getUnsplashData(Config.UnsplashPage = 1, Config.UnsplashTypeItem.getTag(), new NCallBack<UnsplashBase>(getApplicationContext()) {
            @Override
            protected void onResponse(UnsplashBase response) {

                Config.UnsplashPicInfo = response.getData();
                unsplashAdapter = new WinAdapter(getApplicationContext());
                unsplashAdapter.setWinPicRecyclerItemClickListener(onUnsplashPicDataRecyclerItemClickListener);
                unsplashAdapter.setWinPicRecyclerItemLongClickListener(onUnsplashPicDataRecyclerItemLongClickListener);
                picList.setAdapter(unsplashAdapter);
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

        Config.wallhavenNetWorkBusiness.getWallhavenData(Config.WallhavenPage = 1, Config.WallhavenTypeItem.getTag(), new NCallBack<WallhavenBase>(getApplicationContext()) {
            @Override
            protected void onResponse(WallhavenBase response) {

                Config.WallhavenPicInfo = response.getData();
                wallhavenAdapter = new WinAdapter(getApplicationContext());
                wallhavenAdapter.setWinPicRecyclerItemClickListener(onWallhavenPicDataRecyclerItemClickListener);
                wallhavenAdapter.setWinPicRecyclerItemLongClickListener(onWallhavenPicDataRecyclerItemLongClickListener);
                picList.setAdapter(wallhavenAdapter);
                picRefresh.setRefreshing(false);

            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initOneData() {
        Config.date = System.currentTimeMillis();

        if (!Tool.getDate(Config.date-25200000).equals(Tool.getDate(Config.date)) ) Config.date-=86400000;

        Config.oneNetWorkBusiness.getOneData(Tool.getDate(Config.date), new NCallBack<OneBase>(getApplicationContext()) {
            @Override
            protected void onResponse(OneBase response) {

                Config.OnePicPicInfo = response.getData().getContent_list();
                oneAdapter = new SingleAdapter(getApplicationContext());
                oneAdapter.setSinglePicRecyclerItemClickListener(onOnePicDataRecyclerItemClickListener);
                oneAdapter.setSinglePicRecyclerItemLongClickListener(onOnePicDataRecyclerItemLongClickListener);
                picList.setAdapter(oneAdapter);
                picRefresh.setRefreshing(false);

            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initBingData() {
        Config.bingNetWorkBusiness.getBingData(Config.BingPage = 0, new NCallBack<BingBase>(getApplicationContext()) {
            @Override
            protected void onResponse(BingBase response) {
                Config.BingPicInfo = response.getData();
                bingAdapter = new SingleAdapter(getApplicationContext());
                bingAdapter.setSinglePicRecyclerItemClickListener(onBingPicDataRecyclerItemClickListener);
                bingAdapter.setSinglePicRecyclerItemLongClickListener(onBingPicDataRecyclerItemLongClickListener);
                picList.setAdapter(bingAdapter);
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initSimpleData() {

    }



    @Override
    protected void init360RefreshData() {
        Config.sllNetWorkBusiness.getSslInfoData(Integer.valueOf(Config.SllTypeItem.getTag()), 0, Config.SllPage = 15,"360chrome", new NCallBack<SllBase>(getApplicationContext()) {
            @Override
            protected void onResponse(SllBase response) {
                Config.SllPicInfo = response.getData();
                if (sllAdapter ==null){
                    sllAdapter = new WinAdapter(getApplicationContext());
                    sllAdapter.setWinPicRecyclerItemClickListener(onSllPicDataRecyclerItemClickListener);
                    sllAdapter.setWinPicRecyclerItemLongClickListener(onSllPicDataRecyclerItemLongClickListener);
                    picList.setAdapter(sllAdapter);
                }else sllAdapter.refresh();
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }

        });
    }

    @Override
    protected void initOneRefreshData() {
        Config.date = System.currentTimeMillis();
        if (!Tool.getDate(Config.date-25200000).equals(Tool.getDate(Config.date)) ) Config.date-=86400000;
        Config.oneNetWorkBusiness.getOneData(Tool.getDate(Config.date), new NCallBack<OneBase>(getApplicationContext()) {
            @Override
            protected void onResponse(OneBase response) {
                Config.OnePicPicInfo = response.getData().getContent_list();
                if (oneAdapter == null){
                    oneAdapter = new SingleAdapter(getApplicationContext());
                    oneAdapter.setSinglePicRecyclerItemClickListener(onOnePicDataRecyclerItemClickListener);
                    oneAdapter.setSinglePicRecyclerItemLongClickListener(onOnePicDataRecyclerItemLongClickListener);
                    picList.setAdapter(oneAdapter);
                }else oneAdapter.refresh();
                picRefresh.setRefreshing(false);

            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });

    }

    @Override
    protected void initBingRefreshData() {

        Config.bingNetWorkBusiness.getBingData(Config.BingPage = 0, new NCallBack<BingBase>(getApplicationContext()) {
            @Override
            protected void onResponse(BingBase response) {
                Config.BingPicInfo = response.getData();

                if (bingAdapter == null){
                    bingAdapter = new SingleAdapter(getApplicationContext());
                    bingAdapter.setSinglePicRecyclerItemClickListener(onBingPicDataRecyclerItemClickListener);
                    bingAdapter.setSinglePicRecyclerItemLongClickListener(onBingPicDataRecyclerItemLongClickListener);
                    picList.setAdapter(bingAdapter);
                }else bingAdapter.refresh();
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
        Config.wallhavenNetWorkBusiness.getWallhavenData(Config.WallhavenPage = 1, Config.WallhavenTypeItem.getTag(), new NCallBack<WallhavenBase>(getApplicationContext()) {
            @Override
            protected void onResponse(WallhavenBase response) {
                Config.WallhavenPicInfo = response.getData();
                if (wallhavenAdapter == null){
                    wallhavenAdapter = new WinAdapter(getApplicationContext());
                    wallhavenAdapter.setWinPicRecyclerItemClickListener(onWallhavenPicDataRecyclerItemClickListener);
                    wallhavenAdapter.setWinPicRecyclerItemLongClickListener(onWallhavenPicDataRecyclerItemLongClickListener);
                    picList.setAdapter(wallhavenAdapter);
                }else wallhavenAdapter.refresh();
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
        Config.unsplashNetWorkBusiness.getUnsplashData(Config.UnsplashPage = 1, Config.UnsplashTypeItem.getTag(), new NCallBack<UnsplashBase>(getApplicationContext()) {

            @Override
            protected void onResponse(UnsplashBase response) {
                Config.UnsplashPicInfo = response.getData();

                if (unsplashAdapter == null){
                    unsplashAdapter = new WinAdapter(getApplicationContext());
                    unsplashAdapter.setWinPicRecyclerItemClickListener(onUnsplashPicDataRecyclerItemClickListener);
                    unsplashAdapter.setWinPicRecyclerItemLongClickListener(onUnsplashPicDataRecyclerItemLongClickListener);
                    picList.setAdapter(unsplashAdapter);
                }else unsplashAdapter.refresh();
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initPhoneRefreshData() {
        Config.phoneNetWorkBusiness.getInfoPhoneData(Config.PhoneTypeItem.getId(), Config.PhonePage = 0, new NCallBack<BaseWallInfo>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseWallInfo response) {
                Config.PhonePicInfo = response.getres().getVertical();

                if (phoneAdapter == null){
                    phoneAdapter = new PhoneAdapter(getApplicationContext());
                    phoneAdapter.setRecyclerItemClickListener(onPhonePicDataRecyclerItemClickListener);
                    phoneAdapter.setRecyclerItemLongClickListener(onPhonePicDataRecyclerItemLongClickListener);
                    picList.setAdapter(phoneAdapter);
                }else phoneAdapter.refresh();
                picRefresh.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                picRefresh.setRefreshing(false);
            }

        });

    }

    @Override
    protected void initGlide360Data() {
        loading = true;
        Config.sllNetWorkBusiness.getSslInfoData(Integer.valueOf(Config.SllTypeItem.getTag()), Config.SllPage, Config.SllPage += 15,"360chrome", new NCallBack<SllBase>(getApplicationContext()) {
            @Override
            protected void onResponse(SllBase response) {
                if (response.getData().size()==0)Tool.Toast(getApplicationContext(),"暂无更多");

                else sllAdapter.addSll(sllAdapter.getItemCount(),response.getData());
                loading = false;
            }

            @Override
            public void onNoNetwork() {
                loading = false;
            }

        });

    }

    @Override
    protected void initGlidePhoneData() {
        loading = true;
        Config.phoneNetWorkBusiness.getInfoPhoneData(Config.PhoneTypeItem.getId(), Config.PhonePage += 15, new NCallBack<BaseWallInfo>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseWallInfo response) {

                if (response.getres().getVertical().size() == 0) Tool.Toast(getApplicationContext(),"暂无更多");
                else phoneAdapter.addPhone(phoneAdapter.getItemCount(),response.getres().getVertical());
                loading = false;
            }

            @Override
            public void onNoNetwork() {
                loading = false;
            }
        });
    }

    @Override
    protected void initGlideUnsplashData() {
        loading = true;
        Config.unsplashNetWorkBusiness.getUnsplashData(Config.UnsplashPage += 1, Config.UnsplashTypeItem.getTag(), new NCallBack<UnsplashBase>(getApplicationContext()) {
            @Override
            protected void onResponse(UnsplashBase response) {
                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(),"暂无更多");
                else unsplashAdapter.addUnsplash(unsplashAdapter.getItemCount(),response.getData());
                loading = false;
            }

            @Override
            public void onNoNetwork() {
                loading = false;
            }
        });
    }

    @Override
    protected void initGlideWallhavenData() {
        loading = true;
        Config.wallhavenNetWorkBusiness.getWallhavenData(Config.WallhavenPage += 1, Config.WallhavenTypeItem.getTag(), new NCallBack<WallhavenBase>(getApplicationContext()) {
            @Override
            protected void onResponse(WallhavenBase response) {

                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(),"暂无更多");
                else wallhavenAdapter.addWallhaven(wallhavenAdapter.getItemCount(),response.getData());
                loading = false;
            }
            @Override
            public void onNoNetwork() {
                loading = false;
            }
        });
    }

    @Override
    protected void initGlideOneData() {
        loading = true;
        Config.oneNetWorkBusiness.getOneData(Tool.getDate(Config.date -= 86400000), new NCallBack<OneBase>(getApplicationContext()) {
            @Override
            protected void onResponse(OneBase response) {
                if (response.getData().getContent_list().size() == 0) Tool.Toast(getApplicationContext(),"暂无更多");
                else oneAdapter.addOne(oneAdapter.getItemCount(),response.getData().getContent_list());
                loading = false;
            }

            @Override
            public void onNoNetwork() {

            }
        });
    }

    @Override
    protected void initGlideBingData() {
        loading = true;
        Config.bingNetWorkBusiness.getBingData(Config.BingPage += 1, new NCallBack<BingBase>(getApplicationContext()) {
            @Override
            protected void onResponse(BingBase response) {
                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(),"暂无更多");
                else bingAdapter.addBing(bingAdapter.getItemCount(),response.getData());
                loading = false;
            }

            @Override
            public void onNoNetwork() {
                loading = false;
            }
        });
    }

    // phone 监听
    private PhoneAdapter.OnPicDataRecyclerItemClickListener onPhonePicDataRecyclerItemClickListener = new PhoneAdapter.OnPicDataRecyclerItemClickListener() {
        @Override
        public void onPicDataRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),LookPicActivity.class);
            Config.numPicInfo = position;
            startActivity(intent);
        }
    };
    private PhoneAdapter.OnPicDataRecyclerItemLongClickListener onPhonePicDataRecyclerItemLongClickListener = new PhoneAdapter.OnPicDataRecyclerItemLongClickListener() {
        @Override
        public void onPicDataRecyclerItemLongClick(int position) {
            Config.numPicInfo = position;
            new LongAlertDialog(PicInfoActivity.this).show();
        }
    };

    // sll 监听
    private WinAdapter.OnWinPicDataRecyclerItemClickListener onSllPicDataRecyclerItemClickListener = new WinAdapter.OnWinPicDataRecyclerItemClickListener() {
        @Override
        public void onWinPicDataRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),LookPicActivity.class);
            Config.numPicInfo = position;
            startActivity(intent);
        }
    };
    private WinAdapter.OnWinPicDataRecyclerItemLongClickListener onSllPicDataRecyclerItemLongClickListener = new WinAdapter.OnWinPicDataRecyclerItemLongClickListener() {
        @Override
        public void onWinPicDataRecyclerItemLongClick(int position) {
            Config.numPicInfo = position;
            new LongAlertDialog(PicInfoActivity.this).show();
        }
    };

    // Unsplash 监听
    private WinAdapter.OnWinPicDataRecyclerItemClickListener onUnsplashPicDataRecyclerItemClickListener = new WinAdapter.OnWinPicDataRecyclerItemClickListener() {
        @Override
        public void onWinPicDataRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),LookPicActivity.class);
            Config.numPicInfo = position;
            startActivity(intent);
        }
    };
    private WinAdapter.OnWinPicDataRecyclerItemLongClickListener onUnsplashPicDataRecyclerItemLongClickListener = new WinAdapter.OnWinPicDataRecyclerItemLongClickListener() {
        @Override
        public void onWinPicDataRecyclerItemLongClick(int position) {

        }
    };


    // Wallhaven 监听
    private WinAdapter.OnWinPicDataRecyclerItemClickListener onWallhavenPicDataRecyclerItemClickListener = new WinAdapter.OnWinPicDataRecyclerItemClickListener() {
        @Override
        public void onWinPicDataRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),LookPicActivity.class);
            Config.numPicInfo = position;
            startActivity(intent);
        }
    };
    private WinAdapter.OnWinPicDataRecyclerItemLongClickListener onWallhavenPicDataRecyclerItemLongClickListener = new WinAdapter.OnWinPicDataRecyclerItemLongClickListener() {
        @Override
        public void onWinPicDataRecyclerItemLongClick(int position) {

        }
    };

    // one监听
    private SingleAdapter.OnSinglePicDataRecyclerItemClickListener onOnePicDataRecyclerItemClickListener = new SingleAdapter.OnSinglePicDataRecyclerItemClickListener() {
        @Override
        public void onSinglePicDataRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),LookPicActivity.class);
            Config.numPicInfo = position;
            startActivity(intent);
        }
    };
    private SingleAdapter.OnSinglePicDataRecyclerItemLongClickListener onOnePicDataRecyclerItemLongClickListener = new SingleAdapter.OnSinglePicDataRecyclerItemLongClickListener() {
        @Override
        public void onSinglePicDataRecyclerItemLongClick(int position) {
            Config.numPicInfo = position;
            new LongAlertDialog(PicInfoActivity.this).show();
        }
    };


    // Bing 监听
    private SingleAdapter.OnSinglePicDataRecyclerItemClickListener onBingPicDataRecyclerItemClickListener = new SingleAdapter.OnSinglePicDataRecyclerItemClickListener() {
        @Override
        public void onSinglePicDataRecyclerItemClick(int position) {
            Intent intent = new Intent(getApplicationContext(),LookPicActivity.class);
            Config.numPicInfo = position;
            startActivity(intent);
        }
    };
    private SingleAdapter.OnSinglePicDataRecyclerItemLongClickListener onBingPicDataRecyclerItemLongClickListener = new SingleAdapter.OnSinglePicDataRecyclerItemLongClickListener() {
        @Override
        public void onSinglePicDataRecyclerItemLongClick(int position) {

        }
    };




}
