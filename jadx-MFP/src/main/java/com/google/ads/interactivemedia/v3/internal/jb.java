package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class jb {
    public final int a;
    public final String b;
    public final List<ja> c;
    public final byte[] d;

    public jb(int i, String str, List<ja> list, byte[] bArr) {
        List<ja> list2;
        this.a = i;
        this.b = str;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.c = list2;
        this.d = bArr;
    }
}
