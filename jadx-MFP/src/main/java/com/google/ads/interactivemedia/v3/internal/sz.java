package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: IMASDK */
public final class sz extends sj {
    private RandomAccessFile a;
    private Uri b;
    private long c;
    private boolean d;

    public sz() {
        super(false);
    }

    public final long a(sr srVar) throws ta {
        long j;
        try {
            this.b = srVar.a;
            b(srVar);
            this.a = new RandomAccessFile(srVar.a.getPath(), "r");
            this.a.seek(srVar.e);
            if (srVar.f == -1) {
                j = this.a.length() - srVar.e;
            } else {
                j = srVar.f;
            }
            this.c = j;
            if (this.c >= 0) {
                this.d = true;
                c(srVar);
                return this.c;
            }
            throw new EOFException();
        } catch (IOException e) {
            throw new ta(e);
        }
    }

    public final int a(byte[] bArr, int i, int i2) throws ta {
        if (i2 == 0) {
            return 0;
        }
        long j = this.c;
        if (j == 0) {
            return -1;
        }
        try {
            int read = this.a.read(bArr, i, (int) Math.min(j, (long) i2));
            if (read > 0) {
                this.c -= (long) read;
                a(read);
            }
            return read;
        } catch (IOException e) {
            throw new ta(e);
        }
    }

    public final Uri a() {
        return this.b;
    }

    public final void c() throws ta {
        this.b = null;
        try {
            if (this.a != null) {
                this.a.close();
            }
            this.a = null;
            if (this.d) {
                this.d = false;
                d();
            }
        } catch (IOException e) {
            throw new ta(e);
        } catch (Throwable th) {
            this.a = null;
            if (this.d) {
                this.d = false;
                d();
            }
            throw th;
        }
    }
}
