package com.twitter.sdk.android.core.internal.scribe;

import java.io.IOException;

public interface EventTransform<T> {
    byte[] toBytes(T t) throws IOException;
}
