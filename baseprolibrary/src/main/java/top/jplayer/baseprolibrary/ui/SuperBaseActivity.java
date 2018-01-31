package top.jplayer.baseprolibrary.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.dialog.DialogLoading;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.baseprolibrary.ui
 */

public abstract class SuperBaseActivity extends AppCompatActivity {

    protected View contentView;
    protected Toolbar mToolBar;
    protected ImageView mIvGoBack;
    protected TextView tvBarTitle;
    protected ImageView ivBarSearch;
    protected FrameLayout mFlRootView;
    public SuperBaseActivity mBaseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setDefView());
        initBundle(savedInstanceState);
    }


    /**
     * 重写可重置根布局
     *
     * @return 根布局
     */
    public View setDefView() {
        contentView = View.inflate(this, R.layout.activity_super_base, null);
        initDefSuperView(contentView);
        return contentView;
    }


    /**
     * 默认原始含有ToolBar，无需重写
     *
     * @param rootView 根布局
     */
    private void initDefSuperView(View rootView) {
        mFlRootView = rootView.findViewById(R.id.flRootView);
        mToolBar = rootView.findViewById(R.id.toolbar);
        findToolBarView(rootView);
        initSuperData(mFlRootView);
        customBarLeft();
    }

    public void findToolBarView(View rootView) {
        mIvGoBack = contentView.findViewById(R.id.ivGoBack);
        tvBarTitle = contentView.findViewById(R.id.tvBarTitle);
        ivBarSearch = contentView.findViewById(R.id.ivBarSearch);
    }


    /**
     * 默认原始根布局下的FrameLayout,基于相同ToolBar 的视图
     *
     * @param mFlRootView 根布局下的FrameLayout
     */
    public abstract void initSuperData(FrameLayout mFlRootView);

    /**
     * 自定义ToolBar左侧按钮
     */
    public void customBarLeft() {
        mIvGoBack.setVisibility(View.VISIBLE);
        mIvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 保存状态,可设置一些公共代码
     *
     * @param savedInstanceState 所保存的状态信息
     */
    public void initBundle(Bundle savedInstanceState) {

    }

    public DialogLoading mLoading;
    private Date mDate;
    private long mPreTime;

    public void dialogDismiss(String msg) {
        if (mLoading != null && mLoading.isShowing()) {
            long aftTime = mDate.getTime();
            long l = aftTime - mPreTime;
            Observable.timer(l < 1000 ? 1000 - l : 0, TimeUnit.MILLISECONDS)
                    .compose(new IoMainSchedule<>())
                    .subscribe(aLong -> {
                        mLoading.dismiss();
                        if (!msg.equals("")) {
                            ToastUtils.init().showInfoToast(this, msg);
                        }
                    });
        }
    }

    public void dialogShow(Context context) {
        mLoading = new DialogLoading(context);
        if (!mLoading.isShowing()) {
            mDate = new Date();
            mLoading.show();
            mPreTime = mDate.getTime();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        saveInstanceState(outState, outPersistentState);
    }

    public void saveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreInstanceState(savedInstanceState);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {

    }
}
