package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

final class zzay extends ThreadLocal<ByteBuffer> {
    zzay(zzax zzax) {
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        return ByteBuffer.allocate(32);
    }
}
