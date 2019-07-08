package com.myfitnesspal.feature.timestamp.mixin;

import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FeatureHighlightEvent;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView.Listener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$highlightFeature$1$1", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;", "onBackPressed", "", "view", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView;", "onNegativeButtonClick", "onPositiveButtonClick", "onViewDismissed", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
public final class TimestampPickerMixin$highlightFeature$$inlined$let$lambda$1 extends Listener {
    final /* synthetic */ TimestampRowView $row;
    final /* synthetic */ TimestampPickerMixin this$0;

    TimestampPickerMixin$highlightFeature$$inlined$let$lambda$1(TimestampRowView timestampRowView, TimestampPickerMixin timestampPickerMixin) {
        this.$row = timestampRowView;
        this.this$0 = timestampPickerMixin;
    }

    public void onViewDismissed() {
        this.this$0.getLocalSettingsService().setUserSawTimestampFeature(true);
    }

    public void onPositiveButtonClick(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        featureHighlightView.dismiss();
        if (this.this$0.isFeatureSubscribed) {
            ((TimestampAnalyticsHelper) this.this$0.getAnalyticsHelper().get()).reportFeatureHighlightEvent(FeatureHighlightEvent.TRACK_TIME_CLICKED);
            this.this$0.getLocalSettingsService().setShouldShowFoodTimestamps(true);
            return;
        }
        this.this$0.showPremiumUpsell("food_timestamp_tooltip");
        this.$row.showLock();
    }

    public void onNegativeButtonClick(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        featureHighlightView.dismiss();
        this.this$0.onTimestampFeatureDismissed(FeatureHighlightEvent.NO_THANKS_CLICKED);
    }

    public void onBackPressed(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        featureHighlightView.dismiss();
        this.this$0.onTimestampFeatureDismissed(FeatureHighlightEvent.DISMISSED);
    }
}
