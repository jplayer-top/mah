package com.modiwu.mah.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.PostLocalBean;
import com.modiwu.mah.mvp.presenter.DecorateCreateProPresenter;
import com.modiwu.mah.ui.adapter.DecorateItemPicAdapter;
import com.modiwu.mah.utils.CameraUtils;
import com.modiwu.mah.utils.StringUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.BitmapUtil;
import top.jplayer.baseprolibrary.utils.KeyBoardUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.dialog.DialogLoading;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static top.jplayer.baseprolibrary.utils.PermissionUtils.setPermission;

/**
 * Created by Obl on 2018/9/10.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateCreateProActivity extends BaseCommonActivity {
    @BindView(R.id.tvInputCity)
    TextView mTvInputCity;
    @BindView(R.id.tvInputLocal)
    TextView mTvInputLocal;
    @BindView(R.id.tvInputFloorNum)
    TextView mTvInputFloorNum;
    @BindView(R.id.tvInputUnitNum)
    TextView mTvInputUnitNum;
    @BindView(R.id.tvInputDoorNum)
    TextView mTvInputDoorNum;
    @BindView(R.id.recyclerViewOwner)
    RecyclerView mRecyclerViewOwner;
    @BindView(R.id.recyclerViewPic)
    RecyclerView mRecyclerViewPic;
    @BindView(R.id.recyclerViewVisor)
    RecyclerView mRecyclerViewVisor;
    @BindView(R.id.recyclerViewConst)
    RecyclerView mRecyclerViewConst;
    @BindView(R.id.tvPicNum)
    TextView tvPicNum;
    private Unbinder mUnbinder;
    private DecorateCreateProPresenter mPresenter;
    private DecorateItemPicAdapter mAdapter;
    private ArrayList<File> mArrayList;
    private String mCity_code;
    private String mArea_code;
    private DialogLoading mLoading;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_create_pro;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mPresenter = new DecorateCreateProPresenter(this);
        initPicker();
        mTvInputCity.setOnClickListener(v -> {
            if (mLocalBean != null) {
                setLocalBean(mLocalBean);
            } else {
                mPresenter.requestLocalBean();
            }
        });
        mRecyclerViewPic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mArrayList = new ArrayList<>();
        mAdapter = new DecorateItemPicAdapter(mArrayList);
        mRecyclerViewPic.setAdapter(mAdapter);
        View view = View.inflate(this, R.layout.adapter_footer_create_pro_pic, null);
        view.findViewById(R.id.ivPicUpload).setOnClickListener(v -> {
            setPermission(this, 100, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
        });
        mAdapter.addFooterView(view);
        tvBarRight.setVisibility(View.VISIBLE);
        tvBarRight.setOnClickListener(v -> {

            if (mCity_code == null || mArea_code == null) {
                ToastUtils.init().showQuickToast(mBaseActivity, "请选择城市地区");
                return;
            }
            if (StringUtils.getInstance().isNullObj(mTvInputLocal.toString())) {
                ToastUtils.init().showQuickToast(mBaseActivity, "请输入小区名称");
                return;
            }
            if (StringUtils.getInstance().isNullObj(mTvInputFloorNum.toString())) {
                ToastUtils.init().showQuickToast(mBaseActivity, "请输入楼号");
                return;
            }
            if (StringUtils.getInstance().isNullObj(mTvInputUnitNum.toString())) {
                ToastUtils.init().showQuickToast(mBaseActivity, "请输入单元门");
                return;
            }
            if (StringUtils.getInstance().isNullObj(mTvInputDoorNum.toString())) {
                ToastUtils.init().showQuickToast(mBaseActivity, "请输入门牌号");
                return;
            }
            if (!isZiped) {
                ToastUtils.init().showQuickToast(mBaseActivity, "图片压缩中，请稍后");
                return;
            }
            String local_detail = mTvInputLocal.getText().toString();
            String build = mTvInputFloorNum.getText().toString();
            String unit = mTvInputUnitNum.getText().toString();
            String door = mTvInputDoorNum.getText().toString();
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("city_code", mCity_code)
                    .addFormDataPart("area_code", mArea_code)
                    .addFormDataPart("building_name", local_detail)
                    .addFormDataPart("building_no", build)
                    .addFormDataPart("unit_no", unit)
                    .addFormDataPart("house_no", door);
            Observable.fromIterable(mArrayList).subscribe(file -> builder.addFormDataPart("imgs", file.getName(), RequestBody.create(MediaType.parse("image/*"), file)));
            RequestBody requestBody = builder.build();
            mPresenter.requestCreatePro(requestBody);
        });
    }

    private ArrayList<PostLocalBean> optionsLocalSItems = new ArrayList<>();
    private ArrayList<ArrayList<PostLocalBean>> optionsLocalXItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<PostLocalBean>>> optionsQItems = new ArrayList<>();
    private OptionsPickerView mLocalPickerView;
    private LocalBean mLocalBean;

    public void setLocalBean(LocalBean localBean) {
        mLocalBean = localBean;
        if (localBean != null && localBean.areas.size() > 0) {
            for (int i = 0; i < localBean.areas.size(); i++) {//省
                ArrayList<ArrayList<PostLocalBean>> minLocalItems = new ArrayList<>();
                ArrayList<PostLocalBean> subsString = new ArrayList<>();
                for (int j = 0; j < localBean.areas.get(i).subs.size(); j++) {
                    String area_nameS = localBean.areas.get(i).subs.get(j).area_name;
                    String area_codeS = localBean.areas.get(i).subs.get(j).area_code;
                    PostLocalBean postLocalBeanS = new PostLocalBean();
                    postLocalBeanS.name = area_nameS;
                    postLocalBeanS.code = area_codeS;
                    subsString.add(postLocalBeanS);
                    ArrayList<PostLocalBean> subsXString = new ArrayList<>();
                    for (int k = 0; k < localBean.areas.get(i).subs.get(j).subs.size(); k++) {
                        String area_nameX = localBean.areas.get(i).subs.get(j).subs.get(k).area_name;
                        String area_codeX = localBean.areas.get(i).subs.get(j).subs.get(k).area_code;
                        PostLocalBean postLocalBeanX = new PostLocalBean();
                        postLocalBeanX.name = area_nameX;
                        postLocalBeanX.code = area_codeX;
                        subsXString.add(postLocalBeanX);
                    }
                    minLocalItems.add(subsXString);
                }
                optionsLocalXItems.add(subsString);
                optionsQItems.add(minLocalItems);
                String area_name = localBean.areas.get(i).area_name;
                String area_code = localBean.areas.get(i).area_code;
                PostLocalBean postLocalBean = new PostLocalBean();
                postLocalBean.code = area_code;
                postLocalBean.name = area_name;
                optionsLocalSItems.add(postLocalBean);
            }
            if (optionsLocalSItems.size() != 0 & optionsLocalSItems != null) {
                mLocalPickerView.setPicker(optionsLocalSItems, optionsLocalXItems, optionsQItems);
            }
        }
        if (!mLocalPickerView.isShowing()) {
            mLocalPickerView.show();
        }
    }

    private String mProvince_name;
    private String mCity_name;
    private String mArea_name;

    private void initPicker() {
        //地区
        mLocalPickerView = new OptionsPickerView.Builder(this, (options1, option2, options3, v) -> {
            mProvince_name = optionsLocalSItems.get(options1).name + "";
            PostLocalBean localBean = optionsLocalXItems.get(options1).get(option2);
            mCity_name = (localBean.name) == null ? "" : (localBean.name + "");
            mCity_code = localBean.code;
            PostLocalBean areaBean = optionsQItems.get(options1).get(option2).get(options3);
            mArea_code = areaBean.code;
            mArea_name = (areaBean.name) == null ? "" : areaBean.name + "";
            mTvInputCity.setText(String.format(Locale.CHINA, "%s%s%s", mProvince_name, mCity_name, mArea_name));
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();
    }

    @PermissionYes(100)
    protected void getLocationYes(List<String> grantedPermissions) {
        int size = mAdapter.getData().size();
        CameraUtils.getInstance().openSize(this, 8 - size);

    }

    @PermissionNo(100)
    protected void getLocationNo(List<String> deniedPermissions) {
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, 100).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            if (mLoading == null) {
                mLoading = new DialogLoading(this);
            }
            mLoading.setLoadingText("图片上传中...");
            mLoading.show();
            isZiped = false;
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            Observable.just(pathList).map(strings -> {
                for (String path : strings) {
                    mArrayList.add(BitmapUtil.compressImage(new File(path)));
                }
                return mArrayList;
            }).compose(new IoMainSchedule<>()).subscribe(files -> {
                if (mAdapter.getData().size() >= 8) {
                    mAdapter.removeAllFooterView();
                }
                mAdapter.setNewData(files);
                tvPicNum.setText(String.format(Locale.CHINA, "%d/8", files.size()));
                if (mLoading != null && mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                isZiped = true;
            });
        }
    }

    public boolean isZiped = true;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyBoardUtils.closeInput(this, mFlRootView);
        mUnbinder.unbind();
    }
}
