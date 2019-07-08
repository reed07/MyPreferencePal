package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.FoodListItem;
import com.myfitnesspal.feature.nutrition.ui.adapter.FoodListAdapter;
import dagger.Lazy;
import java.util.List;

public class CompleteFoodListsChartRendererImpl extends FoodListsChartRendererBase {
    /* access modifiers changed from: protected */
    public int getSpinnerContainerId() {
        return R.id.chart_spinner_container;
    }

    public CompleteFoodListsChartRendererImpl(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public void addFoodListItemsView(ViewGroup viewGroup, View view, int i) {
        viewGroup.addView(view);
    }

    /* access modifiers changed from: protected */
    public View getViewForFoodList(List<FoodListItem> list, int i, int i2) {
        LayoutInflater from = LayoutInflater.from(this.context);
        ListView listView = (ListView) from.inflate(R.layout.food_list_page, null);
        listView.addHeaderView(from.inflate(R.layout.food_list_header, listView, false));
        listView.setAdapter(new FoodListAdapter(this.context, 0, list, false));
        return listView;
    }
}
