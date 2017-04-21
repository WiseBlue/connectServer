package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyCollect extends AppCompatActivity {
    private MyOpenHelper helper;
    private ListView listview;
    //private ImageView imgview;
    private String TAG="Mainactivity";
    private Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycollect);

        listview= (ListView) findViewById(R.id.listview1);
        //imgview=(ImageView)findViewById(R.id.imageview2);
        helper=MyOpenHelper.getHelper(this);

        query();
    }

    private void query() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //column列名，selection是where —id=？ or name=？，selectionargs行条件参数
        Cursor cur = db.query("Collect", null, null, null, null, null, null);
        //cursor游标，结果集
        if (cur != null) {
            if (cur != null) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout. collect_content,
                        cur,
                        new String[]{"image","text"},//游标数据的名称，实际是Table列名字
                        new int[]{R.id.imageview6,R.id.textview6});//对应的UI微件的id


                adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Cursor cursor, int i) {
                        if(view instanceof ImageView) {
                            ImageView iv = (ImageView) view;
                            byte buff[] = cursor.getBlob(cursor.getColumnIndex("image"));
                            bitmap = BitmapFactory.decodeByteArray(buff, 0, buff.length);
                            iv.setImageBitmap(bitmap);
                            return true;
                        }
                        return false;
                    }
                });
                listview.setAdapter(adapter);

            }
        }

    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent=new Intent(MyCollect.this,MainActivity.class);
        startActivity(intent);
    }
}
