package com.xyc.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.komect.base.R;
import com.xyc.utils.ResourceUtil;

/**
 * Created by xieyusheng on 2019/3/16.
 */

public class Indicator extends LinearLayout {

    private Context context;
    private int currentPosition = 0;

    public Indicator(Context context) {
        this(context, null);
    }

    public Indicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    /**
     * 初始化指示器
     */
    public void initIndicator(int cnt) {
        if (cnt > 9) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        removeAllViews();
        for (int i = 0; i < cnt; i++) {
            //创建底部指示器(小圆点)
            View view = new View(context);
            view.setBackgroundResource(R.drawable.indicator);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ResourceUtil.getDisFromRes(context, R.dimen.dp_5),
                    ResourceUtil.getDisFromRes(context, R.dimen.dp_5));
            //设置间隔
            if (i != 0) {
                layoutParams.leftMargin = ResourceUtil.getDisFromRes(context, R.dimen.dp_10);
            }
            //添加到LinearLayout
            addView(view, layoutParams);
        }
        setEnable(currentPosition);
    }

    public void setEnable(int position) {
        if (getChildAt(currentPosition) != null) {
            getChildAt(currentPosition).setEnabled(false);
        }
        if (getChildAt(position) != null) {
            getChildAt(position).setEnabled(true);
        }
        currentPosition = position;
    }

}
