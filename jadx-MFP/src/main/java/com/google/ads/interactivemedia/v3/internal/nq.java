package com.google.ads.interactivemedia.v3.internal;

import com.brightcove.player.C;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: IMASDK */
public abstract class nq extends ng {
    private byte[] a;
    private volatile boolean b;

    public nq(sn snVar, sr srVar, int i, bs bsVar, int i2, Object obj, byte[] bArr) {
        super(snVar, srVar, 3, bsVar, i2, obj, -9223372036854775807L, -9223372036854775807L);
        this.a = bArr;
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, int i) throws IOException;

    public final byte[] c() {
        return this.a;
    }

    public final void a() {
        this.b = true;
    }

    public final void b() throws IOException, InterruptedException {
        try {
            this.j.a(this.c);
            int i = 0;
            int i2 = 0;
            while (i != -1 && !this.b) {
                if (this.a == null) {
                    this.a = new byte[C.DASH_ROLE_CAPTION_FLAG];
                } else if (this.a.length < i2 + C.DASH_ROLE_CAPTION_FLAG) {
                    this.a = Arrays.copyOf(this.a, this.a.length + C.DASH_ROLE_CAPTION_FLAG);
                }
                i = this.j.a(this.a, i2, C.DASH_ROLE_CAPTION_FLAG);
                if (i != -1) {
                    i2 += i;
                }
            }
            if (!this.b) {
                a(this.a, i2);
            }
        } finally {
            vf.a((sn) this.j);
        }
    }
}
