package com.example.axtonsun.nicedemo.MVP.mvp.view;

import com.example.axtonsun.nicedemo.MVP.Bean.User;

/**
 * Created by AxtonSun on 2016/11/17.
 *
 * V 是和我们用户界面的UI有关的 不管是控件的隐藏和显示 还是控件大小的改变都是我们的V来处理
 *
 * 一般的Activity中要用到View操作无非是:
 * 显示加载框 隐藏加载框 显示出错信息 显示当前数据为空的时候的view之类的
 *
 * 主要负责:
 * 1.提供UI交互
 * 2.在Presenter的控制下修改UI
 * 3.将业务事件交由presenter处理
 *
 * View层 不存储数据 不与Modek层交互
 *
 *
 * 对于View的接口，去观察功能上的操作，然后考虑：
 * 该操作需要什么？（getUserName, getPassword）
 * 该操作的结果，对应的反馈？(toMainActivity, showFailedError)
 * 该操作过程中对应的友好的交互？(showLoading, hideLoading)
 */

public interface BaseView {

    void showProgressDialog();
    void hideProgressDialog();
    void showErrorMessage(String text);
    void showText(User userbean);
}
