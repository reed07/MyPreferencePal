package com.myfitnesspal.feature.gdprhelp.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelper;
import com.myfitnesspal.feature.registration.ui.activity.LogoutActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: GDPRHelpActivity.kt */
final class GDPRHelpActivity$onOptionsItemSelected$1 implements OnClickListener {
    final /* synthetic */ GDPRHelpActivity this$0;

    GDPRHelpActivity$onOptionsItemSelected$1(GDPRHelpActivity gDPRHelpActivity) {
        this.this$0 = gDPRHelpActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        ((GDPRHelpAnalyticsHelper) this.this$0.getGdprHelpAnalyticsHelper().get()).reportLogout();
        this.this$0.getNavigationHelper().withIntent(LogoutActivity.newStartIntent(this.this$0)).startActivity();
    }
}
