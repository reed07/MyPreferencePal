package com.myfitnesspal.feature.nutrition.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.FoodListItem;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.List;

public class FoodListAdapter extends ArrayAdapter<FoodListItem> {
    private LayoutInflater layoutInflater;
    /* access modifiers changed from: private */
    public boolean showMiniView;

    private class ViewHolder {
        private View equalsText;
        private TextView name;
        private TextView timesEatenTextView;
        private TextView total;

        public ViewHolder(View view) {
            this.name = (TextView) ViewUtils.findById(view, R.id.name);
            this.timesEatenTextView = (TextView) ViewUtils.findById(view, R.id.times_eaten);
            this.equalsText = ViewUtils.findById(view, R.id.equals_sign);
            this.total = (TextView) ViewUtils.findById(view, R.id.total);
        }

        public void setupView(FoodListItem foodListItem) {
            String foodName = foodListItem.getFoodName();
            TextView textView = this.name;
            if (Strings.equals(foodName, MealTypeName.QUICK_ADD)) {
                foodName = FoodListAdapter.this.getContext().getString(R.string.quick_add);
            }
            textView.setText(foodName);
            int timesEaten = foodListItem.getTimesEaten();
            setVisibility(this.timesEatenTextView, !FoodListAdapter.this.showMiniView);
            setVisibility(this.equalsText, !FoodListAdapter.this.showMiniView);
            this.timesEatenTextView.setText(FoodListAdapter.this.getContext().getString(R.string.x_times_eaten, new Object[]{Integer.valueOf(timesEaten)}));
            this.total.setText(NumberUtils.localeStringFromInt(Math.round(foodListItem.getAmountConsumed())));
        }

        private void setVisibility(View view, boolean z) {
            ViewUtils.setVisible(z, 4, view);
        }
    }

    public FoodListAdapter(Context context, int i, List<FoodListItem> list, boolean z) {
        super(context, i, list);
        this.layoutInflater = LayoutInflater.from(context);
        this.showMiniView = z;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.food_list_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setupView((FoodListItem) getItem(i));
        return view;
    }
}
