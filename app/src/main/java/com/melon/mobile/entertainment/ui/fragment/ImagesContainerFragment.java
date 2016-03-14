package com.melon.mobile.entertainment.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.melon.mobile.commonlib.smartlayout.SmartTabLayout;
import com.melon.mobile.commonlib.widgets.XViewPager;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.model.BaseModel;
import com.melon.mobile.entertainment.presenter.Presenter;
import com.melon.mobile.entertainment.presenter.impl.ImagesContainerPresenterImpl;
import com.melon.mobile.entertainment.ui.activity.base.BaseFragment;
import com.melon.mobile.entertainment.ui.adapter.ImagesContainerPagerAdapter;
import com.melon.mobile.entertainment.view.CommonContainerView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by sinye on 16/3/11.
 */
public class ImagesContainerFragment extends BaseFragment implements CommonContainerView{
    private Presenter mImagesContainerPresenter = null;

    @InjectView(R.id.fragment_images_tab_smart)
    SmartTabLayout mSmartTabLayout;
    @InjectView(R.id.fragment_images_pager)
    XViewPager mViewPager;

    @Override
    protected void onFirstUserVisible() {
        mImagesContainerPresenter = new ImagesContainerPresenterImpl(mContext, this);
        mImagesContainerPresenter.initialized();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void initializePagerViews(final List<BaseModel> categoryList) {
        if (null != categoryList && !categoryList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(categoryList.size());
            mViewPager.setAdapter(new ImagesContainerPagerAdapter(getSupportFragmentManager(), categoryList));
            mSmartTabLayout.setViewPager(mViewPager);
            mSmartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    ImagesListFragment fragment = (ImagesListFragment) mViewPager.getAdapter().instantiateItem(mViewPager, position);
                    fragment.onPageSelected(position, categoryList.get(position).getId());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
}
