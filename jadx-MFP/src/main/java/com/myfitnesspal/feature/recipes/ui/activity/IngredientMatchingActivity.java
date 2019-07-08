package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.RecipeIngredientEditorMixin;
import com.myfitnesspal.feature.recipes.model.CreateRecipeIntentData;
import com.myfitnesspal.feature.recipes.model.IngredientMatchingAdapterItem;
import com.myfitnesspal.feature.recipes.model.ItemMultiSelectContext;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.ui.adapter.IngredientMatchingAdapter;
import com.myfitnesspal.feature.recipes.ui.adapter.IngredientMatchingAdapter.IngredientItemClickListener;
import com.myfitnesspal.feature.recipes.ui.viewmodel.IngredientsMatchingViewModel;
import com.myfitnesspal.feature.recipes.ui.viewmodel.IngredientsMatchingViewModel.Property;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class IngredientMatchingActivity extends MfpActivity {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "analytics_intent_data";
    private static final String EXTRA_CREATE_RECIPE_DATA = "create_recipe_data";
    private static final String EXTRA_INGREDIENT_ITEMS = "ingredient_items";
    private static final String EXTRA_INGREDIENT_MULTI_SELECT_CONTAINER = "ingredient_multi_select_container";
    private static final String EXTRA_MEAL_NAME = "meal_name";
    private static final int MENU_ADD = 1001;
    private static final int MENU_EDIT = 1002;
    private static final String TAG_DISCARD_UNMATCHED_INGREDIENTS_DIALOG = "discard_unmatched_ingredients_dialog";
    private ViewState currentViewState;
    private final DialogPositiveListener discardUnmatchedIngredientsDialogPositiveListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            IngredientMatchingActivity.this.reportSummaryAndNavigateToEditRecipe();
        }
    };
    /* access modifiers changed from: private */
    public ActionMode editActionMode;
    @BindView(2131362461)
    EmptyStateView emptyStateView;
    /* access modifiers changed from: private */
    public ItemMultiSelectContext<MfpIngredientItem> ingredientItemMultiSelectContext;
    /* access modifiers changed from: private */
    public IngredientMatchingAdapter ingredientMatchingAdapter;
    @BindView(2131362810)
    RecyclerView ingredientsRecyclerView;
    private final IngredientItemClickListener itemClickListener = new IngredientItemClickListener() {
        public void onItemClick(MfpIngredientItem mfpIngredientItem, int i) {
            if (IngredientMatchingActivity.this.ingredientItemMultiSelectContext.isMultiSelectEnabled()) {
                IngredientMatchingActivity.this.ingredientItemMultiSelectContext.toggleSelectedState(mfpIngredientItem);
                IngredientMatchingActivity.this.ingredientMatchingAdapter.notifyItemChanged(i);
                IngredientMatchingActivity.this.setActionModeTitle();
            } else if (!mfpIngredientItem.hasMatches()) {
                IngredientMatchingActivity ingredientMatchingActivity = IngredientMatchingActivity.this;
                ingredientMatchingActivity.startSearchActivity(SearchRecipeIngredientActivity.newStartIntentWithIngredientItem(ingredientMatchingActivity, mfpIngredientItem));
            } else {
                IngredientMatchingActivity.this.getAnalyticsService().reportFoodLookup(CollectionUtils.nameValuePairsToMap("source", SearchSource.RECIPE_PARSER.getTitle()));
                IngredientMatchingActivity.this.getNavigationHelper().withIntent(FoodEditorActivity.newEditRecipeIngredientIntent((Context) IngredientMatchingActivity.this, mfpIngredientItem)).startActivity(RequestCodes.EDIT_RECIPE_INGREDIENT);
            }
        }
    };
    @BindView(2131362943)
    View loadingView;
    @BindView(2131362020)
    View nextButton;
    @Inject
    Lazy<RecipeService> recipeService;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    private IngredientsMatchingViewModel viewModel;

    private class EditActionMode implements Callback {
        private static final int ACTION_DELETE = 2001;

        private EditActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            IngredientMatchingActivity.this.editActionMode = actionMode;
            IngredientMatchingActivity.this.onActionModeEnabled(true);
            MenuItemCompat.setShowAsAction(menu.add(0, 2001, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
            IngredientMatchingActivity.this.setActionModeTitle();
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            menu.findItem(2001).setVisible(IngredientMatchingActivity.this.ingredientItemMultiSelectContext.selectedCount() > 0);
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != 2001) {
                return false;
            }
            IngredientMatchingActivity.this.deleteSelectedItems();
            actionMode.finish();
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            IngredientMatchingActivity.this.editActionMode = null;
            IngredientMatchingActivity.this.onActionModeEnabled(false);
        }
    }

    private enum ViewState {
        DisplayIngredients,
        Loading,
        Empty
    }

    public static Intent newStartIntent(Context context, CreateRecipeIntentData createRecipeIntentData, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        return new Intent(context, IngredientMatchingActivity.class).putExtra(EXTRA_CREATE_RECIPE_DATA, createRecipeIntentData).putExtra("meal_name", str).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.ingredient_matching);
        initIngredientMultiSelectContext(bundle);
        initRecyclerViewAndAdapter();
        initViewModel(bundle);
        setupEmptyView();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.currentViewState == ViewState.DisplayIngredients) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.add_single_ingredient).setIcon(R.drawable.ic_add_white_24dp), 2);
            MenuItemCompat.setShowAsAction(menu.add(0, 1002, 0, R.string.edit).setIcon(R.drawable.ic_edit_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1001:
                startSearchActivity(SearchRecipeIngredientActivity.newStartIntent(this));
                return true;
            case 1002:
                initActionMode();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 199) {
            if (i == 201) {
                processActivityResultForEditingOrAddingIngredient(intent, RecipeIngredientEditorMixin.EXTRA_UPDATED_FOOD, "original_ingredient_item", i2);
            }
            super.onActivityResult(i, i2, intent);
            return;
        }
        processActivityResultForEditingOrAddingIngredient(intent, SearchRecipeIngredientActivity.EXTRA_SELECTED_FOOD, "original_ingredient_item", i2);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(TAG_DISCARD_UNMATCHED_INGREDIENTS_DIALOG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.discardUnmatchedIngredientsDialogPositiveListener);
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        List ingredientItems = this.viewModel.getIngredientItems();
        if (CollectionUtils.notEmpty((Collection<?>) ingredientItems)) {
            bundle.putParcelableArrayList(EXTRA_INGREDIENT_ITEMS, new ArrayList(ingredientItems));
        }
        bundle.putParcelable(EXTRA_INGREDIENT_MULTI_SELECT_CONTAINER, this.ingredientItemMultiSelectContext);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.INGREDIENTS_MATCHED) {
            displayItems();
        } else if (i == Property.INGREDIENTS_MATCH_FAILED) {
            setViewState(ViewState.Empty);
        } else if (i == Property.INGREDIENTS_MATCH_FAILED_NETWORK_ERROR) {
            setViewState(ViewState.Empty);
            new SnackbarBuilder(this.ingredientsRecyclerView).setMessage((int) R.string.no_network_error).setDuration(0).show();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362020})
    public void onNextClick() {
        if (this.viewModel.hasUnmatchedIngredients()) {
            showDiscardUnmatchedIngredientsDialog();
        } else {
            reportSummaryAndNavigateToEditRecipe();
        }
    }

    private void processActivityResultForEditingOrAddingIngredient(Intent intent, String str, String str2, int i) {
        if (i == -1) {
            this.viewModel.replaceOrAddIngredient((MfpFood) ExtrasUtils.getParcelable(intent, str, MfpFood.class.getClassLoader()), (MfpIngredientItem) ExtrasUtils.getParcelable(intent, str2, MfpIngredientItem.class.getClassLoader()));
        }
    }

    private void initIngredientMultiSelectContext(Bundle bundle) {
        this.ingredientItemMultiSelectContext = (ItemMultiSelectContext) BundleUtils.getParcelable(bundle, EXTRA_INGREDIENT_MULTI_SELECT_CONTAINER, new ItemMultiSelectContext(), ItemMultiSelectContext.class.getClassLoader());
        if (this.ingredientItemMultiSelectContext.isMultiSelectEnabled()) {
            initActionMode();
        }
    }

    private void initRecyclerViewAndAdapter() {
        this.ingredientMatchingAdapter = new IngredientMatchingAdapter(this.userEnergyService, this.itemClickListener, this.ingredientItemMultiSelectContext, getRecipeIntentData().isImportingIngredients());
        this.ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.ingredientsRecyclerView.setHasFixedSize(true);
        this.ingredientsRecyclerView.setAdapter(this.ingredientMatchingAdapter);
    }

    private void initViewModel(Bundle bundle) {
        this.viewModel = (IngredientsMatchingViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (IngredientsMatchingViewModel) setViewModel(new IngredientsMatchingViewModel(getRunner(), this.recipeService));
        }
        setupCurrentView(bundle);
    }

    private void setupCurrentView(Bundle bundle) {
        if (CollectionUtils.isEmpty((Collection<?>) this.viewModel.getIngredientItems())) {
            ArrayList parcelableArrayList = BundleUtils.getParcelableArrayList(bundle, EXTRA_INGREDIENT_ITEMS, MfpIngredientItem.class.getClassLoader());
            if (CollectionUtils.notEmpty((Collection<?>) parcelableArrayList)) {
                this.viewModel.setIngredientItems(parcelableArrayList);
                displayItems();
                return;
            }
            CreateRecipeIntentData recipeIntentData = getRecipeIntentData();
            String ingredientsString = recipeIntentData.getIngredientsString();
            if (Strings.notEmpty(ingredientsString)) {
                this.viewModel.load(ingredientsString, recipeIntentData.getHash());
                setViewState(ViewState.Loading);
                return;
            }
            setViewState(ViewState.Empty);
            return;
        }
        displayItems();
    }

    private void initActionMode() {
        if (this.editActionMode == null) {
            startActionMode(new EditActionMode());
        }
    }

    /* access modifiers changed from: private */
    public void onActionModeEnabled(boolean z) {
        this.ingredientItemMultiSelectContext.setMultiSelectEnabled(z);
        if (!z) {
            this.ingredientItemMultiSelectContext.clear();
        }
        IngredientMatchingAdapter ingredientMatchingAdapter2 = this.ingredientMatchingAdapter;
        if (ingredientMatchingAdapter2 != null) {
            ingredientMatchingAdapter2.notifyDataSetChanged();
            if (!z) {
                this.ingredientMatchingAdapter.addOverviewItem(this.viewModel.getOverviewItem(getServings()));
            } else {
                this.ingredientMatchingAdapter.removeOverviewItem();
            }
        }
        setupNextButtonVisibility();
    }

    private void setupEmptyView() {
        this.emptyStateView.initializeForType(Type.RecipeIngredients, new OnClickListener() {
            public void onClick(View view) {
                IngredientMatchingActivity ingredientMatchingActivity = IngredientMatchingActivity.this;
                ingredientMatchingActivity.startSearchActivity(SearchRecipeIngredientActivity.newStartIntent(ingredientMatchingActivity));
            }
        });
    }

    private void displayItems() {
        setViewState(ViewState.DisplayIngredients);
        this.ingredientMatchingAdapter.setItems(getAdapterItems(this.viewModel.getIngredientItems()));
    }

    private List<IngredientMatchingAdapterItem> getAdapterItems(List<MfpIngredientItem> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MfpIngredientItem mfpIngredientItem : list) {
            if (mfpIngredientItem.hasMatches()) {
                arrayList2.add(mfpIngredientItem);
            } else {
                arrayList.add(mfpIngredientItem);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        if (!this.ingredientItemMultiSelectContext.isMultiSelectEnabled()) {
            arrayList3.add(this.viewModel.getOverviewItem(getServings()));
        }
        View view = this.nextButton;
        boolean z = true;
        if (arrayList2.size() < 1) {
            z = false;
        }
        view.setEnabled(z);
        arrayList3.addAll(arrayList);
        arrayList3.addAll(arrayList2);
        return arrayList3;
    }

    private void setViewState(ViewState viewState) {
        boolean z;
        boolean z2;
        boolean z3;
        this.currentViewState = viewState;
        switch (viewState) {
            case DisplayIngredients:
                z = true;
                z3 = false;
                z2 = false;
                break;
            case Empty:
                z = false;
                z3 = true;
                z2 = false;
                break;
            case Loading:
                z = false;
                z3 = false;
                z2 = true;
                break;
            default:
                z = false;
                z3 = false;
                z2 = false;
                break;
        }
        ViewUtils.setVisible(z, this.ingredientsRecyclerView, this.nextButton);
        ViewUtils.setVisible(z3, this.emptyStateView);
        ViewUtils.setVisible(z2, this.loadingView);
        setupNextButtonVisibility();
        invalidateOptionsMenu();
    }

    private void setupNextButtonVisibility() {
        ViewUtils.setVisible(this.editActionMode == null && this.currentViewState == ViewState.DisplayIngredients, this.nextButton);
    }

    /* access modifiers changed from: private */
    public void reportSummaryAndNavigateToEditRecipe() {
        reportSummary();
        getNavigationHelper().withIntent(RecipeDetailsActivity.newStartIntentForCreatingRecipe(this, createRecipeFromCurrentData(), getAnalyticsIntentData(), getIntent().getStringExtra("meal_name"))).startActivity();
    }

    private void reportSummary() {
        CreateRecipeIntentData recipeIntentData = getRecipeIntentData();
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportIngredientMatchingSummary(this.viewModel.getIngredientItems(), getAnalyticsIntentData(), recipeIntentData.getName(), recipeIntentData.getSourceUrl(), this.viewModel.getEditedIngredientCount(), this.viewModel.getDeletedIngredientsCount());
    }

    private RecipeAnalyticsIntentData getAnalyticsIntentData() {
        return (RecipeAnalyticsIntentData) ExtrasUtils.getParcelable(getIntent(), EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
    }

    private MfpRecipe createRecipeFromCurrentData() {
        CreateRecipeIntentData recipeIntentData = getRecipeIntentData();
        String name = recipeIntentData.getName();
        double servings = getServings();
        List<MfpIngredientItem> ingredientItems = this.viewModel.getIngredientItems();
        ArrayList arrayList = new ArrayList();
        for (MfpIngredientItem mfpIngredientItem : ingredientItems) {
            if (mfpIngredientItem.hasMatches()) {
                arrayList.add(mfpIngredientItem.getIngredientForRecipeCreation(servings));
            }
        }
        MfpNutritionalContents fromIngredientList = MfpNutritionalContents.fromIngredientList(arrayList);
        MfpRecipe mfpRecipe = new MfpRecipe();
        mfpRecipe.setName(name);
        mfpRecipe.setServings(Double.valueOf(servings));
        mfpRecipe.setIngredients(arrayList);
        mfpRecipe.setNutritionalContents(fromIngredientList);
        mfpRecipe.setSourceUrl(recipeIntentData.getSourceUrl());
        if (Strings.notEmpty(recipeIntentData.getPictureUrl())) {
            mfpRecipe.setSourceImageUrls(Collections.singletonList(recipeIntentData.getPictureUrl()));
        }
        mfpRecipe.setHash(recipeIntentData.getHash());
        return mfpRecipe;
    }

    /* access modifiers changed from: private */
    public void deleteSelectedItems() {
        int selectedCount = this.ingredientItemMultiSelectContext.selectedCount();
        if (selectedCount != 0) {
            this.viewModel.deleteSelectedIngredients(this.ingredientItemMultiSelectContext);
            if (this.viewModel.getIngredientItems().isEmpty()) {
                setViewState(ViewState.Empty);
            } else {
                this.ingredientMatchingAdapter.setItems(getAdapterItems(this.viewModel.getIngredientItems()));
            }
            new SnackbarBuilder(this.ingredientsRecyclerView).setMessage(getResources().getQuantityString(R.plurals.ingredients_deleted_plural, selectedCount)).build().show();
        }
    }

    /* access modifiers changed from: private */
    public void setActionModeTitle() {
        if (this.editActionMode != null) {
            int selectedCount = this.ingredientItemMultiSelectContext.selectedCount();
            this.editActionMode.setTitle(getString(selectedCount == 0 ? R.string.select_item : R.string.number_selected, new Object[]{Integer.valueOf(selectedCount)}));
            this.editActionMode.invalidate();
        }
    }

    private void showDiscardUnmatchedIngredientsDialog() {
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.continue_confirm)).setMessage((int) R.string.unmatched_ingredient_discard)).setPositiveText(R.string.ok, this.discardUnmatchedIngredientsDialogPositiveListener)).setNegativeText(R.string.cancel, null), TAG_DISCARD_UNMATCHED_INGREDIENTS_DIALOG);
    }

    private CreateRecipeIntentData getRecipeIntentData() {
        return (CreateRecipeIntentData) ExtrasUtils.getParcelable(getIntent(), EXTRA_CREATE_RECIPE_DATA, CreateRecipeIntentData.class.getClassLoader());
    }

    private double getServings() {
        return getRecipeIntentData().getServings();
    }

    /* access modifiers changed from: private */
    public void startSearchActivity(Intent intent) {
        getNavigationHelper().withIntent(intent).startActivity(RequestCodes.SEARCH_RECIPE_INGREDIENT);
    }
}
