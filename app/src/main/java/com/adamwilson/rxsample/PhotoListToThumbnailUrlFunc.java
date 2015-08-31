package com.adamwilson.rxsample;

import com.adamwilson.common.model.Photo;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class PhotoListToThumbnailUrlFunc
        implements Func1<List<Photo>, Observable<List<String>>> {

    @Override public Observable<List<String>> call(List<Photo> photoList) {
        return Observable.from(photoList)
                         .flatMap(new Func1<Photo, Observable<String>>() {
                             @Override public Observable<String> call(Photo photo) {
                                 return Observable.just(photo.getImages().get(0).getUrl());
                             }
                         }).toList();
    }
}
