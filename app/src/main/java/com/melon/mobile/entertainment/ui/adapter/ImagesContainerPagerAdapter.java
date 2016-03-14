package com.melon.mobile.entertainment.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.melon.mobile.entertainment.model.BaseModel;
import com.melon.mobile.entertainment.ui.fragment.ImagesListFragment;

import java.util.List;

public class ImagesContainerPagerAdapter extends FragmentPagerAdapter {

    private List<BaseModel> mCategoryList = null;

    public ImagesContainerPagerAdapter(FragmentManager fm, List<BaseModel> categoryList) {
        super(fm);
        mCategoryList = categoryList;
    }

    @Override
    public Fragment getItem(int position) {
        return new ImagesListFragment();
    }

    @Override
    public int getCount() {
        return null != mCategoryList ? mCategoryList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null != mCategoryList ? mCategoryList.get(position).getName() : null;
    }
}
