package com.example.nenguou.youngleague.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/20.
 */

public class Detail_announce extends AppCompatActivity {
    private TextView tv_title,tv_author,tv_content;
    private ImageButton ib_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_announce);

        tv_title= (TextView) findViewById(R.id.detail_announce_title);
        tv_author= (TextView) findViewById(R.id.detail_announce_author);
        tv_content= (TextView) findViewById(R.id.detail_announce_content);
        ib_main= (ImageButton) findViewById(R.id.ib_main);
        ib_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent=getIntent();
        String title=intent.getStringExtra("title").toString();
        String author=intent.getStringExtra("author").toString();
        String content=intent.getStringExtra("content").toString();

        tv_title.setText(title);
        tv_content.setText(content);
        tv_author.setText(author);
    }
}
