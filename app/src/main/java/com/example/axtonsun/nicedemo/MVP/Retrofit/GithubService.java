package com.example.axtonsun.nicedemo.MVP.Retrofit;

import com.example.axtonsun.nicedemo.MVP.Bean.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by AxtonSun on 2016/11/17.
 *
 * 设置缓存的两种方式
 * 1. 通过添加 @Headers("Cache-Control: max-age=120")进行设置
 *    添加了Cache-Control的请求 retrofit会默认缓存该请求的返回数据
 * 2. 通过Interceptors实现缓存
 *
 * 实现原理一致 但是适用场景不同
 * 通常是使用Interceptors 来设置通用缓存策略
 * 通过@Header针对某个请求单独设置缓存策略
 * retrofit缓存的实现借助Okhttp来的
 * 无论决定那种形式的缓存
 *                     首先要为OkHttpClient设置Cache  否则缓存不会生效(retrofit并为设置默认缓存目录)
 *                     例：@Headers("Cache-Control:public,max-age=120")
 *                       表示在120s内 缓存都是生效状态 无论有网无网都读取缓存
 *
 * 利用拦截器来实现缓存:
 * 创建缓存拦截器  HttpMethods类内部
 */

/**
 * 1.不需要缓存：Cache-Control: no-cache或Cache-Control: max-age=0
 * 2.如果想先显示数据，在请求。（类似于微博等）：Cache-Control: only-if-cached
 * 通过以上配置后通过拦截器中的request.cacheControl().toString() 就可以获取到我们配置的Cache-Control头文件，实现对应的缓存策略。
 */

public interface GithubService {
    @GET("/users/{user}")
    Observable<User> getUser(@Path("user") String username);
}
