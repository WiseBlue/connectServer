package com.example.nenguou.youngleague.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/11.
 */

public class Person_personal_setting extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_personal_setting);
        final RelativeLayout person_remind_setting= (RelativeLayout) findViewById(R.id.person_remind_setting);
        person_remind_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Person_remind_setting.class);
                startActivity(intent);
            }
        });
        final RelativeLayout person_password_amend= (RelativeLayout) findViewById(R.id.person_password_amend);
        person_password_amend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Person_password_amend.class);
                startActivity(intent);
            }
        });


    }
    public void back(View v){
        onBackPressed();
    }
}
