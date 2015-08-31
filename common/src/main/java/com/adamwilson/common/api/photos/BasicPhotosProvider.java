package com.adamwilson.common.api.photos;

import com.adamwilson.common.model.Photo;

import java.util.List;

import retrofit.Callback;

public interface BasicPhotosProvider {

    List<Photo> get(int count,
                    String excludeCategoryCsv,
                    Callback<List<Photo>> cb);

}
