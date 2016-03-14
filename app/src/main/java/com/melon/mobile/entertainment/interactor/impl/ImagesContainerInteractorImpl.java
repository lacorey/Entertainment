package com.melon.mobile.entertainment.interactor.impl;

import android.content.Context;


import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.interactor.CommonContainerInteractor;
import com.melon.mobile.entertainment.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片流实现
 */
public class ImagesContainerInteractorImpl implements CommonContainerInteractor {
    @Override
    public List<BaseModel> getCommonCategoryList(Context context) {
        List<BaseModel> resultData = new ArrayList<>();
        String[] imagesCategoryArrayId = context.getResources().getStringArray(R.array.images_category_list_id);
        String[] imagesCategoryArrayName = context.getResources().getStringArray(R.array.images_category_list_name);
        resultData.add(new BaseModel(imagesCategoryArrayId[0], imagesCategoryArrayName[0]));
        resultData.add(new BaseModel(imagesCategoryArrayId[1], imagesCategoryArrayName[1]));
        resultData.add(new BaseModel(imagesCategoryArrayId[2], imagesCategoryArrayName[2]));
        resultData.add(new BaseModel(imagesCategoryArrayId[3], imagesCategoryArrayName[3]));
        resultData.add(new BaseModel(imagesCategoryArrayId[4], imagesCategoryArrayName[4]));
        resultData.add(new BaseModel(imagesCategoryArrayId[5], imagesCategoryArrayName[5]));
        return resultData;
    }
}
