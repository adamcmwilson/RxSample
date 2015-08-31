package com.adamwilson.common.api.photos.response;

import com.adamwilson.common.api.ImageSize;
import com.adamwilson.common.api.photos.BasicPhotosProvider;
import com.adamwilson.common.api.photos.PhotosAPI;
import com.adamwilson.common.api.photos.PhotosAPIProvider;
import com.adamwilson.common.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotosProvider implements BasicPhotosProvider {

    PhotosAPI                   api;
    List<Photo>                 cache;
    List<Callback<List<Photo>>> callbacks;

    @Override public List<Photo> get(int count,
                                     String excludeCategoryCsv,
                                     Callback<List<Photo>> cb) {

        if (callbacks == null) {
            callbacks = new ArrayList<>(2);
        }

        callbacks.add(cb);

        if (api == null) api = PhotosAPIProvider.getInstance().getApi();

        api.getPhotos(new RawRestCallback(), count,
                      excludeCategoryCsv, ImageSize.getAll());

        return cache;
    }

    private class RawRestCallback implements Callback<GetPhotosResponse> {

        @Override public void success(GetPhotosResponse getPhotosResponse,
                                      Response response) {

            cache = getPhotosResponse.getPhotos();

            if (callbacks != null && !callbacks.isEmpty()) {
                for (int i = 0, size = callbacks.size(); i < size; i++) {
                    callbacks.get(i).success(cache, response);
                }
            }

        }

        @Override public void failure(RetrofitError error) {

            if (callbacks != null && !callbacks.isEmpty()) {
                for (int i = 0, size = callbacks.size(); i < size; i++) {
                    callbacks.get(i).failure(error);
                }
            }

        }
    }
}
