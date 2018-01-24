package top.jplayer.baseprolibrary.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.baseprolibrary.ui
 */

public abstract class SuperBaseActivity extends AppCompatActivity {

    protected View mBaseView;
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
        mBaseView = View.inflate(this, R.layout.activity_super_base, null);
        initDefSuperView(mBaseView);
        return mBaseView;
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
        mIvGoBack = mBaseView.findViewById(R.id.ivGoBack);
        tvBarTitle = mBaseView.findViewById(R.id.tvBarTitle);
        ivBarSearch = mBaseView.findViewById(R.id.ivBarSearch);
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
