package com.google.ads.interactivemedia.v3.internal;

import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class tg extends te {
    public final int a;

    public tg(int i, String str, Map<String, List<String>> map, sr srVar) {
        StringBuilder sb = new StringBuilder(26);
        sb.append("Response code: ");
        sb.append(i);
        super(sb.toString(), srVar, 1);
        this.a = i;
    }
}
