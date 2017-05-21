package com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Adapter.JournalAdapter;
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

public class Fragment_for_journal_dangxiaozuzhi extends BaseFragent implements CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener{


    private int i=0;
    private String url;
    private TextView textView;
    private List<Map<String, Object>> items = new ArrayList<>();
    private String TAG="Mainactivity";
    private JournalAdapter adapter;
    private ListView listview;
    private ImageView imageview;
    private Handler handler;
    CanRefreshLayout refresh;

    @Override
    public int getLayoutID() {
        return R.layout.journal_dangxiaozuhui_layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(), container, false);
        refresh= (CanRefreshLayout)view.findViewById(R.id.refresh);
        listview=(ListView)view.findViewById(R.id.can_content_view);
        refresh.setOnRefreshListener(this);
        refresh.setOnLoadMoreListener(this);

        refresh.setStyle(1,1);
        Log.i(TAG,getContext()+"222222");
        //lazyLoad();
        return view;
    }
    @Override
    public void initView() {

        //C.isPrepared=true;
        url="http://47.92.28.251:1024/journal/"+i+"/3/list?user_id=9965029";
        Thread th = new Thread() {
            @Override
            public void run() {
                fillData();
            }
        };
        th.start();
        while (!th.getState().equals(Thread.State.TERMINATED)) ;
        Log.i(TAG,getContext()+"0000000");
        adapter=new JournalAdapter(getContext(),items);
        listview.setAdapter(adapter);
        Log.i(TAG,getContext()+"1111111");
    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment_for_journal_dangxiaozuzhi getInstance(/*String title*/) {
        Fragment_for_journal_dangxiaozuzhi mf = new Fragment_for_journal_dangxiaozuzhi();
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
                    String title=obj.getString("title");
                    String date=obj.getString("createdAt");
                    String content=obj.getString("content");
                    //Log.i(TAG,title);
                    //Log.i(TAG,date);
                    datas.put("title",title);
                    datas.put("date",date);
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

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
