package com.adamwilson.common.thumbnailgrid;

import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;

import com.adamwilson.common.R;
import com.adamwilson.common.api.ImageSize;
import com.adamwilson.common.model.Image;
import com.adamwilson.common.model.Photo;

import java.util.ArrayList;


public class ThumbnailGridPresenterTest extends AndroidTestCase {

    public void test_grid_not_found_exception() throws Exception {

        View badView = new View(getContext());

        boolean exCaught = false;

        try {
            new ThumbnailGridPresenter(getContext(), badView);
        } catch (RuntimeException rte) {
            exCaught = true;
        }

        assertTrue("Exception not thrown, or was incorrect type.", exCaught);
    }

    public void test_gridAdapterSet_onNext() throws Exception {

        View gridView = getThumbnailGridView();

        ThumbnailGridPresenter presenter = new ThumbnailGridPresenter(getContext(), gridView);
        assertNull(presenter.grid.getAdapter());

        presenter.onNext(null);
        assertNull(presenter.grid.getAdapter());

        final int COUNT = 5;

        final ArrayList<Photo> testThumbs = getValidPhotos(COUNT);

        presenter.onNext(testThumbs);
        assertEquals(COUNT, presenter.grid.getAdapter().getCount());

        presenter.onNext(null);
        assertNull(presenter.grid.getAdapter());
    }

    public void test_onError() throws Exception {

        View gridView = getThumbnailGridView();

        ThumbnailGridPresenter presenter = new ThumbnailGridPresenter(getContext(), gridView);
        assertNotNull(presenter);
        assertNull(presenter.toast);

        presenter.onNext(getValidPhotos(5));
        assertTrue(presenter.grid.getAdapter().getCount() > 0);

        presenter.onError(new Throwable());
        assertNull(presenter.grid.getAdapter());
        assertNotNull(presenter.toast);
    }

    private ArrayList<Photo> getValidPhotos(final int count) {
        ArrayList<Photo> testThumbs = new ArrayList<>();

        ArrayList<Image> images          = new ArrayList<>();
        int[]            validImageSizes = ImageSize.getAll();
        for (int validImageSize : validImageSizes) {
            Image image = new Image(validImageSize, "URL", "URL_HTTPS", "FORMAT");
            images.add(image);
        }

        for (int i = 0; i < count; i++) {
            Photo photo = new Photo();
            photo.setImages(images);
            testThumbs.add(photo);
        }

        assertNotNull(testThumbs);
        assertEquals(count, testThumbs.size());

        return testThumbs;
    }

    private View getThumbnailGridView() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.thumbnail_grid, null);
        assertNotNull(view);
        View gridView = view.findViewById(R.id.thumbnail_grid);
        assertNotNull(gridView);
        return view;
    }
}