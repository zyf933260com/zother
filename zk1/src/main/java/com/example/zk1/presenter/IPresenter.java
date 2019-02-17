package com.example.zk1.presenter;

import com.example.zk1.bean.SBean;
import com.example.zk1.contract.IContract;
import com.example.zk1.model.IModel;
import com.example.zk1.net.ResponseCallBack;

import java.util.HashMap;
import java.util.List;

public class IPresenter extends IContract.IPresenter {

    private IModel model;
    private IContract.IView view;

    public IPresenter(IContract.IView view) {
        this.view = view;
        model=new IModel();
    }

    @Override
    public void getList(String uri, HashMap<String, String> map) {
        if (model!=null){
            model.getList(uri, map, new ResponseCallBack() {
                @Override
                public void success(List<SBean.ResultBean> list) {
                    if (view!=null){
                        view.success(list);
                    }
                }

                @Override
                public void failure(String error) {
                    if (view!=null){
                        view.failure(error);
                    }
                }
            });
        }
    }
}
