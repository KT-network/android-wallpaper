package com.kt.simpleWallPaper.Adapter.MyFragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Adapter.NewLazyFragment;
import com.kt.simpleWallPaper.Adapter.ListAdapter.main.OneTypeAdapter;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.Dialog.UpDateDialog;
import com.kt.simpleWallPaper.ui.PicInfoActivity;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;
import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.Phone.PhoneNetWorkBusiness;
import com.kt.simpleWallPaper.api.Phone.base.BaseWallType;


public class OneFragment extends NewLazyFragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OneTypeAdapter mTypeAdapter;
    private PhoneNetWorkBusiness phoneNetWorkBusiness;

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


    private OneTypeAdapter.OneOnRecyclerItemClickListener oneOnRecyclerItemClickListener = new OneTypeAdapter.OneOnRecyclerItemClickListener() {
        @Override
        public void onOneRecyclerItemClick(int position) {

            Config.PhoneTypeItem = Config.PhoneTypeData.get(position);
            Intent intent = new Intent(getContext(), PicInfoActivity.class);
            Config.PAGEINFOSIGN = 0;
            startActivity(intent);

        }
    };


    @Override
    protected void initData() {
        super.initData();
        phoneNetWorkBusiness = new PhoneNetWorkBusiness();
        if (Config.UPDATE != null) data();
        else isUpDateNull();
    }

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

                    //                    Tool.setUpDate();

                } else {
                    data();
                }

            }

            @Override
            public void onNoNetwork() {
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

    }

    private void data() {
        phoneNetWorkBusiness.getPhoneTypeData(new NCallBack<BaseWallType>(getContext()) {
            @Override
            protected void onResponse(BaseWallType response) {

                Config.PhoneTypeData = response.getres().getCategory();

                if (mTypeAdapter == null) {
                    mTypeAdapter = new OneTypeAdapter(getContext());
                    mTypeAdapter.setOneRecyclerItemClickListener(oneOnRecyclerItemClickListener);
                    mRecyclerView.setAdapter(mTypeAdapter);
                } else mTypeAdapter.upDate();

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

    @Override
    protected void upDateOnDismiss() {
        super.upDateOnDismiss();
        data();
    }
}
