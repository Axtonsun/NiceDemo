package com.example.axtonsun.nicedemo.MVP.Retrofit;

import com.example.axtonsun.nicedemo.MVP.Bean.User;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public class HttpMethods {

    public static final String BASE_URL = "https://api.github.com";
    public static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private GithubService githubService;

    public HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//设置超时时间

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        githubService = retrofit.create(GithubService.class);
    }
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getUser(Subscriber<User> subscriber,String loginName){
        githubService.getUser(loginName)
                .subscribeOn(Schedulers.io())//I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定的操作将在 Android 主线程运行
                .subscribe(subscriber);
    }

}
