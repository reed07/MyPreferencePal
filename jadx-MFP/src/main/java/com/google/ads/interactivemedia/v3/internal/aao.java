package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.BitSet;

/* compiled from: IMASDK */
final class aao extends xj<BitSet> {
    aao() {
    }

    private static BitSet a(abu abu) throws IOException {
        boolean z;
        BitSet bitSet = new BitSet();
        abu.a();
        abw f = abu.f();
        int i = 0;
        while (f != abw.END_ARRAY) {
            switch (f.ordinal()) {
                case 5:
                    String h = abu.h();
                    try {
                        if (Integer.parseInt(h) == 0) {
                            z = false;
                            break;
                        } else {
                            z = true;
                            break;
                        }
                    } catch (NumberFormatException unused) {
                        StringBuilder sb = new StringBuilder("Error: Expecting: bitset number value (1, 0), Found: ");
                        sb.append(h);
                        throw new xh(sb.toString());
                    }
                case 6:
                    if (abu.m() == 0) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case 7:
                    z = abu.i();
                    break;
                default:
                    StringBuilder sb2 = new StringBuilder("Invalid bitset value type: ");
                    sb2.append(f);
                    throw new xh(sb2.toString());
            }
            if (z) {
                bitSet.set(i);
            }
            i++;
            f = abu.f();
        }
        abu.b();
        return bitSet;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return a(abu);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        BitSet bitSet = (BitSet) obj;
        abx.b();
        int length = bitSet.length();
        for (int i = 0; i < length; i++) {
            abx.a(bitSet.get(i) ? 1 : 0);
        }
        abx.c();
    }
}
