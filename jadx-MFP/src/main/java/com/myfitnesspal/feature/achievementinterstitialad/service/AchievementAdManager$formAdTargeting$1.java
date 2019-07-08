package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO;
import com.myfitnesspal.feature.achievementinterstitialad.service.Optional.Some;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "userSummary", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$formAdTargeting$1<T, R> implements Function<T, R> {
    final /* synthetic */ AchievementAdManager this$0;

    AchievementAdManager$formAdTargeting$1(AchievementAdManager achievementAdManager) {
        this.this$0 = achievementAdManager;
    }

    @Nullable
    public final AdTargetingDTO apply(@NotNull UserSummaryObject userSummaryObject) {
        String str;
        Intrinsics.checkParameterIsNotNull(userSummaryObject, "userSummary");
        this.this$0.setUserSummaryObject(userSummaryObject);
        Optional achievementDayByStreakDay$app_googleRelease = this.this$0.achievementDayByStreakDay$app_googleRelease(userSummaryObject.getLoginStreak());
        if (achievementDayByStreakDay$app_googleRelease instanceof Some) {
            StringBuilder sb = new StringBuilder();
            sb.append(AchievementAdDefines.TARGETING_VALUE_STREAK);
            sb.append(((Number) ((Some) achievementDayByStreakDay$app_googleRelease).getValue()).intValue());
            str = sb.toString();
        } else {
            str = "";
        }
        String str2 = str;
        Object obj = this.this$0.premiumService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "premiumService.get()");
        String str3 = ((PremiumService) obj).isPremiumSubscribed() ? "y" : "n";
        Object obj2 = this.this$0.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj2, "localSettingsService.get()");
        String str4 = ((LocalSettingsService) obj2).isAdTrackingEnabled() ? "n" : "y";
        AchievementAdManager achievementAdManager = this.this$0;
        String access$formAdId = achievementAdManager.formAdId();
        Object obj3 = this.this$0.adsSettings.get();
        Intrinsics.checkExpressionValueIsNotNull(obj3, "adsSettings.get()");
        String adsAgeString = ((AdsSettings) obj3).getAdsAgeString();
        Intrinsics.checkExpressionValueIsNotNull(adsAgeString, "adsSettings.get().adsAgeString");
        Object obj4 = this.this$0.adsSettings.get();
        Intrinsics.checkExpressionValueIsNotNull(obj4, "adsSettings.get()");
        String adsGenderString = ((AdsSettings) obj4).getAdsGenderString();
        Intrinsics.checkExpressionValueIsNotNull(adsGenderString, "adsSettings.get().adsGenderString");
        Object obj5 = this.this$0.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj5, "localSettingsService.get()");
        String gaid = ((LocalSettingsService) obj5).getGAID();
        Intrinsics.checkExpressionValueIsNotNull(gaid, "localSettingsService.get().gaid");
        Object obj6 = this.this$0.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj6, "localSettingsService.get()");
        String gaid2 = ((LocalSettingsService) obj6).getGAID();
        Intrinsics.checkExpressionValueIsNotNull(gaid2, "localSettingsService.get().gaid");
        AdTargetingDTO adTargetingDTO = new AdTargetingDTO(access$formAdId, str2, str3, adsAgeString, adsGenderString, gaid, gaid2, str4);
        achievementAdManager.setAdTargetingDTO(adTargetingDTO);
        return this.this$0.getAdTargetingDTO();
    }
}
