package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;

/* compiled from: IMASDK */
final class jq implements Comparator<jf> {
    private jq() {
    }

    private static int a(jf jfVar) {
        return jfVar.a.startsWith("OMX.google") ? -1 : 0;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return a((jf) obj) - a((jf) obj2);
    }

    /* synthetic */ jq(byte b) {
        this();
    }
}
