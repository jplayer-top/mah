package top.jplayer.baseprolibrary.widgets.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.loadding.SpinKitView;

/**
 * Created by Administrator on 2017/3/16.
 */

public class DialogLoading extends BaseCommonDialog {

    private SpinKitView mLoadingView;
    private View mDialogContentView;
    private TextView mTextView;

    public DialogLoading(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public DialogLoading(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public DialogLoading(Context context) {
        super(context);
        initView(context);
    }

    public DialogLoading(Activity context) {
        super(context);
        initView(context);
    }

    public DialogLoading(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView(context);
    }

    private void initView(Context context) {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_spinkit, null);
        mLoadingView = mDialogContentView.findViewById(R.id.spin_kit);
        mTextView =  mDialogContentView.findViewById(R.id.name);
        setContentView(mDialogContentView);
    }

    public void setLoadingText(CharSequence charSequence) {
        mTextView.setText(charSequence);
    }

    public void setLoadingColor(int color) {
        mLoadingView.setColor(color);
    }

    public void cancel(RxCancelType code, String str) {
        cancel();
        switch (code) {
            case normal:
                ToastUtils.init().showQuickToast(getContext(), str);
                break;
            case error:
                ToastUtils.init().showErrorToast(getContext(), str);
                break;
            case success:
                ToastUtils.init().showSuccessToast(getContext(), str);
                break;
            case info:
                ToastUtils.init().showInfoToast(getContext(), str);
                break;
            default:
                ToastUtils.init().showQuickToast(getContext(), str);
                break;
        }
    }

    public void cancel(String str) {
        cancel();
        ToastUtils.init().showQuickToast(getContext(), str);
    }

    public SpinKitView getLoadingView() {
        return mLoadingView;
    }

    public View getDialogContentView() {
        return mDialogContentView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public enum RxCancelType {normal, error, success, info}
}
