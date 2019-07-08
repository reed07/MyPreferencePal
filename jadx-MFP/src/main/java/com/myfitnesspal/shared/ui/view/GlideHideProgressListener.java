package com.myfitnesspal.shared.ui.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GlideHideProgressListener implements RequestListener<Drawable> {
    private View imageView;
    private View progressView;

    public void setLoading(View view, View view2) {
        this.progressView = view;
        this.imageView = view2;
        view.setVisibility(0);
        view2.setVisibility(4);
    }

    public void setLoaded(View view, View view2) {
        this.progressView = view;
        this.imageView = view2;
        view.setVisibility(8);
        view2.setVisibility(0);
    }

    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
        this.progressView.setVisibility(8);
        this.imageView.setVisibility(0);
        return false;
    }

    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
        this.progressView.setVisibility(8);
        this.imageView.setVisibility(0);
        return false;
    }
}
