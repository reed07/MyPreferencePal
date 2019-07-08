package com.myfitnesspal.feature.search.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.shared.constants.Constants.FoodSearch;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.ViewHolder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class ButtonItemViewHolder extends ViewHolder<DiaryEntryCellModel> {
    private final Context context;
    private final FoodSearchTab foodSearchTab;
    private final boolean isPremiumQuickAddEnabled;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final TextView txtButtonItem = ((TextView) findById(R.id.createNewFoodExt));
    private final Lazy<UserEnergyService> userEnergyService;

    public ButtonItemViewHolder(View view, FoodSearchTab foodSearchTab2, boolean z, Lazy<UserEnergyService> lazy, Lazy<LocalizedStringsUtil> lazy2) {
        super(view);
        this.context = view.getContext().getApplicationContext();
        this.foodSearchTab = foodSearchTab2;
        this.isPremiumQuickAddEnabled = z;
        this.userEnergyService = lazy;
        this.localizedStringsUtil = lazy2;
    }

    public void setData(DiaryEntryCellModel diaryEntryCellModel, int i) {
        String str;
        if (Strings.equals(((Food) diaryEntryCellModel).getDescription(), FoodSearch.CREATE_NEW)) {
            str = this.context.getString(this.foodSearchTab == FoodSearchTab.RECIPES ? R.string.new_recipe : R.string.new_food);
        } else if (this.isPremiumQuickAddEnabled) {
            str = this.context.getString(R.string.quick_add);
        } else {
            str = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString("quick_add", this.userEnergyService);
        }
        this.txtButtonItem.setText(str);
    }
}
