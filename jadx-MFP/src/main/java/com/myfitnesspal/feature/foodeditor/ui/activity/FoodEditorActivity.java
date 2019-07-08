package com.myfitnesspal.feature.foodeditor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.BarcodeMultiAddMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.DeprecatedRecipeIngredientEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.MenuItemEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.MultiAddFoodEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.RecipeIngredientEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.SponsoredFoodMixin;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.ui.mixin.ImportImageMixin;
import com.myfitnesspal.feature.meals.model.MealIngredientEditorBundleData;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.meals.ui.mixin.MealIngredientMixin;
import com.myfitnesspal.feature.meals.ui.mixin.SharedMealViewerMixin;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods.TabType;
import com.myfitnesspal.feature.restaurantlogging.model.MenuItemEditorBundleData;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import java.util.Date;
import java.util.Map;
import javax.inject.Inject;

public class FoodEditorActivity extends MfpActivity {
    public static final String EXTRA_ACTIVITY_TO_START = "activity_to_start";
    public static final String EXTRA_FOOD_EDITOR_TYPE = "food_editor_type";
    public static final String EXTRA_SPONSORED_FOOD_AD = "sponosred_food_ad";
    private EditorMixinBase editorMixin;
    @Inject
    Lazy<ImageAssociationService> imageAssociationService;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private OnItemSavedListener onItemSavedListener = new OnItemSavedListener() {
        public void onItemSaveFailed(int i, Bundle bundle) {
            FoodEditorActivity.this.setResult(i, bundle != null ? new Intent().putExtras(bundle) : null);
            FoodEditorActivity.this.finish();
        }

        public void onItemSaved(int i, Bundle bundle) {
            onItemSaved(i, bundle, null, false);
        }

        public void onItemSavedIgnoreStartIntent(int i, Bundle bundle) {
            onItemSaved(i, bundle, null, true);
        }

        public void onItemSavedOverrideStartIntent(int i, Bundle bundle, Intent intent) {
            onItemSaved(i, bundle, intent, false);
        }

        private void onItemSaved(int i, Bundle bundle, Intent intent, boolean z) {
            if (!z) {
                if (intent == null) {
                    intent = (Intent) FoodEditorActivity.this.getIntent().getParcelableExtra(FoodEditorActivity.EXTRA_ACTIVITY_TO_START);
                }
                if (intent != null) {
                    if (bundle != null) {
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            extras.putAll(bundle);
                        } else {
                            extras = bundle;
                        }
                        intent.putExtras(extras);
                    }
                    FoodEditorActivity.this.getNavigationHelper().withIntent(intent).withClearTopAndNewTask().startActivity();
                }
            }
            FoodEditorActivity.this.setResult(i, bundle == null ? new Intent() : new Intent().putExtras(bundle));
            FoodEditorActivity.this.finish();
        }
    };
    @BindView(2131363239)
    ViewGroup viewContainer;

    public static Intent newRestaurantMenuItemEditorIntent(Context context, MenuItemEditorBundleData menuItemEditorBundleData, boolean z, boolean z2) {
        Intent intent = new Intent(context, FoodEditorActivity.class);
        intent.putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.RestaurantMenuItem);
        intent.putExtra(MenuItemEditorMixin.EXTRA_FOOD_EDITOR_ITEM_METADATA, menuItemEditorBundleData);
        intent.putExtra("extra_date", menuItemEditorBundleData.getDate());
        intent.putExtra(FoodEditorMixinBase.EXTRA_MEAL_NAME, menuItemEditorBundleData.getMealName());
        intent.putExtra(MenuItemEditorMixin.EXTRA_RETURN_MENU_ITEM_RESULT, z);
        intent.putExtra("source", SearchSource.RESTAURANT_LOGGING);
        intent.putExtra(MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW, z2);
        return intent;
    }

    public static Intent newDiaryFoodItemEditorIntent(Context context, Intent intent, MfpFood mfpFood, Date date, String str, String str2, SearchSource searchSource, String str3) {
        return newDiaryFoodItemEditorIntent(context, intent, mfpFood, date, str, str2, searchSource, str3, false, new FoodEditorExtras());
    }

    public static Intent newDiaryFoodItemEditorIntent(Context context, Intent intent, MfpFood mfpFood, Date date, String str, String str2, SearchSource searchSource, String str3, boolean z, FoodEditorExtras foodEditorExtras) {
        Intent intent2 = new Intent(context, FoodEditorActivity.class);
        intent2.putExtra(EXTRA_FOOD_EDITOR_TYPE, foodEditorExtras.isMultiAddOn() ? FoodEditorType.MultiAddFood : FoodEditorType.DiaryFood);
        intent2.putExtra(EXTRA_ACTIVITY_TO_START, intent);
        intent2.putExtra("extra_food", mfpFood);
        intent2.putExtra("extra_date", date);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_MEAL_NAME, str);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_BARCODE, str2);
        intent2.putExtra("source", searchSource);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_REFERRER, str3);
        intent2.putExtra(MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW, z);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_EDITOR_EXTRAS, foodEditorExtras);
        return intent2;
    }

    public static Intent newSponsoredFoodIntent(Context context, Intent intent, MfpFood mfpFood, Date date, String str, SearchSource searchSource, String str2, boolean z, FoodEditorExtras foodEditorExtras, SponsoredFoodSearchAd sponsoredFoodSearchAd) {
        Intent intent2 = new Intent(context, FoodEditorActivity.class);
        intent2.putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.SponsoredFood);
        intent2.putExtra(EXTRA_ACTIVITY_TO_START, intent);
        intent2.putExtra("extra_food", mfpFood);
        intent2.putExtra("extra_date", date);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_MEAL_NAME, str);
        intent2.putExtra("source", searchSource);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_REFERRER, str2);
        intent2.putExtra(MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW, z);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_EDITOR_EXTRAS, foodEditorExtras);
        intent2.putExtra(EXTRA_SPONSORED_FOOD_AD, sponsoredFoodSearchAd);
        return intent2;
    }

    public static Intent newBarcodeMultiAddFoodItemEditorIntent(Context context, Intent intent, MfpFood mfpFood, Date date, String str, String str2, String str3) {
        Intent intent2 = new Intent(context, FoodEditorActivity.class);
        intent2.putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.BarcodeMultiAddFood);
        intent2.putExtra(EXTRA_ACTIVITY_TO_START, intent);
        intent2.putExtra("extra_food", mfpFood);
        intent2.putExtra("extra_date", date);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_MEAL_NAME, str);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_BARCODE, str2);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_REFERRER, str3);
        return intent2;
    }

    public static Intent newMealItemEditorIntent(Context context) {
        return newMealItemEditorIntent(context, null, null, null, null);
    }

    public static Intent newMealItemEditorIntent(Context context, Intent intent, String str, MealFood mealFood, String str2) {
        if (intent == null) {
            intent = Diary.newStartIntent(context);
        }
        intent.addFlags(603979776);
        Intent intent2 = new Intent(context, FoodEditorActivity.class);
        intent2.putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.Meal);
        intent2.putExtra(FoodEditorMixinBase.EXTRA_MEAL_NAME, str);
        intent2.putExtra("extra_food", mealFood);
        intent2.putExtra(EXTRA_ACTIVITY_TO_START, intent);
        intent2.putExtra("referrer", str2);
        return intent2;
    }

    public static Intent newMealIngredientEditorIntent(Context context, Intent intent, MealIngredientEditorBundleData mealIngredientEditorBundleData) {
        Intent intent2 = new Intent(context, FoodEditorActivity.class);
        intent2.putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.MealIngredient);
        intent2.putExtra(EXTRA_ACTIVITY_TO_START, intent);
        intent2.putExtra("extra_food", mealIngredientEditorBundleData.getFood());
        intent2.putExtra("extra_date", mealIngredientEditorBundleData.getDate());
        intent2.putExtra(FoodEditorMixinBase.EXTRA_MEAL_NAME, mealIngredientEditorBundleData.getMealName());
        intent2.putExtra(FoodEditorMixinBase.EXTRA_BARCODE, mealIngredientEditorBundleData.getBarcode());
        intent2.putExtra(FoodEditorMixinBase.EXTRA_REFERRER, mealIngredientEditorBundleData.getReferrer());
        intent2.putExtra(FoodEditorMixinBase.EXTRA_MEAL_INGREDIENT_INDEX, mealIngredientEditorBundleData.getIndex());
        intent2.putExtra(FoodEditorMixinBase.EXTRA_IS_EDITING_MEAL_INGREDIENT, mealIngredientEditorBundleData.isEditingMealIngredient());
        return intent2;
    }

    public static Intent newViewSharedMealIntent(Context context, String str, String str2, String str3, String str4, boolean z) {
        return newViewSharedMealIntent(context, str2, str3, str4, z).putExtra("food_id", str);
    }

    private static Intent newViewSharedMealIntent(Context context, String str, String str2, String str3, boolean z) {
        return new Intent(context, FoodEditorActivity.class).putExtra(SharedMealViewerMixin.EXTRA_MEAL_IMAGE_ID, str).putExtra(SharedMealViewerMixin.EXTRA_IS_CURRENT_USERS_MEAL, z).putExtra(SharedMealViewerMixin.EXTRA_MEAL_OWNER_USERNAME, str2).putExtra(SharedMealViewerMixin.EXTRA_MEAL_OWNER_UID, str3).putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.ViewSharedMeal).putExtra(EXTRA_ACTIVITY_TO_START, RecipesAndFoods.newStartIntent(context, TabType.Meals));
    }

    public static Intent newEditRecipeIngredientIntent(Context context, MfpIngredientItem mfpIngredientItem) {
        return newDiaryFoodItemEditorIntent(context, null, mfpIngredientItem.getPrimaryMatch().getFood(), new Date(), MealTypeName.BREAKFAST, null, null, null).putExtra("original_ingredient_item", mfpIngredientItem).putExtra(RecipeIngredientEditorMixin.EXTRA_SHOW_REPLACE_BUTTON, true).putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.RecipeIngredient);
    }

    public static Intent newEditRecipeIngredientIntent(Context context, MfpIngredient mfpIngredient) {
        return newDiaryFoodItemEditorIntent(context, null, mfpIngredient.getFood(), new Date(), MealTypeName.BREAKFAST, null, null, null).putExtra("original_ingredient", mfpIngredient).putExtra(RecipeIngredientEditorMixin.EXTRA_SHOW_REPLACE_BUTTON, true).putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.RecipeIngredient);
    }

    public static Intent newSelectRecipeIngredientIntent(Context context, MfpFood mfpFood) {
        return newDiaryFoodItemEditorIntent(context, null, mfpFood, new Date(), MealTypeName.BREAKFAST, null, null, null).putExtra(EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.RecipeIngredient);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        registerMixin(new ImportImageMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.food_editor);
        initMixin(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.editorMixin.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.editorMixin.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.editorMixin.onPause();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (super.onRebindDialogFragment(dialogFragment, str)) {
            return true;
        }
        return this.editorMixin.onRebindDialogFragment(dialogFragment, str);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        EditorMixinBase editorMixinBase = this.editorMixin;
        if (editorMixinBase == null || !editorMixinBase.processActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isBusy(1)) {
            return true;
        }
        menu.clear();
        return this.editorMixin.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.editorMixin.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        if (!this.editorMixin.backPressed()) {
            super.onBackPressed();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.editorMixin.dispatchTouchEvent(motionEvent) || super.dispatchTouchEvent(motionEvent);
    }

    public String getAnalyticsScreenTag() {
        EditorMixinBase editorMixinBase = this.editorMixin;
        if (editorMixinBase != null) {
            return editorMixinBase.getAnalyticsScreenTag();
        }
        return null;
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        EditorMixinBase editorMixinBase = this.editorMixin;
        if (editorMixinBase != null) {
            return editorMixinBase.getAnalyticsScreenAttributes();
        }
        return null;
    }

    public void initMixin(Bundle bundle) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        switch ((FoodEditorType) BundleUtils.getSerializable(extras, EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.class.getClassLoader())) {
            case RestaurantMenuItem:
                MenuItemEditorMixin menuItemEditorMixin = new MenuItemEditorMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = menuItemEditorMixin;
                break;
            case DiaryFood:
                FoodEditorMixin foodEditorMixin = new FoodEditorMixin((MfpActivity) this, this.onItemSavedListener, intent, bundle, (View) this.viewContainer, (FoodEditorExtras) BundleUtils.getParcelable(extras, FoodEditorMixinBase.EXTRA_EDITOR_EXTRAS, FoodEditorExtras.class.getClassLoader()));
                this.editorMixin = foodEditorMixin;
                break;
            case SponsoredFood:
                SponsoredFoodMixin sponsoredFoodMixin = new SponsoredFoodMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer, (FoodEditorExtras) BundleUtils.getParcelable(extras, FoodEditorMixinBase.EXTRA_EDITOR_EXTRAS, FoodEditorExtras.class.getClassLoader()));
                this.editorMixin = sponsoredFoodMixin;
                break;
            case MultiAddFood:
                MultiAddFoodEditorMixin multiAddFoodEditorMixin = new MultiAddFoodEditorMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer, (FoodEditorExtras) BundleUtils.getParcelable(extras, FoodEditorMixinBase.EXTRA_EDITOR_EXTRAS, FoodEditorExtras.class.getClassLoader()));
                this.editorMixin = multiAddFoodEditorMixin;
                break;
            case BarcodeMultiAddFood:
                BarcodeMultiAddMixin barcodeMultiAddMixin = new BarcodeMultiAddMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = barcodeMultiAddMixin;
                break;
            case DeprecatedRecipeIngredient:
                DeprecatedRecipeIngredientEditorMixin deprecatedRecipeIngredientEditorMixin = new DeprecatedRecipeIngredientEditorMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = deprecatedRecipeIngredientEditorMixin;
                break;
            case Meal:
                MealEditorMixin mealEditorMixin = new MealEditorMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = mealEditorMixin;
                break;
            case MealIngredient:
                MealIngredientMixin mealIngredientMixin = new MealIngredientMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = mealIngredientMixin;
                break;
            case ViewSharedMeal:
                SharedMealViewerMixin sharedMealViewerMixin = new SharedMealViewerMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = sharedMealViewerMixin;
                break;
            case RecipeIngredient:
                RecipeIngredientEditorMixin recipeIngredientEditorMixin = new RecipeIngredientEditorMixin(this, this.onItemSavedListener, intent, bundle, this.viewContainer);
                this.editorMixin = recipeIngredientEditorMixin;
                break;
            default:
                throw new IllegalStateException("Unsupported Item!");
        }
        registerMixin(this.editorMixin);
        this.editorMixin.renderView();
    }
}
