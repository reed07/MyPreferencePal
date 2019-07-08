package com.brightcove.player.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.NoSuchElementException;

public class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();
    private final T value;

    public static <T> Optional<T> from(T t) {
        return new Optional<>(t);
    }

    private Optional() {
        this(null);
    }

    public Optional(@Nullable T t) {
        this.value = t;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    @NonNull
    public T get() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public int hashCode() {
        T t = this.value;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional optional = (Optional) obj;
        T t = this.value;
        if (t != null) {
            z = t.equals(optional.value);
        } else if (optional.value != null) {
            z = false;
        }
        return z;
    }

    public static <T> Optional<T> empty() {
        return EMPTY;
    }
}
