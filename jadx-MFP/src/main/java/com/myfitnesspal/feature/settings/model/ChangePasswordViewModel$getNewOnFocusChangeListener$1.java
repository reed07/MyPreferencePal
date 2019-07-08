package com.myfitnesspal.feature.settings.model;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.TuplesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "hasFocus", "", "onFocusChange"}, k = 3, mv = {1, 1, 13})
/* compiled from: ChangePasswordViewModel.kt */
final class ChangePasswordViewModel$getNewOnFocusChangeListener$1 implements OnFocusChangeListener {
    final /* synthetic */ ChangePasswordViewModel this$0;

    ChangePasswordViewModel$getNewOnFocusChangeListener$1(ChangePasswordViewModel changePasswordViewModel) {
        this.this$0 = changePasswordViewModel;
    }

    public final void onFocusChange(View view, boolean z) {
        if (view != null && !z && !this.this$0.isNewPasswordValid()) {
            this.this$0.getInputError().setValue(TuplesKt.to(Integer.valueOf(R.id.newPassword), Integer.valueOf(R.string.change_password_too_short)));
        }
    }
}
