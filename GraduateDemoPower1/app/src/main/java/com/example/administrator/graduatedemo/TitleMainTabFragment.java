package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */

public class TitleMainTabFragment extends android.support.v4.app.Fragment {

    //private ListView content;
    //private List<String> list;
    //private MyAdapter adapter;

    private ListView listview1;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn = true;
    private List<Map<String, Object>> items = new ArrayList<>();
    List<Bitmap> list=new ArrayList<>();
    List<News> news=new ArrayList<>();

    public static final String TAG = "lalala";
    private static final String url = "http://www.ziyezhirongyao.xyz/demo/json";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //private SimpleAdapter adapter;

    private MyAdapter adapter;

    private int[] ids=new int[]{
            R.drawable.banner,
            R.drawable.banner1,
            R.drawable.banner2
    };
    Map<String, Object> datas1 = new HashMap<>();
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        list=getList();
        adapter=new MyAdapter(context,list);
    }*/

    /*private List<String> getList() {
        List<String> list=new ArrayList<>();
        String content1="最炫民族风";
        list.add(content1);
        String content2="存在";
        list.add(content2);
        String content3="怒放的生命";
        list.add(content3);

        return list;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view= inflater.inflate(R.layout.tab01, container, false);
            //inflater = getLayoutInflater();


            listview1 = (ListView) view.findViewById(R.id.content_view);





        for (int i=0;i<ids.length;i++){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),ids[i]);
            list.add(bitmap);
        }

            ptrl = ((PullToRefreshLayout) view.findViewById(R.id.refresh_view));

            //listview内容显示
            contentshow();



            ptrl.setOnRefreshListener(new MyListener(getContext(),news,adapter,listview1));
            //content= (ListView) view.findViewById(R.id.lv_title);
            //content.setAdapter(adapter);

            listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getContext(), DetailContent.class);

                    RelativeLayout relativeLayout=(RelativeLayout)listview1.getChildAt(i);
                    Log.i(TAG,relativeLayout+"");
                    //ImageView imageview=(ImageView)relativeLayout.findViewById(R.id.imgview1);
                    TextView textview=(TextView)relativeLayout.findViewById(R.id.tv_title);
                    ImageView imageview=(ImageView)relativeLayout.findViewById(R.id.imgview1);
                    //Log.i(TAG,"我是"+textview.getText());
                    byte[] mypicture = null;
                /*imageview.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                imageview.layout(0, 0, imageview.getMeasuredWidth(), imageview.getMeasuredHeight());
                imageview.buildDrawingCache();*/
                    imageview.setDrawingCacheEnabled(true);

                    Bitmap map=imageview.getDrawingCache();
                    mypicture=Bitmap2Bytes(map);
                    intent.putExtra("picture",mypicture);
                    intent.putExtra("text", textview.getText());
                    //Log.i(TAG, "我是" + textview.getText());

                    startActivity(intent);


                }
            });
            initListView();

            ptrl.autoRefresh();
            initlistener();
            return view;


    }
    private void initlistener() {
        Banner banner=(Banner) listview1.getChildAt(0);
        Log.i(TAG,listview1+"");
        Log.i(TAG,"获取到的布局"+listview1.getChildAt(0));

    }

    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    private void contentshow() {

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    fillData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
        while (!th.getState().equals(Thread.State.TERMINATED)) ;

        /*adapter = new SimpleAdapter(getContext(), items, R.layout.show_content,
                new String[]{"picture", "text"},
                new int[]{R.id.imgview1, R.id.textview2}
        );*/
        adapter=new MyAdapter(getContext(),news);
        //设置simpleadapter可以显示网络图片
        /*adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object o, String s) {
                //判断是否为我们要处理的对象
                if (view instanceof ImageView && o instanceof Bitmap) {
                    ImageView iv = (ImageView) view;

                    iv.setImageBitmap((Bitmap) o);
                    return true;
                } else
                    return false;
            }
        });*/
        /*if (adapter!=null){
        listview1.setAdapter(adapter);}*/
    }
    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 第一次进入自动刷新
        if (isFirstIn) {
            ptrl.autoRefresh();
            isFirstIn = false;
        }
    }*/
    private void fillData() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = "{\n" +
                "\"username\": \"xiaoli\",\n" +
                "\"password\": \"xiaoli12345\"\n" +
                "}";

        RequestBody requestBody = RequestBody.create(JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        // Request request = new Request.Builder().url(PATH).build();

        Response response = null;
        response = client.newCall(request).execute();
        //Log.i(TAG,"6666666666666666666666");
        if (response.isSuccessful()) {

            //Log.i(TAG,"999999999999999999");
            //Log.i(TAG,response.body().string());
            Map<String, Object> datas = new HashMap<>();
            String jsonData = null;
            try {

                jsonData = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject data1 = null;
            try {
                //Log.i(TAG,"777777777777777777");
                data1 = new JSONObject(jsonData);
                String username = data1.getString("username");
                String password = data1.getString("password");
                String picture = data1.getString("photo");
                OkHttpClient client1 = new OkHttpClient();
                String url1 = picture;
                //Log.i(TAG,"88888888888888888888888");
                try {
                    Request request1 = new Request.Builder().url(url1).build();
                    Response response1 = client1.newCall(request1).execute();
                    InputStream is = response1.body().byteStream();
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    datas.put("picture", bm);
                    datas.put("text", username + password);
                    //items.add(datas);

                    datas1 = datas;
                    //items.add(datas);
                    //items.add(datas);
                    //Log.i(TAG,"9999999999999999");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    private void initListView() {
        //Log.i(TAG,"我是"+items);


        news.add(new News(R.mipmap.ic_launcher,"我是标题"+0,"我是内容"+0));
        for (int i=1;i<5;i++){
            news.add(new News(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i));

        }

        listview1.setAdapter(adapter);

    }
}
