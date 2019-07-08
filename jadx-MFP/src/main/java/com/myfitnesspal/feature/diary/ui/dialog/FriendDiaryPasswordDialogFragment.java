package com.myfitnesspal.feature.diary.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.PasswordCanceledEvent;
import com.myfitnesspal.feature.diary.event.PasswordEnteredEvent;
import com.myfitnesspal.shared.ui.dialog.EditTextBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Bus;
import dagger.Lazy;
import javax.inject.Inject;

public class FriendDiaryPasswordDialogFragment extends EditTextBaseDialogFragment {
    @Inject
    Lazy<Bus> bus;

    /* access modifiers changed from: protected */
    public String getInitialText() {
        return null;
    }

    public static FriendDiaryPasswordDialogFragment newInstance() {
        return new FriendDiaryPasswordDialogFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getString(R.string.password_required);
    }

    /* access modifiers changed from: protected */
    public void setBuilderProperties(MfpAlertDialogBuilder mfpAlertDialogBuilder) {
        mfpAlertDialogBuilder.setCancelable(false).setCanceledOnTouchOutside(false);
    }

    /* access modifiers changed from: protected */
    public void setEditTextProperties() {
        this.editText.setHint(R.string.enter_password);
        this.editText.setInputType(129);
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(String str) {
        postEventAndHideSoftInput(new PasswordEnteredEvent(str));
    }

    /* access modifiers changed from: protected */
    public void onCancelClicked() {
        postEventAndHideSoftInput(new PasswordCanceledEvent());
    }

    private void postEventAndHideSoftInput(Object obj) {
        ((Bus) this.bus.get()).post(obj);
        hideSoftInput();
    }
}
