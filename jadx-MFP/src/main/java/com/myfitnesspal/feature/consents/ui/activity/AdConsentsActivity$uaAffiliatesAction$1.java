package com.myfitnesspal.feature.consents.ui.activity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.ui.activity.UAAndAffiliatesActivity.Companion;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$uaAffiliatesAction$1 implements OnClickListener {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$uaAffiliatesAction$1(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
    }

    public final void onClick(View view) {
        NavigationHelper navigationHelper = this.this$0.getNavigationHelper();
        Companion companion = UAAndAffiliatesActivity.Companion;
        AdConsentsActivity adConsentsActivity = this.this$0;
        Context context = adConsentsActivity;
        String str = URLs.UA_AFFILIATES;
        String string = adConsentsActivity.getString(R.string.under_armour);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.under_armour)");
        navigationHelper.withIntent(companion.newStartIntent(context, str, string)).startActivity();
    }
}
