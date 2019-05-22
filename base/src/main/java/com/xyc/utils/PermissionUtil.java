package com.xyc.utils;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

/**
 * Created by xieyusheng on 2018/5/7.
 */

public class PermissionUtil {

    public void requestPermission(final Context context, Action nextAction) {
        AndPermission.with(context)
                .permission(Manifest.permission.READ_PHONE_STATE)
                .onGranted(nextAction)
                .onDenied(nextAction)
                .start();
    }

    public void requestPermission(final Context context, Action grantAction, String... permissions) {
        AndPermission.with(context)
                .permission(permissions)
                .onGranted(grantAction)
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                    }
                })
                .start();
    }

    public void requestPermission(final Context context, Action grantAction, Action deniedAction, String... permissions) {
        AndPermission.with(context)
                .permission(permissions)
                .onGranted(grantAction)
                .onDenied(deniedAction)
                .start();
    }

}
