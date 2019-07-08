package com.myfitnesspal.shared.ui.dialog;

public class DialogListTextItem extends DialogListItemBase {
    String text;

    public DialogListTextItem(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }
}
