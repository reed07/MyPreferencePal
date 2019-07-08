package com.myfitnesspal.shared.ui.dialog;

public class DialogListTextItemWithKey extends DialogListItemBase {
    final String key;
    final String text;

    public DialogListTextItemWithKey(String str, String str2) {
        this.key = str;
        this.text = str2;
    }

    public String getKey() {
        return this.key;
    }

    public String getText() {
        return this.text;
    }
}
