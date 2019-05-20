package com.komect.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.komect.receiver.ShortCutReceiver;


/**
 * 添加快捷方式
 * Created by xieyusheng on 2019/4/28.
 */

public class NewShortCutUtil {

    /**
     * @param context
     */
    public static void addShortCut(Context context, Class<?> target, int icon) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addShortCutAboveO(context, target, icon);
        } else {
            addShortcutBelowO(context, target, icon);
        }

    }

    /**
     * android O 添加桌面快捷方式
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void addShortCutAboveO(Context context, Class<?> target, int icon) {
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);

        if (shortcutManager.isRequestPinShortcutSupported()) {
            Intent shortcutInfoIntent = new Intent(context, target);
            shortcutInfoIntent.setAction(Intent.ACTION_VIEW); //action必须设置，不然报错

            ShortcutInfo info = new ShortcutInfo.Builder(context, "The only id")
                    .setIcon(Icon.createWithResource(context, icon))
                    .setShortLabel("智能门禁")
                    .setIntent(shortcutInfoIntent)
                    .build();

            //当添加快捷方式的确认弹框弹出来时，将被回调
            PendingIntent shortcutCallbackIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, ShortCutReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);

            shortcutManager.requestPinShortcut(info, shortcutCallbackIntent.getIntentSender());
        }

    }

    /**
     * Android 7.1及以下 添加桌面
     *
     * @param context
     */
    public static void addShortcutBelowO(Context context, Class<?> target, int icon) {

        // 创建快捷方式的Intent
        Intent shortcutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutIntent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "智能门禁");
        // 快捷图片
        Intent.ShortcutIconResource ico = Intent.ShortcutIconResource.fromContext(context.getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);

        Intent intent = new Intent(context, target);
        // 下面两个属性是为了当应用程序卸载时桌面上的快捷方式会删除
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");

        // 点击快捷图片，运行的程序主入口（部分APP需要通过主页面进入）
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 发送广播。OK
        context.sendBroadcast(shortcutIntent);
    }
}
