package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.afs;

@afs(a = j.class)
/* compiled from: IMASDK */
public abstract class CompanionData {
    private String companionId;

    /* compiled from: IMASDK */
    public enum a {
        Html,
        Static,
        IFrame
    }

    public abstract String clickThroughUrl();

    public abstract String size();

    public abstract String src();

    public abstract a type();

    public static CompanionData create(String str, String str2, String str3, String str4, a aVar) {
        CompanionData create = create(str2, str3, str4, aVar);
        create.companionId = str;
        return create;
    }

    private static CompanionData create(String str, String str2, String str3, a aVar) {
        return new j(str, str2, str3, aVar);
    }

    public String companionId() {
        return this.companionId;
    }

    public String toString() {
        String companionId2 = companionId();
        String size = size();
        String src = src();
        String clickThroughUrl = clickThroughUrl();
        String valueOf = String.valueOf(type());
        StringBuilder sb = new StringBuilder(String.valueOf(companionId2).length() + 66 + String.valueOf(size).length() + String.valueOf(src).length() + String.valueOf(clickThroughUrl).length() + String.valueOf(valueOf).length());
        sb.append("CompanionData [companionId=");
        sb.append(companionId2);
        sb.append(", size=");
        sb.append(size);
        sb.append(", src=");
        sb.append(src);
        sb.append(", clickThroughUrl=");
        sb.append(clickThroughUrl);
        sb.append(", type=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
