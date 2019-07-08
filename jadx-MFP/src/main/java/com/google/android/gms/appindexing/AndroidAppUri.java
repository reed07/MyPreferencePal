package com.google.android.gms.appindexing;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import javax.annotation.Nullable;

@Deprecated
@VisibleForTesting
public final class AndroidAppUri {
    private final Uri uri;

    private AndroidAppUri(Uri uri2) {
        this.uri = uri2;
    }

    @VisibleForTesting
    public static AndroidAppUri newAndroidAppUri(Uri uri2) {
        AndroidAppUri androidAppUri = new AndroidAppUri(uri2);
        if (!"android-app".equals(androidAppUri.uri.getScheme())) {
            throw new IllegalArgumentException("android-app scheme is required.");
        } else if (!TextUtils.isEmpty(androidAppUri.getPackageName())) {
            if (androidAppUri.uri.equals(newAndroidAppUri(androidAppUri.getPackageName(), androidAppUri.getDeepLinkUri()).toUri())) {
                return androidAppUri;
            }
            throw new IllegalArgumentException("URI is not canonical.");
        } else {
            throw new IllegalArgumentException("Package name is empty.");
        }
    }

    @VisibleForTesting
    public static AndroidAppUri newAndroidAppUri(String str, @Nullable Uri uri2) {
        Builder authority = new Builder().scheme("android-app").authority(str);
        if (uri2 != null) {
            authority.appendPath(uri2.getScheme());
            if (uri2.getAuthority() != null) {
                authority.appendPath(uri2.getAuthority());
            }
            for (String appendPath : uri2.getPathSegments()) {
                authority.appendPath(appendPath);
            }
            authority.encodedQuery(uri2.getEncodedQuery()).encodedFragment(uri2.getEncodedFragment());
        }
        return new AndroidAppUri(authority.build());
    }

    @VisibleForTesting
    public final Uri toUri() {
        return this.uri;
    }

    @VisibleForTesting
    public final String getPackageName() {
        return this.uri.getAuthority();
    }

    @VisibleForTesting
    public final Uri getDeepLinkUri() {
        List pathSegments = this.uri.getPathSegments();
        if (pathSegments.size() <= 0) {
            return null;
        }
        String str = (String) pathSegments.get(0);
        Builder builder = new Builder();
        builder.scheme(str);
        if (pathSegments.size() > 1) {
            builder.authority((String) pathSegments.get(1));
            for (int i = 2; i < pathSegments.size(); i++) {
                builder.appendPath((String) pathSegments.get(i));
            }
        }
        builder.encodedQuery(this.uri.getEncodedQuery());
        builder.encodedFragment(this.uri.getEncodedFragment());
        return builder.build();
    }

    public final String toString() {
        return this.uri.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AndroidAppUri) {
            return this.uri.equals(((AndroidAppUri) obj).uri);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.uri);
    }
}
