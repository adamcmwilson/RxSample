package com.adamwilson.rxsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import com.adamwilson.common.api.photos.ObservablePhotosProvider;
import com.adamwilson.common.api.photos.PhotosAPIProvider;
import com.adamwilson.common.model.Photo;
import com.adamwilson.common.rx.AndroidIOTransformer;
import com.adamwilson.common.thumbnailgrid.ThumbnailGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends Activity implements Observer<Integer> {

    @Bind(R.id.toolbar)             Toolbar           toolbar;
    @Bind(R.id.thumbnail_grid_view) ThumbnailGridView grid;

    private Subscription photosApiSubscription;
    private Subscription gridItemSelectionSubscription;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.setDebug(BuildConfig.DEBUG);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setActionBar(toolbar);

        if (grid == null) throw new RuntimeException("Grid is Null...");
    }

    private void observeGridItemSelections() {
        gridItemSelectionSubscription =
                grid.observeSelectedItem().subscribe(this);
    }

    @Override protected void onResume() {
        super.onResume();

        observeGridItemSelections();

        ObservablePhotosProvider provider = ObservablePhotosProvider.getInstance();

        photosApiSubscription = provider.observePhotos()
                                        .compose(new AndroidIOTransformer<List<Photo>>())
                                        .subscribe(grid.asObserver());

        if (!provider.hasItems())
            ObservablePhotosProvider.getInstance().get(
                    36, PhotosAPIProvider.getExcludedCategories());
    }

    @Override protected void onPause() {
        super.onPause();

        if (photosApiSubscription != null) {
            photosApiSubscription.unsubscribe();
        }
    }

    @Override public void onNext(Integer startPosition) {
        gridItemSelectionSubscription.unsubscribe();
        Intent intent = DetailPagerActivity.getStartIntent(this, grid.getItems(), startPosition);
        this.startActivityForResult(intent, DetailPagerActivity.REQUEST_CODE);
    }

    @Override public void onError(Throwable e) { }

    @Override public void onCompleted() { }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DetailPagerActivity.REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        int returnPosition = data.getExtras()
                                                 .getInt(DetailPagerActivity.KEY_RETURN_POSITION);

                        grid.scrollTo(returnPosition);
                        observeGridItemSelections();
                        break;
                }
                finishActivity(DetailPagerActivity.REQUEST_CODE);
                break;
        }
    }
}
