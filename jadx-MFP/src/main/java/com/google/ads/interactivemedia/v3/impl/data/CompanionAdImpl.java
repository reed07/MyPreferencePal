package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.CompanionAd;
import com.google.ads.interactivemedia.v3.internal.ahh;
import com.google.ads.interactivemedia.v3.internal.ahj;

/* compiled from: IMASDK */
public class CompanionAdImpl implements CompanionAd {
    private String apiFramework;
    private int height;
    private String resourceValue;
    private int width;

    public String getApiFramework() {
        return this.apiFramework;
    }

    public void setApiFramework(String str) {
        this.apiFramework = str;
    }

    public String getResourceValue() {
        return this.resourceValue;
    }

    public void setResourceValue(String str) {
        this.resourceValue = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int hashCode() {
        return ahj.a(this, new String[0]);
    }

    public boolean equals(Object obj) {
        return ahh.a((Object) this, obj, new String[0]);
    }

    public String toString() {
        String str = this.apiFramework;
        String str2 = this.resourceValue;
        int i = this.width;
        int i2 = this.height;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 80 + String.valueOf(str2).length());
        sb.append("CompanionAd [apiFramework=");
        sb.append(str);
        sb.append(", resourceUrl=");
        sb.append(str2);
        sb.append(", width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append("]");
        return sb.toString();
    }
}
