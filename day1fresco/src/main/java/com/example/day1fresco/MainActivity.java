package com.example.day1fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_yj)
    Button btnYj;
    @BindView(R.id.btn_yx)
    Button btnYx;
    @BindView(R.id.btn_bl)
    Button btnBl;
    @BindView(R.id.btn_jian)
    Button btnJian;
    @BindView(R.id.btn_ci)
    Button btnCi;
    @BindView(R.id.btn_jiazai)
    Button btnJiazai;
    @BindView(R.id.btn_jt)
    Button btnJt;
    @BindView(R.id.btn_pz)
    Button btnPz;
    @BindView(R.id.fresco)
    SimpleDraweeView fresco;
    private Uri uri;
    private Uri uris;
    private GenericDraweeHierarchyBuilder bulider;
    private RoundingParams mparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取框架
        uri = Uri.parse("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=745096747,3395666420&fm=27&gp=0.jpg");
        uris = Uri.parse("https://ps.ssl.qhimg.com/sdmt/231_135_100/t019d2c3d37cd7568e0.gif");
        bulider = new GenericDraweeHierarchyBuilder(getResources());
    }
    //点击事件
    @OnClick({R.id.btn_yj, R.id.btn_yx, R.id.btn_bl, R.id.btn_jian, R.id.btn_ci, R.id.btn_jiazai, R.id.btn_jt, R.id.btn_pz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //圆角
            case R.id.btn_yj:
                //设置弧度
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(50f);
                GenericDraweeHierarchy build = bulider.setRoundingParams(roundingParams).build();
                //加载图片控件
                fresco.setHierarchy(build);
                fresco.setImageURI(uri);

                break;
            //圆形
            case R.id.btn_yx:
                mparams = RoundingParams.asCircle();
                GenericDraweeHierarchy buildyx = bulider.setRoundingParams(mparams).build();
                fresco.setHierarchy(buildyx);
                fresco.setImageURI(uri);
                break;
            case R.id.btn_bl:
                break;
            //渐进式
            case R.id.btn_jian:
                ProgressiveJpegConfig jpegConfig =new ProgressiveJpegConfig() {
                    @Override
                    public int getNextScanNumberToDecode(int scanNumber) {
                        return scanNumber + 2;
                    }

                    @Override
                    public QualityInfo getQualityInfo(int scanNumber) {
                        boolean isGoodEnough = (scanNumber >= 5);
                        return ImmutableQualityInfo.of(scanNumber,isGoodEnough,false);
                    }
                };
                ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();

                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        //设置打开渐进模式
                        .setProgressiveRenderingEnabled(true)
                        .build();
                AbstractDraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setTapToRetryEnabled(true)
                        .setOldController(fresco.getController())
                        .build();

                fresco.setController(draweeController);
                break;
            case R.id.btn_ci:
                break;
            //动图
            case R.id.btn_jiazai:
                AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uris)
                        //是否自动播放
                        .setAutoPlayAnimations(true)
                        //内存优化
                        .setOldController(fresco.getController())
                        .build();
                fresco.setController(controller);
                if (fresco.getController() != null){
                    Animatable animatable = fresco.getController().getAnimatable();
                    //非空判断同时判断是否播放
                    if (animatable != null && !animatable.isRunning()){
                        animatable.start();
                    }
                }
                break;
            case R.id.btn_jt:
                break;
            case R.id.btn_pz:
                break;
        }
    }

}
