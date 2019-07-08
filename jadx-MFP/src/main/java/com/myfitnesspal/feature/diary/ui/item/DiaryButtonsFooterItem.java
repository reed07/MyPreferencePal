package com.myfitnesspal.feature.diary.ui.item;

import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ViewType;

public class DiaryButtonsFooterItem implements DiaryAdapterItem {
    public ViewType getItemType() {
        return ViewType.ButtonsFooter;
    }
}
