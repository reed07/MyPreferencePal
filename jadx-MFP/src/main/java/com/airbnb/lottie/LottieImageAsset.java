package com.airbnb.lottie;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

public class LottieImageAsset {
    @Nullable
    private Bitmap bitmap;
    private final String dirName;
    private final String fileName;
    private final int height;
    private final String id;
    private final int width;

    @RestrictTo
    public LottieImageAsset(int i, int i2, String str, String str2, String str3) {
        this.width = i;
        this.height = i2;
        this.id = str;
        this.fileName = str2;
        this.dirName = str3;
    }

    public String getId() {
        return this.id;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(@Nullable Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }
}
