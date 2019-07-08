package io.uacf.clientevents.sdk;

import io.uacf.clientevents.internal.ClientEventsService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UacfClientEventsSdkImpl implements UacfClientEventsSdk {
    private final ClientEventsService clientEventsService;

    public UacfClientEventsSdkImpl(ClientEventsService clientEventsService2) {
        this.clientEventsService = clientEventsService2;
    }

    public void reportEvent(String str, Object obj) {
        reportEvent(str, obj, null);
    }

    public void reportEvent(String str, Object obj, Date date) {
        this.clientEventsService.reportEvent(str, convertAttributesIfNecessary(obj), date);
    }

    private Object convertAttributesIfNecessary(Object obj) {
        if (obj == null || !obj.getClass().isAnonymousClass()) {
            return obj;
        }
        if (obj instanceof Map) {
            return new HashMap((Map) obj);
        }
        throw new IllegalArgumentException("Anonymous classes other than a Map<> subclass are not supported");
    }
}
