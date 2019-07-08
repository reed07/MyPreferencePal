package com.myfitnesspal.feature.recipes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.event.CreateNewRecipeEvent;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.task.DeleteRecipesTask;
import com.myfitnesspal.feature.recipes.task.DeleteRecipesTask.CompletedEvent;
import com.myfitnesspal.feature.recipes.ui.activity.RecipeDetailsActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.recipes.ui.adapter.EditableAdapter;
import com.myfitnesspal.feature.recipes.util.RecipeImportUtils;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.RecipeBoxItemsDBAdapter;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.ResourceLoaderProxyActivity;
import com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin.GetRecipeV2Mixin;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.EditListAdapter;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.RowViewHolder;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class MyRecipesFragment extends MyRecipesMealsFoodsBaseFragment<RecipeBoxItem> {
    private static final String RECIPE_LOOKUP_FAILED_DIALOG_TAG = "recipe_lookup_failed";
    private static final int REQUEST_CODE_LOAD_RECIPE = 64;
    private static final String TAG_PROGRESS_DIALOG = "progress_dialog";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    DbConnectionManager dbConnectionManager;
    private List<RecipeBoxItem> items = new ArrayList();
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<RecipeService> recipeService;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @Inject
    UserEnergyService userEnergyService;

    private static class LoadTask extends EventedTaskBase<List<RecipeBoxItem>, Exception> {
        private final DbConnectionManager dbConnectionManager;
        private final SortOrder sortOrder;

        static class CompletedEvent extends TaskEventBase<List<RecipeBoxItem>, Exception> {
        }

        LoadTask(@Nonnull DbConnectionManager dbConnectionManager2, SortOrder sortOrder2) {
            super((TaskEventBase) new CompletedEvent());
            this.dbConnectionManager = dbConnectionManager2;
            this.sortOrder = sortOrder2;
        }

        /* access modifiers changed from: protected */
        public List<RecipeBoxItem> exec(Context context) throws Exception {
            ArrayList<RecipeBoxItem> fetchRecipeBoxItemsWithSortOrder = new RecipeBoxItemsDBAdapter(context, this.dbConnectionManager).fetchRecipeBoxItemsWithSortOrder(this.sortOrder, Integer.MAX_VALUE, 0);
            for (RecipeBoxItem recipeFood : fetchRecipeBoxItemsWithSortOrder) {
                recipeFood.recipeFood(this.dbConnectionManager).loadIngredientsAndPropertiesIfNeeded(this.dbConnectionManager);
            }
            return fetchRecipeBoxItemsWithSortOrder;
        }
    }

    /* access modifiers changed from: protected */
    public boolean disableGenericItemClickHandling() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int getAddItemButtonTextResId() {
        return R.string.new_recipe;
    }

    /* access modifiers changed from: protected */
    public void onItemClicked(RecipeBoxItem recipeBoxItem) {
    }

    /* access modifiers changed from: protected */
    public boolean wantsSwipeToRefresh() {
        return true;
    }

    public static MyRecipesFragment newInstance() {
        return new MyRecipesFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        Intent intent = getActivity().getIntent();
        if (intent.getBooleanExtra(RecipesAndFoods.EXTRA_CREATE_RECIPE, false)) {
            intent.removeExtra(RecipesAndFoods.EXTRA_CREATE_RECIPE);
            navigateToCreateRecipe();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 202 && i2 == -1 && ExtrasUtils.getBoolean(intent, RecipeDetailsActivity.EXTRA_RECIPE_DELETED)) {
            new SnackbarBuilder(getListView()).setMessage((int) R.string.recipe_deleted).setDuration(-1).show();
        } else if (i != 64) {
        } else {
            if (i2 == -1) {
                getNavigationHelper().fromFragment(this).withIntent(RecipeDetailsActivity.newStartIntentForDisplayingRecipe(getActivity(), (MfpRecipe) BundleUtils.getParcelable(intent.getExtras(), GetRecipeV2Mixin.EXTRA_RECIPE_V2, MfpRecipe.class.getClassLoader()))).startActivity(RequestCodes.RECIPE_DETAILS);
                return;
            }
            new SnackbarBuilder(getListView()).setMessage((int) R.string.my_recipes_recipe_lookup_failed_message).setDuration(0).show();
        }
    }

    /* access modifiers changed from: protected */
    public LocalSettingsService getLocalSettings() {
        return (LocalSettingsService) this.localSettingsService.get();
    }

    /* access modifiers changed from: protected */
    public void onActionAddClicked() {
        super.onActionAddClicked();
        navigateToCreateRecipe();
    }

    public Type getEmptyStateViewType() {
        return Type.Recipe;
    }

    public OnClickListener getEmptyStatePrimaryButtonListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                MyRecipesFragment.this.navigateToCreateRecipe();
            }
        };
    }

    @Subscribe
    public void onFetchRecipeBoxItemsCompleted(CompletedEvent completedEvent) {
        this.items = (List) completedEvent.getResult();
        setLoading(false);
    }

    @Subscribe
    public void onDeleteRecipeTaskCompletedEvent(CompletedEvent completedEvent) {
        DialogFragment dialogFragment = (DialogFragment) getFragmentManager().findFragmentByTag("progress_dialog");
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
        fetchData();
    }

    /* access modifiers changed from: protected */
    public void onActionDeleteClicked(List<RecipeBoxItem> list) {
        super.onActionDeleteClicked(list);
        ProgressDialogFragment.newInstance(R.string.deleting_recipes, R.string.progress_dialog_not_too_long).show(getFragmentManager(), "progress_dialog");
        new DeleteRecipesTask(this.dbConnectionManager, list).run(getRunner());
        HashMap hashMap = new HashMap();
        hashMap.put("type", "recipe");
        hashMap.put(Attributes.NUM_DELETED, Strings.toString(Integer.valueOf(list.size())));
        getAnalyticsService().reportEvent(Events.FOOD_DELETED, (Map<String, String>) hashMap);
    }

    /* access modifiers changed from: protected */
    public EditListAdapter<RecipeBoxItem> recreateAdapter() {
        return new EditableAdapter<RecipeBoxItem>(getListView(), this, getActivity()) {
            /* access modifiers changed from: protected */
            public void configureView(RecipeBoxItem recipeBoxItem, RowViewHolder rowViewHolder, int i) {
                RecipeFood recipeFood = recipeBoxItem.recipeFood(MyRecipesFragment.this.dbConnectionManager);
                rowViewHolder.title.setText(Strings.toString(recipeFood.getDescription()));
                rowViewHolder.summary.setText(NutritionUtils.getNutritionalMacrosDetails((Context) MyRecipesFragment.this.getActivity(), recipeFood.getNutritionalValues(), (double) recipeFood.servings()));
                rowViewHolder.calories.setText(Strings.toString(Integer.valueOf(RecipeImportUtils.getPerServingEnergyValue(recipeFood, (double) recipeFood.servings(), MyRecipesFragment.this.userEnergyService))));
                ViewUtils.setVisible(rowViewHolder.summary);
                ViewUtils.setVisible(rowViewHolder.calories);
            }

            /* access modifiers changed from: protected */
            public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
                MyRecipesFragment.this.getNavigationHelper().fromFragment(MyRecipesFragment.this).withAnimations(17432576, 17432577).withIntent(ResourceLoaderProxyActivity.newRecipeV2Intent(MyRecipesFragment.this.getActivity(), (RecipeBoxItem) getItem(i))).startActivity(64);
            }
        };
    }

    /* access modifiers changed from: protected */
    public List<RecipeBoxItem> getItems() {
        return this.items;
    }

    /* access modifiers changed from: protected */
    public boolean addToFilteredList(RecipeBoxItem recipeBoxItem, String str) {
        return recipeBoxItem.getFoodDescription().toLowerCase().contains(str);
    }

    /* access modifiers changed from: protected */
    public void fetchData() {
        setLoading(true);
        new LoadTask(this.dbConnectionManager, getSortOrder().queryConstant).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
    }

    /* access modifiers changed from: private */
    public void navigateToCreateRecipe() {
        ((ActionTrackingService) this.actionTrackingService.get()).registerEvent("recipe_importer", "channel", Extras.REFERRER_MYRECIPES_ACTION_BAR_ADD);
        postEvent(new CreateNewRecipeEvent(StartScreen.MealsRecipesFoods));
    }
}
