package com.myfitnesspal.shared.event;

import android.content.DialogInterface;

public class DialogDismissedEvent extends MfpEventBase {
    private final DialogInterface dialog;

    public DialogDismissedEvent(DialogInterface dialogInterface) {
        this.dialog = dialogInterface;
    }

    public DialogInterface getDialog() {
        return this.dialog;
    }
}
