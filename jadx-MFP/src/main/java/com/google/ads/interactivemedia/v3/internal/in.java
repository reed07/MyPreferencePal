package com.google.ads.interactivemedia.v3.internal;

import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.util.Arrays;

/* compiled from: IMASDK */
final class in {
    public byte[] a = new byte[PacketTypes.RetrieveDiaryDayForOtherUser];
    public int b;
    private final int c;
    private boolean d;
    private boolean e;

    public in(int i, int i2) {
        this.c = i;
        this.a[2] = 1;
    }

    public final void a() {
        this.d = false;
        this.e = false;
    }

    public final boolean b() {
        return this.e;
    }

    public final void a(int i) {
        boolean z = true;
        qi.c(!this.d);
        if (i != this.c) {
            z = false;
        }
        this.d = z;
        if (this.d) {
            this.b = 3;
            this.e = false;
        }
    }

    public final void a(byte[] bArr, int i, int i2) {
        if (this.d) {
            int i3 = i2 - i;
            byte[] bArr2 = this.a;
            int length = bArr2.length;
            int i4 = this.b;
            if (length < i4 + i3) {
                this.a = Arrays.copyOf(bArr2, (i4 + i3) << 1);
            }
            System.arraycopy(bArr, i, this.a, this.b, i3);
            this.b += i3;
        }
    }

    public final boolean b(int i) {
        if (!this.d) {
            return false;
        }
        this.b -= i;
        this.d = false;
        this.e = true;
        return true;
    }
}
