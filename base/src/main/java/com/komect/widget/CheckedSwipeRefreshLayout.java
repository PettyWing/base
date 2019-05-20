package com.komect.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by 51testing on 2018/3/7.
 */

public class CheckedSwipeRefreshLayout extends SwipeRefreshLayout {
    private AbsListView listView;

    public CheckedSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CheckedSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnChildScrollUpCallback(new OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
                if (listView != null) {
                    return ViewCompat.canScrollVertically(listView, -1);
                } else {
                    return ViewCompat.canScrollVertically(child, -1);
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        listView = findList();
    }

    /**
     * 查找是不是有列表的View
     *
     * @return 返回是否有子View的列表的View
     */
    private AbsListView findList() {
        ViewGroup childView = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if ((child instanceof ViewGroup)) {
                childView = (ViewGroup) child;
                break;
            }
        }
        if (childView == null) {
            return null;
        }
        for (int i = 0; i < childView.getChildCount(); i++) {
            View childrenView = childView.getChildAt(i);
            if (childrenView instanceof AbsListView) {
                return (AbsListView) childrenView;
            }
        }
        return null;
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return !isRefreshing() && super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

}
