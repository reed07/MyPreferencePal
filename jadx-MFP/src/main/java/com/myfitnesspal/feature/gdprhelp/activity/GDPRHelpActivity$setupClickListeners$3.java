package com.myfitnesspal.feature.gdprhelp.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.registration.ui.activity.TermsOfUseActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: GDPRHelpActivity.kt */
final class GDPRHelpActivity$setupClickListeners$3 implements OnClickListener {
    final /* synthetic */ GDPRHelpActivity this$0;

    GDPRHelpActivity$setupClickListeners$3(GDPRHelpActivity gDPRHelpActivity) {
        this.this$0 = gDPRHelpActivity;
    }

    public final void onClick(View view) {
        this.this$0.getNavigationHelper().withIntent(TermsOfUseActivity.newStartIntent(this.this$0)).startActivity();
    }
}
