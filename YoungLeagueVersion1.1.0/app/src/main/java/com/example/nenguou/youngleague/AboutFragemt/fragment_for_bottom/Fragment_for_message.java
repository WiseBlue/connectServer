package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Adapter.PassageAdapter;
import com.example.nenguou.youngleague.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_message extends BaseFragent {
    private ListView listview;
    private PassageAdapter adapter;
    private List<Map<String, Object>> items = new ArrayList<>();
    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_messages;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(), container, false);
        listview=(ListView)view.findViewById(R.id.listview2);
        return view;
    }
    @Override
    public void initView() {

        Map<String, Object> datas = new HashMap<>();
        datas.put("title","支部党员大会");
        datas.put("date","2017-05-18 10:22");
        datas.put("content","大会");
        items.add(datas);
        Map<String, Object> datas1 = new HashMap<>();
        datas1.put("title","春游动员会");
        datas1.put("date","2017-05-18 00:20");
        datas1.put("content","大会");
        items.add(datas1);
        Map<String, Object> datas2 = new HashMap<>();
        datas2.put("title","第一次党员大会");
        datas2.put("date","2017-05-17 14:10");
        datas2.put("content","OK");
        items.add(datas2);
        adapter=new PassageAdapter(getContext(),items);
        listview.setAdapter(adapter);


    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment_for_message getInstance(/*String title*/) {
        Fragment_for_message mf = new Fragment_for_message();
        //mf.title = title;
        return mf;
    }
}
