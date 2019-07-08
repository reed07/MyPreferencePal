package com.myfitnesspal.feature.search.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity;
import com.myfitnesspal.feature.barcode.util.BarcodeUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.diary.ui.activity.OfflineSearchNote;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.recipes.event.CreateNewRecipeEvent;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.RestaurantLoggingInterstitial;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.myfitnesspal.feature.search.event.MealFilterChangedEvent;
import com.myfitnesspal.feature.search.event.MultiAddModeToggledEvent;
import com.myfitnesspal.feature.search.event.NavigateToQuickAddEvent;
import com.myfitnesspal.feature.search.event.SearchItemClickedEvent;
import com.myfitnesspal.feature.search.event.SelectedFoodSearchTabChangedEvent;
import com.myfitnesspal.feature.search.event.UpdateFoodSearchBreadcrumbEvent;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.dialog.MealFilterDialog;
import com.myfitnesspal.feature.search.ui.dialog.SortOrderDialog;
import com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragment;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin.OnTimestampChangedListener;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.QuickAddCalorieAddedEvent;
import com.myfitnesspal.shared.event.UpdateMultiAddStatusEvent;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.CalorieAddErrorDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.QuickAddCaloriesDialogFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.view.ClearableEditText;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FoodSearchActivity extends MfpActivity {
    private static final String ANALYTIC_ATTRIBUTE_ADD_RECIPES = "add_recipes";
    private static final String ANALYTIC_ATTRIBUTE_CREATE_FOOD = "create_food";
    private static final String ANALYTIC_ATTRIBUTE_CREATE_MEAL = "create_meal";
    private static final String ANALYTIC_ATTRIBUTE_MEAL_FILTER = "meal_filter";
    private static final String ANALYTIC_ATTRIBUTE_QUICK_ADD = "quick_add";
    private static final String ANALYTIC_ATTRIBUTE_SORT_ORDER = "sort_order";
    private static final String ATTRIBUTE_SOURCE = "source";
    private static final String BUNDLE_EXTRA_MULTI_MODE_ON = "multi_mode_on";
    private static final String BUNDLE_EXTRA_SELECTED_TAB = "selected_tab";
    private static final String EVENT_QUICK_ADD_CLICKED = "quick_add_clicked";
    private static final FoodSearchTab[] FOOD_SEARCH_TABS_CONSOLIDATED = {FoodSearchTab.RECENT, FoodSearchTab.MEALS, FoodSearchTab.RECIPES};
    private static final int MENU_ADD_RECIPE = 106;
    private static final int MENU_CREATE_FOOD = 104;
    private static final int MENU_CREATE_MEAL = 105;
    private static final int MENU_MEAL_FILTER = 102;
    private static final int MENU_MULTI_ADD = 100;
    private static final int MENU_QUICK_ADD = 103;
    private static final int MENU_SORT_ORDER = 101;
    public static final int RECIPE_TAB_INDEX = 4;
    private static final String REFERRER = "food_search";
    private static final int REQUEST_CODE_VENUE_LIST = 1001;
    /* access modifiers changed from: private */
    public ActionMode actionMode;
    private OnPageChangeListener contentPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            FoodSearchActivity.this.updateVisibleFragmentQueryString();
            FoodSearchActivity foodSearchActivity = FoodSearchActivity.this;
            foodSearchActivity.postEvent(new SelectedFoodSearchTabChangedEvent(foodSearchActivity.foodSearchTabs[i]));
            FoodSearchActivity.this.supportInvalidateOptionsMenu();
            FoodSearchActivity.this.invalidateActionMode();
            FoodSearchActivity.this.setBehaviorBasedOnTab(i);
        }
    };
    @BindView(2131362213)
    ViewPager contentPager;
    private ContentPagerAdapter contentPagerAdapter;
    @Inject
    Lazy<CountryService> countryServiceLazy;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryService> diaryService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Extras extras;
    @Inject
    Lazy<FoodEditorAnalytics> foodEditorAnalytics;
    @Inject
    Lazy<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelper;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    /* access modifiers changed from: private */
    public FoodSearchTab[] foodSearchTabs;
    @BindView(2131364057)
    ClearableEditText foodSearchText;
    /* access modifiers changed from: private */
    public boolean isMealFoodCreationFlow;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<MealIngredientMapper> mealIngredientMapper;
    /* access modifiers changed from: private */
    public String mealName;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    @BindView(2131363140)
    TabLayout newTabContainer;
    @BindView(2131363210)
    View offlineSearchContainer;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelper;
    @BindView(2131362027)
    View restaurantLoggingBtn;
    @Inject
    Lazy<RestaurantLoggingSettingsService> restaurantLoggingSettingsService;
    @BindView(2131362029)
    View scanBtn;
    /* access modifiers changed from: private */
    public String searchFlowId = UUID.randomUUID().toString();
    private boolean showMultiAddFromOnCreate;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    /* access modifiers changed from: private */
    public String[] tabLabels;
    private TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            FoodSearchActivity.this.updateVisibleFragmentQueryString();
        }
    };
    /* access modifiers changed from: private */
    public TimestampPickerMixin timestampPickerMixin;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private class ContentPagerAdapter extends FragmentPagerAdapter {
        private SparseArray<WeakReference<LocalFoodSearchFragment>> instantiatedFragments;

        public ContentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.instantiatedFragments = new SparseArray<>(FoodSearchActivity.this.getTabCount());
        }

        public Fragment getItem(int i) {
            return LocalFoodSearchFragment.newInstance(new com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragment.Extras().setFoodSearchTab(FoodSearchActivity.this.foodSearchTabs[i]).setMealName(FoodSearchActivity.this.mealName).isMealFoodCreationFlow(FoodSearchActivity.this.isMealFoodCreationFlow).setMealFoodCreationFlowId(FoodSearchActivity.this.searchFlowId));
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            LocalFoodSearchFragment localFoodSearchFragment = (LocalFoodSearchFragment) super.instantiateItem(viewGroup, i);
            localFoodSearchFragment.updateQuery(FoodSearchActivity.this.getCurrentQueryString());
            this.instantiatedFragments.put(i, new WeakReference(localFoodSearchFragment));
            return localFoodSearchFragment;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            this.instantiatedFragments.remove(i);
            super.destroyItem(viewGroup, i, obj);
        }

        public int getCount() {
            return FoodSearchActivity.this.getTabCount();
        }

        public CharSequence getPageTitle(int i) {
            return FoodSearchActivity.this.tabLabels[i];
        }

        public LocalFoodSearchFragment getFragmentAtPosition(int i) {
            WeakReference weakReference = (WeakReference) this.instantiatedFragments.get(i);
            if (weakReference == null) {
                return null;
            }
            return (LocalFoodSearchFragment) weakReference.get();
        }
    }

    public static class Extras {
        private static final String EXTRA_IS_IN_MEAL_CREATION_FLOW = "is_in_meal_creation_flow";
        private static final String EXTRA_IS_MEAL_CREATED = "is_meal_created";
        private static final String EXTRA_IS_MEAL_EDITED = "is_meal_edited";
        private static final String EXTRA_LATEST_MEAL_ENTRY_TIME = "latest_meal_creation_time";
        private static final String EXTRA_MEAL_CREATION_FLOW_ID = "meal_creation_flow_id";
        private static final String EXTRA_MEAL_INDEX = "user_meal_index";
        private static final String EXTRA_MEAL_NAME = "user_meal_name";
        private static final String EXTRA_SHOULD_SELECT_MEAL_TAB = "should_select_meal_tab";
        private static final String EXTRA_SHOW_WALKTHROUGH = "show_walkthrough";
        private boolean isInMealFoodCreationFlow;
        private boolean isMealCreated;
        private boolean isMealEdited;
        private Date latestMealEntryTime;
        private String mealFoodCreationFlowId;
        private int mealIndex = Integer.MIN_VALUE;
        private String mealName;
        private boolean shouldSelectMealTab;
        private boolean showWalkthrough = false;

        public static Extras fromIntent(Intent intent) {
            return new Extras().setMealCreated(intent.getBooleanExtra(EXTRA_IS_MEAL_CREATED, false)).setMealEdited(intent.getBooleanExtra(EXTRA_IS_MEAL_EDITED, false)).setMealIndex(intent.getIntExtra(EXTRA_MEAL_INDEX, Integer.MIN_VALUE)).setMealName(intent.getStringExtra(EXTRA_MEAL_NAME)).shouldSelectMealTab(intent.getBooleanExtra(EXTRA_SHOULD_SELECT_MEAL_TAB, false)).inMealFoodCreationFlow(intent.getBooleanExtra(EXTRA_IS_IN_MEAL_CREATION_FLOW, false)).setMealFoodCreationFlowId(intent.getStringExtra(EXTRA_MEAL_CREATION_FLOW_ID)).setLatestMealEntryTime((Date) intent.getSerializableExtra(EXTRA_LATEST_MEAL_ENTRY_TIME)).setShowWalkthrough(intent.getBooleanExtra(EXTRA_SHOW_WALKTHROUGH, false));
        }

        public com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2.Extras toV2() {
            com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2.Extras extras = new com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2.Extras(this.isMealCreated, this.isMealEdited, this.mealIndex, this.mealName, this.shouldSelectMealTab, this.isInMealFoodCreationFlow, this.mealFoodCreationFlowId, this.latestMealEntryTime, this.showWalkthrough);
            return extras;
        }

        public void writeToIntent(Intent intent) {
            intent.putExtra(EXTRA_IS_MEAL_CREATED, this.isMealCreated);
            intent.putExtra(EXTRA_IS_MEAL_EDITED, this.isMealEdited);
            intent.putExtra(EXTRA_MEAL_INDEX, this.mealIndex);
            intent.putExtra(EXTRA_MEAL_NAME, this.mealName);
            intent.putExtra(EXTRA_SHOULD_SELECT_MEAL_TAB, this.shouldSelectMealTab);
            intent.putExtra(EXTRA_IS_IN_MEAL_CREATION_FLOW, this.isInMealFoodCreationFlow);
            intent.putExtra(EXTRA_MEAL_CREATION_FLOW_ID, this.mealFoodCreationFlowId);
            intent.putExtra(EXTRA_LATEST_MEAL_ENTRY_TIME, this.latestMealEntryTime);
            intent.putExtra(EXTRA_SHOW_WALKTHROUGH, this.showWalkthrough);
        }

        public boolean isMealCreated() {
            return this.isMealCreated;
        }

        public Extras setMealCreated(boolean z) {
            this.isMealCreated = z;
            return this;
        }

        public boolean isMealEdited() {
            return this.isMealEdited;
        }

        public Extras setMealEdited(boolean z) {
            this.isMealEdited = z;
            return this;
        }

        public int getMealIndex() {
            return this.mealIndex;
        }

        public Extras setMealIndex(int i) {
            this.mealIndex = i;
            return this;
        }

        public boolean hasMealIndex() {
            return this.mealIndex != Integer.MIN_VALUE;
        }

        public String getMealName() {
            return this.mealName;
        }

        public Extras setMealName(String str) {
            this.mealName = str;
            return this;
        }

        public boolean shouldSelectMealTab() {
            return this.shouldSelectMealTab;
        }

        public Extras shouldSelectMealTab(boolean z) {
            this.shouldSelectMealTab = z;
            return this;
        }

        public boolean isInMealFoodCreationFlow() {
            return this.isInMealFoodCreationFlow;
        }

        public Extras inMealFoodCreationFlow(boolean z) {
            this.isInMealFoodCreationFlow = z;
            return this;
        }

        public String getMealFoodCreationFlowId() {
            return this.mealFoodCreationFlowId;
        }

        public Extras setMealFoodCreationFlowId(String str) {
            this.mealFoodCreationFlowId = str;
            return this;
        }

        public Date getLatestMealEntryTime() {
            return this.latestMealEntryTime;
        }

        public Extras setLatestMealEntryTime(Date date) {
            this.latestMealEntryTime = date;
            return this;
        }

        public boolean isShowWalkthrough() {
            return this.showWalkthrough;
        }

        public Extras setShowWalkthrough(boolean z) {
            this.showWalkthrough = z;
            return this;
        }
    }

    private class MultiAddActionMode implements Callback {
        private static final int ACTION_ADD_ALL = 1001;

        public MultiAddActionMode(Activity activity) {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            FoodSearchActivity.this.actionMode = actionMode;
            FoodSearchActivity.this.getMultiAddFoodHelper().enable();
            FoodSearchActivity.this.updateMultiAddSelectionCount();
            FoodSearchActivity.this.postEvent(new MultiAddModeToggledEvent(true));
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            menu.clear();
            boolean z = FoodSearchActivity.this.getMultiAddFoodHelper().totalItemCount() > 0;
            menu.add(0, 1001, 0, R.string.add_selected).setIcon(z ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp).setEnabled(z).setShowAsAction(2);
            FoodSearchActivity.this.addOverflowMenuIcons(menu);
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != 1001) {
                return FoodSearchActivity.this.handleOptionMenuItemSelected(menuItem);
            }
            FoodSearchActivity.this.getFoodSearchAnalyticsHelper().reportAddAllClickAnalyticsEvent();
            if (FoodSearchActivity.this.isMealFoodCreationFlow) {
                FoodSearchActivity.this.saveEntriesToMeal();
                actionMode.finish();
            } else if (!((PremiumService) FoodSearchActivity.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FoodTimestamps) || !((LocalSettingsService) FoodSearchActivity.this.localSettingsService.get()).shouldShowFoodTimestamps()) {
                FoodSearchActivity.this.saveEntriesToDiary(null, null);
                actionMode.finish();
            } else {
                FoodSearchActivity.this.timestampPickerMixin.showTimestampOptions();
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            FoodSearchActivity.this.getMultiAddFoodHelper().disable();
            FoodSearchActivity.this.updateMultiAddSelectionCount();
            FoodSearchActivity.this.postEvent(new MultiAddModeToggledEvent(false));
            FoodSearchActivity.this.actionMode = null;
        }
    }

    private class TimestampPickerListener implements OnTimestampChangedListener {
        private TimestampPickerListener() {
        }

        public void onTimestampChange(@Nullable Date date, @NotNull TimestampOption timestampOption) {
            FoodSearchActivity.this.saveEntriesToDiary(date, timestampOption);
            FoodSearchActivity.this.actionMode.finish();
        }
    }

    public String getAnalyticsScreenTag() {
        return Screens.FOOD_SEARCH;
    }

    public static Intent newStartIntent(Context context, Extras extras2) {
        Intent intent = new Intent(context, FoodSearchActivity.class);
        extras2.writeToIntent(intent);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        component().inject(this);
        MealNames mealNames = getSession().getUser().getMealNames();
        this.extras = Extras.fromIntent(getIntent());
        this.mealName = this.extras.getMealName();
        if (this.extras.hasMealIndex()) {
            this.mealName = mealNames.nameForIndex(this.extras.getMealIndex());
        }
        this.isMealFoodCreationFlow = this.extras.isInMealFoodCreationFlow();
        if (this.isMealFoodCreationFlow) {
            this.searchFlowId = this.extras.getMealFoodCreationFlowId();
        } else if (checkShowRestaurantLoggingInterstitial()) {
            return;
        }
        setContentView((int) R.layout.new_food_search);
        MaterialUtils.removeDefaultToolbarElevation(this);
        if (this.isMealFoodCreationFlow) {
            str = getString(R.string.addFood);
        } else {
            str = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(this.mealName, (UserEnergyService) this.userEnergyService.get());
        }
        setTitle(str);
        setRLButton();
        setupListeners();
        setupTabs();
        setupPager();
        selectDefaultSearchTab(getSelectedTab(bundle, this.extras.shouldSelectMealTab()));
        boolean z = bundle != null ? BundleUtils.getBoolean(bundle, BUNDLE_EXTRA_MULTI_MODE_ON) : ((LocalSettingsService) this.localSettingsService.get()).getMultiAddToggleOnByDefault();
        if (z) {
            this.showMultiAddFromOnCreate = true;
        } else {
            getMultiAddFoodHelper().disable();
        }
        this.timestampPickerMixin = new TimestampPickerMixin(this, this.extras.getLatestMealEntryTime());
        this.timestampPickerMixin.setTimestampChangeListener(new TimestampPickerListener());
        registerMixin(this.timestampPickerMixin);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.disposable.dispose();
    }

    private int getSelectedTab(Bundle bundle, boolean z) {
        int i;
        if (bundle != null) {
            return BundleUtils.getInt(bundle, BUNDLE_EXTRA_SELECTED_TAB);
        }
        if (z) {
            i = 6003;
        } else {
            i = ((LocalSettingsService) this.localSettingsService.get()).getDefaultSearchTab();
        }
        return i;
    }

    private void setRLButton() {
        if (this.restaurantLoggingBtn != null) {
            ViewUtils.setVisible(ConfigUtils.showRLFlow(getConfigService()), this.restaurantLoggingBtn);
            this.restaurantLoggingBtn.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    FoodSearchActivity.lambda$setRLButton$0(FoodSearchActivity.this, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$setRLButton$0(FoodSearchActivity foodSearchActivity, View view) {
        ((RestaurantLoggingAnalyticsHelper) foodSearchActivity.restaurantLoggingAnalyticsHelper.get()).reportEntryPoint(foodSearchActivity.mealName);
        foodSearchActivity.finishActionMode();
        foodSearchActivity.getNavigationHelper().withIntent(VenuesActivity.newStartIntent(foodSearchActivity, foodSearchActivity.mealName, foodSearchActivity.getCurrentActiveDate(), foodSearchActivity.isMealFoodCreationFlow)).startActivity(1001);
    }

    private Date getCurrentActiveDate() {
        return getSession().getUser().getActiveDate();
    }

    static /* synthetic */ Boolean lambda$selectDefaultSearchTab$1(int i, FoodSearchTab foodSearchTab) throws RuntimeException {
        return Boolean.valueOf(i == foodSearchTab.getTabId());
    }

    private void selectDefaultSearchTab(int i) {
        int indexOf = Enumerable.indexOf((T[]) this.foodSearchTabs, (ReturningFunction1<Boolean, T>) new ReturningFunction1(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final Object execute(Object obj) {
                return FoodSearchActivity.lambda$selectDefaultSearchTab$1(this.f$0, (FoodSearchTab) obj);
            }
        });
        if (indexOf == -1) {
            indexOf = 0;
        }
        this.contentPager.setCurrentItem(indexOf, false);
        this.contentPager.post(new Runnable(indexOf) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                FoodSearchActivity.this.setBehaviorBasedOnTab(this.f$1);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getFoodSearchAnalyticsHelper().updateFoodSearchBreadcrumb(Attributes.SEARCH_VIEW);
        toggleOfflineTextVisibility();
        updateMultiAddSelectionCount();
        this.extras = Extras.fromIntent(getIntent());
        if (this.extras.isMealCreated() || this.extras.isMealEdited()) {
            refreshDBList();
            selectDefaultSearchTab(6003);
            this.extras.setMealCreated(false);
            this.extras.setMealEdited(false);
            return;
        }
        updateVisibleFragmentQueryStringIfNotDoingOnlineSearch();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        if (this.showMultiAddFromOnCreate) {
            this.showMultiAddFromOnCreate = false;
            showMultiAddMode();
        }
    }

    private void toggleOfflineTextVisibility() {
        ViewUtils.setVisible(ConnectivityUtil.isOffline().booleanValue(), this.offlineSearchContainer);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2;
        FoodEditorType foodEditorType;
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 54:
            case 55:
            case RequestCodes.FOOD_EDITOR /*183*/:
            case RequestCodes.MENUS /*185*/:
            case 1001:
                if (i2 == -1) {
                    boolean isMultiAddModeOn = ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn();
                    if (this.isMealFoodCreationFlow && !isMultiAddModeOn) {
                        packageMealIngredientsAsFoodEntriesAndFinish(intent);
                    }
                    LocalFoodSearchFragment currentFoodSearchFragment = getCurrentFoodSearchFragment();
                    if (currentFoodSearchFragment != null) {
                        currentFoodSearchFragment.onActivityResult(i, i2, intent);
                    }
                    if (!isMultiAddModeOn) {
                        finish();
                        return;
                    }
                    return;
                }
                return;
            case 62:
                if (this.isMealFoodCreationFlow) {
                    foodEditorType = FoodEditorType.MealIngredient;
                    intent2 = null;
                } else if (((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn()) {
                    foodEditorType = FoodEditorType.BarcodeMultiAddFood;
                    intent2 = null;
                } else {
                    Intent newStartIntent = Diary.newStartIntent(this);
                    newStartIntent.addFlags(603979776);
                    foodEditorType = FoodEditorType.DiaryFood;
                    intent2 = newStartIntent;
                }
                BarcodeUtil.handleScanResult(this, getAnalyticsService(), foodEditorType, intent2, i2, getSession(), intent, "food_search", this.mealName, this.searchFlowId, getCurrentActiveDate());
                return;
            case 63:
            case 128:
            case RequestCodes.MEAL_COLLECTIONS /*203*/:
                refreshDBList();
                return;
            case 159:
                if (i2 == -1) {
                    getImmHelper().hideSoftInput();
                    addOrUpdateEntry((QuickAddFood) intent.getParcelableExtra(QuickAddActivity.EXTRA_QUICK_ADD_OBJECT));
                    return;
                }
                return;
            case RequestCodes.FOOD_MULTI_ADD_EDITOR /*196*/:
                if (i2 == -1) {
                    ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).addAndLogItemsFromActivityResultData(intent.getExtras(), this.foodEditorAnalytics);
                    LocalFoodSearchFragment currentFoodSearchFragment2 = getCurrentFoodSearchFragment();
                    if (currentFoodSearchFragment2 != null) {
                        currentFoodSearchFragment2.onActivityResult(i, i2, intent);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void refreshDBList() {
        LocalFoodSearchFragment currentFoodSearchFragment = getCurrentFoodSearchFragment();
        if (currentFoodSearchFragment != null) {
            currentFoodSearchFragment.fetchDBList(true);
        }
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getFoodSearchScreenAdUnitValue();
    }

    private boolean checkShowRestaurantLoggingInterstitial() {
        if (((LocalSettingsService) this.localSettingsService.get()).isNewUser() || !ConfigUtils.showRLFlow(getConfigService()) || ((RestaurantLoggingSettingsService) this.restaurantLoggingSettingsService.get()).wasRestaurantLoggingInterstitialDisplayed()) {
            return false;
        }
        getNavigationHelper().finishActivityAfterNavigation().withIntent(RestaurantLoggingInterstitial.newStartIntent(this, Extras.fromIntent(getIntent()))).startActivity();
        ((RestaurantLoggingSettingsService) this.restaurantLoggingSettingsService.get()).setRestaurantLoggingInterstitialDisplayed(true);
        return true;
    }

    private boolean isViewingOnlineSearch() {
        LocalFoodSearchFragment currentFoodSearchFragment = getCurrentFoodSearchFragment();
        return currentFoodSearchFragment != null && currentFoodSearchFragment.isSearchFragmentVisible();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(BUNDLE_EXTRA_MULTI_MODE_ON, getMultiAddFoodHelper().isMultiAddModeOn());
        bundle.putInt(BUNDLE_EXTRA_SELECTED_TAB, this.foodSearchTabs[this.contentPager.getCurrentItem()].getTabId());
        super.onSaveInstanceState(bundle);
    }

    private void setupListeners() {
        this.foodSearchText.setTextWatcherListener(this.textWatcher);
        this.foodSearchText.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return FoodSearchActivity.lambda$setupListeners$3(FoodSearchActivity.this, textView, i, keyEvent);
            }
        });
        this.scanBtn.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FoodSearchActivity.this.startBarcodeScanner();
            }
        });
        this.offlineSearchContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FoodSearchActivity.this.getNavigationHelper().withIntent(OfflineSearchNote.newStartIntent(FoodSearchActivity.this)).startActivity(43);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r5.getAction() == 0) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ boolean lambda$setupListeners$3(com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity r2, android.widget.TextView r3, int r4, android.view.KeyEvent r5) {
        /*
            r3 = 0
            if (r5 != 0) goto L_0x0004
            return r3
        L_0x0004:
            r0 = 1
            if (r4 == 0) goto L_0x000a
            r1 = 3
            if (r4 != r1) goto L_0x001b
        L_0x000a:
            int r4 = r5.getAction()
            if (r4 != r0) goto L_0x0014
            r2.onSearchClicked()
            goto L_0x001c
        L_0x0014:
            int r4 = r5.getAction()
            if (r4 != 0) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.lambda$setupListeners$3(com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity, android.widget.TextView, int, android.view.KeyEvent):boolean");
    }

    private void onSearchClicked() {
        toggleOfflineTextVisibility();
        LocalFoodSearchFragment currentFoodSearchFragment = getCurrentFoodSearchFragment();
        if (currentFoodSearchFragment != null) {
            currentFoodSearchFragment.onSearchClicked();
        }
    }

    private boolean shouldShowMealOptions(FoodSearchTab foodSearchTab) {
        return foodSearchTab == FoodSearchTab.FREQUENT || foodSearchTab == FoodSearchTab.RECENT;
    }

    private FoodSearchTab getCurrentFoodSearchTab() {
        return this.foodSearchTabs[this.contentPager.getCurrentItem()];
    }

    /* access modifiers changed from: private */
    public void startBarcodeScanner() {
        getNavigationHelper().withIntent(BarcodeScannerActivity.newStartIntent(this, "food_search", getCurrentActiveDate())).startActivity(62);
        getFoodSearchAnalyticsHelper().reportBarcodeEvent();
    }

    private void setupTabs() {
        int i = 0;
        this.foodSearchTabs = ((CountryService) this.countryServiceLazy.get()).getCurrentLanguage().equalsIgnoreCase("en") ^ true ? FOOD_SEARCH_TABS_CONSOLIDATED : new FoodSearchTab[]{FoodSearchTab.RECENT, FoodSearchTab.FREQUENT, FoodSearchTab.MY_FOODS, FoodSearchTab.MEALS, FoodSearchTab.RECIPES};
        this.tabLabels = new String[this.foodSearchTabs.length];
        while (true) {
            FoodSearchTab[] foodSearchTabArr = this.foodSearchTabs;
            if (i < foodSearchTabArr.length) {
                this.tabLabels[i] = getString(foodSearchTabArr[i].getLabelResId());
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public FoodSearchAnalyticsHelper getFoodSearchAnalyticsHelper() {
        return (FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get();
    }

    private void setupPager() {
        this.contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        this.contentPager.setAdapter(this.contentPagerAdapter);
        this.contentPager.addOnPageChangeListener(this.contentPageChangeListener);
        TabLayout tabLayout = this.newTabContainer;
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(this.contentPager);
        }
    }

    @Subscribe
    public void onSearchItemClickedEvent(SearchItemClickedEvent searchItemClickedEvent) {
        onSearchClicked();
    }

    @Subscribe
    public void onQuickAddCalorieAddedEvent(QuickAddCalorieAddedEvent quickAddCalorieAddedEvent) {
        float calories = quickAddCalorieAddedEvent.getCalories();
        if (calories <= BitmapDescriptorFactory.HUE_RED || calories > 99999.0f) {
            CalorieAddErrorDialogFragment newInstance = CalorieAddErrorDialogFragment.newInstance(Dialogs.OUT_OF_RANGE_ERROR_DIALOG);
            if (newInstance != null) {
                newInstance.show(getSupportFragmentManager(), Fragments.CALORIES_ADD_ERROR_DIALOG);
            }
            return;
        }
        addOrUpdateQuickFoodEntry(FoodEntry.quickAddedCaloriesFoodEntry(calories, this.mealName, getSession(), (DbConnectionManager) this.dbConnectionManager.get()));
    }

    @Subscribe
    public void onNavigateToQuickAddEvent(NavigateToQuickAddEvent navigateToQuickAddEvent) {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.QuickAddMacros);
        if (featureAvailability == Availability.Available || featureAvailability == Availability.Locked) {
            getNavigationHelper().withIntent(QuickAddActivity.newStartIntent(getActivity(), new com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.Extras().setMealName(this.mealName))).startActivity(159);
        } else if (featureAvailability == Availability.Revoked) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.premium_feature_revoked));
        } else {
            QuickAddCaloriesDialogFragment newInstance = QuickAddCaloriesDialogFragment.newInstance("", this.mealName);
            if (newInstance != null) {
                newInstance.show(getSupportFragmentManager(), Fragments.QUICK_ADD_DIALOG);
            }
        }
        getAnalyticsService().reportEvent(EVENT_QUICK_ADD_CLICKED, MapUtil.createMap("source", navigateToQuickAddEvent.getSource()));
    }

    @Subscribe
    public void onUpdateMultiAddStatusEvent(UpdateMultiAddStatusEvent updateMultiAddStatusEvent) {
        updateMultiAddSelectionCount();
    }

    @Subscribe
    public void onUpdateFoodSearchBreadcrumbEvent(UpdateFoodSearchBreadcrumbEvent updateFoodSearchBreadcrumbEvent) {
        getFoodSearchAnalyticsHelper().updateFoodSearchBreadcrumb(updateFoodSearchBreadcrumbEvent.getValue());
    }

    @Subscribe
    public void onMealFilterChangedEvent(MealFilterChangedEvent mealFilterChangedEvent) {
        refreshDBList();
    }

    private void addOrUpdateEntry(QuickAddFood quickAddFood) {
        addOrUpdateQuickFoodEntry(FoodEntry.quickAddedPremiumFoodEntry(getSession().getUser(), quickAddFood, this.mealName, (DbConnectionManager) this.dbConnectionManager.get()));
    }

    private void addOrUpdateQuickFoodEntry(FoodEntry foodEntry) {
        if (foodEntry == null) {
            postEvent(new AlertEvent(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.QUICK_ADD_FAIL, this.userEnergyService.get())));
        }
        MultiAddFoodHelper multiAddFoodHelper2 = getMultiAddFoodHelper();
        if (multiAddFoodHelper2.isMultiAddModeOn()) {
            multiAddFoodHelper2.addItem(foodEntry);
            onUpdateMultiAddStatusEvent(null);
            return;
        }
        ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().addFoodEntry(foodEntry);
        ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        getNavigationHelper().withClearTopAndSingleTop().withIntent(Diary.newStartIntentWithReferrer(this, com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED)).startActivity();
    }

    /* access modifiers changed from: private */
    public MultiAddFoodHelper getMultiAddFoodHelper() {
        return (MultiAddFoodHelper) this.multiAddFoodHelper.get();
    }

    /* access modifiers changed from: private */
    public void updateMultiAddSelectionCount() {
        int i = getMultiAddFoodHelper().totalItemCount();
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.setTitle(getString(i > 0 ? R.string.number_selected : R.string.select_item, new Object[]{Integer.valueOf(i)}));
        }
        invalidateActionMode();
    }

    private void packageMealIngredientsAsFoodEntriesAndFinish(Intent intent) {
        Intent intent2 = new Intent();
        ArrayList arrayList = new ArrayList();
        FoodEntry foodEntry = (FoodEntry) BundleUtils.getParcelable(intent.getExtras(), "food_entry", FoodEntry.class.getClassLoader());
        ArrayList parcelableArrayList = BundleUtils.getParcelableArrayList(intent.getExtras(), MealEditorMixin.EXTRA_FOOD_ENTRIES, FoodEntry.class.getClassLoader());
        if (CollectionUtils.notEmpty((Collection<?>) parcelableArrayList)) {
            arrayList.addAll(parcelableArrayList);
        }
        if (foodEntry != null) {
            arrayList.add(foodEntry);
        }
        intent2.putExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, arrayList);
        setResult(-1, intent2);
        finish();
    }

    /* access modifiers changed from: private */
    public void saveEntriesToMeal() {
        Intent intent = new Intent();
        intent.putExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, (ArrayList) ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).getAllSelectedItemsAsFoodEntries(this.mealIngredientMapper, (DiaryService) this.diaryService.get(), getSession()));
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void saveEntriesToDiary(@android.support.annotation.Nullable Date date, @android.support.annotation.Nullable TimestampOption timestampOption) {
        getMultiAddFoodHelper().addAllSelectedEntriesToDiaryWithMealName(this.mealName, this.mealIngredientMapper, (DiaryService) this.diaryService.get(), getSession(), date);
        getFoodSearchAnalyticsHelper().fireFoodLoggedFromMultiAdd(this.searchFlowId, this.mealName, getMultiAddFoodHelper().getMealAndRecipeFoodCount(), timestampOption != null ? TimeValue.fromTimestampOption(timestampOption) : null);
        getNavigationHelper().finishActivityAfterNavigation().setResult(-1).withIntent(Diary.newStartIntentWithReferrer(this, com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED)).withClearTopAndSingleTop().startActivity();
    }

    /* access modifiers changed from: private */
    public void setBehaviorBasedOnTab(int i) {
        if (this.contentPagerAdapter.getFragmentAtPosition(i) != null) {
            MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        }
    }

    /* access modifiers changed from: private */
    public String getCurrentQueryString() {
        return Strings.toString(this.foodSearchText.getText());
    }

    /* access modifiers changed from: private */
    public int getTabCount() {
        return this.foodSearchTabs.length;
    }

    private void updateVisibleFragmentQueryStringIfNotDoingOnlineSearch() {
        LocalFoodSearchFragment currentFoodSearchFragment = getCurrentFoodSearchFragment();
        if (currentFoodSearchFragment != null && !currentFoodSearchFragment.isSearchFragmentVisible()) {
            updateVisibleFragmentQueryString();
        }
    }

    /* access modifiers changed from: private */
    public void updateVisibleFragmentQueryString() {
        LocalFoodSearchFragment currentFoodSearchFragment = getCurrentFoodSearchFragment();
        if (currentFoodSearchFragment != null) {
            currentFoodSearchFragment.updateQuery(getCurrentQueryString());
        }
    }

    private LocalFoodSearchFragment getCurrentFoodSearchFragment() {
        return this.contentPagerAdapter.getFragmentAtPosition(this.contentPager.getCurrentItem());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        menu.add(0, 100, 0, R.string.multi_add).setShowAsAction(6);
        addOverflowMenuIcons(menu);
        return true;
    }

    /* access modifiers changed from: private */
    public void addOverflowMenuIcons(Menu menu) {
        if (!getCountryService().isCurrentCountryCJK()) {
            menu.add(0, 101, 0, R.string.sort_order).setShowAsAction(0);
        }
        if (shouldShowMealOptions(getCurrentFoodSearchTab())) {
            menu.add(0, 102, 0, R.string.meal_filter).setShowAsAction(0);
        }
        if (!this.isMealFoodCreationFlow) {
            menu.add(0, 103, 0, R.string.quick_add).setShowAsAction(0);
            menu.add(0, 104, 0, R.string.new_food).setShowAsAction(0);
            menu.add(0, 105, 0, R.string.create_new_meal).setShowAsAction(0);
            menu.add(0, 106, 0, R.string.new_recipe).setShowAsAction(0);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return handleOptionMenuItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (str.equals(SortOrderDialog.TAG)) {
            this.disposable.add(((SortOrderDialog) dialogFragment).getSortOrderSubject().subscribe((Consumer<? super T>) new Consumer() {
                public final void accept(Object obj) {
                    FoodSearchActivity.this.refreshDBList();
                }
            }));
        }
        return super.onRebindDialogFragment(dialogFragment, str);
    }

    /* access modifiers changed from: private */
    public boolean handleOptionMenuItemSelected(MenuItem menuItem) {
        String str = null;
        switch (menuItem.getItemId()) {
            case 100:
                showMultiAddMode();
                break;
            case 101:
                str = "sort_order";
                SortOrderDialog newInstance = SortOrderDialog.newInstance(getCurrentFoodSearchTab().getTabId(), this.mealName);
                this.disposable.add(newInstance.getSortOrderSubject().subscribe((Consumer<? super T>) new Consumer() {
                    public final void accept(Object obj) {
                        FoodSearchActivity.this.refreshDBList();
                    }
                }));
                showDialogFragment(newInstance, SortOrderDialog.TAG);
                break;
            case 102:
                str = ANALYTIC_ATTRIBUTE_MEAL_FILTER;
                showDialogFragment(MealFilterDialog.newInstance(getCurrentFoodSearchTab().getTabId(), this.mealName), MealFilterDialog.TAG);
                break;
            case 103:
                str = "quick_add";
                onNavigateToQuickAddEvent(new NavigateToQuickAddEvent(NavigateToQuickAddEvent.SOURCE_OVERFLOW_MENU));
                break;
            case 104:
                str = ANALYTIC_ATTRIBUTE_CREATE_FOOD;
                finishActionMode();
                getNavigationHelper().withIntent(AddFood.newStartIntent(this, this.mealName, true)).startActivity(53);
                break;
            case 105:
                String str2 = ANALYTIC_ATTRIBUTE_CREATE_MEAL;
                finishActionMode();
                getNavigationHelper().withIntent(FoodEditorActivity.newMealItemEditorIntent(getActivity(), ((FoodSearchActivityFactory) this.foodSearchRouter.get()).getFoodSearchActivityIntent(getActivity(), Extras.fromIntent(getIntent()).setMealCreated(true)), null, null, "food_search")).startActivity(RequestCodes.FOOD_EDITOR);
                str = str2;
                break;
            case 106:
                str = ANALYTIC_ATTRIBUTE_ADD_RECIPES;
                postEvent(new CreateNewRecipeEvent(this.mealName, getSession().getUser().getActiveDate(), StartScreen.FoodSearch));
                break;
            default:
                return false;
        }
        if (str != null) {
            getAnalyticsService().reportEvent(Events.SEARCH_FOOD_SCREEN_OVERFLOW_TAP_ITEM, new Builder().put(Attributes.ITEM, str).build());
        }
        return true;
    }

    private void finishActionMode() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.finish();
        }
    }

    private void showMultiAddMode() {
        if (this.actionMode == null) {
            startActionMode(new MultiAddActionMode(this));
            MaterialUtils.cleanActionModeDoneText(this);
        }
    }

    /* access modifiers changed from: private */
    public void invalidateActionMode() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.invalidate();
        }
    }
}
