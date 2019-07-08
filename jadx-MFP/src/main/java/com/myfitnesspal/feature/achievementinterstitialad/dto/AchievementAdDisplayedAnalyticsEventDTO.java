package com.myfitnesspal.feature.achievementinterstitialad.dto;

import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AchievementAdDisplayedAnalyticsEventDTO;", "", "adFormat", "", "adNetwork", "adUnitId", "screen", "userId", "clientId", "keywords", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;)V", "getAdFormat", "()Ljava/lang/String;", "getAdNetwork", "getAdUnitId", "getClientId", "getKeywords", "()Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "getScreen", "getUserId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementAdDisplayedAnalyticsEventDTO.kt */
public final class AchievementAdDisplayedAnalyticsEventDTO {
    @NotNull
    private final String adFormat;
    @NotNull
    private final String adNetwork;
    @NotNull
    private final String adUnitId;
    @NotNull
    private final String clientId;
    @NotNull
    private final AdTargetingDTO keywords;
    @NotNull
    private final String screen;
    @NotNull
    private final String userId;

    @NotNull
    public static /* synthetic */ AchievementAdDisplayedAnalyticsEventDTO copy$default(AchievementAdDisplayedAnalyticsEventDTO achievementAdDisplayedAnalyticsEventDTO, String str, String str2, String str3, String str4, String str5, String str6, AdTargetingDTO adTargetingDTO, int i, Object obj) {
        if ((i & 1) != 0) {
            str = achievementAdDisplayedAnalyticsEventDTO.adFormat;
        }
        if ((i & 2) != 0) {
            str2 = achievementAdDisplayedAnalyticsEventDTO.adNetwork;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = achievementAdDisplayedAnalyticsEventDTO.adUnitId;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = achievementAdDisplayedAnalyticsEventDTO.screen;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = achievementAdDisplayedAnalyticsEventDTO.userId;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = achievementAdDisplayedAnalyticsEventDTO.clientId;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            adTargetingDTO = achievementAdDisplayedAnalyticsEventDTO.keywords;
        }
        return achievementAdDisplayedAnalyticsEventDTO.copy(str, str7, str8, str9, str10, str11, adTargetingDTO);
    }

    @NotNull
    public final String component1() {
        return this.adFormat;
    }

    @NotNull
    public final String component2() {
        return this.adNetwork;
    }

    @NotNull
    public final String component3() {
        return this.adUnitId;
    }

    @NotNull
    public final String component4() {
        return this.screen;
    }

    @NotNull
    public final String component5() {
        return this.userId;
    }

    @NotNull
    public final String component6() {
        return this.clientId;
    }

    @NotNull
    public final AdTargetingDTO component7() {
        return this.keywords;
    }

    @NotNull
    public final AchievementAdDisplayedAnalyticsEventDTO copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull AdTargetingDTO adTargetingDTO) {
        String str7 = str;
        Intrinsics.checkParameterIsNotNull(str, "adFormat");
        String str8 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "adNetwork");
        String str9 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "adUnitId");
        String str10 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "screen");
        String str11 = str5;
        Intrinsics.checkParameterIsNotNull(str5, "userId");
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str6, Attributes.CLIENT_ID);
        AdTargetingDTO adTargetingDTO2 = adTargetingDTO;
        Intrinsics.checkParameterIsNotNull(adTargetingDTO2, Attributes.ACHIEVEMENT_KEYWORDS);
        AchievementAdDisplayedAnalyticsEventDTO achievementAdDisplayedAnalyticsEventDTO = new AchievementAdDisplayedAnalyticsEventDTO(str7, str8, str9, str10, str11, str12, adTargetingDTO2);
        return achievementAdDisplayedAnalyticsEventDTO;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.keywords, (java.lang.Object) r3.keywords) != false) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0051
            boolean r0 = r3 instanceof com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedAnalyticsEventDTO
            if (r0 == 0) goto L_0x004f
            com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedAnalyticsEventDTO r3 = (com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedAnalyticsEventDTO) r3
            java.lang.String r0 = r2.adFormat
            java.lang.String r1 = r3.adFormat
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.adNetwork
            java.lang.String r1 = r3.adNetwork
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.adUnitId
            java.lang.String r1 = r3.adUnitId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.screen
            java.lang.String r1 = r3.screen
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.userId
            java.lang.String r1 = r3.userId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.clientId
            java.lang.String r1 = r3.clientId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO r0 = r2.keywords
            com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO r3 = r3.keywords
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r3 = 0
            return r3
        L_0x0051:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedAnalyticsEventDTO.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.adFormat;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.adNetwork;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.adUnitId;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.screen;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.userId;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.clientId;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        AdTargetingDTO adTargetingDTO = this.keywords;
        if (adTargetingDTO != null) {
            i = adTargetingDTO.hashCode();
        }
        return hashCode6 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AchievementAdDisplayedAnalyticsEventDTO(adFormat=");
        sb.append(this.adFormat);
        sb.append(", adNetwork=");
        sb.append(this.adNetwork);
        sb.append(", adUnitId=");
        sb.append(this.adUnitId);
        sb.append(", screen=");
        sb.append(this.screen);
        sb.append(", userId=");
        sb.append(this.userId);
        sb.append(", clientId=");
        sb.append(this.clientId);
        sb.append(", keywords=");
        sb.append(this.keywords);
        sb.append(")");
        return sb.toString();
    }

    public AchievementAdDisplayedAnalyticsEventDTO(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull AdTargetingDTO adTargetingDTO) {
        Intrinsics.checkParameterIsNotNull(str, "adFormat");
        Intrinsics.checkParameterIsNotNull(str2, "adNetwork");
        Intrinsics.checkParameterIsNotNull(str3, "adUnitId");
        Intrinsics.checkParameterIsNotNull(str4, "screen");
        Intrinsics.checkParameterIsNotNull(str5, "userId");
        Intrinsics.checkParameterIsNotNull(str6, Attributes.CLIENT_ID);
        Intrinsics.checkParameterIsNotNull(adTargetingDTO, Attributes.ACHIEVEMENT_KEYWORDS);
        this.adFormat = str;
        this.adNetwork = str2;
        this.adUnitId = str3;
        this.screen = str4;
        this.userId = str5;
        this.clientId = str6;
        this.keywords = adTargetingDTO;
    }

    @NotNull
    public final String getAdFormat() {
        return this.adFormat;
    }

    @NotNull
    public final String getAdNetwork() {
        return this.adNetwork;
    }

    @NotNull
    public final String getAdUnitId() {
        return this.adUnitId;
    }

    public /* synthetic */ AchievementAdDisplayedAnalyticsEventDTO(String str, String str2, String str3, String str4, String str5, String str6, AdTargetingDTO adTargetingDTO, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "interstitial" : str, (i & 2) != 0 ? AchievementAdDefines.ACHIEVEMENT_AD_PROVIDER : str2, str3, (i & 8) != 0 ? AchievementAdDefines.ACHIEVEMENT_SHOWED_ON_NEWS_FEED : str4, str5, str6, adTargetingDTO);
    }

    @NotNull
    public final String getScreen() {
        return this.screen;
    }

    @NotNull
    public final String getUserId() {
        return this.userId;
    }

    @NotNull
    public final String getClientId() {
        return this.clientId;
    }

    @NotNull
    public final AdTargetingDTO getKeywords() {
        return this.keywords;
    }
}
