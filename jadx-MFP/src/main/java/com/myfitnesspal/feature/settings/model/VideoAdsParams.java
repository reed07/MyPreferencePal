package com.myfitnesspal.feature.settings.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/myfitnesspal/feature/settings/model/VideoAdsParams;", "", "amazonAppId", "", "dfpAdUnit", "imaTagTemplate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAmazonAppId", "()Ljava/lang/String;", "getDfpAdUnit", "getImaTagTemplate", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: VideoAdsParams.kt */
public final class VideoAdsParams {
    @NotNull
    private final String amazonAppId;
    @NotNull
    private final String dfpAdUnit;
    @NotNull
    private final String imaTagTemplate;

    @NotNull
    public static /* synthetic */ VideoAdsParams copy$default(VideoAdsParams videoAdsParams, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoAdsParams.amazonAppId;
        }
        if ((i & 2) != 0) {
            str2 = videoAdsParams.dfpAdUnit;
        }
        if ((i & 4) != 0) {
            str3 = videoAdsParams.imaTagTemplate;
        }
        return videoAdsParams.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.amazonAppId;
    }

    @NotNull
    public final String component2() {
        return this.dfpAdUnit;
    }

    @NotNull
    public final String component3() {
        return this.imaTagTemplate;
    }

    @NotNull
    public final VideoAdsParams copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "amazonAppId");
        Intrinsics.checkParameterIsNotNull(str2, "dfpAdUnit");
        Intrinsics.checkParameterIsNotNull(str3, "imaTagTemplate");
        return new VideoAdsParams(str, str2, str3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.imaTagTemplate, (java.lang.Object) r3.imaTagTemplate) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.myfitnesspal.feature.settings.model.VideoAdsParams
            if (r0 == 0) goto L_0x0027
            com.myfitnesspal.feature.settings.model.VideoAdsParams r3 = (com.myfitnesspal.feature.settings.model.VideoAdsParams) r3
            java.lang.String r0 = r2.amazonAppId
            java.lang.String r1 = r3.amazonAppId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.dfpAdUnit
            java.lang.String r1 = r3.dfpAdUnit
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.imaTagTemplate
            java.lang.String r3 = r3.imaTagTemplate
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.model.VideoAdsParams.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.amazonAppId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.dfpAdUnit;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.imaTagTemplate;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VideoAdsParams(amazonAppId=");
        sb.append(this.amazonAppId);
        sb.append(", dfpAdUnit=");
        sb.append(this.dfpAdUnit);
        sb.append(", imaTagTemplate=");
        sb.append(this.imaTagTemplate);
        sb.append(")");
        return sb.toString();
    }

    public VideoAdsParams(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "amazonAppId");
        Intrinsics.checkParameterIsNotNull(str2, "dfpAdUnit");
        Intrinsics.checkParameterIsNotNull(str3, "imaTagTemplate");
        this.amazonAppId = str;
        this.dfpAdUnit = str2;
        this.imaTagTemplate = str3;
    }

    @NotNull
    public final String getAmazonAppId() {
        return this.amazonAppId;
    }

    @NotNull
    public final String getDfpAdUnit() {
        return this.dfpAdUnit;
    }

    @NotNull
    public final String getImaTagTemplate() {
        return this.imaTagTemplate;
    }
}
