package com.google.android.gms.internal.ads;

final class zzazw implements zzy {
    private final /* synthetic */ String zzdvo;
    private final /* synthetic */ zzazz zzena;

    zzazw(zzazs zzazs, String str, zzazz zzazz) {
        this.zzdvo = str;
        this.zzena = zzazz;
    }

    public final void zzd(zzae zzae) {
        String str = this.zzdvo;
        String zzae2 = zzae.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(zzae2).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append("\n");
        sb.append(zzae2);
        zzaxz.zzeo(sb.toString());
        this.zzena.zzb(null);
    }
}
