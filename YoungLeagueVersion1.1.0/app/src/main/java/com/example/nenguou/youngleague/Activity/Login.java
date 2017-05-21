package com.example.nenguou.youngleague.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nenguou.youngleague.Database.MyOpenHelper;
import com.example.nenguou.youngleague.R;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/19.
 */

public class Login extends AppCompatActivity {
    private EditText editText_username,editText_password;
    private SharedPreferences mPreferences1;
    private String username,password;
    private String user_id;
    private MyOpenHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_username=(EditText) findViewById(R.id.EditText3);
        editText_password=(EditText) findViewById(R.id.EditText4);

        helper=MyOpenHelper.getHelper(this);




        //mPreferences1=getSharedPreferences("test",CONTEXT_IGNORE_SECURITY);
    }


    public void register(View view) {

    }

    public void entry(View view) {

        username = editText_username.getText().toString().trim();
        password = editText_password.getText().toString().trim();

        if(editText_username.getText().length()==0){
            Toast.makeText(Login.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        }
        if(editText_password.getText().length()==0){
            Toast.makeText(Login.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
        if((editText_username.getText().length()!=0)&&(editText_password.getText().length()!=0)){
            Thread th = new Thread(){
                @Override
                public void run() {
                    OkHttpClient client = new OkHttpClient();
                    FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();

                    formEncodingBuilder.add("username", username);
                    formEncodingBuilder.add("password", password);

                    RequestBody formBody = formEncodingBuilder.build();
                    final Request request = new Request
                            .Builder()
                            .addHeader("Authorization", "Basic YXBwOjEyMzQ1Ng==")
                            .url("http://47.92.28.251:1024/oauth/token?grant_type=password")
                            .post(formBody)
                            .build();
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(response.isSuccessful()){
                        try {
                            String jsonData=response.body().string();
                            try {
                                JSONObject json=new JSONObject(jsonData);
                                String access_token=json.getString("access_token");
                                user_id=json.getString("user_id");
                                OkHttpClient client1=new OkHttpClient();
                                Request request1=new Request
                                        .Builder()
                                        .url("http://47.92.28.251:1024/user/selfinfo?access_token="+access_token+"&user_id="+user_id)
                                        .build();
                                Response response1=client1.newCall(request1).execute();
                                if (response1.isSuccessful()){
                                    SQLiteDatabase db=helper.getReadableDatabase();
                                    ContentValues values=new ContentValues();
                                    values.put("userId",user_id);
                                    values.put("access","true");
                                    long rowId=db.insert("Person",null,values);
                                    if(rowId!=-1){
                                        //Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                                    }
                                    db.close();
                                    Intent intent=new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }else {
                        //Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            };
            th.start();


        }
    }
}
