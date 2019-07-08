package io.requery.util.function;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
