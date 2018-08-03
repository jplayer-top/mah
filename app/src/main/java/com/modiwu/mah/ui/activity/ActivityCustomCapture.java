package com.modiwu.mah.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.utils.StringUtils;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.utils.BitmapUtil;
import top.jplayer.baseprolibrary.utils.QRCodeDecoderUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/3/27.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ActivityCustomCapture extends AppCompatActivity {
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_custom_camera);
        Observable.timer(100, TimeUnit.MILLISECONDS).subscribe(aLong -> refresh());
        findViewById(R.id.ivToolLeft).setOnClickListener(v -> finish());
        findViewById(R.id.tvToolRight).setOnClickListener(this::toolRightBtn);
    }


    public void toolRightBtn(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {

        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            int indexOf = result.indexOf("?") + 1;
            String code = result.substring(indexOf);
            SharePreUtil.saveData(mActivity, "qcode", code);
            if (StringUtils.getInstance().assert2Login(mActivity)) {
                ToastUtils.init().showQuickToast(mActivity, "当前账号已登录");
            }
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
            ToastUtils.init().showErrorToast(mActivity, "解析二维码失败");
            refresh();
        }
    };

    private void refresh() {
        try {
            CaptureFragment captureFragment = new CaptureFragment();
            captureFragment.setAnalyzeCallback(analyzeCallback);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
        } catch (Exception e) {
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    String path = StringUtils.getRealFilePath(mActivity, uri);
                    Bitmap bitmap = BitmapUtil.getDecodeAbleBitmap(path);
                    if (bitmap != null) {
                        Observable.just(bitmap)
                                .subscribeOn(Schedulers.io())
                                .map(QRCodeDecoderUtils::syncDecodeQRCode)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<String>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(String result) {

                                        if (TextUtils.isEmpty(result)) {
                                            ToastUtils.init().showInfoToast(mActivity, "未发现二维码");
                                            refresh();
                                        } else {
                                            int indexOf = result.indexOf("?") + 1;
                                            String code = result.substring(indexOf);
                                            SharePreUtil.saveData(mActivity, "qcode", code);
                                            if (StringUtils.getInstance().assert2Login(mActivity)) {
                                                ToastUtils.init().showQuickToast(mActivity, "当前账号已登录");
                                            }
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
