package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzrp {
    private static final Integer zzbqn = Integer.valueOf(0);
    private static final Integer zzbqo = Integer.valueOf(1);
    private final ExecutorService zzadl;
    private final Context zzri;

    public zzrp(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    @VisibleForTesting
    private zzrp(Context context, ExecutorService executorService) {
        this.zzri = context;
        this.zzadl = executorService;
    }
}
