package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class MfpAnalyticsEventsContainer {
    @Expose
    private String clientId;
    @Expose
    private UUID deviceId;
    @Expose
    private List<MfpAnalyticsEvent> events;
    @Expose
    private String userId;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(" deviceId = ");
        sb.append(this.deviceId.toString());
        sb.append(", clientId = ");
        sb.append(this.clientId);
        sb.append(", userId = ");
        sb.append(this.userId);
        sb.append(", events = [");
        if (CollectionUtils.size((Collection<?>) this.events) > 0) {
            for (MfpAnalyticsEvent mfpAnalyticsEvent : this.events) {
                sb.append(mfpAnalyticsEvent.toString());
            }
        }
        sb.append(" ] }");
        return sb.toString();
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public UUID getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(UUID uuid) {
        this.deviceId = uuid;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public List<MfpAnalyticsEvent> getEvents() {
        ensureList();
        return this.events;
    }

    public void setEvents(List<MfpAnalyticsEvent> list) {
        this.events = list;
    }

    public void add(MfpAnalyticsEvent mfpAnalyticsEvent) {
        ensureList();
        this.events.add(mfpAnalyticsEvent);
    }

    public void addAll(List<MfpAnalyticsEvent> list) {
        ensureList();
        this.events.addAll(list);
    }

    public void clear() {
        List<MfpAnalyticsEvent> list = this.events;
        if (list != null) {
            list.clear();
            this.events = null;
        }
    }

    private void ensureList() {
        if (this.events == null) {
            this.events = new ArrayList();
        }
    }
}
