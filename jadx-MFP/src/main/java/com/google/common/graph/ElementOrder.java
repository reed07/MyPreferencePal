package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.Immutable;
import java.util.Comparator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
@Beta
public final class ElementOrder<T> {
    @NullableDecl
    private final Comparator<T> comparator;
    private final Type type;

    public enum Type {
        UNORDERED,
        INSERTION,
        SORTED
    }

    /* access modifiers changed from: 0000 */
    public <T1 extends T> ElementOrder<T1> cast() {
        return this;
    }

    private ElementOrder(Type type2, @NullableDecl Comparator<T> comparator2) {
        this.type = (Type) Preconditions.checkNotNull(type2);
        this.comparator = comparator2;
        boolean z = true;
        if ((type2 == Type.SORTED) != (comparator2 != null)) {
            z = false;
        }
        Preconditions.checkState(z);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, null);
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator2) {
        return new ElementOrder<>(Type.SORTED, comparator2);
    }

    public Type type() {
        return this.type;
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public boolean equals(@NullableDecl Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        if (this.type != elementOrder.type || !Objects.equal(this.comparator, elementOrder.comparator)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.comparator);
    }

    public String toString() {
        ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("type", (Object) this.type);
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            add.add("comparator", (Object) comparator2);
        }
        return add.toString();
    }

    /* access modifiers changed from: 0000 */
    public <K extends T, V> Map<K, V> createMap(int i) {
        switch (this.type) {
            case UNORDERED:
                return Maps.newHashMapWithExpectedSize(i);
            case INSERTION:
                return Maps.newLinkedHashMapWithExpectedSize(i);
            case SORTED:
                return Maps.newTreeMap(comparator());
            default:
                throw new AssertionError();
        }
    }
}
