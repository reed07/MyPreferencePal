package io.uacf.clientevents.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import java.util.Calendar;
import java.util.Date;

public class UacfClientEventAttributes {
    @Expose
    protected Object attributes = new Object();
    @SerializedName("type")
    @Expose
    protected String eventName;
    @Expose
    protected GsonMappableIso8601Date timestamp;

    public static final class Builder {
        private Object attributes;
        private String eventName;
        private Date timestamp;

        public Builder withEventName(String str) {
            this.eventName = str;
            return this;
        }

        public Builder withTimestamp(Date date) {
            this.timestamp = date;
            return this;
        }

        public Builder withAttributes(Object obj) {
            this.attributes = obj;
            return this;
        }

        public final UacfClientEventAttributes build() {
            UacfClientEventAttributes createEvent = createEvent();
            createEvent.eventName = this.eventName;
            Date date = this.timestamp;
            if (date == null) {
                date = Calendar.getInstance().getTime();
            }
            createEvent.timestamp = GsonMappableIso8601Date.newInstance(date);
            Object obj = this.attributes;
            if (obj == null) {
                obj = new Object();
            }
            createEvent.attributes = obj;
            return createEvent;
        }

        /* access modifiers changed from: protected */
        public UacfClientEventAttributes createEvent() {
            return new UacfClientEventAttributes();
        }
    }

    protected UacfClientEventAttributes() {
    }
}
