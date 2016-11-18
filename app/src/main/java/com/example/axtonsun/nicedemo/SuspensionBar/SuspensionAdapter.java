package com.example.axtonsun.nicedemo.SuspensionBar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.axtonsun.nicedemo.R;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public class SuspensionAdapter extends RecyclerView.Adapter<SuspensionAdapter.SuspensionHolder> {

    public SuspensionAdapter() {
    }

    @Override
    public SuspensionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suspension,parent,false);
        return new SuspensionHolder(view);
    }

    @Override
    public void onBindViewHolder(SuspensionHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(getIamgeResId(position))
                .into(holder.mIvImage);
        Glide.with(holder.itemView.getContext())
                .load(getWarResId(position))
                .into(holder.mIvWar);
        holder.mTvName.setText("Sun" + position);

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    private int getIamgeResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.iamge1;
            case 1:
                return R.drawable.iamge2;
            case 2:
                return R.drawable.iamge3;
            case 3:
                return R.drawable.iamge4;
        }
        return 0;
    }

    private int getWarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.war1;
            case 1:
                return R.drawable.war2;
            case 2:
                return R.drawable.war3;
            case 3:
                return R.drawable.war4;
        }
        return 0;
    }

    public static class SuspensionHolder extends RecyclerView.ViewHolder{
        private ImageView mIvImage;
        private ImageView mIvWar;
        private TextView mTvName;
        public SuspensionHolder(View itemView) {
            super(itemView);
            mIvImage = (ImageView) itemView.findViewById(R.id.iv_avatar);
            mIvWar = (ImageView) itemView.findViewById(R.id.iv_content);
            mTvName = (TextView) itemView.findViewById(R.id.tv_nickname);
        }
    }
}
