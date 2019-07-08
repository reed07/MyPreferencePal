package io.requery.query;

public interface Aliasable<T> {
    T as(String str);

    String getAlias();
}
