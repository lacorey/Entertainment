package com.melon.mobile.entertainment.ui.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.melon.mobile.commonlib.base.BaseSwipeBackCompatActivity;
import com.melon.mobile.entertainment.EntertainmentApplication;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.view.base.BaseView;

import butterknife.ButterKnife;

/**
 * Created by sinye on 16/3/10.
 */
public abstract class BaseSwipeBackActivity extends BaseSwipeBackCompatActivity implements BaseView {
    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        customerToolbar();
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (mToolbarTitle != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 16/3/10 modify by sinye
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO: 16/3/10 modify by sinye
//        MobclickAgent.onPause(this);
    }

    protected EntertainmentApplication getBaseApplication() {
        return (EntertainmentApplication) getApplication();
    }


    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    protected abstract boolean isApplyKitKatTranslucency();

    protected abstract void customerToolbar();
}
