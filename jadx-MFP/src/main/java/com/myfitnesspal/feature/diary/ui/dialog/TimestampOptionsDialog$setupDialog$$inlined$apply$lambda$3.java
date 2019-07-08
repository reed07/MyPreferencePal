package com.myfitnesspal.feature.diary.ui.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/diary/ui/dialog/TimestampOptionsDialog$setupDialog$1$3"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampOptionsDialog.kt */
final class TimestampOptionsDialog$setupDialog$$inlined$apply$lambda$3 implements OnClickListener {
    final /* synthetic */ TimestampOptionsDialog this$0;

    TimestampOptionsDialog$setupDialog$$inlined$apply$lambda$3(TimestampOptionsDialog timestampOptionsDialog) {
        this.this$0 = timestampOptionsDialog;
    }

    public final void onClick(View view) {
        this.this$0.selectTimeOption(TimestampOption.NO_TIME);
    }
}
