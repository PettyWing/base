package com.xyc.widget;

/**
 * Created by xieyusheng on 2017/9/30.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komect.base.R;
import com.komect.base.databinding.LayoutCommonTopbarBinding;

/**
 * 应用类通用的顶部导航栏.
 * Author by hf
 * Create on 16/9/13
 */
public class TopBar extends ConstraintLayout {
    /**
     * 导航栏事件回调接口
     */
    public interface OnTopBarEventListener {
        /**
         * 导航栏左侧item点击事件
         */
        void onTopBarLeftClick(View leftItemView);

        /**
         * 导航栏右侧item点击事件
         */
        void onTopBarRightClick(View rightItemView);
    }

    private OnTopBarEventListener mOnTopBarEventListener;
    private LayoutCommonTopbarBinding binding;
    private int backgroundColor;
    private Drawable leftIcon;
    private boolean leftIconVisible;
    private String title;
    private int titleColor;
    private Drawable rightIcon;
    private String rightTxt;
    private boolean dividerVisible;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContent(context);
        initAttrs(context, attrs);
        initView();
    }

    private void initContent(Context context) {
        binding = LayoutCommonTopbarBinding.inflate(LayoutInflater.from(context));
        this.addView(binding.getRoot(), ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.top_bar_height_fit));
        binding.btLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnTopBarEventListener != null) {
                    mOnTopBarEventListener.onTopBarLeftClick(view);
                }
            }
        });
        binding.txRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnTopBarEventListener != null) {
                    mOnTopBarEventListener.onTopBarRightClick(view);
                }
            }
        });
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TopBar, 0, 0);
        if (typeArray != null) {
            backgroundColor = typeArray.getColor(R.styleable.TopBar_topbar_bg_color, getResources().getColor(R.color.white));
            leftIcon = typeArray.getDrawable(R.styleable.TopBar_topbar_left_icon);
            leftIconVisible = typeArray.getBoolean(R.styleable.TopBar_topbar_left_icon_visible, true);
            title = typeArray.getString(R.styleable.TopBar_topbar_title_txt);
            titleColor = typeArray.getColor(R.styleable.TopBar_topbar_title_color, getResources().getColor(R.color.txt_color1));
            rightIcon = typeArray.getDrawable(R.styleable.TopBar_topbar_right_icon);
            rightTxt = typeArray.getString(R.styleable.TopBar_topbar_right_txt);
            dividerVisible = typeArray.getBoolean(R.styleable.TopBar_topbar_divider_visible, true);
            typeArray.recycle();//注意这里要释放掉
        }
    }

    private void initView() {
        binding.layoutTopbar.setBackgroundColor(backgroundColor);
        binding.btLeft.setImageDrawable(leftIcon == null ? getResources().getDrawable(R.drawable.btn_nav_back) : leftIcon);
        binding.btLeft.setVisibility(leftIconVisible ? VISIBLE : GONE);
        binding.tvTitle.setText(title);
        binding.tvTitle.setTextColor(titleColor);
        binding.txRight.setCompoundDrawablesWithIntrinsicBounds(null, null, rightIcon, null);
        binding.txRight.setText(rightTxt);
        boolean txRightVisible = !TextUtils.isEmpty(rightTxt) || rightIcon != null;
        binding.txRight.setVisibility(txRightVisible ? VISIBLE : GONE);
        binding.divider.setVisibility(dividerVisible ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置导航栏事件接口回调
     *
     * @param onTopBarEventListener
     */
    public void setOnTopBarEventListener(OnTopBarEventListener onTopBarEventListener) {
        mOnTopBarEventListener = onTopBarEventListener;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        binding.tvTitle.setText(title);
    }

    /**
     * 设置左侧按钮是否可见
     *
     * @param visible
     */
    public void setLeftIconVisible(boolean visible) {
        binding.btLeft.setVisibility(visible ? VISIBLE : GONE);
    }
}
