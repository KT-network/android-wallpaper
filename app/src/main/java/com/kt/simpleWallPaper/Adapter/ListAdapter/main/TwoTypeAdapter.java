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
import com.kt.simpleWallPaper.api.My.base.TypeBase;

import java.util.List;

public class TwoTypeAdapter extends RecyclerView.Adapter<TwoTypeAdapter.ViewHolder> {

    private Context context;


    public TwoTypeAdapter(Context context) {
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
        holder.typeTxt.setText(Config.SllTypeData.get(position).getName());
        Glide.with(context).load(Config.SllTypeData.get(position).getCoverPicUrl()).into(holder.typeImage);
    }

    @Override
    public int getItemCount() {
        return Config.SllTypeData.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mTwoOnRecyclerItemClick != null) {
                        mTwoOnRecyclerItemClick.onTwoRecyclerItemClick(getAdapterPosition());
                    }

                }
            });

        }
    }

    // 定义item点击监听
    private TwoOnRecyclerItemClickListener mTwoOnRecyclerItemClick;
    public void setTwoRecyclerItemClickListener(TwoOnRecyclerItemClickListener listener){
        mTwoOnRecyclerItemClick = listener;
    }

    public interface TwoOnRecyclerItemClickListener {
        void onTwoRecyclerItemClick(int position);
    }


    public void upDate() {

        notifyDataSetChanged();

    }


}
