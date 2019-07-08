package com.myfitnesspal.feature.search.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.event.MealFilterChangedEvent;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MealFilterDialog extends SortFilterDialogBase<CheckableListItem> {
    public static final int ALL_MEALS_INDEX = 1;
    public static final String TAG = "MealFilterDialog";
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    /* access modifiers changed from: protected */
    public int getTitleResId() {
        return R.string.meal_filter;
    }

    public static MealFilterDialog newInstance(int i, String str) {
        MealFilterDialog mealFilterDialog = new MealFilterDialog();
        setArgumentsToFragment(mealFilterDialog, i, str);
        return mealFilterDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    /* access modifiers changed from: protected */
    public List<CheckableListItem> getItemsList(int i) {
        ArrayList arrayList = new ArrayList();
        boolean shouldShowAllMeals = ((LocalSettingsService) this.localSettingsService.get()).shouldShowAllMeals();
        arrayList.add(new CheckableListItem(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(this.mealName, (UserEnergyService) this.userEnergyService.get()), !shouldShowAllMeals));
        arrayList.add(new CheckableListItem(getString(R.string.all_meals), shouldShowAllMeals));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        LocalSettingsService localSettingsService2 = (LocalSettingsService) this.localSettingsService.get();
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        localSettingsService2.setShouldShowAllMeals(z);
        this.messageBus.post(new MealFilterChangedEvent());
    }
}
