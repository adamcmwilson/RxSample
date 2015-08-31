package com.adamwilson.common.thumbnailgrid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.adamwilson.common.R;
import com.adamwilson.common.model.Photo;

import java.util.List;

import rx.Observable;
import rx.Observer;

public class ThumbnailGridView extends FrameLayout {

    ThumbnailGridPresenter presenter;

    public ThumbnailGridView(Context context) {
        this(context, null);
    }

    public ThumbnailGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThumbnailGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final View layout = LayoutInflater.from(context).inflate(
                R.layout.thumbnail_grid, this, false);

        presenter = new ThumbnailGridPresenter(context, layout);

        this.addView(layout);
    }

    public void scrollTo(final int pos) {
        if (presenter != null)
            presenter.scrollTo(pos);
    }

    public List<Photo> getItems() {
        if (presenter == null) return null;
        return presenter.adapter.getItems();
    }

    public Observer<List<Photo>> asObserver() {
        return presenter;
    }

    public Observable<Integer> observeSelectedItem() {
        return presenter.observeSelectedItem();
    }

}
