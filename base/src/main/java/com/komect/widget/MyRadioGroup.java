package com.komect.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2019/4/8.
 */

public class MyRadioGroup extends LinearLayout implements MyRadioButton.OnCheckedChangeListener {
    private List<MyRadioButton> btns;
    private OnCheckedChangeListener listener;

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int checkedId);
    }

    public MyRadioGroup(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();
    }

    public MyRadioGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        btns = new ArrayList<>();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        addRadioButton(child);
        if (child instanceof MyRadioButton) {
            ((MyRadioButton) child).setOnCheckedChangeListener(this);
        }
        initCheck();
        super.addView(child, index, params);
    }

    private void initCheck() {
        if (btns != null && btns.size() != 0) {
            btns.get(0).setChecked(true);
        }
    }

    private void addRadioButton(View child) {
        if (child instanceof MyRadioButton) {
            btns.add((MyRadioButton) child);
        }
    }

    @Override
    public void onCheckedChanged(MyRadioButton button, boolean checked) {
        for (MyRadioButton bt : btns) {
            bt.setChecked(false);
        }
        button.setChecked(checked);
        if (listener != null && checked) {
            listener.onCheckedChanged(btns.indexOf(button));
        }
    }
}

