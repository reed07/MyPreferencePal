package com.myfitnesspal.shared.ui.view;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.squareup.otto.Bus;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class FoodInsightViewBinder extends InsightViewHolderFAB {
    public FoodInsightViewBinder(View view, Lazy<FoodService> lazy, Bus bus, FoodEntry foodEntry) {
        super(view, lazy, bus, foodEntry);
    }

    /* access modifiers changed from: protected */
    public void setTheming(View view, TextView textView, Type type) {
        int i;
        super.setTheming(view, textView, type);
        View findById = ViewUtils.findById(this.view, R.id.insight_divider);
        int i2 = 0;
        switch (type) {
            case Positive:
                i2 = R.color.food_info_insight_background_green;
                i = R.color.food_info_insight_divider_green;
                break;
            case Negative:
                i2 = R.color.food_info_insight_background_orange;
                i = R.color.food_info_insight_divider_orange;
                break;
            case Question:
                i2 = R.color.background_light_blue;
                i = R.color.divider_light_blue;
                break;
            default:
                i = 0;
                break;
        }
        Resources resources = view.getResources();
        view.setBackgroundColor(resources.getColor(i2));
        findById.setBackgroundColor(resources.getColor(i));
    }
}
