package com.example.axtonsun.nicedemo.MVP.mvp.model;

import com.example.axtonsun.nicedemo.MVP.Bean.User;
import com.example.axtonsun.nicedemo.MVP.Retrofit.HttpMethods;

import rx.Subscriber;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public class MainModel implements BaseModel{
    @Override
    public void getUser(Subscriber<User> subscriber, String loginName) {
        HttpMethods.getInstance().getUser(subscriber,loginName);
    }
}
