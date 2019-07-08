package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.ActionType;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.ui.fragment.CreateOrEditRecipeFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.CreateOrEditRecipeFragment.OnRecipeCreatedOrUpdatedListener;
import com.myfitnesspal.feature.recipes.ui.fragment.RecipeDetailsFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.RecipeDetailsFragment.OnRecipeActionListener;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class RecipeDetailsActivity extends MfpActivity {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "analytics_intent_data";
    private static final String EXTRA_IS_CREATING_RECIPE = "is_creating_recipe";
    private static final String EXTRA_MEAL_NAME = "meal_name";
    private static final String EXTRA_RECIPE = "recipe";
    public static final String EXTRA_RECIPE_DELETED = "recipe_deleted";
    private static final String EXTRA_RECIPE_STATE = "recipe_state";
    private static final String TAG_RECIPE_CREATE_FRAGMENT = "recipe_create_fragment";
    private static final String TAG_RECIPE_DETAILS_FRAGMENT = "recipe_details_fragment";
    private static final String TAG_RECIPE_EDIT_FRAGMENT = "recipe_edit_fragment";
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    private final OnRecipeActionListener onRecipeActionListener = new OnRecipeActionListener() {
        public void onEditRecipeTapped() {
            RecipeAnalyticsIntentData create = RecipeAnalyticsIntentData.create(StartScreen.RecipeDetailsScreen, ActionType.Edit);
            ((RecipesAnalyticsHelper) RecipeDetailsActivity.this.recipesAnalyticsHelper.get()).reportRecipeFlowStarted(create);
            RecipeDetailsActivity.this.getIntent().putExtra(RecipeDetailsActivity.EXTRA_ANALYTICS_INTENT_DATA, create);
            RecipeDetailsActivity.this.state = RecipeState.Edit;
            RecipeDetailsActivity.this.setupViewBasedOnState();
        }

        public void onRecipeDeleted() {
            if (RecipeDetailsActivity.this.isCreatingRecipe()) {
                RecipeDetailsActivity.this.getNavigationHelper().asTopLevelActivity().withIntent(RecipesAndFoods.newStartIntentAfterRecipeDeletion(RecipeDetailsActivity.this)).withClearTopAndSingleTop().startActivity();
            } else {
                RecipeDetailsActivity.this.setResult(-1, new Intent().putExtra(RecipeDetailsActivity.EXTRA_RECIPE_DELETED, true));
            }
            RecipeDetailsActivity.this.finish();
        }
    };
    private final OnRecipeCreatedOrUpdatedListener onRecipeCreatedOrUpdatedListener = new OnRecipeCreatedOrUpdatedListener() {
        public void onRecipeCreatedOrUpdated(MfpRecipe mfpRecipe) {
            RecipeDetailsActivity.this.recipe = mfpRecipe;
            RecipeDetailsActivity.this.state = RecipeState.Display;
            RecipeDetailsActivity.this.setupViewBasedOnState();
        }
    };
    /* access modifiers changed from: private */
    public MfpRecipe recipe;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    /* access modifiers changed from: private */
    public RecipeState state;

    private enum RecipeState {
        Create(R.string.new_recipe_text, RecipeDetailsActivity.TAG_RECIPE_CREATE_FRAGMENT),
        Edit(R.string.edit_recipe, RecipeDetailsActivity.TAG_RECIPE_EDIT_FRAGMENT),
        Display(R.string.recipe_details, RecipeDetailsActivity.TAG_RECIPE_DETAILS_FRAGMENT);
        
        /* access modifiers changed from: private */
        public final String fragmentTag;
        /* access modifiers changed from: private */
        @StringRes
        public final int titleResId;

        private RecipeState(int i, String str) {
            this.titleResId = i;
            this.fragmentTag = str;
        }
    }

    public static Intent newStartIntentForCreatingRecipe(Context context, MfpRecipe mfpRecipe, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        return new Intent(context, RecipeDetailsActivity.class).putExtra("recipe", mfpRecipe).putExtra(EXTRA_RECIPE_STATE, RecipeState.Create).putExtra(EXTRA_IS_CREATING_RECIPE, true).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData).putExtra("meal_name", str);
    }

    public static Intent newStartIntentForDisplayingRecipe(Context context, MfpRecipe mfpRecipe) {
        return new Intent(context, RecipeDetailsActivity.class).putExtra("recipe", mfpRecipe).putExtra(EXTRA_RECIPE_STATE, RecipeState.Display);
    }

    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.recipe_details_container);
        this.recipe = (MfpRecipe) BundleUtils.getParcelable(bundle, "recipe", ExtrasUtils.getParcelable(getIntent(), "recipe", MfpRecipe.class.getClassLoader()), MfpRecipe.class.getClassLoader());
        this.state = (RecipeState) BundleUtils.getSerializable(bundle, EXTRA_RECIPE_STATE, ExtrasUtils.getSerializable(getIntent(), EXTRA_RECIPE_STATE, RecipeState.class.getClassLoader()), RecipeState.class.getClassLoader());
        setupViewBasedOnState();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("recipe", this.recipe);
        bundle.putSerializable(EXTRA_RECIPE_STATE, this.state);
    }

    public void onBackPressed() {
        if (!overrideBackPress()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void setupViewBasedOnState() {
        setTitle(this.state.titleResId);
        String access$100 = this.state.fragmentTag;
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(access$100);
        if (findFragmentByTag == null) {
            switch (this.state) {
                case Create:
                    findFragmentByTag = CreateOrEditRecipeFragment.newInstanceForCreation(this.recipe, getRecipeAnalyticsIntentData(), getIntent().getStringExtra("meal_name"));
                    break;
                case Edit:
                    findFragmentByTag = CreateOrEditRecipeFragment.newInstanceForEdit(this.recipe, getRecipeAnalyticsIntentData());
                    break;
                case Display:
                    findFragmentByTag = RecipeDetailsFragment.newInstance(this.recipe, getIntent().getStringExtra("meal_name"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled state: ");
                    sb.append(this.state);
                    throw new IllegalStateException(sb.toString());
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.container, findFragmentByTag, access$100);
            beginTransaction.commit();
        }
        if (this.state == RecipeState.Create || this.state == RecipeState.Edit) {
            ((CreateOrEditRecipeFragment) findFragmentByTag).setOnRecipeCreatedOrUpdatedListener(this.onRecipeCreatedOrUpdatedListener);
        } else if (this.state == RecipeState.Display) {
            ((RecipeDetailsFragment) findFragmentByTag).setOnRecipeActionListener(this.onRecipeActionListener);
        }
    }

    private boolean overrideBackPress() {
        Intent intent;
        switch (this.state) {
            case Edit:
                this.state = RecipeState.Display;
                setupViewBasedOnState();
                return true;
            case Display:
                if (!isCreatingRecipe()) {
                    return false;
                }
                NavigationHelper asTopLevelActivity = getNavigationHelper().asTopLevelActivity();
                if (isStartScreenFoodSearch()) {
                    intent = ((FoodSearchActivityFactory) this.foodSearchRouter.get()).getFoodSearchActivityIntent(this, new Extras().setMealName(ExtrasUtils.getString(getIntent(), "meal_name")));
                } else {
                    intent = RecipesAndFoods.newStartIntent(this);
                }
                asTopLevelActivity.withIntent(intent).withClearTopAndSingleTop().startActivity();
                return true;
            default:
                return false;
        }
    }

    private boolean isStartScreenFoodSearch() {
        return getRecipeAnalyticsIntentData().getStartScreen() == StartScreen.FoodSearch;
    }

    private RecipeAnalyticsIntentData getRecipeAnalyticsIntentData() {
        return (RecipeAnalyticsIntentData) ExtrasUtils.getParcelable(getIntent(), EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
    }

    /* access modifiers changed from: private */
    public boolean isCreatingRecipe() {
        return ExtrasUtils.getBoolean(getIntent(), EXTRA_IS_CREATING_RECIPE);
    }
}
