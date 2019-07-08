package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;

/* compiled from: IMASDK */
final class jr implements Comparator<jf> {
    private jr() {
    }

    private static int a(jf jfVar) {
        String str = jfVar.a;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return -1;
        }
        return (vf.a >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : 1;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return a((jf) obj) - a((jf) obj2);
    }

    /* synthetic */ jr(byte b) {
        this();
    }
}
