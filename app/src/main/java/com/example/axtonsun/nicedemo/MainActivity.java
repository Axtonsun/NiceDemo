package com.example.axtonsun.nicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.axtonsun.nicedemo.MVP.MvpActivity;
import com.example.axtonsun.nicedemo.Palette.PaletteActivity;
import com.example.axtonsun.nicedemo.ProgressBar.ProgressBarActivity;
import com.example.axtonsun.nicedemo.SuspensionBar.RvSuspensionBar;
import com.example.axtonsun.nicedemo.TabLayoutTop.TabLayoutTopActivity;
import com.example.axtonsun.nicedemo.XituActivity.XiTuActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn1 = (Button) findViewById(R.id.main_btn1);
        mBtn2 = (Button) findViewById(R.id.main_btn2);
        mBtn3 = (Button) findViewById(R.id.main_btn3);
        mBtn4 = (Button) findViewById(R.id.main_btn4);
        mBtn5 = (Button) findViewById(R.id.main_btn5);
        mBtn6 = (Button) findViewById(R.id.main_btn6);
        mBtn6.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn1:
                Intent intent = new Intent(MainActivity.this,ProgressBarActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn2:
                Intent intent1 = new Intent(MainActivity.this, RvSuspensionBar.class);
                startActivity(intent1);
                break;
            case R.id.main_btn3:
                Intent intent2 = new Intent(MainActivity.this, MvpActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_btn4:
                Intent intent3 = new Intent(MainActivity.this, TabLayoutTopActivity.class);
                startActivity(intent3);
                break;
            case R.id.main_btn5:
                Intent intent4 = new Intent(MainActivity.this, PaletteActivity.class);
                startActivity(intent4);
                break;
            case R.id.main_btn6:
                Intent intent5 = new Intent(MainActivity.this, XiTuActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
