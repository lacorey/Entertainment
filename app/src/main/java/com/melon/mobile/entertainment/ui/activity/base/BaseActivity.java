package com.melon.mobile.entertainment.ui.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.melon.mobile.commonlib.base.BaseAppCompatActivity;
import com.melon.mobile.entertainment.EntertainmentApplication;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.view.base.BaseView;

/**
 * Created by sinye on 16/3/9.
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView{
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
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        if (null != mToolbar) {
            mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            customerToolbar();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
    }
}
