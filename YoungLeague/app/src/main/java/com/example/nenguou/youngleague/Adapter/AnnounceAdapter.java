package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nenguou.youngleague.R;

/**
 * Created by Administrator on 2017/5/10.
 */

public class AnnounceAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public AnnounceAdapter(Context context){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.content_annocement,null);
        return convertView;
    }

}
