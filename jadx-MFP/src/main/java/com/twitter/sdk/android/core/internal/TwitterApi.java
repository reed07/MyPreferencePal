package com.twitter.sdk.android.core.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class TwitterApi {
    private final String baseHostUrl;

    public TwitterApi() {
        this("https://api.twitter.com");
    }

    public TwitterApi(String str) {
        this.baseHostUrl = str;
    }

    public String getBaseHostUrl() {
        return this.baseHostUrl;
    }

    public Builder buildUponBaseHostUrl(String... strArr) {
        Builder buildUpon = Uri.parse(getBaseHostUrl()).buildUpon();
        if (strArr != null) {
            for (String appendPath : strArr) {
                buildUpon.appendPath(appendPath);
            }
        }
        return buildUpon;
    }

    public static String buildUserAgent(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append('/');
        sb.append(str2);
        sb.append(' ');
        sb.append(Build.MODEL);
        sb.append('/');
        sb.append(VERSION.RELEASE);
        sb.append(" (");
        sb.append(Build.MANUFACTURER);
        sb.append(';');
        sb.append(Build.MODEL);
        sb.append(';');
        sb.append(Build.BRAND);
        sb.append(';');
        sb.append(Build.PRODUCT);
        sb.append(')');
        return normalizeString(sb.toString());
    }

    static String normalizeString(String str) {
        return stripNonAscii(Normalizer.normalize(str, Form.NFD));
    }

    static String stripNonAscii(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt > 31 && charAt < 127) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
