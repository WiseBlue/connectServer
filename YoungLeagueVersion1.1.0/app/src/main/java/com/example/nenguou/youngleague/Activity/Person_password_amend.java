package com.example.nenguou.youngleague.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/19.
 */

public class Person_password_amend extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_password_amend);
    }
    public void back(View v){
        onBackPressed();
    }

    public void reanmend(View view) {
        Toast.makeText(getApplicationContext(),"密码修改成功",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),Person_personal_setting.class);
        startActivity(intent);
    }
}
