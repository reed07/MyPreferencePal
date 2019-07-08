package com.myfitnesspal.feature.foodfeedback.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/foodfeedback/ui/dialog/FeedbackOptionsDialog$initListeners$2$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FeedbackOptionsDialog.kt */
final class FeedbackOptionsDialog$initListeners$$inlined$apply$lambda$1 implements OnClickListener {
    final /* synthetic */ Dialog $this_apply;
    final /* synthetic */ FeedbackOptionsDialog this$0;

    FeedbackOptionsDialog$initListeners$$inlined$apply$lambda$1(Dialog dialog, FeedbackOptionsDialog feedbackOptionsDialog) {
        this.$this_apply = dialog;
        this.this$0 = feedbackOptionsDialog;
    }

    public final void onClick(View view) {
        this.this$0.saveSelectedOptions();
        this.$this_apply.dismiss();
    }
}
