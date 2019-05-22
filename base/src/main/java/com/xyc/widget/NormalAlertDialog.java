package com.xyc.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.komect.base.R;
import com.komect.base.databinding.DialogNormalBinding;
import com.xyc.bean.DialogBean;


/**
 * Created by xieyusheng on 2017/11/6.
 */

public class NormalAlertDialog extends AppCompatDialog {

    private DialogNormalBinding binding;
    public DialogBean bean;

    private NormalAlertDialog(Context context) {
        super(context);
        binding = DialogNormalBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        initDialogView();
    }

    public DialogNormalBinding getBinding() {
        return binding;
    }

    public void setBinding(DialogNormalBinding binding) {
        this.binding = binding;
    }

    public DialogBean getBean() {
        return bean;
    }

    public void setBean(DialogBean bean) {
        this.bean = bean;
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
     * 设置标题
     *
     * @param title
     * @return
     */
    public NormalAlertDialog setTitle(@NonNull String title) {
        bean.setTitle(title);
        return this;
    }

    /**
     * 设置内容
     *
     * @param text
     * @return
     */
    public NormalAlertDialog setText(@NonNull String text) {
        bean.setText(text);
        return this;
    }


    /**
     * positive按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public NormalAlertDialog setPositive(@NonNull String text, final View.OnClickListener listener) {
        bean.setPositiveVisible(true);
        bean.setPositiveText(text);
        binding.btOk.setOnClickListener(new View.OnClickListener() {
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
     * negative按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public NormalAlertDialog setNegative(@NonNull String text, final View.OnClickListener listener) {
        bean.setNegativeVisible(true);
        bean.setNegativeText(text);
        binding.btCancel.setOnClickListener(new View.OnClickListener() {
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
     * 设置是否可以取消
     *
     * @param cancelable
     * @return
     */
    public NormalAlertDialog setIsCancelable(boolean cancelable) {
        setCancelable(cancelable);
        bean.setNegativeVisible(cancelable);
        binding.btOk.setBackground(cancelable ?
                getContext().getResources().getDrawable(R.drawable.shape_dialog_ok) :
                getContext().getResources().getDrawable(R.drawable.shape_dialog_ok2));
        return this;
    }

    /**
     * 显示
     */
    public void show() {
        binding.setProfile(bean);
        binding.executePendingBindings();
        if (!super.isShowing()) {
            super.show();
        }
    }

    /**
     * Builder
     */
    public static class Builder {

        private Context context;
        private NormalAlertDialog normalAlertDialog;

        public Builder(@NonNull Context context) {
            this.context = context.getApplicationContext();
            normalAlertDialog = new NormalAlertDialog(context);
            normalAlertDialog.setBean(new DialogBean());
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(@NonNull String title) {
            normalAlertDialog.getBean().setTitle(title);
            return this;
        }

        /**
         * 设置内容
         *
         * @param text
         * @return
         */
        public Builder setText(@NonNull String text) {
            normalAlertDialog.getBean().setText(text);
            return this;
        }

        /**
         * 设置是否可以取消
         *
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            if (normalAlertDialog == null) {
                normalAlertDialog = new NormalAlertDialog(context);
            }
            normalAlertDialog.setIsCancelable(cancelable);
            return this;
        }

        /**
         * positive按钮
         *
         * @param text
         * @param listener
         * @return
         */
        public Builder setPositive(@NonNull String text, final View.OnClickListener listener) {
            normalAlertDialog.getBean().setPositiveVisible(true);
            normalAlertDialog.getBean().setPositiveText(text);
            normalAlertDialog.getBinding().btOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(view);
                    }
                    if (normalAlertDialog == null) {
                        normalAlertDialog = new NormalAlertDialog(context);
                    }
                    normalAlertDialog.dismiss();
                }
            });
            return this;
        }

        /**
         * negative按钮
         *
         * @param text
         * @param listener
         * @return
         */
        public Builder setNegative(@NonNull String text, final View.OnClickListener listener) {
            normalAlertDialog.getBean().setNegativeVisible(true);
            normalAlertDialog.getBean().setNegativeText(text);
            normalAlertDialog.getBinding().btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(view);
                    }
                    if (normalAlertDialog == null) {
                        normalAlertDialog = new NormalAlertDialog(context);
                    }
                    normalAlertDialog.dismiss();
                }
            });
            return this;
        }

        /**
         * 创建
         *
         * @return
         */
        public NormalAlertDialog create() {
            if (normalAlertDialog == null) {
                normalAlertDialog = new NormalAlertDialog(context);
            }
            return normalAlertDialog;
        }

        /**
         * 显示
         */
        public void show() {
            if (normalAlertDialog == null) {
                normalAlertDialog = new NormalAlertDialog(context);
            }
            if (!normalAlertDialog.isShowing()) {
                normalAlertDialog.show();
            }
        }

    }

}
