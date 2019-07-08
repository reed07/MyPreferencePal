package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.js.a;

/* compiled from: IMASDK */
final class qf extends mq {
    public qf(sf sfVar) {
        super(sfVar);
    }

    public final void a(bs bsVar) {
        js jsVar = bsVar.f;
        if (jsVar == null) {
            jsVar = null;
        } else {
            int a = jsVar.a();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= a) {
                    i2 = -1;
                    break;
                }
                a a2 = jsVar.a(i2);
                if (a2 instanceof ku) {
                    if (HlsMediaChunk.PRIV_TIMESTAMP_FRAME_OWNER.equals(((ku) a2).a)) {
                        break;
                    }
                }
                i2++;
            }
            if (i2 != -1) {
                if (a == 1) {
                    jsVar = null;
                } else {
                    a[] aVarArr = new a[(a - 1)];
                    while (i < a) {
                        if (i != i2) {
                            aVarArr[i < i2 ? i : i - 1] = jsVar.a(i);
                        }
                        i++;
                    }
                    jsVar = new js(aVarArr);
                }
            }
        }
        super.a(bsVar.a(jsVar));
    }
}
