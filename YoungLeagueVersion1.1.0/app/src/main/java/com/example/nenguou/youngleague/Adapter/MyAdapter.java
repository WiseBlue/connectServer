package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nenguou.youngleague.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> items;
    private LayoutInflater inflater;
    private String TAG="MyAdapter";

    public MyAdapter(Context context, List<Map<String, Object>> items){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null){
            convertView=inflater.inflate(R.layout.content_main,null);
            viewHolder.imageView=(ImageView)convertView.findViewById(R.id.imgview1);
            viewHolder.title=(TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.ll_date_main=(LinearLayout)convertView.findViewById(R.id.ll_date_main);
            viewHolder.vedio=(TextView)viewHolder.ll_date_main.findViewById(R.id.tv_vedio);
            Log.i(TAG,viewHolder.vedio+"");
            viewHolder.fancy=(TextView)viewHolder.ll_date_main.findViewById(R.id.tv_fancy);
            viewHolder.date=(TextView)viewHolder.ll_date_main.findViewById(R.id.tv_date);
            //viewHolder.content=(TextView)convertView.findViewById(R.id.tv_content);


            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Map<String,Object> content=items.get(position);
        Glide.with(context).load(content.get("imageUrl").toString()).into(viewHolder.imageView);
        //viewHolder.imageView.setImageBitmap((Bitmap)content.get("imageUrl"));
        viewHolder.title.setText(content.get("title").toString());
        viewHolder.vedio.setText(content.get("vedio").toString());
        viewHolder.fancy.setText(content.get("fancy").toString());
        viewHolder.date.setText(stringToDate(content.get("date").toString()));
        //viewHolder.content.setText(content.get("summary").toString());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView title;
        //TextView content;
        LinearLayout ll_date_main;
        TextView vedio;
        TextView fancy;
        TextView date;
    }
    public static String stringToDate(String lo){

        long time = Long.parseLong(lo);

        Date date = new Date(time);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return sd.format(date);

    }

}
