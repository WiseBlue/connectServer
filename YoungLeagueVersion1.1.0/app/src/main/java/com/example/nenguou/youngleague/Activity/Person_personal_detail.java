package com.example.nenguou.youngleague.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/20.
 */

public class Person_personal_detail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_personal_detail);
    }

    public void back(View view) {
        onBackPressed();
    }
}
