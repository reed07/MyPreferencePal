package com.google.firebase.appindexing;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import java.util.List;

public final class AndroidAppUri {
    private final Uri uri;

    private AndroidAppUri(Uri uri2) {
        this.uri = uri2;
    }

    public static AndroidAppUri newAndroidAppUri(Uri uri2) {
        AndroidAppUri androidAppUri = new AndroidAppUri(uri2);
        if (!"android-app".equals(androidAppUri.uri.getScheme())) {
            throw new IllegalArgumentException("android-app scheme is required.");
        } else if (!TextUtils.isEmpty(androidAppUri.getPackageName())) {
            return androidAppUri;
        } else {
            throw new IllegalArgumentException("Package name is empty.");
        }
    }

    public final String getPackageName() {
        return this.uri.getAuthority();
    }

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
