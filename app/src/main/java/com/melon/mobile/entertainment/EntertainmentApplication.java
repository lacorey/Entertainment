package com.melon.mobile.entertainment;

import com.melon.mobile.commonlib.base.BaseAppManager;
import com.melon.mobile.entertainment.api.ApiConstants;
import com.melon.mobile.entertainment.utils.ImageLoaderHelper;
import com.melon.mobile.entertainment.utils.VolleyHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by sinye on 16/3/10.
 */
public class EntertainmentApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();

        VolleyHelper.getInstance().init(this);
        ImageLoader.getInstance().init(ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(ApiConstants.Paths.IMAGE_LOADER_CACHE_PATH));
    }

    public String configDownloadPath() {
        return null;
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    public void exitApp() {
        BaseAppManager.getInstance().clear();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
