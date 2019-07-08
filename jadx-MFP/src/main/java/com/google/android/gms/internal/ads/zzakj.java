package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzaf;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

final class zzakj implements zzaf {
    private final /* synthetic */ zzakg zzdkn;
    private final zzaji zzdko;
    private final zzbcl zzdkp;

    public zzakj(zzakg zzakg, zzaji zzaji, zzbcl zzbcl) {
        this.zzdkn = zzakg;
        this.zzdko = zzaji;
        this.zzdkp = zzbcl;
    }

    public final void zzd(JSONObject jSONObject) {
        try {
            this.zzdkp.set(this.zzdkn.zzdkh.zze(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzdkp.set(e);
        } finally {
            this.zzdko.release();
        }
    }

    public final void zzbw(@Nullable String str) {
        if (str == null) {
            try {
                this.zzdkp.setException(new zzaju());
            } catch (IllegalStateException unused) {
                this.zzdko.release();
                return;
            } catch (Throwable th) {
                this.zzdko.release();
                throw th;
            }
        } else {
            this.zzdkp.setException(new zzaju(str));
        }
        this.zzdko.release();
    }
}
