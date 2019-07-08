package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Map;

public final class zzpd extends zzpc {
    public final int responseCode;
    private final Map<String, List<String>> zzbgs;

    public zzpd(int i, Map<String, List<String>> map, zzoz zzoz) {
        StringBuilder sb = new StringBuilder(26);
        sb.append("Response code: ");
        sb.append(i);
        super(sb.toString(), zzoz, 1);
        this.responseCode = i;
        this.zzbgs = map;
    }
}
