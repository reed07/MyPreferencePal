package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class qn extends qr {
    public static final qn a;
    public final List<qo> b;
    public final List<qo> c;
    public final List<qo> d;
    public final bs e;
    public final List<bs> f;
    public final Map<String, String> g;

    public qn(String str, List<String> list, List<qo> list2, List<qo> list3, List<qo> list4, bs bsVar, List<bs> list5, boolean z, Map<String, String> map) {
        super(str, list, z);
        this.b = Collections.unmodifiableList(list2);
        this.c = Collections.unmodifiableList(list3);
        this.d = Collections.unmodifiableList(list4);
        this.e = bsVar;
        this.f = list5 != null ? Collections.unmodifiableList(list5) : null;
        this.g = Collections.unmodifiableMap(map);
    }

    public static qn a(String str) {
        List singletonList = Collections.singletonList(new qo(str, bs.a("0", null, MimeTypes.APPLICATION_M3U8, null, null, -1, 0, 0, null), ""));
        List emptyList = Collections.emptyList();
        qn qnVar = new qn(null, Collections.emptyList(), singletonList, emptyList, emptyList, null, null, false, Collections.emptyMap());
        return qnVar;
    }

    private static List<qo> a(List<qo> list, int i, List<lc> list2) {
        ArrayList arrayList = new ArrayList(list2.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            qo qoVar = (qo) list.get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= list2.size()) {
                    break;
                }
                lc lcVar = (lc) list2.get(i3);
                if (lcVar.b == i && lcVar.c == i2) {
                    arrayList.add(qoVar);
                    break;
                }
                i3++;
            }
        }
        return arrayList;
    }

    public final /* synthetic */ Object a(List list) {
        qn qnVar = new qn(this.n, this.o, a(this.b, 0, list), a(this.c, 1, list), a(this.d, 2, list), this.e, this.f, this.p, this.g);
        return qnVar;
    }

    static {
        qn qnVar = new qn("", Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, Collections.emptyList(), false, Collections.emptyMap());
        a = qnVar;
    }
}
