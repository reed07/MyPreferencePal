package com.google.android.gms.internal.icing;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

final /* synthetic */ class zzbq implements OnSharedPreferenceChangeListener {
    private final zzbp zzdw;

    zzbq(zzbp zzbp) {
        this.zzdw = zzbp;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zzdw.zza(sharedPreferences, str);
    }
}
