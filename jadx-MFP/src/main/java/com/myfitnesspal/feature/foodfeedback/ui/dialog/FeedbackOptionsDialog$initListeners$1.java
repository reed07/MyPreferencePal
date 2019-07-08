package com.myfitnesspal.feature.foodfeedback.ui.dialog;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: FeedbackOptionsDialog.kt */
final class FeedbackOptionsDialog$initListeners$1 implements OnCheckedChangeListener {
    final /* synthetic */ FeedbackOptionsDialog this$0;

    FeedbackOptionsDialog$initListeners$1(FeedbackOptionsDialog feedbackOptionsDialog) {
        this.this$0 = feedbackOptionsDialog;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        HashMap access$getCheckBoxSelectionMap$cp = FeedbackOptionsDialog.checkBoxSelectionMap;
        Intrinsics.checkExpressionValueIsNotNull(compoundButton, "buttonView");
        access$getCheckBoxSelectionMap$cp.put(Integer.valueOf(compoundButton.getId()), Boolean.valueOf(z));
        this.this$0.toggleSaveCheckMarkState();
    }
}
