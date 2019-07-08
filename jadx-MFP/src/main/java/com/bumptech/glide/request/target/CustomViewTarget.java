package com.bumptech.glide.request.target;

import android.support.annotation.IdRes;
import android.view.View;
import com.bumptech.glide.R;

public abstract class CustomViewTarget<T extends View, Z> implements Target<Z> {
    @IdRes
    private static final int VIEW_TAG_ID = R.id.glide_custom_view_target_tag;
    protected final T view;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Target for: ");
        sb.append(this.view);
        return sb.toString();
    }
}
