package com.myfitnesspal.feature.consents.ui.activity;

import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/consents/ui/activity/AdConsentsActivity$askForConfirmationRemindMeLater$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$askForConfirmationRemindMeLater$$inlined$let$lambda$1<TData> implements DialogPositiveListener<Object> {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$askForConfirmationRemindMeLater$$inlined$let$lambda$1(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
    }

    public final void onClick(Object obj) {
        ((AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get()).reportInterruptionSkipped();
        this.this$0.disableConsentInterruptionAndFinish();
    }
}