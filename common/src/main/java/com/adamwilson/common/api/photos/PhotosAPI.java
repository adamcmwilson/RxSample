package com.adamwilson.common.api.photos;

import com.adamwilson.common.api.photos.response.GetPhotosResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface PhotosAPI {

    @GET("/photos") Observable<GetPhotosResponse> getPhotos(
            @Query("rpp") int count,
            @Query("exclude") String excludedCategoryCsv,
            @Query("image_size[]") int... imageSize);


    @GET("/photos") void getPhotos(
            Callback<GetPhotosResponse> cb,
            @Query("rpp") int count,
            @Query("exclude") String excludedCategoryCsv,
            @Query("image_size[]") int... imageSize);

}
