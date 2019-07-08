package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: IMASDK */
public abstract class xj<T> {
    public abstract T read(abu abu) throws IOException;

    public abstract void write(abx abx, T t) throws IOException;

    public final void toJson(Writer writer, T t) throws IOException {
        write(new abx(writer), t);
    }

    public final xj<T> nullSafe() {
        return new xk(this);
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final wz toJsonTree(T t) {
        try {
            zl zlVar = new zl();
            write(zlVar, t);
            return zlVar.a();
        } catch (IOException e) {
            throw new xa((Throwable) e);
        }
    }

    public final T fromJson(Reader reader) throws IOException {
        return read(new abu(reader));
    }

    public final T fromJson(String str) throws IOException {
        return fromJson((Reader) new StringReader(str));
    }

    public final T fromJsonTree(wz wzVar) {
        try {
            return read(new zj(wzVar));
        } catch (IOException e) {
            throw new xa((Throwable) e);
        }
    }
}
