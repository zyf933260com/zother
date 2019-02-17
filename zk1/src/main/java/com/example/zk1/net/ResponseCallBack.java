package com.example.zk1.net;

import com.example.zk1.bean.SBean;
import com.example.zk1.bean.XBean;

import java.util.List;

public interface ResponseCallBack {
    //成功
    void success(List<SBean.ResultBean> list);
    //失败
    void failure(String error);
}
