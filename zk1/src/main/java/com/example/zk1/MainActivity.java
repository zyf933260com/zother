package com.example.zk1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zk1.adapter.MAdapter;
import com.example.zk1.api.Api;
import com.example.zk1.bean.SBean;
import com.example.zk1.contract.IContract;
import com.example.zk1.presenter.IPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IContract.IView,XRecyclerView.LoadingListener{

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.sou)
    TextView sou;
    @BindView(R.id.xre)
    XRecyclerView xre;
    private MAdapter madapter;
    private IPresenter iPresenter;
    private int page;
    private int count=10;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        //点击事件
        sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                String name=title.getText().toString();
                map.put("keyword",name);
                map.put("page","1");
                map.put("count","5");
                iPresenter.getList(Api.SOUSUO, map);
            }
        });

    }

    /**
     * 初始化视图
     */
    private void initView() {
        //new P层
        iPresenter = new IPresenter(this);
        xre.setLoadingListener(this);
        //布局管理器
        xre.setLayoutManager(new GridLayoutManager(this, 2));
        //创建适配器
        madapter = new MAdapter(this);
        xre.setAdapter(madapter);
    }

    @Override
    public void success(List<SBean.ResultBean> list) {

        madapter.setList(list);
    }

    @Override
    public void failure(String error) {
        Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT);
    }
    //刷新
    @Override
    public void onRefresh() {

        map.put("page",page+"");
        iPresenter.getList(Api.SOUSUO, map);
        xre.refreshComplete();
    }
    //加载
    @Override
    public void onLoadMore() {
        map.put("page",page+"");
        iPresenter.getList(Api.SOUSUO, map);
        xre.loadMoreComplete();
    }
}
