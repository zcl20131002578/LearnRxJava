package com.inke.zcl.learnrxjava.view.optimization_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019-09-26
 * Version 1.0
 * Description:
 */
public class TextStabView extends CustomFatherView {

    private static final String TAG = "test_viewstub";
    protected ViewStub mViewStub;

    public TextStabView(@NonNull Context context) {
        super(context);
    }

    public TextStabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_test_viewstub;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void init() {
        mViewStub = (ViewStub) findViewById(R.id.act_test_viewstub_viewstub);
        Log.e(TAG, "viewstub: " + findViewById(R.id.act_test_viewstub_viewstub));
        Log.e(TAG, "layout: " + findViewById(R.id.act_layout_viewstub_new));

        findViewById(R.id.act_test_viewstub_tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layoutView;
//                mViewStub.setVisibility(View.VISIBLE);
                layoutView = mViewStub.inflate();

                Log.e(TAG, "mViewStub: " + mViewStub);
                // ViewStub在visible/inflated后会被移除

                // 因为所以此处为null
                Log.e(TAG, "viewstub: " + findViewById(R.id.act_test_viewstub_viewstub));
//                layoutView = findViewById(R.id.act_layout_viewstub_new);
                Log.e(TAG, "layoutView equals finviewbyid(layout): " +
                        layoutView.equals(findViewById(R.id.act_layout_viewstub_new)));
                Log.e(TAG, "layout: " + layoutView);

                // layoutView的root view id 是mViewStub inflatedId指定的ID
                if (layoutView.getId() == R.id.act_layout_viewstub_new) {
                    Log.e(TAG, "layout root id is act_layout_viewstub_new");
                } else if (layoutView.getId() == R.id.layout_viewstub_old) {
                    Log.e(TAG, "layout root id is layout_viewstub_old");
                } else {
                    Log.e(TAG, "layout root id is anyone : " + layoutView.getId());
                }

                // layoutView的root view布局 和mViewStub的布局保持一致
                int width = layoutView.getLayoutParams().width;
                if (width == ViewGroup.LayoutParams.MATCH_PARENT) {
                    Log.e(TAG, "layout width is MATCH_PARENT");
                } else if (width == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    Log.e(TAG, "layout width is WRAP_CONTENT");
                } else {
                    Log.e(TAG, "layout width is anyone : " + width);
                }
            }
        });

    }

    @Override
    protected void callOnClick(View v) {

    }
}
