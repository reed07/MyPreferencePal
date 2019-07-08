package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* compiled from: IMASDK */
final class po extends LinkedHashMap<Uri, byte[]> {
    public po() {
        super(8, 1.0f, false);
    }

    /* renamed from: a */
    public final byte[] get(Object obj) {
        if (obj == null) {
            return null;
        }
        return (byte[]) super.get(obj);
    }

    /* renamed from: a */
    public final byte[] put(Uri uri, byte[] bArr) {
        return (byte[]) super.put(uri, (byte[]) qi.a(bArr));
    }

    /* access modifiers changed from: protected */
    public final boolean removeEldestEntry(Entry<Uri, byte[]> entry) {
        return size() > 4;
    }
}
