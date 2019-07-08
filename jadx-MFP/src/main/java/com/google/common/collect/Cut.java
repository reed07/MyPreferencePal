package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
abstract class Cut<C extends Comparable> implements Serializable, Comparable<Cut<C>> {
    private static final long serialVersionUID = 0;
    @NullableDecl
    final C endpoint;

    private static final class AboveAll extends Cut<Comparable<?>> {
        /* access modifiers changed from: private */
        public static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0;

        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : 1;
        }

        /* access modifiers changed from: 0000 */
        public boolean isLessThan(Comparable<?> comparable) {
            return false;
        }

        public String toString() {
            return "+∞";
        }

        private AboveAll() {
            super(null);
        }

        /* access modifiers changed from: 0000 */
        public Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsLowerBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsUpperBound() {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: 0000 */
        public Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: 0000 */
        public Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: 0000 */
        public void describeAsLowerBound(StringBuilder sb) {
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append("+∞)");
        }

        /* access modifiers changed from: 0000 */
        public Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        public Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.maxValue();
        }

        public int hashCode() {
            return System.identityHashCode(this);
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class AboveValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return Cut.super.compareTo((Cut) obj);
        }

        AboveValue(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        /* access modifiers changed from: 0000 */
        public boolean isLessThan(C c) {
            return Range.compareOrThrow(this.endpoint, c) < 0;
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsLowerBound() {
            return BoundType.OPEN;
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsUpperBound() {
            return BoundType.CLOSED;
        }

        /* access modifiers changed from: 0000 */
        public Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    Comparable next = discreteDomain.next(this.endpoint);
                    return next == null ? Cut.belowAll() : belowValue(next);
                case OPEN:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: 0000 */
        public Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    return this;
                case OPEN:
                    Comparable next = discreteDomain.next(this.endpoint);
                    return next == null ? Cut.aboveAll() : belowValue(next);
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: 0000 */
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append('(');
            sb.append(this.endpoint);
        }

        /* access modifiers changed from: 0000 */
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(']');
        }

        /* access modifiers changed from: 0000 */
        public C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.next(this.endpoint);
        }

        /* access modifiers changed from: 0000 */
        public C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        /* access modifiers changed from: 0000 */
        public Cut<C> canonical(DiscreteDomain<C> discreteDomain) {
            Comparable leastValueAbove = leastValueAbove(discreteDomain);
            return leastValueAbove != null ? belowValue(leastValueAbove) : Cut.aboveAll();
        }

        public int hashCode() {
            return ~this.endpoint.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("/");
            sb.append(this.endpoint);
            sb.append("\\");
            return sb.toString();
        }
    }

    private static final class BelowAll extends Cut<Comparable<?>> {
        /* access modifiers changed from: private */
        public static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0;

        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : -1;
        }

        /* access modifiers changed from: 0000 */
        public boolean isLessThan(Comparable<?> comparable) {
            return true;
        }

        public String toString() {
            return "-∞";
        }

        private BelowAll() {
            super(null);
        }

        /* access modifiers changed from: 0000 */
        public Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsLowerBound() {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsUpperBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: 0000 */
        public Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: 0000 */
        public Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: 0000 */
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append("(-∞");
        }

        /* access modifiers changed from: 0000 */
        public void describeAsUpperBound(StringBuilder sb) {
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        public Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.minValue();
        }

        /* access modifiers changed from: 0000 */
        public Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        public Cut<Comparable<?>> canonical(DiscreteDomain<Comparable<?>> discreteDomain) {
            try {
                return Cut.belowValue(discreteDomain.minValue());
            } catch (NoSuchElementException unused) {
                return this;
            }
        }

        public int hashCode() {
            return System.identityHashCode(this);
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class BelowValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return Cut.super.compareTo((Cut) obj);
        }

        BelowValue(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        /* access modifiers changed from: 0000 */
        public boolean isLessThan(C c) {
            return Range.compareOrThrow(this.endpoint, c) <= 0;
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsLowerBound() {
            return BoundType.CLOSED;
        }

        /* access modifiers changed from: 0000 */
        public BoundType typeAsUpperBound() {
            return BoundType.OPEN;
        }

        /* access modifiers changed from: 0000 */
        public Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    return this;
                case OPEN:
                    Comparable previous = discreteDomain.previous(this.endpoint);
                    return previous == null ? Cut.belowAll() : new AboveValue<>(previous);
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: 0000 */
        public Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    Comparable previous = discreteDomain.previous(this.endpoint);
                    return previous == null ? Cut.aboveAll() : new AboveValue<>(previous);
                case OPEN:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: 0000 */
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append('[');
            sb.append(this.endpoint);
        }

        /* access modifiers changed from: 0000 */
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(')');
        }

        /* access modifiers changed from: 0000 */
        public C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        /* access modifiers changed from: 0000 */
        public C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.previous(this.endpoint);
        }

        public int hashCode() {
            return this.endpoint.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\\");
            sb.append(this.endpoint);
            sb.append("/");
            return sb.toString();
        }
    }

    /* access modifiers changed from: 0000 */
    public Cut<C> canonical(DiscreteDomain<C> discreteDomain) {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public abstract void describeAsLowerBound(StringBuilder sb);

    /* access modifiers changed from: 0000 */
    public abstract void describeAsUpperBound(StringBuilder sb);

    /* access modifiers changed from: 0000 */
    public abstract C greatestValueBelow(DiscreteDomain<C> discreteDomain);

    public abstract int hashCode();

    /* access modifiers changed from: 0000 */
    public abstract boolean isLessThan(C c);

    /* access modifiers changed from: 0000 */
    public abstract C leastValueAbove(DiscreteDomain<C> discreteDomain);

    /* access modifiers changed from: 0000 */
    public abstract BoundType typeAsLowerBound();

    /* access modifiers changed from: 0000 */
    public abstract BoundType typeAsUpperBound();

    /* access modifiers changed from: 0000 */
    public abstract Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    /* access modifiers changed from: 0000 */
    public abstract Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    Cut(@NullableDecl C c) {
        this.endpoint = c;
    }

    public int compareTo(Cut<C> cut) {
        if (cut == belowAll()) {
            return 1;
        }
        if (cut == aboveAll()) {
            return -1;
        }
        int compareOrThrow = Range.compareOrThrow(this.endpoint, cut.endpoint);
        if (compareOrThrow != 0) {
            return compareOrThrow;
        }
        return Booleans.compare(this instanceof AboveValue, cut instanceof AboveValue);
    }

    /* access modifiers changed from: 0000 */
    public C endpoint() {
        return this.endpoint;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj instanceof Cut) {
            try {
                if (compareTo((Cut) obj) == 0) {
                    z = true;
                }
                return z;
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    static <C extends Comparable> Cut<C> belowAll() {
        return BelowAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> aboveAll() {
        return AboveAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> belowValue(C c) {
        return new BelowValue(c);
    }

    static <C extends Comparable> Cut<C> aboveValue(C c) {
        return new AboveValue(c);
    }
}
