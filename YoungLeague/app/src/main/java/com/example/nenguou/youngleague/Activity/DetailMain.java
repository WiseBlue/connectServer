package com.example.nenguou.youngleague.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/10.
 */

public class DetailMain extends AppCompatActivity {
    private TextView tv_title,tv_university,tv_summary;
    private TextView tv_vedio,tv_fancy,tv_date;
    private ImageButton ib_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);

        tv_title= (TextView) findViewById(R.id.detail_tv_title);
        tv_university= (TextView) findViewById(R.id.detail_tv_university);
        tv_summary= (TextView) findViewById(R.id.detail_tv_summary);
        tv_vedio= (TextView) findViewById(R.id.detail_tv_vedio);
        tv_fancy= (TextView) findViewById(R.id.detail_tv_fancy);
        tv_date= (TextView) findViewById(R.id.detail_tv_date);
        ib_back= (ImageButton) findViewById(R.id.ib_main);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title").toString();
        String university=intent.getStringExtra("university").toString();
        String summary=intent.getStringExtra("detail").toString();
        String vedio=intent.getStringExtra("vedio").toString();
        String fancy=intent.getStringExtra("fancy").toString();
        String date=intent.getStringExtra("date").toString();


        tv_vedio.setText(vedio);
        tv_fancy.setText(fancy);
        tv_date.setText(date);
        tv_title.setText(title);
        tv_university.setText(university);
        tv_summary.setText(Html.fromHtml(summary));
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
