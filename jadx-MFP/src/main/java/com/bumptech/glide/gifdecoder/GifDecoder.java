package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public interface GifDecoder {

    public interface BitmapProvider {
        @NonNull
        Bitmap obtain(int i, int i2, @NonNull Config config);

        @NonNull
        byte[] obtainByteArray(int i);

        @NonNull
        int[] obtainIntArray(int i);

        void release(@NonNull Bitmap bitmap);

        void release(@NonNull byte[] bArr);

        void release(@NonNull int[] iArr);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GifDecodeStatus {
    }

    void advance();

    void clear();

    int getByteSize();

    int getCurrentFrameIndex();

    @NonNull
    ByteBuffer getData();

    int getFrameCount();

    int getNextDelay();

    @Nullable
    Bitmap getNextFrame();

    void resetFrameIndex();

    void setDefaultBitmapConfig(@NonNull Config config);
}
