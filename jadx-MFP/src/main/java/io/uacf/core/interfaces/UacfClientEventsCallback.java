package io.uacf.core.interfaces;

import java.util.Date;

public interface UacfClientEventsCallback {
    void reportEvent(String str, Object obj);

    void reportEvent(String str, Object obj, Date date);
}
