package com.myfitnesspal.feature.diary.ui.item;

import com.myfitnesspal.shared.ui.dialog.DialogListItemBase;

public class MoreItem extends DialogListItemBase {
    private int description;
    private int id;

    public MoreItem(int i, int i2) {
        this.description = i;
        this.id = i2;
    }

    public int getTextResId() {
        return this.description;
    }

    public int getDescription() {
        return this.description;
    }

    public void setDescription(int i) {
        this.description = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }
}
