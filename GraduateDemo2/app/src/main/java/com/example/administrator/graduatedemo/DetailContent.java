package com.example.administrator.graduatedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by Administrator on 2017/2/26.
 */

public class DetailContent extends AppCompatActivity {
    ImageView imageview1;
    TextView textview2;
    Bitmap bitmap;
    Tencent mTencent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_content);

        mTencent = Tencent.createInstance("1106053596", this.getApplicationContext());

        imageview1 = (ImageView) findViewById(R.id.imgview1);
        textview2 = (TextView) findViewById(R.id.textview2);

        Intent intent = getIntent();

        Toast.makeText(DetailContent.this, "我接收到图片", Toast.LENGTH_LONG).show();
        //Toast.makeText(DetailContent.this,"我是"+bm,Toast.LENGTH_LONG).show();
        String text = intent.getStringExtra("text");
        byte buff[] = intent.getByteArrayExtra("picture");
        bitmap = BitmapFactory.decodeByteArray(buff, 0, buff.length);
        imageview1.setImageBitmap(bitmap);
        textview2.setText(text);


    }

    public void share(View view) {
  /*      Bundle bundle = new Bundle();
//这条分享消息被好友点击后的跳转URL。
        bundle.putString("url", "http://www.liuyinxin.com/images/default.jpg");
//分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString("title", "我在测试");
//分享的图片URL
        bundle.putString("image_url",
                "http://www.liuyinxin.com/images/default.jpg");
//分享的消息摘要，最长50个字
        bundle.putString("summary", "测试");
//手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
        bundle.putString("appname", "??我在测试");
//标识该消息的来源应用，值为应用名称+AppId。
   bundle.putString("app_source", "星期几" + 1106053596);
   mTencent.shareToQQ(this, bundle, listener);*/

        ShareListener myListener = new ShareListener();

        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "测试");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "正在测试");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.liuyinxin.com/images/default.jpg");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://www.liuyinxin.com/images/default.jpg");
        mTencent.shareToQQ(DetailContent.this, params, myListener);

    }


    private class ShareListener implements IUiListener {

        @Override
        public void onCancel() {
            // TODO Auto-generated method stub
            Toast.makeText(DetailContent.this,"取消分享",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onComplete(Object arg0) {
            // TODO Auto-generated method stub
            Toast.makeText(DetailContent.this,"分享成功",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(UiError arg0) {
            // TODO Auto-generated method stub
            Toast.makeText(DetailContent.this,"分享出错",Toast.LENGTH_LONG).show();
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent)
            mTencent.onActivityResult(requestCode, resultCode, data);
    }

}
