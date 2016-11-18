package com.example.axtonsun.nicedemo.MVP.mvp.presenter;

import com.example.axtonsun.nicedemo.MVP.mvp.view.BaseView;

/**
 * Created by AxtonSun on 2016/11/17.
 *
 * P 主要是用于逻辑处理的 最经常要写的 即使 AttachView 和 DetachView
 * P 处理层
 */

public interface BasePresenter<T extends BaseView>{//实现类
    void attachView(T view);
    void detachView();
    void searchUser(String loginName);//获取用户信息的逻辑
}
