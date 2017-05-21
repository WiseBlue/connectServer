package com.example.nenguou.youngleague.AboutFragemt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nenguou.youngleague.Refresh.CanRefreshLayout;

/**
 * Created by Nenguou on 2017/5/8.
 */

public abstract class BaseFragent extends Fragment {
    protected boolean isVisible;
    public abstract int getLayoutID();

    public abstract void initView();

    public View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(), container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
            onVisible();
        }else {
            isVisible=false;
            onInVisible();
        }
    }

    protected void onVisible(){
        lazyLoad();

    }
    protected void onInVisible(){

    }
    protected abstract void lazyLoad();
}
