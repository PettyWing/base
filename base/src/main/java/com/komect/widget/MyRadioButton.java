package com.komect.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.komect.base.databinding.LayoutRadioButtonBinding;

/**
 * Created by xieyusheng on 2019/4/8.
 */

public class MyRadioButton extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {

    LayoutRadioButtonBinding binding;
    private boolean mChecked;
    private OnCheckedChangeListener listener;

    public MyRadioButton(Context context) {
        this(context, null);
    }

    public MyRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
        binding.radio.setOnCheckedChangeListener(this);
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MyRadioButton button, boolean checked);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listener.onCheckedChanged(this, isChecked);
    }

    private void initView(Context context) {
        binding = LayoutRadioButtonBinding.inflate(LayoutInflater.from(context));
        addView(binding.getRoot());
    }

    public void setTitle(String title) {
        binding.title.setText(title);
    }

    public void setDrawable(Drawable drawable) {
        binding.icon.setImageDrawable(drawable);
    }

    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            binding.radio.setChecked(mChecked);
        }
    }

    public boolean isChecked() {
        return binding.radio.isChecked();
    }

}

