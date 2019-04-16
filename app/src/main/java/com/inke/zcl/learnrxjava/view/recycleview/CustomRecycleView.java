package com.inke.zcl.learnrxjava.view.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.support.v7.widget.RecyclerView;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By chunliangzhang on 2019/4/16
 * Version 1.0
 * Description:
 */
public class CustomRecycleView extends CustomFatherView {

    private RecyclerView recyclerView;
    private CustomRecycleViewAdapter adapter;

    public CustomRecycleView(@NonNull Context context) {
        super(context);
    }

    public CustomRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_recycleview_layout;
    }

    @Override
    protected void init() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new CustomRecycleViewAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void callOnClick(View v) {

    }
}
