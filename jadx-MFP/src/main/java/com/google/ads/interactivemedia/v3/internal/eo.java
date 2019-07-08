package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

/* compiled from: IMASDK */
final class eo extends ed {
    eo() {
    }

    public final int c() {
        return 2;
    }

    public final boolean a(int i, int i2, int i3) throws dk {
        if (i3 == 3 || i3 == 2 || i3 == Integer.MIN_VALUE || i3 == 1073741824) {
            return b(i, i2, i3);
        }
        throw new dk(i, i2, i3);
    }

    public final boolean a() {
        return (this.d == 0 || this.d == 2) ? false : true;
    }

    public final void a(ByteBuffer byteBuffer) {
        int i;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i2 = limit - position;
        int i3 = this.d;
        if (i3 == Integer.MIN_VALUE) {
            i = (i2 / 3) << 1;
        } else if (i3 == 3) {
            i = i2 << 1;
        } else if (i3 == 1073741824) {
            i = i2 / 2;
        } else {
            throw new IllegalStateException();
        }
        ByteBuffer a = a(i);
        int i4 = this.d;
        if (i4 == Integer.MIN_VALUE) {
            while (position < limit) {
                a.put(byteBuffer.get(position + 1));
                a.put(byteBuffer.get(position + 2));
                position += 3;
            }
        } else if (i4 == 3) {
            while (position < limit) {
                a.put(0);
                a.put((byte) ((byteBuffer.get(position) & 255) - 128));
                position++;
            }
        } else if (i4 == 1073741824) {
            while (position < limit) {
                a.put(byteBuffer.get(position + 2));
                a.put(byteBuffer.get(position + 3));
                position += 4;
            }
        } else {
            throw new IllegalStateException();
        }
        byteBuffer.position(byteBuffer.limit());
        a.flip();
    }
}
