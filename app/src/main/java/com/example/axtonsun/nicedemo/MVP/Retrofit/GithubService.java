package com.example.axtonsun.nicedemo.MVP.Retrofit;

import com.example.axtonsun.nicedemo.MVP.Bean.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public interface GithubService {
    @GET("/users/{user}")
    Observable<User> getUser(@Path("user") String username);
}
