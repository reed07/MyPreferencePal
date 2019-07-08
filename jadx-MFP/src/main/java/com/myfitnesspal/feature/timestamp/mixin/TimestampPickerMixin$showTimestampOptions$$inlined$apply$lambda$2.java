package com.myfitnesspal.feature.timestamp.mixin;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "com/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$showTimestampOptions$1$2"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
final class TimestampPickerMixin$showTimestampOptions$$inlined$apply$lambda$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ TimestampPickerMixin this$0;

    TimestampPickerMixin$showTimestampOptions$$inlined$apply$lambda$2(TimestampPickerMixin timestampPickerMixin) {
        this.this$0 = timestampPickerMixin;
        super(0);
    }

    public final void invoke() {
        this.this$0.isShowingOptionPicker = false;
    }
}
