package com.melon.mobile.entertainment.view;

import android.view.animation.Animation;

public interface SplashView {

    void animateBackgroundImage(Animation animation);

    void initializeViews(String versionName, String copyright, int backgroundResId);

    void navigateToHomePage();
}
