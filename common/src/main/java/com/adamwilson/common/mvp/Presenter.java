package com.adamwilson.common.mvp;

import android.view.View;

public abstract class Presenter {

    public Presenter(View view) {
        init(view);
    }

    protected abstract void init(final View view);

    protected abstract void teardown();


}
