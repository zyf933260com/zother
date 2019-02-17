package com.example.zk1.model;

import com.example.zk1.api.ShowApi;
import com.example.zk1.bean.SBean;
import com.example.zk1.contract.IContract;
import com.example.zk1.net.ResponseCallBack;
import com.example.zk1.utils.RetrofitUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IModel implements IContract.IModel {
    @Override
    public void getList(String uri, HashMap<String, String> map, final ResponseCallBack callBack) {
        ShowApi showApi = RetrofitUtils.getInstence().showApi();
        Call<SBean> sshow = showApi.sshow(uri, map);
        sshow.enqueue(new Callback<SBean>() {
            @Override
            public void onResponse(Call<SBean> call, Response<SBean> response) {
                //成功的方法
                if (callBack!=null){
                    callBack.success(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<SBean> call, Throwable t) {
                //失败的方法
                if (callBack!=null){
                    callBack.failure(t.getMessage());
                }
            }
        });
    }
}
