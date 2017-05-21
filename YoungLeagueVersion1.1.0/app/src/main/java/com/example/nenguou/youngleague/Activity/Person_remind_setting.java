package com.example.nenguou.youngleague.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/11.
 */

public class Person_remind_setting extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_remind_setting);
    }
    public void back(View v){
        onBackPressed();
    }
}
