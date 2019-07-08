package com.myfitnesspal.feature.consents.ui.activity;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "consentStatusUpdated", "Lcom/myfitnesspal/feature/consents/model/Resource;", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$initDataObservers$2<T> implements Observer<Resource<? extends Boolean>> {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$initDataObservers$2(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
    }

    public final void onChanged(@Nullable Resource<Boolean> resource) {
        if (resource == null) {
            Ln.e("Updating consent status failed", new Object[0]);
        } else if (resource instanceof Success) {
            this.this$0.hideProgressDialog();
            Pair updatedConsentInSettings = this.this$0.getViewModel().getUpdatedConsentInSettings();
            if (((Boolean) updatedConsentInSettings.getSecond()).booleanValue()) {
                ((AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get()).reportConsentTappedOn((String) updatedConsentInSettings.getFirst());
            } else {
                ((AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get()).reportConsentOffSaved((String) updatedConsentInSettings.getFirst());
            }
        } else if (resource instanceof Error) {
            this.this$0.revertConsentCheckStatus();
            this.this$0.hideProgressDialog();
            this.this$0.showErrorAlert();
            ((AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get()).reportConsentSettingsError();
            StringBuilder sb = new StringBuilder();
            sb.append("Updating Consent status failed with error: ");
            sb.append(((Error) resource).getThrowable().getLocalizedMessage());
            Ln.e(sb.toString(), new Object[0]);
        } else if (resource instanceof Loading) {
            Ln.d("Updating consent status - Loading", new Object[0]);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
