package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public final class zl extends abx {
    private static final Writer a = new zm();
    private static final xe b = new xe("closed");
    private final List<wz> c = new ArrayList();
    private String d;
    private wz e = xb.a;

    public zl() {
        super(a);
    }

    public final void flush() throws IOException {
    }

    public final wz a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        StringBuilder sb = new StringBuilder("Expected one JSON element but was ");
        sb.append(this.c);
        throw new IllegalStateException(sb.toString());
    }

    private final wz j() {
        List<wz> list = this.c;
        return (wz) list.get(list.size() - 1);
    }

    private final void a(wz wzVar) {
        if (this.d != null) {
            if (!(wzVar instanceof xb) || i()) {
                ((xc) j()).a(this.d, wzVar);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = wzVar;
        } else {
            wz j = j();
            if (j instanceof wx) {
                ((wx) j).a(wzVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public final abx b() throws IOException {
        wx wxVar = new wx();
        a((wz) wxVar);
        this.c.add(wxVar);
        return this;
    }

    public final abx c() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof wx) {
            List<wz> list = this.c;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final abx d() throws IOException {
        xc xcVar = new xc();
        a((wz) xcVar);
        this.c.add(xcVar);
        return this;
    }

    public final abx e() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof xc) {
            List<wz> list = this.c;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final abx a(String str) throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof xc) {
            this.d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final abx b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        a((wz) new xe(str));
        return this;
    }

    public final abx f() throws IOException {
        a((wz) xb.a);
        return this;
    }

    public final abx a(boolean z) throws IOException {
        a((wz) new xe(Boolean.valueOf(z)));
        return this;
    }

    public final abx a(Boolean bool) throws IOException {
        if (bool == null) {
            return f();
        }
        a((wz) new xe(bool));
        return this;
    }

    public final abx a(long j) throws IOException {
        a((wz) new xe((Number) Long.valueOf(j)));
        return this;
    }

    public final abx a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                StringBuilder sb = new StringBuilder("JSON forbids NaN and infinities: ");
                sb.append(number);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        a((wz) new xe(number));
        return this;
    }

    public final void close() throws IOException {
        if (this.c.isEmpty()) {
            this.c.add(b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
