package com.komect.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.komect.Constants;
import com.komect.event.MsgEvent;
import com.komect.widget.SelectDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by xieyusheng on 2019/3/11.
 */

public class BaseViewModel {

    private MsgHelper msgHelper = new MsgHelper();
    private Activity context;

    public void create(Activity context) {
        this.context = context;
        msgHelper.attach(context);
    }

    public void attach() {
        EventBus.getDefault().register(this);
    }

    public void detach() {
        msgHelper.detach();
        EventBus.getDefault().unregister(this);
    }

    public MsgHelper getMsgHelper() {
        return msgHelper;
    }

    public void showToast(String msg) {
        msgHelper.showToast(msg);
    }

    public void showLoading(String msg) {
        msgHelper.showLoading(msg);
    }

    public void showAlert(String content, View.OnClickListener listener, boolean cancel) {
        msgHelper.showAlert(content, listener, cancel);
    }

    public void dismissLoading() {
        msgHelper.dismissLoading();
    }

    public void closeAlert() {
        msgHelper.closeAlert();
    }

    public void showSelectDialog(SelectDialog.SelectDialogListener listener, String... names) {
        msgHelper.showSelectDialog(listener, names);
    }

    public void showAdvert(String path, View.OnClickListener listener, int time) {
        msgHelper.showAdvert(path, listener, time);
    }

    protected void startActivity(Class<?> clz) {
        context.startActivity(new Intent(context, clz));
    }

    protected void startActivity(Class<?> clz, Bundle bundle) {
        context.startActivity(new Intent(context, clz).putExtra(Constants.BUNDLE, bundle));
    }

    protected void startActivity(Class<?> clz, int flags) {
        context.startActivity(new Intent(context, clz).addFlags(flags));
    }

    protected void finish() {
        context.finish();
    }

    protected Context getContext() {
        return context;
    }

    /**
     * 获得资源文件：字符串
     */
    protected String getStrFromRes(int resId) {
        return context.getResources().getString(resId);
    }

    /**
     * 获得资源文件中的距离  db值
     */
    protected int getDisFromRes(int resId) {
        return context.getResources().getDimensionPixelOffset(resId);
    }

    /**
     * 获取颜色
     *
     * @param resId 颜色的资源id
     */
    protected int getColFromRes(int resId) {
        return context.getResources().getColor(resId);
    }

    /**
     * 获取图片
     *
     * @param resId 图片的资源id
     */
    protected Drawable getDraFromRes(int resId) {
        return context.getResources().getDrawable(resId);
    }

    /**
     * 接收到EventBus发送的事件，处理UI消息提示的显示
     *
     * @param event 包含了提示信息和显示方式的EventBus事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGlobalMsg(MsgEvent event) {
        switch (event.getDisplayType()) {
            case Toast:
                showToast(event.getMsg());
                break;
            case LoadDialog:
                showLoading(event.getMsg());
                break;
            case Dismiss:
                dismissLoading();
                break;
            default:
                break;
        }
    }
}
