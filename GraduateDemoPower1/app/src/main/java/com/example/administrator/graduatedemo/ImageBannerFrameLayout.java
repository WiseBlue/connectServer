package com.example.administrator.graduatedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ImageBannerFrameLayout extends FrameLayout implements ImageBarnnerViewGroup.ImageBarnnerViewGroupListener,ImageBarnnerViewGroup.ImageBarnnerLister{

    private ImageBarnnerViewGroup imageBarnnerViewGroup;
    private LinearLayout linearLayout;

    private FrameLayoutListener listener;

    public FrameLayoutListener getlistener(){
        return listener;
    }
    public void setlistener(FrameLayoutListener listener){
        this.listener=listener;
    }

    public ImageBannerFrameLayout(Context context) {
        super(context);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    public ImageBannerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    public ImageBannerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    public void addBitmaps(List<Bitmap> list){
        for (int i=0;i<list.size();i++){
            Bitmap bitmap=list.get(i);
            addBitmapToImageBarnnerViewGroup(bitmap);
            addDotToLinearLayout();
        }
    }

    private void addDotToLinearLayout() {
        ImageView iv=new ImageView(getContext());
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);
    }

    private void addBitmapToImageBarnnerViewGroup(Bitmap bitmap) {
        ImageView iv=new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(C.WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setImageBitmap(bitmap);
        imageBarnnerViewGroup.addView(iv);
    }

    private void initImageBarnnerViewGroup() {
        //初始化自定义图片轮播功能核心类
        imageBarnnerViewGroup=new ImageBarnnerViewGroup(getContext());
        LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        imageBarnnerViewGroup.setLayoutParams(lp);
        imageBarnnerViewGroup.setBarnnerViewGroupListener(this);//这里就是将listener传递给framelayout
        imageBarnnerViewGroup.setLister(this);
        addView(imageBarnnerViewGroup);
    }
    private void initDotLinearLayout(){
        //初始化我们的底部圆点布局
        linearLayout=new LinearLayout(getContext());
        LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, 40);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.RED);
        addView(linearLayout);

        LayoutParams layoutparams= (LayoutParams) linearLayout.getLayoutParams();
        layoutparams.gravity=Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutparams);
        linearLayout.setAlpha(0.5f);


    }

    @Override
    public void selectImage(int index) {
        int count=linearLayout.getChildCount();
        for (int i=0;i<count;i++){
            ImageView iv=(ImageView)linearLayout.getChildAt(i);
            if (i==index){
                iv.setImageResource(R.drawable.dot_select);

            }else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {

        listener.clickImageIndex(pos);
    }
    public interface FrameLayoutListener{
        void clickImageIndex(int pos);
    }
}
