package com.melon.mobile.entertainment.interactor.impl;

import android.content.Context;

import com.melon.mobile.commonlib.base.BaseLazyFragment;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.interactor.HomeInteractor;
import com.melon.mobile.entertainment.model.NavigationModel;
import com.melon.mobile.entertainment.ui.fragment.ImagesContainerFragment;
import com.melon.mobile.entertainment.ui.fragment.MusicsFragment;
import com.melon.mobile.entertainment.ui.fragment.VideosContainerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinye on 16/3/11.
 */
public class HomeInteractorImpl implements HomeInteractor {
    @Override
    public List<BaseLazyFragment> getPagerFragments() {
        List<BaseLazyFragment> fragments = new ArrayList<>();
        fragments.add(new ImagesContainerFragment());
        fragments.add(new VideosContainerFragment());
        fragments.add(new MusicsFragment());

        return fragments;
    }

    @Override
    public List<NavigationModel> getNavigationListData(Context context) {
        List<NavigationModel> navigationEntities = new ArrayList<>();
        String[] navigationArrays = context.getResources().getStringArray(R.array.navigation_list);
        navigationEntities.add(new NavigationModel("", navigationArrays[0], R.drawable.ic_picture));
        navigationEntities.add(new NavigationModel("", navigationArrays[1], R.drawable.ic_video));
        navigationEntities.add(new NavigationModel("", navigationArrays[2], R.drawable.ic_music));
        return navigationEntities;
    }
}

