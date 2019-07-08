package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class zzp {
    public final List<zzl> allHeaders;
    public final byte[] data;
    public final int statusCode;
    public final Map<String, String> zzab;
    public final boolean zzac;
    private final long zzad;

    @Deprecated
    public zzp(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        List list;
        if (map == null) {
            list = null;
        } else if (map.isEmpty()) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                arrayList.add(new zzl((String) entry.getKey(), (String) entry.getValue()));
            }
            list = arrayList;
        }
        this(i, bArr, map, list, z, j);
    }

    public zzp(int i, byte[] bArr, boolean z, long j, List<zzl> list) {
        Map map;
        if (list == null) {
            map = null;
        } else if (list.isEmpty()) {
            map = Collections.emptyMap();
        } else {
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (zzl zzl : list) {
                treeMap.put(zzl.getName(), zzl.getValue());
            }
            map = treeMap;
        }
        this(i, bArr, map, list, z, j);
    }

    @Deprecated
    public zzp(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0);
    }

    private zzp(int i, byte[] bArr, Map<String, String> map, List<zzl> list, boolean z, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.zzab = map;
        if (list == null) {
            this.allHeaders = null;
        } else {
            this.allHeaders = Collections.unmodifiableList(list);
        }
        this.zzac = z;
        this.zzad = j;
    }
}
