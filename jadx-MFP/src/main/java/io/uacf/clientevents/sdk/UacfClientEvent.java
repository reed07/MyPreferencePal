package io.uacf.clientevents.sdk;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import io.uacf.core.app.UacfAppId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UacfClientEvent {
    /* access modifiers changed from: private */
    @Expose
    public UacfAppId applicationName;
    /* access modifiers changed from: private */
    @Expose
    public String clientId;
    /* access modifiers changed from: private */
    @Expose
    public String deviceId;
    /* access modifiers changed from: private */
    @Expose
    public String domain;
    /* access modifiers changed from: private */
    @Expose
    public List<UacfClientEventAttributes> events;
    /* access modifiers changed from: private */
    @Expose
    public String locale;
    /* access modifiers changed from: private */
    @Expose
    public String uacfId;
    /* access modifiers changed from: private */
    @Expose
    public String userAgent;
    /* access modifiers changed from: private */
    @Expose
    public String userId;

    public static final class Builder {
        private UacfAppId applicationName;
        private String clientId;
        private String deviceId;
        private String domain;
        private List<UacfClientEventAttributes> events;
        private String locale;
        private String uacfId;
        private String userAgent;
        private String userId;

        public Builder() {
        }

        public Builder(UacfClientEvent uacfClientEvent) {
            withClientId(uacfClientEvent.clientId).withDeviceId(uacfClientEvent.deviceId).withLocale(uacfClientEvent.locale).withApplicationName(uacfClientEvent.applicationName).withDomain(uacfClientEvent.domain).withUserId(uacfClientEvent.userId).withUacfId(uacfClientEvent.uacfId).withUserAgent(uacfClientEvent.userAgent).withEvents(uacfClientEvent.events);
        }

        public Builder withClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder withDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder withLocale(String str) {
            this.locale = str;
            return this;
        }

        public Builder withApplicationName(UacfAppId uacfAppId) {
            this.applicationName = uacfAppId;
            return this;
        }

        public Builder withDomain(String str) {
            this.domain = nullifyIfEmptyString(str);
            return this;
        }

        public Builder withUserId(String str) {
            this.userId = nullifyIfEmptyString(str);
            return this;
        }

        public Builder withUacfId(String str) {
            this.uacfId = nullifyIfEmptyString(str);
            return this;
        }

        public Builder withUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public Builder withEvents(List<UacfClientEventAttributes> list) {
            if (CollectionUtils.notEmpty((Collection<?>) list)) {
                ensureEvents();
                this.events.addAll(list);
            }
            return this;
        }

        public Builder withEvent(UacfClientEventAttributes uacfClientEventAttributes) {
            ensureEvents();
            this.events.add(uacfClientEventAttributes);
            return this;
        }

        private String nullifyIfEmptyString(String str) {
            if (Strings.notEmpty(str)) {
                return str;
            }
            return null;
        }

        private void ensureEvents() {
            if (this.events == null) {
                this.events = new ArrayList();
            }
        }

        public UacfClientEvent build() {
            UacfClientEvent uacfClientEvent = new UacfClientEvent();
            uacfClientEvent.clientId = this.clientId;
            uacfClientEvent.deviceId = this.deviceId;
            uacfClientEvent.locale = this.locale;
            uacfClientEvent.applicationName = this.applicationName;
            uacfClientEvent.domain = this.domain;
            uacfClientEvent.userId = this.userId;
            uacfClientEvent.uacfId = this.uacfId;
            uacfClientEvent.userAgent = this.userAgent;
            uacfClientEvent.events = this.events;
            return uacfClientEvent;
        }
    }

    private UacfClientEvent() {
    }

    public String getDomain() {
        return this.domain;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUacfId() {
        return this.uacfId;
    }

    public List<UacfClientEventAttributes> getEvents() {
        return this.events;
    }
}
