package com.inmobi.ads;

import com.brightcove.player.event.AbstractEvent;
import java.util.Map;

public class InMobiAdRequest {
    final long a;
    final MonetizationContext b;
    final int c;
    final int d;
    final String e;
    final Map<String, String> f;

    public static class Builder {
        int a;
        int b;
        String c;
        Map<String, String> d;
        private long e;
        private MonetizationContext f = MonetizationContext.MONETIZATION_CONTEXT_OTHER;

        public Builder(long j) {
            this.e = j;
        }

        public Builder setMonetizationContext(MonetizationContext monetizationContext) {
            this.f = monetizationContext;
            return this;
        }

        public Builder setSlotSize(int i, int i2) {
            this.a = i;
            this.b = i2;
            return this;
        }

        public Builder setKeywords(String str) {
            this.c = str;
            return this;
        }

        public Builder setExtras(Map<String, String> map) {
            this.d = map;
            return this;
        }

        public InMobiAdRequest build() {
            InMobiAdRequest inMobiAdRequest = new InMobiAdRequest(this.e, this.f, this.a, this.b, this.c, this.d, 0);
            return inMobiAdRequest;
        }
    }

    public enum MonetizationContext {
        MONETIZATION_CONTEXT_ACTIVITY(AbstractEvent.ACTIVITY),
        MONETIZATION_CONTEXT_OTHER("others");
        
        final String a;

        private MonetizationContext(String str) {
            this.a = str;
        }

        static MonetizationContext a(String str) {
            MonetizationContext[] values;
            for (MonetizationContext monetizationContext : values()) {
                if (monetizationContext.a.equalsIgnoreCase(str)) {
                    return monetizationContext;
                }
            }
            return null;
        }

        public final String toString() {
            return this.a;
        }
    }

    /* synthetic */ InMobiAdRequest(long j, MonetizationContext monetizationContext, int i, int i2, String str, Map map, byte b2) {
        this(j, monetizationContext, i, i2, str, map);
    }

    private InMobiAdRequest(long j, MonetizationContext monetizationContext, int i, int i2, String str, Map<String, String> map) {
        this.a = j;
        this.b = monetizationContext;
        this.c = i;
        this.d = i2;
        this.e = str;
        this.f = map;
    }
}
