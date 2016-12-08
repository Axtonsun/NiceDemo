package com.example.axtonsun.nicedemo.MVP.Retrofit;

import com.example.axtonsun.nicedemo.MVP.Bean.User;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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

    public HttpMethods() {// 3
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//设置连接超时时间
        //httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);写操作 超时时间
        //httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS); 读操作 超时时间

        //创建Cache
        //Cache cache = new Cache(AppContext.context().getCacheDir(), 10 * 1024 * 1024);
        //getCacheDir() 作为缓存文件的存放路径(/data/data/包名/cache)
        //getExternalCacheDir() 想看到缓存文件可以临时使用（/sdcard/Android/data/包名/cache）
        //httpClientBuilder.addNetworkInterceptor(getCacheInterceptor()).cache(cache).addInterceptor(getCacheInterceptor());
/**
 * 缓存策略应该由服务器指定，但是在有些情况下服务器并不支持缓存策略，这就要求我们客户端自行设置缓存策略。
 * 以上的代码假设服务端不支持缓存策略，因此器缓存策略完全由客户端通过重写request和response来实现。
 *
 * addNetWorkInterceptor()添加的是网络拦截器 它会在request和response时分别被调用一次(实现在线缓存)
 * addInterceptor()添加的是应用拦截器 它只会在response被调用一次(实现离线缓存)
 */
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        githubService = retrofit.create(GithubService.class);
    }
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();// 2
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;// 1
    }

    public void getUser(Subscriber<User> subscriber,String loginName){// 0
        githubService.getUser(loginName)
                .subscribeOn(Schedulers.io())//I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定的操作将在 Android 主线程运行
                .subscribe(subscriber);
    }

    /**
     * 在无网络的情况下 读取缓存
     * 有网络的情况下 根据缓存的过期时间重新请求
     * @return
     */
/*    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!TDevice.hasInternet()) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);//是每个拦截器的关键部分的实现(存在所有Http工作发生的地方 生产满足请求响应)
                if (TDevice.hasInternet()) {//有网络情况下，根据请求接口的设置，配置缓存。
                    //这样在下次请求时，根据缓存决定是否真正发出请求。
                    String cacheControl = request.cacheControl().toString();
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
                    return response.newBuilder().header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                }else{//无网络
                    return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
    }*///和其他的拦截器使用一样  将其设置到OkHttpClient即可
    // 但是此时设置缓存拦截器使用的addNetWorkInterceptor()方法
    // 凡是使用该设置的缓存拦截器的OkhttpClient都具备了缓存功能

}
