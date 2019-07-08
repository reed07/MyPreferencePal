package com.bumptech.glide.load.resource.bitmap;

import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RequiresApi
public final class ExifInterfaceImageHeaderParser implements ImageHeaderParser {
    @NonNull
    public ImageType getType(@NonNull InputStream inputStream) throws IOException {
        return ImageType.UNKNOWN;
    }

    @NonNull
    public ImageType getType(@NonNull ByteBuffer byteBuffer) throws IOException {
        return ImageType.UNKNOWN;
    }

    public int getOrientation(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        int attributeInt = new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
        if (attributeInt == 0) {
            return -1;
        }
        return attributeInt;
    }
}
