package com.melon.mobile.entertainment.interactor;

import android.content.Context;


import com.melon.mobile.commonlib.base.BaseLazyFragment;
import com.melon.mobile.entertainment.model.NavigationModel;

import java.util.List;

public interface HomeInteractor {

    List<BaseLazyFragment> getPagerFragments();

    List<NavigationModel> getNavigationListData(Context context);
}
