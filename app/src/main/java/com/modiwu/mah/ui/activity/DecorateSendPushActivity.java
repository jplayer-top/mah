package com.modiwu.mah.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.FlowSelBean;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.model.bean.SelWorkerBean;
import com.modiwu.mah.mvp.model.event.PickerItemSel;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.ui.adapter.DecorateItemCommonAdapter;
import com.modiwu.mah.ui.adapter.DecorateItemPicAdapter;
import com.modiwu.mah.utils.CameraUtils;
import com.modiwu.mah.utils.PickerUtils;
import com.modiwu.mah.utils.StringUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
 * Created by Obl on 2018/9/17.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateSendPushActivity extends BaseCommonActivity {
    @BindView(R.id.tvSelFlow)
    TextView mTvSelFlow;
    @BindView(R.id.editInputText)
    EditText mEditInputText;
    @BindView(R.id.tvPicNum)
    TextView mTvPicNum;
    @BindView(R.id.recyclerViewPic)
    RecyclerView mRecyclerViewPic;
    @BindView(R.id.recyclerViewConst)
    RecyclerView mRecyclerViewConst;
    private Unbinder mUnbinder;
    private DecorateItemPicAdapter mAdapter;
    private ArrayList<File> mArrayList;
    private DecorateBasePresenter mPresenter;
    private DecorateItemCommonAdapter mWokerAdapter;
    private PickerUtils mPickerUtils;
    private List<SelWorkerBean.RowsBean> mRowsBeans;
    private String mProject_id;
    private String mTask_id;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_send_push;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mPickerUtils = new PickerUtils();
        mRowsBeans = new ArrayList<>();
        mRecyclerViewPic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mArrayList = new ArrayList<>();
        EventBus.getDefault().register(this);
        mAdapter = new DecorateItemPicAdapter(mArrayList);
        mRecyclerViewPic.setAdapter(mAdapter);
        View view = View.inflate(this, R.layout.adapter_footer_create_pro_pic, null);
        view.findViewById(R.id.ivPicUpload).setOnClickListener(v -> {
            setPermission(this, 100, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
        });
        mAdapter.addFooterView(view);
        tvBarRight.setVisibility(View.VISIBLE);
        tvBarRight.setText("发布");
        mProject_id = mBundle.getString("project_id");
        mTask_id = mBundle.getString("task_id");
        tvBarRight.setOnClickListener(v -> {

            if ("".equals(flow_id)) {
                ToastUtils.init().showInfoToast(this, "请选择施工类型");
                return;
            }
            if (mRowsBeans.size() < 1) {
                ToastUtils.init().showInfoToast(this, "请选择参与人员");
                return;
            }
            if (StringUtils.getInstance().isNullObj(mEditInputText)) {
                ToastUtils.init().showInfoToast(this, "请输入施工信息");
                return;
            }

            StringBuilder stringBuilder = new StringBuilder();
            Observable.fromIterable(mRowsBeans).subscribe(rowsBean -> {
                stringBuilder.append(rowsBean.user_id);
                stringBuilder.append(",");
            });
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("project_id", mProject_id)
                    .addFormDataPart("task_id", mTask_id)
                    .addFormDataPart("flow_id", flow_id)
                    .addFormDataPart("work_content", mEditInputText.getText().toString())
                    .addFormDataPart("workers", stringBuilder.toString());
            Observable.fromIterable(mArrayList).subscribe(file -> builder.addFormDataPart("imgs", file.getName(), RequestBody.create(MediaType.parse("image/*"), file)));
            RequestBody requestBody = builder.build();
            mPresenter.sendPush(requestBody);
        });
        mPresenter = new DecorateBasePresenter(this);
        mTvSelFlow.setOnClickListener(v -> {
            KeyBoardUtils.closeInput(this, v);
            if (mFlowSelBean != null) {
                getFlowSel(mFlowSelBean);
            } else
                mPresenter.getFlowSelBean(mTask_id);
        });
        mRecyclerViewConst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mWokerAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewConst.setAdapter(mWokerAdapter);
        mWokerAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
        mWokerAdapter.getFooterLayout().setOnClickListener(v -> {
            KeyBoardUtils.closeInput(this, v);
            if (mSelWorkerBean != null) {
                getSelWorker(mSelWorkerBean);
            } else
                mPresenter.getSelWorker(mProject_id);
        });

    }

    public void getWorkerList(List<SelWorkerBean.RowsBean> beans) {
        Gson gson = new Gson();
        mCommonWorkerBeans = gson.fromJson(gson.toJson(beans), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        mWokerAdapter.setNewData(mCommonWorkerBeans);
    }

    List<ProInfoBean.CommonBean> mCommonWorkerBeans;
    private String flow_id = "";

    @PermissionYes(100)
    protected void getLocationYes(List<String> grantedPermissions) {
        int size = mAdapter.getData().size();
        CameraUtils.getInstance().openSize(this, 8 - size);

    }

    @Subscribe
    public void onEvent(PickerItemSel event) {
        if ("工人".equals(event.type)) {
            SelWorkerBean.RowsBean rowsBean = mSelWorkerBean.rows.get(event.options1);
            if (!mRowsBeans.contains(rowsBean)) {
                mRowsBeans.add(rowsBean);
            } else {
                ToastUtils.init().showInfoToast(this, "已经存在");
            }
            getWorkerList(mRowsBeans);
        } else if ("flow".equals(event.type)) {
            flow_id = mFlowSelBean.rows.get(event.options1).flow_id;
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
                mTvPicNum.setText(String.format(Locale.CHINA, "%d/8", files.size()));
                if (mLoading != null && mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                isZiped = true;
            });
        }
    }

    public boolean isZiped = true;

    @Override
    public void sendPush() {
        super.sendPush();

    }

    @PermissionNo(100)
    protected void getLocationNo(List<String> deniedPermissions) {
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, 100).show();
        }
    }

    private ArrayList<String> optionsItems = new ArrayList<>();
    public FlowSelBean mFlowSelBean;

    @Override
    public void getFlowSel(FlowSelBean bean) {
        super.getFlowSel(bean);
        this.mFlowSelBean = bean;
        optionsItems.clear();
        Observable.fromIterable(bean.rows).subscribe(rowsBean -> {
            optionsItems.add(rowsBean.flow_name);
        });
        mPickerUtils.initStringPicker(optionsItems, this, "flow");
        if (!mPickerUtils.mPickerView.isShowing()) {
            mPickerUtils.mPickerView.show(mTvSelFlow);
        }
    }

    public SelWorkerBean mSelWorkerBean;

    @Override
    public void getSelWorker(SelWorkerBean bean) {
        super.getSelWorker(bean);
        optionsItems.clear();
        this.mSelWorkerBean = bean;
        Observable.fromIterable(bean.rows).subscribe(rowsBean -> {
            optionsItems.add(rowsBean.user_name + "  " + rowsBean.work_type);
        });
        mPickerUtils.initStringPicker(optionsItems, this, "工人");
        if (!mPickerUtils.mPickerView.isShowing()) {
            mPickerUtils.mPickerView.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyBoardUtils.closeInput(this, mFlRootView);
        EventBus.getDefault().unregister(this);
        mUnbinder.unbind();
    }
}
