package com.example.nenguou.youngleague;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom.Fragment_for_announcemnt;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom.Fragment_for_journal;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom.Fragment_for_message;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom.Fragment_for_person;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom.Fragment_for_recommend;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SlidingTabLayout tablayout;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private ArrayList<BaseFragent> mFagments = new ArrayList<>();
    private String[] mTitles = {"推荐", "公告", "消息", "日志", "个人"};
   /* private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect,R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select, R.mipmap.tab_more_select};*/
    //private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        initViews();
    }

    private void initViews() {
        mFagments.add(Fragment_for_recommend.getInstance());
        mFagments.add(Fragment_for_announcemnt.getInstance());
        mFagments.add(Fragment_for_message.getInstance());
        mFagments.add(Fragment_for_journal.getInstance());
        mFagments.add(Fragment_for_person.getInstance());

        /*for(int i = 0;i < mTitles.length; i++){
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }*/

       // mFagments= (Fragment)mFagments;
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        //tablayout.setTabData(mTabEntities,this,R.layout.activity_main,mFagments);


        tablayout.setViewPager(viewPager,mTitles);

    }

    private void initId() {
        tablayout = (SlidingTabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
