package top.jplayer.baseprolibrary.utils;

import com.yanzhenjie.permission.AndPermission;

import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Obl on 2018/3/9.
 * top.jplayer.baseprolibrary.utils
 */

public class PermissionUtils {
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
}
