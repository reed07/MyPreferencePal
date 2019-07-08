package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.view.MultiLineEditTextHint;
import com.myfitnesspal.feature.recipes.model.CreateRecipeIntentData;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class CreateRecipeManuallyActivity extends MfpActivity {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "analytics_intent_data";
    private static final String EXTRA_MEAL_NAME = "meal_name";
    public static final int MENU_NEXT = 1001;
    @BindView(2131362030)
    CompoundButton bulkImportSwitch;
    @BindView(2131362812)
    MultiLineEditTextHint ingredientsInputView;
    private final TextWatcher nameAndServingsTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            CreateRecipeManuallyActivity.this.invalidateOptionsMenu();
        }
    };
    @BindView(2131363093)
    EditText nameInputView;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @BindView(2131363613)
    EditText servingsInputView;

    public static Intent newStartIntent(Context context, RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        return new Intent(context, CreateRecipeManuallyActivity.class).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
    }

    public static Intent newStartIntent(Context context, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        return new Intent(context, CreateRecipeManuallyActivity.class).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData).putExtra("meal_name", str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.create_recipe_manually);
        setupListeners();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.next), 6);
        toggleNextEnabled(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        startIngredientMatching();
        reportRecipeManualImportEvent(true);
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        reportRecipeManualImportEvent(false);
    }

    private void startIngredientMatching() {
        getNavigationHelper().withIntent(IngredientMatchingActivity.newStartIntent(this, new CreateRecipeIntentData(this.nameInputView.getText().toString(), NumberUtils.tryParseDouble(this.servingsInputView.getText().toString()), this.bulkImportSwitch.isChecked() ? this.ingredientsInputView.getText().toString() : null), getAnalyticsIntentData(), getIntent().getStringExtra("meal_name"))).startActivity();
    }

    private void toggleNextEnabled(Menu menu) {
        MenuItem findItem = menu.findItem(1001);
        if (findItem != null) {
            findItem.setEnabled(Strings.notEmpty((Object) this.nameInputView.getText()) && NumberUtils.tryParseDouble(this.servingsInputView.getText().toString()) > 0.0d);
        }
    }

    private void setupListeners() {
        this.bulkImportSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ViewUtils.setVisible(z, CreateRecipeManuallyActivity.this.ingredientsInputView);
            }
        });
        this.nameInputView.addTextChangedListener(this.nameAndServingsTextWatcher);
        this.servingsInputView.addTextChangedListener(this.nameAndServingsTextWatcher);
    }

    private void reportRecipeManualImportEvent(boolean z) {
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeManualImport(getAnalyticsIntentData(), this.bulkImportSwitch.isChecked(), z);
    }

    private RecipeAnalyticsIntentData getAnalyticsIntentData() {
        return (RecipeAnalyticsIntentData) ExtrasUtils.getParcelable(getIntent(), EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
    }
}
