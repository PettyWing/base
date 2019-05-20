package com.komect.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.komect.base.R;
import com.komect.base.databinding.DialogLoadBinding;


/**
 * Created by xieyusheng on 2018/11/23.
 */

public class LoadingDialog extends AppCompatDialog {

    DialogLoadBinding binding;

    public LoadingDialog(Context context) {
        super(context);
        binding = DialogLoadBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        initDialogView();
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
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度-100,单位为dp
        p.height = d.getHeight();
        getWindow().setAttributes(p);
        getWindow().setDimAmount(0f);
//        Window window = getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(0));
//        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.dimAmount = 0;
//        layoutParams.gravity = Gravity.CENTER;
//        window.setAttributes(layoutParams);

        setCanceledOnTouchOutside(false);
    }

    public LoadingDialog setLabel(CharSequence title) {
        binding.label.setText(title);
        binding.executePendingBindings();
        return this;
    }

    /**
     * @param listener
     * @return
     */
    public LoadingDialog setIsCancelable(OnCancelListener listener) {
        setOnCancelListener(listener);
        setCancelable(listener != null);
        return this;
    }

    /**
     * Builder
     */
    public static class Builder {

        private Context context;
        private LoadingDialog loadingDialog;

        public Builder(@NonNull Context context) {
            this.context = context.getApplicationContext();
            loadingDialog = new LoadingDialog(context);
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public LoadingDialog.Builder setLabel(@NonNull CharSequence title) {
            loadingDialog.setTitle(title);
            return this;
        }

        /**
         * 创建
         *
         * @return
         */
        public LoadingDialog create() {
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(context);
            }
            return loadingDialog;
        }

        /**
         * 显示
         */
        public void show() {
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(context);
            }
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }

    }
}
