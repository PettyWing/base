package com.xyc.base;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.komect.base.R;
import com.xyc.widget.AdvertDialog;
import com.xyc.widget.LoadingDialog;
import com.xyc.widget.NormalAlertDialog;
import com.xyc.widget.SelectDialog;

import java.util.Arrays;

/**
 * 消息显示中心，用来显示在前台展示的消息
 * Created by xieyusheng on 2019/3/12.
 */

public class MsgHelper {

    protected Context context;
    private LoadingDialog hud;
    private NormalAlertDialog mAlertDialog;

    public void attach(Context context) {
        this.context = context;
    }

    public void detach() {
        dismissLoading();
        closeAlert();
    }

    /**
     * 显示toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示加载中对话框
     *
     * @param msg 对话框的文案信息
     */
    public void showLoading(String msg) {
        if (hud == null) {
            hud = new LoadingDialog.Builder(context)
                    .create();
        }
        hud.setLabel(msg.toString());
        if (!hud.isShowing()) {
            hud.show();
        }
    }

    /**
     * 隐藏正在显示的加载中对话框
     */
    public void dismissLoading() {
        if (hud != null && hud.isShowing()) {
            hud.dismiss();
        }
    }

    /**
     * 显示alert
     *
     * @param content
     * @param listener
     * @param cancel
     */
    public void showAlert(String content, View.OnClickListener listener, boolean cancel) {
        if (mAlertDialog == null) {
            mAlertDialog = new NormalAlertDialog.Builder(context).create();
        }
        mAlertDialog.setTitle("")
                .setText(content)
                .setPositive("确定", listener)
                .setNegative("取消", null)
                .setIsCancelable(cancel)
                .show();
    }

    /**
     * 关闭alert
     */
    public void closeAlert() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.cancel();
        }
    }

    /**
     * 显示选择器
     *
     * @param listener
     * @param names
     */
    public void showSelectDialog(SelectDialog.SelectDialogListener listener, String... names) {
        new SelectDialog(context, R.style
                .transparentFrameWindowStyle,
                listener, Arrays.asList(names)).show();
    }

    /**
     * 显示广告
     *
     * @param path
     * @param listener
     * @param time
     */
    public void showAdvert(String path, View.OnClickListener listener, int time) {
        new AdvertDialog.Builder(context).setTitle("开锁成功~")
                .setPositive(listener)
                .setNegative(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setTime(time)
                .setImage(path)
                .show();
    }
}
