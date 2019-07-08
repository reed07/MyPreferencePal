package com.myfitnesspal.feature.restaurantlogging.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.restaurantlogging.event.UnableToFetchMenuItemInfoEvent;
import com.myfitnesspal.feature.restaurantlogging.listener.OnMenuItemActionListener;
import com.myfitnesspal.feature.restaurantlogging.model.MenuItemEditorBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.MenusActivityBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuSection;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMultiAddMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.feature.restaurantlogging.task.AddMultiAddMenuItemsToDiaryTask;
import com.myfitnesspal.feature.restaurantlogging.task.CreateMenuItemMatchesTask;
import com.myfitnesspal.feature.restaurantlogging.task.GetMenuItemsTask.CompletedEvent;
import com.myfitnesspal.feature.restaurantlogging.ui.adapter.MenuPagerAdapter;
import com.myfitnesspal.feature.restaurantlogging.ui.dialog.NotifyRequestMenuDialogFragment;
import com.myfitnesspal.feature.restaurantlogging.ui.view.MenusListView;
import com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.MenusViewModel;
import com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.MenusViewModel.Property;
import com.myfitnesspal.feature.restaurantlogging.util.MenuItemFetchHelper;
import com.myfitnesspal.feature.restaurantlogging.util.MultiAddMenuItemHelper;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.CustomSwipeableViewPager;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

