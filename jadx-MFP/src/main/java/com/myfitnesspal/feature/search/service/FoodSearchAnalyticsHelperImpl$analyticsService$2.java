package com.myfitnesspal.feature.search.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchAnalyticsHelperImpl.kt */
final class FoodSearchAnalyticsHelperImpl$analyticsService$2 extends Lambda implements Function0<AnalyticsService> {
    final /* synthetic */ FoodSearchAnalyticsHelperImpl this$0;

    FoodSearchAnalyticsHelperImpl$analyticsService$2(FoodSearchAnalyticsHelperImpl foodSearchAnalyticsHelperImpl) {
        this.this$0 = foodSearchAnalyticsHelperImpl;
        super(0);
    }

    public final AnalyticsService invoke() {
        return (AnalyticsService) this.this$0.analyticsServiceLazy.get();
    }
}
