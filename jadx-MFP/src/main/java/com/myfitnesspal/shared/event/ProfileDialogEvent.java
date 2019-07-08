package com.myfitnesspal.shared.event;

import android.support.v4.app.DialogFragment;

public class ProfileDialogEvent {
    DialogFragment dialogFragment;

    public ProfileDialogEvent(DialogFragment dialogFragment2) {
        this.dialogFragment = dialogFragment2;
    }

    public DialogFragment getDialogFragment() {
        return this.dialogFragment;
    }

    public void setDialogFragment(DialogFragment dialogFragment2) {
        this.dialogFragment = dialogFragment2;
    }
}
