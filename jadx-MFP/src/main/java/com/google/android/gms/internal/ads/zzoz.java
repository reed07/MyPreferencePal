package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.Arrays;

public final class zzoz {
    public final int flags;
    public final Uri uri;
    public final long zzaha;
    public final byte[] zzbft;
    public final long zzbfu;
    public final String zzcb;
    public final long zzcc;

    public zzoz(Uri uri2) {
        this(uri2, 0);
    }

    public zzoz(Uri uri2, int i) {
        this(uri2, 0, -1, null, i);
    }

    public zzoz(Uri uri2, long j, long j2, String str) {
        this(uri2, j, j, j2, str, 0);
    }

    public zzoz(Uri uri2, long j, long j2, String str, int i) {
        this(uri2, j, j, j2, str, i);
    }

    private zzoz(Uri uri2, long j, long j2, long j3, String str, int i) {
        this(uri2, null, j, j2, j3, str, i);
    }

    public zzoz(Uri uri2, byte[] bArr, long j, long j2, long j3, String str, int i) {
        boolean z = true;
        zzpo.checkArgument(j >= 0);
        zzpo.checkArgument(j2 >= 0);
        if (j3 <= 0 && j3 != -1) {
            z = false;
        }
        zzpo.checkArgument(z);
        this.uri = uri2;
        this.zzbft = bArr;
        this.zzbfu = j;
        this.zzaha = j2;
        this.zzcc = j3;
        this.zzcb = str;
        this.flags = i;
    }

    public final boolean zzbg(int i) {
        return (this.flags & 1) == 1;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.uri);
        String arrays = Arrays.toString(this.zzbft);
        long j = this.zzbfu;
        long j2 = this.zzaha;
        long j3 = this.zzcc;
        String str = this.zzcb;
        int i = this.flags;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 93 + String.valueOf(arrays).length() + String.valueOf(str).length());
        sb.append("DataSpec[");
        sb.append(valueOf);
        sb.append(", ");
        sb.append(arrays);
        sb.append(", ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(", ");
        sb.append(j3);
        sb.append(", ");
        sb.append(str);
        sb.append(", ");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }
}
