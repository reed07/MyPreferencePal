package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class DataSpec {
    public static final int FLAG_ALLOW_CACHING_UNKNOWN_LENGTH = 2;
    public static final int FLAG_ALLOW_GZIP = 1;
    public static final int HTTP_METHOD_GET = 1;
    public static final int HTTP_METHOD_HEAD = 3;
    public static final int HTTP_METHOD_POST = 2;
    public final long absoluteStreamPosition;
    public final int flags;
    @Nullable
    public final byte[] httpBody;
    public final int httpMethod;
    @Nullable
    public final String key;
    public final long length;
    public final long position;
    @Nullable
    @Deprecated
    public final byte[] postBody;
    public final Uri uri;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface HttpMethod {
    }

    public DataSpec(Uri uri2) {
        this(uri2, 0);
    }

    public DataSpec(Uri uri2, int i) {
        this(uri2, 0, -1, null, i);
    }

    public DataSpec(Uri uri2, long j, long j2, @Nullable String str) {
        this(uri2, j, j, j2, str, 0);
    }

    public DataSpec(Uri uri2, long j, long j2, @Nullable String str, int i) {
        this(uri2, j, j, j2, str, i);
    }

    public DataSpec(Uri uri2, long j, long j2, long j3, @Nullable String str, int i) {
        this(uri2, null, j, j2, j3, str, i);
    }

    public DataSpec(Uri uri2, @Nullable byte[] bArr, long j, long j2, long j3, @Nullable String str, int i) {
        this(uri2, bArr != null ? 2 : 1, bArr, j, j2, j3, str, i);
    }

    public DataSpec(Uri uri2, int i, @Nullable byte[] bArr, long j, long j2, long j3, @Nullable String str, int i2) {
        byte[] bArr2 = bArr;
        long j4 = j;
        long j5 = j2;
        long j6 = j3;
        boolean z = true;
        Assertions.checkArgument(j4 >= 0);
        Assertions.checkArgument(j5 >= 0);
        if (j6 <= 0 && j6 != -1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.uri = uri2;
        this.httpMethod = i;
        if (bArr2 == null || bArr2.length == 0) {
            bArr2 = null;
        }
        this.httpBody = bArr2;
        this.postBody = this.httpBody;
        this.absoluteStreamPosition = j4;
        this.position = j5;
        this.length = j6;
        this.key = str;
        this.flags = i2;
    }

    public boolean isFlagSet(int i) {
        return (this.flags & i) == i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataSpec[");
        sb.append(getHttpMethodString());
        sb.append(" ");
        sb.append(this.uri);
        sb.append(", ");
        sb.append(Arrays.toString(this.httpBody));
        sb.append(", ");
        sb.append(this.absoluteStreamPosition);
        sb.append(", ");
        sb.append(this.position);
        sb.append(", ");
        sb.append(this.length);
        sb.append(", ");
        sb.append(this.key);
        sb.append(", ");
        sb.append(this.flags);
        sb.append("]");
        return sb.toString();
    }

    public final String getHttpMethodString() {
        return getStringForHttpMethod(this.httpMethod);
    }

    public static String getStringForHttpMethod(int i) {
        switch (i) {
            case 1:
                return HttpConstants.METHOD_GET;
            case 2:
                return HttpConstants.METHOD_POST;
            case 3:
                return "HEAD";
            default:
                throw new AssertionError(i);
        }
    }

    public DataSpec subrange(long j) {
        long j2 = this.length;
        long j3 = -1;
        if (j2 != -1) {
            j3 = j2 - j;
        }
        return subrange(j, j3);
    }

    public DataSpec subrange(long j, long j2) {
        if (j == 0 && this.length == j2) {
            return this;
        }
        DataSpec dataSpec = new DataSpec(this.uri, this.httpMethod, this.httpBody, this.absoluteStreamPosition + j, this.position + j, j2, this.key, this.flags);
        return dataSpec;
    }

    public DataSpec withUri(Uri uri2) {
        DataSpec dataSpec = new DataSpec(uri2, this.httpMethod, this.httpBody, this.absoluteStreamPosition, this.position, this.length, this.key, this.flags);
        return dataSpec;
    }
}
