package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IMASDK */
final class zo<K, V> extends xj<Map<K, V>> {
    private final xj<K> a;
    private final xj<V> b;
    private final ys<? extends Map<K, V>> c;
    private final /* synthetic */ zn d;

    public zo(zn znVar, wo woVar, Type type, xj<K> xjVar, Type type2, xj<V> xjVar2, ys<? extends Map<K, V>> ysVar) {
        this.d = znVar;
        this.a = new aab(woVar, xjVar, type);
        this.b = new aab(woVar, xjVar2, type2);
        this.c = ysVar;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        abw f = abu.f();
        if (f == abw.NULL) {
            abu.j();
            return null;
        }
        Map map = (Map) this.c.a();
        if (f == abw.BEGIN_ARRAY) {
            abu.a();
            while (abu.e()) {
                abu.a();
                Object read = this.a.read(abu);
                if (map.put(read, this.b.read(abu)) == null) {
                    abu.b();
                } else {
                    StringBuilder sb = new StringBuilder("duplicate key: ");
                    sb.append(read);
                    throw new xh(sb.toString());
                }
            }
            abu.b();
        } else {
            abu.c();
            while (abu.e()) {
                ym.a.a(abu);
                Object read2 = this.a.read(abu);
                if (map.put(read2, this.b.read(abu)) != null) {
                    StringBuilder sb2 = new StringBuilder("duplicate key: ");
                    sb2.append(read2);
                    throw new xh(sb2.toString());
                }
            }
            abu.d();
        }
        return map;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        Map map = (Map) obj;
        if (map == null) {
            abx.f();
        } else if (!this.d.a) {
            abx.d();
            for (Entry entry : map.entrySet()) {
                abx.a(String.valueOf(entry.getKey()));
                this.b.write(abx, entry.getValue());
            }
            abx.e();
        } else {
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Entry entry2 : map.entrySet()) {
                wz jsonTree = this.a.toJsonTree(entry2.getKey());
                arrayList.add(jsonTree);
                arrayList2.add(entry2.getValue());
                z |= (jsonTree instanceof wx) || (jsonTree instanceof xc);
            }
            if (z) {
                abx.b();
                int size = arrayList.size();
                while (i < size) {
                    abx.b();
                    yu.a((wz) arrayList.get(i), abx);
                    this.b.write(abx, arrayList2.get(i));
                    abx.c();
                    i++;
                }
                abx.c();
                return;
            }
            abx.d();
            int size2 = arrayList.size();
            while (i < size2) {
                wz wzVar = (wz) arrayList.get(i);
                if (wzVar instanceof xe) {
                    xe g = wzVar.g();
                    if (g.i()) {
                        str = String.valueOf(g.a());
                    } else if (g.h()) {
                        str = Boolean.toString(g.f());
                    } else if (g.j()) {
                        str = g.b();
                    } else {
                        throw new AssertionError();
                    }
                } else if (wzVar instanceof xb) {
                    str = "null";
                } else {
                    throw new AssertionError();
                }
                abx.a(str);
                this.b.write(abx, arrayList2.get(i));
                i++;
            }
            abx.e();
        }
    }
}
