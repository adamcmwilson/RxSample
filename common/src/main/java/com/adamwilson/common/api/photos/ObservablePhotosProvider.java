package com.adamwilson.common.api.photos;

import com.adamwilson.common.api.ImageSize;
import com.adamwilson.common.model.Photo;

import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class ObservablePhotosProvider implements ObsPhotosProvider {

    private static ObservablePhotosProvider     instance;
    private        BehaviorSubject<List<Photo>> photosSubject;

    private ObservablePhotosProvider() { }

    public static ObservablePhotosProvider getInstance() {
        if (instance == null)
            instance = new ObservablePhotosProvider();
        return instance;
    }

    @Override public boolean hasItems() {
        return photosSubject.hasValue();
    }

    @Override public Observable<List<Photo>> observePhotos() {
        if (photosSubject == null) {
            this.photosSubject = BehaviorSubject.create();
            this.photosSubject.buffer(1);
        }
        return photosSubject.asObservable();
    }

    @Override public void get(int count, String excludeCategoryCsv) {
        PhotosAPIProvider.getInstance().getApi()
                         .getPhotos(count, excludeCategoryCsv, ImageSize.getAll())
                         .flatMap(new PhotosFromResponseFunction())
                         .subscribe(photosSubject);
    }

}
