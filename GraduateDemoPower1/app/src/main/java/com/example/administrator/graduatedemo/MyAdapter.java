package com.example.administrator.graduatedemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class MyAdapter extends BaseAdapter implements ImageBannerFrameLayout.FrameLayoutListener {

    private Context context;
    private List<News> news;
    private LayoutInflater inflater;

    private List<String> imageUrl;
    private List<String> bannerTitle;

    private String TAG="Myadapter";


    public MyAdapter(Context context, List<News> news){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.news=news;

    }
    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        initData();
        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null){
                convertView=inflater.inflate(R.layout.imagebarnner,null);
                viewHolder.banner= (Banner) convertView.findViewById(R.id.banner);
                viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imgview1);
                viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.content= (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);


        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        News news1=news.get(position);
        viewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        viewHolder.banner.setImageLoader(new GlideImageLoader());

        viewHolder.banner.setImages(imageUrl);

        viewHolder.banner.setBannerTitles(bannerTitle);

        viewHolder.banner.setDelayTime(3000);

        viewHolder.banner.start();
        final Banner banner1=viewHolder.banner;
        viewHolder.banner.setOnBannerListener(new OnBannerListener() {

            @Override

            public void OnBannerClick(int position) {

                switch (position) {

                    case 0:
                        DetailData(banner1,0);

                        break;

                    case 1:

                        DetailData(banner1,1);


                        break;

                    case 2:

                        Uri uri3 = Uri.parse("http://blog.csdn.net/nenguou04");
                        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);

                        context.startActivity(intent3);

                        break;

                    case 3:

                        Uri uri4 = Uri.parse("https://github.com/Nenguou");

                        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);

                        context.startActivity(intent4);

                        break;

                    default:

                        break;

                }

            }

        });
            viewHolder.imageView.setImageResource(news1.imgResourceId);
            viewHolder.title.setText(news1.title);
            viewHolder.content.setText(news1.content);
        if (position==0){
            viewHolder.banner.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.title.setVisibility(View.GONE);
            viewHolder.content.setVisibility(View.GONE);
        }else {
            viewHolder.banner.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.title.setVisibility(View.VISIBLE);
            viewHolder.content.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    private void DetailData(Banner banner,int position){
        RelativeLayout relativeLayout=(RelativeLayout) banner.getChildAt(0);
        ViewPager viewPager=(ViewPager) relativeLayout.getChildAt(0);
        RelativeLayout relativeLayout1=(RelativeLayout) relativeLayout.getChildAt(1);
        ImageView imageView=(ImageView)viewPager.getChildAt(position);
        LinearLayout linearLayout=(LinearLayout) relativeLayout1.getChildAt(2);
        TextView textView=(TextView)linearLayout.getChildAt(0);
        Intent intent = new Intent(context,DetailContent.class);
        byte[] mypicture = null;
        imageView.setDrawingCacheEnabled(true);
        Bitmap map=imageView.getDrawingCache();
        mypicture=Bitmap2Bytes(map);
        intent.putExtra("picture",mypicture);
        Log.i("TAG",""+textView.getText());
        intent.putExtra("text",textView.getText());
        context.startActivity(intent);

    }
    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    public void clickImageIndex(int pos) {
        Toast.makeText(context,"我是"+pos,Toast.LENGTH_SHORT).show();
    }
    private void initView() {




    }
    private void initData() {

        //图片地址

        imageUrl = new ArrayList<>();

        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-cdd808dc420a5fce.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-08596267b2af6966.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-7588df97c05e65dd.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-978f28c337669ab0.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");





        //Title名称

        bannerTitle = new ArrayList<>();

        bannerTitle.add("Blog");

        bannerTitle.add("简书");

        bannerTitle.add("CSDN");

        bannerTitle.add("GitHub");

    }

    class ViewHolder{
        Banner banner;
        ImageView imageView;
        TextView title;
        TextView content;
    }
}
