package com.myfitnesspal.feature.consents.model;

import com.myfitnesspal.feature.consents.model.Resource.Success;
import io.reactivex.functions.Action;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.BooleanRef;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsViewModel.kt */
final class AdConsentsViewModel$updateAdConsents$1 implements Action {
    final /* synthetic */ BooleanRef $isPersonalizedAdConsentAccepted;
    final /* synthetic */ AdConsentsViewModel this$0;

    AdConsentsViewModel$updateAdConsents$1(AdConsentsViewModel adConsentsViewModel, BooleanRef booleanRef) {
        this.this$0 = adConsentsViewModel;
        this.$isPersonalizedAdConsentAccepted = booleanRef;
    }

    public final void run() {
        this.this$0.getUpdateAdConsentsStatus().setValue(new Success(Boolean.valueOf(true)));
        this.this$0.localSettingsService.setIsPersonalizedAdConsentAccepted(this.$isPersonalizedAdConsentAccepted.element);
    }
}
