package com.myfitnesspal.feature.diary.ui.dialog;

import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampOptionsDialog.kt */
final class TimestampOptionsDialog$onOptionSelect$1 extends Lambda implements Function1<TimestampOption, Unit> {
    public static final TimestampOptionsDialog$onOptionSelect$1 INSTANCE = new TimestampOptionsDialog$onOptionSelect$1();

    TimestampOptionsDialog$onOptionSelect$1() {
        super(1);
    }

    public final void invoke(@NotNull TimestampOption timestampOption) {
        Intrinsics.checkParameterIsNotNull(timestampOption, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TimestampOption) obj);
        return Unit.INSTANCE;
    }
}
