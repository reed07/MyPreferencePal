package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: IMASDK */
public final class zj extends abu {
    private static final Reader b = new zk();
    private static final Object c = new Object();
    private Object[] d = new Object[32];
    private int e = 0;
    private String[] f = new String[32];
    private int[] g = new int[32];

    public zj(wz wzVar) {
        super(b);
        a((Object) wzVar);
    }

    public final void a() throws IOException {
        a(abw.BEGIN_ARRAY);
        a((Object) ((wx) t()).iterator());
        this.g[this.e - 1] = 0;
    }

    public final void b() throws IOException {
        a(abw.END_ARRAY);
        u();
        u();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public final void c() throws IOException {
        a(abw.BEGIN_OBJECT);
        a((Object) ((xc) t()).h().iterator());
    }

    public final void d() throws IOException {
        a(abw.END_OBJECT);
        u();
        u();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public final boolean e() throws IOException {
        abw f2 = f();
        return (f2 == abw.END_OBJECT || f2 == abw.END_ARRAY) ? false : true;
    }

    public final abw f() throws IOException {
        while (this.e != 0) {
            Object t = t();
            if (t instanceof Iterator) {
                boolean z = this.d[this.e - 2] instanceof xc;
                Iterator it = (Iterator) t;
                if (!it.hasNext()) {
                    return z ? abw.END_OBJECT : abw.END_ARRAY;
                }
                if (z) {
                    return abw.NAME;
                }
                a(it.next());
            } else if (t instanceof xc) {
                return abw.BEGIN_OBJECT;
            } else {
                if (t instanceof wx) {
                    return abw.BEGIN_ARRAY;
                }
                if (t instanceof xe) {
                    xe xeVar = (xe) t;
                    if (xeVar.j()) {
                        return abw.STRING;
                    }
                    if (xeVar.h()) {
                        return abw.BOOLEAN;
                    }
                    if (xeVar.i()) {
                        return abw.NUMBER;
                    }
                    throw new AssertionError();
                } else if (t instanceof xb) {
                    return abw.NULL;
                } else {
                    if (t == c) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                    throw new AssertionError();
                }
            }
        }
        return abw.END_DOCUMENT;
    }

    private final Object t() {
        return this.d[this.e - 1];
    }

    private final Object u() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object obj = objArr[i];
        objArr[this.e] = null;
        return obj;
    }

    private final void a(abw abw) throws IOException {
        if (f() != abw) {
            StringBuilder sb = new StringBuilder("Expected ");
            sb.append(abw);
            sb.append(" but was ");
            sb.append(f());
            sb.append(v());
            throw new IllegalStateException(sb.toString());
        }
    }

    public final String g() throws IOException {
        a(abw.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        String str = (String) entry.getKey();
        this.f[this.e - 1] = str;
        a(entry.getValue());
        return str;
    }

    public final String h() throws IOException {
        abw f2 = f();
        if (f2 == abw.STRING || f2 == abw.NUMBER) {
            String b2 = ((xe) u()).b();
            int i = this.e;
            if (i > 0) {
                int[] iArr = this.g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return b2;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(abw.STRING);
        sb.append(" but was ");
        sb.append(f2);
        sb.append(v());
        throw new IllegalStateException(sb.toString());
    }

    public final boolean i() throws IOException {
        a(abw.BOOLEAN);
        boolean f2 = ((xe) u()).f();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return f2;
    }

    public final void j() throws IOException {
        a(abw.NULL);
        u();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public final double k() throws IOException {
        abw f2 = f();
        if (f2 == abw.NUMBER || f2 == abw.STRING) {
            double c2 = ((xe) t()).c();
            if (q() || (!Double.isNaN(c2) && !Double.isInfinite(c2))) {
                u();
                int i = this.e;
                if (i > 0) {
                    int[] iArr = this.g;
                    int i2 = i - 1;
                    iArr[i2] = iArr[i2] + 1;
                }
                return c2;
            }
            StringBuilder sb = new StringBuilder("JSON forbids NaN and infinities: ");
            sb.append(c2);
            throw new NumberFormatException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("Expected ");
        sb2.append(abw.NUMBER);
        sb2.append(" but was ");
        sb2.append(f2);
        sb2.append(v());
        throw new IllegalStateException(sb2.toString());
    }

    public final long l() throws IOException {
        abw f2 = f();
        if (f2 == abw.NUMBER || f2 == abw.STRING) {
            long d2 = ((xe) t()).d();
            u();
            int i = this.e;
            if (i > 0) {
                int[] iArr = this.g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return d2;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(abw.NUMBER);
        sb.append(" but was ");
        sb.append(f2);
        sb.append(v());
        throw new IllegalStateException(sb.toString());
    }

    public final int m() throws IOException {
        abw f2 = f();
        if (f2 == abw.NUMBER || f2 == abw.STRING) {
            int e2 = ((xe) t()).e();
            u();
            int i = this.e;
            if (i > 0) {
                int[] iArr = this.g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return e2;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(abw.NUMBER);
        sb.append(" but was ");
        sb.append(f2);
        sb.append(v());
        throw new IllegalStateException(sb.toString());
    }

    public final void close() throws IOException {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public final void n() throws IOException {
        if (f() == abw.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            u();
            int i = this.e;
            if (i > 0) {
                this.f[i - 1] = "null";
            }
        }
        int i2 = this.e;
        if (i2 > 0) {
            int[] iArr = this.g;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    public final String toString() {
        return getClass().getSimpleName();
    }

    public final void o() throws IOException {
        a(abw.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        a(entry.getValue());
        a((Object) new xe((String) entry.getKey()));
    }

    private final void a(Object obj) {
        int i = this.e;
        Object[] objArr = this.d;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[(i << 1)];
            int[] iArr = new int[(i << 1)];
            String[] strArr = new String[(i << 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.g, 0, iArr, 0, this.e);
            System.arraycopy(this.f, 0, strArr, 0, this.e);
            this.d = objArr2;
            this.g = iArr;
            this.f = strArr;
        }
        Object[] objArr3 = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        objArr3[i2] = obj;
    }

    public final String p() {
        StringBuilder sb = new StringBuilder("$");
        int i = 0;
        while (i < this.e) {
            Object[] objArr = this.d;
            if (objArr[i] instanceof wx) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.g[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof xc) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.f;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    private final String v() {
        StringBuilder sb = new StringBuilder(" at path ");
        sb.append(p());
        return sb.toString();
    }
}
