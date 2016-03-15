package com.melon.mobile.entertainment.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.melon.mobile.commonlib.netstatus.NetUtils;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.presenter.Presenter;
import com.melon.mobile.entertainment.presenter.impl.SplashPresenterImpl;
import com.melon.mobile.entertainment.ui.activity.base.BaseActivity;
import com.melon.mobile.entertainment.view.SplashView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sinye on 16/3/15.
 */
public class SplashActivity extends BaseActivity implements SplashView{
    @InjectView(R.id.splash_image)
    ImageView mSplashImage;
    @InjectView(R.id.splash_version_name)
    TextView mVersionName;
    @InjectView(R.id.splash_copyright)
    TextView mCopyright;

    private Presenter mSplashPresenter = null;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void customerToolbar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mSplashPresenter = new SplashPresenterImpl(this, this);
        mSplashPresenter.initialized();
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
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        mSplashImage.startAnimation(animation);
    }

    @Override
    public void initializeViews(String versionName, String copyright, int backgroundResId) {
        mCopyright.setText(copyright);
        mVersionName.setText(versionName);
        mSplashImage.setImageResource(backgroundResId);
    }

    @Override
    public void navigateToHomePage() {
        readyGoThenKill(HomeActivity.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
