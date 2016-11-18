package com.example.axtonsun.nicedemo.MVP.mvp.presenter;

import android.text.TextUtils;

import com.example.axtonsun.nicedemo.MVP.Bean.User;
import com.example.axtonsun.nicedemo.MVP.mvp.model.MainModel;
import com.example.axtonsun.nicedemo.MVP.mvp.view.BaseView;

import rx.Subscriber;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public class MainPresenter implements BasePresenter{

    // MainPresenter 使用了 Model 的方法  即 P ---> M

    private BaseView mMainView;
    private MainModel mModle;

    public MainPresenter() {
        mModle = new MainModel();
    }

    @Override
    public void attachView(BaseView view) {
        mMainView = view;
    }

    @Override
    public void detachView() {
        mMainView = null;
    }

    @Override
    public void searchUser(String loginName) {
        if (TextUtils.isEmpty(loginName.trim())) {
            mMainView.showErrorMessage("请输入登陆名");
            return;
        }
        if (mModle!=null){
            //用RxJava的观察者观察结果   再去调用View的方法刷新界面 即  P--->V
            mModle.getUser(new Subscriber<User>() {
                @Override
                public void onStart() {//先显示对话框
                    super.onStart();
                    mMainView.showProgressDialog();
                }

                @Override
                public void onCompleted() {//请求结束 对话框消失
                    mMainView.hideProgressDialog();
                }

                @Override
                public void onError(Throwable e) {//error时
                    mMainView.showErrorMessage("搜素失败");
                }

                @Override
                public void onNext(User user) {
                    mMainView.showText(user);
                }
            },loginName);
        }
    }
}
