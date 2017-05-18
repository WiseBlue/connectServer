package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.R;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_message extends BaseFragent {
    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_messages;
    }

    @Override
    public void initView() {

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
