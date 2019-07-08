package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public final class LazyBitmapDrawableResource implements Initializable, Resource<BitmapDrawable> {
    private final Resource<Bitmap> bitmapResource;
    private final Resources resources;

    @Nullable
    public static Resource<BitmapDrawable> obtain(@NonNull Resources resources2, @Nullable Resource<Bitmap> resource) {
        if (resource == null) {
            return null;
        }
        return new LazyBitmapDrawableResource(resources2, resource);
    }

    private LazyBitmapDrawableResource(@NonNull Resources resources2, @NonNull Resource<Bitmap> resource) {
        this.resources = (Resources) Preconditions.checkNotNull(resources2);
        this.bitmapResource = (Resource) Preconditions.checkNotNull(resource);
    }

    @NonNull
    public Class<BitmapDrawable> getResourceClass() {
        return BitmapDrawable.class;
    }

    @NonNull
    public BitmapDrawable get() {
        return new BitmapDrawable(this.resources, (Bitmap) this.bitmapResource.get());
    }

    public int getSize() {
        return this.bitmapResource.getSize();
    }

    public void recycle() {
        this.bitmapResource.recycle();
    }

    public void initialize() {
        Resource<Bitmap> resource = this.bitmapResource;
        if (resource instanceof Initializable) {
            ((Initializable) resource).initialize();
        }
    }
}
