package com.komect.widget;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.komect.base.R;
import com.komect.base.databinding.DialogProgressBinding;

/**
 * Created by lenovo on 2017/11/6.
 */
public class ProgressDialog extends AppCompatDialog {
    DialogProgressBinding binding;

    public ProgressDialog(Context context) {
        super(context);
        binding = DialogProgressBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        initDialogView();
    }

    public ProgressDialog setMax(int max) {
        binding.numberProgressBar.setMax(max);
        return this;
    }

    public ProgressDialog setProgress(int progress) {
        binding.numberProgressBar.setProgress(progress);
        return this;
    }

    public ProgressDialog setPercent(String percent) {
        binding.layoutRate.setVisibility(View.VISIBLE);
        binding.finishRate.setText(percent);
        return this;
    }

    public ProgressDialog setTotal(String total) {
        binding.totalRate.setText(total);
        return this;
    }


    /**
     * 初始化window背景框
     */
    private void initDialogView() {
        WindowManager m = getWindow().getWindowManager();
        //设置dialog 背景颜色
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth() - 2 * 130; //设置dialog的宽度为当前手机屏幕的宽度-100,单位为dp
        getWindow().setAttributes(p);
    }

    /**
     * 显示进度dialog
     */
    public void show() {
        binding.executePendingBindings();
        super.setCancelable(false);
        super.show();
    }

}
