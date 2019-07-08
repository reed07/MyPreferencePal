package com.myfitnesspal.feature.search.ui.model;

import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import java.util.List;

public class FoodSearchModel {
    public final FoodImages images;
    public final List<DiaryEntryCellModel> items;
    public final int limit;

    public FoodSearchModel(List<DiaryEntryCellModel> list, FoodImages foodImages, int i) {
        this.items = list;
        this.images = foodImages;
        this.limit = i;
    }
}
