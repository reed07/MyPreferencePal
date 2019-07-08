package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzsx;
import com.google.android.gms.internal.measurement.zzsy;

public abstract class zzsy<MessageType extends zzsx<MessageType, BuilderType>, BuilderType extends zzsy<MessageType, BuilderType>> implements zzvw {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzty */
    public abstract BuilderType clone();

    public final /* synthetic */ zzvw zza(zzvv zzvv) {
        if (zzwj().getClass().isInstance(zzvv)) {
            return zza((MessageType) (zzsx) zzvv);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
