package com.melon.mobile.entertainment.view;


import com.melon.mobile.commonlib.base.BaseLazyFragment;
import com.melon.mobile.entertainment.model.NavigationModel;

import java.util.List;

public interface HomeView {

    void initializeViews(List<BaseLazyFragment> fragments, List<NavigationModel> navigationList);

}
