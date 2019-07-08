package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzaao implements Callable<Void> {
    private final /* synthetic */ Context val$context;

    zzaao(Context context) {
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzwu.zzpz().initialize(this.val$context);
        return null;
    }
}
