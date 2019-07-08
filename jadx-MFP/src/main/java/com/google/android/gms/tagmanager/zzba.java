package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
public final class zzba implements zzdj {
    private int zzyn = 5;

    public final void e(String str) {
        if (this.zzyn <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    public final void zza(String str, Throwable th) {
        if (this.zzyn <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public final void zzab(String str) {
        if (this.zzyn <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    public final void zzb(String str, Throwable th) {
        if (this.zzyn <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }

    public final void zzdm(String str) {
        if (this.zzyn <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    public final void zzdn(String str) {
        if (this.zzyn <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    public final void v(String str) {
        if (this.zzyn <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    public final void setLogLevel(int i) {
        this.zzyn = i;
    }
}
