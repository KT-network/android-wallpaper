package com.kt.simpleWallPaper.Adapter.LookPageAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.api.One.base.OneContentBase;
import com.kt.simpleWallPaper.api.Sll.base.SllDataBase;
import com.kt.simpleWallPaper.api.Unsplash.base.UnsplashDataBase;
import com.kt.simpleWallPaper.api.Wallhaven.base.WallhavenDataBase;
import com.kt.simpleWallPaper.api.bing.base.BingDataBase;

import java.util.List;

public class WinLookAdapter extends RecyclerView.Adapter<WinLookAdapter.ViewHolder> {

    private Context context;

    public WinLookAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_look_win_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (Config.PageType == 0) Glide.with(context).load(Config.PhonePicInfo.get(position).getPreview()).into(holder.imageView);
        if (Config.PAGEINFOSIGN == 1)
            Glide.with(context).load(Config.SllPicInfo.get(position).getImg_1600_900()).into(holder.imageView);
        if (Config.PAGEINFOSIGN == 2)
            Glide.with(context).load(Config.OnePicPicInfo.get(position).getImg_url()).into(holder.imageView);
        if (Config.PAGEINFOSIGN == 3)
            Glide.with(context).load(Config.BingPicInfo.get(position).getFullSrc()).into(holder.imageView);
        if (Config.PAGEINFOSIGN == 4)
            Glide.with(context).load(Config.WallhavenPicInfo.get(position).getPath()).into(holder.imageView);
        if (Config.PAGEINFOSIGN == 5)
            Glide.with(context).load(Config.UnsplashPicInfo.get(position).getRaw()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (Config.PAGEINFOSIGN == 1) return Config.SllPicInfo.size();
        else if (Config.PAGEINFOSIGN == 2) return Config.OnePicPicInfo.size();
        else if (Config.PAGEINFOSIGN == 3) return Config.BingPicInfo.size();
        else if (Config.PAGEINFOSIGN == 4) return Config.WallhavenPicInfo.size();
        else if (Config.PAGEINFOSIGN == 5) return Config.UnsplashPicInfo.size();
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lookImageItem);

            if (mOnWinPagerItemClick != null) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnWinPagerItemClick.OnWinPagerItemClick(getAdapterPosition());
                    }
                });
            }

            if (mOnWinPagerItemLongClick != null) {

                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mOnWinPagerItemLongClick.OnWinPagerItemLongClick(getAdapterPosition());
                        return true;
                    }
                });

            }
        }
    }


    public void addSll(int position, List<SllDataBase> data) {

        if (data != null) {
            Config.SllPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.SllPicInfo.size());

    }

    public void addWallhaven(int position, List<WallhavenDataBase> data) {
        if (data != null) {
            Config.WallhavenPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.WallhavenPicInfo.size());
    }

    public void addUnsplash(int position, List<UnsplashDataBase> data) {
        if (data != null) {
            Config.UnsplashPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.UnsplashPicInfo.size());

    }

    public void addOne(int position, List<OneContentBase> data) {

        if (data != null) {
            Config.OnePicPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.OnePicPicInfo.size());
    }

    public void addBing(int position, List<BingDataBase> data) {
        if (data != null) {
            Config.BingPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.BingPicInfo.size());

    }

    // 定义item点击监听
    public interface OnWinPagerItemClickListener {
        void OnWinPagerItemClick(int position);
    }

    private OnWinPagerItemClickListener mOnWinPagerItemClick;

    public void setOnPagerItemClickListener(OnWinPagerItemClickListener listener) {
        mOnWinPagerItemClick = listener;
    }

    // 定义item长按事件
    public interface OnWinPagerItemLongClickListener {
        void OnWinPagerItemLongClick(int position);
    }

    private OnWinPagerItemLongClickListener mOnWinPagerItemLongClick;

    public void setOnPagerItemLongClickListener(OnWinPagerItemLongClickListener listener) {
        mOnWinPagerItemLongClick = listener;
    }


}
