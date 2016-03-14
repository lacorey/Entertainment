package com.melon.mobile.entertainment.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.melon.mobile.commonlib.netstatus.NetUtils;
import com.melon.mobile.commonlib.widgets.SmoothImageView;
import com.melon.mobile.entertainment.R;
import com.melon.mobile.entertainment.ui.activity.base.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by sinye on 16/3/14.
 */
public class ImagesDetailActivity extends BaseActivity {
    public static final String INTENT_IMAGE_URL_TAG = "INTENT_IMAGE_URL_TAG";
    public static final String INTENT_IMAGE_X_TAG = "INTENT_IMAGE_X_TAG";
    public static final String INTENT_IMAGE_Y_TAG = "INTENT_IMAGE_Y_TAG";
    public static final String INTENT_IMAGE_W_TAG = "INTENT_IMAGE_W_TAG";
    public static final String INTENT_IMAGE_H_TAG = "INTENT_IMAGE_H_TAG";

    private String mImageUrl;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;

    @InjectView(R.id.images_detail_smooth_image)
    SmoothImageView mSmoothImageView;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void customerToolbar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mImageUrl = extras.getString(INTENT_IMAGE_URL_TAG);
        mLocationX = extras.getInt(INTENT_IMAGE_X_TAG);
        mLocationY = extras.getInt(INTENT_IMAGE_Y_TAG);
        mWidth = extras.getInt(INTENT_IMAGE_W_TAG);
        mHeight = extras.getInt(INTENT_IMAGE_H_TAG);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_images_detail;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mSmoothImageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        mSmoothImageView.transformIn();

        ImageLoader.getInstance().displayImage(mImageUrl, mSmoothImageView);

        mSmoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });

        mSmoothImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v2) {
                mSmoothImageView.transformOut();
            }
        });
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
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
