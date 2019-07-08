package com.myfitnesspal.shared.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\nHÆ\u0003JK\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006 "}, d2 = {"Lcom/myfitnesspal/shared/model/AdUnit;", "", "adType", "Lcom/myfitnesspal/shared/model/AdType;", "dfpAdUnitId", "", "amazonAdUnitId", "amazonSlotUuid", "moPubAdUnitId", "isAutoRefresh", "", "(Lcom/myfitnesspal/shared/model/AdType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAdType", "()Lcom/myfitnesspal/shared/model/AdType;", "getAmazonAdUnitId", "()Ljava/lang/String;", "getAmazonSlotUuid", "getDfpAdUnitId", "()Z", "getMoPubAdUnitId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdUnit.kt */
public final class AdUnit {
    @NotNull
    private final AdType adType;
    @Nullable
    private final String amazonAdUnitId;
    @Nullable
    private final String amazonSlotUuid;
    @NotNull
    private final String dfpAdUnitId;
    private final boolean isAutoRefresh;
    @Nullable
    private final String moPubAdUnitId;

    @NotNull
    public static /* synthetic */ AdUnit copy$default(AdUnit adUnit, AdType adType2, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            adType2 = adUnit.adType;
        }
        if ((i & 2) != 0) {
            str = adUnit.dfpAdUnitId;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = adUnit.amazonAdUnitId;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = adUnit.amazonSlotUuid;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = adUnit.moPubAdUnitId;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            z = adUnit.isAutoRefresh;
        }
        return adUnit.copy(adType2, str5, str6, str7, str8, z);
    }

    @NotNull
    public final AdType component1() {
        return this.adType;
    }

    @NotNull
    public final String component2() {
        return this.dfpAdUnitId;
    }

    @Nullable
    public final String component3() {
        return this.amazonAdUnitId;
    }

    @Nullable
    public final String component4() {
        return this.amazonSlotUuid;
    }

    @Nullable
    public final String component5() {
        return this.moPubAdUnitId;
    }

    public final boolean component6() {
        return this.isAutoRefresh;
    }

    @NotNull
    public final AdUnit copy(@NotNull AdType adType2, @NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, boolean z) {
        Intrinsics.checkParameterIsNotNull(adType2, "adType");
        Intrinsics.checkParameterIsNotNull(str, "dfpAdUnitId");
        AdUnit adUnit = new AdUnit(adType2, str, str2, str3, str4, z);
        return adUnit;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AdUnit) {
                AdUnit adUnit = (AdUnit) obj;
                if (Intrinsics.areEqual((Object) this.adType, (Object) adUnit.adType) && Intrinsics.areEqual((Object) this.dfpAdUnitId, (Object) adUnit.dfpAdUnitId) && Intrinsics.areEqual((Object) this.amazonAdUnitId, (Object) adUnit.amazonAdUnitId) && Intrinsics.areEqual((Object) this.amazonSlotUuid, (Object) adUnit.amazonSlotUuid) && Intrinsics.areEqual((Object) this.moPubAdUnitId, (Object) adUnit.moPubAdUnitId)) {
                    if (this.isAutoRefresh == adUnit.isAutoRefresh) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        AdType adType2 = this.adType;
        int i = 0;
        int hashCode = (adType2 != null ? adType2.hashCode() : 0) * 31;
        String str = this.dfpAdUnitId;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.amazonAdUnitId;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.amazonSlotUuid;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.moPubAdUnitId;
        if (str4 != null) {
            i = str4.hashCode();
        }
        int i2 = (hashCode4 + i) * 31;
        boolean z = this.isAutoRefresh;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdUnit(adType=");
        sb.append(this.adType);
        sb.append(", dfpAdUnitId=");
        sb.append(this.dfpAdUnitId);
        sb.append(", amazonAdUnitId=");
        sb.append(this.amazonAdUnitId);
        sb.append(", amazonSlotUuid=");
        sb.append(this.amazonSlotUuid);
        sb.append(", moPubAdUnitId=");
        sb.append(this.moPubAdUnitId);
        sb.append(", isAutoRefresh=");
        sb.append(this.isAutoRefresh);
        sb.append(")");
        return sb.toString();
    }

    public AdUnit(@NotNull AdType adType2, @NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, boolean z) {
        Intrinsics.checkParameterIsNotNull(adType2, "adType");
        Intrinsics.checkParameterIsNotNull(str, "dfpAdUnitId");
        this.adType = adType2;
        this.dfpAdUnitId = str;
        this.amazonAdUnitId = str2;
        this.amazonSlotUuid = str3;
        this.moPubAdUnitId = str4;
        this.isAutoRefresh = z;
    }

    @NotNull
    public final AdType getAdType() {
        return this.adType;
    }

    @NotNull
    public final String getDfpAdUnitId() {
        return this.dfpAdUnitId;
    }

    public /* synthetic */ AdUnit(AdType adType2, String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(adType2, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? true : z);
    }

    @Nullable
    public final String getAmazonAdUnitId() {
        return this.amazonAdUnitId;
    }

    @Nullable
    public final String getAmazonSlotUuid() {
        return this.amazonSlotUuid;
    }

    @Nullable
    public final String getMoPubAdUnitId() {
        return this.moPubAdUnitId;
    }

    public final boolean isAutoRefresh() {
        return this.isAutoRefresh;
    }
}
