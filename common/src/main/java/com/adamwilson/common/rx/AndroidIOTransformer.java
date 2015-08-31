package com.adamwilson.common.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AndroidIOTransformer<T> implements Observable.Transformer<T, T> {

    @Override public Observable<T> call(Observable<T> obs) {
        return obs.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread());
    }
}
