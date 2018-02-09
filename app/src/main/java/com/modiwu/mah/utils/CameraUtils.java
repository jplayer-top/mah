package com.modiwu.mah.utils;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageLoader;
import com.jaiky.imagespickers.ImageSelector;
import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.glide.GlideUtils;


/**
 * Created by PEO on 2017/3/13.
 * 打开相机的UTils
 */

public class CameraUtils {
    private static CameraUtils mCamere;

    public static CameraUtils getInstance() {
        if (mCamere == null) {
            mCamere = new CameraUtils();
        }
        return mCamere;
    }

    /**
     * 打开相册
     */
    public void openSingalCamer(Activity activity) {
        ImageConfig imageConfig
                = new ImageConfig.Builder((ImageLoader) (context, path, imageView) -> Glide.with(context).load(path).apply(GlideUtils.init().options()).into(imageView))
                .steepToolBarColor(activity.getResources().getColor(R.color.blue))
                .titleBgColor(activity.getResources().getColor(R.color.blue))
                .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                .titleTextColor(activity.getResources().getColor(R.color.white))
                // 开启单选   （默认为多选）
                .singleSelect()
                .crop(1, 1, 200, 200)
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ImageSelector/Pictures")
                .requestCode(1)
                .build();
        ImageSelector.open(activity, imageConfig);   // 开启图片选择器
    }

    /**
     * 打开图库选择照片
     */
    public void openCamer(Activity activity) {
        ImageConfig imageConfig
                = new ImageConfig.Builder((ImageLoader) (context, path, imageView) -> Glide.with(context).load(path).apply(GlideUtils.init().options()).into(imageView))
                .steepToolBarColor(activity.getResources().getColor(R.color.blue))
                .titleBgColor(activity.getResources().getColor(R.color.blue))
                .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                .titleTextColor(activity.getResources().getColor(R.color.white))
                .mutiSelect()
                .mutiSelectMaxSize(9)
                .showCamera()
                .filePath("/ImageSelector/Pictures")
                .requestCode(1)
                .build();
        ImageSelector.open(activity, imageConfig);   // 开启图片选择器
    }

    /**
     * 打开图库选择照片
     */
    public void openNoSinCamer(Activity activity) {
        ImageConfig imageConfig
                = new ImageConfig.Builder((ImageLoader) (context, path, imageView) -> Glide.with(context).load(path).apply(GlideUtils.init().options()).into(imageView))
                .steepToolBarColor(activity.getResources().getColor(R.color.blue))
                .titleBgColor(activity.getResources().getColor(R.color.blue))
                .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                .titleTextColor(activity.getResources().getColor(R.color.white))
                // 开启单选   （默认为多选）
                .singleSelect()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/GPUImage/")
                .requestCode(1)
                .build();
        ImageSelector.open(activity, imageConfig);   // 开启图片选择器
    }
}
