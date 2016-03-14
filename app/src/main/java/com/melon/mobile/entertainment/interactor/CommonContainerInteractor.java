package com.melon.mobile.entertainment.interactor;

import android.content.Context;

import com.melon.mobile.entertainment.model.BaseModel;

import java.util.List;

public interface CommonContainerInteractor {

    List<BaseModel> getCommonCategoryList(Context context);
}