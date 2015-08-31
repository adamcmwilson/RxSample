package com.adamwilson.common.api.photos;

import com.adamwilson.common.api.photos.response.GetPhotosResponse;
import com.adamwilson.common.model.Photo;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class PhotosFromResponseFunction
        implements Func1<GetPhotosResponse, Observable<List<Photo>>> {

    @Override public Observable<List<Photo>> call(final GetPhotosResponse response) {

        return Observable.create(new Observable.OnSubscribe<List<Photo>>() {
            @Override public void call(Subscriber<? super List<Photo>> subscriber) {
                subscriber.onNext(response.getPhotos());
            }
        });
    }
}
