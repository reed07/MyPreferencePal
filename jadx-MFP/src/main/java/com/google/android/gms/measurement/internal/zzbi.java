package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbi {
    private String value;
    private boolean zzanv;
    private final /* synthetic */ zzbd zzanw;
    private final String zzaob = null;
    private final String zzoj;

    public zzbi(zzbd zzbd, String str, String str2) {
        this.zzanw = zzbd;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
    }

    @WorkerThread
    public final String zzkd() {
        if (!this.zzanv) {
            this.zzanv = true;
            this.value = this.zzanw.zzju().getString(this.zzoj, null);
        }
        return this.value;
    }

    @WorkerThread
    public final void zzcd(String str) {
        if (!zzfx.zzv(str, this.value)) {
            Editor edit = this.zzanw.zzju().edit();
            edit.putString(this.zzoj, str);
            edit.apply();
            this.value = str;
        }
    }
}
