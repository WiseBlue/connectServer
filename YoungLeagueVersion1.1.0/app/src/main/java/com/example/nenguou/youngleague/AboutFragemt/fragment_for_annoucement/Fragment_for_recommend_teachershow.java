package com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Activity.DetailMain;
import com.example.nenguou.youngleague.Adapter.MyAdapter;
import com.example.nenguou.youngleague.Common.C;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.Refresh.CanRefreshLayout;
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

public class Fragment_for_recommend_teachershow extends BaseFragent implements CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener{


    private int i=0;
    private String url;
    private TextView textView;
    private List<Map<String, Object>> items = new ArrayList<>();
    private String TAG="Mainactivity";
    private MyAdapter adapter;
    private ListView listview;
    CanRefreshLayout refresh;

    @Override
    public int getLayoutID() {
        return R.layout.recommend_teachershow_layout;
    }

    @Override
    public void initView() {


    }
    public static Fragment_for_recommend_teachershow getInstance(/*String title*/) {
        Fragment_for_recommend_teachershow mf = new Fragment_for_recommend_teachershow();

        //mf.title = title;
        return mf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(), container, false);
        refresh= (CanRefreshLayout)view.findViewById(R.id.refresh);
        listview=(ListView)view.findViewById(R.id.can_content_view);
        refresh.setOnRefreshListener(this);
        refresh.setOnLoadMoreListener(this);

        refresh.setStyle(1,1);
        C.isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        C.isPrepared = true;
        if(!C.isPrepared || !isVisible) {
            return;
        }
        //textView= (TextView) findViewById(R.id.tv_content);
        //listview=(ListView)view.findViewById(R.id.listview);
        url="http://47.92.28.251:1024/article/"+i+"/2/list";
        Thread th = new Thread() {
            @Override
            public void run() {
                fillData();
            }
        };
        th.start();
        while (!th.getState().equals(Thread.State.TERMINATED)) ;
        adapter=new MyAdapter(getContext(),items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),DetailMain.class);
                intent.putExtra("title",items.get(position).get("title").toString());
                intent.putExtra("university",items.get(position).get("university").toString());
                intent.putExtra("detail",items.get(position).get("detail").toString());
                intent.putExtra("vedio",items.get(position).get("vedio").toString());
                intent.putExtra("fancy",items.get(position).get("fancy").toString());
                intent.putExtra("date",items.get(position).get("date").toString());
                startActivity(intent);
            }
        });
        C.isPrepared=false;
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
                    String title=obj.getString("title");
                    String summary=obj.getString("summary");
                    String imageUrl=obj.getString("imageUrl");
                    String vedio=obj.getString("vedioUrl");
                    String fancy=obj.getString("fancy");
                    String date=obj.getString("createdAt");
                    String university=obj.getString("university");
                    String detail=obj.getString("detail");
                    //Log.i(TAG,title);
                    //Log.i(TAG,date);
                    datas.put("id",id);
                    datas.put("title",title);
                    datas.put("summary",summary);
                    datas.put("university",university);
                    datas.put("imageUrl",imageUrl);
                    datas.put("vedio",vedio);
                    datas.put("fancy",fancy);
                    datas.put("date",date);
                    datas.put("detail",detail);
                    items.add(datas);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    } @Override
    public void onLoadMore() {
        i++;
        url="http://47.92.28.251:1024/article/"+i+"/2/list";
        refresh.post(new Runnable() {
            @Override
            public void run() {
                Thread th=new Thread(){
                    @Override
                    public void run() {
                        fillData();
                    }
                };
                th.start();
                while (!th.getState().equals(Thread.State.TERMINATED)) ;
                Log.i(TAG,"加载完成");
                int pos=listview.getCount()-1;
                adapter=new MyAdapter(getContext(),items);
                //recyclerview.setAdapter(adapter);
                listview.setAdapter(adapter);
                listview.setSelection(pos);
                refresh.loadMoreComplete();
            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        i=0;
        url="http://47.92.28.251:1024/article/"+i+"/2/list";
        refresh.post(new Runnable() {
            @Override
            public void run() {
                Thread th=new Thread(){
                    @Override
                    public void run() {
                        fillData();
                    }
                };
                th.start();
                while (!th.getState().equals(Thread.State.TERMINATED)) ;
                adapter=new MyAdapter(getContext(),items);
                //recyclerview.setAdapter(adapter);
                listview.setAdapter(adapter);
                refresh.refreshComplete();
            }
        });

    }

}
