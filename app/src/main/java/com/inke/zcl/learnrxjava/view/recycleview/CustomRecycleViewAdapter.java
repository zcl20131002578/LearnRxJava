package com.inke.zcl.learnrxjava.view.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.utils.CollectionUtils;

import java.util.List;


/**
 * Create By chunliangzhang on 2019/4/16
 * Version 1.0
 * Description:
 */
public class CustomRecycleViewAdapter extends RecyclerView.Adapter<CustomRecycleViewAdapter.CustomViewHolder> {

    private List list;
    private Context context;

    public CustomRecycleViewAdapter(Context context, @NonNull List list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.custom_rey_item, null);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.setData((String) list.get(i));
    }

    @Override
    public int getItemCount() {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        } else {
            return list.size();
        }
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.num);
        }

        public void setData(String str) {
            textView.setText(str);
        }
    }
}
