package com.modiwu.mah.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.modiwu.mah.ui.activity.LoginAnimActivity;

import java.lang.ref.WeakReference;
import java.util.Map;

import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by PEO on 2017/3/10.
 * 判断文字
 */

public class StringUtils {
    private static StringUtils mUtils;
    public String str;
    private int mLength;
    public boolean isLength;

    public static StringUtils getInstance() {
        if (mUtils == null) {
            mUtils = new StringUtils();
        }
        return mUtils;
    }

    public boolean isNullObj(String o) {
        if (o == null || "".equals(o)) {
            return true;
        }
        return false;
    }

    public boolean assertNoLogin(Context context) {
        context = new WeakReference<>(context).get();
        String isLogin = (String) SharePreUtil.getData(context, "mark_login", "0");
        if (!"1".equals(isLogin)) {
            ToastUtils.init().showInfoToast(context, "请先登录");
            return true;
        }
        return false;
    }

    public boolean assertNoTipLogin(Context context) {
        context = new WeakReference<>(context).get();
        String isLogin = (String) SharePreUtil.getData(context, "mark_login", "0");
        if ("1".equals(isLogin)) {
            return true;
        }
        return false;
    }

    public boolean assert2Login(Context context) {
        context = new WeakReference<>(context).get();
        String isLogin = (String) SharePreUtil.getData(context, "mark_login", "0");
        if (!"1".equals(isLogin)) {
            ActivityUtils.init().start(context, LoginAnimActivity.class);
            return false;
        } else {
            return true;
        }
    }

    public SpannableString addUnderLineSpan(String str) {
        SpannableString spanString = new SpannableString(str);
        UnderlineSpan span = new UnderlineSpan();
        spanString.setSpan(span, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }


    /**
     * 获取输入文字
     */
    public String trim(EditText editText) {
        str = editText.getText().toString().trim();
        return str;
    }

    /**
     * 获取输入文字
     */
    public StringUtils trim(String string) {
        str = string.trim();
        return this;
    }

    /**
     * 判断是否为空
     */
    public StringUtils isNullable() {
        if (str == null) {
            str = "";
        }
        return this;
    }


    public String parseJson(Object object, String aftStr) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    entry.setValue(aftStr);
                }
            }
        }
        return gson.toJson(map);
    }

    public Map<String, String> change2Map(Object object, String aftStr) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    entry.setValue(aftStr);
                }
            }
        }
        return map;
    }

    /**
     * 判断是否为空,并返回输入的相应字段
     */
    public String isNullable(String preStr, String aftStr) {
        if (preStr == null || preStr.equals("")) {
            return aftStr;
        }
        return preStr;
    }

    /**
     * 判断是否为空,并返回输入的相应字段
     */
    public String isNullable(String preStr, String addStr, String aftStr) {
        if (preStr == null || preStr.equals("")) {
            return aftStr;
        }
        return preStr + addStr;
    }

    public String isNullable(String addStr, String... preStr) {
        StringBuilder aft = new StringBuilder("");
        for (int i = 0; i < preStr.length; i++) {
            if (preStr[i] == null) {
                aft.append("");
            } else {
                aft.append(preStr[i]);
                aft.append(addStr);
            }
        }
        return aft.toString();
    }

    public String isNullable(int preInt, String addStr, String aftStr) {
        if (preInt == 0) {
            return aftStr;
        }
        return preInt + addStr;
    }

    public StringUtils getLength() {
        mLength = str.length();
        if (mLength < 6 || mLength > 16) {
            isLength = false;
        } else {
            isLength = true;
        }
        return this;
    }

    public boolean getLength(EditText editText, int pre, int aft) {
        mLength = editText.getText().toString().length();
        if (mLength < pre || mLength > aft) {
            isLength = false;
        } else {
            isLength = true;
        }
        return isLength;
    }

    public StringUtils build() {
        return this;
    }

    public boolean isAllNullObj(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null || strings[i].trim().equals("")) {
                return true;
            }
        }
        return false;
    }

    public boolean isNullObj(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText == null) {
                return true;
            }
            String trim = editText.getText().toString().trim();
            if (trim.equals("")) {
                return true;
            }
        }
        return false;
    }

    public boolean isLengthRight(EditText editText) {
        int length = editText.getText().toString().trim().length();
        if (length > 5 && length < 17) {
            return false;
        }
        return true;
    }

    private Handler mHandler = new Handler();
    private int curPosition;

    /**
     * 获取验证码文字变动
     */
    public void getSmCode(final TextView mTvCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 60; i++) {
                    curPosition = 60 - i;
                    SystemClock.sleep(1000);
                    if (i >= 60) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvCode.setText("获取验证码");
                                mTvCode.setEnabled(true);
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                String text = curPosition + "秒后重置";
                                mTvCode.setText(text);
                                mTvCode.setEnabled(false);
                            }
                        });
                    }
                }
            }
        }).start();
    }

    /**
     * uri转path
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (DocumentsContract.isDocumentUri(context, uri)) {
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                data = getStringByCursor(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                data = getStringByCursor(context, contentUri, null);
            }
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            data = getStringByCursor(context, uri, null);
        }
        return data;
    }

    private static String getStringByCursor(Context context, Uri uri, String selection) {
        String data = "";
        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, selection, null, null);
        if (null != cursor) {
            if (cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                if (index > -1) {
                    data = cursor.getString(index);
                }
            }
            cursor.close();
        }
        return data;
    }

//    public boolean assesMessageType(Message message) {
//        String objectName = message.getObjectName().trim();
//        boolean equals = "XY:BpMsg".equals(objectName);
//        Log.e("Obl", objectName + "-" + equals);
//        return equals;
//    }
//
//    public String getMessageContent(Message message) {
//        String contentStr;
//        if (message.getObjectName().equals("RC:TxtMsg")) {
//            MessageContent messageContent = message.getContent();
//            TextMessage content = (TextMessage) messageContent;
//            contentStr = content.getContent();
//        } else if (message.getObjectName().equals("RC:VcMsg")) {
//            contentStr = "[语音]";
//        } else if (message.getObjectName().equals("RC:ImgMsg")) {
//            contentStr = "[图片]";
//        } else if (message.getObjectName().equals("RC:LBSMsg")) {
//            contentStr = "[位置]";
//        } else if (message.getObjectName().equals("XY:BpMsg")) {
//            contentStr = "[健康数据]";
//        } else if (message.getObjectName().equals("XY:BgMsg")) {
//            contentStr = "[健康数据]";
//        } else if (message.getObjectName().equals("XY:BqMsg")) {
//            contentStr = "[健康数据]";
//        } else if (message.getObjectName().equals("XY:MovieMsg")) {
//            contentStr = "[微视频]";
//        } else if (message.getObjectName().equals("XY:VcPicMsg")) {
//            contentStr = "[微图片]";
//        } else {
//            contentStr = "";
//        }
//        return contentStr;
//    }
}
