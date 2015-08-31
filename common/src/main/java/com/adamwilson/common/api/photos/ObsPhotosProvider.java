package com.adamwilson.common.api.photos;

import com.adamwilson.common.model.Photo;

import java.util.List;

import rx.Observable;

public interface ObsPhotosProvider {

    Observable<List<Photo>> observePhotos();

    void get(final int count,
             final String excludeCategoryCsv);

    boolean hasItems();

}
