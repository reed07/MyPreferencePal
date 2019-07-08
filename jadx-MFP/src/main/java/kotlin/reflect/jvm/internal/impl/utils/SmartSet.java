package kotlin.reflect.jvm.internal.impl.utils;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;

/* compiled from: SmartSet.kt */
public final class SmartSet<T> extends AbstractSet<T> {
    private static final int ARRAY_THRESHOLD = 5;
    public static final Companion Companion = new Companion(null);
    private Object data;
    private int size;

    /* compiled from: SmartSet.kt */
    private static final class ArrayIterator<T> implements Iterator<T>, KMutableIterator {
        private final Iterator<T> arrayIterator;

        public ArrayIterator(@NotNull T[] tArr) {
            Intrinsics.checkParameterIsNotNull(tArr, "array");
            this.arrayIterator = ArrayIteratorKt.iterator(tArr);
        }

        public boolean hasNext() {
            return this.arrayIterator.hasNext();
        }

        public T next() {
            return this.arrayIterator.next();
        }

        @NotNull
        public Void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: SmartSet.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final <T> SmartSet<T> create() {
            return new SmartSet<>(null);
        }

        @JvmStatic
        @NotNull
        public final <T> SmartSet<T> create(@NotNull Collection<? extends T> collection) {
            Intrinsics.checkParameterIsNotNull(collection, "set");
            SmartSet<T> smartSet = new SmartSet<>(null);
            smartSet.addAll(collection);
            return smartSet;
        }
    }

    /* compiled from: SmartSet.kt */
    private static final class SingletonIterator<T> implements Iterator<T>, KMutableIterator {
        private final T element;
        private boolean hasNext = true;

        public SingletonIterator(T t) {
            this.element = t;
        }

        public T next() {
            if (this.hasNext) {
                this.hasNext = false;
                return this.element;
            }
            throw new NoSuchElementException();
        }

        public boolean hasNext() {
            return this.hasNext;
        }

        @NotNull
        public Void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @JvmStatic
    @NotNull
    public static final <T> SmartSet<T> create() {
        return Companion.create();
    }

    private SmartSet() {
    }

    public /* synthetic */ SmartSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    @NotNull
    public Iterator<T> iterator() {
        if (size() == 0) {
            return Collections.emptySet().iterator();
        }
        if (size() == 1) {
            return new SingletonIterator<>(this.data);
        }
        if (size() < ARRAY_THRESHOLD) {
            Object obj = this.data;
            if (obj != null) {
                return new ArrayIterator<>((Object[]) obj);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Object obj2 = this.data;
        if (obj2 != null) {
            return TypeIntrinsics.asMutableSet(obj2).iterator();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableSet<T>");
    }

    public boolean add(T t) {
        Object obj;
        if (size() == 0) {
            this.data = t;
        } else if (size() == 1) {
            if (Intrinsics.areEqual(this.data, (Object) t)) {
                return false;
            }
            this.data = new Object[]{this.data, t};
        } else if (size() < ARRAY_THRESHOLD) {
            Object obj2 = this.data;
            if (obj2 != null) {
                Object[] objArr = (Object[]) obj2;
                if (ArraysKt.contains((T[]) objArr, t)) {
                    return false;
                }
                if (size() == ARRAY_THRESHOLD - 1) {
                    LinkedHashSet linkedSetOf = SetsKt.linkedSetOf(Arrays.copyOf(objArr, objArr.length));
                    linkedSetOf.add(t);
                    obj = linkedSetOf;
                } else {
                    Object[] copyOf = Arrays.copyOf(objArr, size() + 1);
                    copyOf[copyOf.length - 1] = t;
                    obj = copyOf;
                }
                this.data = obj;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            Object obj3 = this.data;
            if (obj3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableSet<T>");
            } else if (!TypeIntrinsics.asMutableSet(obj3).add(t)) {
                return false;
            }
        }
        setSize(size() + 1);
        return true;
    }

    public void clear() {
        this.data = null;
        setSize(0);
    }

    public boolean contains(Object obj) {
        if (size() == 0) {
            return false;
        }
        if (size() == 1) {
            return Intrinsics.areEqual(this.data, obj);
        }
        if (size() < ARRAY_THRESHOLD) {
            Object obj2 = this.data;
            if (obj2 != null) {
                return ArraysKt.contains((T[]) (Object[]) obj2, obj);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Object obj3 = this.data;
        if (obj3 != null) {
            return ((Set) obj3).contains(obj);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Set<T>");
    }
}
