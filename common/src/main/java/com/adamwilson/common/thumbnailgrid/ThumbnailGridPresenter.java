package com.adamwilson.common.thumbnailgrid;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.adamwilson.common.R;
import com.adamwilson.common.model.Photo;
import com.adamwilson.common.mvp.Presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;

public class ThumbnailGridPresenter extends Presenter
        implements Observer<List<Photo>>, AdapterView.OnItemClickListener {

    GridView               grid;
    ThumbnailGridAdapter   adapter;
    Toast                  toast;
    WeakReference<Context> context;
    PublishSubject<Integer> selectedItemSubject = PublishSubject.create();

    public ThumbnailGridPresenter(Context context,
                                  View view) {
        super(view);
        this.context = new WeakReference<>(context);
    }

    @Override protected void init(View view) {
        this.grid = (GridView) view.findViewById(R.id.thumbnail_grid);

        if (grid == null)
            throw new RuntimeException("GridView is null.");

        grid.setOnItemClickListener(this);
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (selectedItemSubject != null) {
            selectedItemSubject.onNext(position);
        }
    }

    @Override public void onNext(List<Photo> thumbnailUrls) {
        if (thumbnailUrls == null) {
            grid.setAdapter(null);
            return;
        }

        adapter = new ThumbnailGridAdapter(context.get(),
                                           thumbnailUrls,
                                           R.drawable.placeholder);

        grid.setAdapter(adapter);
    }

    public Observable<Integer> observeSelectedItem() {
        return selectedItemSubject.asObservable();
    }

    public void scrollTo(final int position) {
        grid.setSelection(position);
    }

    @Override public void onError(Throwable e) {
        grid.setAdapter(null);
        toast = Toast.makeText(context.get(), "An error occurred...", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override public void onCompleted() { }

    @Override protected void teardown() {
        if (context != null)
            context.clear();
    }
}
