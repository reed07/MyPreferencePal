package com.myfitnesspal.feature.timestamp.mixin;

import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "option", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
final class TimestampPickerMixin$validateDialogs$1 extends Lambda implements Function1<TimestampOption, Unit> {
    final /* synthetic */ TimestampPickerMixin this$0;

    TimestampPickerMixin$validateDialogs$1(TimestampPickerMixin timestampPickerMixin) {
        this.this$0 = timestampPickerMixin;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TimestampOption) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull TimestampOption timestampOption) {
        Intrinsics.checkParameterIsNotNull(timestampOption, WaterLoggingAnalyticsHelper.OPTION);
        this.this$0.setSelectedOption(timestampOption);
    }
}
