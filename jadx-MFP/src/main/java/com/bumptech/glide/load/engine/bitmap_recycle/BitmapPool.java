package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.NonNull;

public interface BitmapPool {
    void clearMemory();

    @NonNull
    Bitmap get(int i, int i2, Config config);

    @NonNull
    Bitmap getDirty(int i, int i2, Config config);

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f);

    void trimMemory(int i);
}
