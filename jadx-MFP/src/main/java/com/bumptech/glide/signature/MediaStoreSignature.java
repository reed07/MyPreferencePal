package com.bumptech.glide.signature;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MediaStoreSignature implements Key {
    private final long dateModified;
    @NonNull
    private final String mimeType;
    private final int orientation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaStoreSignature mediaStoreSignature = (MediaStoreSignature) obj;
        return this.dateModified == mediaStoreSignature.dateModified && this.orientation == mediaStoreSignature.orientation && this.mimeType.equals(mediaStoreSignature.mimeType);
    }

    public int hashCode() {
        int hashCode = this.mimeType.hashCode() * 31;
        long j = this.dateModified;
        return ((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.orientation;
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(12).putLong(this.dateModified).putInt(this.orientation).array());
        messageDigest.update(this.mimeType.getBytes(CHARSET));
    }
}
