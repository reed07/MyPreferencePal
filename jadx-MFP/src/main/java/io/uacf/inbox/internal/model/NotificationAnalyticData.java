package io.uacf.inbox.internal.model;

import com.google.gson.annotations.Expose;
import java.util.Map;

public class NotificationAnalyticData {
    @Expose
    private Map<String, Object> data;

    public static final class Builder {
    }

    public Map<String, Object> getData() {
        return this.data;
    }
}
