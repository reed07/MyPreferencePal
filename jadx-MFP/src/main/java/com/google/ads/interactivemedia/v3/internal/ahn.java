package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Objects;

/* compiled from: IMASDK */
public abstract class ahn<L, R> implements Serializable, Comparable<ahn<L, R>>, Entry<L, R> {
    private static final long serialVersionUID = 4954918890077093841L;

    public abstract L a();

    public abstract R b();

    public static <L, R> ahn<L, R> a(L l, R r) {
        return new ahm(l, r);
    }

    public final L getKey() {
        return a();
    }

    public R getValue() {
        return b();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return Objects.equals(getKey(), entry.getKey()) && Objects.equals(getValue(), entry.getValue());
    }

    public int hashCode() {
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (getValue() != null) {
            i = getValue().hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(a());
        sb.append(',');
        sb.append(b());
        sb.append(')');
        return sb.toString();
    }

    public /* synthetic */ int compareTo(Object obj) {
        ahn ahn = (ahn) obj;
        return new ahg().a(a(), ahn.a()).a(b(), ahn.b()).a();
    }
}
