package com.melon.mobile.entertainment.presenter;


import com.melon.mobile.entertainment.model.ImagesListModel;

public interface ImagesListPresenter {

    void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh);

    void onItemClickListener(int position, ImagesListModel entity, int x, int y, int width, int height);

}
