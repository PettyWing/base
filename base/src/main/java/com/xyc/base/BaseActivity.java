package com.xyc.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.xyc.Constants;
import com.xyc.widget.TopBar;

/**
 * Created by xieyusheng on 2019/3/11.
 */

public abstract class BaseActivity<V extends ViewDataBinding,
        VM extends BaseViewModel> extends AppCompatActivity
        implements TopBar.OnTopBarEventListener {
    protected V binding;
    protected VM viewModel;
    protected int viewModelId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .init();
        initViewDataBinding();
        getParams(getIntent().getBundleExtra(Constants.BUNDLE));
        initView(savedInstanceState);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.attach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.detach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel = null;
        binding.unbind();
    }

    private void initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, this.initContentView());
        viewModelId = initViewId();
        viewModel = initViewModel();
        viewModel.create(this);
        this.binding.setVariable(viewModelId, viewModel);
    }

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
     * 获取传递过来的参数
     */
    protected void getParams(Bundle bundle) {

    }

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
        finish();
    }

    @Override
    public void onTopBarRightClick(View rightItemView) {

    }

}
