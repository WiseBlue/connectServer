package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/3/29.
 */

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
    public void onBackPressed(){
        //super.onBackPressed();
        Intent intent=new Intent(About.this,MainActivity.class);
        startActivity(intent);
    }
}
