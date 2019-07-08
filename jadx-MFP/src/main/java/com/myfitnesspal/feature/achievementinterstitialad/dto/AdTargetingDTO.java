package com.myfitnesspal.feature.achievementinterstitialad.dto;

import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JY\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006$"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "", "interstitialAdId", "", "achievementDay", "premiumUser", "age", "gender", "krux", "deviceId", "ladtr", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAchievementDay", "()Ljava/lang/String;", "getAge", "getDeviceId", "getGender", "getInterstitialAdId", "getKrux", "getLadtr", "getPremiumUser", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdTargetingDTO.kt */
public final class AdTargetingDTO {
    @NotNull
    private final String achievementDay;
    @NotNull
    private final String age;
    @NotNull
    private final String deviceId;
    @NotNull
    private final String gender;
    @NotNull
    private final String interstitialAdId;
    @NotNull
    private final String krux;
    @NotNull
    private final String ladtr;
    @NotNull
    private final String premiumUser;

    @NotNull
    public static /* synthetic */ AdTargetingDTO copy$default(AdTargetingDTO adTargetingDTO, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, Object obj) {
        AdTargetingDTO adTargetingDTO2 = adTargetingDTO;
        int i2 = i;
        return adTargetingDTO.copy((i2 & 1) != 0 ? adTargetingDTO2.interstitialAdId : str, (i2 & 2) != 0 ? adTargetingDTO2.achievementDay : str2, (i2 & 4) != 0 ? adTargetingDTO2.premiumUser : str3, (i2 & 8) != 0 ? adTargetingDTO2.age : str4, (i2 & 16) != 0 ? adTargetingDTO2.gender : str5, (i2 & 32) != 0 ? adTargetingDTO2.krux : str6, (i2 & 64) != 0 ? adTargetingDTO2.deviceId : str7, (i2 & 128) != 0 ? adTargetingDTO2.ladtr : str8);
    }

    @NotNull
    public final String component1() {
        return this.interstitialAdId;
    }

    @NotNull
    public final String component2() {
        return this.achievementDay;
    }

    @NotNull
    public final String component3() {
        return this.premiumUser;
    }

    @NotNull
    public final String component4() {
        return this.age;
    }

    @NotNull
    public final String component5() {
        return this.gender;
    }

    @NotNull
    public final String component6() {
        return this.krux;
    }

    @NotNull
    public final String component7() {
        return this.deviceId;
    }

    @NotNull
    public final String component8() {
        return this.ladtr;
    }

    @NotNull
    public final AdTargetingDTO copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8) {
        String str9 = str;
        Intrinsics.checkParameterIsNotNull(str, "interstitialAdId");
        String str10 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "achievementDay");
        String str11 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "premiumUser");
        String str12 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "age");
        String str13 = str5;
        Intrinsics.checkParameterIsNotNull(str5, "gender");
        String str14 = str6;
        Intrinsics.checkParameterIsNotNull(str14, "krux");
        String str15 = str7;
        Intrinsics.checkParameterIsNotNull(str15, "deviceId");
        String str16 = str8;
        Intrinsics.checkParameterIsNotNull(str16, Keywords.LIMIT_AD_TRACKING);
        AdTargetingDTO adTargetingDTO = new AdTargetingDTO(str9, str10, str11, str12, str13, str14, str15, str16);
        return adTargetingDTO;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.ladtr, (java.lang.Object) r3.ladtr) != false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x005b
            boolean r0 = r3 instanceof com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO
            if (r0 == 0) goto L_0x0059
            com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO r3 = (com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO) r3
            java.lang.String r0 = r2.interstitialAdId
            java.lang.String r1 = r3.interstitialAdId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.achievementDay
            java.lang.String r1 = r3.achievementDay
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.premiumUser
            java.lang.String r1 = r3.premiumUser
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.age
            java.lang.String r1 = r3.age
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.gender
            java.lang.String r1 = r3.gender
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.krux
            java.lang.String r1 = r3.krux
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.deviceId
            java.lang.String r1 = r3.deviceId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.ladtr
            java.lang.String r3 = r3.ladtr
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r3 = 0
            return r3
        L_0x005b:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.interstitialAdId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.achievementDay;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.premiumUser;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.age;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.gender;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.krux;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.deviceId;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.ladtr;
        if (str8 != null) {
            i = str8.hashCode();
        }
        return hashCode7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdTargetingDTO(interstitialAdId=");
        sb.append(this.interstitialAdId);
        sb.append(", achievementDay=");
        sb.append(this.achievementDay);
        sb.append(", premiumUser=");
        sb.append(this.premiumUser);
        sb.append(", age=");
        sb.append(this.age);
        sb.append(", gender=");
        sb.append(this.gender);
        sb.append(", krux=");
        sb.append(this.krux);
        sb.append(", deviceId=");
        sb.append(this.deviceId);
        sb.append(", ladtr=");
        sb.append(this.ladtr);
        sb.append(")");
        return sb.toString();
    }

    public AdTargetingDTO(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8) {
        Intrinsics.checkParameterIsNotNull(str, "interstitialAdId");
        Intrinsics.checkParameterIsNotNull(str2, "achievementDay");
        Intrinsics.checkParameterIsNotNull(str3, "premiumUser");
        Intrinsics.checkParameterIsNotNull(str4, "age");
        Intrinsics.checkParameterIsNotNull(str5, "gender");
        Intrinsics.checkParameterIsNotNull(str6, "krux");
        Intrinsics.checkParameterIsNotNull(str7, "deviceId");
        Intrinsics.checkParameterIsNotNull(str8, Keywords.LIMIT_AD_TRACKING);
        this.interstitialAdId = str;
        this.achievementDay = str2;
        this.premiumUser = str3;
        this.age = str4;
        this.gender = str5;
        this.krux = str6;
        this.deviceId = str7;
        this.ladtr = str8;
    }

    @NotNull
    public final String getInterstitialAdId() {
        return this.interstitialAdId;
    }

    @NotNull
    public final String getAchievementDay() {
        return this.achievementDay;
    }

    @NotNull
    public final String getPremiumUser() {
        return this.premiumUser;
    }

    @NotNull
    public final String getAge() {
        return this.age;
    }

    @NotNull
    public final String getGender() {
        return this.gender;
    }

    @NotNull
    public final String getKrux() {
        return this.krux;
    }

    @NotNull
    public final String getDeviceId() {
        return this.deviceId;
    }

    @NotNull
    public final String getLadtr() {
        return this.ladtr;
    }
}
