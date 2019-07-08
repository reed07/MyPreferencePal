package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

/* compiled from: IMASDK */
public final class op implements Callback {
    private final sf a;
    private final or b;
    private final jw c = new jw();
    /* access modifiers changed from: private */
    public final Handler d = vf.a(vf.a(), (Callback) this);
    private final TreeMap<Long, Long> e = new TreeMap<>();
    private tc f;
    private long g;
    private long h = -9223372036854775807L;
    private long i = -9223372036854775807L;
    private boolean j;
    private boolean k;

    public op(tc tcVar, or orVar, sf sfVar) {
        this.f = tcVar;
        this.b = orVar;
        this.a = sfVar;
    }

    public final void a(tc tcVar) {
        this.j = false;
        this.g = -9223372036854775807L;
        this.f = tcVar;
        Iterator it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            if (((Long) ((Entry) it.next()).getKey()).longValue() < this.f.h) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(long j2) {
        boolean z = false;
        if (!this.f.d) {
            return false;
        }
        if (this.j) {
            return true;
        }
        Entry ceilingEntry = this.e.ceilingEntry(Long.valueOf(this.f.h));
        if (ceilingEntry != null && ((Long) ceilingEntry.getValue()).longValue() < j2) {
            this.g = ((Long) ceilingEntry.getKey()).longValue();
            this.b.a(this.g);
            z = true;
        }
        if (z) {
            c();
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(ng ngVar) {
        if (!this.f.d) {
            return false;
        }
        if (this.j) {
            return true;
        }
        long j2 = this.h;
        if (!(j2 != -9223372036854775807L && j2 < ngVar.h)) {
            return false;
        }
        c();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final void b(ng ngVar) {
        if (this.h != -9223372036854775807L || ngVar.i > this.h) {
            this.h = ngVar.i;
        }
    }

    public final os a() {
        return new os(this, new mq(this.a));
    }

    public final void b() {
        this.k = true;
        this.d.removeCallbacksAndMessages(null);
    }

    public final boolean handleMessage(Message message) {
        if (this.k) {
            return true;
        }
        if (message.what != 1) {
            return false;
        }
        oq oqVar = (oq) message.obj;
        long j2 = oqVar.a;
        long j3 = oqVar.b;
        Long l = (Long) this.e.get(Long.valueOf(j3));
        if (l == null) {
            this.e.put(Long.valueOf(j3), Long.valueOf(j2));
        } else if (l.longValue() > j2) {
            this.e.put(Long.valueOf(j3), Long.valueOf(j2));
        }
        return true;
    }

    private final void c() {
        long j2 = this.i;
        if (j2 == -9223372036854775807L || j2 != this.h) {
            this.j = true;
            this.i = this.h;
            this.b.a();
        }
    }

    /* access modifiers changed from: private */
    public static long b(ju juVar) {
        try {
            return vf.g(vf.a(juVar.e));
        } catch (ca unused) {
            return -9223372036854775807L;
        }
    }
}
