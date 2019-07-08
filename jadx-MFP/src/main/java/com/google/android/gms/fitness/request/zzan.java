package com.google.android.gms.fitness.request;

import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolders;
import java.util.HashMap;
import java.util.Map;

public final class zzan {
    private static final zzan zzhp = new zzan();
    private final Map<ListenerKey<OnDataPointListener>, zzal> zzhq = new HashMap();

    private zzan() {
    }

    public static zzan zzw() {
        return zzhp;
    }

    public final zzal zzc(ListenerHolder<OnDataPointListener> listenerHolder) {
        zzal zzal;
        synchronized (this.zzhq) {
            zzal = (zzal) this.zzhq.get(listenerHolder.getListenerKey());
            if (zzal == null) {
                zzal = new zzal(listenerHolder, null);
                this.zzhq.put(listenerHolder.getListenerKey(), zzal);
            }
        }
        return zzal;
    }

    public final zzal zza(OnDataPointListener onDataPointListener, Looper looper) {
        return zzc(zzc(onDataPointListener, looper));
    }

    public final zzal zzd(ListenerHolder<OnDataPointListener> listenerHolder) {
        zzal zzal;
        synchronized (this.zzhq) {
            zzal = (zzal) this.zzhq.remove(listenerHolder.getListenerKey());
            if (zzal != null) {
                zzal.release();
            }
        }
        return zzal;
    }

    @Nullable
    public final zzal zzb(OnDataPointListener onDataPointListener, Looper looper) {
        return zzd(zzc(onDataPointListener, looper));
    }

    private static ListenerHolder<OnDataPointListener> zzc(OnDataPointListener onDataPointListener, Looper looper) {
        return ListenerHolders.createListenerHolder(onDataPointListener, looper, OnDataPointListener.class.getSimpleName());
    }
}
