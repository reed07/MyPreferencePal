package com.myfitnesspal.feature.consents.ui.activity;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "consentId", "", "<anonymous parameter 1>", "Landroid/view/View;", "isChecked", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$interruptionCheckboxAction$1 extends Lambda implements Function3<String, View, Boolean, Unit> {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$interruptionCheckboxAction$1(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
        super(3);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((String) obj, (View) obj2, ((Boolean) obj3).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str, @NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "consentId");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        this.this$0.getViewModel().setConsentChecked(str, z);
    }
}
