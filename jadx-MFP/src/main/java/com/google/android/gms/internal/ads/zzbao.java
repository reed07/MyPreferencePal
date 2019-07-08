package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzbao implements Callable<String> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Context zzens;

    zzbao(zzbam zzbam, Context context, Context context2) {
        this.zzens = context;
        this.val$context = context2;
    }

    public final /* synthetic */ Object call() throws Exception {
        SharedPreferences sharedPreferences;
        boolean z = false;
        if (this.zzens != null) {
            zzaxz.v("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.zzens.getSharedPreferences("admob_user_agent", 0);
        } else {
            zzaxz.v("Attempting to read user agent from local cache.");
            sharedPreferences = this.val$context.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        String string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            zzaxz.v("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.val$context);
            if (z) {
                sharedPreferences.edit().putString("user_agent", string).apply();
                zzaxz.v("Persisting user agent.");
            }
        }
        return string;
    }
}
