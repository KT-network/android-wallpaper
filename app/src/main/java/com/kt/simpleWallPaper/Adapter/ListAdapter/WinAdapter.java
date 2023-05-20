package com.kt.simpleWallPaper.Adapter.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class WinAdapter extends RecyclerView.Adapter<WinAdapter.ViewHolder> {
    private Context context;
    public WinAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pic_win_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (Config.PAGEINFOSIGN == 1)
            Glide.with(context).load(Config.SllPicInfo.get(position).getImg_1024_768()).into(holder.picImage);
        else if(Config.PAGEINFOSIGN == 4)
            Glide.with(context).load(Config.WallhavenPicInfo.get(position).getThumbs().getSmall()).into(holder.picImage);
        else if(Config.PAGEINFOSIGN == 5)
            Glide.with(context).load(Config.UnsplashPicInfo.get(position).getThumb()).into(holder.picImage);

    }


    @Override
    public int getItemCount() {

        if (Config.PAGEINFOSIGN == 1) return Config.SllPicInfo.size();
        else if(Config.PAGEINFOSIGN == 4) return Config.WallhavenPicInfo.size();
        else if(Config.PAGEINFOSIGN == 5) return Config.UnsplashPicInfo.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picImage;
        private LinearLayout picLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picLine = itemView.findViewById(R.id.picLine);
            picImage = itemView.findViewById(R.id.picImage);


            if (mOnWinPicDataRecyclerItemClick != null) {
                picImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnWinPicDataRecyclerItemClick.onWinPicDataRecyclerItemClick(getAdapterPosition());
                    }
                });
            }

            if (mOnWinPicDataRecyclerItemLongClick != null) {

                picImage.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        mOnWinPicDataRecyclerItemLongClick.onWinPicDataRecyclerItemLongClick(getAdapterPosition());
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

    public void addWallhaven(int position, List<WallhavenDataBase> data){
        if (data != null){
            Config.WallhavenPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.WallhavenPicInfo.size());
    }

    public void addUnsplash(int position, List<UnsplashDataBase> data){
        if (data != null){
            Config.UnsplashPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.UnsplashPicInfo.size());

    }


    public void refresh() {
        notifyDataSetChanged();
    }


    // 定义item点击监听
    public interface OnWinPicDataRecyclerItemClickListener {
        void onWinPicDataRecyclerItemClick(int position);
    }

    private OnWinPicDataRecyclerItemClickListener mOnWinPicDataRecyclerItemClick;

    public void setWinPicRecyclerItemClickListener(OnWinPicDataRecyclerItemClickListener listener) {
        mOnWinPicDataRecyclerItemClick = listener;
    }

    // 定义item长按事件
    public interface OnWinPicDataRecyclerItemLongClickListener {
        void onWinPicDataRecyclerItemLongClick(int position);
    }

    private OnWinPicDataRecyclerItemLongClickListener mOnWinPicDataRecyclerItemLongClick;

    public void setWinPicRecyclerItemLongClickListener(OnWinPicDataRecyclerItemLongClickListener listener) {
        mOnWinPicDataRecyclerItemLongClick = listener;
    }

}
