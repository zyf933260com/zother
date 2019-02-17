package com.example.day2fan;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.yxt)
    Button yxt;
    @BindView(R.id.yj)
    Button yj;
    @BindView(R.id.kgb)
    Button kgb;
    @BindView(R.id.dt)
    Button dt;
    @BindView(R.id.simple)
    SimpleDraweeView simple;
    @BindView(R.id.zj)
    Button zj;
    @BindView(R.id.fs)
    Button fs;
    private Uri uri;
    private Uri uris;
    private RoundingParams params;
    private GenericDraweeHierarchyBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取框架
        uri = Uri.parse("http://www.zhaoapi.cn/images/quarter/ad1.png");
        uris = Uri.parse("http://www.zhaoapi.cn/images/girl.gif");
        builder = new GenericDraweeHierarchyBuilder(getResources());
    }
    //点击事件
    @OnClick({R.id.yxt, R.id.yj, R.id.kgb, R.id.dt, R.id.zj, R.id.fs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //圆形图
            case R.id.yxt:
                params = RoundingParams.asCircle();
                GenericDraweeHierarchy buildyxt = builder.setRoundingParams(params).build();
                simple.setHierarchy(buildyxt);
                simple.setImageURI(uri);
                break;
              // 圆角图
            case R.id.yj:
              //设置弧度
                 RoundingParams roundingParams = RoundingParams.fromCornersRadius(20);
                GenericDraweeHierarchy build = builder.setRoundingParams(roundingParams).build();
                //加载图片控件
                simple.setHierarchy(build);
                simple.setImageURI(uri);
                break;
             //宽高比
            case R.id.kgb:
                simple.setAspectRatio(2.71f);
                break;
             //加载动图
            case R.id.dt:
                AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uris)
                        //是否自动播放
                        .setAutoPlayAnimations(true)
                        //内存优化
                        .setOldController(simple.getController())
                        .build();
                  simple.setController(controller);
                break;
            //获取注解
            case R.id.zj:

                break;
            //获取反射添加集合
            case R.id.fs:

                break;
        }
    }
}
