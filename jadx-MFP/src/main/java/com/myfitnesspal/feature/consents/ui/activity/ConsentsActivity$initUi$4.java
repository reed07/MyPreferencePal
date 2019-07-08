package com.myfitnesspal.feature.consents.ui.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
final class ConsentsActivity$initUi$4 implements OnCheckedChangeListener {
    final /* synthetic */ ConsentsActivity this$0;

    ConsentsActivity$initUi$4(ConsentsActivity consentsActivity) {
        this.this$0 = consentsActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.this$0.getViewModel().markAllConsentsAsChecked(z);
    }
}
