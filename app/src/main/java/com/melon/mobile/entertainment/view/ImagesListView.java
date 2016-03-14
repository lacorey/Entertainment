package com.melon.mobile.entertainment.view;


import com.melon.mobile.entertainment.model.ImagesListModel;
import com.melon.mobile.entertainment.model.ResponseImagesListModel;
import com.melon.mobile.entertainment.view.base.BaseView;

public interface ImagesListView extends BaseView {

    void refreshListData(ResponseImagesListModel responseImagesListEntity);

    void addMoreListData(ResponseImagesListModel responseImagesListEntity);

    void navigateToImagesDetail(int position, ImagesListModel entity, int x, int y, int width, int height);

}
