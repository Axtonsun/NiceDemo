package com.example.axtonsun.nicedemo.MVP.mvp.model;

import com.example.axtonsun.nicedemo.MVP.Bean.User;

import rx.Subscriber;

/**
 * Created by AxtonSun on 2016/11/17.
 */

/**
 * 表示 数据模型和业务逻辑
 * 主要负责:
 * 1.网络 数据库 文件 传感器 第三方等数据源读写数据
 * 2.对外部的数据类型进行解析转换为APP内部数据交由上层处理
 * 3.对数据的临时存储 管理 协调上层数据请求
 */
public interface BaseModel {
    void getUser(Subscriber<User> subscriber,String loginName);
}
