package com.myfitnesspal.feature.consents.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.ButtonMode;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$initListeners$2 implements OnClickListener {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$initListeners$2(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.getViewModel().getAcceptButtonMode() == ButtonMode.I_ACCEPT) {
            ((AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get()).reportInterruptionSaveTapped();
            if (this.this$0.getViewModel().getConsentsList().size() == 1) {
                this.this$0.getViewModel().setAllConsentsAsChecked();
                this.this$0.getViewModel().updateAdConsents(true);
                this.this$0.disableConsentInterruptionAndFinish();
            } else if (this.this$0.getViewModel().getCheckedConsents().size() == this.this$0.getViewModel().getConsentsList().size()) {
                this.this$0.getViewModel().updateAdConsents(true);
                this.this$0.disableConsentInterruptionAndFinish();
            } else {
                this.this$0.askForConfirmationInterruption();
            }
        } else {
            this.this$0.getViewModel().setAllConsentsAsChecked();
        }
    }
}
