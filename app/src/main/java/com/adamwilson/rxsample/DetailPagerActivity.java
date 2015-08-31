package com.adamwilson.rxsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adamwilson.common.api.ImageSize;
import com.adamwilson.common.model.Image;
import com.adamwilson.common.model.Photo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailPagerActivity extends AppCompatActivity {

    public static final String KEY_START_POSITION   = "KEY_START_POSITION";
    public static final String KEY_PHOTO_COLLECTION = "KEY_PHOTO_COLLECTION";
    public static final String KEY_RETURN_POSITION  = "KEY_RETURN_POSITION";

    public static final int REQUEST_CODE = 7;

    @Bind(R.id.toolbar)     Toolbar   toolbar;
    @Bind(R.id.photo_pager) ViewPager photo_pager;

    private PhotoPageAdapter pageAdapter;
    private PageListener     pageListener;

    public static Intent getStartIntent(Context context,
                                        List<Photo> photos,
                                        final int startPosition) {
        Intent intent = new Intent(context, DetailPagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_START_POSITION, startPosition);
        bundle.putParcelableArrayList(KEY_PHOTO_COLLECTION, new ArrayList<Parcelable>(photos));
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_pager);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(0);
        }

        ArrayList<Photo> photos        = null;
        int              startPosition = 0;

        Intent intent = getIntent();
        Bundle args   = intent.getExtras();

        if (args != null) {
            photos = args.getParcelableArrayList(KEY_PHOTO_COLLECTION);
            startPosition = args.getInt(KEY_START_POSITION);
        }

        pageAdapter = new PhotoPageAdapter(this, photos);

        pageListener = new PageListener();
        photo_pager.setOffscreenPageLimit(1);
        photo_pager.addOnPageChangeListener(pageListener);

        photo_pager.setAdapter(pageAdapter);

        setStartScrollPosition(startPosition);
    }

    private void setStartScrollPosition(int position) {
        if (photo_pager != null) {
            if (pageAdapter.getCount() >= position)
                photo_pager.setCurrentItem(position, true);
        }
    }

    @Override public void onBackPressed() {
        setResult();
        super.onBackPressed();
    }

    @Override public boolean onSupportNavigateUp() {
        setResult();
        return super.onSupportNavigateUp();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(KEY_RETURN_POSITION, pageListener.getCurrentPage());
        setResult(Activity.RESULT_OK, intent);
    }

    private class PageListener implements ViewPager.OnPageChangeListener {

        private int currentPage;

        public int getCurrentPage() {
            return currentPage;
        }

        @Override public void onPageScrolled(int position,
                                             float positionOffset,
                                             int positionOffsetPixels) { }

        @Override public void onPageSelected(int position) {
            currentPage = position;
        }

        @Override public void onPageScrollStateChanged(int state) { }
    }

    private class PhotoPageAdapter extends PagerAdapter {

        LayoutInflater inflater;
        List<Photo>    items;

        public PhotoPageAdapter(Context context, List<Photo> photos) {
            inflater = LayoutInflater.from(context);
            items = photos;
        }

        @Override public View instantiateItem(ViewGroup container, int position) {
            if (items == null || items.isEmpty()) return null;

            Photo photo = items.get(position);

            if (photo == null) return null;

            ImageView view = (ImageView) inflater.inflate(R.layout.photo_page, container, false);

            final Image fullImage = photo.getImage(ImageSize.UNCROPPED_LONGEST_1080px);

            String url = fullImage == null ? null : fullImage.getUrl();
            if (url != null && url.trim().isEmpty()) url = null;

            Glide.with(DetailPagerActivity.this)
                 .load(url)
                 .into(view);

            container.addView(view);
            return view;
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
