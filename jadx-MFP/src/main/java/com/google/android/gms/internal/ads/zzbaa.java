package com.google.android.gms.internal.ads;

import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.Map;

public final class zzbaa extends zzr<zzp> {
    private final zzbcl<zzp> zzene;
    private final Map<String, String> zzenf;
    private final zzbax zzeng;

    public zzbaa(String str, zzbcl<zzp> zzbcl) {
        this(str, null, zzbcl);
    }

    private zzbaa(String str, Map<String, String> map, zzbcl<zzp> zzbcl) {
        super(0, str, new zzbab(zzbcl));
        this.zzenf = null;
        this.zzene = zzbcl;
        this.zzeng = new zzbax();
        this.zzeng.zza(str, HttpConstants.METHOD_GET, null, null);
    }

    /* access modifiers changed from: protected */
    public final zzx<zzp> zza(zzp zzp) {
        return zzx.zza(zzp, zzap.zzb(zzp));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        zzp zzp = (zzp) obj;
        this.zzeng.zza(zzp.zzab, zzp.statusCode);
        zzbax zzbax = this.zzeng;
        byte[] bArr = zzp.data;
        if (zzbax.isEnabled() && bArr != null) {
            zzbax.zzi(bArr);
        }
        this.zzene.set(zzp);
    }
}
