package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbg {
    private long value;
    private boolean zzanv;
    private final /* synthetic */ zzbd zzanw;
    private final long zzanx;
    private final String zzoj;

    public zzbg(zzbd zzbd, String str, long j) {
        this.zzanw = zzbd;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
        this.zzanx = j;
    }

    @WorkerThread
    public final long get() {
        if (!this.zzanv) {
            this.zzanv = true;
            this.value = this.zzanw.zzju().getLong(this.zzoj, this.zzanx);
        }
        return this.value;
    }

    @WorkerThread
    public final void set(long j) {
        Editor edit = this.zzanw.zzju().edit();
        edit.putLong(this.zzoj, j);
        edit.apply();
        this.value = j;
    }
}
