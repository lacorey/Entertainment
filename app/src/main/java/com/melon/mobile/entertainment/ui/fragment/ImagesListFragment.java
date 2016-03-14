package com.melon.mobile.entertainment.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.melon.mobile.commonlib.adapter.ListViewDataAdapter;
import com.melon.mobile.commonlib.adapter.ViewHolderBase;
import com.melon.mobile.commonlib.adapter.ViewHolderCreator;
import com.melon.mobile.commonlib.netstatus.NetUtils;
import com.melon.mobile.commonlib.pla.PLAAdapterView;
import com.melon.mobile.commonlib.pla.PLAImageView;
import com.melon.mobile.commonlib.widgets.XSwipeRefreshLayout;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.api.ApiConstants;
import com.melon.mobile.entertainment.common.Constants;
import com.melon.mobile.entertainment.common.OnCommonPageSelectedListener;
import com.melon.mobile.entertainment.model.ImagesListModel;
import com.melon.mobile.entertainment.model.ResponseImagesListModel;
import com.melon.mobile.entertainment.presenter.ImagesListPresenter;
import com.melon.mobile.entertainment.presenter.impl.ImagesListPresenterImpl;
import com.melon.mobile.entertainment.ui.activity.ImagesDetailActivity;
import com.melon.mobile.entertainment.ui.activity.base.BaseFragment;
import com.melon.mobile.entertainment.utils.UriHelper;
import com.melon.mobile.entertainment.view.ImagesListView;
import com.melon.mobile.entertainment.widgets.PLALoadMoreListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImagesListFragment extends BaseFragment implements ImagesListView, OnCommonPageSelectedListener,
        PLALoadMoreListView.OnLoadMoreListener, PLAAdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.fragment_images_list_swipe_layout)
    XSwipeRefreshLayout mSwipeRefreshLayout;

    @InjectView(R.id.fragment_images_list_list_view)
    PLALoadMoreListView mListView;

    /**
     * this variable must be initialized.
     */
    private static String mCurrentImagesCategory = null;

    /**
     * the page number
     */
    private int mCurrentPage = 0;

    private ImagesListPresenter mImagesListPresenter = null;
    private ListViewDataAdapter<ImagesListModel> mListViewAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentImagesCategory = getResources().getStringArray(R.array.images_category_list_id)[0];
    }

    @Override
    protected void onFirstUserVisible() {
        mCurrentPage = 0;
        mImagesListPresenter = new ImagesListPresenterImpl(mContext, this);

        if (NetUtils.isNetworkConnected(mContext)) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mImagesListPresenter.loadListData(TAG, Constants.EVENT_REFRESH_DATA, mCurrentImagesCategory,
                                mCurrentPage, false);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImagesListPresenter.loadListData(TAG, Constants.EVENT_REFRESH_DATA, mCurrentImagesCategory,
                            mCurrentPage, false);
                }
            });
        }
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    @Override
    protected void initViewsAndEvents() {
        mListViewAdapter = new ListViewDataAdapter<ImagesListModel>(new ViewHolderCreator<ImagesListModel>() {
            @Override
            public ViewHolderBase<ImagesListModel> createViewHolder(int position) {
                return new ViewHolderBase<ImagesListModel>() {

                    PLAImageView mItemImage;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout.item_images_list, null);
                        mItemImage = ButterKnife.findById(convertView, R.id.list_item_images_list_image);

                        return convertView;
                    }

                    @Override
                    public void showData(int position, ImagesListModel itemData) {
                        int width = itemData.getThumbnailWidth();
                        int height = itemData.getThumbnailHeight();

                        String imageUrl = itemData.getThumbnailUrl();

                        if (!TextUtils.isEmpty(imageUrl)) {
                            ImageLoader.getInstance().displayImage(imageUrl, mItemImage);
                        }

                        mItemImage.setImageWidth(width);
                        mItemImage.setImageHeight(height);
                    }
                };
            }
        });

        mListView.setOnItemClickListener(this);
        mListView.setOnLoadMoreListener(this);
        mListView.setAdapter(mListViewAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images_list;
    }

    // TODO: 16/3/11 modify by sinye
//    @Override
//    protected void onEventComming(EventCenter eventCenter) {
//
//    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void refreshListData(ResponseImagesListModel responseImagesListEntity) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (null != responseImagesListEntity) {
            if (null != mListViewAdapter) {
                mListViewAdapter.getDataList().clear();
                mListViewAdapter.getDataList().addAll(responseImagesListEntity.getImgs());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (null != mListView) {
                if (UriHelper.getInstance().calculateTotalPages(responseImagesListEntity.getTotalNum()) > mCurrentPage) {
                    mListView.setCanLoadMore(true);
                } else {
                    mListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void addMoreListData(ResponseImagesListModel responseImagesListEntity) {
        if (null != mListView) {
            mListView.onLoadMoreComplete();
        }

        if (null != responseImagesListEntity) {
            if (null != mListViewAdapter) {
                mListViewAdapter.getDataList().addAll(responseImagesListEntity.getImgs());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (null != mListView) {
                if (UriHelper.getInstance().calculateTotalPages(responseImagesListEntity.getTotalNum()) > mCurrentPage) {
                    mListView.setCanLoadMore(true);
                } else {
                    mListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void navigateToImagesDetail(int position, ImagesListModel entity, int x, int y, int width, int height) {
        Bundle extras = new Bundle();
        extras.putString(ImagesDetailActivity.INTENT_IMAGE_URL_TAG, entity.getThumbnailUrl());
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_X_TAG, x);
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_Y_TAG, y);
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_W_TAG, width);
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_H_TAG, height);
        readyGo(ImagesDetailActivity.class, extras);
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onPageSelected(int position, String keywords) {
        mCurrentImagesCategory = keywords;
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mImagesListPresenter.loadListData(TAG, Constants.EVENT_REFRESH_DATA, mCurrentImagesCategory, mCurrentPage,
                true);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        mImagesListPresenter.loadListData(TAG, Constants.EVENT_LOAD_MORE_DATA, mCurrentImagesCategory, mCurrentPage, true);
    }

    @Override
    public void showError(String msg) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImagesListPresenter.loadListData(TAG, Constants.EVENT_REFRESH_DATA, mCurrentImagesCategory,
                        mCurrentPage, false);
            }
        });
    }

    @Override
    public void onItemClick(PLAAdapterView<?> parent, View view, int position, long id) {
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        location[1] += statusBarHeight;

        int width = view.getWidth();
        int height = view.getHeight();

        if (null != mListViewAdapter) {
            if (position >= 0 && position < mListViewAdapter.getDataList().size()) {
                mImagesListPresenter.onItemClickListener(position,
                        mListViewAdapter.getDataList().get(position),
                        location[0],
                        location[1],
                        width,
                        height);
            }
        }
    }
}