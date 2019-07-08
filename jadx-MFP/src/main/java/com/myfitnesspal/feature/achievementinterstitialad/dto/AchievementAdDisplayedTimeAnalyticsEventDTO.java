package com.myfitnesspal.feature.achievementinterstitialad.dto;

import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006("}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AchievementAdDisplayedTimeAnalyticsEventDTO;", "", "adFormat", "", "adNetwork", "adUnitId", "screen", "userId", "clientId", "displayTime", "", "keywords", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;)V", "getAdFormat", "()Ljava/lang/String;", "getAdNetwork", "getAdUnitId", "getClientId", "getDisplayTime", "()J", "getKeywords", "()Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "getScreen", "getUserId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementAdDisplayedTimeAnalyticsEventDTO.kt */
public final class AchievementAdDisplayedTimeAnalyticsEventDTO {
    @NotNull
    private final String adFormat;
    @NotNull
    private final String adNetwork;
    @NotNull
    private final String adUnitId;
    @NotNull
    private final String clientId;
    private final long displayTime;
    @NotNull
    private final AdTargetingDTO keywords;
    @NotNull
    private final String screen;
    @NotNull
    private final String userId;

    @NotNull
    public static /* synthetic */ AchievementAdDisplayedTimeAnalyticsEventDTO copy$default(AchievementAdDisplayedTimeAnalyticsEventDTO achievementAdDisplayedTimeAnalyticsEventDTO, String str, String str2, String str3, String str4, String str5, String str6, long j, AdTargetingDTO adTargetingDTO, int i, Object obj) {
        AchievementAdDisplayedTimeAnalyticsEventDTO achievementAdDisplayedTimeAnalyticsEventDTO2 = achievementAdDisplayedTimeAnalyticsEventDTO;
        int i2 = i;
        return achievementAdDisplayedTimeAnalyticsEventDTO.copy((i2 & 1) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.adFormat : str, (i2 & 2) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.adNetwork : str2, (i2 & 4) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.adUnitId : str3, (i2 & 8) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.screen : str4, (i2 & 16) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.userId : str5, (i2 & 32) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.clientId : str6, (i2 & 64) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.displayTime : j, (i2 & 128) != 0 ? achievementAdDisplayedTimeAnalyticsEventDTO2.keywords : adTargetingDTO);
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

    public final long component7() {
        return this.displayTime;
    }

    @NotNull
    public final AdTargetingDTO component8() {
        return this.keywords;
    }

    @NotNull
    public final AchievementAdDisplayedTimeAnalyticsEventDTO copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, long j, @NotNull AdTargetingDTO adTargetingDTO) {
        String str7 = str;
        Intrinsics.checkParameterIsNotNull(str, "adFormat");
        String str8 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "adNetwork");
        String str9 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "adUnitId");
        String str10 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "screen");
        String str11 = str5;
        Intrinsics.checkParameterIsNotNull(str11, "userId");
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str12, Attributes.CLIENT_ID);
        AdTargetingDTO adTargetingDTO2 = adTargetingDTO;
        Intrinsics.checkParameterIsNotNull(adTargetingDTO2, Attributes.ACHIEVEMENT_KEYWORDS);
        AchievementAdDisplayedTimeAnalyticsEventDTO achievementAdDisplayedTimeAnalyticsEventDTO = new AchievementAdDisplayedTimeAnalyticsEventDTO(str7, str8, str9, str10, str11, str12, j, adTargetingDTO2);
        return achievementAdDisplayedTimeAnalyticsEventDTO;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AchievementAdDisplayedTimeAnalyticsEventDTO) {
                AchievementAdDisplayedTimeAnalyticsEventDTO achievementAdDisplayedTimeAnalyticsEventDTO = (AchievementAdDisplayedTimeAnalyticsEventDTO) obj;
                if (Intrinsics.areEqual((Object) this.adFormat, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.adFormat) && Intrinsics.areEqual((Object) this.adNetwork, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.adNetwork) && Intrinsics.areEqual((Object) this.adUnitId, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.adUnitId) && Intrinsics.areEqual((Object) this.screen, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.screen) && Intrinsics.areEqual((Object) this.userId, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.userId) && Intrinsics.areEqual((Object) this.clientId, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.clientId)) {
                    if (!(this.displayTime == achievementAdDisplayedTimeAnalyticsEventDTO.displayTime) || !Intrinsics.areEqual((Object) this.keywords, (Object) achievementAdDisplayedTimeAnalyticsEventDTO.keywords)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
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
        int hashCode6 = (((hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + Long.hashCode(this.displayTime)) * 31;
        AdTargetingDTO adTargetingDTO = this.keywords;
        if (adTargetingDTO != null) {
            i = adTargetingDTO.hashCode();
        }
        return hashCode6 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AchievementAdDisplayedTimeAnalyticsEventDTO(adFormat=");
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
        sb.append(", displayTime=");
        sb.append(this.displayTime);
        sb.append(", keywords=");
        sb.append(this.keywords);
        sb.append(")");
        return sb.toString();
    }

    public AchievementAdDisplayedTimeAnalyticsEventDTO(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, long j, @NotNull AdTargetingDTO adTargetingDTO) {
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
        this.displayTime = j;
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

    public /* synthetic */ AchievementAdDisplayedTimeAnalyticsEventDTO(String str, String str2, String str3, String str4, String str5, String str6, long j, AdTargetingDTO adTargetingDTO, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "interstitial" : str, (i & 2) != 0 ? AchievementAdDefines.ACHIEVEMENT_AD_PROVIDER : str2, str3, (i & 8) != 0 ? AchievementAdDefines.ACHIEVEMENT_SHOWED_ON_NEWS_FEED : str4, str5, str6, j, adTargetingDTO);
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

    public final long getDisplayTime() {
        return this.displayTime;
    }

    @NotNull
    public final AdTargetingDTO getKeywords() {
        return this.keywords;
    }
}
