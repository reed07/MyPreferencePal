package com.twitter.sdk.android.core.internal.persistence;

public interface SerializationStrategy<T> {
    T deserialize(String str);

    String serialize(T t);
}
