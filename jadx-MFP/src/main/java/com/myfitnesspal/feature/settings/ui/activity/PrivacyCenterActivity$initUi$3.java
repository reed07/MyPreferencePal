package com.myfitnesspal.feature.settings.ui.activity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.feature.settings.ui.activity.SharingAndPrivacySettings.Companion;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: PrivacyCenterActivity.kt */
final class PrivacyCenterActivity$initUi$3 implements OnClickListener {
    final /* synthetic */ PrivacyCenterActivity this$0;

    PrivacyCenterActivity$initUi$3(PrivacyCenterActivity privacyCenterActivity) {
        this.this$0 = privacyCenterActivity;
    }

    public final void onClick(View view) {
        NavigationHelper navigationHelper = this.this$0.getNavigationHelper();
        Companion companion = SharingAndPrivacySettings.Companion;
        Activity activity = this.this$0.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, AbstractEvent.ACTIVITY);
        navigationHelper.withIntent(companion.newStartIntent(activity)).startActivity();
    }
}
