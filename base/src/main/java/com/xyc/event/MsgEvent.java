package com.xyc.event;

import org.greenrobot.eventbus.EventBus;

/**
 * 全局消息事件
 * Author by hf
 * Create on 16/8/18
 */
public class MsgEvent extends BaseEvent {
    /**
     * 快速发送Toast
     */
    public static void toast(String msg) {
        EventBus.getDefault().post(new MsgEvent().setMsg(msg).setDisplayType(DisplayType.Toast));
    }

    /**
     * 快速发送Loading
     */
    public static void loading(String msg) {
        EventBus.getDefault().post(new MsgEvent().setMsg(msg).setDisplayType(DisplayType.LoadDialog));
    }

    /**
     * 快速关闭Loading
     */
    public static void dismiss() {
        EventBus.getDefault().post(new MsgEvent().setDisplayType(DisplayType.Dismiss));
    }


    public enum DisplayType {
        Toast, Dialog, LoadDialog, Dismiss
    }

    /**
     * 消息显示类型：Toast或者Dialog，默认Toast
     */
    private DisplayType displayType = DisplayType.Toast;
    /**
     * 消息文案
     */
    private String msg;

    public String getMsg() {
        return msg;
    }

    public MsgEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public MsgEvent setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
        return this;
    }
}
