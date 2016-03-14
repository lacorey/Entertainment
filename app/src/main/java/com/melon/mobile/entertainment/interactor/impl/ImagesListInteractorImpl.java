package com.melon.mobile.entertainment.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.google.gson.reflect.TypeToken;
import com.melon.mobile.entertainment.interactor.CommonListInteractor;
import com.melon.mobile.entertainment.listeners.BaseMultiLoadedListener;
import com.melon.mobile.entertainment.model.ResponseImagesListModel;
import com.melon.mobile.entertainment.utils.UriHelper;
import com.melon.mobile.entertainment.utils.VolleyHelper;

public class ImagesListInteractorImpl implements CommonListInteractor {

    private BaseMultiLoadedListener<ResponseImagesListModel> loadedListener = null;

    public ImagesListInteractorImpl(BaseMultiLoadedListener<ResponseImagesListModel> loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonListData(final String requestTag, final int event_tag, String keywords, int page) {
        GsonRequest<ResponseImagesListModel> gsonRequest = new GsonRequest<ResponseImagesListModel>(
                UriHelper.getInstance().getImagesListUrl(keywords, page),
                null,
                new TypeToken<ResponseImagesListModel>() {
                }.getType(),
                new Response.Listener<ResponseImagesListModel>() {
                    @Override
                    public void onResponse(ResponseImagesListModel response) {
                        loadedListener.onSuccess(event_tag, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadedListener.onException(error.getMessage());
                    }
                }
        );

        gsonRequest.setShouldCache(true);
        gsonRequest.setTag(requestTag);

        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }

}
