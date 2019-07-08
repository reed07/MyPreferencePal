package io.requery.query;

public interface Tuple {
    <V> V get(int i);

    <V> V get(String str);
}
