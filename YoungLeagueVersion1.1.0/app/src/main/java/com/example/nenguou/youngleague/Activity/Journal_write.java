package com.example.nenguou.youngleague.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nenguou.youngleague.R;

import java.io.File;

/**
 * Created by Administrator on 2017/5/20.
 */

public class Journal_write extends AppCompatActivity {
    private ImageView add;
    private ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_write);
        add= (ImageView) findViewById(R.id.journal_write_add);
        back= (ImageButton) findViewById(R.id.journal_write_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void write(View view) {

        //调用相册
        /*Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "camera.jpg")));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        startActivityForResult(intent, 10);*/
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data",true);
        startActivityForResult(intent, 11);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            this.add.setImageDrawable(Drawable.createFromPath(new File(
                    Environment.getExternalStorageDirectory(), "camera.jpg")
                    .getAbsolutePath()));
            System.out.println("data-->"+data);
        }else if (requestCode == 11 && resultCode ==Activity.RESULT_OK) {
            System.out.println("data2-->"+data);
        }
    }

    public void add_picture_finish(View view) {
        onBackPressed();
    }
}
