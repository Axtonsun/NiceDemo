package com.example.axtonsun.nicedemo.MVP.mvp.presenter;

import android.text.TextUtils;

import com.example.axtonsun.nicedemo.MVP.Bean.User;
import com.example.axtonsun.nicedemo.MVP.mvp.model.MainModel;
import com.example.axtonsun.nicedemo.MVP.mvp.view.BaseView;

import rx.Subscriber;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public class MainPresenter implements BasePresenter{//实现Presenter接口 完善方法

    private BaseView mMainView;//包含一个View
    private MainModel mModle;//依赖Model

    public MainPresenter() {
        mModle = new MainModel();
    }
    //public MainPresenter(BaseView view){//构造方法中传递的对象绑定  MvpActivity中的接口方法传递数据
    // this.mMainView = view;
    // }

    /**
     * 完善方法
     * @param view
     */
    @Override
    public void attachView(BaseView view) {//把View传入
        mMainView = view;
    }

    @Override
    public void detachView() {
        mMainView = null;
    }

    /**
     * 并在方法中通过View更新数据
     * @param loginName
     */
    @Override
    public void searchUser(String loginName) {
        if (TextUtils.isEmpty(loginName.trim())) {
            mMainView.showErrorMessage("请输入登陆名");
            return;
        }
        if (mModle!=null){
            // 调用 M的方法 传 参
            // MainPresenter 使用了 Model 的方法  即 P ---> M
            mModle.getUser(new Subscriber<User>() {
                @Override
                public void onStart() {//先显示对话框
                    super.onStart();
                    //通过构造中传递的对象绑定 MvpActivity中的接口方法传递数据
                    //用RxJava的观察者观察结果   再去调用View的方法刷新界面 即  P--->V
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
