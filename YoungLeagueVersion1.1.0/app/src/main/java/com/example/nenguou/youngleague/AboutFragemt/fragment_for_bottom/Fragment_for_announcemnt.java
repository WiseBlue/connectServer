package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Activity.Detail_announce;
import com.example.nenguou.youngleague.Adapter.AnnounceAdapter;
import com.example.nenguou.youngleague.Common.C;
import com.example.nenguou.youngleague.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_announcemnt extends BaseFragent {
    int i=0;
    String url;
    private ListView listview;
    private AnnounceAdapter adapter;
    private List<Map<String, Object>> items = new ArrayList<>();
    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_announcemrnt;
    }

    @Override
    public void initView() {
        listview= (ListView) view.findViewById(R.id.listview1);

        C.isPrepared = true;


    }

    @Override
    protected void lazyLoad() {

        if(!C.isPrepared || !isVisible) {
            return;
        }
        url="http://47.92.28.251:1024/notice/"+i+"/list";
        Thread th = new Thread() {
            @Override
            public void run() {
                fillData();
            }
        };
        th.start();
        while (!th.getState().equals(Thread.State.TERMINATED)) ;
        adapter=new AnnounceAdapter(getContext(),items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),Detail_announce.class);
                intent.putExtra("title",items.get(position).get("title").toString());
                intent.putExtra("author",items.get(position).get("author").toString());
                intent.putExtra("content",items.get(position).get("content").toString());
                startActivity(intent);
            }
        });
        C.isPrepared=false;

    }

    public static Fragment_for_announcemnt getInstance(/*String title*/) {
        Fragment_for_announcemnt mf = new Fragment_for_announcemnt();
        //mf.title = title;
        return mf;
    }
    private void fillData(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()){

                String jsonData=null;
                jsonData=response.body().string();
                JSONArray article=new JSONArray(jsonData);
                for (int index=0;index<article.length();index++){
                    Map<String, Object> datas = new HashMap<>();
                    JSONObject obj=article.getJSONObject(index);
                    String id=obj.getString("id");
                    String author=obj.getString("author");
                    String title=obj.getString("title");
                    String content=obj.getString("content");
                    //Log.i(TAG,title);
                    //Log.i(TAG,date);
                    datas.put("id",id);
                    datas.put("title",title);
                    datas.put("author",author);
                    datas.put("content",content);
                    items.add(datas);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
