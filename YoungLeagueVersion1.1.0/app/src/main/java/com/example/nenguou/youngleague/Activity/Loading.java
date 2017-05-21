package com.example.nenguou.youngleague.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.nenguou.youngleague.Database.MyOpenHelper;
import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/20.
 */

public class Loading extends AppCompatActivity {
    private MyOpenHelper helper;
    private String TAG="Loading_Activity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        RelativeLayout loading= (RelativeLayout) findViewById(R.id.loading_main);
        helper=MyOpenHelper.getHelper(this);
        loading.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (query()){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent1=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent1);
                }
            }
        }, 4000);
    }
    private boolean query() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //column列名，selection是where —id=？ or name=？，selectionargs行条件参数
        Cursor cur = db.query("Person", null,"access='"+"true"+"'", null, null, null, null);
        //cursor游标，结果集
        Log.i(TAG,cur+"");

            try{
                if(cur.moveToFirst()){
                    if (cur!= null && (cur.getString(cur. getColumnIndex("access"))!="false")){
                        return true;
                    }
                }
            }catch (CursorIndexOutOfBoundsException e){
                return false;
            }
        //Log.i(TAG,cur.getString(cur. getColumnIndex("access")));
        return false;


    }

}
