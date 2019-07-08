package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.Session;

public interface SessionVerifier<T extends Session> {
    void verifySession(T t);
}
