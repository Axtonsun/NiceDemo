package com.example.axtonsun.nicedemo.MVP;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axtonsun.nicedemo.MVP.Bean.User;
import com.example.axtonsun.nicedemo.MVP.mvp.presenter.MainPresenter;
import com.example.axtonsun.nicedemo.MVP.mvp.view.BaseView;
import com.example.axtonsun.nicedemo.R;

import java.lang.reflect.Method;

public class MvpActivity extends AppCompatActivity implements BaseView{//在Activity
    /**
     * http://www.cnblogs.com/xurui1995/p/6021209.html
     */

    private Button button;
    private EditText editText;
    private TextView textView;

    private ProgressDialog dialog;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);



        button = (Button) findViewById(R.id.search_btn);
        editText = (EditText) findViewById(R.id.ed_text);
        textView = (TextView) findViewById(R.id.tv);
        initView();

        mainPresenter = new MainPresenter();//新建Presenter
        mainPresenter.attachView(this);//将持有的view赋值
        // mainPresenter = new MainPresenter(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过new Presenter 实例绑定 通过Presenter中的公开方法请求数据
                // 保留了 对 BasePresenter接口内方法的调用
                //当点击Button产生事件 将逻辑交给mainPresenter去处理 对应关系 V --->P
                mainPresenter.searchUser(editText.getText().toString());
            }
        });

    }

    private void initView(){
        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在搜索中");
    }

    /**
     * 调用数据  加载函数
     */
    @Override
    public void showProgressDialog() {
        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
        dialog.dismiss();
    }

    @Override
    public void showText(User userbean) {
        String temp = getResources().getString(R.string.user_format);
        String str = String.format(temp,userbean.getLogin(),userbean.getName(),userbean.getFollowers(),userbean.getFollowing());
        textView.setText(str);
    }

    @Override
    public void showErrorMessage(String text) {
        Toast.makeText(this, "text", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainPresenter!=null){
            mainPresenter.detachView();
        }
    }
}
