package com.bumptech.glide.load.resource.transcode;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.LazyBitmapDrawableResource;
import com.bumptech.glide.util.Preconditions;

public class BitmapDrawableTranscoder implements ResourceTranscoder<Bitmap, BitmapDrawable> {
    private final Resources resources;

    public BitmapDrawableTranscoder(@NonNull Resources resources2) {
        this.resources = (Resources) Preconditions.checkNotNull(resources2);
    }

    @Nullable
    public Resource<BitmapDrawable> transcode(@NonNull Resource<Bitmap> resource, @NonNull Options options) {
        return LazyBitmapDrawableResource.obtain(this.resources, resource);
    }
}