public class MenusActivity extends MfpActivity implements OnMenuItemActionListener {
    public static final String DEEP_LINK_SOURCE = "deep_link";
    public static final String EXTRA_BUNDLE = "extra_bundle";
    public static final String EXTRA_MENU_ITEM_RESULT = "menu_item_result";
    public static final String EXTRA_MULTI_ADD_MODE_ON = "multi_add_mode_on";
    public static final String FOOD_SEARCH_SOURCE = "food_search";
    private static final int MAX_TAB_COUNT_FOR_FIXED = 3;
    private static final int MENU_MULTI_ADD = 1002;
    private static final int MENU_SEARCH = 1001;
    private static final int MINIMUM_NUM_MENUS_TO_SHOW_TAB = 2;
    public static final String RESTAURANT_LOGGING_SOURCE = "restaurant_logging";
    /* access modifiers changed from: private */
    public ActionMode actionMode;
    /* access modifiers changed from: private */
    public MenusActivityBundleData activityBundleData;
    @Inject
    Lazy<Bus> bus;
    private State currentState;
    @Inject
    Lazy<DiaryService> diaryService;
    @BindView(2131362460)
    View emptyButton;
    @BindView(2131362467)
    View emptyView;
    private Handler handler;
    @BindView(2131362946)
    View loadingMenus;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    /* access modifiers changed from: private */
    public MenuItemFetchHelper menuItemFetchHelper;
    /* access modifiers changed from: private */
    public MenuPagerAdapter menuPagerAdapter;
    @BindView(2131363052)
    View menuParentView;
    @BindView(2131363050)
    EditText menuSearchEditText;
    @Inject
    Lazy<MenuService> menuService;
    /* access modifiers changed from: private */
    public List<MfpMenu> menus;
    @Inject
    Lazy<MultiAddMenuItemHelper> multiAddMenuItemHelper;
    private SimpleOnPageChangeListener pageChangeListener = new SimpleOnPageChangeListener() {
        public void onPageSelected(int i) {
            MenusActivity.this.menuItemFetchHelper.fetchMenuItemInfo();
            MenusActivity.this.reportMenuViewed();
        }
    };
    private Runnable reportMenuViewedRunnable = new Runnable() {
        public void run() {
            if (!CollectionUtils.isEmpty((Collection<?>) MenusActivity.this.menus)) {
                MfpMenu mfpMenu = (MfpMenu) MenusActivity.this.menus.get(MenusActivity.this.viewPager.getCurrentItem());
                List<MfpMenuSection> sections = mfpMenu.getSections();
                int i = 0;
                for (MfpMenuSection items : sections) {
                    i += CollectionUtils.size((Collection<?>) items.getItems());
                }
                ((RestaurantLoggingAnalyticsHelper) MenusActivity.this.restaurantLoggingAnalyticsHelper.get()).reportMenuViewed(mfpMenu, MenusActivity.this.venue, MenusActivity.this.getMealName(), MenusActivity.this.activityBundleData.getFlowId(), MenusActivity.this.activityBundleData.getSource(), MenusActivity.this.menus.size(), i, sections.size());
            }
        }
    };
    @Inject
    Lazy<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelper;
    @Inject
    Lazy<RestaurantLoggingSettingsService> restaurantLoggingSettingsService;
    @BindView(2131363775)
    TabLayout tabLayout;
    private TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            MenusListView access$1000 = MenusActivity.this.getCurrentListView();
            if (access$1000 != null) {
                access$1000.setFilterString(editable.toString());
                MenusActivity.this.menuItemFetchHelper.fetchMenuItemInfo();
            }
        }
    };
    @BindView(2131363833)
    TextView titleTextView;
    private Runnable updateViewPagerViewAndFetchMenuItemInfo = new Runnable() {
        public void run() {
            MenusActivity.this.displayMenuItems();
            MenusActivity.this.menuItemFetchHelper.fetchMenuItemInfo();
        }
    };
    /* access modifiers changed from: private */
    public Venue venue;
    @Inject
    Lazy<VenueService> venueService;
    private MenusViewModel viewModel;
    @BindView(2131362213)
    CustomSwipeableViewPager viewPager;

    private class MultiAddActionMode implements Callback {
        private static final int ACTION_ADD_ALL = 1001;

        private MultiAddActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenusActivity.this.actionMode = actionMode;
            ((MultiAddMenuItemHelper) MenusActivity.this.multiAddMenuItemHelper.get()).enable();
            MenusActivity.this.updateActionModeTitle();
            MenusActivity.this.menuPagerAdapter.notifyListViews();
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            menu.clear();
            boolean z = ((MultiAddMenuItemHelper) MenusActivity.this.multiAddMenuItemHelper.get()).getItemCount() > 0;
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.add_selected).setIcon(z ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp).setEnabled(z), 2);
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != 1001) {
                return false;
            }
            MenusActivity.this.addAllSelectedItems();
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            ((MultiAddMenuItemHelper) MenusActivity.this.multiAddMenuItemHelper.get()).disable();
            MenusActivity.this.menuPagerAdapter.notifyListViews();
            MenusActivity.this.actionMode = null;
        }
    }

    private enum State {
        Blank,
        Empty,
        Loading,
        Viewing,
        Filtering
    }

    public int getCustomToolbarLayoutResId() {
        return R.layout.menus_toolbar;
    }

    @Subscribe
    public void onUnableToFetchMenuItemInfoEvent(UnableToFetchMenuItemInfoEvent unableToFetchMenuItemInfoEvent) {
    }

    public static Intent newStartIntent(Context context, MenusActivityBundleData menusActivityBundleData) {
        return new Intent(context, MenusActivity.class).putExtra(EXTRA_BUNDLE, menusActivityBundleData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.menus);
        component().inject(this);
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        this.activityBundleData = (MenusActivityBundleData) ExtrasUtils.getParcelable(getIntent(), EXTRA_BUNDLE, MenusActivityBundleData.class.getClassLoader());
        this.venue = this.activityBundleData.getVenue();
        initAdapter();
        setTitle(this.venue.getName());
        this.menuItemFetchHelper = new MenuItemFetchHelper(this.viewPager, getRunner(), this.menuService, this.bus);
        this.menuItemFetchHelper.restoreInstanceState(bundle);
        if (this.venue.hasMenu()) {
            checkShowMenuEstimateDialog();
            setListeners();
            setState(State.Loading);
            initViewModel();
        } else {
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportNoMenuDES(this.venue, this.activityBundleData.getFlowId());
            setState(State.Empty);
            setEmptyButtonListener();
        }
        if (BundleUtils.getBoolean(bundle, EXTRA_MULTI_ADD_MODE_ON) || ((LocalSettingsService) this.localSettingsService.get()).getMultiAddToggleOnByDefault()) {
            getHandler().post(new Runnable() {
                public void run() {
                    MenusActivity.this.showMultiAddMode();
                }
            });
        } else {
            ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).disable();
        }
    }

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        return this.handler;
    }

    private void initAdapter() {
        this.menuPagerAdapter = new MenuPagerAdapter(this, this);
        this.viewPager.setAdapter(this.menuPagerAdapter);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MenuPagerAdapter menuPagerAdapter2 = this.menuPagerAdapter;
        if (menuPagerAdapter2 != null) {
            menuPagerAdapter2.notifyListViews();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        getImmHelper().hideSoftInput();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        MenuItemFetchHelper menuItemFetchHelper2 = this.menuItemFetchHelper;
        if (menuItemFetchHelper2 != null) {
            menuItemFetchHelper2.destroy();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_MULTI_ADD_MODE_ON, this.actionMode != null);
        this.menuItemFetchHelper.saveInstanceState(bundle);
    }

    private void checkShowMenuEstimateDialog() {
        if (!((RestaurantLoggingSettingsService) this.restaurantLoggingSettingsService.get()).wasMenuEstimatedDialogDisplayed()) {
            new MfpAlertDialogBuilder(this).setTitle((int) R.string.menu_estimates_dialog_header).setMessage((int) R.string.menu_estimates_dialog_body).setPositiveButton((int) R.string.ok, (OnClickListener) null).setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    ((RestaurantLoggingSettingsService) MenusActivity.this.restaurantLoggingSettingsService.get()).setMenuEstimatesDialogDisplayed(true);
                }
            }).setCancelable(false).setCanceledOnTouchOutside(false).show();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!CollectionUtils.isEmpty((Collection<?>) this.menus) && !isBusy(16) && this.currentState == State.Viewing) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1002, 0, R.string.multi_add), 6);
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.searchBtn).setIcon(R.drawable.ic_search_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1001) {
            setState(State.Filtering);
            ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportSearchClicked(this.venue, this.activityBundleData.getFlowId(), getMealName());
        } else if (menuItem.getItemId() == 1002) {
            showMultiAddMode();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void showMultiAddMode() {
        if (this.actionMode == null) {
            startActionMode(new MultiAddActionMode());
            MaterialUtils.cleanActionModeDoneText(this);
        }
    }

    public void onUpPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        if (this.currentState == State.Filtering) {
            setState(State.Viewing);
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 183 || i2 != -1) {
            return;
        }
        if (((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).isEnabled()) {
            final MfpMenuItem mfpMenuItem = (MfpMenuItem) ExtrasUtils.getParcelable(intent, EXTRA_MENU_ITEM_RESULT, MfpMenuItem.class.getClassLoader());
            getHandler().post(new Runnable() {
                public void run() {
                    MenusActivity.this.updateMenuItemInCurrentData(mfpMenuItem);
                }
            });
        } else if (ExtrasUtils.containsKey(intent, "food_entry")) {
            setResult(-1, intent);
            finish();
        } else {
            showDiarySnackBar(R.string.food_logged);
        }
    }

    /* access modifiers changed from: private */
    public void updateMenuItemInCurrentData(MfpMenuItem mfpMenuItem) {
        if (!((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).hasReachedLimit()) {
            for (MfpMenu sections : this.menus) {
                for (MfpMenuSection items : sections.getSections()) {
                    Iterator it = items.getItems().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        MfpMenuItem mfpMenuItem2 = (MfpMenuItem) it.next();
                        if (Strings.equals(mfpMenuItem.getId(), mfpMenuItem2.getId())) {
                            mfpMenuItem2.setMfpMenuItem(mfpMenuItem);
                            ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).addItem(mfpMenuItem2);
                            updateActionModeTitle();
                            break;
                        }
                    }
                }
            }
        } else {
            showToastForMultiAddLimit();
        }
        this.menuPagerAdapter.notifyListViews();
    }

    private void initViewModel() {
        this.viewModel = (MenusViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (MenusViewModel) setViewModel(new MenusViewModel(getRunner(), this.venueService));
        }
        List menus2 = this.viewModel.getMenus();
        if (menus2 != null) {
            handleMenus(menus2);
            return;
        }
        this.viewModel.load(this.venue.getId());
    }

    private void showDiarySnackBar(int i) {
        new SnackbarBuilder(this.menuParentView).setMessage(i).setDuration(0).setAction((int) R.string.view_in_diary, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                MenusActivity.this.navigateToDiary();
                ((RestaurantLoggingAnalyticsHelper) MenusActivity.this.restaurantLoggingAnalyticsHelper.get()).reportFlowEndDES(MenusActivity.this.activityBundleData.getFlowId());
            }
        }).setActionTextColorResId(R.color.snackbar_action_text).showWithDelay();
    }

    /* access modifiers changed from: private */
    public void navigateToDiary() {
        getNavigationHelper().withClearTopAndSingleTop().withIntent(Diary.newStartIntent(this)).startActivity();
    }

    public void setTitle(CharSequence charSequence) {
        this.titleTextView.setText(charSequence);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.MENUS_LIST_FETCHED) {
            handleMenus(this.viewModel.getMenus());
        } else if (i == Property.MENUS_LIST_FETCH_FAILED) {
            setState(State.Blank);
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(null, getString(R.string.unable_fetch_menu), getString(R.string.ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MenusActivity.this.finish();
                }
            }, null, null);
        }
    }

    public void onItemCheckToggled(CompoundButton compoundButton, MfpMenuItem mfpMenuItem, boolean z) {
        if (!z || !((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).hasReachedLimit()) {
            if (CollectionUtils.isEmpty((Collection<?>) mfpMenuItem.getMatches())) {
                onItemClicked(mfpMenuItem);
            } else {
                if (z) {
                    ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).addItem(mfpMenuItem);
                } else {
                    ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).removeItem(mfpMenuItem);
                }
                updateActionModeTitle();
            }
            return;
        }
        showToastForMultiAddLimit();
        compoundButton.setChecked(false);
    }

    private void showToastForMultiAddLimit() {
        postEvent(new AlertEvent(getString(R.string.reached_multi_add_menu_limit)).asToast());
    }

    public void onItemClicked(MfpMenuItem mfpMenuItem) {
        String str;
        String str2;
        String mealName = this.activityBundleData.getMealName();
        Venue venue2 = this.activityBundleData.getVenue();
        String name = venue2.getName();
        MfpMenu menuForMenuItem = getMenuForMenuItem(mfpMenuItem);
        if (menuForMenuItem != null) {
            String name2 = menuForMenuItem.getName();
            str2 = getSectionNameForSectionIdFromMenu(menuForMenuItem, mfpMenuItem.getSectionId());
            str = name2;
        } else {
            str2 = null;
            str = null;
        }
        NavigationHelper withContext = getNavigationHelper().withContext(this);
        MenuItemEditorBundleData menuItemEditorBundleData = new MenuItemEditorBundleData(mfpMenuItem, this.activityBundleData.getFlowId(), venue2, str, str2, this.activityBundleData.getMealName(), this.activityBundleData.getDate());
        withContext.withIntent(FoodEditorActivity.newRestaurantMenuItemEditorIntent(this, menuItemEditorBundleData, ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).isEnabled(), this.activityBundleData.isCurrentlyInMealCreationFlow())).startActivity(RequestCodes.FOOD_EDITOR);
        getAnalyticsService().reportFoodLookup(CollectionUtils.nameValuePairsToMap("source", SearchSource.RESTAURANT_LOGGING.getTitle(), "flow_id", this.activityBundleData.getFlowId()));
        ((RestaurantLoggingAnalyticsHelper) this.restaurantLoggingAnalyticsHelper.get()).reportMenuItemClicked(name, mealName, str);
    }

    private MfpMenu getMenuForMenuItem(MfpMenuItem mfpMenuItem) {
        for (MfpMenu mfpMenu : this.menus) {
            if (Strings.equals(mfpMenuItem.getMenuId(), mfpMenu.getId())) {
                return mfpMenu;
            }
        }
        return null;
    }

    private String getSectionNameForSectionIdFromMenu(MfpMenu mfpMenu, String str) {
        for (MfpMenuSection mfpMenuSection : mfpMenu.getSections()) {
            if (Strings.equals(mfpMenuSection.getId(), str)) {
                return mfpMenuSection.getName();
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void updateActionModeTitle() {
        if (this.actionMode != null) {
            int itemCount = ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).getItemCount();
            this.actionMode.setTitle(getString(itemCount > 0 ? R.string.number_selected : R.string.select_item, new Object[]{Integer.valueOf(itemCount)}));
            this.actionMode.invalidate();
        }
    }

    private void handleMenus(List<MfpMenu> list) {
        this.menus = list;
        if (CollectionUtils.notEmpty((Collection<?>) this.menus)) {
            setupView(this.menus);
            setState(State.Viewing);
            this.menuItemFetchHelper.fetchMenuItemInfo();
            invalidateOptionsMenu();
            reportMenuViewed();
            return;
        }
        setState(State.Empty);
    }

    /* access modifiers changed from: private */
    public void reportMenuViewed() {
        getHandler().post(this.reportMenuViewedRunnable);
    }

    /* access modifiers changed from: private */
    public String getMealName() {
        return this.activityBundleData.getMealName();
    }

    private void setState(State state) {
        if (this.currentState != state) {
            this.currentState = state;
            setViewsVisibility();
            invalidateOptionsMenu();
            getHandler().post(this.updateViewPagerViewAndFetchMenuItemInfo);
        }
    }

    private void setViewsVisibility() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        switch (this.currentState) {
            case Blank:
                z7 = false;
                z6 = false;
                z5 = false;
                z4 = false;
                z3 = true;
                z2 = false;
                z = false;
                break;
            case Empty:
                z7 = false;
                z6 = false;
                z5 = false;
                z4 = false;
                z3 = true;
                z2 = false;
                z = true;
                break;
            case Loading:
                z7 = false;
                z6 = false;
                z5 = false;
                z4 = false;
                z3 = true;
                z2 = true;
                z = false;
                break;
            case Filtering:
                z7 = true;
                z6 = false;
                z5 = false;
                z4 = true;
                z3 = false;
                z2 = false;
                z = false;
                break;
            case Viewing:
                z7 = true;
                z6 = true;
                z5 = true;
                z4 = false;
                z3 = true;
                z2 = false;
                z = false;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported state. State: ");
                sb.append(this.currentState);
                throw new IllegalStateException(sb.toString());
        }
        ViewUtils.setVisible(z7, this.viewPager);
        ViewUtils.setVisible(z6 && CollectionUtils.size((Collection<?>) this.menus) >= 2, this.tabLayout);
        ViewUtils.setVisible(z4, this.menuSearchEditText);
        ViewUtils.setVisible(z3, this.titleTextView);
        ViewUtils.setVisible(z2, this.loadingMenus);
        ViewUtils.setVisible(z, this.emptyView);
        this.viewPager.setSwipeEnabled(z5);
        if (z4) {
            this.menuSearchEditText.requestFocus();
            getImmHelper().showSoftInput(this.menuSearchEditText);
            return;
        }
        getImmHelper().hideSoftInput((View) this.menuSearchEditText);
    }

    /* access modifiers changed from: private */
    public void displayMenuItems() {
        if (this.menus != null) {
            MenusListView currentListView = getCurrentListView();
            if (currentListView != null) {
                if (this.currentState == State.Filtering) {
                    currentListView.setMenus(this.menus);
                } else {
                    currentListView.setMenu((MfpMenu) this.menus.get(this.viewPager.getCurrentItem()));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public MenusListView getCurrentListView() {
        CustomSwipeableViewPager customSwipeableViewPager = this.viewPager;
        return (MenusListView) ViewUtils.findById(customSwipeableViewPager.findViewWithTag(MenuPagerAdapter.getPageTag(customSwipeableViewPager.getCurrentItem())), R.id.menu_list_view);
    }

    private void setListeners() {
        this.viewPager.addOnPageChangeListener(this.pageChangeListener);
        this.menuSearchEditText.addTextChangedListener(this.textWatcher);
        this.menuSearchEditText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return true;
            }
        });
    }

    private void setEmptyButtonListener() {
        this.emptyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MenusActivity.this.showDialogFragment(NotifyRequestMenuDialogFragment.newInstance(MenusActivity.this.venue), NotifyRequestMenuDialogFragment.class.getName());
            }
        });
    }

    private void setupView(List<MfpMenu> list) {
        this.menuPagerAdapter.clearCurrentAndAddNewMenus(list);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setTabMode(this.menuPagerAdapter.getCount() <= 3 ? 1 : 0);
    }

    @Subscribe
    public void onGetMenuItemsTaskCompletedEvent(CompletedEvent completedEvent) {
        this.menuItemFetchHelper.getMenuItemsTaskCompletedEvent(completedEvent);
    }

    @Subscribe
    public void onCreateMenuItemMatchesTaskCompletedEvent(CreateMenuItemMatchesTask.CompletedEvent completedEvent) {
        if (completedEvent.getFailure() == null) {
            List<MfpMultiAddMenuItem> list = (List) completedEvent.getResult();
            String mealName = getMealName();
            Date date = this.activityBundleData.getDate();
            if (this.activityBundleData.isCurrentlyInMealCreationFlow()) {
                ArrayList arrayList = new ArrayList();
                for (MfpMultiAddMenuItem mfpFood : list) {
                    arrayList.add(mfpFood.toMfpFood().toFoodEntry(getSession().getUser(), mealName, date));
                }
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, arrayList);
                setResult(-1, intent);
                finish();
                return;
            }
            new AddMultiAddMenuItemsToDiaryTask(this.diaryService, list, mealName, date).run(getRunner());
            return;
        }
        postEvent(new AlertEvent(getString(R.string.menu_multi_add_request_failed)));
    }

    @Subscribe
    public void onAddMultiAddMenuItemsToDiaryTaskCompletedEvent(AddMultiAddMenuItemsToDiaryTask.CompletedEvent completedEvent) {
        if (completedEvent.getFailure() == null) {
            navigateToDiary();
            ActionMode actionMode2 = this.actionMode;
            if (actionMode2 != null) {
                actionMode2.finish();
            }
        } else {
            postEvent(new AlertEvent(getString(R.string.menu_multi_add_request_failed)));
        }
        setBusy(16, false);
    }

    /* access modifiers changed from: private */
    public void addAllSelectedItems() {
        new CreateMenuItemMatchesTask(this.menuService, ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).getSelectedItems()).run(getRunner());
        setBusy(16, true);
    }
}
