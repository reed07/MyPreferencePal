package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;

/* compiled from: IMASDK */
public final class afm {
    private String a = "ad.doubleclick.net";
    private String[] b = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private afi c;

    public afm(afi afi) {
        this.c = afi;
    }

    private final boolean b(Uri uri) {
        if (uri != null) {
            try {
                return uri.getHost().equals(this.a);
            } catch (NullPointerException unused) {
                return false;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final boolean a(Uri uri) {
        if (uri != null) {
            try {
                String host = uri.getHost();
                for (String endsWith : this.b) {
                    if (host.endsWith(endsWith)) {
                        return true;
                    }
                }
                return false;
            } catch (NullPointerException unused) {
                return false;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final afi a() {
        return this.c;
    }

    public final Uri a(Uri uri, Context context) throws afn {
        return a(uri, context, null, false);
    }

    private final Uri a(Uri uri, Context context, String str, boolean z) throws afn {
        try {
            boolean b2 = b(uri);
            if (b2) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new afn("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new afn("Query parameter already exists: ms");
            }
            String a2 = this.c.a(context);
            if (b2) {
                String str2 = "dc_ms";
                String uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    int i = indexOf + 1;
                    StringBuilder sb = new StringBuilder(uri2.substring(0, i));
                    sb.append(str2);
                    sb.append("=");
                    sb.append(a2);
                    sb.append(";");
                    sb.append(uri2.substring(i));
                    return Uri.parse(sb.toString());
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                StringBuilder sb2 = new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2));
                sb2.append(";");
                sb2.append(str2);
                sb2.append("=");
                sb2.append(a2);
                sb2.append(";");
                sb2.append(uri2.substring(indexOf2 + encodedPath.length()));
                return Uri.parse(sb2.toString());
            }
            String str3 = "ms";
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter(str3, a2).build();
            }
            int i2 = indexOf3 + 1;
            StringBuilder sb3 = new StringBuilder(uri3.substring(0, i2));
            sb3.append(str3);
            sb3.append("=");
            sb3.append(a2);
            sb3.append("&");
            sb3.append(uri3.substring(i2));
            return Uri.parse(sb3.toString());
        } catch (UnsupportedOperationException unused) {
            throw new afn("Provided Uri is not in a valid state");
        }
    }

    static {
        new String[]{"/aclk", "/pcs/click"};
    }
}
