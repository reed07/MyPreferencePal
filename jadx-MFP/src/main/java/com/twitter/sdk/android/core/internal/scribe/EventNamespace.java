package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;

public class EventNamespace {
    @SerializedName("action")
    public final String action;
    @SerializedName("client")
    public final String client;
    @SerializedName("component")
    public final String component;
    @SerializedName("element")
    public final String element;
    @SerializedName("page")
    public final String page;
    @SerializedName("section")
    public final String section;

    public static class Builder {
        private String action;
        private String client;
        private String component;
        private String element;
        private String page;
        private String section;

        public Builder setClient(String str) {
            this.client = str;
            return this;
        }

        public Builder setPage(String str) {
            this.page = str;
            return this;
        }

        public Builder setSection(String str) {
            this.section = str;
            return this;
        }

        public Builder setComponent(String str) {
            this.component = str;
            return this;
        }

        public Builder setElement(String str) {
            this.element = str;
            return this;
        }

        public Builder setAction(String str) {
            this.action = str;
            return this;
        }

        public EventNamespace builder() {
            EventNamespace eventNamespace = new EventNamespace(this.client, this.page, this.section, this.component, this.element, this.action);
            return eventNamespace;
        }
    }

    public EventNamespace(String str, String str2, String str3, String str4, String str5, String str6) {
        this.client = str;
        this.page = str2;
        this.section = str3;
        this.component = str4;
        this.element = str5;
        this.action = str6;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("client=");
        sb.append(this.client);
        sb.append(", page=");
        sb.append(this.page);
        sb.append(", section=");
        sb.append(this.section);
        sb.append(", component=");
        sb.append(this.component);
        sb.append(", element=");
        sb.append(this.element);
        sb.append(", action=");
        sb.append(this.action);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventNamespace eventNamespace = (EventNamespace) obj;
        String str = this.action;
        if (str == null ? eventNamespace.action != null : !str.equals(eventNamespace.action)) {
            return false;
        }
        String str2 = this.client;
        if (str2 == null ? eventNamespace.client != null : !str2.equals(eventNamespace.client)) {
            return false;
        }
        String str3 = this.component;
        if (str3 == null ? eventNamespace.component != null : !str3.equals(eventNamespace.component)) {
            return false;
        }
        String str4 = this.element;
        if (str4 == null ? eventNamespace.element != null : !str4.equals(eventNamespace.element)) {
            return false;
        }
        String str5 = this.page;
        if (str5 == null ? eventNamespace.page != null : !str5.equals(eventNamespace.page)) {
            return false;
        }
        String str6 = this.section;
        return str6 == null ? eventNamespace.section == null : str6.equals(eventNamespace.section);
    }

    public int hashCode() {
        String str = this.client;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.page;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.section;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.component;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.element;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.action;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode5 + i;
    }
}
