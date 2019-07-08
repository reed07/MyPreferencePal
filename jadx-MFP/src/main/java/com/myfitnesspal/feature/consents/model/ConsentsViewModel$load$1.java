package com.myfitnesspal.feature.consents.model;

import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import io.reactivex.functions.Consumer;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
final class ConsentsViewModel$load$1<T> implements Consumer<UacfConsentStatus> {
    final /* synthetic */ ConsentsViewModel this$0;

    ConsentsViewModel$load$1(ConsentsViewModel consentsViewModel) {
        this.this$0 = consentsViewModel;
    }

    public final void accept(UacfConsentStatus uacfConsentStatus) {
        if (uacfConsentStatus == null) {
            this.this$0.consents.setValue(new Error(new Throwable("Consents failed to load")));
            return;
        }
        this.this$0.setConsentsMap(uacfConsentStatus);
        this.this$0.consents.setValue(new Success(this.this$0.consentsList));
        if (!this.this$0.consentsList.isEmpty()) {
            this.this$0.consentsAnalyticsHelper.reportConsentsSeeToAnalytics(this.this$0.consentsList.size(), this.this$0.getMode());
        }
    }
}
