package com.example.axtonsun.nicedemo.SuspensionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.axtonsun.nicedemo.R;

public class RvSuspensionBar extends AppCompatActivity {

    private RecyclerView mFeedList;
    private RelativeLayout mSuspensionBar;
    private TextView mSuspensionTv;
    private ImageView mSuspensionIv;
    private int mCurrentPosition = 0;

    private int mSuspensionHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rv_suspension_bar);
        mSuspensionBar = (RelativeLayout) findViewById(R.id.suspension_bar);
        mSuspensionTv = (TextView) findViewById(R.id.tv_nickname);
        mSuspensionIv = (ImageView) findViewById(R.id.iv_avatar);



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final SuspensionAdapter adapter = new SuspensionAdapter();

        mFeedList = (RecyclerView) findViewById(R.id.feed_list);
        mFeedList.setLayoutManager(linearLayoutManager);
        mFeedList.setAdapter(adapter);
        mFeedList.setHasFixedSize(true);

        mFeedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {//滚动状态变化时回调(状态)  newState:目前的状态
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();//获得RelavtiveLayout的高度 155
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {//滚动时回调(过程) dx: 水平滚动距离 dy:垂直滚动距离
                super.onScrolled(recyclerView, dx, dy);
                //dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                //dy < 0 时为手指向下滚动,列表滚动显示上面的内容
                Log.e("AAA", "onScrolled: " + dx + "\\" + dy );

                //getChildCount() 当前可见的item个数
                Log.e("SSSS", "onScrolled: "+  linearLayoutManager.getChildCount()+ "||"+ linearLayoutManager.findFirstVisibleItemPosition()+ "||" + linearLayoutManager.getItemCount());

                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);//获取指定位置的Item View
                //View.getY()获取到的值为 相对于父视图而言的上边缘的距离  ==》getTop + getTranslationY

                //view.getX 相当于 view距离父容器左边缘的距离 ==》 getleft+getTranslationX(view的偏移量)
                if (view != null) {
                    if (view.getTop() <= mSuspensionHeight) { //view得到的高 小于等于 获取 155
                        mSuspensionBar.setY(-(mSuspensionHeight - view.getTop())); //    给Bar设置高度 ( - 155-(很大的正数) )  取相反数 是因为 y轴正方向向下
                    } else {
                        mSuspensionBar.setY(0);//setY ==> setTranslation(y-getTop)  setTranslationY 在竖直方向上将该控件从它本来的位置偏移i个像素
                    }
                }
                //悬浮条显示的信息是来自第一个可见View的，而其下方的View正是第二个列表项，我们可以获取到它的top值。
                //mCurrentPosition为悬浮条信息来自的那个列表项在RecyclerView的位置
                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {//获取第一个可见Item的位置
                    //linearLayoutManager.findFirstCompletelyVisibleItemPosition()//获取第一个完全可见的Item位置
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mSuspensionBar.setY(0);

                    updateSuspensionBar();
                }
            }
        });
        // updateSuspensionBar();
    }

    private void updateSuspensionBar() {
        Glide.with(RvSuspensionBar.this)
                .load(getIamgeResId(mCurrentPosition))
                .into(mSuspensionIv);

        mSuspensionTv.setText("AxtonSun " + mCurrentPosition);
    }

    private int getIamgeResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.iamge1;
            case 1:
                return R.drawable.iamge2;
            case 2:
                return R.drawable.iamge3;
            case 3:
                return R.drawable.iamge4;
        }
        return 0;
    }
}
