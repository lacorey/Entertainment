package com.melon.mobile.entertainment.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.melon.mobile.commonlib.netstatus.NetUtils;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.ui.activity.base.BaseSwipeBackActivity;

/**
 * Created by sinye on 16/3/9.
 */
public class TestActivity extends BaseSwipeBackActivity {

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void customerToolbar() {
        if(mToolbar != null){
            mToolbar.setTitle("咻咻咻");
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }
}
