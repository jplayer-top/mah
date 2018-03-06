package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseApplication;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.mvp.constract.LoginAnimContract;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.mvp.model.bean.RegisterBean;
import com.modiwu.mah.mvp.model.event.LoginSuccessEvent;
import com.modiwu.mah.mvp.model.event.TokenEvent;
import com.modiwu.mah.mvp.model.event.WXLoginSuccessEvent;
import com.modiwu.mah.mvp.presenter.LoginPresenter;
import com.modiwu.mah.utils.EditTextUtils;
import com.modiwu.mah.utils.StringUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.SizeUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

import static com.modiwu.mah.base.BaseApplication.APP_ID;

/**
 * Created by Obl on 2017/3/22.
 * com.ilanchuang.xiaoi.activity
 */

public class LoginAnimActivity extends BaseSpecialActivity implements TextWatcher, LoginAnimContract.ILoginAnimView {
    @BindView(R.id.ivBG)
    ImageView mIvBG;
    @BindView(R.id.iv_head_login)
    LinearLayout mIvLogo;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.bt_register)
    Button mBtRegister;
    @BindView(R.id.llBottomDisAnim)
    LinearLayout mDisMain;
    @BindView(R.id.llBackAnim)
    LinearLayout mLlShowBack;
    @BindView(R.id.phone_number)
    EditText mPhoneNumber;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.forget)
    TextView mForget;
    @BindView(R.id.tvBackBefore)
    TextView tvBackBefore;
    @BindView(R.id.bt_login1)
    Button mBtLogin1;
    @BindView(R.id.wechat)
    ImageView mWechat;
    @BindView(R.id.llShowInput)
    LinearLayout mLlShowLogin;
    @BindView(R.id.register_number)
    EditText mRegisterNumber;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.rtn_code)
    Button mRtnCode;
    @BindView(R.id.bt_register1)
    Button mBtRegister1;
    @BindView(R.id.llShowRegister)
    LinearLayout mLlShowRegister;
    @BindView(R.id.llNextBtn)
    LinearLayout llNextBtn;
    @BindView(R.id.llBeforeBtn)
    LinearLayout llFinishRegister;
    @BindView(R.id.register_pwd)
    EditText mRegisterPwd;
    @BindView(R.id.et_2code)
    EditText mEt2code;
    @BindView(R.id.bt_finish_register)
    Button mBtFinishRegister;
    @BindView(R.id.login_register)
    TextView mLoginRegister;
    @BindView(R.id.tvSetType)
    TextView mTvSetType;
    @BindView(R.id.llAgreement)
    LinearLayout llAgreement;
    @BindView(R.id.et_nick)
    EditText mEtNick;
    @BindView(R.id.llSetNick)
    LinearLayout mLlSetNick;
    @BindView(R.id.viewGone)
    View mViewGone;
    @BindView(R.id.tvBackBefore1)
    TextView mTvBackBefore1;
    @BindView(R.id.llWxBindIssue)
    LinearLayout mLlWxBindIssue;
    @BindView(R.id.tvDoNotLogin)
    TextView tvDoNotLogin;
    private LoginPresenter mPresenter;
    private String mPhone;
    private String mRNum;
    private String mRCode;
    private String mRePwd;
    private String mRe2Pwd;
    private String mPassword;
    private Map<String, String> mMap;
    public Bundle mBundle;
    private IWXAPI mIWXAPI;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_login_anim;
    }

    @Override
    public void initBaseData() {
        ButterKnife.bind(this, contentView);
        mPresenter = new LoginPresenter(this);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mLlShowBack.getLayoutParams();
        params.topMargin = ScreenUtils.getStatusBar(this) + SizeUtils.dp2px(2);
        EventBus.getDefault().register(this);
        mMap = new HashMap<>();
        mLlWxBindIssue.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPhoneNumber.addTextChangedListener(this);
        mEtPwd.addTextChangedListener(this);
        mRegisterNumber.addTextChangedListener(this);
        mEtCode.addTextChangedListener(this);
        mRegisterPwd.addTextChangedListener(this);
        mEt2code.addTextChangedListener(this);
        mEtNick.addTextChangedListener(this);
        EditTextUtils.setEditFacusListener(mPhoneNumber, getResources().getString(R.string.input_phone), this);
        EditTextUtils.setEditFacusListener(mRegisterNumber, getResources().getString(R.string.input_phone), this);
        EditTextUtils.setEditFacusListener(mEtPwd, getResources().getString(R.string.input_password), this);
        EditTextUtils.setEditFacusListener(mEtCode, "请输入验证码", this);
        EditTextUtils.setEditFacusListener(mRegisterPwd, "请输入密码", this);
        EditTextUtils.setEditFacusListener(mEtNick, "请输入昵称", this);
        EditTextUtils.setEditFacusListener(mEt2code, "请再次输入密码", this);
        ViewAnimator.animate(mIvBG)
                .scale(1.0f, 1.1f)
                .alpha(0.8f, 1.0f)
                .duration(10000)
                .start();
    }

    //输入前
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    //输入中
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    //输入后
    @Override
    public void afterTextChanged(Editable s) {

        lenLength(mRegisterPwd, s, 16, "您输入的密码长度超出范围");
        lenLength(mEtNick, s, 10, "您输入的昵称已超出范围");
    }

    private void lenLength(EditText editText, Editable s, int i, String ms) {
        if (editText.hasFocus()) {
            if (s.length() > i) {
                ToastUtils.init().showInfoToast(this, ms);
                editText.setText(s.toString().substring(0, i));
                editText.setSelection(i);
            }
        }
    }

    public boolean isRegister = false;

    @Subscribe
    public void wxLogin(WXLoginSuccessEvent event) {
        login(event.loginBean);
    }

    @Override
    public void login(LoginBean loginBean) {
        SharePreUtil.saveData(this, "uid", loginBean.uid);
        String imtoken = loginBean.imtoken;
        if (imtoken != null) {
            SharePreUtil.saveData(this, "token", imtoken);
        }
        EventBus.getDefault().post(new LoginSuccessEvent(loginBean.uid, StringUtils.getInstance().isNullable(imtoken, ""
        )));
        finish();
    }

    @Override
    public void register(RegisterBean registerBean) {

    }

    public void forget() {
        disRegister();
        showLogin();
        mPhoneNumber.setText(mRNum);
    }

    @Override
    public void smsCode(Map<String, String> map, TextView mRtnCode) {

    }

    @Override
    public void verfiyCode() {

    }

    public void isRegistered() {
        mPhoneNumber.setText(mRNum);
    }


    /**
     * 修复Bug 快速点击忘记密码+返回出现的bug
     */
    private int clickView = 0;
    public static final int MIN_CLICK_DELAY_TIME = 5000;
    private long lastClickTime = 0;

    @OnClick({R.id.tvDoNotLogin, R.id.login_register, R.id.llAgreement, R.id.bt_finish_register, R.id.bt_login, R.id
            .bt_register, R.id
            .llBackAnim, R.id.forget, R.id.bt_login1, R.id.wechat, R.id.rtn_code, R.id.bt_register1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDoNotLogin://先逛逛看
                dotoLogin();
                break;
            case R.id.bt_login://主页登录
                mBtRegister.setEnabled(false);
                showLogin();
                showBack();
                disMain();
                upLogo();
                break;
            case R.id.bt_register://主页注册
            case R.id.login_register://登录页立即注册
                mBtLogin.setEnabled(false);
                llAgreement.setVisibility(View.VISIBLE);
                mTvSetType.setText("验证手机号");
                mBtFinishRegister.setText("完成注册");
                mBtRegister1.setText("下一步");
                mLlSetNick.setVisibility(View.VISIBLE);
                mViewGone.setVisibility(View.VISIBLE);
                if (view.getId() == R.id.login_register) {
                    disLogin(250);
                } else {
                    showBack();
                    disMain();
                    upLogo();
                }
                showRegister(500);
                break;
            case R.id.llBackAnim://返回按钮
                backClickAnim();
                break;
            case R.id.forget://登录页忘记密码
                llAgreement.setVisibility(View.INVISIBLE);
                mLlSetNick.setVisibility(View.GONE);
                mViewGone.setVisibility(View.GONE);
                clickView = 1;
                mEtPwd.setText("");
                mTvSetType.setText("验证账号");
                mBtFinishRegister.setText("重置密码");
                mBtRegister1.setText("下一步");
                showRegister(500);
                disLoginShowRes();//这里有毛病
                //downLogo();
                break;
            case R.id.bt_register1://下一步
                mRNum = mRegisterNumber.getText().toString().trim();
                mRCode = mEtCode.getText().toString().trim();
                if (StringUtils.getInstance().isNullObj(mRNum) && StringUtils.getInstance().isNullObj(mRCode)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的手机号码和验证码");
                    return;
                }
                if (StringUtils.getInstance().isNullObj(mRNum)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的手机号码");
                    return;
                }
                if (StringUtils.getInstance().isNullObj(mRCode)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的验证码");
                    return;
                }


                if ("完成绑定".equals(mBtRegister1.getText().toString())) {
                    mPresenter.wxLogin(token, mRNum, mRCode);
                    break;
                }
                showLoading();
                mPresenter.verfiyCode(mRNum, mRCode);
                break;
            case R.id.bt_login1://正式登陆
                //登陆
                mPhone = mPhoneNumber.getText().toString().trim();
                mPassword = mEtPwd.getText().toString().trim();
                if (StringUtils.getInstance().isNullObj(mPhone) && StringUtils.getInstance().isNullObj(mPassword)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的手机号码和密码");
                    return;
                }
                if (StringUtils.getInstance().isNullObj(mPhone)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的手机号码");
                    return;
                }
                if (StringUtils.getInstance().isNullObj(mPassword)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的密码");
                    return;
                }
                mPresenter.login(mPhone, mPassword);
                break;
            case R.id.wechat://微信登录
                long currentTime = Calendar.getInstance().getTimeInMillis();
                if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                    wxLogin();
                    lastClickTime = currentTime;
                } else {
                    ToastUtils.init().showInfoToast(this, "请不要重复点击");
                }
                break;
            case R.id.rtn_code://验证码
                mRNum = mRegisterNumber.getText().toString().trim();
                if (StringUtils.getInstance().isNullObj(mRNum)) {
                    ToastUtils.init().showInfoToast(this, "请输入您的手机号码");
                    return;
                }
                if (mRtnCode.isEnabled()) {
                    if (!mMap.isEmpty()) {
                        mMap.clear();
                    }
                    mMap.put("phone", StringUtils.getInstance().isNullable(mRNum, ""));
                    if (!"验证账号".equals(mTvSetType.getText().toString()) || "完成绑定".equals(mBtRegister1.getText().toString())) {
                        mMap.put("check", "1");
                    } else {
                        mMap.put("check", "2");
                    }
                    showLoading();
                    mPresenter.smsCode(mMap, mRtnCode);
                }
                break;
            case R.id.bt_finish_register://完成注册
                String nick = mEtNick.getText().toString().trim();
                mRePwd = mRegisterPwd.getText().toString().trim();
                mRe2Pwd = mEt2code.getText().toString().trim();
                if (mViewGone.getVisibility() == View.VISIBLE) {
                    if (StringUtils.getInstance().isNullObj(nick) && StringUtils.getInstance().isNullObj(mRePwd) && StringUtils.getInstance()
                            .isNullObj(mRe2Pwd)) {
                        ToastUtils.init().showInfoToast(this, "请输入您的昵称和密码");
                    }
                    if (StringUtils.getInstance().isNullObj(nick)) {
                        ToastUtils.init().showInfoToast(this, "请输入您的昵称");
                        return;
                    }
                }
                if (StringUtils.getInstance().isNullObj(mRePwd)) {
                    ToastUtils.init().showInfoToast(this, "请设置6-16位登录密码");
                }
                if (!mRePwd.equals(mRe2Pwd)) {
                    ToastUtils.init().showInfoToast(this, "您输入的密码不一致，请重新输入");
                    return;
                }
                if (StringUtils.getInstance().isLengthRight(mEt2code)) {
                    ToastUtils.init().showInfoToast(this, "请设置6-16位登录密码");
                    return;
                }
                if (!mMap.isEmpty()) {
                    mMap.clear();
                }
                mMap.put("phone", StringUtils.getInstance().isNullable(mRNum, ""));
                mMap.put("nick", StringUtils.getInstance().isNullable(nick, ""));
                mMap.put("smCode", StringUtils.getInstance().isNullable(mRCode, ""));
                mMap.put("password", StringUtils.getInstance().isNullable(mRe2Pwd, ""));
                if ("重置密码".equals(mBtFinishRegister.getText().toString())) {
                    mPresenter.forget(mMap);
                    break;
                }
                mPresenter.register(mMap);
                break;
            case R.id.llAgreement:

                break;
        }
    }

    private void dotoLogin() {
        super.onBackPressed();
    }

    // 检查微信是否安装
    private boolean checkWX() {
        boolean bErr = false;
        try {
            if (!mIWXAPI.isWXAppInstalled() || !mIWXAPI.isWXAppSupportAPI()) {
                bErr = true;
            }
        } catch (Exception e) {
            bErr = true;
            e.printStackTrace();
        }
        if (!bErr) {
            return true;
        }
        return false;
    }

    private void wxLogin() {
        if (mIWXAPI == null) {
            mIWXAPI = WXAPIFactory.createWXAPI(this, APP_ID, true);
        }
        if (!checkWX()) {
            ToastUtils.init().showQuickToast(this, "您手机尚未安装微信，请安装后再登录");
            return;
        }
        Log.e("Oblivion", "1");
        mIWXAPI.registerApp(APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = UUID.randomUUID().toString();
        mIWXAPI.sendReq(req);
        Log.e("Oblivion", "2");
    }

    private String token;

    @Subscribe
    public void checkWxLogin(TokenEvent event) {
        token = event.token;
        mBtLogin.setEnabled(false);
        llAgreement.setVisibility(View.VISIBLE);
        mTvSetType.setText("绑定微信");
        mBtRegister1.setText("完成绑定");
        mBtFinishRegister.setText("完成注册");
        mLlWxBindIssue.setVisibility(View.VISIBLE);
        mLlSetNick.setVisibility(View.VISIBLE);
        mViewGone.setVisibility(View.VISIBLE);
        disLogin(0);
        showRegister(0);
    }

    private void backClickAnim() {
        if ("重置密码".equals(mBtFinishRegister.getText()) && mLlShowRegister.getVisibility() == View.VISIBLE) {
            clickView = 0;
            showLogin();
            disRegister();
            //upLogo();
            return;
        }
        mBtRegister.setEnabled(true);
        mBtLogin.setEnabled(true);
        showMain();
        downLogo();
        disBack();
        disLogin(250);
        disRegister();
    }


    /**
     * 系统返回按键
     */
    @Override
    public void onBackPressed() {
        if (mDisMain.getVisibility() != View.VISIBLE) {
            backClickAnim();
        } else {
            isDoubleBack = true;
            super.onBackPressed();
        }
    }

    private int curClick = 0;
    protected boolean isDoubleBack = false;

    /**
     * 提示两次退出程序
     */
    private void checkBack() {
        curClick++;
        BaseApplication.getMainThreadHandler().postDelayed(() -> curClick = 0, 1000);
        if (curClick == 1) {
            if (!isDoubleBack) {
                curClick = 0;
                finish();
            } else
                ToastUtils.init().showQuickToast(this, "再按一次退出应用");
        }
        if (curClick == 2) {
            finish();
        }
    }

    /**
     * 下一步
     */
    public void goNext() {
        ViewAnimator.animate(llNextBtn).translationX(0, -llNextBtn.getWidth()).duration(500)
                .andAnimate(llFinishRegister).translationX(llNextBtn.getWidth(), 0).duration(500)
                .onStart(() -> llFinishRegister.setVisibility(View.VISIBLE))
                .onStop(() -> llNextBtn.setVisibility(View.INVISIBLE)).start();
    }


    /**
     * 隐藏注册页
     */
    private void disRegister() {
        ViewAnimator.animate(mLlShowRegister).dp().translationY(0, 100).duration(250).alpha(1f, 0f)
                .onStop(() -> mLlShowRegister.setVisibility(View.INVISIBLE))
                .thenAnimate(llNextBtn).translationX(-llNextBtn.getWidth(), 0).duration(0)
                .thenAnimate(llFinishRegister).translationX(llNextBtn.getWidth(), 0).duration(500)
                .onStop(() -> {
                    llNextBtn.setVisibility(View.VISIBLE);
                    llFinishRegister.setVisibility(View.INVISIBLE);
                }).start();
    }

    /**
     * 隐藏登录页
     */
    private void disLogin(int dur) {
        ViewAnimator.animate(mLlShowLogin).translationY(0, 50).alpha(1.0f, 0f).duration(dur)
                .onStop(() -> mLlShowLogin.setVisibility(View.INVISIBLE)).start();
    }

    /**
     * 隐藏登录页
     */
    private void disLoginShowRes() {
        ViewAnimator.animate(mLlShowLogin).translationY(0, 50).alpha(1.0f, 0f).duration(250).onStop(() -> {
            if (clickView == 1) {
                mLlShowLogin.setVisibility(View.INVISIBLE);
            }
        }).start();
    }

    /**
     * 显示注册或忘记密码页
     */
    private void showRegister(int dur) {
        ViewAnimator.animate(mLlShowRegister).dp().translationY(100, 0).duration(dur).alpha(0f, 1f)
                .onStart(() -> mLlShowRegister.setVisibility(View.VISIBLE)).start();
    }

    /**
     * 显示主页
     */
    private void showMain() {
        ViewAnimator.animate(mDisMain).dp().translationY(100, 0).alpha(0f, 1f).duration(500)
                .onStart(() -> mDisMain.setVisibility(View.VISIBLE)).start();
    }

    /**
     * 隐藏初始界面
     */
    private void disMain() {
        ViewAnimator.animate(mDisMain).dp().translationY(0, 50).alpha(1f, 0f).duration(150)
                .onStop(() -> mDisMain.setVisibility(View.INVISIBLE)).start();
    }

    /**
     * Logo动画
     */
    private void upLogo() {
        ViewAnimator.animate(mIvLogo).dp().translationY(0, -50).duration(500).start();
    }

    /**
     * Logo动画
     */
    private void downLogo() {
        ViewAnimator.animate(mIvLogo).dp().translationY(-50, 0).duration(500).start();
    }

    /**
     * 隐藏返回按钮
     */
    private void disBack() {
        ViewAnimator.animate(mLlShowBack).translationX(0, -100).alpha(1f, 0f).duration(500).start();
    }

    /**
     * 显示登录及返回按钮
     */
    private void showLogin() {
        ViewAnimator.animate(mLlShowLogin).dp().translationY(50, 0).duration(500).alpha(0f, 1f)
                .onStart(() -> mLlShowLogin.setVisibility(View.VISIBLE))
                .start();
    }

    private void showBack() {
        ViewAnimator.animate(mLlShowBack).translationX(-100, 0).alpha(0f, 1f).duration(500)
                .onStart(() -> mLlShowBack.setVisibility(View.VISIBLE))
                .start();
    }


    @Override
    public void showError() {
        dialogDismiss("网络错误");
    }


    @Override
    public void showLoading() {
        dialogShow(this);
    }

    @Override
    public void showEmpty() {

    }

    public void showSpecError(String msg) {
        dialogDismiss(msg);
    }


    public void smsSend(TextView mRtnCode) {
        Disposable disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(60)
                .compose(new IoMainSchedule<>())
                .subscribe(aLong -> {
                    if (aLong >= 59) {
                        mRtnCode.setText("获取验证码");
                    } else {
                        mRtnCode.setText(String.format(Locale.CHINA, "%d秒后获取", 60 - aLong));
                    }
                });
        mPresenter.addSubscription(disposable);
        dialogDismiss("");
    }


}
