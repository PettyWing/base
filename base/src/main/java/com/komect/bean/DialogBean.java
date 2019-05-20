package com.komect.bean;

/**
 * Created by xieyusheng on 2017/11/6.
 */

public class DialogBean {
    private String title; // 标题
    private String text; // 内容

    private Boolean positiveVisible = false;
    private Boolean negativeVisible = false;
    private String positiveText;
    private String negativeText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public Boolean getPositiveVisible() {
        return positiveVisible;
    }

    public void setPositiveVisible(Boolean positiveVisible) {
        this.positiveVisible = positiveVisible;
    }

    public Boolean getNegativeVisible() {
        return negativeVisible;
    }

    public void setNegativeVisible(Boolean negativeVisible) {
        this.negativeVisible = negativeVisible;
    }
}
