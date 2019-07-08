package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: IMASDK */
public final class sg extends sj {
    private final AssetManager a;
    private Uri b;
    private InputStream c;
    private long d;
    private boolean e;

    public sg(Context context) {
        super(false);
        this.a = context.getAssets();
    }

    public final long a(sr srVar) throws ahd {
        try {
            this.b = srVar.a;
            String path = this.b.getPath();
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            b(srVar);
            this.c = this.a.open(path, 1);
            if (this.c.skip(srVar.e) >= srVar.e) {
                if (srVar.f != -1) {
                    this.d = srVar.f;
                } else {
                    this.d = (long) this.c.available();
                    if (this.d == 2147483647L) {
                        this.d = -1;
                    }
                }
                this.e = true;
                c(srVar);
                return this.d;
            }
            throw new EOFException();
        } catch (IOException e2) {
            throw new ahd(e2);
        }
    }

    public final int a(byte[] bArr, int i, int i2) throws ahd {
        if (i2 == 0) {
            return 0;
        }
        long j = this.d;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, (long) i2);
            } catch (IOException e2) {
                throw new ahd(e2);
            }
        }
        int read = this.c.read(bArr, i, i2);
        if (read != -1) {
            long j2 = this.d;
            if (j2 != -1) {
                this.d = j2 - ((long) read);
            }
            a(read);
            return read;
        } else if (this.d == -1) {
            return -1;
        } else {
            throw new ahd((IOException) new EOFException());
        }
    }

    public final Uri a() {
        return this.b;
    }

    public final void c() throws ahd {
        this.b = null;
        try {
            if (this.c != null) {
                this.c.close();
            }
            this.c = null;
            if (this.e) {
                this.e = false;
                d();
            }
        } catch (IOException e2) {
            throw new ahd(e2);
        } catch (Throwable th) {
            this.c = null;
            if (this.e) {
                this.e = false;
                d();
            }
            throw th;
        }
    }
}
