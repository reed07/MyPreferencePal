package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

final /* synthetic */ class zzsq implements OnSharedPreferenceChangeListener {
    private final zzsp zzbse;

    zzsq(zzsp zzsp) {
        this.zzbse = zzsp;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zzbse.zza(sharedPreferences, str);
    }
}
