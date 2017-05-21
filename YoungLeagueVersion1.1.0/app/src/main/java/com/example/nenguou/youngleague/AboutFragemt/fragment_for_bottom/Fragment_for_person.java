package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Activity.Person_personal_detail;
import com.example.nenguou.youngleague.Activity.Person_personal_setting;
import com.example.nenguou.youngleague.R;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_person extends BaseFragent {
    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_person;
    }

    @Override
    public void initView() {
        RelativeLayout person_setting= (RelativeLayout) view.findViewById(R.id.person_setting);
        person_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Person_personal_setting.class);
                startActivity(intent);
            }
        });
        RelativeLayout person_detail= (RelativeLayout) view.findViewById(R.id.person_personal_detail);
        person_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Person_personal_detail.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment_for_person getInstance(/*String title*/) {
        Fragment_for_person mf = new Fragment_for_person();
        //mf.title = title;
        return mf;
    }
}
