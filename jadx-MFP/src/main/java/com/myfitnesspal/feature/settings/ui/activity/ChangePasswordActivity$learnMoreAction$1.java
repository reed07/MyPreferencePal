package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.ui.activity.LearnMoreActivity;
import com.myfitnesspal.feature.consents.ui.activity.LearnMoreActivity.Companion;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Strings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: ChangePasswordActivity.kt */
final class ChangePasswordActivity$learnMoreAction$1 implements OnClickListener {
    final /* synthetic */ ChangePasswordActivity this$0;

    ChangePasswordActivity$learnMoreAction$1(ChangePasswordActivity changePasswordActivity) {
        this.this$0 = changePasswordActivity;
    }

    public final void onClick(View view) {
        NavigationHelper navigationHelper = this.this$0.getNavigationHelper();
        Companion companion = LearnMoreActivity.Companion;
        ChangePasswordActivity changePasswordActivity = this.this$0;
        Context context = changePasswordActivity;
        String strings = Strings.toString(changePasswordActivity.getViewModel().getPasswordNoticeUri());
        Intrinsics.checkExpressionValueIsNotNull(strings, "Strings.toString(viewModel.getPasswordNoticeUri())");
        String string = this.this$0.getString(R.string.learn_more);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.learn_more)");
        navigationHelper.withIntent(companion.newStartIntent(context, strings, string)).startActivity();
    }
}
