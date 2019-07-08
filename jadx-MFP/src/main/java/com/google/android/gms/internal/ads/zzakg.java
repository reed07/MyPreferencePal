package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzaf;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzakg<I, O> implements zzajv<I, O> {
    private final zzait zzdkg;
    /* access modifiers changed from: private */
    public final zzajx<O> zzdkh;
    private final zzajy<I> zzdki;
    private final String zzdkj;

    zzakg(zzait zzait, String str, zzajy<I> zzajy, zzajx<O> zzajx) {
        this.zzdkg = zzait;
        this.zzdkj = str;
        this.zzdki = zzajy;
        this.zzdkh = zzajx;
    }

    public final zzbcb<O> zzj(I i) {
        zzbcl zzbcl = new zzbcl();
        zzaji zzb = this.zzdkg.zzb((zzcu) null);
        zzb.zza(new zzakh(this, zzb, i, zzbcl), new zzaki(this, zzbcl, zzb));
        return zzbcl;
    }

    /* access modifiers changed from: private */
    public final void zza(zzaji zzaji, zzajr zzajr, I i, zzbcl<O> zzbcl) {
        try {
            zzbv.zzlf();
            String zzzs = zzayh.zzzs();
            zzf.zzdfh.zza(zzzs, (zzaf) new zzakj(this, zzaji, zzbcl));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", zzzs);
            jSONObject.put("args", this.zzdki.zzk(i));
            zzajr.zzb(this.zzdkj, jSONObject);
        } catch (Exception e) {
            zzbcl.setException(e);
            zzaxz.zzb("Unable to invokeJavaScript", e);
            zzaji.release();
        } catch (Throwable th) {
            zzaji.release();
            throw th;
        }
    }

    public final zzbcb<O> zzf(@Nullable I i) throws Exception {
        return zzj(i);
    }
}
