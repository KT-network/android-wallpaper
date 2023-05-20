package com.kt.simpleWallPaper.Adapter.LookPageAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.api.Phone.base.resDataInfo.verticalList;

import java.util.List;

public class PhoneLookAdapter extends RecyclerView.Adapter<PhoneLookAdapter.ViewHolder> {

    private Context context;
    public PhoneLookAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_look_phone_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (Config.PAGEINFOSIGN == 0) Glide.with(context).load(Config.PhonePicInfo.get(position).getPreview()).into(holder.imageView);
//        if (Config.PageType == 1) Glide.with(context).load(Config.SllPicInfo.get(position).getImg_1600_900()).into(holder.imageView);
//        if (Config.PageType == 2) Glide.with(context).load(Config.OnePicPicInfo.get(position).getImg_url()).into(holder.imageView);
//        if (Config.PageType == 3) Glide.with(context).load(Config.BingPicInfo.get(position).getFullSrc()).into(holder.imageView);
//        if (Config.PageType == 4) Glide.with(context).load(Config.UnsplashPicInfo.get(position).getRaw()).into(holder.imageView);
//        if (Config.PageType == 5)Glide.with(context).load(Config.WallhavenPicInfo.get(position).getPath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (Config.PAGEINFOSIGN == 0) return Config.PhonePicInfo.size();
//        else if (Config.PageType == 1) return Config.SllPicInfo.size();
//        else if (Config.PageType == 2) return Config.OnePicPicInfo.size();
//        else if (Config.PageType == 3) return Config.BingPicInfo.size();
//        else if (Config.PageType == 4) return Config.UnsplashPicInfo.size();
//        else if (Config.PageType == 5) return Config.WallhavenPicInfo.size();
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lookImageItem);

            if (mOnPhonePagerItemClick != null){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnPhonePagerItemClick.OnPhonePagerItemClick(getAdapterPosition());
                    }
                });
            }

            if (mOnPhonePagerItemLongClick != null){

                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mOnPhonePagerItemLongClick.OnPhonePagerItemLongClick(getAdapterPosition());
                        return true;
                    }
                });

            }
        }
    }

    public void addPhone(int position, List<verticalList> data){

        if (data != null){
            Config.PhonePicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.PhonePicInfo.size());

    }

    // 定义item点击监听
    public interface OnPhonePagerItemClickListener {
        void OnPhonePagerItemClick(int position);
    }
    private OnPhonePagerItemClickListener mOnPhonePagerItemClick;
    public void setOnPagerItemClickListener(OnPhonePagerItemClickListener listener){
        mOnPhonePagerItemClick = listener;
    }

    // 定义item长按事件
    public interface OnPhonePagerItemLongClickListener {
        void OnPhonePagerItemLongClick(int position);
    }
    private OnPhonePagerItemLongClickListener mOnPhonePagerItemLongClick;
    public void setOnPagerItemLongClickListener(OnPhonePagerItemLongClickListener listener){
        mOnPhonePagerItemLongClick = listener;
    }


}
