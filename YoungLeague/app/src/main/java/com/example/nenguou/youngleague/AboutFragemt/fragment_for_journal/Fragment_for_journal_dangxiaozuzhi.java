package com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.R;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_journal_dangxiaozuzhi extends BaseFragent {



    @Override
    public int getLayoutID() {
        return R.layout.journal_dangxiaozuhui_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment_for_journal_dangxiaozuzhi getInstance(/*String title*/) {
        Fragment_for_journal_dangxiaozuzhi mf = new Fragment_for_journal_dangxiaozuzhi();
        //mf.title = title;
        return mf;
    }
}
