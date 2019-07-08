package com.myfitnesspal.shared.ui.dialog;

public class DialogListTextResItem extends DialogListItemBase {
    private final int textResId;

    public DialogListTextResItem(int i) {
        this.textResId = i;
    }

    public int getTextResId() {
        return this.textResId;
    }
}
