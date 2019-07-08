package com.myfitnesspal.feature.addentry.util;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.uacf.core.util.Strings;
import java.util.Comparator;

public class FoodComparator implements Comparator<DiaryEntryCellModel> {
    private SortOrder sortOrder;

    public FoodComparator(SortOrder sortOrder2) {
        this.sortOrder = sortOrder2;
    }

    public int compare(DiaryEntryCellModel diaryEntryCellModel, DiaryEntryCellModel diaryEntryCellModel2) {
        String lowerCase = Strings.toString(diaryEntryCellModel.summaryLine1()).toLowerCase();
        String lowerCase2 = Strings.toString(diaryEntryCellModel2.summaryLine1()).toLowerCase();
        if (Strings.equals(lowerCase, lowerCase2)) {
            return 0;
        }
        switch (this.sortOrder) {
            case ALPHABETICAL_ASCENDING:
                return lowerCase.compareToIgnoreCase(lowerCase2);
            case ALPHABETICAL_DESCENDING:
                return lowerCase2.compareToIgnoreCase(lowerCase);
            default:
                return 0;
        }
    }
}
