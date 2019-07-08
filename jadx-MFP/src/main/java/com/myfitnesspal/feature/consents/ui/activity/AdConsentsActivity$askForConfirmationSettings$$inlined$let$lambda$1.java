package com.myfitnesspal.feature.consents.ui.activity;

import android.view.View;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/consents/ui/activity/AdConsentsActivity$askForConfirmationSettings$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$askForConfirmationSettings$$inlined$let$lambda$1<TData> implements DialogPositiveListener<Object> {
    final /* synthetic */ String $consentId$inlined;
    final /* synthetic */ View $itemView$inlined;
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$askForConfirmationSettings$$inlined$let$lambda$1(AdConsentsActivity adConsentsActivity, String str, View view) {
        this.this$0 = adConsentsActivity;
        this.$consentId$inlined = str;
        this.$itemView$inlined = view;
    }

    public final void onClick(Object obj) {
        AdConsentsViewModel viewModel = this.this$0.getViewModel();
        viewModel.setConsentChecked(this.$consentId$inlined, false);
        this.this$0.showProgressDialog();
        AdConsentsViewModel.updateAdConsents$default(viewModel, false, 1, null);
    }
}
