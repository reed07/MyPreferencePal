package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

public final class zzay {
    private final Context zzwq;
    private final Context zzwr;

    public zzay(Context context) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext, "Application context can't be null");
        this.zzwq = applicationContext;
        this.zzwr = applicationContext;
    }

    public final Context getApplicationContext() {
        return this.zzwq;
    }

    public final Context zzcm() {
        return this.zzwr;
    }
}
