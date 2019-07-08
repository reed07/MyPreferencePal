package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.event.ServingsEditedEvent;
import com.myfitnesspal.feature.foodeditor.ui.event.BasedOnViewClickEvent;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.restaurantlogging.model.FoodGroup;
import com.myfitnesspal.feature.restaurantlogging.model.MenuItemEditorBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatch;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatchData;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.restaurantlogging.task.CreateMenuItemMatchTask;
import com.myfitnesspal.feature.restaurantlogging.task.CreateMenuItemMatchTask.CompletedEvent;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.MenusActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.SearchMatchActivity;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class MenuItemEditorMixin extends EditorMixinBase<MfpMenuItem> {
    public static final String EXTRA_FOOD_EDITOR_ITEM_METADATA = "food_editor_bundle_data";
    public static final String EXTRA_RETURN_MENU_ITEM_RESULT = "return_menu_item_result";
    /* access modifiers changed from: private */
    public final MfpActivity activity;
    /* access modifiers changed from: private */
    public MenuItemEditorBundleData bundleData;
    private BusEvents busEvents;
    private FoodEditorMixinBase foodEditorMixin;
    private final Intent intent;
    /* access modifiers changed from: private */
    public MfpMenuItem menuItem;
    private MfpMenuItemMatch menuItemMatch;
    @Inject
    Lazy<MenuService> menuService;
    private final OnItemSavedListener onItemSavedListener;
    private final View parentView;
    @Inject
    Lazy<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelper;
    @Inject
    Lazy<RestaurantLoggingSettingsService> restaurantLoggingSettingsService;
    private Bundle savedInstanceState;

    private class BusEvents {
        private BusEvents() {
        }

        @Subscribe
        public void onServingsEditedEvent(ServingsEditedEvent servingsEditedEvent) {
            ((RestaurantLoggingAnalyticsHelper) MenuItemEditorMixin.this.restaurantLoggingAnalyticsHelper.get()).reportServingsChange(MenuItemEditorMixin.this.bundleData, MenuItemEditorMixin.this.menuItem);
        }

        @Subscribe
        public void onCreateMenuItemMatchTaskCompletedEvent(CompletedEvent completedEvent) {
            if (completedEvent.getFailure() == null) {
                MenuItemEditorMixin.this.addMenuItemToTarget(((MfpMenuItemMatch) completedEvent.getResult()).getLogMatchData());
                return;
            }
            ((LegacyAlertMixin) MenuItemEditorMixin.this.activity.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(null, MenuItemEditorMixin.this.getString(R.string.unable_create_menu_item_match), MenuItemEditorMixin.this.getString(R.string.ok), null, null, null);
        }

        @Subscribe
        public void onBasedOnViewClickEvent(BasedOnViewClickEvent basedOnViewClickEvent) {
            if (!MenuItemEditorMixin.this.checkShowInaccurateMatchDialog()) {
                MenuItemEditorMixin.this.navigateToSearchMatchActivity();
            }
        }
    }

    public MenuItemEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener2, Intent intent2, Bundle bundle, View view) {
        this(mfpActivity, onItemSavedListener2, intent2, bundle, view, 0);
    }

    public MenuItemEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener2, Intent intent2, Bundle bundle, View view, int i) {
        super(mfpActivity);
        this.busEvents = new BusEvents();
        component().inject(this);
        this.activity = mfpActivity;
        this.onItemSavedListener = onItemSavedListener2;
        this.bundleData = (MenuItemEditorBundleData) BundleUtils.getParcelable(EXTRA_FOOD_EDITOR_ITEM_METADATA, null, MenuItemEditorBundleData.class.getClassLoader(), bundle, intent2.getExtras());
        this.menuItem = this.bundleData.getMenuItem();
        this.intent = intent2;
        this.savedInstanceState = bundle;
        this.parentView = view;
        if (CollectionUtils.notEmpty((Collection<?>) this.menuItem.getMatches())) {
            initBasedOnMatchIndex(i);
        } else {
            navigateToSearchMatchActivity(false);
        }
    }

    private void initBasedOnMatchIndex(int i) {
        this.menuItemMatch = (MfpMenuItemMatch) this.menuItem.getMatches().get(i);
        initFoodEditorMixin(this.menuItemMatch.getBasedOnMatchData());
        reportMenuItemViewedEvent();
    }

    public void onResume() {
        super.onResume();
        FoodEditorMixinBase foodEditorMixinBase = this.foodEditorMixin;
        if (foodEditorMixinBase != null) {
            foodEditorMixinBase.onResume();
        }
        this.activity.getMessageBus().register(this.busEvents);
    }

    public void onPause() {
        super.onPause();
        FoodEditorMixinBase foodEditorMixinBase = this.foodEditorMixin;
        if (foodEditorMixinBase != null) {
            foodEditorMixinBase.onPause();
        }
        this.activity.getMessageBus().unregister(this.busEvents);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        FoodEditorMixinBase foodEditorMixinBase = this.foodEditorMixin;
        if (foodEditorMixinBase == null || !foodEditorMixinBase.onRebindDialogFragment(dialogFragment, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        return true;
    }

    private void initFoodEditorMixin(MfpMenuItemMatchData mfpMenuItemMatchData) {
        if (mfpMenuItemMatchData instanceof MfpFood) {
            FoodEditorMixin foodEditorMixin2 = new FoodEditorMixin(this.activity, this.onItemSavedListener, this.intent, this.savedInstanceState, this.parentView, (MfpFood) mfpMenuItemMatchData);
            this.foodEditorMixin = foodEditorMixin2;
        } else if (mfpMenuItemMatchData instanceof MfpRecipe) {
            RecipeEditorMixin recipeEditorMixin = new RecipeEditorMixin(this.activity, this.onItemSavedListener, this.intent, this.savedInstanceState, this.parentView, (MfpRecipe) mfpMenuItemMatchData);
            this.foodEditorMixin = recipeEditorMixin;
        } else if (mfpMenuItemMatchData instanceof FoodGroup) {
            FoodGroupEditorMixin foodGroupEditorMixin = new FoodGroupEditorMixin(this.activity, this.onItemSavedListener, this.intent, this.savedInstanceState, this.parentView, (FoodGroup) mfpMenuItemMatchData);
            this.foodEditorMixin = foodGroupEditorMixin;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("The match can only be a food, recipe or food group object. Currently it's ");
            sb.append(mfpMenuItemMatchData.getClass().getName());
            throw new IllegalStateException(sb.toString());
        }
        this.foodEditorMixin.setDate(this.bundleData.getDate());
        this.foodEditorMixin.setMealName(this.bundleData.getMealName());
        this.foodEditorMixin.timestampPickerMixin.setScreenType(FoodScreenType.RESTAURANT_MENU);
    }

    private void reportMenuItemViewedEvent() {
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportMenuItemViewed(this.bundleData, this.menuItem);
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportMenuItemViewedDES(this.bundleData, this.menuItem, this.menuItemMatch);
    }

    public void renderView() {
        FoodEditorMixinBase foodEditorMixinBase = this.foodEditorMixin;
        if (foodEditorMixinBase != null) {
            foodEditorMixinBase.renderView();
            this.foodEditorMixin.setName(this.menuItem.getName());
            this.foodEditorMixin.setDescription(this.menuItem.getDescription());
            FoodEditorMixinBase foodEditorMixinBase2 = this.foodEditorMixin;
            foodEditorMixinBase2.setBasedOnText(foodEditorMixinBase2.getFoodItemName());
        }
    }

    public void saveItemToTarget() {
        saveItemToTarget(this.menuItemMatch.getLogMatchData());
    }

    public void saveItemToTarget(MfpMenuItem mfpMenuItem) {
        saveItemToTarget(((MfpMenuItemMatch) mfpMenuItem.getMatches().get(0)).getLogMatchData());
    }

    private void saveItemToTarget(MfpMenuItemMatchData mfpMenuItemMatchData) {
        if (ExtrasUtils.getBoolean(this.intent, EXTRA_RETURN_MENU_ITEM_RESULT)) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MenusActivity.EXTRA_MENU_ITEM_RESULT, this.menuItem);
            this.onItemSavedListener.onItemSaved(-1, bundle);
            return;
        }
        this.activity.setBusy(1, true);
        if (mfpMenuItemMatchData != null) {
            addMenuItemToTarget(mfpMenuItemMatchData);
            return;
        }
        new CreateMenuItemMatchTask(this.menuService, getMenuItemMatch(), this.menuItem.getMenuId(), this.menuItem.getId()).run(this.activity.getRunner());
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(EXTRA_FOOD_EDITOR_ITEM_METADATA, this.bundleData);
        FoodEditorMixinBase foodEditorMixinBase = this.foodEditorMixin;
        if (foodEditorMixinBase != null) {
            foodEditorMixinBase.onSaveInstanceState(bundle);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent2) {
        processActivityResult(i, i2, intent2);
    }

    public boolean processActivityResult(int i, int i2, final Intent intent2) {
        if (i != 184) {
            return false;
        }
        if (i2 == -1) {
            this.parentView.post(new Runnable() {
                public void run() {
                    MfpMenuItemMatch mfpMenuItemMatch = new MfpMenuItemMatch((MfpFood) ExtrasUtils.getParcelable(intent2, SearchMatchActivity.NEW_MATCH, MfpFood.class.getClassLoader()));
                    List matches = MenuItemEditorMixin.this.menuItem.getMatches();
                    MenuItemEditorMixin.this.menuItem.addNewPrimaryMatch(mfpMenuItemMatch);
                    MenuItemEditorMixin menuItemEditorMixin = MenuItemEditorMixin.this;
                    menuItemEditorMixin.setNewItem(menuItemEditorMixin.menuItem);
                    MenuItemEditorMixin.this.showMatchChangeSnackbar();
                    ((RestaurantLoggingAnalyticsHelper) MenuItemEditorMixin.this.restaurantLoggingAnalyticsHelper.get()).reportAlternateMatchSelectedDES(MenuItemEditorMixin.this.menuItem, mfpMenuItemMatch, !CollectionUtils.isEmpty((Collection<?>) matches) ? (MfpMenuItemMatch) matches.get(0) : null, MenuItemEditorMixin.this.bundleData, ExtrasUtils.getString(intent2, SearchMatchActivity.SEARCH_TEXT), ExtrasUtils.getInt(intent2, SearchMatchActivity.SEARCHED_MATCH_INDEX), ExtrasUtils.getString(intent2, SearchMatchActivity.SELECTED_ITEM_SOURCE));
                }
            });
        } else if (CollectionUtils.isEmpty((Collection<?>) this.menuItem.getMatches())) {
            this.activity.finish();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void setNewItem(MfpMenuItem mfpMenuItem) {
        this.bundleData.setMenuItem(mfpMenuItem);
        this.menuItem = mfpMenuItem;
        this.menuItemMatch = (MfpMenuItemMatch) mfpMenuItem.getMatches().get(0);
        initFoodEditorMixin(this.menuItemMatch.getBasedOnMatchData());
        renderView();
    }

    /* access modifiers changed from: private */
    public void showMatchChangeSnackbar() {
        new SnackbarBuilder(this.parentView).setMessage((int) R.string.match_change_reported).setDuration(0).showWithDelay();
    }

    /* access modifiers changed from: private */
    public void navigateToSearchMatchActivity() {
        navigateToSearchMatchActivity(true);
    }

    private void navigateToSearchMatchActivity(boolean z) {
        this.activity.getNavigationHelper().withIntent(SearchMatchActivity.newStartIntent(this.activity, this.bundleData)).startActivity(RequestCodes.SEARCH_MATCH);
        if (z) {
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportChooseAlternateMatch(this.bundleData, this.menuItem);
        }
    }

    /* access modifiers changed from: private */
    public boolean checkShowInaccurateMatchDialog() {
        if (((RestaurantLoggingSettingsService) this.restaurantLoggingSettingsService.get()).wasInaccurateMatchDialogDisplayed()) {
            return false;
        }
        new MfpAlertDialogBuilder(this.activity).setTitle((int) R.string.inaccurate_match_dialog_header).setMessage((int) R.string.inaccurate_match_dialog_body).setPositiveButton((int) R.string.choose_match, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuItemEditorMixin.this.navigateToSearchMatchActivity();
            }
        }).setNegativeButton((int) R.string.dismiss, (OnClickListener) null).setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                ((RestaurantLoggingSettingsService) MenuItemEditorMixin.this.restaurantLoggingSettingsService.get()).setInaccurateMatchDialogDisplayed(true);
            }
        }).setCancelable(false).setCanceledOnTouchOutside(false).show();
        return true;
    }

    /* access modifiers changed from: private */
    public String getString(int i) {
        return this.activity.getString(i);
    }

    /* access modifiers changed from: private */
    public void addMenuItemToTarget(MfpMenuItemMatchData mfpMenuItemMatchData) {
        if (mfpMenuItemMatchData instanceof FoodGroup) {
            FoodGroupEditorMixin foodGroupEditorMixin = (FoodGroupEditorMixin) this.foodEditorMixin;
            r5 = (MfpFood) ((FoodGroup) mfpMenuItemMatchData).get(foodGroupEditorMixin.getCurrentBasedOnFoodIndex());
            foodGroupEditorMixin.saveItemToTarget(r5);
            r5 = r5;
        } else {
            this.foodEditorMixin.saveItemToTarget(mfpMenuItemMatchData);
            r5 = mfpMenuItemMatchData;
        }
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportItemLogged(this.bundleData, this.menuItem, r5, this.menuItemMatch);
    }

    private MfpMenuItemMatch getMenuItemMatch() {
        return new MfpMenuItemMatch(this.menuItemMatch);
    }
}
