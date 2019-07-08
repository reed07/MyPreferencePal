package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public final class RoundedCorners extends BitmapTransformation {
    private static final byte[] ID_BYTES = "com.bumptech.glide.load.resource.bitmap.RoundedCorners".getBytes(CHARSET);
    private final int roundingRadius;

    /* access modifiers changed from: protected */
    public Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return TransformationUtils.roundedCorners(bitmapPool, bitmap, this.roundingRadius);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof RoundedCorners)) {
            return false;
        }
        if (this.roundingRadius == ((RoundedCorners) obj).roundingRadius) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Util.hashCode("com.bumptech.glide.load.resource.bitmap.RoundedCorners".hashCode(), Util.hashCode(this.roundingRadius));
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.roundingRadius).array());
    }
}
