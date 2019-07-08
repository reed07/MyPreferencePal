package com.google.android.gms.internal.ads;

import android.util.Log;

public final class zzbwb extends zzbwg {
    private String name;

    public zzbwb(String str) {
        this.name = str;
    }

    public final void zzge(String str) {
        String str2 = this.name;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
        sb.append(str2);
        sb.append(":");
        sb.append(str);
        Log.d("isoparser", sb.toString());
    }
}
