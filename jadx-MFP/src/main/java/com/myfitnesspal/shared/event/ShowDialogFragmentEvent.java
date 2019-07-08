package com.myfitnesspal.shared.event;

import android.support.v4.app.DialogFragment;
import com.uacf.core.util.Strings;

public class ShowDialogFragmentEvent {
    private final DialogFragment dialogFragment;
    private final String tag;

    public ShowDialogFragmentEvent(DialogFragment dialogFragment2, String str) {
        if (dialogFragment2 == null || Strings.isEmpty(str)) {
            throw new IllegalArgumentException("dialogFragment and tag must both be specified");
        }
        this.dialogFragment = dialogFragment2;
        this.tag = str;
    }

    public DialogFragment getDialogFragment() {
        return this.dialogFragment;
    }

    public String getTag() {
        return this.tag;
    }
}
