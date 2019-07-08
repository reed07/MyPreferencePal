package com.myfitnesspal.feature.timestamp.mixin;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.TimePicker;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/TimePicker;", "kotlin.jvm.PlatformType", "hourOfDay", "", "minute", "onTimeSet"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
final class TimestampPickerMixin$showTimestampPicker$1 implements OnTimeSetListener {
    final /* synthetic */ Calendar $entryTime;
    final /* synthetic */ TimestampPickerMixin this$0;

    TimestampPickerMixin$showTimestampPicker$1(TimestampPickerMixin timestampPickerMixin, Calendar calendar) {
        this.this$0 = timestampPickerMixin;
        this.$entryTime = calendar;
    }

    public final void onTimeSet(TimePicker timePicker, int i, int i2) {
        this.$entryTime.clear();
        this.$entryTime.set(11, i);
        this.$entryTime.set(12, i2);
        TimestampPickerMixin timestampPickerMixin = this.this$0;
        Calendar calendar = this.$entryTime;
        Intrinsics.checkExpressionValueIsNotNull(calendar, "entryTime");
        timestampPickerMixin.setNewTimestamp(calendar.getTime());
    }
}
