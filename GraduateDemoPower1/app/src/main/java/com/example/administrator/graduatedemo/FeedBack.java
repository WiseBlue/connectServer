package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/29.
 */

public class FeedBack extends AppCompatActivity {
    private TextView textview1,textview2,textview3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        textview1= (TextView) findViewById(R.id.textViewback2);
        textview1= (TextView) findViewById(R.id.textViewback3);
        textview1= (TextView) findViewById(R.id.textViewback4);
    }

    public void feedback(View view) {
        Toast.makeText(FeedBack.this,"感谢您的评价",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(FeedBack.this,MainActivity.class);
        startActivity(intent);
    }
}
