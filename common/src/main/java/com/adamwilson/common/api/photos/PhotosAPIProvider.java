package com.adamwilson.common.api.photos;

import com.adamwilson.common.CsvUtil;
import com.adamwilson.common.api.Category;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class PhotosAPIProvider {

    private final String API_URL = "https://api.500px.com/v1";

    private PhotosAPI photosAPI;

    private static PhotosAPIProvider instance;

    public static PhotosAPIProvider getInstance() {
        if (instance == null) instance = new PhotosAPIProvider();
        return instance;
    }

    private PhotosAPIProvider() {
        photosAPI = new RestAdapter.Builder()
                .setRequestInterceptor(new ApiInterceptor())
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(API_URL)
                .build()
                .create(PhotosAPI.class);
    }

    public PhotosAPI getApi() {
        return photosAPI;
    }

    private class ApiInterceptor implements RequestInterceptor {
        @Override public void intercept(RequestFacade request) {
            request.addQueryParam("consumer_key", "LmS2m0VEPcyCvwrbllCn68dMXhTQrBDx2JXF6nMx");
        }
    }

    public static String getExcludedCategories() {
        return CsvUtil.toCsv(Category.NUDE,
                             Category.CELEBRITIES);
    }
}
