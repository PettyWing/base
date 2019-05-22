package com.xyc.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyc.widget.TopBar;

/**
 * Created by xieyusheng on 2019/3/11.
 */

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements TopBar.OnTopBarEventListener {
    private static final String TAG = "BaseFragment";
    protected Activity mActivity;
    protected V binding;
    protected VM viewModel;
    protected int viewModelId;
    private boolean frgVisible = true; // fragment是否前台可见
    private boolean actVisible; // activity是否前台可见

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initContentView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViewDataBinding(view);
        initView(savedInstanceState);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    private void initViewDataBinding(View view) {
        binding = DataBindingUtil.bind(view);
        viewModelId = initViewId();
        viewModel = initViewModel();
        viewModel.create(mActivity);
        this.binding.setVariable(viewModelId, viewModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        frgVisible = !hidden;
        onVisible(frgVisible && actVisible);
        Log.d(TAG, "onHiddenChanged: " + getClass().getName() + (frgVisible && actVisible));
    }

    @Override
    public void onResume() {
        super.onResume();
        actVisible = true;
        onVisible(frgVisible);
        viewModel.attach();
        Log.d(TAG, "onResume: " + getClass().getName() + frgVisible);
    }

    @Override
    public void onPause() {
        super.onPause();
        actVisible = false;
        onVisible(false);
        viewModel.detach();
        Log.d(TAG, "onPause: " + getClass().getName() + "false");
    }

    protected abstract void onVisible(boolean visible);

    /**
     * @return xml文件id
     */
    protected abstract int initContentView();

    /**
     * 初始化viewModelId
     *
     * @return
     */
    protected abstract int initViewId();

    /**
     * 初始化viewModel
     *
     * @return
     */
    protected abstract VM initViewModel();


    /**
     * 初始化页面view
     */
    protected void initView(Bundle savedInstanceState) {

    }

    /**
     * 初始化页面data
     */
    protected void initData() {

    }

    /**
     * 绑定标题栏
     *
     * @param topBar
     */
    protected void bindTopBar(TopBar topBar) {
        topBar.setOnTopBarEventListener(this);
    }

    @Override
    public void onTopBarLeftClick(View leftItemView) {

    }

    @Override
    public void onTopBarRightClick(View rightItemView) {

    }

}

