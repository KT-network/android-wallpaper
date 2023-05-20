package com.kt.simpleWallPaper.Adapter.MyFragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Adapter.ListAdapter.main.TwoTypeAdapter;
import com.kt.simpleWallPaper.Adapter.NewLazyFragment;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.Dialog.UpDateDialog;
import com.kt.simpleWallPaper.ui.PicInfoActivity;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.My.base.TypeBase;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;
import com.kt.simpleWallPaper.api.NCallBack;

import java.util.List;

public class TwoFragment extends NewLazyFragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TwoTypeAdapter twoTypeAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_page_type;
    }

    /**
     * 初始化视图
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.typeList);
        mSwipeRefreshLayout = view.findViewById(R.id.typeRefresh);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mSwipeRefreshLayout.setOnRefreshListener(onRefresh);

    }


    /*
     * 刷新数据
     * */
    private SwipeRefreshLayout.OnRefreshListener onRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (Config.UPDATE != null) data();
            else isUpDateNull();
        }
    };


    /*
     * 初始化数据
     * */
    @Override
    protected void initData() {
        super.initData();

        if (Config.UPDATE != null) data();
        else isUpDateNull();

    }

    private TwoTypeAdapter.TwoOnRecyclerItemClickListener twoOnRecyclerItemClickListener = new TwoTypeAdapter.TwoOnRecyclerItemClickListener() {
        @Override
        public void onTwoRecyclerItemClick(int position) {
            Intent intent = null;

            switch (Config.TwoDataTag) {
                case "sll":
                    Config.SllTypeItem = Config.SllTypeData.get(position);
                    intent = new Intent(getContext(), PicInfoActivity.class);
                    Config.PAGEINFOSIGN = 1;
                    break;
                case "Wallhaven":
                    Config.WallhavenTypeItem = Config.SllTypeData.get(position);
                    intent = new Intent(getContext(), PicInfoActivity.class);
                    Config.PAGEINFOSIGN = 4;
                    break;
                case "Unsplash":
                    Config.UnsplashTypeItem = Config.SllTypeData.get(position);
                    intent = new Intent(getContext(), PicInfoActivity.class);
                    Config.PAGEINFOSIGN = 5;
                    break;

            }
            startActivity(intent);

        }
    };

    private void isUpDateNull() {

        Config.myNetWorkBusiness.getUpData(new NCallBack<BaseResponseEntity<UpDateBase>>(getContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<UpDateBase> response) {
                Config.UPDATE = response.getData();
                mSwipeRefreshLayout.setRefreshing(false);

                if (Double.valueOf(Config.UPDATE.getVersionsNum()) > Config.versionsNUM) {
                    UpDateDialog upDateDialog = new UpDateDialog(getContext(), Config.UPDATE);
                    upDateDialog.show();
                    upDateDialog.setConfirmOnClickListener(upDateConfirmOnClickListener);
                    upDateDialog.setOnDismissListener(upDateOnDismissListener);
                } else data();

            }

            @Override
            public void onNoNetwork() {
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

    }

    private void data() {
        Config.myNetWorkBusiness.getData(Config.TwoDataTag, new NCallBack<BaseResponseEntity<List<TypeBase>>>(getContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.SllTypeData = response.getData();
                if (twoTypeAdapter == null) {
                    twoTypeAdapter = new TwoTypeAdapter(getContext());
                    twoTypeAdapter.setTwoRecyclerItemClickListener(twoOnRecyclerItemClickListener);
                    mRecyclerView.setAdapter(twoTypeAdapter);
                } else twoTypeAdapter.upDate();

                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNoNetwork() {
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });
    }


    @Override
    protected void initEvent() {
        super.initEvent();
        mSwipeRefreshLayout.setRefreshing(true);
    }
}
