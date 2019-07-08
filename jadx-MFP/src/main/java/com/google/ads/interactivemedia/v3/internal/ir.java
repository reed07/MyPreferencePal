package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.List;

/* compiled from: IMASDK */
final class ir {
    private final List<bs> a;
    private final gc[] b;

    public ir(List<bs> list) {
        this.a = list;
        this.b = new gc[list.size()];
    }

    public final void a(fs fsVar, jd jdVar) {
        for (int i = 0; i < this.b.length; i++) {
            jdVar.a();
            gc a2 = fsVar.a(jdVar.b(), 3);
            bs bsVar = (bs) this.a.get(i);
            String str = bsVar.h;
            boolean z = MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str);
            String str2 = "Invalid closed caption mime type provided: ";
            String valueOf = String.valueOf(str);
            qi.a(z, (Object) valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            a2.a(bs.a(bsVar.a != null ? bsVar.a : jdVar.c(), str, (String) null, -1, bsVar.c, bsVar.x, bsVar.y, (fa) null, Long.MAX_VALUE, bsVar.j));
            this.b[i] = a2;
        }
    }

    public final void a(long j, ut utVar) {
        rb.a(j, utVar, this.b);
    }
}