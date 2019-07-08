package com.inmobi.commons.core.utilities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.mopub.common.Constants;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/* compiled from: ClickUrlHandler */
public class b {
    private static final String a = "b";

    public static boolean a(Context context, String str) {
        if (str == null) {
            return false;
        }
        if (context == null) {
            return a(Uri.parse(str));
        }
        try {
            if (new Intent("android.intent.action.VIEW", Uri.parse(str)).resolveActivity(context.getPackageManager()) != null) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void b(Context context, @NonNull String str) throws URISyntaxException, ActivityNotFoundException {
        while (context != null) {
            try {
                Intent parseUri = Intent.parseUri(str, 0);
                parseUri.setFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(parseUri);
                return;
            } catch (ActivityNotFoundException e) {
                if (Constants.INTENT_SCHEME.equals(Uri.parse(str).getScheme())) {
                    str = b(str);
                    if (TextUtils.isEmpty(str)) {
                    }
                }
                throw e;
            }
        }
    }

    @Nullable
    public static String a(Context context, @NonNull String str, @Nullable String str2) {
        if (context == null) {
            return null;
        }
        try {
            Intent parseUri = Intent.parseUri(str, 0);
            if (parseUri.resolveActivity(context.getPackageManager()) != null) {
                parseUri.setFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(parseUri);
                return str;
            }
            StringBuilder sb = new StringBuilder("No app can handle the:");
            sb.append(str);
            sb.append(", trying with fallback URL if any");
            if (!TextUtils.isEmpty(str2)) {
                return a(context, str2, null);
            }
            if (Constants.INTENT_SCHEME.equals(Uri.parse(str).getScheme())) {
                String b = b(str);
                if (!TextUtils.isEmpty(b)) {
                    return a(context, URLDecoder.decode(b, "UTF-8"), null);
                }
            }
            return null;
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("No app can handle the url:");
            sb2.append(str);
            sb2.append(", Log : ");
            sb2.append(e.getMessage());
        }
    }

    @Nullable
    private static String b(String str) {
        try {
            return Intent.parseUri(str, 1).getStringExtra("browser_fallback_url");
        } catch (URISyntaxException e) {
            new StringBuilder("Exception while getting Fallback Url :").append(e.getMessage());
            return null;
        }
    }

    private static boolean a(Uri uri) {
        return Constants.HTTP.equals(uri.getScheme()) || Constants.HTTPS.equals(uri.getScheme());
    }

    public static boolean a(@NonNull String str) {
        Uri parse = Uri.parse(str);
        return a(parse) && !com.myfitnesspal.shared.constants.Constants.Uri.GOOGLE_PLAY.equals(parse.getHost()) && !"market.android.com".equals(parse.getHost()) && !"market".equals(parse.getScheme());
    }
}
