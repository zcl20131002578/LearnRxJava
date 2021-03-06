package com.inke.zcl.learnrxjava.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.customview.GradientProgressBar;

/**
 * Created by WangChunLei on 16/1/6.
 */

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = RouterConstans.GradientProgressActivity)
public class GradientProgressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gradient_progress);

        final GradientProgressBar gradientProgressBar = (GradientProgressBar) findViewById(R.id.gradient_progress_bar);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gradientProgressBar.setPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
