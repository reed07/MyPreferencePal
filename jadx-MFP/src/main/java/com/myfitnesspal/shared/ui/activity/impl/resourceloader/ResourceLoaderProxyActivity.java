package com.myfitnesspal.shared.ui.activity.impl.resourceloader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.meals.ui.mixin.SharedMealViewerMixin;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin.GetRecipeV2Mixin;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin.MealFoodLoaderMixin;

public class ResourceLoaderProxyActivity extends MfpActivity {
    private static final String EXTRA_MIXIN_TYPE = "extra_mixin_type";

    private enum Type {
        ViewSharedMeal,
        GetRecipeV2
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent newViewSharedMealIntent(Context context, String str, String str2, String str3, String str4, boolean z) {
        return new Intent(context, ResourceLoaderProxyActivity.class).putExtra("food_id", str).putExtra(SharedMealViewerMixin.EXTRA_MEAL_IMAGE_ID, str2).putExtra(SharedMealViewerMixin.EXTRA_IS_CURRENT_USERS_MEAL, z).putExtra(SharedMealViewerMixin.EXTRA_MEAL_OWNER_USERNAME, str3).putExtra(SharedMealViewerMixin.EXTRA_MEAL_OWNER_UID, str4).putExtra(FoodEditorActivity.EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.ViewSharedMeal).putExtra(EXTRA_MIXIN_TYPE, Type.ViewSharedMeal.ordinal());
    }

    public static Intent newRecipeV2Intent(Context context, RecipeBoxItem recipeBoxItem) {
        return new Intent(context, ResourceLoaderProxyActivity.class).putExtra(GetRecipeV2Mixin.EXTRA_RECIPE_BOX_ITEM, recipeBoxItem).putExtra(EXTRA_MIXIN_TYPE, Type.GetRecipeV2.ordinal());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setContentView((int) R.layout.progress_overlay_activity);
        super.onCreate(bundle);
        RunnerLifecycleMixin createMixin = createMixin(getIntent());
        if (createMixin != null) {
            registerMixin(createMixin);
        }
        if (createMixin == null) {
            finish();
        }
    }

    private RunnerLifecycleMixin createMixin(Intent intent) {
        Type type = Type.values()[intent.getExtras().getInt(EXTRA_MIXIN_TYPE)];
        if (intent.getExtras() != null) {
            switch (type) {
                case ViewSharedMeal:
                    return new MealFoodLoaderMixin(this, intent);
                case GetRecipeV2:
                    return new GetRecipeV2Mixin(this);
            }
        }
        return null;
    }
}
