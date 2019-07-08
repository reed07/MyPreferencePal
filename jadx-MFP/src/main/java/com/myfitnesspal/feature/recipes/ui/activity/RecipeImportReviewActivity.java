package com.myfitnesspal.feature.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.view.MultiLineEditTextHint;
import com.myfitnesspal.feature.recipes.api.RecipeParseResult;
import com.myfitnesspal.feature.recipes.model.CreateRecipeIntentData;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import javax.inject.Inject;

public class RecipeImportReviewActivity extends MfpActivity {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "analytics_intent_data";
    private static final String EXTRA_MEAL_NAME = "meal_name";
    private static final String EXTRA_RECIPE_PARSE_RESULT = "recipe_parse_result";
    private static final int MENU_NEXT = 1001;
    private RecipeImportReviewPagerAdapter pagerAdapter;
    @BindView(2131363483)
    ViewPager recipeReviewPager;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @BindView(2131363775)
    TabLayout tabLayout;

    private static class RecipeImportReviewPagerAdapter extends PagerAdapter {
        private final Context context;
        private MultiLineEditTextHint ingredientsInputView;
        private final RecipeParseResult recipeParseResult;

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private RecipeImportReviewPagerAdapter(Context context2, RecipeParseResult recipeParseResult2) {
            this.context = context2;
            this.recipeParseResult = recipeParseResult2;
        }

        public int getCount() {
            return ViewType.values().length;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            LayoutInflater from = LayoutInflater.from(this.context);
            ViewType viewType = ViewType.values()[i];
            switch (viewType) {
                case IngredientList:
                    view = from.inflate(R.layout.recipe_ingredients_import_review, viewGroup, false);
                    setupIngredientsReviewView(view);
                    break;
                case RecipeReviewBrowser:
                    view = from.inflate(R.layout.recipe_ingredients_import_browser_review, viewGroup, false);
                    setupIngredientsBrowserReviewView(view);
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled viewtype: ");
                    sb.append(viewType);
                    throw new IllegalStateException(sb.toString());
            }
            viewGroup.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public CharSequence getPageTitle(int i) {
            return this.context.getString(ViewType.values()[i].tabTitleResId);
        }

        /* access modifiers changed from: 0000 */
        public String getIngredientsInputString() {
            return this.ingredientsInputView.getText().toString();
        }

        private void setupIngredientsReviewView(View view) {
            this.ingredientsInputView = (MultiLineEditTextHint) ViewUtils.findById(view, R.id.ingredients_input);
            if (CollectionUtils.notEmpty((Collection<?>) this.recipeParseResult.getIngredients())) {
                String join = Strings.join("\n", (Collection<T>) this.recipeParseResult.getIngredients());
                this.ingredientsInputView.setText(join);
                this.ingredientsInputView.setSelection(join.length());
            }
        }

        private void setupIngredientsBrowserReviewView(View view) {
            WebView webView = (WebView) ViewUtils.findById(view, R.id.recipe_web_view);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.clearCache(true);
            webView.loadUrl(this.recipeParseResult.getUrl());
        }
    }

    private enum ViewType {
        IngredientList(R.string.ingredients),
        RecipeReviewBrowser(R.string.recipe);
        
        /* access modifiers changed from: private */
        @StringRes
        public final int tabTitleResId;

        private ViewType(int i) {
            this.tabTitleResId = i;
        }
    }

    public static Intent newStartIntent(Context context, RecipeParseResult recipeParseResult, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        return new Intent(context, RecipeImportReviewActivity.class).putExtra(EXTRA_RECIPE_PARSE_RESULT, recipeParseResult).putExtra("meal_name", str).putExtra(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.recipe_import_review);
        init();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.next), 6);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        reportSummaryAndStartIngredientMatching();
        return true;
    }

    private void reportSummaryAndStartIngredientMatching() {
        RecipeParseResult recipeParseResultFromIntent = getRecipeParseResultFromIntent();
        CreateRecipeIntentData createRecipeIntentData = new CreateRecipeIntentData(recipeParseResultFromIntent.getName(), recipeParseResultFromIntent.getServings(), this.pagerAdapter.getIngredientsInputString(), recipeParseResultFromIntent.getUrl(), recipeParseResultFromIntent.getPictureUrl(), recipeParseResultFromIntent.getHash());
        reportSummary();
        getNavigationHelper().withIntent(IngredientMatchingActivity.newStartIntent(this, createRecipeIntentData, getAnalyticsIntentData(), getIntent().getStringExtra("meal_name"))).startActivity();
    }

    private void reportSummary() {
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeTextParseSummary(getAnalyticsIntentData(), this.pagerAdapter.getIngredientsInputString(), CollectionUtils.size((Collection<?>) getRecipeParseResultFromIntent().getIngredients()));
    }

    private void init() {
        MaterialUtils.removeDefaultToolbarElevation(this);
        RecipeParseResult recipeParseResultFromIntent = getRecipeParseResultFromIntent();
        if (CollectionUtils.isEmpty((Collection<?>) recipeParseResultFromIntent.getIngredients())) {
            new SnackbarBuilder(this.recipeReviewPager).setMessage((int) R.string.unable_parse_recipe).setDuration(0).build().show();
        }
        this.pagerAdapter = new RecipeImportReviewPagerAdapter(this, recipeParseResultFromIntent);
        this.recipeReviewPager.setAdapter(this.pagerAdapter);
        this.recipeReviewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                RecipeImportReviewActivity.this.getImmHelper().hideSoftInput();
            }
        });
        this.tabLayout.setupWithViewPager(this.recipeReviewPager);
    }

    private RecipeParseResult getRecipeParseResultFromIntent() {
        return (RecipeParseResult) ExtrasUtils.getParcelable(getIntent(), EXTRA_RECIPE_PARSE_RESULT, RecipeParseResult.class.getClassLoader());
    }

    private RecipeAnalyticsIntentData getAnalyticsIntentData() {
        return (RecipeAnalyticsIntentData) ExtrasUtils.getParcelable(getIntent(), EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
    }
}
