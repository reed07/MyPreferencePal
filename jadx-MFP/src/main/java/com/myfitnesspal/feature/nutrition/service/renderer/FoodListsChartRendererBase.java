package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.model.FoodListItem;
import com.uacf.core.util.Function1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class FoodListsChartRendererBase extends CoreChartRendererBase {
    /* access modifiers changed from: protected */
    public abstract void addFoodListItemsView(ViewGroup viewGroup, View view, int i);

    /* access modifiers changed from: protected */
    public abstract View getViewForFoodList(List<FoodListItem> list, int i, int i2);

    protected FoodListsChartRendererBase(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public void constructDailyChart(ViewGroup viewGroup, Date date, String str, int i, int i2) {
        constructChart(viewGroup, date, i, false, i2);
    }

    /* access modifiers changed from: protected */
    public void constructWeeklyChart(ViewGroup viewGroup, Date date, int i, int i2) {
        constructChart(viewGroup, date, i, true, i2);
    }

    private void constructChart(final ViewGroup viewGroup, Date date, final int i, boolean z, final int i2) {
        removeSpinnerContainerBackground(viewGroup);
        getNutritionGraphService().renderFoodList(date, i, z, new Function1<Map<FoodListItem, Integer>>() {
            public void execute(Map<FoodListItem, Integer> map) throws RuntimeException {
                FoodListsChartRendererBase.this.hideSpinnerContainer(viewGroup);
                FoodListsChartRendererBase foodListsChartRendererBase = FoodListsChartRendererBase.this;
                foodListsChartRendererBase.addFoodListItemsView(viewGroup, foodListsChartRendererBase.getViewForFoodListItemAndCountMap(map, i, i2), map.size());
            }
        });
    }

    /* access modifiers changed from: private */
    public View getViewForFoodListItemAndCountMap(Map<FoodListItem, Integer> map, int i, int i2) {
        return getViewForFoodList(getFoodListItemForMap(map), i, i2);
    }

    private List<FoodListItem> getFoodListItemForMap(Map<FoodListItem, Integer> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            FoodListItem foodListItem = (FoodListItem) entry.getKey();
            int intValue = ((Integer) entry.getValue()).intValue();
            foodListItem.setTimesEaten(intValue);
            foodListItem.setAmountConsumed(((float) intValue) * foodListItem.getAmountConsumed());
            arrayList.add(foodListItem);
        }
        Collections.sort(arrayList);
        return arrayList;
    }
}
