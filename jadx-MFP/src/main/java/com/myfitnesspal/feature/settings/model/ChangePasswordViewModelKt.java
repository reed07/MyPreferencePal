package com.myfitnesspal.feature.settings.model;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputEditText;
import android.view.View.OnFocusChangeListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"HOURS_TO_RETRY_AFTER", "", "PASSWORD_LENGTH", "PASSWORD_NOTICE", "", "bindFocusChange", "", "editText", "Landroid/support/design/widget/TextInputEditText;", "onFocusChangeListener", "Landroid/view/View$OnFocusChangeListener;", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: ChangePasswordViewModel.kt */
public final class ChangePasswordViewModelKt {
    private static final int HOURS_TO_RETRY_AFTER = 24;
    private static final int PASSWORD_LENGTH = 8;
    private static final String PASSWORD_NOTICE = "https://content.myfitnesspal.com/security-information/password-notice.html";

    @BindingAdapter({"onFocus"})
    public static final void bindFocusChange(@NotNull TextInputEditText textInputEditText, @NotNull OnFocusChangeListener onFocusChangeListener) {
        Intrinsics.checkParameterIsNotNull(textInputEditText, "editText");
        Intrinsics.checkParameterIsNotNull(onFocusChangeListener, "onFocusChangeListener");
        if (textInputEditText.getOnFocusChangeListener() == null) {
            textInputEditText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }
}
