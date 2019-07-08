package com.myfitnesspal.feature.consents.ui.activity;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012 \u0010\u0002\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "resource", "Lcom/myfitnesspal/feature/consents/model/Resource;", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
final class ConsentsActivity$initUi$1<T> implements Observer<Resource<? extends List<? extends Pair<? extends UacfConsent, ? extends Boolean>>>> {
    final /* synthetic */ ConsentsActivity this$0;

    ConsentsActivity$initUi$1(ConsentsActivity consentsActivity) {
        this.this$0 = consentsActivity;
    }

    public final void onChanged(@Nullable Resource<? extends List<? extends Pair<? extends UacfConsent, Boolean>>> resource) {
        if (resource instanceof Success) {
            this.this$0.setConsents((List) ((Success) resource).getData());
        } else if (resource instanceof Loading) {
            this.this$0.showLoading();
        } else if (resource instanceof Error) {
            ConsentsActivity consentsActivity = this.this$0;
            consentsActivity.showLoadError(consentsActivity.getViewModel().isOnBoarding() ? R.string.gdpr_offline_error : R.string.unknown_error);
        }
    }
}
