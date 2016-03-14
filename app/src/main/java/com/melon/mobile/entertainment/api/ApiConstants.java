package com.melon.mobile.entertainment.api;

import android.os.Environment;

public class ApiConstants {

    public static final class Urls {
        public static final String BAIDU_IMAGES_URLS = "http://image.baidu.com/data/imgs";
        public static final String YOUKU_VIDEOS_URLS = "https://openapi.youku.com/v2/searches/video/by_keyword.json";
        public static final String YOUKU_USER_URLS = "https://openapi.youku.com/v2/users/show.json";
        public static final String DOUBAN_PLAY_LIST_URLS = "http://www.douban.com/j/app/radio/people";
    }

    public static final class Paths {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        public static final String IMAGE_LOADER_CACHE_PATH = "/Entertainment/Images/";
    }

    public static final class Integers {
        public static final int PAGE_LAZY_LOAD_DELAY_TIME_MS = 200;
    }
}