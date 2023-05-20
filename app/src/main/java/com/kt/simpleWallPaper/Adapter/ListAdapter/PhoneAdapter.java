package com.kt.simpleWallPaper.Adapter.ListAdapter;

import android.content.Context;
import android.util.Log;
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
import com.kt.simpleWallPaper.api.Phone.base.resDataInfo.verticalList;
import com.kt.simpleWallPaper.api.Sll.base.SllDataBase;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private Context context;


    public PhoneAdapter(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pic_phone_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(Config.PhonePicInfo.get(position).getThumb()).into(holder.picImage);

    }


    @Override
    public int getItemCount() {

        return Config.PhonePicInfo.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picImage;
        private LinearLayout picLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picLine = itemView.findViewById(R.id.picLine);
            picImage = itemView.findViewById(R.id.picImage);


            if (mOnPicDataRecyclerItemClick != null) {
                picImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnPicDataRecyclerItemClick.onPicDataRecyclerItemClick(getAdapterPosition());
                    }
                });
            }

            if (mOnPicDataRecyclerItemLongClick != null) {

                picImage.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        mOnPicDataRecyclerItemLongClick.onPicDataRecyclerItemLongClick(getAdapterPosition());
                        return true;
                    }
                });

            }


        }
    }


    public void addPhone(int position, List<verticalList> data) {

        if (data != null) {
            Config.PhonePicInfo.addAll(data);
        }
        notifyItemInserted(position);
        notifyItemRangeChanged(position, Config.PhonePicInfo.size());

    }

    public void refresh() {
        notifyDataSetChanged();
    }


    // 定义item点击监听
    public interface OnPicDataRecyclerItemClickListener {
        void onPicDataRecyclerItemClick(int position);
    }

    private OnPicDataRecyclerItemClickListener mOnPicDataRecyclerItemClick;

    public void setRecyclerItemClickListener(OnPicDataRecyclerItemClickListener listener) {
        mOnPicDataRecyclerItemClick = listener;
    }

    // 定义item长按事件
    public interface OnPicDataRecyclerItemLongClickListener {
        void onPicDataRecyclerItemLongClick(int position);
    }

    private OnPicDataRecyclerItemLongClickListener mOnPicDataRecyclerItemLongClick;

    public void setRecyclerItemLongClickListener(OnPicDataRecyclerItemLongClickListener listener) {
        mOnPicDataRecyclerItemLongClick = listener;
    }
}
