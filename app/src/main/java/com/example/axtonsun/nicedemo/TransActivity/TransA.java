package com.example.axtonsun.nicedemo.TransActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

import com.example.axtonsun.nicedemo.R;

/**
 * Created by AxtonSun on 2016/12/7.
 */

public class TransA extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transa_activity);
    }

    // 设置不同动画效果
    public void explode(View view) {
        intent = new Intent(this, TransB.class);
        intent.putExtra("flag", 0);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this)
                        .toBundle());
    }
    // 设置不同动画效果
    public void slide(View view) {
        intent = new Intent(this, TransB.class);
        intent.putExtra("flag", 1);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this)
                        .toBundle());
    }
    // 设置不同动画效果
    public void fade(View view) {
        intent = new Intent(this, TransB.class);
        intent.putExtra("flag", 2);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this)
                        .toBundle());
    }
    // 设置不同动画效果
    public void share(View view) {
        View fab = findViewById(R.id.fab_button);
        intent = new Intent(this, TransB.class);
        intent.putExtra("flag", 3);
        // 创建单个共享元素
//        startActivity(intent,
//                ActivityOptions.makeSceneTransitionAnimation(
//                        this, view, "share").toBundle());
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        // 创建多个共享元素
                        Pair.create(view, "share"),
                        Pair.create(fab, "fab")).toBundle());
    }
}
