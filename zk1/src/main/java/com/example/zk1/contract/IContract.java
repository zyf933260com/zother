package com.example.zk1.contract;

import com.example.zk1.bean.SBean;
import com.example.zk1.bean.XBean;
import com.example.zk1.net.ResponseCallBack;

import java.util.HashMap;
import java.util.List;

public interface IContract {
    //抽象类
    public abstract class IPresenter{
        public abstract void getList(String uri,HashMap<String, String> map);
    }
    //Model层
    interface IModel{
        void getList(String uri, HashMap<String,String> map, ResponseCallBack callBack);
    }
    interface IView{
        void success(List<SBean.ResultBean> list);
        void failure(String error);
    }
}
