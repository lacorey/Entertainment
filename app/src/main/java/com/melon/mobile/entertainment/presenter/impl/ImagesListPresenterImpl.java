package com.melon.mobile.entertainment.presenter.impl;

import android.content.Context;
import android.provider.SyncStateContract;

import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.common.Constants;
import com.melon.mobile.entertainment.interactor.CommonListInteractor;
import com.melon.mobile.entertainment.interactor.impl.ImagesListInteractorImpl;
import com.melon.mobile.entertainment.listeners.BaseMultiLoadedListener;
import com.melon.mobile.entertainment.model.ImagesListModel;
import com.melon.mobile.entertainment.model.ResponseImagesListModel;
import com.melon.mobile.entertainment.presenter.ImagesListPresenter;
import com.melon.mobile.entertainment.view.ImagesListView;

public class ImagesListPresenterImpl implements ImagesListPresenter, BaseMultiLoadedListener<ResponseImagesListModel> {

    private Context mContext = null;
    private ImagesListView mImagesListView = null;
    private CommonListInteractor mCommonListInteractor = null;

    public ImagesListPresenterImpl(Context context, ImagesListView imagesListView) {
        mContext = context;
        mImagesListView = imagesListView;
        mCommonListInteractor = new ImagesListInteractorImpl(this);
    }

    @Override
    public void onSuccess(int event_tag, ResponseImagesListModel data) {
        mImagesListView.hideLoading();
        if (event_tag == Constants.EVENT_REFRESH_DATA) {
            mImagesListView.refreshListData(data);
        } else if (event_tag == Constants.EVENT_LOAD_MORE_DATA) {
            mImagesListView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mImagesListView.hideLoading();
        mImagesListView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mImagesListView.hideLoading();
        mImagesListView.showError(msg);
    }

    @Override
    public void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh) {
        mImagesListView.hideLoading();
        if (!isSwipeRefresh) {
            mImagesListView.showLoading(mContext.getString(R.string.common_loading_message));
        }
        mCommonListInteractor.getCommonListData(requestTag, event_tag, keywords, page);
    }

    @Override
    public void onItemClickListener(int position, ImagesListModel entity, int x, int y, int width, int height) {
        mImagesListView.navigateToImagesDetail(position, entity, x, y, width, height);
    }
}
