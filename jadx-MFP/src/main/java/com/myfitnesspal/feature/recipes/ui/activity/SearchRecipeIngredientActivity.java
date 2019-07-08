package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity;
import com.myfitnesspal.feature.barcode.util.BarcodeUtil;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.RecipeIngredientEditorMixin;
import com.myfitnesspal.feature.search.event.FoodItemSelectedEvent;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.ui.activity.SearchFoodItemBaseActivity;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import java.util.Date;

public class SearchRecipeIngredientActivity extends SearchFoodItemBaseActivity {
    public static final String EXTRA_ORIGINAL_INGREDIENT = "original_ingredient";
    public static final String EXTRA_ORIGINAL_INGREDIENT_ITEM = "original_ingredient_item";
    public static final String EXTRA_SELECTED_FOOD = "selected_food";
    private static final int MENU_BARCODE = 1001;

    public int getCustomToolbarLayoutResId() {
        return R.layout.search_ingredient_toolbar;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.search_ingredient;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, SearchRecipeIngredientActivity.class);
    }

    public static Intent newStartIntentWithIngredient(Context context, MfpIngredient mfpIngredient) {
        return newStartIntent(context).putExtra("original_ingredient", mfpIngredient);
    }

    public static Intent newStartIntentWithIngredientItem(Context context, MfpIngredientItem mfpIngredientItem) {
        return newStartIntent(context).putExtra("original_ingredient_item", mfpIngredientItem);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        doInitialSearchIfNeeded();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.barcode).setIcon(R.drawable.ic_barcode_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        getNavigationHelper().withIntent(BarcodeScannerActivity.newStartIntent(this)).startActivity(62);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 55) {
            if (i == 62) {
                BarcodeUtil.handleScanResult(this, getAnalyticsService(), FoodEditorType.RecipeIngredient, null, i2, getSession(), intent, null, MealTypeName.BREAKFAST, null, new Date());
                return;
            } else if (i != 200) {
                super.onActivityResult(i, i2, intent);
                return;
            }
        }
        processEditRecipeIngredientFoodResult(intent, i2);
    }

    /* access modifiers changed from: protected */
    public void foodSelected(FoodItemSelectedEvent foodItemSelectedEvent) {
        getNavigationHelper().withIntent(FoodEditorActivity.newSelectRecipeIngredientIntent(this, foodItemSelectedEvent.getItem())).startActivity(200);
    }

    private void doInitialSearchIfNeeded() {
        String initialSearchText = getInitialSearchText();
        if (!Strings.isEmpty(initialSearchText)) {
            this.inputText.setText(initialSearchText);
            searchForMatches(initialSearchText);
        }
    }

    private String getInitialSearchText() {
        String searchTextFromIngredient = getSearchTextFromIngredient(getIngredientFromIntent());
        if (Strings.notEmpty(searchTextFromIngredient)) {
            return searchTextFromIngredient;
        }
        MfpIngredientItem ingredientItemFromIntent = getIngredientItemFromIntent();
        if (ingredientItemFromIntent != null) {
            return getSearchTextFromIngredientItem(ingredientItemFromIntent);
        }
        return null;
    }

    private String getSearchTextFromIngredient(MfpIngredient mfpIngredient) {
        String str = null;
        if (mfpIngredient == null) {
            return null;
        }
        String text = mfpIngredient.getText();
        if (Strings.notEmpty(text)) {
            return text;
        }
        String rawText = mfpIngredient.getRawText();
        if (Strings.notEmpty(rawText)) {
            return rawText;
        }
        MfpFood food = mfpIngredient.getFood();
        if (food != null) {
            str = food.getDescription();
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (com.uacf.core.util.Strings.notEmpty(r0) != false) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getSearchTextFromIngredientItem(com.myfitnesspal.shared.model.v2.MfpIngredientItem r3) {
        /*
            r2 = this;
            com.myfitnesspal.shared.model.v2.MfpNormalizedData r0 = r3.getNormalizedData()
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = r0.getIngredient()
            boolean r1 = com.uacf.core.util.Strings.notEmpty(r0)
            if (r1 == 0) goto L_0x0011
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            if (r0 != 0) goto L_0x001c
            com.myfitnesspal.shared.model.v2.MfpIngredient r3 = r3.getIngredient()
            java.lang.String r0 = r2.getSearchTextFromIngredient(r3)
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.recipes.ui.activity.SearchRecipeIngredientActivity.getSearchTextFromIngredientItem(com.myfitnesspal.shared.model.v2.MfpIngredientItem):java.lang.String");
    }

    private void processEditRecipeIngredientFoodResult(Intent intent, int i) {
        if (i == -1) {
            setResult(-1, new Intent().putExtra(EXTRA_SELECTED_FOOD, (MfpFood) ExtrasUtils.getParcelable(intent, RecipeIngredientEditorMixin.EXTRA_UPDATED_FOOD, MfpFood.class.getClassLoader())).putExtra("original_ingredient", getIngredientFromIntent()).putExtra("original_ingredient_item", getIngredientItemFromIntent()));
            finish();
        }
    }

    private MfpIngredient getIngredientFromIntent() {
        return (MfpIngredient) ExtrasUtils.getParcelable(getIntent(), "original_ingredient", MfpIngredient.class.getClassLoader());
    }

    private MfpIngredientItem getIngredientItemFromIntent() {
        return (MfpIngredientItem) ExtrasUtils.getParcelable(getIntent(), "original_ingredient_item", MfpIngredientItem.class.getClassLoader());
    }
}
