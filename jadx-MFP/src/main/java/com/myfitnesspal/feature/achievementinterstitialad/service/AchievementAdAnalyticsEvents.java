package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedAnalyticsEventDTO;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AchievementAdDisplayedTimeAnalyticsEventDTO;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.CollectionUtils;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/service/AchievementAdAnalyticsEvents;", "", "analyticsService", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;)V", "reportAchievementAdClickedEvent", "", "isClicked", "", "reportAchievementAdDisplayedEvent", "eventDTO", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AchievementAdDisplayedAnalyticsEventDTO;", "reportAchievementAdDisplayedTimeEvent", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AchievementAdDisplayedTimeAnalyticsEventDTO;", "reportAchievementAdNotLoaded", "errorMessage", "", "reportAchievementAdRequestedEvent", "screen", "reportAchievementAdViewedEvent", "streakCount", "", "reportAchievementReachedEvent", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementAdAnalyticsEvents.kt */
public final class AchievementAdAnalyticsEvents {
    private AnalyticsService analyticsService;

    public AchievementAdAnalyticsEvents(@NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.analyticsService = analyticsService2;
    }

    public final void reportAchievementReachedEvent(int i) {
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_STREAK_REACHED, CollectionUtils.nameValuePairsToMap(Attributes.ACHIEVEMENT_STREAK_VALUE, String.valueOf(i)));
    }

    public final void reportAchievementAdViewedEvent(int i) {
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_STREAK_CELEBRATION_VIEWED, CollectionUtils.nameValuePairsToMap(Attributes.ACHIEVEMENT_STREAK_VALUE, String.valueOf(i)));
    }

    public final void reportAchievementAdClickedEvent(boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = "click";
        objArr[1] = z ? "yes" : "no";
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_STREAK_CTA_CLICKED, CollectionUtils.nameValuePairsToMap(objArr));
    }

    public final void reportAchievementAdNotLoaded(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, AbstractEvent.ERROR_MESSAGE);
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_STREAK_AD_NOT_LOADED, CollectionUtils.nameValuePairsToMap("error_message", str));
    }

    public final void reportAchievementAdRequestedEvent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "screen");
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_AD_REQUESTED, CollectionUtils.nameValuePairsToMap("screen", str));
    }

    public final void reportAchievementAdDisplayedEvent(@NotNull AchievementAdDisplayedAnalyticsEventDTO achievementAdDisplayedAnalyticsEventDTO) {
        Intrinsics.checkParameterIsNotNull(achievementAdDisplayedAnalyticsEventDTO, "eventDTO");
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_AD_DISPLAYED, CollectionUtils.nameValuePairsToMap(Attributes.ACHIEVEMENT_AD_FORMAT, achievementAdDisplayedAnalyticsEventDTO.getAdFormat(), Attributes.ACHIEVEMENT_AD_NETWORK, achievementAdDisplayedAnalyticsEventDTO.getAdNetwork(), "ad_unit_id", achievementAdDisplayedAnalyticsEventDTO.getAdUnitId(), "screen", achievementAdDisplayedAnalyticsEventDTO.getScreen(), "user_id", achievementAdDisplayedAnalyticsEventDTO.getUserId(), Attributes.ACHIEVEMENT_CLIENT_ID, achievementAdDisplayedAnalyticsEventDTO.getClientId(), Attributes.ACHIEVEMENT_KEYWORDS, CollectionUtils.nameValuePairsToMap(AchievementAdDefines.TARGETING_ACHIEVEMENT, achievementAdDisplayedAnalyticsEventDTO.getKeywords().getAchievementDay(), AchievementAdDefines.TARGETING_PREMIUM, achievementAdDisplayedAnalyticsEventDTO.getKeywords().getPremiumUser(), "dkw", achievementAdDisplayedAnalyticsEventDTO.getKeywords().getGender(), "oex", achievementAdDisplayedAnalyticsEventDTO.getKeywords().getAge(), "kuid", achievementAdDisplayedAnalyticsEventDTO.getKeywords().getKrux(), "did", achievementAdDisplayedAnalyticsEventDTO.getKeywords().getDeviceId())));
    }

    public final void reportAchievementAdDisplayedTimeEvent(@NotNull AchievementAdDisplayedTimeAnalyticsEventDTO achievementAdDisplayedTimeAnalyticsEventDTO) {
        Intrinsics.checkParameterIsNotNull(achievementAdDisplayedTimeAnalyticsEventDTO, "eventDTO");
        Map nameValuePairsToMap = CollectionUtils.nameValuePairsToMap(AchievementAdDefines.TARGETING_ACHIEVEMENT, achievementAdDisplayedTimeAnalyticsEventDTO.getKeywords().getAchievementDay(), AchievementAdDefines.TARGETING_PREMIUM, achievementAdDisplayedTimeAnalyticsEventDTO.getKeywords().getPremiumUser(), "dkw", achievementAdDisplayedTimeAnalyticsEventDTO.getKeywords().getGender(), "oex", achievementAdDisplayedTimeAnalyticsEventDTO.getKeywords().getAge(), "kuid", achievementAdDisplayedTimeAnalyticsEventDTO.getKeywords().getKrux(), "did", achievementAdDisplayedTimeAnalyticsEventDTO.getKeywords().getDeviceId());
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf(((double) achievementAdDisplayedTimeAnalyticsEventDTO.getDisplayTime()) / ((double) 1000))};
        String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        this.analyticsService.reportEvent(Events.ACHIEVEMENT_AD_DISPLAYED_TIME, CollectionUtils.nameValuePairsToMap(Attributes.ACHIEVEMENT_AD_FORMAT, achievementAdDisplayedTimeAnalyticsEventDTO.getAdFormat(), Attributes.ACHIEVEMENT_AD_NETWORK, achievementAdDisplayedTimeAnalyticsEventDTO.getAdNetwork(), "ad_unit_id", achievementAdDisplayedTimeAnalyticsEventDTO.getAdUnitId(), "screen", achievementAdDisplayedTimeAnalyticsEventDTO.getScreen(), "user_id", achievementAdDisplayedTimeAnalyticsEventDTO.getUserId(), Attributes.ACHIEVEMENT_CLIENT_ID, achievementAdDisplayedTimeAnalyticsEventDTO.getClientId(), "display_time", format, Attributes.ACHIEVEMENT_KEYWORDS, nameValuePairsToMap));
    }
}
