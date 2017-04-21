package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.znq.zbarcode.CaptureActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewpager;
    private FragmentPagerAdapter adapter;
    private SimpleAdapter adapter1;

    private List<Fragment> datas;
    private List<Map<String,String>> listdata;
    private Map<String,String> listdatas;

    private TextView textview1,textview2,textview3,textview4;
    private ListView listview2;
    private ImageView tabline;
    private int screen1_4;

    private int mCurrentPageIndex;

    private String TAG="Mainactivity";

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        C.WIDTH=dm.widthPixels;


        initTabLine();
        initView();
        //setSupportActionBar(toolbar);


        //侧边栏和悬浮按钮
        floatandsidebar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    private void initTabLine() {

        tabline= (ImageView) findViewById(R.id.tabline);
        Display display=getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics=new DisplayMetrics();
        display.getMetrics(outMetrics);
        screen1_4=outMetrics.widthPixels/4;
        ViewGroup.LayoutParams lp=tabline.getLayoutParams();
        lp.width=screen1_4;
        tabline.setLayoutParams(lp);
    }
    private void initView() {
        viewpager= (ViewPager) findViewById(R.id.viewpager01);
        textview1= (TextView) findViewById(R.id.tv_title);
        textview2= (TextView) findViewById(R.id.tv_campus);
        textview3= (TextView) findViewById(R.id.tv_career);
        textview4= (TextView) findViewById(R.id.tv_class);



        datas=new ArrayList<Fragment>();

        TitleMainTabFragment tab01=new TitleMainTabFragment();
        CampusMainTabFragment tab02=new CampusMainTabFragment();
        CareerMainTabFragment tab03=new CareerMainTabFragment();
        ClassMainTabFragment tab04=new ClassMainTabFragment();

        datas.add(tab01);
        datas.add(tab02);
        datas.add(tab03);
        datas.add(tab04);

        adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return datas.get(position);
            }

            @Override
            public int getCount() {

                return datas.size();
            }
        };
        viewpager.setAdapter(adapter);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                /*Log.e("TAG", position + " , " + positionOffset + " , "
                        + positionOffsetPixels);*/
                //Log.e("TAG", mCurrentPageIndex+","+position);

                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) tabline
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (positionOffset * screen1_4 + mCurrentPageIndex
                            * screen1_4);
                } else if (mCurrentPageIndex == 1 && position == 0)// 1->0
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + (positionOffset - 1)
                            * screen1_4);
                } else if (mCurrentPageIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + positionOffset
                            * screen1_4);
                } else if (mCurrentPageIndex==2 && position ==2)//2->3
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + positionOffset
                            * screen1_4);
                }else if (mCurrentPageIndex==3 && position ==3)//3->4
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + positionOffset
                            * screen1_4);
                } else if (mCurrentPageIndex == 4 && position == 3)// 4->3
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + (positionOffset - 1)
                            * screen1_4);
                }else if (mCurrentPageIndex == 3 && position == 0)// 3->2
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + (positionOffset - 1)
                            * screen1_4);
                }else if (mCurrentPageIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * screen1_4 + ( positionOffset-1)
                            * screen1_4);
                }
                tabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                //Toast.makeText(MainActivity.this,"我是"+position,Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        textview1.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case 1:
                        textview2.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case 2:
                        textview3.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case 3:
                        textview4.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                }
                mCurrentPageIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void resetTextView(){
        textview1.setTextColor(Color.parseColor("#607d8b"));
        textview2.setTextColor(Color.parseColor("#607d8b"));
        textview3.setTextColor(Color.parseColor("#607d8b"));
        textview4.setTextColor(Color.parseColor("#607d8b"));
    }








    private void floatandsidebar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
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
            Intent intent1 = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(intent1, 11);


        } else if (id == R.id.nav_gallery) {
            Intent intent2=new Intent(MainActivity.this,MyCollect.class);
            startActivity(intent2);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent intent4=new Intent(MainActivity.this,About.class);
            startActivity(intent4);

        } else if (id == R.id.nav_send) {

            Intent intent3=new Intent(MainActivity.this,FeedBack.class);
            startActivity(intent3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            String result = data.getStringExtra(CaptureActivity.EXTRA_STRING);
            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
        }
    }*/
}
