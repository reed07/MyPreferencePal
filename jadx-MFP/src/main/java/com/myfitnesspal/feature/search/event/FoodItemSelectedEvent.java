package com.myfitnesspal.feature.search.event;

import com.myfitnesspal.shared.model.v2.MfpFood;

public class FoodItemSelectedEvent<T> {
    private final int index;
    private MfpFood item;
    private final String mealName;
    private final String queryTerm;

    public FoodItemSelectedEvent(String str, MfpFood mfpFood, int i, String str2) {
        this.queryTerm = str;
        this.item = mfpFood;
        this.index = i;
        this.mealName = str2;
    }

    public String getQueryTerm() {
        return this.queryTerm;
    }

    public MfpFood getItem() {
        return this.item;
    }

    public int getIndex() {
        return this.index;
    }

    public String getMealName() {
        return this.mealName;
    }
}
