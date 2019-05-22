package com.xyc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.komect.base.R;
import com.komect.base.databinding.TxtArrowItemBinding;


/**
 * Created by xieyusheng on 2018/8/21.
 */

public class TxtArrowItemView extends ConstraintLayout
        implements CompoundButton.OnCheckedChangeListener {

    private TxtArrowItemBinding binding;
    private String leftTxt;
    private int leftColor;
    private float leftSize;
    private boolean rightArrowVisible;
    private String rightTxt;
    private boolean switchButtonVisible;
    private boolean isChecked;
    private boolean dividerVisible;
    private Drawable rightIcon;
    private int rightColor;
    private float rightSize;
    private Drawable leftIcon;
    private OnCheckedChangeListener listener;

    public TxtArrowItemView(Context context) {
        this(context, null);
    }

    public TxtArrowItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initView(context);
        initListener();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    private void initView(Context context) {
        binding = TxtArrowItemBinding.inflate(LayoutInflater.from(context));
        // 设置title
        binding.title.setText(leftTxt);
        binding.title.setTextColor(leftColor);
        binding.title.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftSize);
        if (leftIcon != null) {
            binding.title.setCompoundDrawablePadding((int) getResources().getDimension(R.dimen.dp_20));
            binding.title.setCompoundDrawablesWithIntrinsicBounds(leftIcon, null, null, null);
        }
        // 设置arrow
        binding.arrow.setVisibility(rightArrowVisible ? VISIBLE : GONE);
        // 设置右侧文字
        boolean txRightVisible = !TextUtils.isEmpty(rightTxt) || rightIcon != null;
        binding.rightTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, rightIcon, null);
        binding.rightTxt.setVisibility(txRightVisible ? VISIBLE : GONE);
        binding.rightTxt.setText(rightTxt);
        binding.rightTxt.setTextColor(rightColor);
        binding.rightTxt.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightSize);
        // 设置选择按钮
        binding.switchButton.setVisibility(switchButtonVisible ? VISIBLE : GONE);
        binding.switchButton.setChecked(isChecked);
        // 设置divider
        binding.divider.setVisibility(dividerVisible ? VISIBLE : INVISIBLE);

        addView(binding.getRoot(), ViewGroup.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.dp_45));
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TxtArrowItem, 0, 0);
        if (typeArray != null) {
            leftTxt = typeArray.getString(R.styleable.TxtArrowItem_ta_leftTxt);
            leftColor = typeArray.getColor(R.styleable.TxtArrowItem_ta_leftTxtColor, getResources().getColor(R.color.txt_color1));
            leftSize = typeArray.getDimension(R.styleable.TxtArrowItem_ta_leftTxtSize, getResources().getDimension(R.dimen.txt_size_16));
            rightArrowVisible = typeArray.getBoolean(R.styleable.TxtArrowItem_ta_rightArrowVisible, true);
            rightTxt = typeArray.getString(R.styleable.TxtArrowItem_ta_rightTxt);
            switchButtonVisible = typeArray.getBoolean(R.styleable.TxtArrowItem_ta_switchButtonVisible, false);
            rightIcon = typeArray.getDrawable(R.styleable.TxtArrowItem_ta_right_icon);
            rightColor = typeArray.getColor(R.styleable.TxtArrowItem_ta_rightTxtColor, getResources().getColor(R.color.txt_color2));
            rightSize = typeArray.getDimension(R.styleable.TxtArrowItem_ta_rightTxtSize, getResources().getDimension(R.dimen.txt_size_14));
            isChecked = typeArray.getBoolean(R.styleable.TxtArrowItem_ta_checked, true);
            dividerVisible = typeArray.getBoolean(R.styleable.TxtArrowItem_ta_dividerVisible, true);
            leftIcon = typeArray.getDrawable(R.styleable.TxtArrowItem_ta_left_icon);
            typeArray.recycle();//注意这里要释放掉
        }
    }

    private void initListener() {
        binding.switchButton.setOnCheckedChangeListener(this);
    }

    public void setLeftTxt(String leftTxt) {
        binding.title.setText(leftTxt);
    }

    public void setRightTxt(String rightTxt) {
        this.rightTxt = rightTxt;
        binding.rightTxt.setVisibility(TextUtils.isEmpty(rightTxt) ? GONE : VISIBLE);
        binding.rightTxt.setText(rightTxt);
    }

    public void setDividerVisible(boolean dividerVisible) {
        binding.divider.setVisibility(dividerVisible ? VISIBLE : INVISIBLE);
    }

    public void setRightIcon(int rightIconId) {
        if (rightIconId != 0) {
            binding.rightTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(rightIconId), null);
            binding.rightTxt.setVisibility(VISIBLE);
        } else {
            binding.rightTxt.setVisibility(GONE);
        }
    }

    public void setChecked(boolean checked) {
        binding.switchButton.setChecked(checked);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setRightArrowVisible(boolean rightArrowVisible) {
        this.rightArrowVisible = rightArrowVisible;
        binding.arrow.setVisibility(rightArrowVisible ? VISIBLE : GONE);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.isChecked = isChecked;
        binding.switchButton.setChecked(isChecked);
        listener.onCheckedChanged(buttonView, isChecked);
    }


    public interface OnCheckedChangeListener {
        void onCheckedChanged(CompoundButton view, boolean isChecked);
    }
}

