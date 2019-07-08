package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;

/* compiled from: IMASDK */
final class em extends ed {
    private static final int e = Float.floatToIntBits(Float.NaN);

    em() {
    }

    public final int c() {
        return 4;
    }

    public final boolean a(int i, int i2, int i3) throws dk {
        if (vf.d(i3)) {
            return b(i, i2, i3);
        }
        throw new dk(i, i2, i3);
    }

    public final boolean a() {
        return vf.d(this.d);
    }

    public final void a(ByteBuffer byteBuffer) {
        boolean z = this.d == 1073741824;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        if (!z) {
            i = (i / 3) << 2;
        }
        ByteBuffer a = a(i);
        if (z) {
            while (position < limit) {
                a((byteBuffer.get(position) & 255) | ((byteBuffer.get(position + 1) & 255) << 8) | ((byteBuffer.get(position + 2) & 255) << Ascii.DLE) | ((byteBuffer.get(position + 3) & 255) << Ascii.CAN), a);
                position += 4;
            }
        } else {
            while (position < limit) {
                a(((byteBuffer.get(position) & 255) << 8) | ((byteBuffer.get(position + 1) & 255) << Ascii.DLE) | ((byteBuffer.get(position + 2) & 255) << Ascii.CAN), a);
                position += 3;
            }
        }
        byteBuffer.position(byteBuffer.limit());
        a.flip();
    }

    private static void a(int i, ByteBuffer byteBuffer) {
        int floatToIntBits = Float.floatToIntBits((float) (((double) i) * 4.656612875245797E-10d));
        if (floatToIntBits == e) {
            floatToIntBits = Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED);
        }
        byteBuffer.putInt(floatToIntBits);
    }
}
