package com.adamwilson.common.thumbnailgrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.adamwilson.common.R;
import com.adamwilson.common.api.ImageSize;
import com.adamwilson.common.model.Photo;
import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;

public class ThumbnailGridAdapter extends BaseAdapter {

    private       WeakReference<Context> context;
    private       List<Photo>            items;
    private       LayoutInflater         inflater;
    private final int                    placeholderDrawableResId;

    public ThumbnailGridAdapter(Context context,
                                final List<Photo> items,
                                final int placeholderDrawableResId) {

        this.context = new WeakReference<>(context);
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.placeholderDrawableResId = placeholderDrawableResId;
    }

    public List<Photo> getItems() {
        return items;
    }

    @Override public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override public Photo getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = inflater.inflate(R.layout.thumbnail_grid_item, parent, false);

        Photo photo = getItem(position);

        String url = photo.getImage(ImageSize.CROPPED_px440).getUrl();

        if (url != null && url.trim().isEmpty()) url = null;

        ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail_grid_item_image);

        Glide.with(context.get())
             .load(isValid(url) ? url : null)
             .placeholder(placeholderDrawableResId)
             .into(imageView);

        return view;
    }

    private boolean isValid(String imageUrl) {return imageUrl != null && !imageUrl.isEmpty();}

}
