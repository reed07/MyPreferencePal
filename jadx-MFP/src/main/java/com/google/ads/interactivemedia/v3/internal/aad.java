package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aad extends xj<Class> {
    aad() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        Class cls = (Class) obj;
        StringBuilder sb = new StringBuilder("Attempted to serialize java.lang.Class: ");
        sb.append(cls.getName());
        sb.append(". Forgot to register a type adapter?");
        throw new UnsupportedOperationException(sb.toString());
    }
}
