package com.kt.simpleWallPaper.Adapter.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.api.One.base.OneContentBase;
import com.kt.simpleWallPaper.api.bing.base.BingDataBase;

import java.util.List;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.ViewHolder> {

    private Context context;

    public SingleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pic_one_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (Config.PAGEINFOSIGN == 2){
            Glide.with(context).load(Config.OnePicPicInfo.get(position).getImg_url()).into(holder.image);
            holder.text.setText(Config.OnePicPicInfo.get(position).getForward());
            holder.textLinear.setVisibility(View.VISIBLE);
        }
        else if (Config.PAGEINFOSIGN == 3){
            Glide.with(context).load(Config.BingPicInfo.get(position).getFullSrc()).into(holder.image);
            holder.textLinear.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {

        if (Config.PAGEINFOSIGN == 2) return Config.OnePicPicInfo.size();
        else if(Config.PAGEINFOSIGN == 3) return Config.BingPicInfo.size();
        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private ImageView image;
        private LinearLayout textLinear,linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);
            textLinear = itemView.findViewById(R.id.textLinear);
            linear = itemView.findViewById(R.id.linear);


            if (mOnSinglePicDataRecyclerItemClick != null) {
                linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnSinglePicDataRecyclerItemClick.onSinglePicDataRecyclerItemClick(getAdapterPosition());
                    }
                });
            }

            if (mOnSinglePicDataRecyclerItemLongClick != null) {

                linear.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        mOnSinglePicDataRecyclerItemLongClick.onSinglePicDataRecyclerItemLongClick(getAdapterPosition());
                        return true;
                    }
                });

            }


        }
    }


    public void addOne(int position, List<OneContentBase> data){

        if (data != null){
            Config.OnePicPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.OnePicPicInfo.size());
    }

    public void addBing(int position, List<BingDataBase> data){
        if (data != null){
            Config.BingPicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.BingPicInfo.size());

    }


    public void refresh() {
        notifyDataSetChanged();
    }


    // 定义item点击监听
    public interface OnSinglePicDataRecyclerItemClickListener {
        void onSinglePicDataRecyclerItemClick(int position);
    }

    private OnSinglePicDataRecyclerItemClickListener mOnSinglePicDataRecyclerItemClick;

    public void setSinglePicRecyclerItemClickListener(OnSinglePicDataRecyclerItemClickListener listener) {
        mOnSinglePicDataRecyclerItemClick = listener;
    }

    // 定义item长按事件
    public interface OnSinglePicDataRecyclerItemLongClickListener {
        void onSinglePicDataRecyclerItemLongClick(int position);
    }

    private OnSinglePicDataRecyclerItemLongClickListener mOnSinglePicDataRecyclerItemLongClick;

    public void setSinglePicRecyclerItemLongClickListener(OnSinglePicDataRecyclerItemLongClickListener listener) {
        mOnSinglePicDataRecyclerItemLongClick = listener;
    }
}
