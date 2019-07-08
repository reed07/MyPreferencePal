package io.requery.sql.gen;

public interface Generator<E> {
    void write(Output output, E e);
}
