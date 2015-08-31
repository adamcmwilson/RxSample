package com.adamwilson.rxsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.adamwilson.common.api.photos.PhotosAPIProvider;
import com.adamwilson.common.api.photos.response.PhotosProvider;
import com.adamwilson.common.model.Photo;
import com.adamwilson.common.thumbnailgrid.ThumbnailGridAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotoGridActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.toolbar)    Toolbar  toolbar;
    @Bind(R.id.photo_grid) GridView photo_grid;

    List<Photo>          items;
    ThumbnailGridAdapter thumbnailGridAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_grid);
        ButterKnife.bind(this);

        setActionBar(toolbar);

        if (photo_grid == null) throw new RuntimeException("Grid is Null...");

        setupGridView(
                new PhotosProvider().get(
                        36, PhotosAPIProvider.getExcludedCategories(),
                        new GetPhotosCallback())
                     );
    }

    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = DetailPagerActivity.getStartIntent(this, items, position);
        this.startActivityForResult(intent, DetailPagerActivity.REQUEST_CODE);
    }

    @Override public void onNothingSelected(AdapterView<?> parent) { /* do nothing */ }

    private void setupGridView(final List<Photo> photos) {

        items = photos;

        if (photos != null && !photos.isEmpty()) {

            thumbnailGridAdapter = new ThumbnailGridAdapter(
                    this, items, R.drawable.placeholder);

            photo_grid.setAdapter(thumbnailGridAdapter);

        }
    }

    private class GetPhotosCallback implements Callback<List<Photo>> {
        @Override public void success(List<Photo> photos, Response response) {
            setupGridView(photos);
        }

        @Override public void failure(RetrofitError error) {
            Toast.makeText(PhotoGridActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
