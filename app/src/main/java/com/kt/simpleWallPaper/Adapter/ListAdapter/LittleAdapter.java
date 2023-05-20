package com.kt.simpleWallPaper.Adapter.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;

public class LittleAdapter extends RecyclerView.Adapter<LittleAdapter.ViewHolder> {

    private Context context;


    public LittleAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_type_item1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (Config.PAGETYPESIGN == 0) holder.Txt.setText(Config.SllTypeData.get(position).getName());
        if (Config.PAGETYPESIGN == 1) holder.Txt.setText(Config.UnsplashTypeData.get(position).getName());
        if (Config.PAGETYPESIGN == 2) holder.Txt.setText(Config.WallhavenTypeData.get(position).getName());
    }

    @Override
    public int getItemCount() {

        if (Config.PAGETYPESIGN == 0) return Config.SllTypeData.size();
        if (Config.PAGETYPESIGN == 1) return Config.UnsplashTypeData.size();
        if (Config.PAGETYPESIGN == 2) return Config.WallhavenTypeData.size();


        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Txt;
        private LinearLayout Line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Line = itemView.findViewById(R.id.line);
            Txt = itemView.findViewById(R.id.functionName);

            if (mOnLittleRecyclerItemClick != null) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mOnLittleRecyclerItemClick != null) {
                            mOnLittleRecyclerItemClick.onLittleRecyclerItemClick(getAdapterPosition());
                        }

                    }
                });
            }

            if (mOnLittleDataRecyclerItemLongClick != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnLittleDataRecyclerItemLongClick.onLittleDataRecyclerItemLongClick(getAdapterPosition());
                        return true;
                    }
                });

            }

        }
    }

    // 定义item点击监听
    private OnLittleRecyclerItemClickListener mOnLittleRecyclerItemClick;

    public void setLittleRecyclerItemClickListener(OnLittleRecyclerItemClickListener listener) {
        mOnLittleRecyclerItemClick = listener;
    }

    public interface OnLittleRecyclerItemClickListener {
        void onLittleRecyclerItemClick(int position);
    }

    // 定义item长按事件
    public interface OnLittleDataRecyclerItemLongClickListener {
        void onLittleDataRecyclerItemLongClick(int position);
    }

    private OnLittleDataRecyclerItemLongClickListener mOnLittleDataRecyclerItemLongClick;

    public void setLittleRecyclerItemLongClickListener(OnLittleDataRecyclerItemLongClickListener listener) {
        mOnLittleDataRecyclerItemLongClick = listener;
    }


    public void refresh() {

        notifyDataSetChanged();

    }


}
