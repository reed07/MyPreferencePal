package com.myfitnesspal.feature.achievementinterstitialad.constants;

import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u001b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0014\"\u0004\b#\u0010\u0016¨\u0006$"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/constants/AchievementAdDefines;", "", "()V", "ACHIEVEMENT_AD_FORMAT", "", "ACHIEVEMENT_AD_PROVIDER", "ACHIEVEMENT_DAYS", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getACHIEVEMENT_DAYS", "()Ljava/util/ArrayList;", "ACHIEVEMENT_DAY_14", "ACHIEVEMENT_DAY_28", "ACHIEVEMENT_DAY_3", "ACHIEVEMENT_DAY_7", "ACHIEVEMENT_SHOWED_ON_NEWS_FEED", "DAYS_BETWEEN_THE_SAME_ACHIEVEMENT_AD", "PRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID", "getPRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID", "()Ljava/lang/String;", "setPRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID", "(Ljava/lang/String;)V", "SHARED_PREFERENCES_ACHIEVEMENT_PREFIX", "TARGETING_ACHIEVEMENT", "TARGETING_AGE", "TARGETING_DEVICE_ID", "TARGETING_GENDER", "TARGETING_KRUX", "TARGETING_PREMIUM", "TARGETING_VALUE_NO", "TARGETING_VALUE_STREAK", "TARGETING_VALUE_YES", "TEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID", "getTEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID", "setTEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementAdDefines.kt */
public final class AchievementAdDefines {
    @NotNull
    public static final String ACHIEVEMENT_AD_FORMAT = "interstitial";
    @NotNull
    public static final String ACHIEVEMENT_AD_PROVIDER = "AdMob";
    @NotNull
    private static final ArrayList<Integer> ACHIEVEMENT_DAYS = CollectionsKt.arrayListOf(Integer.valueOf(3), Integer.valueOf(7), Integer.valueOf(14), Integer.valueOf(28));
    public static final int ACHIEVEMENT_DAY_14 = 14;
    public static final int ACHIEVEMENT_DAY_28 = 28;
    public static final int ACHIEVEMENT_DAY_3 = 3;
    public static final int ACHIEVEMENT_DAY_7 = 7;
    @NotNull
    public static final String ACHIEVEMENT_SHOWED_ON_NEWS_FEED = "news_feed";
    public static final int DAYS_BETWEEN_THE_SAME_ACHIEVEMENT_AD = 80;
    public static final AchievementAdDefines INSTANCE = new AchievementAdDefines();
    @NotNull
    private static String PRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID = null;
    @NotNull
    public static final String SHARED_PREFERENCES_ACHIEVEMENT_PREFIX = "achievement_day_";
    @NotNull
    public static final String TARGETING_ACHIEVEMENT = "achievement";
    @NotNull
    public static final String TARGETING_AGE = "oex";
    @NotNull
    public static final String TARGETING_DEVICE_ID = "did";
    @NotNull
    public static final String TARGETING_GENDER = "dkw";
    @NotNull
    public static final String TARGETING_KRUX = "kuid";
    @NotNull
    public static final String TARGETING_PREMIUM = "prem";
    @NotNull
    public static final String TARGETING_VALUE_NO = "n";
    @NotNull
    public static final String TARGETING_VALUE_STREAK = "streak";
    @NotNull
    public static final String TARGETING_VALUE_YES = "y";
    @NotNull
    private static String TEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID;

    static {
        MyFitnessPalApp instance = MyFitnessPalApp.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "MyFitnessPalApp.getInstance()");
        String string = instance.getResources().getString(R.string.test_streak_day_count_interstitial_ad_id);
        Intrinsics.checkExpressionValueIsNotNull(string, "MyFitnessPalApp.getInsta…count_interstitial_ad_id)");
        TEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID = string;
        MyFitnessPalApp instance2 = MyFitnessPalApp.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance2, "MyFitnessPalApp.getInstance()");
        String string2 = instance2.getResources().getString(R.string.production_streak_day_count_interstitial_ad_id);
        Intrinsics.checkExpressionValueIsNotNull(string2, "MyFitnessPalApp.getInsta…count_interstitial_ad_id)");
        PRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID = string2;
    }

    private AchievementAdDefines() {
    }

    @NotNull
    public final String getTEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID() {
        return TEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID;
    }

    public final void setTEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        TEST_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID = str;
    }

    @NotNull
    public final String getPRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID() {
        return PRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID;
    }

    public final void setPRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        PRODUCTION_STREAK_DAY_COUNT_INTERSTITIAL_AD_ID = str;
    }

    @NotNull
    public final ArrayList<Integer> getACHIEVEMENT_DAYS() {
        return ACHIEVEMENT_DAYS;
    }
}
