package com.melon.mobile.entertainment.presenter.impl;

import android.content.Context;

import com.melon.mobile.entertainment.interactor.HomeInteractor;
import com.melon.mobile.entertainment.interactor.impl.HomeInteractorImpl;
import com.melon.mobile.entertainment.presenter.Presenter;
import com.melon.mobile.entertainment.view.HomeView;

/**
 * Created by sinye on 16/3/11.
 */
public class HomePresenterImpl implements Presenter {
    private Context mContext = null;
    private HomeView mHomeView = null;
    private HomeInteractor mHomeInteractor = null;

    public HomePresenterImpl(Context context, HomeView homeView) {
        if (null == homeView) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }

        mContext = context;
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void initialized() {
        mHomeView.initializeViews(mHomeInteractor.getPagerFragments(), mHomeInteractor.getNavigationListData(mContext));
    }
}
