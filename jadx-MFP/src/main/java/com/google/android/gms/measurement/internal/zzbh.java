package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzbh {
    private final long zzabv;
    private final /* synthetic */ zzbd zzanw;
    @VisibleForTesting
    private final String zzany;
    private final String zzanz;
    private final String zzaoa;

    private zzbh(zzbd zzbd, String str, long j) {
        this.zzanw = zzbd;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.zzany = String.valueOf(str).concat(":start");
        this.zzanz = String.valueOf(str).concat(":count");
        this.zzaoa = String.valueOf(str).concat(":value");
        this.zzabv = j;
    }

    @WorkerThread
    private final void zzfl() {
        this.zzanw.zzaf();
        long currentTimeMillis = this.zzanw.zzbx().currentTimeMillis();
        Editor edit = this.zzanw.zzju().edit();
        edit.remove(this.zzanz);
        edit.remove(this.zzaoa);
        edit.putLong(this.zzany, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    public final void zzc(String str, long j) {
        this.zzanw.zzaf();
        if (zzfn() == 0) {
            zzfl();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zzanw.zzju().getLong(this.zzanz, 0);
        if (j2 <= 0) {
            Editor edit = this.zzanw.zzju().edit();
            edit.putString(this.zzaoa, str);
            edit.putLong(this.zzanz, 1);
            edit.apply();
            return;
        }
        long j3 = j2 + 1;
        boolean z = (this.zzanw.zzgr().zzmk().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j3;
        Editor edit2 = this.zzanw.zzju().edit();
        if (z) {
            edit2.putString(this.zzaoa, str);
        }
        edit2.putLong(this.zzanz, j3);
        edit2.apply();
    }

    @WorkerThread
    public final Pair<String, Long> zzfm() {
        long j;
        this.zzanw.zzaf();
        this.zzanw.zzaf();
        long zzfn = zzfn();
        if (zzfn == 0) {
            zzfl();
            j = 0;
        } else {
            j = Math.abs(zzfn - this.zzanw.zzbx().currentTimeMillis());
        }
        long j2 = this.zzabv;
        if (j < j2) {
            return null;
        }
        if (j > (j2 << 1)) {
            zzfl();
            return null;
        }
        String string = this.zzanw.zzju().getString(this.zzaoa, null);
        long j3 = this.zzanw.zzju().getLong(this.zzanz, 0);
        zzfl();
        if (string == null || j3 <= 0) {
            return zzbd.zzana;
        }
        return new Pair<>(string, Long.valueOf(j3));
    }

    @WorkerThread
    private final long zzfn() {
        return this.zzanw.zzju().getLong(this.zzany, 0);
    }
}
