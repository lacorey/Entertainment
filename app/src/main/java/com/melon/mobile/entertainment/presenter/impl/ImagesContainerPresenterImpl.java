package com.melon.mobile.entertainment.presenter.impl;

import android.content.Context;

import com.melon.mobile.entertainment.interactor.CommonContainerInteractor;
import com.melon.mobile.entertainment.interactor.impl.ImagesContainerInteractorImpl;
import com.melon.mobile.entertainment.presenter.Presenter;
import com.melon.mobile.entertainment.view.CommonContainerView;

public class ImagesContainerPresenterImpl implements Presenter {

    private Context mContext;
    private CommonContainerInteractor mCommonContainerInteractor;
    private CommonContainerView mCommonContainerView;

    public ImagesContainerPresenterImpl(Context context, CommonContainerView commonContainerView) {
        mContext = context;
        mCommonContainerView = commonContainerView;
        mCommonContainerInteractor = new ImagesContainerInteractorImpl();
    }

    @Override
    public void initialized() {
        mCommonContainerView.initializePagerViews(mCommonContainerInteractor.getCommonCategoryList(mContext));
    }
}
