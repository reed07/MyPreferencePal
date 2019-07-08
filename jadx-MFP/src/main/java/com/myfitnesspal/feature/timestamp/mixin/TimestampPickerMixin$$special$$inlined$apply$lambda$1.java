package com.myfitnesspal.feature.timestamp.mixin;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
final class TimestampPickerMixin$$special$$inlined$apply$lambda$1 implements OnClickListener {
    final /* synthetic */ TimestampPickerMixin this$0;

    TimestampPickerMixin$$special$$inlined$apply$lambda$1(TimestampPickerMixin timestampPickerMixin) {
        this.this$0 = timestampPickerMixin;
    }

    public final void onClick(View view) {
        TimeValue timeValue;
        FoodScreenType screenType = this.this$0.getScreenType();
        if (screenType != null) {
            TimestampAnalyticsHelper timestampAnalyticsHelper = (TimestampAnalyticsHelper) this.this$0.getAnalyticsHelper().get();
            if (this.this$0.isEditingEntry) {
                timeValue = TimeValue.LOGGED_TIME;
            } else {
                timeValue = TimeValue.Companion.fromTimestampOption(this.this$0.getSelectedOption());
            }
            timestampAnalyticsHelper.reportTimeFieldTapped(screenType, timeValue);
        }
        this.this$0.activity.getImmHelper().hideSoftInput();
        if (this.this$0.isFeatureSubscribed) {
            this.this$0.showTimestampOptions();
        } else {
            this.this$0.showPremiumUpsell(TimestampAnalyticsHelper.FEATURE_TIMESTAMP_ADD_FOOD);
        }
    }
}
