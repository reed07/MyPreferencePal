package com.myfitnesspal.feature.restaurantlogging.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.model.MenuItemEditorBundleData;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.search.event.FoodItemSelectedEvent;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.ui.activity.SearchFoodItemBaseActivity;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class SearchMatchActivity extends SearchFoodItemBaseActivity {
    public static final String BUNDLE_DATA = "bundle_data";
    private static final String FOOD_SEARCH = "food_search";
    public static final String NEW_MATCH = "new_match";
    public static final String SEARCHED_MATCH_INDEX = "searched_match_index";
    public static final String SEARCH_TEXT = "search_text";
    public static final String SELECTED_ITEM_SOURCE = "selected_item_source";
    private MenuItemEditorBundleData bundleData;
    @Inject
    Lazy<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelper;

    public int getCustomToolbarLayoutResId() {
        return R.layout.search_match_toolbar;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.search_match;
    }

    public static Intent newStartIntent(Context context, MenuItemEditorBundleData menuItemEditorBundleData) {
        return new Intent(context, SearchMatchActivity.class).putExtra(BUNDLE_DATA, menuItemEditorBundleData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.bundleData = (MenuItemEditorBundleData) ExtrasUtils.getParcelable(getIntent(), BUNDLE_DATA, MenuItemEditorBundleData.class.getClassLoader());
        doInitialSearchIfNeeded();
        if (bundle != null) {
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportAlternativeScreenLoaded(this.bundleData);
        }
    }

    /* access modifiers changed from: protected */
    public void searchForMatches(String str) {
        super.searchForMatches(str);
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportAlternativeSearchDES(this.bundleData, str);
    }

    /* access modifiers changed from: protected */
    public void foodSelected(FoodItemSelectedEvent foodItemSelectedEvent) {
        MfpFood item = foodItemSelectedEvent.getItem();
        Intent intent = new Intent();
        intent.putExtra(NEW_MATCH, item);
        intent.putExtra(SEARCHED_MATCH_INDEX, foodItemSelectedEvent.getIndex());
        intent.putExtra(SEARCH_TEXT, Strings.toString(this.inputText.getText()));
        intent.putExtra(SELECTED_ITEM_SOURCE, "food_search");
        setResult(-1, intent);
        finish();
    }

    private void doInitialSearchIfNeeded() {
        String menuItemName = getMenuItemName();
        if (!Strings.isEmpty(menuItemName)) {
            this.inputText.setText(menuItemName);
            searchForMatches(menuItemName);
        }
    }

    private String getMenuItemName() {
        return this.bundleData.getMenuItem().getName();
    }
}
