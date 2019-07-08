package com.myfitnesspal.feature.consents.model;

import com.myfitnesspal.feature.consents.model.Resource.Success;
import io.reactivex.functions.Action;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
final class ConsentsViewModel$finishSignUpFlow$1 implements Action {
    final /* synthetic */ ConsentsViewModel this$0;

    ConsentsViewModel$finishSignUpFlow$1(ConsentsViewModel consentsViewModel) {
        this.this$0 = consentsViewModel;
    }

    public final void run() {
        this.this$0.consentsService.storeConsentData(this.this$0.consentsMatrixVersion, this.this$0.totalNumberOfConsents, this.this$0.iso);
        this.this$0.signUpStatus.setValue(new Success(Boolean.valueOf(true)));
    }
}
