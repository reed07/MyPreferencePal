package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzbx;
import com.google.android.gms.internal.icing.zzby;

public abstract class zzby<MessageType extends zzbx<MessageType, BuilderType>, BuilderType extends zzby<MessageType, BuilderType>> implements zzer {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzah */
    public abstract BuilderType clone();

    public final /* synthetic */ zzer zza(zzeq zzeq) {
        if (zzbv().getClass().isInstance(zzeq)) {
            return zza((MessageType) (zzbx) zzeq);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
