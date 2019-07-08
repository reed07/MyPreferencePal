package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class acr extends acj {
    private final int appVersion;
    private final String packageName;

    acr(int i, String str) {
        this.appVersion = i;
        if (str != null) {
            this.packageName = str;
            return;
        }
        throw new NullPointerException("Null packageName");
    }

    public final int appVersion() {
        return this.appVersion;
    }

    public final String packageName() {
        return this.packageName;
    }

    public final String toString() {
        int i = this.appVersion;
        String str = this.packageName;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("MarketAppInfo{appVersion=");
        sb.append(i);
        sb.append(", packageName=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof acj)) {
            return false;
        }
        acj acj = (acj) obj;
        return this.appVersion == acj.appVersion() && this.packageName.equals(acj.packageName());
    }

    public final int hashCode() {
        return ((this.appVersion ^ 1000003) * 1000003) ^ this.packageName.hashCode();
    }
}
