package com.myfitnesspal.feature.settings.ui.activity;

import android.arch.lifecycle.Observer;
import android.support.design.widget.TextInputLayout;
import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "inputError", "Lkotlin/Pair;", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: ChangePasswordActivity.kt */
final class ChangePasswordActivity$initUi$2<T> implements Observer<Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ ChangePasswordActivity this$0;

    ChangePasswordActivity$initUi$2(ChangePasswordActivity changePasswordActivity) {
        this.this$0 = changePasswordActivity;
    }

    public final void onChanged(@Nullable Pair<Integer, Integer> pair) {
        if (pair != null) {
            int intValue = ((Number) pair.getFirst()).intValue();
            CharSequence charSequence = null;
            if (intValue == R.id.newPassword) {
                TextInputLayout textInputLayout = (TextInputLayout) this.this$0._$_findCachedViewById(R.id.newPasswordInputLayout);
                Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "newPasswordInputLayout");
                if (R.string.change_password_too_short == ((Number) pair.getSecond()).intValue()) {
                    charSequence = this.this$0.getString(((Number) pair.getSecond()).intValue());
                }
                textInputLayout.setError(charSequence);
            } else if (intValue == R.id.retypePassword) {
                TextInputLayout textInputLayout2 = (TextInputLayout) this.this$0._$_findCachedViewById(R.id.retypePasswordInputLayout);
                Intrinsics.checkExpressionValueIsNotNull(textInputLayout2, "retypePasswordInputLayout");
                if (R.string.change_password_passwords_no_match == ((Number) pair.getSecond()).intValue()) {
                    charSequence = this.this$0.getString(((Number) pair.getSecond()).intValue());
                }
                textInputLayout2.setError(charSequence);
            }
        }
    }
}
