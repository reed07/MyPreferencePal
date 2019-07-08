package com.myfitnesspal.feature.gdprhelp.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity.ExportMode;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountPremiumActivity;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelperImpl;
import com.myfitnesspal.feature.premium.service.PremiumService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: GDPRHelpActivity.kt */
final class GDPRHelpActivity$setupClickListeners$4 implements OnClickListener {
    final /* synthetic */ GDPRHelpActivity this$0;

    GDPRHelpActivity$setupClickListeners$4(GDPRHelpActivity gDPRHelpActivity) {
        this.this$0 = gDPRHelpActivity;
    }

    public final void onClick(View view) {
        Intent intent;
        String stringExtra = this.this$0.getIntent().getStringExtra("entry_point");
        DeleteAccountAnalyticsHelper deleteAccountAnalyticsHelper = (DeleteAccountAnalyticsHelper) this.this$0.getDeleteAccountAnalyticsHelper().get();
        String str = GDPRHelpAnalyticsHelperImpl.HELP_SEE;
        Intrinsics.checkExpressionValueIsNotNull(stringExtra, "entryPoint");
        deleteAccountAnalyticsHelper.reportAccountDeleteStarted(str, stringExtra);
        Object obj = this.this$0.getPremiumService().get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "premiumService.get()");
        if (((PremiumService) obj).isPremiumSubscribed()) {
            intent = DeleteAccountPremiumActivity.newStartIntent(this.this$0, ExportMode.Enabled, stringExtra);
        } else {
            intent = DeleteAccountActivity.newStartIntent(this.this$0, ExportMode.Enabled, stringExtra);
        }
        this.this$0.getNavigationHelper().withIntent(intent).startActivity();
    }
}
