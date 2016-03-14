package com.melon.mobile.entertainment.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.melon.mobile.commonlib.adapter.ListViewDataAdapter;
import com.melon.mobile.commonlib.adapter.ViewHolderBase;
import com.melon.mobile.commonlib.adapter.ViewHolderCreator;
import com.melon.mobile.commonlib.base.BaseLazyFragment;
import com.melon.mobile.commonlib.netstatus.NetUtils;
import com.melon.mobile.commonlib.widgets.XViewPager;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.model.NavigationModel;
import com.melon.mobile.entertainment.presenter.Presenter;
import com.melon.mobile.entertainment.presenter.impl.HomePresenterImpl;
import com.melon.mobile.entertainment.ui.activity.base.BaseActivity;
import com.melon.mobile.entertainment.ui.adapter.VPFragmentAdapter;
import com.melon.mobile.entertainment.view.HomeView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity implements HomeView{
    private static long DOUBLE_CLICK_TIME = 0L;
    private Presenter mHomePresenter = null;
    private ListViewDataAdapter<NavigationModel> mNavListAdapter = null;
    private int mCheckedListItemColorResIds[] = {
            R.color.navigation_checked_picture_text_color,
            R.color.navigation_checked_video_text_color,
            R.color.navigation_checked_music_text_color,
    };
    private int mCurrentMenuCheckedPos = 0;


    @InjectView(R.id.home_container)
    XViewPager mViewPager;
    @InjectView(R.id.home_navigation_list)
    ListView mNavListView;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle = null;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(this, R.id.home_container);
    }

    @Override
    protected void initViewsAndEvents() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);
        mHomePresenter = new HomePresenterImpl(this, this);
        mHomePresenter.initialized();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void customerToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("哒哒哒");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                    showToast(getString(R.string.double_click_exit));
                    DOUBLE_CLICK_TIME = System.currentTimeMillis();
                } else {
                    getBaseApplication().exitApp();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void initializeViews(List<BaseLazyFragment> fragments, List<NavigationModel> navigationList) {
        //设置Drawer
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                setTitle("列表");
            }
        };

        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        //设置content区域的fragment
        if (null != fragments && !fragments.isEmpty()) {
            mViewPager.setEnableScroll(false);
            mViewPager.setOffscreenPageLimit(fragments.size());
            mViewPager.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
        }
        //设置导航的条目
        mNavListAdapter = new ListViewDataAdapter<NavigationModel>(new ViewHolderCreator<NavigationModel>() {

            @Override
            public ViewHolderBase<NavigationModel> createViewHolder(int position) {

                return new ViewHolderBase<NavigationModel>() {

                    ImageView itemIcon;
                    TextView itemName;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout.item_home_navigation_list, null);
                        itemIcon = ButterKnife.findById(convertView, R.id.list_item_navigation_icon);
                        itemName = ButterKnife.findById(convertView, R.id.list_item_navigation_name);

                        return convertView;
                    }

                    @Override
                    public void showData(int i, NavigationModel navigationEntity) {
                        itemIcon.setImageResource(navigationEntity.getIconResId());
                        itemName.setText(navigationEntity.getName());

                        if (mCurrentMenuCheckedPos == i) {
                            // checked
                            itemName.setTextColor(getResources().getColor(mCheckedListItemColorResIds[i]));
                        } else {
                            // unchecked
                            itemName.setTextColor(getResources().getColor(android.R.color.black));
                        }
                    }
                };
            }
        });

        mNavListView.setAdapter(mNavListAdapter);
        mNavListAdapter.getDataList().addAll(navigationList);
        mNavListAdapter.notifyDataSetChanged();

        mNavListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentMenuCheckedPos = position;
                mNavListAdapter.notifyDataSetChanged();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mViewPager.setCurrentItem(mCurrentMenuCheckedPos, false);
            }
        });
    }
}
