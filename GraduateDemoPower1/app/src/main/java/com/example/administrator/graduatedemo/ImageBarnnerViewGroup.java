package com.example.administrator.graduatedemo;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ImageBarnnerViewGroup extends ViewGroup {

    private int children;//子视图个数
    private int childwidth;//子视图宽度
    private int childheight;//子视图高度

    private int x;//此时的x的值代表第一次按下位置横坐标，每一次移动过程前位置横坐标
    private int index;//代表的是我们每张图片的索引

    private Scroller scroller;

    //要想实现图片的单机事件的获取，采用的方法是利用一个单击变量开关进行判断
    //在用户离开屏幕的一瞬间我们去判断变量开关来判断用户的操作是点击还是移动

    private boolean isClick;//true代表点击，false代表不是点击事件

    private ImageBarnnerLister lister;

    public ImageBarnnerLister getLister(){
        return lister;
    }
    public void setLister(ImageBarnnerLister lister){
        this.lister=lister;
    }

    public interface ImageBarnnerLister{
        void clickImageIndex(int pos);//pos代表当前图片地址索引值
    }

    private ImageBarnnerViewGroupListener barnnerViewGroupListener;

    public ImageBarnnerViewGroupListener getListener(){
        return barnnerViewGroupListener;
    }

    public void setBarnnerViewGroupListener(ImageBarnnerViewGroupListener barnnerViewGroupListener){
        this.barnnerViewGroupListener=barnnerViewGroupListener;
    }
    /*
    * 要想实现图片轮播底部圆点以及底部圆点切换功能步骤思路
    * 1.我们需要自定义一个继承自framelayout的布局，利用framelayout布局的特性
    * （在同一位置放置不同的View最终显示的是最后一个View）就可以实现底部圆点的布局
    * 2.我们需要准备素材，底部圆点，我们可以利用Drawable的功能去实现一个圆点图片
    * 3.我们需要继承framelayout来自定义一个类，在该类的实现过程中，我们去加载我们
    * 刚才自定义的核心类和我们需要实现的底部圆点的布局linearlayout*/



    /*
    * 自动轮播
    * 利用Timer，TimerTask，Handler三者相结合的方式实现自动轮播
    * 我们会抽出2个方法来控制，是否启动自动轮播，我们称之为startAuto（），stopAuto（）
    * 我们在两个方法控制过程中，我们实际上希望控制的是自动开启轮播图开关
    * 那么我们需要一个变量参数来作为我们自动开启轮播图开关，我们称之为isAuto，true为开，false为关*/
    private boolean isAuto=true;//默认情况开启
    private Timer timer=new Timer();
    private TimerTask task;
    private android.os.Handler autoHandler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://我们需要图片的自动轮播
                    if (++index>=children){//说明此时是最后一张图，让他从第一张开始移动
                        index=0;
                    }
                    scrollTo(childwidth*index,0);

                    barnnerViewGroupListener.selectImage(index);
                    break;
            }
        }
    };

    private void startAuto(){
        isAuto=true;
    }

    private void stopAuto(){
        isAuto=false;
    }

    public ImageBarnnerViewGroup(Context context) {
        super(context);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObj();
    }

    private void initObj() {
        scroller=new Scroller(getContext());

        task=new TimerTask() {
            @Override
            public void run() {
                if (isAuto){//说明开启了轮播图
                    autoHandler.sendEmptyMessage(0);
                }
            }
        };

        timer.schedule(task,100,2500);
    }

    /*@Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }*/

    /*
    *我们在自定义ViewGroup中，我们必须要实现的方法有：测量-》布局-》绘制
    * 那么对于来说就是：onMeasure（）
    * 我们对于绘制来说，因为我们是自定义的viewgroup 容器，针对容器的绘制，
    * 其实就是容器内的子控件的绘制过程，那么我们只需要调用系统自带的绘制即可，
    * 也就是对于viewgroup绘制过程我们不需要再重写该方法调用系统自带的即可
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*
        * 由于我们要实现一个viewgroup容器，那么我们应该需要知道该容器中的
        * 所有子视图，我们想要测量我们的viewgroup的宽度和高度，那么我们必须要先去测量
        * 子视图的宽度和高度之和，才能知道我们的viewgroup的宽度和高度是多少*/

        //1.求出子视图的个数
        children=getChildCount();//我们就可以知道子视图个数
        if(0==children){
            setMeasuredDimension(0,0);
        }else {
            //2.测量子视图的宽度和高度
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //此时我们以第一个子视图为基准，也就是说我们的viewgroup的高度就是
            //我们第一个子视图的高度，宽度是我们第一个子视图的宽度*子视图的个数
            View view=getChildAt(0);//因为此时第一个视图一定存在

            //3.根据子视图的宽度和高度，来求出该viewgroup的宽度和高度
            childheight=view.getMeasuredHeight();
            childwidth=view.getMeasuredWidth();
            int width=view.getMeasuredWidth()*children;
            setMeasuredDimension(width,childheight);

        }

    }
    /*
    * 事件的传递过程中的调用方法，我们需要调用容器的拦截方法 onInterceptTouchEvent
    * 针对于该方法我们可以理解为如果说该方法的返回值为true的时候，那么我们 自定义的view
    * group就会处理此次拦截事件，如果说返回值为false的时候，那么我们自定义的viewgroup
    * 容器将不会接收此次事件的处理过程，将会继续向下传递该事件
    * 针对我们自定义的viewgroup我们当然希望我们的viewgroup容器处理接受事件那我们返回值为true
    * 如果返回true的话，真正处理该事件的方法是onTouchEvent
    * */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    /*
    * 用2种方式来实现轮播图的手动轮播
    * 1.利用scrollTo，scrollBy完成轮播图的手动轮播
    * 2.利用scroller 对象 完成轮播图的手动轮播
    *
    * 第一：我们在滑动屏幕图片的过程中，其实就是我们自定义viewgroup的子视图的移动过程
    * 那么我们只需要滑动之前的横坐标和滑动之后的横坐标，此时我们就可以求出我们此次过程中
    * 移动的距离，我们再利用scrollBy方法实现图片的滑动，所以说我们需要由两个值是我们需要
    * 求出的，移动之前移动之后的横坐标值
    *
    * 第二：在我们第一次按下的一瞬间，此时的移动之前和移动之后的值是相等的，也就是我们按下
    * 一瞬间此时横坐标的值
    *
    * 第三:我们在不断滑动过程中，是会不断调用我们的Action-move方法，那么此时我们就应该将
    * 移动之前的值和移动之后的值保存下来，以便我们能够算出滑动的距离
    *
    * 第四：在我们抬起的那一瞬间，我们需要计算出我们需要滑动到哪张图片上的位置
    *
    * 此时我们需要求得将要滑动到的那张图片的索引值
    * 我们当前viewfroup的滑动位置+我们的每一张图片的1/2/我们的们一张图片宽度值
    *
    * 此时我们就可以利用scrollTo方法去滑动到该图片的位置上*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        stopAuto();
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN://表示的是用户按下德一瞬间

                isClick=true;
                x=(int)event.getX();
                break;
            case MotionEvent.ACTION_MOVE://表示的是用户按下之后再屏幕上移动

                int moveX=(int)event.getX();
                int distance=moveX-x;
                scrollBy(-distance,0);
                x=moveX;
                isClick=false;
                break;
            case MotionEvent.ACTION_UP://表示的是用户停止触摸

                int scrollX=getScrollX();
                index=(scrollX+childwidth/2)/childwidth;

                if (index<0){//说明了此事已经滑动到了最左边第一张图片
                    index=0;
                }else if (index>children-1){
                    index=children-1;
                }
                if (isClick){
                    lister.clickImageIndex(index);
                }else{
                    scrollTo(index*childwidth,0);
                    barnnerViewGroupListener.selectImage(index);
                }

                startAuto();
                break;
        }
        return true;//返回true的目的是告诉我们该ViewGroup的父View，我们已经处理好了该事件
    }

    //继承viewgroup必须要实现布局onlayout方法
    /*
    * changed viewgroup发生改变为true，没有改变为false
    * l=left,t=top,r=right,b=bottom
    * */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if(changed){
            int leftMagrin=0;//左边界坐标
            for (int i=0;i<children;i++){
                View view=getChildAt(i);

                view.layout(leftMagrin, 0, leftMagrin+childwidth, childheight);
                leftMagrin+=childwidth;
            }
        }
    }
   public interface ImageBarnnerViewGroupListener{
        void selectImage(int index);
    }
}
