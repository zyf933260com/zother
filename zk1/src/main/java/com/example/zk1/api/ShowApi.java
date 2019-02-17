package com.example.zk1.api;

import com.example.zk1.bean.SBean;
import com.example.zk1.bean.XBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShowApi {

    @GET
    Call<SBean> sshow(@Url String uri, @QueryMap HashMap<String,String> map);
    @GET
    Call<XBean> xshow(@Url String uri,@QueryMap HashMap<String,String> map);
}
