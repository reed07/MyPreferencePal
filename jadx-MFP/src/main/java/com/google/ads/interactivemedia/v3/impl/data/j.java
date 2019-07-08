package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.impl.data.CompanionData.a;

/* compiled from: IMASDK */
final class j extends CompanionData {
    private final String clickThroughUrl;
    private final String size;
    private final String src;
    private final a type;

    j(String str, String str2, String str3, a aVar) {
        if (str != null) {
            this.size = str;
            if (str2 != null) {
                this.src = str2;
                if (str3 != null) {
                    this.clickThroughUrl = str3;
                    if (aVar != null) {
                        this.type = aVar;
                        return;
                    }
                    throw new NullPointerException("Null type");
                }
                throw new NullPointerException("Null clickThroughUrl");
            }
            throw new NullPointerException("Null src");
        }
        throw new NullPointerException("Null size");
    }

    public final String size() {
        return this.size;
    }

    public final String src() {
        return this.src;
    }

    public final String clickThroughUrl() {
        return this.clickThroughUrl;
    }

    public final a type() {
        return this.type;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CompanionData)) {
            return false;
        }
        CompanionData companionData = (CompanionData) obj;
        return this.size.equals(companionData.size()) && this.src.equals(companionData.src()) && this.clickThroughUrl.equals(companionData.clickThroughUrl()) && this.type.equals(companionData.type());
    }

    public final int hashCode() {
        return ((((((this.size.hashCode() ^ 1000003) * 1000003) ^ this.src.hashCode()) * 1000003) ^ this.clickThroughUrl.hashCode()) * 1000003) ^ this.type.hashCode();
    }
}
