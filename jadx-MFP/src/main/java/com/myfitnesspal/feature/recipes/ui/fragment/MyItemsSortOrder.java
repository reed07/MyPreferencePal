package com.myfitnesspal.feature.recipes.ui.fragment;

import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.uacf.core.util.Strings;
import javax.annotation.Nullable;

public enum MyItemsSortOrder {
    Default(0, SortOrder.DATE_DESCENDING, R.string.recipes_foods_meals_sort_default),
    Ascending(1, SortOrder.ALPHABETICAL_ASCENDING, R.string.recipes_foods_meals_ascending),
    Descending(2, SortOrder.ALPHABETICAL_DESCENDING, R.string.recipes_foods_meals_descending);
    
    public final SortOrder queryConstant;
    public final int titleId;
    public final int typeId;

    private MyItemsSortOrder(int i, SortOrder sortOrder, int i2) {
        this.typeId = i;
        this.queryConstant = sortOrder;
        this.titleId = i2;
    }

    static MyItemsSortOrder fromTypeId(int i) {
        MyItemsSortOrder[] values;
        for (MyItemsSortOrder myItemsSortOrder : values()) {
            if (i == myItemsSortOrder.typeId) {
                return myItemsSortOrder;
            }
        }
        return Default;
    }

    static MyItemsSortOrder fromString(@Nullable String str) {
        MyItemsSortOrder[] values;
        if (Strings.isEmpty(str)) {
            return Default;
        }
        for (MyItemsSortOrder myItemsSortOrder : values()) {
            if (myItemsSortOrder.toString().equals(str)) {
                return myItemsSortOrder;
            }
        }
        return Default;
    }
}
