package com.facebook.ads.internal.w.c;

import android.support.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class e extends BufferedInputStream {
    private int a;
    private boolean b;
    private int c = Integer.MAX_VALUE;

    public e(InputStream inputStream) {
        super(inputStream);
    }

    public boolean a() {
        return this.b;
    }

    public synchronized void mark(int i) {
        this.c = i;
        super.mark(i);
    }

    public int read() {
        int i = this.a;
        if (i + 1 > this.c) {
            this.b = true;
            return -1;
        }
        this.a = i + 1;
        return super.read();
    }

    public int read(@NonNull byte[] bArr) {
        if (this.a + bArr.length <= this.c) {
            return super.read(bArr);
        }
        this.b = true;
        return -1;
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        if (this.a + i2 > this.c) {
            this.b = true;
            return -1;
        }
        int read = super.read(bArr, i, i2);
        this.a += read;
        return read;
    }

    public synchronized void reset() {
        this.c = Integer.MAX_VALUE;
        super.reset();
    }

    public synchronized long skip(long j) {
        if (((long) this.a) + j > ((long) this.c)) {
            this.b = true;
            return 0;
        }
        this.a = (int) (((long) this.a) + j);
        return super.skip(j);
    }
}
