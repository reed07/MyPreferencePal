package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.Session;
import java.util.Map;

public interface SessionManager<T extends Session> {
    void clearSession(long j);

    T getActiveSession();

    T getSession(long j);

    Map<Long, T> getSessionMap();

    void setActiveSession(T t);
}
