package com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin;

import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.meals.task.RetrieveMealFoodTask;
import com.myfitnesspal.feature.meals.task.RetrieveMealFoodTask.CompletedEvent;
import com.myfitnesspal.feature.meals.ui.mixin.SharedMealViewerMixin;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import javax.inject.Inject;

public class MealFoodLoaderMixin extends RunnerLifecycleMixin {
    private MfpActivity activity;
    private String foodId;
    @Inject
    Lazy<FoodService> foodService;
    private boolean isCurrentUsersMeal;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    private MealFood mealFood;
    private String mealFoodNotes = "";
    private String mealImageId;
    private String mealOwnerUserId;
    private String mealOwnerUserName;

    public MealFoodLoaderMixin(MfpActivity mfpActivity, Intent intent) {
        super(mfpActivity);
        this.activity = mfpActivity;
        this.mealFood = (MealFood) ExtrasUtils.getParcelable(intent, "meal_food", MealFood.class.getClassLoader());
        this.foodId = ExtrasUtils.getString(intent, "food_id");
        this.isCurrentUsersMeal = ExtrasUtils.getBoolean(intent, SharedMealViewerMixin.EXTRA_IS_CURRENT_USERS_MEAL);
        this.mealImageId = ExtrasUtils.getString(intent, SharedMealViewerMixin.EXTRA_MEAL_IMAGE_ID);
        this.mealOwnerUserName = ExtrasUtils.getString(intent, SharedMealViewerMixin.EXTRA_MEAL_OWNER_USERNAME);
        this.mealOwnerUserId = ExtrasUtils.getString(intent, SharedMealViewerMixin.EXTRA_MEAL_OWNER_UID);
        component().inject(this);
        init();
    }

    private void init() {
        if (this.mealFood == null) {
            new RetrieveMealFoodTask(this.foodService, this.foodId, this.isCurrentUsersMeal).run(this.activity.getRunner());
        } else if (this.isCurrentUsersMeal) {
            startFoodEditorAsMealItemEditor();
        } else {
            startFoodEditorAsViewSharedMeal(this.activity.getIntent().getExtras());
        }
    }

    @Subscribe
    public void onRetrieveMealFoodTaskCompletedEvent(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            Tuple2 tuple2 = (Tuple2) completedEvent.getResult();
            this.mealFood = (MealFood) tuple2.getItem1();
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportFriendsMealViewed(this.isCurrentUsersMeal);
            if (tuple2.getItem2() != null) {
                this.mealFoodNotes = ((FoodNotes) tuple2.getItem2()).getNotes();
            }
            if (this.isCurrentUsersMeal) {
                startFoodEditorAsMealItemEditor();
                return;
            }
            Bundle extras = this.activity.getIntent().getExtras();
            extras.putParcelable("meal_food", this.mealFood);
            extras.putString(SharedMealViewerMixin.EXTRA_MEAL_NOTES, this.mealFoodNotes);
            startFoodEditorAsViewSharedMeal(extras);
            return;
        }
        Ln.e(completedEvent.getFailure());
        startFoodEditorAsViewSharedMeal(this.activity.getIntent().getExtras());
    }

    private void startFoodEditorAsMealItemEditor() {
        this.activity.getNavigationHelper().withIntent(FoodEditorActivity.newMealItemEditorIntent(this.activity, null, null, this.mealFood, null)).finishActivityAfterNavigation().startActivity();
    }

    private void startFoodEditorAsViewSharedMeal(Bundle bundle) {
        this.activity.getNavigationHelper().withIntent(FoodEditorActivity.newViewSharedMealIntent(this.activity, this.foodId, this.mealImageId, this.mealOwnerUserName, this.mealOwnerUserId, this.isCurrentUsersMeal).putExtras(bundle)).finishActivityAfterNavigation().startActivity();
    }
}
