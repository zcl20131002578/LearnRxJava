package com.inke.zcl.learnrxjava.activity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.inke.zcl.learnrxjava.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
    }


}
