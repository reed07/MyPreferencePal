package com.myfitnesspal.feature.achievementinterstitialad.service;

import android.support.annotation.VisibleForTesting;
import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedAnalyticsEventDTO;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedTimeAnalyticsEventDTO;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO;
import com.myfitnesspal.feature.achievementinterstitialad.repository.UserSummaryRepository;
import com.myfitnesspal.feature.achievementinterstitialad.service.Optional.None;
import com.myfitnesspal.feature.achievementinterstitialad.service.Optional.Some;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.achievementinterstitialad.util.DateExtensionsKt;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001Bo\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0006\u0010 \u001a\u00020!J\u001b\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020$H\u0001¢\u0006\u0002\b&J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(J \u0010*\u001a\u00020)2\u0006\u0010%\u001a\u00020$2\u0006\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020\u0012H\u0002J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00150(H\u0002J\u0010\u0010/\u001a\u00020)2\u0006\u0010+\u001a\u00020$H\u0002J\u0010\u00100\u001a\u00020)2\u0006\u0010+\u001a\u00020$H\u0002J\f\u00101\u001a\b\u0012\u0004\u0012\u00020)0(J\b\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u000203H\u0002J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00020$0(H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/service/AchievementAdManager;", "", "userSummaryRepository", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/achievementinterstitialad/repository/UserSummaryRepository;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "adsSettings", "Lcom/myfitnesspal/feature/settings/model/AdsSettings;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "storedAchievementEvents", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/StoredAchievementEvents;", "achievementInterstitialAd", "Lcom/myfitnesspal/feature/achievementinterstitialad/ui/AchievementInterstitialAd;", "achievementAdAnalyticsEvents", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/AchievementAdAnalyticsEvents;", "clientId", "", "(Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ljava/lang/String;)V", "adTargetingDTO", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "getAdTargetingDTO", "()Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "setAdTargetingDTO", "(Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;)V", "userSummaryObject", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "getUserSummaryObject", "()Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "setUserSummaryObject", "(Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;)V", "achievementAdShowed", "Lio/reactivex/Completable;", "achievementDayByStreakDay", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;", "", "streakDay", "achievementDayByStreakDay$app_googleRelease", "checkIsNeedToShowAchievementAd", "Lio/reactivex/Single;", "", "checkIsNeedToShowInterstitialAdForDay", "achievementDay", "nextAchievementDay", "formAdId", "formAdTargeting", "isAchievementShowedBefore", "isAchievementShowedForDay", "preloadInterstitialAd", "reportDisplayEvent", "", "reportDisplayedTimeEvent", "whatIsStreakDayNow", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
public final class AchievementAdManager {
    /* access modifiers changed from: private */
    public final Lazy<AchievementAdAnalyticsEvents> achievementAdAnalyticsEvents;
    /* access modifiers changed from: private */
    public final Lazy<AchievementInterstitialAd> achievementInterstitialAd;
    @Nullable
    private AdTargetingDTO adTargetingDTO;
    /* access modifiers changed from: private */
    public final Lazy<AdsSettings> adsSettings;
    private final String clientId;
    /* access modifiers changed from: private */
    public final Lazy<LocalSettingsService> localSettingsService;
    /* access modifiers changed from: private */
    public final Lazy<PremiumService> premiumService;
    /* access modifiers changed from: private */
    public final Lazy<StoredAchievementEvents> storedAchievementEvents;
    @Nullable
    private UserSummaryObject userSummaryObject;
    private final Lazy<UserSummaryRepository> userSummaryRepository;

    public AchievementAdManager(@NotNull Lazy<UserSummaryRepository> lazy, @NotNull Lazy<PremiumService> lazy2, @NotNull Lazy<AdsSettings> lazy3, @NotNull Lazy<LocalSettingsService> lazy4, @NotNull Lazy<StoredAchievementEvents> lazy5, @NotNull Lazy<AchievementInterstitialAd> lazy6, @NotNull Lazy<AchievementAdAnalyticsEvents> lazy7, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(lazy, "userSummaryRepository");
        Intrinsics.checkParameterIsNotNull(lazy2, "premiumService");
        Intrinsics.checkParameterIsNotNull(lazy3, "adsSettings");
        Intrinsics.checkParameterIsNotNull(lazy4, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy5, "storedAchievementEvents");
        Intrinsics.checkParameterIsNotNull(lazy6, "achievementInterstitialAd");
        Intrinsics.checkParameterIsNotNull(lazy7, "achievementAdAnalyticsEvents");
        Intrinsics.checkParameterIsNotNull(str, Attributes.CLIENT_ID);
        this.userSummaryRepository = lazy;
        this.premiumService = lazy2;
        this.adsSettings = lazy3;
        this.localSettingsService = lazy4;
        this.storedAchievementEvents = lazy5;
        this.achievementInterstitialAd = lazy6;
        this.achievementAdAnalyticsEvents = lazy7;
        this.clientId = str;
    }

    @Nullable
    public final UserSummaryObject getUserSummaryObject() {
        return this.userSummaryObject;
    }

    public final void setUserSummaryObject(@Nullable UserSummaryObject userSummaryObject2) {
        this.userSummaryObject = userSummaryObject2;
    }

    @Nullable
    public final AdTargetingDTO getAdTargetingDTO() {
        return this.adTargetingDTO;
    }

    public final void setAdTargetingDTO(@Nullable AdTargetingDTO adTargetingDTO2) {
        this.adTargetingDTO = adTargetingDTO2;
    }

    @NotNull
    public final Single<Boolean> checkIsNeedToShowAchievementAd() {
        Single<Boolean> map = whatIsStreakDayNow().map(new AchievementAdManager$checkIsNeedToShowAchievementAd$1(this)).map(new AchievementAdManager$checkIsNeedToShowAchievementAd$2(this));
        Intrinsics.checkExpressionValueIsNotNull(map, "whatIsStreakDayNow()    …r.first\n                }");
        return map;
    }

    @NotNull
    public final Single<Boolean> preloadInterstitialAd() {
        Single<Boolean> flatMap = formAdTargeting().flatMap(new AchievementAdManager$preloadInterstitialAd$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "formAdTargeting()\n      …      }\n                }");
        return flatMap;
    }

    private final Single<AdTargetingDTO> formAdTargeting() {
        Single<AdTargetingDTO> map = ((UserSummaryRepository) this.userSummaryRepository.get()).getUserSummary().map(new AchievementAdManager$formAdTargeting$1(this));
        Intrinsics.checkExpressionValueIsNotNull(map, "userSummaryRepository.ge…tingDTO\n                }");
        return map;
    }

    @NotNull
    public final Completable achievementAdShowed() {
        reportDisplayEvent();
        reportDisplayedTimeEvent();
        Completable flatMapCompletable = whatIsStreakDayNow().map(new AchievementAdManager$sam$io_reactivex_functions_Function$0(new AchievementAdManager$achievementAdShowed$storeAchievementEventObserver$1(this))).map(new AchievementAdManager$achievementAdShowed$storeAchievementEventObserver$2(this)).flatMapCompletable(AchievementAdManager$achievementAdShowed$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(flatMapCompletable, "storeAchievementEventObs…plete()\n                }");
        return flatMapCompletable;
    }

    /* access modifiers changed from: private */
    public final boolean checkIsNeedToShowInterstitialAdForDay(int i, int i2, int i3) {
        return i2 <= i && i3 - 1 >= i && !isAchievementShowedForDay(i2);
    }

    @NotNull
    @VisibleForTesting
    public final Optional<Integer> achievementDayByStreakDay$app_googleRelease(int i) {
        Integer num;
        Optional<Integer> optional = None.INSTANCE;
        int size = AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().size();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().get(i2);
            Intrinsics.checkExpressionValueIsNotNull(obj, "AchievementAdDefines.ACHIEVEMENT_DAYS[i]");
            int intValue = ((Number) obj).intValue();
            if (i2 < AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().size() - 1) {
                num = (Integer) AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().get(i2 + 1);
            } else {
                num = Integer.valueOf(Integer.MAX_VALUE);
            }
            Intrinsics.checkExpressionValueIsNotNull(num, "if (i < AchievementAdDef…t.MAX_VALUE\n            }");
            if (checkIsNeedToShowInterstitialAdForDay(i, intValue, num.intValue())) {
                optional = new Some<>(Integer.valueOf(intValue));
            }
        }
        return optional;
    }

    private final boolean isAchievementShowedForDay(int i) {
        if (isAchievementShowedBefore(i) && DateExtensionsKt.daysBetween(new Date(), ((StoredAchievementEvents) this.storedAchievementEvents.get()).previousShowedDate(i)) < ((long) 80)) {
            return true;
        }
        return false;
    }

    private final boolean isAchievementShowedBefore(int i) {
        return ((StoredAchievementEvents) this.storedAchievementEvents.get()).isAchievementHasStoredEvents(i);
    }

    private final Single<Integer> whatIsStreakDayNow() {
        Single<Integer> map = ((UserSummaryRepository) this.userSummaryRepository.get()).getUserSummary().map(AchievementAdManager$whatIsStreakDayNow$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "userSummaryRepository.ge…reakDay\n                }");
        return map;
    }

    /* access modifiers changed from: private */
    public final String formAdId() {
        Object obj = this.adsSettings.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "adsSettings.get()");
        if (((AdsSettings) obj).isTestModeForAds()) {
            return AchievementAdDefines.INSTANCE.getTEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID();
        }
        return AchievementAdDefines.INSTANCE.getPRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID();
    }

    private final void reportDisplayEvent() {
        UserSummaryObject userSummaryObject2 = this.userSummaryObject;
        if (userSummaryObject2 != null) {
            AdTargetingDTO adTargetingDTO2 = this.adTargetingDTO;
            if (adTargetingDTO2 != null) {
                String formAdId = formAdId();
                String uid = userSummaryObject2.getUid();
                Intrinsics.checkExpressionValueIsNotNull(uid, "uso.uid");
                AchievementAdDisplayedAnalyticsEventDTO achievementAdDisplayedAnalyticsEventDTO = new AchievementAdDisplayedAnalyticsEventDTO(null, null, formAdId, null, uid, this.clientId, adTargetingDTO2, 11, null);
                ((AchievementAdAnalyticsEvents) this.achievementAdAnalyticsEvents.get()).reportAchievementAdDisplayedEvent(achievementAdDisplayedAnalyticsEventDTO);
            }
        }
    }

    private final void reportDisplayedTimeEvent() {
        UserSummaryObject userSummaryObject2 = this.userSummaryObject;
        if (userSummaryObject2 != null) {
            AdTargetingDTO adTargetingDTO2 = this.adTargetingDTO;
            if (adTargetingDTO2 != null) {
                String formAdId = formAdId();
                String uid = userSummaryObject2.getUid();
                Intrinsics.checkExpressionValueIsNotNull(uid, "uso.uid");
                AchievementAdDisplayedTimeAnalyticsEventDTO achievementAdDisplayedTimeAnalyticsEventDTO = new AchievementAdDisplayedTimeAnalyticsEventDTO(null, null, formAdId, null, uid, this.clientId, ((AchievementInterstitialAd) this.achievementInterstitialAd.get()).getLastAdRequestedDelay(), adTargetingDTO2, 11, null);
                ((AchievementAdAnalyticsEvents) this.achievementAdAnalyticsEvents.get()).reportAchievementAdDisplayedTimeEvent(achievementAdDisplayedTimeAnalyticsEventDTO);
            }
        }
    }
}
