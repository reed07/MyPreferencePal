package com.myfitnesspal.feature.goals.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.myfitnesspal.feature.goals.model.MealGoalsActivityViewModel;
import com.myfitnesspal.feature.goals.model.MealGoalsActivityViewModel.DayOfWeek;
import com.myfitnesspal.feature.goals.ui.fragment.MealGoalsFragment;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.util.DateTimeUtils;

public class MealGoalsPagerAdapter extends FragmentStatePagerAdapter {
    private static final int MULTI_ITEMS_COUNT = 7;
    private static final int SINGLE_ITEM_COUNT = 1;
    private final Context context;
    private MealGoalsActivityViewModel mealGoalsActivityViewModel;

    public int getItemPosition(Object obj) {
        return -2;
    }

    public MealGoalsPagerAdapter(Context context2, FragmentManager fragmentManager, MealGoalsActivityViewModel mealGoalsActivityViewModel2) {
        super(fragmentManager);
        this.context = context2;
        this.mealGoalsActivityViewModel = mealGoalsActivityViewModel2;
    }

    public int getCount() {
        return this.mealGoalsActivityViewModel.isCustomGoalsPresent() ? 7 : 1;
    }

    public Fragment getItem(int i) {
        return MealGoalsFragment.newInstance(getDailyGoalForPosition(i), this.mealGoalsActivityViewModel.getMealNames().getNames(), this.mealGoalsActivityViewModel.getInputMode());
    }

    public CharSequence getPageTitle(int i) {
        return this.mealGoalsActivityViewModel.isCustomGoalsPresent() ? this.context.getString(DateTimeUtils.getFormattedDayOFWeek(this.mealGoalsActivityViewModel.getDailyGoalForDayOfWeek(DayOfWeek.values()[i]).getDayOfWeek())) : "";
    }

    public void setMealGoalsActivityViewModel(MealGoalsActivityViewModel mealGoalsActivityViewModel2) {
        this.mealGoalsActivityViewModel = mealGoalsActivityViewModel2;
    }

    private MfpDailyGoal getDailyGoalForPosition(int i) {
        if (this.mealGoalsActivityViewModel.isCustomGoalsPresent()) {
            return this.mealGoalsActivityViewModel.getDailyGoalForDayOfWeek(DayOfWeek.values()[i]);
        }
        return this.mealGoalsActivityViewModel.getDefaultDailyGoal();
    }
}
