package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

/* compiled from: IMASDK */
public class ex extends et {
    public final eu a;
    public ByteBuffer b;
    public long c;
    private final int d;

    public ex(int i) {
        this.a = new eu();
        this.d = i;
    }

    public final void d(int i) {
        ByteBuffer byteBuffer = this.b;
        if (byteBuffer == null) {
            this.b = e(i);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = this.b.position();
        int i2 = i + position;
        if (capacity < i2) {
            ByteBuffer e = e(i2);
            if (position > 0) {
                this.b.position(0);
                this.b.limit(position);
                e.put(this.b);
            }
            this.b = e;
        }
    }

    public final boolean e() {
        return this.b == null && this.d == 0;
    }

    public final boolean f() {
        return c(1073741824);
    }

    public final void g() {
        this.b.flip();
    }

    public final void a() {
        super.a();
        ByteBuffer byteBuffer = this.b;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    private final ByteBuffer e(int i) {
        int i2 = this.d;
        if (i2 == 1) {
            return ByteBuffer.allocate(i);
        }
        if (i2 == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        ByteBuffer byteBuffer = this.b;
        int capacity = byteBuffer == null ? 0 : byteBuffer.capacity();
        StringBuilder sb = new StringBuilder(44);
        sb.append("Buffer too small (");
        sb.append(capacity);
        sb.append(" < ");
        sb.append(i);
        sb.append(")");
        throw new IllegalStateException(sb.toString());
    }

    public ex() {
        this(1);
    }
}
