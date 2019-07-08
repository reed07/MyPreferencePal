package com.facebook.ads.internal.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.s.c;
import java.util.HashMap;
import java.util.Map;

public abstract class h extends b {
    protected final m d;

    public h(Context context, c cVar, String str, @Nullable m mVar) {
        super(context, cVar, str);
        this.d = mVar;
    }

    public final void a() {
        m mVar = this.d;
        if (mVar != null) {
            mVar.a(this.c);
        }
        e();
    }

    /* access modifiers changed from: protected */
    public final void a(Map<String, String> map, @Nullable a aVar) {
        if (!TextUtils.isEmpty(this.c)) {
            if (this instanceof f) {
                this.b.h(this.c, map);
            } else {
                this.b.c(this.c, map);
            }
            boolean a = a.a(aVar);
            m mVar = this.d;
            if (mVar != null) {
                mVar.a(aVar);
                if (a) {
                    this.d.a();
                }
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put("leave_time", Long.toString(-1));
                hashMap.put("back_time", Long.toString(-1));
                hashMap.put("outcome", a.CANNOT_TRACK.name());
                this.b.m(this.c, hashMap);
            }
        }
        com.facebook.ads.internal.w.b.c.a(this.a, "Click logged");
    }

    /* access modifiers changed from: 0000 */
    public abstract void e();
}
