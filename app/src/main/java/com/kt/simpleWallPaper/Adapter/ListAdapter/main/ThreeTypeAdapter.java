package com.kt.simpleWallPaper.Adapter.ListAdapter.main;

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

import java.util.List;

public class ThreeTypeAdapter extends RecyclerView.Adapter<ThreeTypeAdapter.ViewHolder> {

    private Context context;


    public ThreeTypeAdapter(Context context) {
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

        holder.Txt.setText(Config.TTypeData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return Config.TTypeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Txt;
        private LinearLayout Line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Line = itemView.findViewById(R.id.line);
            Txt = itemView.findViewById(R.id.functionName);

            if (mOnThreeRecyclerItemClick != null) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mOnThreeRecyclerItemClick != null) {
                            mOnThreeRecyclerItemClick.onThreeRecyclerItemClick(getAdapterPosition());
                        }

                    }
                });
            }

            if (mOnThreeDataRecyclerItemLongClick != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnThreeDataRecyclerItemLongClick.onThreeDataRecyclerItemLongClick(getAdapterPosition());
                        return true;
                    }
                });

            }

        }
    }

    // 定义item点击监听
    private OnThreeRecyclerItemClickListener mOnThreeRecyclerItemClick;

    public void setThreeRecyclerItemClickListener(OnThreeRecyclerItemClickListener listener) {
        mOnThreeRecyclerItemClick = listener;
    }

    public interface OnThreeRecyclerItemClickListener {
        void onThreeRecyclerItemClick(int position);
    }

    // 定义item长按事件
    public interface OnThreeDataRecyclerItemLongClickListener {
        void onThreeDataRecyclerItemLongClick(int position);
    }

    private OnThreeDataRecyclerItemLongClickListener mOnThreeDataRecyclerItemLongClick;

    public void setThreeRecyclerItemLongClickListener(OnThreeDataRecyclerItemLongClickListener listener) {
        mOnThreeDataRecyclerItemLongClick = listener;
    }


    public void upDate() {

        notifyDataSetChanged();

    }


}
