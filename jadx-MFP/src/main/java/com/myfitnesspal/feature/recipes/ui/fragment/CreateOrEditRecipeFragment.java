package com.myfitnesspal.feature.recipes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.RecipeIngredientEditorMixin;
import com.myfitnesspal.feature.recipes.model.ItemMultiSelectContext;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.task.CreateOrEditRecipeTask;
import com.myfitnesspal.feature.recipes.task.CreateOrEditRecipeTask.CompletedEvent;
import com.myfitnesspal.feature.recipes.ui.activity.SearchRecipeIngredientActivity;
import com.myfitnesspal.feature.recipes.ui.mixin.RecipeCreateEditDetailMixin;
import com.myfitnesspal.feature.recipes.ui.view.IngredientsContainer;
import com.myfitnesspal.feature.recipes.ui.view.IngredientsContainer.IngredientActionListener;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class CreateOrEditRecipeFragment extends MfpFragment {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "analytics_intent_data";
    private static final String EXTRA_DISPLAYED_RECIPE = "displayed_recipe";
    private static final String EXTRA_INGREDIENT_ITEM_MULTI_SELECT_CONTEXT = "ingredient_item_multi_select_context";
    private static final String EXTRA_MEAL_NAME = "meal_name";
    private static final String EXTRA_ORIGINAL_RECIPE = "original_recipe";
    private static final String EXTRA_STATE = "state";
    private static final int MENU_ADD_INGREDIENT = 1001;
    /* access modifiers changed from: private */
    public ActionMode actionMode;
    private MfpRecipe displayedRecipe;
    private final IngredientActionListener ingredientActionListener = new IngredientActionListener() {
        public void onItemClick(MfpIngredient mfpIngredient, View view) {
            if (CreateOrEditRecipeFragment.this.ingredientMultiSelectContext.isMultiSelectEnabled()) {
                CreateOrEditRecipeFragment.this.ingredientsContainer.toggleCheckBoxOfItem(view);
                return;
            }
            CreateOrEditRecipeFragment.this.getAnalyticsService().reportFoodLookup(CollectionUtils.nameValuePairsToMap("source", SearchSource.RECIPE_DETAILS_SCREEN.getTitle()));
            CreateOrEditRecipeFragment.this.getNavigationHelper().withIntent(FoodEditorActivity.newEditRecipeIngredientIntent((Context) CreateOrEditRecipeFragment.this.getActivity(), mfpIngredient)).fromFragment(CreateOrEditRecipeFragment.this).startActivity(RequestCodes.EDIT_RECIPE_INGREDIENT);
        }

        public void onItemCheckBoxToggled(MfpIngredient mfpIngredient) {
            CreateOrEditRecipeFragment.this.startActionMode();
            CreateOrEditRecipeFragment.this.ingredientMultiSelectContext.toggleSelectedState(mfpIngredient);
            CreateOrEditRecipeFragment.this.setActionModeTitle();
        }
    };
    /* access modifiers changed from: private */
    public ItemMultiSelectContext<MfpIngredient> ingredientMultiSelectContext;
    @BindView(2131362811)
    IngredientsContainer ingredientsContainer;
    @BindView(2131362945)
    View loadingContainer;
    @BindView(2131363093)
    EditText nameInputView;
    private OnRecipeCreatedOrUpdatedListener onRecipeCreatedOrUpdatedListener;
    private MfpRecipe originalRecipe;
    /* access modifiers changed from: private */
    public RecipeCreateEditDetailMixin recipeCreateEditDetailMixin;
    @BindView(2131363420)
    ImageView recipeImageView;
    @Inject
    Lazy<RecipeService> recipeService;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @BindView(2131362016)
    TextView saveButton;
    @BindView(2131363613)
    EditText servingsInputView;
    private final TextWatcher servingsTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.length() != 0) {
                CreateOrEditRecipeFragment.this.recipeCreateEditDetailMixin.updateServings(Double.valueOf(editable.toString()).doubleValue());
            }
        }
    };
    @Inject
    Lazy<SyncService> syncService;
    private final TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            CreateOrEditRecipeFragment.this.toggleSaveButtonEnabled();
        }
    };

    private class EditIngredientsActionMode implements Callback {
        private static final int ACTION_DELETE = 2001;

        private EditIngredientsActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            CreateOrEditRecipeFragment.this.actionMode = actionMode;
            CreateOrEditRecipeFragment.this.onActionModeEnabled(true);
            MenuItemCompat.setShowAsAction(menu.add(0, 2001, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
            CreateOrEditRecipeFragment.this.setActionModeTitle();
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            menu.findItem(2001).setVisible(CreateOrEditRecipeFragment.this.ingredientMultiSelectContext.selectedCount() > 0);
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != 2001) {
                return false;
            }
            CreateOrEditRecipeFragment.this.deleteSelectedItems();
            actionMode.finish();
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            CreateOrEditRecipeFragment.this.onActionModeEnabled(false);
            CreateOrEditRecipeFragment.this.actionMode = null;
        }
    }

    public interface OnRecipeCreatedOrUpdatedListener {
        void onRecipeCreatedOrUpdated(MfpRecipe mfpRecipe);
    }

    private enum State {
        Create,
        Edit
    }

    public static CreateOrEditRecipeFragment newInstanceForCreation(MfpRecipe mfpRecipe, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ORIGINAL_RECIPE, mfpRecipe);
        bundle.putSerializable("state", State.Create);
        bundle.putParcelable(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
        bundle.putString("meal_name", str);
        CreateOrEditRecipeFragment createOrEditRecipeFragment = new CreateOrEditRecipeFragment();
        createOrEditRecipeFragment.setArguments(bundle);
        return createOrEditRecipeFragment;
    }

    public static CreateOrEditRecipeFragment newInstanceForEdit(MfpRecipe mfpRecipe, RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ORIGINAL_RECIPE, mfpRecipe);
        bundle.putSerializable("state", State.Edit);
        bundle.putParcelable(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
        CreateOrEditRecipeFragment createOrEditRecipeFragment = new CreateOrEditRecipeFragment();
        createOrEditRecipeFragment.setArguments(bundle);
        return createOrEditRecipeFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        registerMixin(new RecipeCreateEditDetailMixin(this));
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recipe_details, viewGroup, false);
        layoutInflater.inflate(R.layout.recipe_create_or_edit_input_container, (ViewGroup) ViewUtils.findById(inflate, R.id.info_container));
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        init(bundle);
        setupListeners();
        setupView();
        if (this.ingredientMultiSelectContext.isMultiSelectEnabled()) {
            startActionMode();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        updateDisplayedRecipeWithCurrentInfo();
        bundle.putParcelable(EXTRA_DISPLAYED_RECIPE, this.displayedRecipe);
        bundle.putParcelable(EXTRA_INGREDIENT_ITEM_MULTI_SELECT_CONTEXT, this.ingredientMultiSelectContext);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.add_single_ingredient).setEnabled(!isLoading()).setIcon(R.drawable.ic_add_white_24dp), 2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        getNavigationHelper().withIntent(SearchRecipeIngredientActivity.newStartIntent(getActivity())).fromFragment(this).startActivity(RequestCodes.SEARCH_RECIPE_INGREDIENT);
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 199) {
            replaceIngredient(intent, SearchRecipeIngredientActivity.EXTRA_SELECTED_FOOD, "original_ingredient", i2);
        } else if (i != 201) {
            super.onActivityResult(i, i2, intent);
        } else {
            replaceIngredient(intent, RecipeIngredientEditorMixin.EXTRA_UPDATED_FOOD, "original_ingredient", i2);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362016})
    public void onSaveClick() {
        updateDisplayedRecipeWithCurrentInfo();
        getImmHelper().hideSoftInput();
        Bundle arguments = getArguments();
        RecipeAnalyticsIntentData recipeAnalyticsIntentData = (RecipeAnalyticsIntentData) BundleUtils.getParcelable(arguments, EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
        State state = (State) BundleUtils.getSerializable(arguments, "state", State.class.getClassLoader());
        if (state == State.Create) {
            CreateOrEditRecipeTask.newTaskForCreatingRecipe(this.recipeService, this.syncService, this.displayedRecipe).run(getRunner());
            ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeFlowCompleted(recipeAnalyticsIntentData);
        } else if (state == State.Edit) {
            if (this.displayedRecipe.equals(this.originalRecipe)) {
                this.onRecipeCreatedOrUpdatedListener.onRecipeCreatedOrUpdated(this.displayedRecipe);
                return;
            } else {
                CreateOrEditRecipeTask.newTaskForEditingRecipe(this.recipeService, this.syncService, this.displayedRecipe).run(getRunner());
                ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeFlowCompleted(recipeAnalyticsIntentData);
            }
        }
        toggleLoadingContainerVisibility();
        invalidateOptionsMenu();
    }

    @Subscribe
    public void onCreateOrEditRecipeTaskCompletedEvent(CompletedEvent completedEvent) {
        toggleLoadingContainerVisibility();
        if (completedEvent.successful()) {
            this.onRecipeCreatedOrUpdatedListener.onRecipeCreatedOrUpdated((MfpRecipe) completedEvent.getResult());
        } else {
            new SnackbarBuilder(this.ingredientsContainer).setMessage((int) R.string.unable_save_recipe_try_again).setDuration(0).show();
        }
    }

    public void setOnRecipeCreatedOrUpdatedListener(OnRecipeCreatedOrUpdatedListener onRecipeCreatedOrUpdatedListener2) {
        this.onRecipeCreatedOrUpdatedListener = onRecipeCreatedOrUpdatedListener2;
    }

    private void init(Bundle bundle) {
        this.recipeCreateEditDetailMixin = (RecipeCreateEditDetailMixin) mixin(RecipeCreateEditDetailMixin.class);
        this.displayedRecipe = (MfpRecipe) BundleUtils.getParcelable(bundle, EXTRA_DISPLAYED_RECIPE, getOriginalRecipe(), MfpRecipe.class.getClassLoader());
        this.ingredientMultiSelectContext = (ItemMultiSelectContext) BundleUtils.getParcelable(bundle, EXTRA_INGREDIENT_ITEM_MULTI_SELECT_CONTEXT, new ItemMultiSelectContext(), ItemMultiSelectContext.class.getClassLoader());
    }

    private void setupView() {
        this.nameInputView.setText(this.displayedRecipe.getName());
        this.servingsInputView.setText(Strings.toString(Integer.valueOf(this.displayedRecipe.getServings().intValue())));
        this.saveButton.setText(R.string.save);
        toggleLoadingContainerVisibility();
        toggleSaveButtonEnabled();
        setupListeners();
        this.ingredientsContainer.showIngredients(this.displayedRecipe.getIngredients(), this.ingredientMultiSelectContext);
        this.recipeCreateEditDetailMixin.renderNutritionInfo(this.displayedRecipe);
        this.recipeCreateEditDetailMixin.displayRecipeImage(this.displayedRecipe, this.recipeImageView);
    }

    /* access modifiers changed from: private */
    public void toggleSaveButtonEnabled() {
        this.saveButton.setEnabled(Strings.notEmpty(getInputText(this.nameInputView)) && NumberUtils.localeFloatFromString(getInputText(this.servingsInputView)) > BitmapDescriptorFactory.HUE_RED && CollectionUtils.notEmpty((Collection<?>) this.displayedRecipe.getIngredients()));
    }

    private void toggleLoadingContainerVisibility() {
        ViewUtils.setVisible(isLoading(), this.loadingContainer);
    }

    private void replaceIngredient(Intent intent, String str, String str2, int i) {
        int i2;
        if (i == -1) {
            MfpIngredient mfpIngredient = (MfpIngredient) ExtrasUtils.getParcelable(intent, str2, MfpIngredient.class.getClassLoader());
            MfpIngredient fromFood = MfpIngredient.fromFood((MfpFood) ExtrasUtils.getParcelable(intent, str, MfpFood.class.getClassLoader()));
            List ingredients = this.displayedRecipe.getIngredients();
            if (mfpIngredient != null) {
                i2 = ingredients.indexOf(mfpIngredient);
                if (i2 >= 0) {
                    removeIngredientAtIndex(i2);
                    addIngredientAtIndex(i2, fromFood);
                    this.recipeCreateEditDetailMixin.updateNutritionInfo(this.displayedRecipe);
                    toggleSaveButtonEnabled();
                }
            }
            i2 = 0;
            addIngredientAtIndex(i2, fromFood);
            this.recipeCreateEditDetailMixin.updateNutritionInfo(this.displayedRecipe);
            toggleSaveButtonEnabled();
        }
    }

    private void updateDisplayedRecipeWithCurrentInfo() {
        this.displayedRecipe.setName(getInputText(this.nameInputView));
        this.displayedRecipe.setServings(Double.valueOf(NumberUtils.tryParseDouble(getInputText(this.servingsInputView))));
    }

    private void setupListeners() {
        this.ingredientsContainer.setIngredientActionListener(this.ingredientActionListener);
        this.nameInputView.addTextChangedListener(this.textWatcher);
        this.servingsInputView.addTextChangedListener(this.textWatcher);
        this.servingsInputView.addTextChangedListener(this.servingsTextWatcher);
    }

    private void removeIngredientAtIndex(int i) {
        this.displayedRecipe.getIngredients().remove(i);
        this.ingredientsContainer.removeIngredientAt(i);
    }

    private void addIngredientAtIndex(int i, MfpIngredient mfpIngredient) {
        this.displayedRecipe.getIngredients().add(i, mfpIngredient);
        this.ingredientsContainer.addIngredientAt(mfpIngredient, this.ingredientMultiSelectContext, i);
    }

    private String getInputText(EditText editText) {
        return editText.getText().toString().trim();
    }

    /* access modifiers changed from: private */
    public void startActionMode() {
        if (this.actionMode == null) {
            getImmHelper().hideSoftInput();
            getActivity().startActionMode(new EditIngredientsActionMode());
        }
    }

    /* access modifiers changed from: private */
    public void onActionModeEnabled(boolean z) {
        this.ingredientMultiSelectContext.setMultiSelectEnabled(z);
        ViewUtils.setVisible(!z, this.saveButton);
        if (!z) {
            toggleSaveButtonEnabled();
            this.ingredientMultiSelectContext.clear();
            this.ingredientsContainer.uncheckAllItems();
        }
    }

    /* access modifiers changed from: private */
    public void setActionModeTitle() {
        if (this.actionMode != null) {
            int selectedCount = this.ingredientMultiSelectContext.selectedCount();
            this.actionMode.setTitle(getString(selectedCount == 0 ? R.string.select_item : R.string.number_selected, Integer.valueOf(selectedCount)));
            this.actionMode.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void deleteSelectedItems() {
        int selectedCount = this.ingredientMultiSelectContext.selectedCount();
        if (selectedCount != 0) {
            new SnackbarBuilder(this.ingredientsContainer).setMessage(getResources().getQuantityString(R.plurals.ingredients_deleted_plural, selectedCount)).build().show();
            List ingredients = this.displayedRecipe.getIngredients();
            ingredients.removeAll(this.ingredientMultiSelectContext.getItemSet());
            this.ingredientsContainer.showIngredients(ingredients, this.ingredientMultiSelectContext);
            this.displayedRecipe.setIngredients(ingredients);
            this.displayedRecipe.setNutritionalContents(MfpNutritionalContents.fromIngredientList(ingredients));
            setupView();
            toggleSaveButtonEnabled();
        }
    }

    public boolean isLoading() {
        return getRunner().running(CreateOrEditRecipeTask.NAME);
    }

    /* access modifiers changed from: protected */
    public MfpRecipe getOriginalRecipe() {
        if (this.originalRecipe == null) {
            this.originalRecipe = (MfpRecipe) BundleUtils.getParcelable(getArguments(), EXTRA_ORIGINAL_RECIPE, MfpRecipe.class.getClassLoader());
        }
        return (MfpRecipe) ParcelableUtil.clone(this.originalRecipe, MfpRecipe.CREATOR);
    }
}
