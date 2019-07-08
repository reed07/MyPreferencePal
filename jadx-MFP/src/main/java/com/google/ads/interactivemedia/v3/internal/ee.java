package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: IMASDK */
final class ee extends ed {
    private int[] e;
    private boolean f;
    private int[] g;

    ee() {
    }

    public final void a(int[] iArr) {
        this.e = iArr;
    }

    public final boolean a(int i, int i2, int i3) throws dk {
        boolean z = !Arrays.equals(this.e, this.g);
        this.g = this.e;
        int[] iArr = this.g;
        if (iArr == null) {
            this.f = false;
            return z;
        } else if (i3 != 2) {
            throw new dk(i, i2, i3);
        } else if (!z && !b(i, i2, i3)) {
            return false;
        } else {
            this.f = i2 != iArr.length;
            int i4 = 0;
            while (i4 < iArr.length) {
                int i5 = iArr[i4];
                if (i5 < i2) {
                    this.f = (i5 != i4) | this.f;
                    i4++;
                } else {
                    throw new dk(i, i2, i3);
                }
            }
            return true;
        }
    }

    public final boolean a() {
        return this.f;
    }

    public final int b() {
        int[] iArr = this.g;
        return iArr == null ? this.c : iArr.length;
    }

    public final void a(ByteBuffer byteBuffer) {
        int[] iArr = (int[]) qi.a(this.g);
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        ByteBuffer a = a((((limit - position) / (this.c * 2)) * iArr.length) << 1);
        while (position < limit) {
            for (int i : iArr) {
                a.putShort(byteBuffer.getShort((i * 2) + position));
            }
            position += this.c << 1;
        }
        byteBuffer.position(limit);
        a.flip();
    }

    /* access modifiers changed from: protected */
    public final void m() {
        this.g = null;
        this.e = null;
        this.f = false;
    }
}
