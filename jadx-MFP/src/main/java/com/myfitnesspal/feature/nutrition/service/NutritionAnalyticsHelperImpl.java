package com.myfitnesspal.feature.nutrition.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/nutrition/service/NutritionAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/nutrition/service/NutritionAnalyticsHelper;", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "reportFoodAnalysisViewed", "", "listType", "", "reportViewMore", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NutritionAnalyticsHelperImpl.kt */
public final class NutritionAnalyticsHelperImpl implements NutritionAnalyticsHelper {
    public static final Companion Companion = new Companion(null);
    private static final String FOOD_ANALYSIS_VIEWED = "food_analysis_viewed";
    private static final String FOOD_ANALYSIS_VIEW_MORE_TAPPED = "food_analysis_view_more_tapped";
    private static final String NUTRITION_LIST_TYPE = "list_type";
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/nutrition/service/NutritionAnalyticsHelperImpl$Companion;", "", "()V", "FOOD_ANALYSIS_VIEWED", "", "FOOD_ANALYSIS_VIEW_MORE_TAPPED", "NUTRITION_LIST_TYPE", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: NutritionAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NutritionAnalyticsHelperImpl(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public void reportViewMore(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "listType");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_ANALYSIS_VIEW_MORE_TAPPED, MapsKt.mapOf(new Pair("list_type", str)));
    }

    public void reportFoodAnalysisViewed(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "listType");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_ANALYSIS_VIEWED, MapsKt.mapOf(new Pair("list_type", str)));
    }
}
