package com.myfitnesspal.shared.ui.dialog;

public class DialogListTextResImageItem extends DialogListItemBase {
    private final int imageResId;
    private final int itemId;
    private final int textResId;

    public DialogListTextResImageItem(int i, int i2, int i3) {
        this.textResId = i;
        this.imageResId = i2;
        this.itemId = i3;
    }

    public DialogListTextResImageItem(int i, int i2) {
        this(i, i2, -1);
    }

    public int getTextResId() {
        return this.textResId;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public int getItemId() {
        return this.itemId;
    }
}
