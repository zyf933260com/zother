package com.example.zk1.utils;

import com.example.zk1.api.Api;
import com.example.zk1.api.ShowApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils instence;
    private final Retrofit retrofit;

    //单例模式
    public static RetrofitUtils getInstence() {
        if (instence==null){
            synchronized (RetrofitUtils.class){
                if (instence==null){
                    instence=new RetrofitUtils();
                }
            }
        }
        return instence;
    }
    private RetrofitUtils(){
        //日志拦截器
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //初始化okhttp
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(interceptor)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        //初始化retrofit  一个是gson解析 一个是结合rxjava
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .build();


    }


    public ShowApi showApi(){
        return retrofit.create(ShowApi.class);
    }
}
