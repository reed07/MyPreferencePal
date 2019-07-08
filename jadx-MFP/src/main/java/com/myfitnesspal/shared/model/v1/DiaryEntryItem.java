package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ViewType;
import com.myfitnesspal.feature.diary.ui.item.DiaryAdapterItem;

public class DiaryEntryItem implements DiaryAdapterItem {
    private final DatabaseObject databaseObject;
    private final boolean isLastItemOfSection;
    private final int sectionType;

    public DiaryEntryItem(DatabaseObject databaseObject2, int i, boolean z) {
        this.databaseObject = databaseObject2;
        this.sectionType = i;
        this.isLastItemOfSection = z;
    }

    public ViewType getItemType() {
        return ViewType.Entry;
    }

    public DatabaseObject getDatabaseObject() {
        return this.databaseObject;
    }

    public int getSectionType() {
        return this.sectionType;
    }

    public boolean isLastItemOfSection() {
        return this.isLastItemOfSection;
    }
}
