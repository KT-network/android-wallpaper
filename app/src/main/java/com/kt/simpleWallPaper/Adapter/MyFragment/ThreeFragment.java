package com.kt.simpleWallPaper.Adapter.MyFragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Adapter.ListAdapter.main.ThreeTypeAdapter;
import com.kt.simpleWallPaper.Adapter.NewLazyFragment;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.Dialog.UpDateDialog;
import com.kt.simpleWallPaper.ui.PicInfoActivity;
import com.kt.simpleWallPaper.ui.PicTypeActivity;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.My.base.TypeBase;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;
import com.kt.simpleWallPaper.api.NCallBack;

import java.util.List;

public class ThreeFragment extends NewLazyFragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ThreeTypeAdapter mTypeAdapter;


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


    private SwipeRefreshLayout.OnRefreshListener onRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (Config.UPDATE != null) data();
            else isUpDateNull();
        }
    };

    private ThreeTypeAdapter.OnThreeRecyclerItemClickListener onThreeRecyclerItemClickListener = new ThreeTypeAdapter.OnThreeRecyclerItemClickListener() {
        @Override
        public void onThreeRecyclerItemClick(int position) {

            String tag = Config.TTypeData.get(position).getTag();

            if (tag.equals("one")) {
                Intent intent = new Intent(getContext(), PicInfoActivity.class);
                Config.PAGEINFOSIGN = 2;
                startActivity(intent);
            }
            if (tag.equals("bing")) {
                Intent intent = new Intent(getContext(), PicInfoActivity.class);
                Config.PAGEINFOSIGN = 3;
                startActivity(intent);
            }
            if (tag.equals("Unsplash")) {
                Intent intent = new Intent(getContext(), PicTypeActivity.class);
                Config.PAGETYPESIGN = 1;
                startActivity(intent);
            }
            if (tag.equals("Wallhaven")) {
                Intent intent = new Intent(getContext(), PicTypeActivity.class);
                Config.PAGETYPESIGN = 2;
                startActivity(intent);
            }
            if (tag.equals("sll")){
                Intent intent = new Intent(getContext(), PicTypeActivity.class);
                Config.PAGETYPESIGN = 0;
                startActivity(intent);
            }
            if (tag.equals("My")) {

            }

        }
    };

    private ThreeTypeAdapter.OnThreeDataRecyclerItemLongClickListener onThreeDataRecyclerItemLongClickListener = new ThreeTypeAdapter.OnThreeDataRecyclerItemLongClickListener() {
        @Override
        public void onThreeDataRecyclerItemLongClick(int position) {
            String tag = Config.TTypeData.get(position).getTag();
            String name = Config.TTypeData.get(position).getName();

            if (tag.equals("sll")) {
                setAlertDialog(name,tag);
            } else if (tag.equals("Wallhaven")) {
                setAlertDialog(name,tag);
            } else if (tag.equals("Unsplash")) {
                setAlertDialog(name,tag);
            } else {
                Config.Utitle.setText("提示");
                Config.Utext.setText("此数据不支持此功能！！！");
                Config.Uconfirm.setVisibility(View.GONE);
                Config.Udialog.setView(Config.Uview);
                Config.Udialog.show();
            }

        }
    };


    private void setAlertDialog(String name,String tag){
        Config.Utitle.setText("提示");
        Config.Utext.setText("你确定要在下次启动时应用此数据（"+name+"）到第二个界面吗？");
        Config.Uconfirm.setVisibility(View.VISIBLE);
        Config.UconfirmText.setText("确定");
        Config.Udialog.setView(Config.Uview);
        Config.Udialog.show();

        Config.setOnIncidentListener(new Config.onIncidentListener() {
            @Override
            public void setDataIncidentListener() {
                Tool.Toast(getContext(),name);
                Config.Udialog.dismiss();
            }
        });

    }


    @Override
    protected void initData() {
        super.initData();
        if (Config.UPDATE != null) data();
        else isUpDateNull();
    }

    private void isUpDateNull() {

        Config.myNetWorkBusiness.getUpData(new NCallBack<BaseResponseEntity<UpDateBase>>(getContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<UpDateBase> response) {
                Config.UPDATE = response.getData();
                mSwipeRefreshLayout.setRefreshing(false);

                if (Double.valueOf(Config.UPDATE.getVersionsNum()) > Config.versionsNUM){
                    UpDateDialog upDateDialog = new UpDateDialog(getContext(), Config.UPDATE);
                    upDateDialog.show();
                    upDateDialog.setConfirmOnClickListener(upDateConfirmOnClickListener);
                    upDateDialog.setOnDismissListener(upDateOnDismissListener);
                }

                else data();

            }

            @Override
            public void onNoNetwork() {
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

    }

    private void data() {

        Config.myNetWorkBusiness.getData("test", new NCallBack<BaseResponseEntity<List<TypeBase>>>(getContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<TypeBase>> response) {
                Config.TTypeData = response.getData();

                if (mTypeAdapter == null) {
                    mTypeAdapter = new ThreeTypeAdapter(getContext());
                    mTypeAdapter.setThreeRecyclerItemClickListener(onThreeRecyclerItemClickListener);
//                    mTypeAdapter.setThreeRecyclerItemLongClickListener(onThreeDataRecyclerItemLongClickListener);
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
}
