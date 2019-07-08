package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import java.util.concurrent.ThreadFactory;

final class zzqf implements ThreadFactory {
    private final /* synthetic */ String zzbih;

    zzqf(String str) {
        this.zzbih = str;
    }

    public final Thread newThread(@NonNull Runnable runnable) {
        return new Thread(runnable, this.zzbih);
    }
}
