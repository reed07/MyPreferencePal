package com.myfitnesspal.feature.settings.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.ui.activity.TermsOfUseActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: PrivacyCenterActivity.kt */
final class PrivacyCenterActivity$initUi$1 implements OnClickListener {
    final /* synthetic */ PrivacyCenterActivity this$0;

    PrivacyCenterActivity$initUi$1(PrivacyCenterActivity privacyCenterActivity) {
        this.this$0 = privacyCenterActivity;
    }

    public final void onClick(View view) {
        ((UpdatedTermsAnalyticsHelper) this.this$0.getUpdatedTermsAnalyticsHelper().get()).reportTOSSee(ConsentsAnalyticsHelperImpl.PRIVACY_CENTER_SEE);
        this.this$0.getNavigationHelper().withIntent(TermsOfUseActivity.newStartIntent(this.this$0.getActivity())).startActivity();
    }
}
