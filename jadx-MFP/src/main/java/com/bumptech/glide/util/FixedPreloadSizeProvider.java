package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.ListPreloader.PreloadSizeProvider;

public class FixedPreloadSizeProvider<T> implements PreloadSizeProvider<T> {
    private final int[] size;

    @Nullable
    public int[] getPreloadSize(@NonNull T t, int i, int i2) {
        return this.size;
    }
}
