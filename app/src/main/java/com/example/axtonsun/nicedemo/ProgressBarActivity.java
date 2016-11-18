package com.example.axtonsun.nicedemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView contentImageView;
    private ImageView overImageView;
    private float h, w;
    private Button button1,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        contentImageView = (ImageView) findViewById(R.id.content);
        overImageView = (ImageView) findViewById(R.id.over);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        /**
         * http://blog.csdn.net/nailsoul/article/details/25909313
         *
         * 在activity中可以调用View.getWidth、View.getHeight()、
         *                    View.getMeasuredWidth() 、View.getgetMeasuredHeight()来获得某个view的宽度或高度
         *但是在onCreate()、onStrart()、onResume()方法中会返回0，
         *
         * 当前activity所代表的界面还没显示出来没有添加到WindowPhone的DecorView上
         * 或要获取的view没有被添加到DecorView上
         * 或者该View的visibility属性为gone
         * 或者该view的width或height真的为0
         *
         */
        /**
         * 解决办法
         * 1.在该View事件回调里使用这时候该view已经被显示即被添加到DecorView上  如点击事件、触摸事件、焦点事件等
         * 2.在activity被显示出来时即添加到了DecorView上时获取宽和高如onWindowFocusChanged() 回调方法
         * 3.或在onResume方法最后开线程300毫秒左右后获取宽和高   因为onResume执行完后300毫秒后 界面就显示出来了
         * 第二种和第三种 需要保证获取宽高的view是在setContentView时设进去的View或它的子View
         * 4.在onCreate()或onResume()等方法中需要获取宽高时使用
         *   getViewTreeObserver().addOnGlobalLayoutListener()来添为view加回调在回调里获得宽度或者高度获取完后让view删除该回调
         * 5.在窗口焦点发生变化时获取宽高 onResume完后就会把界面渲染到窗口上 渲染完后将执行窗口焦点花生变化回调   所以onResume到 onWindowFocusChanged为把界面渲染到窗口的时间
         */

        /*
        UI事件队列会按顺序处理事件。在setContentView()被调用后
        事件队列中会包含一个要求重新layout的message
        所以任何你post到队列中的东西都会在Layout发生变化后执行
        */
        contentImageView.post(new Runnable() {
            @Override
            public void run() {
                h = contentImageView.getHeight();
                w = contentImageView.getWidth();
                Toast.makeText(ProgressBarActivity.this, "h:" + h + "w:" + w, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                ObjectAnimator
                        .ofFloat(overImageView, "translationX", w).
                        setDuration(500).start();
                break;
            case R.id.button2:
                ObjectAnimator
                        .ofFloat(overImageView, "translationX", 0).
                        setDuration(500).start();
                break;
            case R.id.button3:
                ObjectAnimator
                        .ofFloat(overImageView, "translationX", w * 0.289f).
                        setDuration(500).start();
                break;
            case R.id.button4:
                ObjectAnimator
                        .ofFloat(overImageView, "translationX",  w * 0.736f).
                        setDuration(500).start();
                break;
        }
    }
}
