package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal.Fragment_for_journal_all;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal.Fragment_for_journal_dangke;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal.Fragment_for_journal_dangxiaozuzhi;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal.Fragment_for_journal_dangyuandahui;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal.Fragment_for_journal_zhiweihui;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal.Fragment_for_journal_zuzhishenghuo;
import com.example.nenguou.youngleague.Activity.Journal_write;
import com.example.nenguou.youngleague.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_journal extends BaseFragent {
    SlidingTabLayout tablayout;
    ViewPager viewPager;
    private ImageView write;
    private ArrayList<BaseFragent> mFagments = new ArrayList<>();
    private String[] mTitles = {"全部", "党员大会", "支委会", "党小组会", "党课","组织生活"};

    private MyPagerAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFagments.add(Fragment_for_journal_all.getInstance());
        mFagments.add(Fragment_for_journal_dangyuandahui.getInstance());
        mFagments.add(Fragment_for_journal_zhiweihui.getInstance());
        mFagments.add(Fragment_for_journal_dangxiaozuzhi.getInstance());
        mFagments.add(Fragment_for_journal_dangke.getInstance());
        mFagments.add(Fragment_for_journal_zuzhishenghuo.getInstance());


        adapter = new MyPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        tablayout.setViewPager(viewPager,mTitles);

    }
    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_journal;
    }

    @Override
    public void initView() {
        tablayout = (SlidingTabLayout) view.findViewById(R.id.tablayout3);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager3);
        write= (ImageView) view.findViewById(R.id.journal_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Journal_write.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void lazyLoad() {

    }


    public static Fragment_for_journal getInstance(/*String title*/) {
        Fragment_for_journal mf = new Fragment_for_journal();
        //mf.title = title;
        return mf;
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
