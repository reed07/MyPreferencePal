package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbpl;
import com.google.android.gms.internal.ads.zzbpm;

public abstract class zzbpm<MessageType extends zzbpl<MessageType, BuilderType>, BuilderType extends zzbpm<MessageType, BuilderType>> implements zzbsm {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzakh */
    public abstract BuilderType clone();

    public final /* synthetic */ zzbsm zzd(zzbsl zzbsl) {
        if (zzamv().getClass().isInstance(zzbsl)) {
            return zza((zzbpl) zzbsl);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
