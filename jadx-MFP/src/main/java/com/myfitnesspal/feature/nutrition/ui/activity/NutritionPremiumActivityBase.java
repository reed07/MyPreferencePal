package com.myfitnesspal.feature.nutrition.ui.activity;

import android.content.Context;
import com.myfitnesspal.feature.goals.ui.activity.AdditionalNutrientGoalsActivity;
import com.myfitnesspal.feature.goals.ui.activity.EditCustomMacroGoalsActivity;
import com.myfitnesspal.feature.nutrition.event.GoalsClickedEvent;
import com.myfitnesspal.feature.nutrition.event.LegendClickedEvent;
import com.myfitnesspal.feature.nutrition.event.NavigateToFoodListEvent;
import com.myfitnesspal.feature.nutrition.event.NavigateToPremiumUpsellEvent;
import com.myfitnesspal.feature.nutrition.event.NutrientEntryClickEvent;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.BusEventHandler;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Subscribe;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public abstract class NutritionPremiumActivityBase extends MfpActivity {
    protected GraphViewFragment currentGraphViewFragment;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private static class BusEventHelper implements BusEventHandler {
        NutritionPremiumActivityBase activity;

        public BusEventHelper(NutritionPremiumActivityBase nutritionPremiumActivityBase) {
            this.activity = nutritionPremiumActivityBase;
        }

        private NavigationHelper getNavigationHelper() {
            return this.activity.getNavigationHelper();
        }

        @Subscribe
        public void onGoalsClickedEvent(GoalsClickedEvent goalsClickedEvent) {
            if (NutritionalValues.isMacroOrCalorieIndex(goalsClickedEvent.getNutrientIndex())) {
                getNavigationHelper().withIntent(EditCustomMacroGoalsActivity.newStartIntent(this.activity)).startActivity();
            } else {
                getNavigationHelper().withIntent(AdditionalNutrientGoalsActivity.newStartIntent(this.activity)).startActivity();
            }
        }

        @Subscribe
        public void onLegendClickedEvent(LegendClickedEvent legendClickedEvent) {
            int nutrientIndex = legendClickedEvent.getNutrientIndex();
            NutritionPremiumActivityBase nutritionPremiumActivityBase = this.activity;
            getNavigationHelper().withIntent(NutrientGraphActivity.newStartIntent(this.activity, new NutrientEntry(nutrientIndex, NutritionalValues.simplifiedLabelForNutrientIndex(nutritionPremiumActivityBase, nutrientIndex, ((UserEnergyService) nutritionPremiumActivityBase.userEnergyService.get()).isCalories())))).startActivity();
        }

        @Subscribe
        public void onNutrientEntryClickEvent(NutrientEntryClickEvent nutrientEntryClickEvent) {
            getNavigationHelper().withIntent(NutrientGraphActivity.newStartIntent(this.activity, nutrientEntryClickEvent.getNutrientEntry())).startActivity();
        }

        @Subscribe
        public void onNavigateToPremiumUpsellEvent(NavigateToPremiumUpsellEvent navigateToPremiumUpsellEvent) {
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) this.activity, navigateToPremiumUpsellEvent.getPremiumFeature())).startActivity();
        }

        @Subscribe
        public void onNavigateToFoodListEvent(NavigateToFoodListEvent navigateToFoodListEvent) {
            getNavigationHelper().withIntent(FoodListsActivity.newStartIntent(this.activity, navigateToFoodListEvent.getNutrientEntry(), this.activity.currentGraphViewFragment.getAnalyticsEventId(), navigateToFoodListEvent.getMacroIndex())).startActivity();
        }
    }

    public void onBackPressed() {
        GraphViewFragment graphViewFragment = this.currentGraphViewFragment;
        if (graphViewFragment == null || !graphViewFragment.isDateBarHidden()) {
            super.onBackPressed();
        } else {
            this.currentGraphViewFragment.onBackPressed();
        }
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        super.addBusEventHandlers(list);
        list.add(new BusEventHelper(this));
    }
}
