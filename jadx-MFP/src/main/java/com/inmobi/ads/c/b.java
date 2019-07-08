package com.inmobi.ads.c;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.bi;
import com.inmobi.ads.bj;
import com.inmobi.ads.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: InterstitialPreLoader */
public class b extends a {
    /* access modifiers changed from: private */
    public static final String d = "b";
    private static volatile b e;
    private static final Object f = new Object();
    /* access modifiers changed from: private */
    public static List<a> g = new LinkedList();

    /* compiled from: InterstitialPreLoader */
    static class a extends com.inmobi.ads.i.b {
        private bi a;

        public final boolean i() {
            return false;
        }

        a(bi biVar) {
            this.a = biVar;
        }

        public final void a(boolean z) {
            b.d;
        }

        public final void a() {
            b.d;
            b.g.remove(this);
        }

        public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            b.d;
            new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage());
            i iVar = (i) a.a.remove(this.a);
            if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
                iVar.d("PreLoadServerNoFill");
            }
            b.g.remove(this);
        }
    }

    public static b d() {
        b bVar = e;
        if (bVar == null) {
            synchronized (f) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    private b() {
        super("int");
    }

    public static void a(i iVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", iVar.b());
        hashMap.put("plId", Long.valueOf(iVar.d));
        hashMap.put("clientRequestId", iVar.l);
    }

    public static void a(String str, i iVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorCode", str);
        hashMap.put("type", iVar.b());
        hashMap.put("plId", Long.valueOf(iVar.d));
        hashMap.put("clientRequestId", iVar.l);
    }

    static /* synthetic */ void a(b bVar) {
        if (b.c(bVar.c).a && a.size() >= b.c(bVar.c).c) {
            bj.a();
            ArrayList arrayList = (ArrayList) bj.a(bVar.c);
            Iterator it = a.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (!arrayList.contains(entry.getKey())) {
                    ((i) entry.getValue()).v();
                    it.remove();
                    StringBuilder sb = new StringBuilder("Removing extra ad unit from ad unit cache. Pid:");
                    sb.append(((bi) entry.getKey()).a);
                    sb.append(" tp:");
                    sb.append(((bi) entry.getKey()).b);
                }
            }
        }
    }
}
