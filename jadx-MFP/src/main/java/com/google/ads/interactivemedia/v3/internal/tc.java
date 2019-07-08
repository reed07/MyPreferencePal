package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class tc implements la {
    public final long a;
    public final long b;
    public final long c;
    public final boolean d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;
    public final pj i;
    public final Uri j;
    public final ow k;
    private final List<ov> l;

    public tc(long j2, long j3, long j4, boolean z, long j5, long j6, long j7, long j8, ow owVar, pj pjVar, Uri uri, List<ov> list) {
        this.a = j2;
        this.b = j3;
        this.c = j4;
        this.d = z;
        this.e = j5;
        this.f = j6;
        this.g = j7;
        this.h = j8;
        this.k = owVar;
        this.i = pjVar;
        this.j = uri;
        this.l = list == null ? Collections.emptyList() : list;
    }

    public int a() {
        return this.l.size();
    }

    public ov a(int i2) {
        return (ov) this.l.get(i2);
    }

    public long b(int i2) {
        if (i2 != this.l.size() - 1) {
            return ((ov) this.l.get(i2 + 1)).b - ((ov) this.l.get(i2)).b;
        }
        long j2 = this.b;
        if (j2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return j2 - ((ov) this.l.get(i2)).b;
    }

    public long c(int i2) {
        return at.b(b(i2));
    }

    /* renamed from: b */
    public tc a(List<lc> list) {
        long j2;
        LinkedList linkedList = new LinkedList(list);
        Collections.sort(linkedList);
        linkedList.add(new lc(-1, -1, -1));
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        int i2 = 0;
        while (true) {
            j2 = -9223372036854775807L;
            if (i2 >= a()) {
                break;
            }
            if (((lc) linkedList.peek()).a != i2) {
                long b2 = b(i2);
                if (b2 != -9223372036854775807L) {
                    j3 += b2;
                }
            } else {
                ov a2 = a(i2);
                ov ovVar = new ov(a2.a, a2.b - j3, a(a2.c, linkedList), a2.d);
                arrayList.add(ovVar);
            }
            i2++;
        }
        long j4 = this.b;
        if (j4 != -9223372036854775807L) {
            j2 = j4 - j3;
        }
        tc tcVar = new tc(this.a, j2, this.c, this.d, this.e, this.f, this.g, this.h, this.k, this.i, this.j, arrayList);
        return tcVar;
    }

    private static ArrayList<rr> a(List<rr> list, LinkedList<lc> linkedList) {
        lc lcVar = (lc) linkedList.poll();
        int i2 = lcVar.a;
        ArrayList<rr> arrayList = new ArrayList<>();
        do {
            int i3 = lcVar.b;
            rr rrVar = (rr) list.get(i3);
            List<oy> list2 = rrVar.c;
            ArrayList arrayList2 = new ArrayList();
            do {
                arrayList2.add((oy) list2.get(lcVar.c));
                lcVar = (lc) linkedList.poll();
                if (lcVar.a != i2) {
                    break;
                }
            } while (lcVar.b == i3);
            rr rrVar2 = new rr(rrVar.a, rrVar.b, arrayList2, rrVar.d, rrVar.e);
            arrayList.add(rrVar2);
        } while (lcVar.a == i2);
        linkedList.addFirst(lcVar);
        return arrayList;
    }
}
