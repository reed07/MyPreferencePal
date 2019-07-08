package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.recipes.ui.fragment.MyFoodsFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.MyMealsFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.MyRecipesFragment;
import com.myfitnesspal.feature.recipes.ui.fragment.MyRecipesMealsFoodsBaseFragment;
import com.myfitnesspal.feature.recipes.util.RecipesMealsFoodsAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity.EditablePagerAdapter;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity.EditablePagerAdapter.FragmentEntry;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class RecipesAndFoods extends MfpPagedEditableActivity {
    public static final String EXTRA_CREATE_RECIPE = "create_recipe";
    public static final String EXTRA_HIDE_MEAL_COLLECTIONS_CARD = "hide_meal_collections_card";
    private static final String EXTRA_RECIPE_DELETED = "recipe_deleted";
    private static final String EXTRA_SELECTED_TAB = "selected_tab";
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    /* access modifiers changed from: private */
    public final DialogPositiveListener onMealCreatedViewClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            ((MealAnalyticsHelper) RecipesAndFoods.this.mealAnalyticsHelper.get()).reportViewSavedMeal();
            RecipesAndFoods.this.getNavigationHelper().withIntent(((FoodSearchActivityFactory) RecipesAndFoods.this.foodSearchRouter.get()).getFoodSearchActivityIntent(RecipesAndFoods.this.getActivity(), new Extras().shouldSelectMealTab(true))).startActivity();
        }
    };
    @Inject
    Lazy<RecipesMealsFoodsAnalyticsHelper> recipesMealsFoodsAnalyticsHelper;
    @BindView(2131363577)
    EditText searchText;

    static class RecipesAndFoodsPagerAdapter extends EditablePagerAdapter {
        private String filterString;
        private Map<Integer, MyRecipesMealsFoodsBaseFragment> instantiatedFragments = new HashMap();

        RecipesAndFoodsPagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager, context);
        }

        /* access modifiers changed from: protected */
        public List<FragmentEntry> createFragmentList() {
            return Arrays.asList(new FragmentEntry[]{new FragmentEntry((MfpEditableFragmentBase) MyRecipesFragment.newInstance(), TabType.Recipes.getNameId()), new FragmentEntry((MfpEditableFragmentBase) MyMealsFragment.newInstance(), TabType.Meals.getNameId()), new FragmentEntry((MfpEditableFragmentBase) MyFoodsFragment.newInstance(), TabType.Foods.getNameId())});
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            MyRecipesMealsFoodsBaseFragment myRecipesMealsFoodsBaseFragment = (MyRecipesMealsFoodsBaseFragment) super.instantiateItem(viewGroup, i);
            myRecipesMealsFoodsBaseFragment.setFilterStrings(this.filterString);
            this.instantiatedFragments.put(Integer.valueOf(i), myRecipesMealsFoodsBaseFragment);
            return myRecipesMealsFoodsBaseFragment;
        }

        /* access modifiers changed from: 0000 */
        public void setFilterString(String str) {
            this.filterString = str;
            for (MyRecipesMealsFoodsBaseFragment filterStrings : allFragments()) {
                filterStrings.setFilterStrings(str);
            }
        }

        /* access modifiers changed from: 0000 */
        public void invalidateData() {
            for (MyRecipesMealsFoodsBaseFragment invalidateData : allFragments()) {
                invalidateData.invalidateData();
            }
        }

        /* access modifiers changed from: 0000 */
        public List<MyRecipesMealsFoodsBaseFragment> allFragments() {
            return new ArrayList(this.instantiatedFragments.values());
        }

        /* access modifiers changed from: 0000 */
        public MyRecipesMealsFoodsBaseFragment getFragment(int i) {
            return (MyRecipesMealsFoodsBaseFragment) this.instantiatedFragments.get(Integer.valueOf(i));
        }
    }

    public enum TabType {
        Recipes("recipes", R.string.recipes),
        Meals("meals", R.string.meals),
        Foods("foods", R.string.foods);
        
        private final int nameId;
        private final String tabName;

        private TabType(String str, int i) {
            this.tabName = str;
            this.nameId = i;
        }

        @StringRes
        public int getNameId() {
            return this.nameId;
        }

        public String getTabName() {
            return this.tabName;
        }
    }

    public String getAnalyticsScreenTag() {
        return Screens.RECIPES_AND_FOODS;
    }

    /* access modifiers changed from: protected */
    public int getContentViewId() {
        return R.layout.recipes_and_foods;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, RecipesAndFoods.class);
    }

    public static Intent newStartIntent(Context context, TabType tabType) {
        return newStartIntent(context).putExtra(EXTRA_SELECTED_TAB, tabType);
    }

    public static Intent newStartIntent(Context context, TabType tabType, boolean z) {
        return newStartIntent(context).putExtra(EXTRA_SELECTED_TAB, tabType).putExtra(EXTRA_HIDE_MEAL_COLLECTIONS_CARD, z);
    }

    public static Intent newStartIntentAfterRecipeDeletion(Context context) {
        return newStartIntent(context).putExtra("recipe_deleted", true);
    }

    public static Intent newStartIntentForRecipeCreation(Context context) {
        return newStartIntent(context, TabType.Recipes).putExtra(EXTRA_CREATE_RECIPE, true);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        ViewPager pager = getPager();
        TabType tabType = (TabType) ExtrasUtils.getSerializable(getIntent(), EXTRA_SELECTED_TAB, TabType.class.getClassLoader());
        if (tabType != null) {
            pager.setCurrentItem(tabType.ordinal());
        }
        setupListeners();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (ExtrasUtils.getBoolean(intent, "recipe_deleted")) {
            intent.removeExtra("recipe_deleted");
            new SnackbarBuilder(getPager()).setMessage((int) R.string.recipe_deleted).setDuration(-1).show();
        }
    }

    /* access modifiers changed from: protected */
    public EditablePagerAdapter recreateAdapter() {
        return new RecipesAndFoodsPagerAdapter(getSupportFragmentManager(), this);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getAdapter().invalidateData();
        if (i == 64) {
            getMessageBus().post(new StartSyncEvent());
        } else if (i == 183 && i2 == -1 && intent != null) {
            showCreateMealDialog(intent);
        }
    }

    public void onBackPressed() {
        if (showAsTopLevelActivity()) {
            getNavigationHelper().withIntent(HomeActivity.newStartIntent(getActivity())).startActivity();
        } else {
            super.onBackPressed();
        }
    }

    private void setupListeners() {
        getPager().addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                MyRecipesMealsFoodsBaseFragment fragment = RecipesAndFoods.this.getAdapter().getFragment(i);
                if (fragment != null) {
                    ((RecipesMealsFoodsAnalyticsHelper) RecipesAndFoods.this.recipesMealsFoodsAnalyticsHelper.get()).reportRecipesMealsFoodsTabEvent(RecipesAndFoods.this.getTabName(i));
                    fragment.onSelectedInPagerAdapter();
                }
            }
        });
        this.searchText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    ((RecipesMealsFoodsAnalyticsHelper) RecipesAndFoods.this.recipesMealsFoodsAnalyticsHelper.get()).reportRecipesAndFoodsSearchTap(RecipesAndFoods.this.getCurrentTabName());
                }
            }
        });
        this.searchText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                RecipesAndFoods.this.getAdapter().setFilterString(editable.toString());
                ((RecipesMealsFoodsAnalyticsHelper) RecipesAndFoods.this.recipesMealsFoodsAnalyticsHelper.get()).reportRecipesAndFoodsSearchType(RecipesAndFoods.this.getCurrentTabName());
            }
        });
    }

    /* access modifiers changed from: private */
    public String getCurrentTabName() {
        return getTabName(getPager().getCurrentItem());
    }

    /* access modifiers changed from: private */
    public String getTabName(int i) {
        return TabType.values()[i].getTabName();
    }

    /* access modifiers changed from: private */
    public RecipesAndFoodsPagerAdapter getAdapter() {
        return (RecipesAndFoodsPagerAdapter) this.adapter;
    }

    /* access modifiers changed from: protected */
    public void showCreateMealDialog(Intent intent) {
        String stringExtra = intent.getStringExtra("operation");
        final String stringExtra2 = intent.getStringExtra("meal_food");
        intent.removeExtra("operation");
        final boolean equals = Strings.equals(stringExtra, "create");
        if ((equals || Strings.equals(stringExtra, MealEditorMixin.EXTRA_REPLACE)) && Strings.notEmpty(stringExtra2)) {
            new Handler().post(new Runnable() {
                public void run() {
                    RecipesAndFoods.this.showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(equals ? R.string.meal_created : R.string.meal_updated)).setMessage(RecipesAndFoods.this.getString(equals ? R.string.meal_has_been_added : R.string.meal_has_been_updated, new Object[]{stringExtra2}))).setPositiveText(R.string.view, RecipesAndFoods.this.onMealCreatedViewClickListener)).setNegativeText(R.string.dismiss, null), "");
                }
            });
        }
    }
}
