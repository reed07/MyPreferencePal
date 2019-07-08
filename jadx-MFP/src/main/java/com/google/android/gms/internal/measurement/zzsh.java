package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzsh {
    public static Uri zzfq(String str) {
        String str2 = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode(str));
        return Uri.parse(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
