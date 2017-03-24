package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewpager;
    private ViewPagerIndicator mIndicator;

    private List<String> mTitles= Arrays.asList("头条","校园","就业","课堂");
    private List<VpSimpleFragment> mContents=new ArrayList<VpSimpleFragment>();
    private FragmentPagerAdapter mAdapter;
    private ListView listview1;
    private List<Map<String,Object>> data;

    public static final String TAG = "MainActivity";
    private static final String url="http://www.ziyezhirongyao.xyz/demo/json";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //listview内容显示
        contentshow();

        //setSupportActionBar(toolbar);

        //侧边栏和悬浮按钮
        floatandsidebar();
        //ViewPager
        initViews();
        initDatas();





        mViewpager.setAdapter(mAdapter);

        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,DetailContent.class);
                ImageView imageview=(ImageView)findViewById(R.id.imgview1);
                TextView textview=(TextView)findViewById(R.id.textview2);
                byte[] mypicture=null;
                imageview.setDrawingCacheEnabled(true);
                Bitmap map=imageview.getDrawingCache();
                mypicture=Bitmap2Bytes(map);
                intent.putExtra("picture",mypicture);
                intent.putExtra("text",textview.getText());
                startActivity(intent);


            }
        });
    }
    private byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    private void contentshow() {

            new Thread(){
                @Override
                public void run() {
                    try {
                        fillData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listview1=(ListView)findViewById(R.id.listview1);
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.show_content,
                new String[]{"picture","text"},
                new int[]{R.id.imgview1,R.id.textview2}
        );
        //设置simpleadapter可以显示网络图片
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
        });
        if (adapter!=null){
        listview1.setAdapter(adapter);}
    }


    private void floatandsidebar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void fillData()throws IOException {
        data = new ArrayList<Map<String, Object>>();
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
            Map<String,Object> datas=new HashMap<>();
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
                            data.add(datas);
                            data.add(datas);
                            data.add(datas);
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

    private void initDatas() {

        for (String title:mTitles) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);
            mContents.add(fragment);

            mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mContents.get(position);
                }

                @Override
                public int getCount() {
                    return mContents.size();
                }
            };

        }
    }

    private void initViews() {
        mViewpager=(ViewPager)findViewById(R.id.viewpager);
        mIndicator=(ViewPagerIndicator)findViewById(R.id.indicator);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //右上角菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //侧边栏
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
