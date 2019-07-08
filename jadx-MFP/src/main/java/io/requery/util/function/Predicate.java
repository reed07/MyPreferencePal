package io.requery.util.function;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
