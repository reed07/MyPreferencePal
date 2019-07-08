package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.StringWriter;

/* compiled from: IMASDK */
public class wz {
    public final xe g() {
        if (this instanceof xe) {
            return (xe) this;
        }
        StringBuilder sb = new StringBuilder("Not a JSON Primitive: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public boolean f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            abx abx = new abx(stringWriter);
            abx.b(true);
            yu.a(this, abx);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
