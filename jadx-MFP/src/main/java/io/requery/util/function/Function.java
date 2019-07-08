package io.requery.util.function;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
