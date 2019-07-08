package com.uacf.core.caching;

import com.uacf.core.mapping.Mapper2;
import java.util.Map;

public interface Cache<T> {
    Map<String, T> allItems();

    void clear();

    boolean contains(String str);

    void flush();

    T get(String str);

    long getMetadata(String str, long j);

    void put(String str, T t);

    void putMetadata(String str, long j);

    void remove(String str);

    Cache<T> withMapper(Mapper2 mapper2);
}
