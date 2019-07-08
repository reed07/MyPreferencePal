package com.myfitnesspal.feature.consents.ui.activity;

import android.arch.lifecycle.Observer;
import android.widget.ProgressBar;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.uacf.core.util.Ln;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$initDataObservers$1<T> implements Observer<List<? extends Pair<? extends UacfConsent, ? extends Boolean>>> {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$initDataObservers$1(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
    }

    public final void onChanged(@Nullable List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        if (list == null || !(!list.isEmpty())) {
            Ln.e("Consent List is empty", new Object[0]);
            this.this$0.finish();
            return;
        }
        this.this$0.updateButtonsVisibilityAndText();
        ProgressBar progressBar = (ProgressBar) this.this$0._$_findCachedViewById(R.id.listProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "listProgressBar");
        progressBar.setVisibility(8);
        AdConsentsActivity.access$getAdConsentsAdapter$p(this.this$0).setConsents(list);
        AdConsentsActivity.access$getAdConsentsAdapter$p(this.this$0).notifyDataSetChanged();
        ((AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get()).reportAdConsentsDisplayed(this.this$0.getViewModel().getMode(), this.this$0.getViewModel().getConsentsList());
    }
}
