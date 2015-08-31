package com.adamwilson.common.api.photos;

import com.adamwilson.common.api.photos.response.GetPhotosResponse;
import com.adamwilson.common.model.Photo;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class PhotosFromResponseFunction
        implements Func1<GetPhotosResponse, Observable<List<Photo>>> {

    @Override public Observable<List<Photo>> call(GetPhotosResponse response) {
        return Observable.just(response.getPhotos());
    }
}
