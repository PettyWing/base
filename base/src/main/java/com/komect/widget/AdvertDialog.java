package com.komect.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.komect.base.R;
import com.komect.base.databinding.DialogAdvertBinding;

/**
 * Created by xieyusheng on 2019/4/23.
 */

public class AdvertDialog extends AppCompatDialog {

    private DialogAdvertBinding binding;
    private Context context;
    private CountDownTimer timer;

    private AdvertDialog(Context context) {
        super(context);
        this.context = context;
        binding = DialogAdvertBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        initDialogView();
    }

    public DialogAdvertBinding getBinding() {
        return binding;
    }

    public void setBinding(DialogAdvertBinding binding) {
        this.binding = binding;
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
        p.width = d.getWidth() - 2 * 50; //设置dialog的宽度为当前手机屏幕的宽度-100,单位为dp
        getWindow().setAttributes(p);
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public AdvertDialog setTitle(@NonNull String title) {
        binding.title.setText(title);
        return this;
    }

    /**
     * 设置图片
     *
     * @param path
     * @return
     */
    public AdvertDialog setImage(@NonNull String path) {
        RequestOptions options = new RequestOptions();
//                .placeholder(R.drawable.icon_arrow)//图片加载出来前，显示的图片
//                .fallback(R.drawable.icon_arrow) //url为空的时候,显示的图片
//                .error(R.drawable.icon_arrow);//图片加载失败后，显示的图片
        Glide.with(context)
                .load(path)
//                .apply(options)
                .into(binding.iv);
        return this;
    }

    /**
     * positive按钮
     *
     * @param listener
     * @return
     */
    public AdvertDialog setPositive(final View.OnClickListener listener) {
        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view);
                }
            }
        });
        return this;
    }

    /**
     * negative按钮
     *
     * @param listener
     * @return
     */
    public AdvertDialog setNegative(final View.OnClickListener listener) {
        binding.btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view);
                }
                dismiss();
            }
        });
        return this;
    }

    /**
     * 设置倒计时时间
     *
     * @return
     */
    public AdvertDialog setTime(int time) {
        timer = new CountDownTimer(time * 1000 + 500, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                binding.btClose.setText("关闭 " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        };
        return this;
    }

    /**
     * 显示
     */
    public void show() {
        timer.start();
        binding.executePendingBindings();
        if (!super.isShowing()) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        timer.cancel();
        super.dismiss();
    }

    /**
     * Builder
     */
    public static class Builder {

        private Context context;
        private AdvertDialog dialog;

        public Builder(@NonNull Context context) {
            this.context = context.getApplicationContext();
            dialog = new AdvertDialog(context);
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public AdvertDialog.Builder setTitle(@NonNull String title) {
            dialog.setTitle(title);
            return this;
        }

        /**
         * 设置图片
         *
         * @param path
         * @return
         */
        public AdvertDialog.Builder setImage(@NonNull String path) {
            dialog.setImage(path);
            return this;
        }

        /**
         * positive按钮
         *
         * @param listener
         * @return
         */
        public AdvertDialog.Builder setPositive(final View.OnClickListener listener) {
            dialog.setPositive(listener);
            return this;
        }

        /**
         * negative按钮
         *
         * @param listener
         * @return
         */
        public AdvertDialog.Builder setNegative(final View.OnClickListener listener) {
            dialog.setNegative(listener);
            return this;
        }

        /**
         * 设置倒计时时间
         *
         * @param time
         * @return
         */
        public AdvertDialog.Builder setTime(final int time) {
            dialog.setTime(time);
            return this;
        }

        /**
         * 创建
         *
         * @return
         */
        public AdvertDialog create() {
            if (dialog == null) {
                dialog = new AdvertDialog(context);
            }
            return dialog;
        }

        /**
         * 显示
         */
        public void show() {
            if (dialog == null) {
                dialog = new AdvertDialog(context);
            }
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }

}

