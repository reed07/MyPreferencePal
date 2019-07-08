package com.google.android.gms.internal.ads;

public final class zzob {
    public final String value;
    public final String zzbdi;

    public zzob(String str, String str2) {
        this.zzbdi = str;
        this.value = str2;
    }

    public final String toString() {
        String str = this.zzbdi;
        String str2 = this.value;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(", ");
        sb.append(str2);
        return sb.toString();
    }
}
