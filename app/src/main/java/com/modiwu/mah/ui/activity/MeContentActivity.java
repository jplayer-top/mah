package com.modiwu.mah.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.MeInfoBean;
import com.modiwu.mah.mvp.model.event.MessageEvent;
import com.modiwu.mah.mvp.presenter.MeInfoPresenter;
import com.modiwu.mah.utils.CameraUtils;
import com.modiwu.mah.utils.StringUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.activity
 */

public class MeContentActivity extends BaseCommonActivity {

    @BindView(R.id.tvAvatar)
    TextView tvAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvFloor)
    TextView tvFloor;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvPoint)
    TextView tvPoint;
    @BindView(R.id.ivMeAvatar)
    ImageView ivMeAvatar;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    private Unbinder bind;
    private MeInfoPresenter presenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_me;
    }

    @Override
    public void initBaseData() {
        EventBus.getDefault().register(this);
        bind = ButterKnife.bind(this, addRootView);
        tvName.setOnClickListener(view -> messageChange("姓名", tvName));
        tvType.setOnClickListener(view -> messageChange("户型", tvType));
        tvArea.setOnClickListener(view -> messageChange("面积", tvArea));
        tvFloor.setOnClickListener(view -> messageChange("小区", tvFloor));
        tvEmail.setOnClickListener(view -> messageChange("邮箱", tvEmail));
        presenter = new MeInfoPresenter(this);
        tvAvatar.setOnClickListener(v -> {
            setPermission(this, 100, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);

        });
        int uid = (int) SharePreUtil.getData(this, "uid", 0);
        presenter.getMeInfo(uid + "");
    }

    private void messageChange(String key, TextView view) {
        Bundle bundle = new Bundle();
        bundle.putString(key, view.getText().toString().trim());
        ActivityUtils.init().start(this,
                MessageChangeActivity.class, key, bundle);
    }

    @Subscribe
    public void messageChange(MessageEvent event) {
        switch (event.key) {
            case "姓名":
                presenter.setMeInfo("user_name", event.value);
                break;
            case "邮箱":
                presenter.setMeInfo("user_email", event.value);
                break;
            case "小区":
                presenter.setMeInfo("user_building", event.value);
                break;
            case "户型":
                presenter.setMeInfo("user_huxing", event.value);
                break;
            case "面积":
                presenter.setMeInfo("user_mian", event.value);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void success(String col, String value) {
        switch (col) {
            case "user_name":
                tvName.setText(value);
                break;
            case "user_email":
                tvEmail.setText(value);
                break;
            case "user_building":
                tvFloor.setText(value);
                break;
            case "user_huxing":
                tvType.setText(value);
                break;
            case "user_mian":
                tvArea.setText(value);
                break;
        }
    }

    public static void setPermission(final SuperBaseActivity activity, int code, String... permissions) {
        AndPermission.with(activity)
                .requestCode(code)
                .permission(permissions)
                .rationale((requestCode, rationale) -> {
                            // 此对话框可以自定义，调用rationale.resume()就可以继续申请。
                            AndPermission.rationaleDialog(activity, rationale).show();
                        }
                )
                .send();
    }

    File mFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                mFile = new File(path);
                String fileName = mFile.getName();
                presenter.upDateAvatarMes("img", fileName, mFile);
            }
        }
    }

    @PermissionYes(100)
    protected void getLocationYes(List<String> grantedPermissions) {
        CameraUtils.getInstance().openSingalCamer(this);
    }

    @PermissionNo(100)
    protected void getLocationNo(List<String> deniedPermissions) {
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, 100).show();
        }
    }

    public void successGet(MeInfoBean baseBean) {
        MeInfoBean.ProfileBean profile = baseBean.profile;
        Glide.with(this).load(profile.user_avatar)
                .apply(GlideUtils.init().options())
                .apply(RequestOptions.circleCropTransform())
                .into(ivMeAvatar);
        tvArea.setText(String.format(Locale.CHINA, "%s m²", StringUtils.getInstance().isNullable(profile.user_mian,
                "")));
        tvType.setText(String.format(Locale.CHINA, "%s", StringUtils.getInstance().isNullable(profile.user_huxing,
                "")));
        tvEmail.setText(String.format(Locale.CHINA, "%s", StringUtils.getInstance().isNullable(profile.user_email,
                "")));
        tvPhone.setText(String.format(Locale.CHINA, "%s", StringUtils.getInstance().isNullable(profile.user_phone,
                "")));
        tvName.setText(String.format(Locale.CHINA, "%s", StringUtils.getInstance().isNullable(profile.user_name,
                "")));
        tvFloor.setText(String.format(Locale.CHINA, "%s", StringUtils.getInstance().isNullable(profile.user_building,
                "")));
    }

    public void successAvatar(BaseBean baseBean) {
        Glide.with(this).load(mFile).apply(GlideUtils.init().options()).apply(RequestOptions.circleCropTransform()).into(ivMeAvatar);
    }
}
