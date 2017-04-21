package com.example.administrator.graduatedemo;

/**
 * Created by Administrator on 2017/4/14.
 */

public class News {
    public int imgResourceId;
    public String title;
    public String content;

    public News( int imgResourceId,
                String title, String content){
        this.imgResourceId=imgResourceId;
        this.title=title;
        this.content=content;
    }
}
