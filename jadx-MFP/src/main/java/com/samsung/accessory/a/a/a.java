package com.samsung.accessory.a.a;

public final class a {
    private final byte[] a;
    private final int b;
    private int c = 0;
    private int d = 0;
    private boolean e = false;

    a(byte[] bArr, int i) {
        this.a = bArr;
        this.b = i;
    }

    public final synchronized void a(int i) {
        if (!this.e) {
            this.c = i;
        } else {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
    }

    public final synchronized void a(byte[] bArr, int i, int i2) throws c {
        if (this.e) {
            throw new IllegalStateException("Failed to extract from a recycled buffer!");
        } else if (this.c + this.d + i2 <= this.b) {
            System.arraycopy(bArr, i, this.a, this.c + this.d, i2);
            this.d += i2;
        } else {
            StringBuilder sb = new StringBuilder("Cannot extract from byte[]. Buffer length exceeded! [buff offset=");
            sb.append(this.c);
            sb.append("; payload len=");
            sb.append(this.d);
            sb.append("; length to write = ");
            sb.append(i2);
            sb.append("; buff len = ");
            sb.append(this.b);
            sb.append("]");
            throw new c(sb.toString());
        }
    }

    public final synchronized byte[] a() {
        if (!this.e) {
        } else {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return this.a;
    }

    public final synchronized byte[] b() {
        byte[] bArr;
        if (!this.e) {
            bArr = new byte[this.d];
            System.arraycopy(this.a, this.c, bArr, 0, this.d);
        } else {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return bArr;
    }

    public final synchronized int c() {
        if (!this.e) {
        } else {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return this.c;
    }

    public final synchronized int d() {
        if (!this.e) {
        } else {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return this.d;
    }

    public final synchronized boolean e() {
        if (this.e) {
            return false;
        }
        this.e = d.a(this.a);
        return this.e;
    }
}
