package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzbv;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@zzark
public final class zzaft implements zzm {
    private final Context mContext;
    /* access modifiers changed from: private */
    public volatile zzafk zzdgm;

    public zzaft(Context context) {
        this.mContext = context;
    }

    public final zzp zzc(zzr<?> zzr) throws zzae {
        zzafl zzh = zzafl.zzh(zzr);
        long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
        try {
            zzbcl zzbcl = new zzbcl();
            this.zzdgm = new zzafk(this.mContext, zzbv.zzlv().zzaak(), new zzafx(this, zzbcl), new zzafy(this, zzbcl));
            this.zzdgm.checkAvailabilityAndConnect();
            zzbcb zza = zzbbq.zza(zzbbq.zza((zzbcb<A>) zzbcl, (zzbbl<? super A, ? extends B>) new zzafu<Object,Object>(this, zzh), (Executor) zzayf.zzeky), (long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcwa)).intValue(), TimeUnit.MILLISECONDS, zzayf.zzela);
            zza.zza(new zzafw(this), zzayf.zzeky);
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) zza.get();
            long elapsedRealtime2 = zzbv.zzlm().elapsedRealtime() - elapsedRealtime;
            StringBuilder sb = new StringBuilder(52);
            sb.append("Http assets remote cache took ");
            sb.append(elapsedRealtime2);
            sb.append("ms");
            zzaxz.v(sb.toString());
            zzafn zzafn = (zzafn) new zzasy(parcelFileDescriptor).zza(zzafn.CREATOR);
            if (zzafn == null) {
                return null;
            }
            if (zzafn.zzdgk) {
                throw new zzae(zzafn.zzdgl);
            } else if (zzafn.zzdgi.length != zzafn.zzdgj.length) {
                return null;
            } else {
                HashMap hashMap = new HashMap();
                for (int i = 0; i < zzafn.zzdgi.length; i++) {
                    hashMap.put(zzafn.zzdgi[i], zzafn.zzdgj[i]);
                }
                zzp zzp = new zzp(zzafn.statusCode, zzafn.data, (Map<String, String>) hashMap, zzafn.zzac, zzafn.zzad);
                return zzp;
            }
        } catch (InterruptedException | ExecutionException unused) {
            long elapsedRealtime3 = zzbv.zzlm().elapsedRealtime() - elapsedRealtime;
            StringBuilder sb2 = new StringBuilder(52);
            sb2.append("Http assets remote cache took ");
            sb2.append(elapsedRealtime3);
            sb2.append("ms");
            zzaxz.v(sb2.toString());
            return null;
        } catch (Throwable th) {
            long elapsedRealtime4 = zzbv.zzlm().elapsedRealtime() - elapsedRealtime;
            StringBuilder sb3 = new StringBuilder(52);
            sb3.append("Http assets remote cache took ");
            sb3.append(elapsedRealtime4);
            sb3.append("ms");
            zzaxz.v(sb3.toString());
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        if (this.zzdgm != null) {
            this.zzdgm.disconnect();
            Binder.flushPendingCommands();
        }
    }
}
