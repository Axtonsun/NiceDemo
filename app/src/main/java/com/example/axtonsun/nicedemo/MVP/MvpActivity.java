package com.example.axtonsun.nicedemo.MVP;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axtonsun.nicedemo.MVP.Bean.User;
import com.example.axtonsun.nicedemo.MVP.mvp.presenter.MainPresenter;
import com.example.axtonsun.nicedemo.MVP.mvp.view.BaseView;
import com.example.axtonsun.nicedemo.R;

public class MvpActivity extends AppCompatActivity implements BaseView{
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

        mainPresenter = new MainPresenter();

        mainPresenter.attachView(this);

        button.setOnClickListener(new View.OnClickListener() {
            //当点击Button产生事件 将逻辑交给mainPresenter去处理 对应关系 V --->P
            @Override
            public void onClick(View v) {
                mainPresenter.searchUser(editText.getText().toString());
            }
        });

    }

    private void initView(){
        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在搜索中");
    }

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
