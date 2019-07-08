package com.myfitnesspal.feature.diary.event;

import com.myfitnesspal.shared.model.v1.DatabaseObject;

public class DiaryItemCheckedEvent {
    private boolean checked;
    private DatabaseObject checkedDiaryItem;

    public DiaryItemCheckedEvent(DatabaseObject databaseObject, boolean z) {
        this.checkedDiaryItem = databaseObject;
        this.checked = z;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public DatabaseObject getCheckedDiaryItem() {
        return this.checkedDiaryItem;
    }
}
