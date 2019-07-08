package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class zzcu {
    private static final String[] zzry = {"/aclk", "/pcs/click"};
    private String zzru = "googleads.g.doubleclick.net";
    private String zzrv = "/pagead/ads";
    private String zzrw = "ad.doubleclick.net";
    private String[] zzrx = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzcq zzrz;

    public zzcu(zzcq zzcq) {
        this.zzrz = zzcq;
    }

    private final boolean zza(Uri uri) {
        if (uri != null) {
            try {
                return uri.getHost().equals(this.zzrw);
            } catch (NullPointerException unused) {
                return false;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final boolean zzb(Uri uri) {
        if (uri != null) {
            try {
                String host = uri.getHost();
                for (String endsWith : this.zzrx) {
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

    public final zzcq zzab() {
        return this.zzrz;
    }

    public final Uri zza(Uri uri, Context context) throws zzcv {
        return zza(uri, context, null, false, null, null);
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzrz.zza(motionEvent);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzcv {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException unused) {
            throw new zzcv("Provided Uri is not in a valid state");
        }
    }

    public final boolean zzc(Uri uri) {
        if (zzb(uri)) {
            for (String endsWith : zzry) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final Uri zza(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcv {
        String str2;
        try {
            boolean zza = zza(uri);
            if (zza) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcv("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcv("Query parameter already exists: ms");
            }
            if (z) {
                str2 = this.zzrz.zza(context, str, view, activity);
            } else {
                str2 = this.zzrz.zza(context);
            }
            if (zza) {
                String str3 = "dc_ms";
                String uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    int i = indexOf + 1;
                    StringBuilder sb = new StringBuilder(uri2.substring(0, i));
                    sb.append(str3);
                    sb.append("=");
                    sb.append(str2);
                    sb.append(";");
                    sb.append(uri2.substring(i));
                    return Uri.parse(sb.toString());
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                StringBuilder sb2 = new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2));
                sb2.append(";");
                sb2.append(str3);
                sb2.append("=");
                sb2.append(str2);
                sb2.append(";");
                sb2.append(uri2.substring(indexOf2 + encodedPath.length()));
                return Uri.parse(sb2.toString());
            }
            String str4 = "ms";
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter(str4, str2).build();
            }
            int i2 = indexOf3 + 1;
            StringBuilder sb3 = new StringBuilder(uri3.substring(0, i2));
            sb3.append(str4);
            sb3.append("=");
            sb3.append(str2);
            sb3.append("&");
            sb3.append(uri3.substring(i2));
            return Uri.parse(sb3.toString());
        } catch (UnsupportedOperationException unused) {
            throw new zzcv("Provided Uri is not in a valid state");
        }
    }
}
