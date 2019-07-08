package com.myfitnesspal.feature.diary.ui.item;

import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ViewType;

public class DiaryRowActionItem extends SectionHeaderItem {
    public DiaryRowActionItem(String str, String str2) {
        super(str, str2, 0, 0, false, null);
    }

    public ViewType getItemType() {
        return ViewType.SectionFooter;
    }
}
