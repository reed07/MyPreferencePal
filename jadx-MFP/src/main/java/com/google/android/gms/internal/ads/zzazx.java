package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzazx extends zzaw {
    private final /* synthetic */ byte[] zzenb;
    private final /* synthetic */ Map zzenc;
    private final /* synthetic */ zzbax zzend;

    zzazx(zzazs zzazs, int i, String str, zzz zzz, zzy zzy, byte[] bArr, Map map, zzbax zzbax) {
        this.zzenb = bArr;
        this.zzenc = map;
        this.zzend = zzbax;
        super(i, str, zzz, zzy);
    }

    public final byte[] zzh() throws zza {
        byte[] bArr = this.zzenb;
        return bArr == null ? super.zzh() : bArr;
    }

    public final Map<String, String> getHeaders() throws zza {
        Map<String, String> map = this.zzenc;
        return map == null ? super.getHeaders() : map;
    }

    /* access modifiers changed from: protected */
    public final void zzh(String str) {
        this.zzend.zzek(str);
        super.zza(str);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        zza((String) obj);
    }
}
