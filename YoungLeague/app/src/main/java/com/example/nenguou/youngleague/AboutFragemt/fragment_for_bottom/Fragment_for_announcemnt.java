package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;


import android.widget.ListView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Adapter.AnnounceAdapter;
import com.example.nenguou.youngleague.R;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_announcemnt extends BaseFragent {
    private ListView listview;
    private AnnounceAdapter adapter;
    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_announcemrnt;
    }

    @Override
    public void initView() {
        listview= (ListView) view.findViewById(R.id.listview1);
        adapter=new AnnounceAdapter(getContext());
        listview.setAdapter(adapter);



    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment_for_announcemnt getInstance(/*String title*/) {
        Fragment_for_announcemnt mf = new Fragment_for_announcemnt();
        //mf.title = title;
        return mf;
    }
}
