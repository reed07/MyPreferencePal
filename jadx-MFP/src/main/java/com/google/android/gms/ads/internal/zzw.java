package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaso;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzayh;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzw {
    private final Context mContext;
    private boolean zzbmw;
    private zzawr zzbmx;
    private zzaso zzbmy;

    public zzw(Context context, zzawr zzawr, zzaso zzaso) {
        this.mContext = context;
        this.zzbmx = zzawr;
        this.zzbmy = zzaso;
        if (this.zzbmy == null) {
            this.zzbmy = new zzaso();
        }
    }

    private final boolean zzjt() {
        zzawr zzawr = this.zzbmx;
        return (zzawr != null && zzawr.zzxp().zzegm) || this.zzbmy.zzdzg;
    }

    public final void recordClick() {
        this.zzbmw = true;
    }

    public final boolean zzju() {
        return !zzjt() || this.zzbmw;
    }

    public final void zzas(@Nullable String str) {
        if (zzjt()) {
            if (str == null) {
                str = "";
            }
            zzawr zzawr = this.zzbmx;
            if (zzawr != null) {
                zzawr.zza(str, null, 3);
                return;
            }
            if (this.zzbmy.zzdzg && this.zzbmy.zzdzh != null) {
                for (String str2 : this.zzbmy.zzdzh) {
                    if (!TextUtils.isEmpty(str2)) {
                        String replace = str2.replace("{NAVIGATION_URL}", Uri.encode(str));
                        zzbv.zzlf();
                        zzayh.zzc(this.mContext, "", replace);
                    }
                }
            }
        }
    }
}
