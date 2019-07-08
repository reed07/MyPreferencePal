package com.myfitnesspal.feature.settings.ui.view;

import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.util.Gender;

public class GenderItem extends CheckableListItem {
    private Gender gender;

    public GenderItem(String str, Gender gender2) {
        super(str, false);
        this.gender = gender2;
    }

    public Gender getGender() {
        return this.gender;
    }
}
