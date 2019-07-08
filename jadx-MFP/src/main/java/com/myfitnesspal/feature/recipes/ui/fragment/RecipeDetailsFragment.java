package com.myfitnesspal.feature.recipes.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.task.DeleteRecipesTask;
import com.myfitnesspal.feature.recipes.task.GetFoodFromRecipeTask;
import com.myfitnesspal.feature.recipes.task.GetFoodFromRecipeTask.CompletedEvent;
import com.myfitnesspal.feature.recipes.ui.mixin.RecipeCreateEditDetailMixin;
import com.myfitnesspal.feature.recipes.ui.view.IngredientsContainer;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class RecipeDetailsFragment extends MfpFragment {
    private static final String EXTRA_MEAL_NAME = "meal_name";
    private static final String EXTRA_RECIPE = "recipe";
    private static final int MENU_DELETE_RECIPE = 1002;
    private static final int MENU_EDIT_RECIPE = 1001;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @BindView(2131362016)
    TextView addToDiaryButton;
    @BindView(2131362071)
    TextView caloriesPerServingTextView;
    private MfpRecipe displayedRecipe;
    @BindView(2131362811)
    IngredientsContainer ingredientsContainer;
    @BindView(2131363095)
    TextView nameTextView;
    @BindView(2131363188)
    TextView numServingsTextView;
    private OnRecipeActionListener onRecipeActionListener;
    private RecipeCreateEditDetailMixin recipeCreateEditDetailMixin;
    @BindView(2131363420)
    ImageView recipeImageView;
    @Inject
    Lazy<RecipeService> recipeService;
    @BindView(2131363684)
    TextView sourceTextView;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public interface OnRecipeActionListener {
        void onEditRecipeTapped();

        void onRecipeDeleted();
    }

    public static RecipeDetailsFragment newInstance(MfpRecipe mfpRecipe, String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", mfpRecipe);
        bundle.putString("meal_name", str);
        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        recipeDetailsFragment.setArguments(bundle);
        return recipeDetailsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        registerMixin(new RecipeCreateEditDetailMixin(this));
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recipe_details, viewGroup, false);
        layoutInflater.inflate(R.layout.recipe_details_info_container, (ViewGroup) ViewUtils.findById(inflate, R.id.info_container));
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        init();
        setHasOptionsMenu(true);
        setupView();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.edit_recipe), 0);
        MenuItemCompat.setShowAsAction(menu.add(0, 1002, 0, R.string.delete_recipe), 0);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1001:
                this.onRecipeActionListener.onEditRecipeTapped();
                return true;
            case 1002:
                new DeleteRecipesTask(this.recipeService, this.displayedRecipe).run(getRunner());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362016})
    public void onAddToDiaryClick() {
        new GetFoodFromRecipeTask(this.recipeService, this.displayedRecipe).run(getRunner());
    }

    @Subscribe
    public void onGetFoodFromRecipeTaskCompletedEvent(CompletedEvent completedEvent) {
        FoodEditorExtras screenTitle = new FoodEditorExtras().setActionType(ActionType.Create).setSupportPairedFoods(false).setForRecipe(true).setScreenTitle(getString(R.string.add_recipe));
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("recipe_importer", MapUtil.createMap("source", StartScreen.RecipeDetailsScreen.getAnalyticsAttributeValue(), Attributes.RECIPE_COUNT, Integer.toString(1)));
        startActivity(FoodEditorActivity.newDiaryFoodItemEditorIntent(getActivity(), Diary.newStartIntent(getActivity()), (MfpFood) completedEvent.getResult(), null, getArguments().getString("meal_name"), null, null, null, false, screenTitle));
    }

    @Subscribe
    public void onDeleteRecipesTaskCompletedEvent(DeleteRecipesTask.CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            this.onRecipeActionListener.onRecipeDeleted();
            return;
        }
        new SnackbarBuilder(this.ingredientsContainer).setMessage(getResources().getQuantityString(R.plurals.recipe_deleted_fail, 1, new Object[]{Integer.valueOf(1)})).setDuration(-1).show();
    }

    public void setOnRecipeActionListener(OnRecipeActionListener onRecipeActionListener2) {
        this.onRecipeActionListener = onRecipeActionListener2;
    }

    private void init() {
        this.recipeCreateEditDetailMixin = (RecipeCreateEditDetailMixin) mixin(RecipeCreateEditDetailMixin.class);
        this.displayedRecipe = (MfpRecipe) BundleUtils.getParcelable(getArguments(), "recipe", MfpRecipe.class.getClassLoader());
    }

    private void setupView() {
        double doubleValue = this.displayedRecipe.getServings().doubleValue();
        this.nameTextView.setText(this.displayedRecipe.getName());
        this.numServingsTextView.setText(getString(R.string.num_servings, NumberUtils.localeStringFromDouble(doubleValue, 2)));
        this.addToDiaryButton.setText(R.string.addToDiaryBtn);
        setupEnergyPerServingText();
        this.ingredientsContainer.showIngredients(this.displayedRecipe.getIngredients());
        this.recipeCreateEditDetailMixin.renderNutritionInfo(this.displayedRecipe);
        this.recipeCreateEditDetailMixin.displayRecipeImage(this.displayedRecipe, this.recipeImageView);
        setupSourceText();
    }

    private void setupSourceText() {
        final String sourceUrl = this.displayedRecipe.getSourceUrl();
        boolean notEmpty = Strings.notEmpty(sourceUrl);
        ViewUtils.setVisible(notEmpty, this.sourceTextView);
        if (notEmpty) {
            String authority = Uri.parse(sourceUrl).getAuthority();
            this.sourceTextView.setText(getString(R.string.view_directions_on, authority));
            this.sourceTextView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    RecipeDetailsFragment.this.getNavigationHelper().withIntent(FullScreenWebView.newStartIntent(RecipeDetailsFragment.this.getActivity(), new FullScreenWebViewIntentExtras().setTitle(RecipeDetailsFragment.this.getString(R.string.directions)).setUrl(sourceUrl).setShowCloseAsBackButton(true))).startActivity();
                }
            });
        }
    }

    private void setupEnergyPerServingText() {
        this.caloriesPerServingTextView.setText(getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.calories_per_servings : R.string.kj_per_servings, LocalizedEnergy.getRoundedDisplayStringWithoutUnit(getActivity(), LocalizedEnergy.fromCalories(this.displayedRecipe.getNutritionalContents().getCalories().doubleValue() / this.displayedRecipe.getServings().doubleValue()), ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit())));
    }
}
