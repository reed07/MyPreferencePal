package com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin;

import android.app.Activity;
import android.content.Intent;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.task.FetchCompleteRecipeTask;
import com.myfitnesspal.feature.recipes.task.FetchCompleteRecipeTask.CompletedEvent;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class GetRecipeV2Mixin extends RunnerLifecycleMixin {
    public static final String EXTRA_RECIPE_BOX_ITEM = "extra_recipe_box_item";
    public static final String EXTRA_RECIPE_V2 = "extra_recipe_v2";
    @Inject
    Lazy<RecipeService> recipeService;

    public GetRecipeV2Mixin(MfpActivity mfpActivity) {
        super(mfpActivity);
        MyFitnessPalApp.getInstance().component().inject(this);
        new FetchCompleteRecipeTask(this.recipeService, (RecipeBoxItem) BundleUtils.getParcelable(mfpActivity.getIntent().getExtras(), EXTRA_RECIPE_BOX_ITEM, RecipeBoxItem.class.getClassLoader())).run(getRunner());
    }

    @Subscribe
    public void onFetchRecipeTaskCompleted(CompletedEvent completedEvent) {
        Activity activity = getActivity();
        if (!completedEvent.successful() || completedEvent.getResult() == null) {
            activity.setResult(0);
        } else {
            MfpRecipe mfpRecipe = (MfpRecipe) completedEvent.getResult();
            Intent intent = new Intent();
            intent.putExtra(EXTRA_RECIPE_V2, mfpRecipe);
            activity.setResult(-1, intent);
        }
        activity.finish();
    }
}
