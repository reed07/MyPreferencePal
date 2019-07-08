package io.uacf.inbox.sdk.model;

import com.google.gson.annotations.Expose;
import java.util.Map;

public class UacfNotificationAnalyticData {
    @Expose
    private Map<String, Object> data;

    public UacfNotificationAnalyticData(Map<String, Object> map) {
        this.data = map;
    }

    public Map<String, Object> getData() {
        return this.data;
    }
}
