package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class BitmapTransitionFactory extends BitmapContainerTransitionFactory<Bitmap> {
    /* access modifiers changed from: protected */
    @NonNull
    public Bitmap getBitmap(@NonNull Bitmap bitmap) {
        return bitmap;
    }
}
