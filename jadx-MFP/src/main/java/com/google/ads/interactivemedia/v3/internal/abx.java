package com.google.ads.interactivemedia.v3.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* compiled from: IMASDK */
public class abx implements Closeable, Flushable {
    private static final String[] a = new String[128];
    private static final String[] b;
    private final Writer c;
    private int[] d = new int[32];
    private int e = 0;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private String j;
    private boolean k;

    public abx(Writer writer) {
        a(6);
        this.g = ":";
        this.k = true;
        if (writer != null) {
            this.c = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    public final void c(String str) {
        if (str.length() == 0) {
            this.f = null;
            this.g = ":";
            return;
        }
        this.f = str;
        this.g = ": ";
    }

    public final void b(boolean z) {
        this.h = z;
    }

    public final boolean g() {
        return this.h;
    }

    public final void c(boolean z) {
        this.i = z;
    }

    public final boolean h() {
        return this.i;
    }

    public final void d(boolean z) {
        this.k = z;
    }

    public final boolean i() {
        return this.k;
    }

    public abx b() throws IOException {
        j();
        return a(1, "[");
    }

    public abx c() throws IOException {
        return a(1, 2, "]");
    }

    public abx d() throws IOException {
        j();
        return a(3, "{");
    }

    public abx e() throws IOException {
        return a(3, 5, "}");
    }

    private final abx a(int i2, String str) throws IOException {
        l();
        a(i2);
        this.c.write(str);
        return this;
    }

    private final abx a(int i2, int i3, String str) throws IOException {
        int a2 = a();
        if (a2 != i3 && a2 != i2) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.j == null) {
            this.e--;
            if (a2 == i3) {
                k();
            }
            this.c.write(str);
            return this;
        } else {
            StringBuilder sb = new StringBuilder("Dangling name: ");
            sb.append(this.j);
            throw new IllegalStateException(sb.toString());
        }
    }

    private final void a(int i2) {
        int i3 = this.e;
        int[] iArr = this.d;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[(i3 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.d = iArr2;
        }
        int[] iArr3 = this.d;
        int i4 = this.e;
        this.e = i4 + 1;
        iArr3[i4] = i2;
    }

    private final int a() {
        int i2 = this.e;
        if (i2 != 0) {
            return this.d[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private final void b(int i2) {
        this.d[this.e - 1] = i2;
    }

    public abx a(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.j != null) {
            throw new IllegalStateException();
        } else if (this.e != 0) {
            this.j = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    private final void j() throws IOException {
        if (this.j != null) {
            int a2 = a();
            if (a2 == 5) {
                this.c.write(44);
            } else if (a2 != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            k();
            b(4);
            d(this.j);
            this.j = null;
        }
    }

    public abx b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        j();
        l();
        d(str);
        return this;
    }

    public abx f() throws IOException {
        if (this.j != null) {
            if (this.k) {
                j();
            } else {
                this.j = null;
                return this;
            }
        }
        l();
        this.c.write("null");
        return this;
    }

    public abx a(boolean z) throws IOException {
        j();
        l();
        this.c.write(z ? "true" : "false");
        return this;
    }

    public abx a(Boolean bool) throws IOException {
        if (bool == null) {
            return f();
        }
        j();
        l();
        this.c.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public abx a(long j2) throws IOException {
        j();
        l();
        this.c.write(Long.toString(j2));
        return this;
    }

    public abx a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        j();
        String obj = number.toString();
        if (this.h || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            l();
            this.c.append(obj);
            return this;
        }
        StringBuilder sb = new StringBuilder("Numeric values must be finite, but was ");
        sb.append(number);
        throw new IllegalArgumentException(sb.toString());
    }

    public void flush() throws IOException {
        if (this.e != 0) {
            this.c.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public void close() throws IOException {
        this.c.close();
        int i2 = this.e;
        if (i2 > 1 || (i2 == 1 && this.d[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.e = 0;
    }

    private final void d(String str) throws IOException {
        String str2;
        String[] strArr = this.i ? b : a;
        this.c.write("\"");
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i2 < i3) {
                this.c.write(str, i2, i3 - i2);
            }
            this.c.write(str2);
            i2 = i3 + 1;
        }
        if (i2 < length) {
            this.c.write(str, i2, length - i2);
        }
        this.c.write("\"");
    }

    private final void k() throws IOException {
        if (this.f != null) {
            this.c.write("\n");
            int i2 = this.e;
            for (int i3 = 1; i3 < i2; i3++) {
                this.c.write(this.f);
            }
        }
    }

    private final void l() throws IOException {
        switch (a()) {
            case 1:
                b(2);
                k();
                return;
            case 2:
                this.c.append(',');
                k();
                return;
            case 4:
                this.c.append(this.g);
                b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        b(7);
    }

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            a[i2] = String.format("\\u%04x", new Object[]{Integer.valueOf(i2)});
        }
        String[] strArr = a;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        b = strArr2;
        strArr2[60] = "\\u003c";
        String[] strArr3 = b;
        strArr3[62] = "\\u003e";
        strArr3[38] = "\\u0026";
        strArr3[61] = "\\u003d";
        strArr3[39] = "\\u0027";
    }
}
