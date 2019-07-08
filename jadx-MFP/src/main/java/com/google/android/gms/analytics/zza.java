package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.internal.measurement.zzak;
import com.google.android.gms.internal.measurement.zzaw;
import java.util.ListIterator;

@VisibleForTesting
public class zza extends zzj<zza> {
    private final zzaw zzqx;
    private boolean zzqy;

    @VisibleForTesting
    public zza(zzaw zzaw) {
        super(zzaw.zzca(), zzaw.zzbx());
        this.zzqx = zzaw;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final zzaw zzl() {
        return this.zzqx;
    }

    public final zzg zzm() {
        zzg zzs = this.zzsk.zzs();
        zzs.zza((zzi) this.zzqx.zzci().zzdf());
        zzs.zza((zzi) this.zzqx.zzcj().zzek());
        zzd(zzs);
        return zzs;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzg zzg) {
        zzag zzag = (zzag) zzg.zzb(zzag.class);
        if (TextUtils.isEmpty(zzag.zzbd())) {
            zzag.setClientId(this.zzqx.zzcr().zzdr());
        }
        if (this.zzqy && TextUtils.isEmpty(zzag.zzbf())) {
            zzak zzcq = this.zzqx.zzcq();
            zzag.zzm(zzcq.zzbn());
            zzag.zza(zzcq.zzbg());
        }
    }

    public final void enableAdvertisingIdCollection(boolean z) {
        this.zzqy = z;
    }

    public final void zza(String str) {
        Preconditions.checkNotEmpty(str);
        Uri zzb = zzb.zzb(str);
        ListIterator listIterator = this.zzsk.zzu().listIterator();
        while (listIterator.hasNext()) {
            if (zzb.equals(((zzo) listIterator.next()).zzo())) {
                listIterator.remove();
            }
        }
        this.zzsk.zzu().add(new zzb(this.zzqx, str));
    }
}
