package com.kt.simpleWallPaper.Adapter.ListAdapter.main;

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
import com.kt.simpleWallPaper.api.Phone.base.resDataType.CategoryList;

import java.util.List;

public class OneTypeAdapter extends RecyclerView.Adapter<OneTypeAdapter.ViewHolder> {

    private Context context;


    public OneTypeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_type_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.typeTxt.setText(Config.PhoneTypeData.get(position).getName());
        Glide.with(context).load(Config.PhoneTypeData.get(position).getCover()).into(holder.typeImage);
    }

    @Override
    public int getItemCount() {
        return Config.PhoneTypeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView typeImage;
        private TextView typeTxt;
        private LinearLayout Line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Line = itemView.findViewById(R.id.Line);
            typeImage = itemView.findViewById(R.id.typeImage);
            typeTxt = itemView.findViewById(R.id.typeTxt);
            Line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mOneOnRecyclerItemClick != null) {
                        mOneOnRecyclerItemClick.onOneRecyclerItemClick(getAdapterPosition());
                    }

                }
            });

        }
    }

    // 定义item点击监听
    private OneOnRecyclerItemClickListener mOneOnRecyclerItemClick;
    public void setOneRecyclerItemClickListener(OneOnRecyclerItemClickListener listener){
        mOneOnRecyclerItemClick = listener;
    }

    public interface OneOnRecyclerItemClickListener {
        void onOneRecyclerItemClick(int position);
    }


    public void upDate() {

        notifyDataSetChanged();

    }


}
