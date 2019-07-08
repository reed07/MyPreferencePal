package io.uacf.clientevents.internal;

import io.uacf.core.api.UacfApiException;
import java.util.Date;

public interface ClientEventsService {
    void reportEvent(String str, Object obj, Date date);

    boolean sendEventsToBackend() throws UacfApiException;
}
