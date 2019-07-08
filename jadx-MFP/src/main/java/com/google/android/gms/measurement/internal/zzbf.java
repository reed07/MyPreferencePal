package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbf {
    private boolean value;
    private final boolean zzanu;
    private boolean zzanv;
    private final /* synthetic */ zzbd zzanw;
    private final String zzoj;

    public zzbf(zzbd zzbd, String str, boolean z) {
        this.zzanw = zzbd;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
        this.zzanu = z;
    }

    @WorkerThread
    public final boolean get() {
        if (!this.zzanv) {
            this.zzanv = true;
            this.value = this.zzanw.zzju().getBoolean(this.zzoj, this.zzanu);
        }
        return this.value;
    }

    @WorkerThread
    public final void set(boolean z) {
        Editor edit = this.zzanw.zzju().edit();
        edit.putBoolean(this.zzoj, z);
        edit.apply();
        this.value = z;
    }
}
