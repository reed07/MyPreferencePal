package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.date.MfpIso8601Date;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Strings;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MfpAnalyticsEvent {
    @Expose
    private Map<String, String> attributes;
    @Expose
    private int sampleRate;
    @Expose
    private MfpIso8601Date timestamp = new MfpIso8601Date();
    @Expose
    private String type;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("type = ");
        sb.append(getType());
        sb.append(", timestamp = ");
        sb.append(Database.encodeDateAndTime(getTimestamp()));
        sb.append(", sampleRate = ");
        sb.append(getSampleRate());
        sb.append(", attributes = ");
        sb.append(Strings.toString(getAttributes()));
        sb.append(" },");
        return sb.toString();
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public MfpAnalyticsEvent setSampleRate(int i) {
        this.sampleRate = i;
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public MfpAnalyticsEvent setAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public MfpAnalyticsEvent addAttribute(String str, Object obj) {
        if (!Strings.isEmpty(str)) {
            ensureMap();
            this.attributes.put(str, Strings.toString(obj));
            return this;
        }
        throw new IllegalArgumentException("Key must not be empty");
    }

    public MfpAnalyticsEvent addAllAttributes(Map<String, String> map) {
        ensureMap();
        this.attributes.putAll(map);
        return this;
    }

    public String getType() {
        return this.type;
    }

    public MfpAnalyticsEvent setType(String str) {
        this.type = str;
        return this;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public MfpAnalyticsEvent setTimestamp(Date date) {
        this.timestamp = MfpIso8601Date.newInstance(date);
        return this;
    }

    private void ensureMap() {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
    }
}
