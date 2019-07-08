package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzcu;
import com.google.android.gms.internal.ads.zzwu;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbt extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzbp zzbra;

    private zzbt(zzbp zzbp) {
        this.zzbra = zzbp;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final String doInBackground(Void... voidArr) {
        try {
            this.zzbra.zzbqy = (zzcu) this.zzbra.zzbqv.get(((Long) zzwu.zzpz().zzd(zzaan.zzcvi)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzbbd.zzc("", e);
        }
        return this.zzbra.zzkw();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.zzbra.zzbqx != null && str != null) {
            this.zzbra.zzbqx.loadUrl(str);
        }
    }

    /* synthetic */ zzbt(zzbp zzbp, zzbq zzbq) {
        this(zzbp);
    }
}
