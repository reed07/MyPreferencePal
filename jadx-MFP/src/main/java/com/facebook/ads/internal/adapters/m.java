package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.e.a;
import java.util.Map;

public class m extends b {
    /* access modifiers changed from: private */
    public static final String c = "m";
    /* access modifiers changed from: private */
    public final a d;
    private final c e;
    /* access modifiers changed from: private */
    public l f;
    private boolean g;

    public m(Context context, c cVar, a aVar, com.facebook.ads.internal.x.a aVar2, c cVar2) {
        super(context, cVar2, aVar2);
        this.e = cVar;
        this.d = aVar;
    }

    public void a(l lVar) {
        this.f = lVar;
    }

    /* access modifiers changed from: protected */
    public void a(Map<String, String> map) {
        l lVar = this.f;
        if (lVar != null && !TextUtils.isEmpty(lVar.getClientToken())) {
            this.e.a(this.f.getClientToken(), map);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.g     // Catch:{ all -> 0x002b }
            if (r0 != 0) goto L_0x0029
            com.facebook.ads.internal.adapters.l r0 = r2.f     // Catch:{ all -> 0x002b }
            if (r0 != 0) goto L_0x000a
            goto L_0x0029
        L_0x000a:
            r0 = 1
            r2.g = r0     // Catch:{ all -> 0x002b }
            com.facebook.ads.internal.w.e.a r0 = r2.d     // Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0027
            com.facebook.ads.internal.adapters.l r0 = r2.f     // Catch:{ all -> 0x002b }
            java.lang.String r0 = r0.d()     // Catch:{ all -> 0x002b }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x002b }
            if (r0 != 0) goto L_0x0027
            com.facebook.ads.internal.w.e.a r0 = r2.d     // Catch:{ all -> 0x002b }
            com.facebook.ads.internal.adapters.m$1 r1 = new com.facebook.ads.internal.adapters.m$1     // Catch:{ all -> 0x002b }
            r1.<init>()     // Catch:{ all -> 0x002b }
            r0.post(r1)     // Catch:{ all -> 0x002b }
        L_0x0027:
            monitor-exit(r2)
            return
        L_0x0029:
            monitor-exit(r2)
            return
        L_0x002b:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.m.b():void");
    }
}
