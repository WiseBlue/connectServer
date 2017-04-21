package com.example.administrator.graduatedemo;

/**
 * Created by Administrator on 2017/3/23.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import java.util.List;


public class MyListener implements PullToRefreshLayout.OnRefreshListener
{
    /*private List<String> items = null;
    private  MyAdapter adapter = null;

    public MyListener(List<String> items, MyAdapter adapter) {
        this.items = items;
        this.adapter = adapter;
    }*/

    private Context context;
    private List<News> news;
    private MyAdapter adapter;
    private ListView listview;
    private int i=5;

    //private List<Map<String,Object>> items;
    //private ListView listView;
    //private SimpleAdapter adapter;
    //private Map<String,Object> datas1=new HashMap<>();

    public MyListener(Context context, List<News> news,MyAdapter adapter,ListView listview){
        this.context=context;
        this.news=news;
        this.adapter=adapter;
        this.listview=listview;

    }
    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
    {
        // 下拉刷新操作
        new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 千万别忘了告诉控件刷新完毕了哦！
                //Toast.makeText(context,"刷新完成",Toast.LENGTH_SHORT).show();


                //items.add(data);
                //items.add(datas1);
                //items.add(datas1);
                    news.add(new News(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i));
                i++;


                //items.add(datas1);

                /*adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Object o, String s) {
                        //判断是否为我们要处理的对象
                        if(view instanceof ImageView && o instanceof Bitmap){
                            ImageView iv = (ImageView) view;

                            iv.setImageBitmap((Bitmap) o);
                            return true;
                        }else
                            return false;
                    }
                });*/
                listview.setAdapter(adapter);
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 2000);

    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
    {
        // 加载操作
        new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 千万别忘了告诉控件加载完毕了哦！
                /*items.add(datas1);


                adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Object o, String s) {
                        //判断是否为我们要处理的对象
                        if(view instanceof ImageView && o instanceof Bitmap){
                            ImageView iv = (ImageView) view;

                            iv.setImageBitmap((Bitmap) o);
                            return true;
                        }else
                            return false;
                    }
                });*/

                news.add(new News(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i));
                i++;
                listview.setAdapter(adapter);
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 2000);
    }

}
