package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.PostLocalBean;
import com.modiwu.mah.mvp.presenter.DecorateCreateProPresenter;
import com.modiwu.mah.ui.adapter.DecorateItemCommonAdapter;
import com.modiwu.mah.ui.adapter.DecorateItemPicAdapter;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private Unbinder mUnbinder;
    private DecorateCreateProPresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_create_pro;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        mRecyclerViewOwner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewOwner.setAdapter(new DecorateItemCommonAdapter(data));
        mRecyclerViewVisor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewVisor.setAdapter(new DecorateItemCommonAdapter(data));
        mRecyclerViewConst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewConst.setAdapter(new DecorateItemCommonAdapter(data));
        mRecyclerViewPic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewPic.setAdapter(new DecorateItemPicAdapter(data));
        mPresenter = new DecorateCreateProPresenter(this);
        initPicker();
        mTvInputCity.setOnClickListener(v -> {
            if (mLocalBean != null) {
                setLocalBean(mLocalBean);
            } else {
                mPresenter.requestLocalBean();
            }
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
            mCity_name = (optionsLocalXItems.get(options1).get(option2).name) == null ? "" : (optionsLocalXItems.get(options1).get(option2).name + "");
            mArea_name = (optionsQItems.get(options1).get(option2).get(options3).name) == null ? "" : optionsQItems.get(options1).get(option2).get(options3).name + "";
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
