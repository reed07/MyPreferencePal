package com.myfitnesspal.feature.diary.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardBase.DashboardUserType;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.diary.listener.DiaryAdapterItemActionListener;
import com.myfitnesspal.feature.diary.listener.MealHeaderTip;
import com.myfitnesspal.feature.diary.model.DiaryDayContext;
import com.myfitnesspal.feature.diary.model.DiaryViewModelBase;
import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter;
import com.myfitnesspal.feature.diary.ui.item.SectionHeaderItem;
import com.myfitnesspal.feature.diary.ui.listener.OnDiaryDayFetchedListener;
import com.myfitnesspal.feature.diary.util.DiaryDelegate;
import com.myfitnesspal.feature.diary.util.DiaryDelegate.DiaryType;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.fragment.MfpDateRestrictedFragment;
import com.myfitnesspal.shared.ui.fragment.MfpDateRestrictedFragment.ContentPagerAdapter;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.DateUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public abstract class DiaryFragmentBase extends MfpDateRestrictedFragment implements DiaryAdapterItemActionListener, OnDiaryDayFetchedListener {
    private static final String CREATE_MEAL_DIALOG_TAG = "create_meal_dialog_tag";
    private static final String VALUE_GRAM = "g";
    private static final String VALUE_PERCENT = "percent";
    private static final String VIEW_TAG_PREFIX = "diaryPage";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    @Inject
    Lazy<ConfigService> configService;
    private OnPageChangeListener contentOnPageChangeListener = new SimpleOnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageSelected(int i) {
            Calendar calendar = (Calendar) DiaryFragmentBase.this.dateList.get(i);
            DiaryFragmentBase.this.setDate(calendar.getTime());
            ((DiaryService) DiaryFragmentBase.this.diaryService.get()).clearCachedInsights();
            DiaryFragmentBase diaryFragmentBase = DiaryFragmentBase.this;
            diaryFragmentBase.notifyDataSetChangedToRecyclerView(diaryFragmentBase.getRecyclerViewForCalendarDate(calendar));
            if (DiaryFragmentBase.this.diaryDelegate.shouldShowMealGoalsCard()) {
                ((NewsFeedAnalyticsHelper) DiaryFragmentBase.this.newsFeedAnalyticsHelper.get()).reportHeroCardDisplayed(((PremiumService) DiaryFragmentBase.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealGoals) ? NewsFeedAnalyticsHelper.PREMIUM_MEAL_GOAL_CARD_TYPE : NewsFeedAnalyticsHelper.NON_PREMIUM_MEAL_GOAL_CARD_TYPE);
            }
        }
    };
    /* access modifiers changed from: private */
    public Map<Long, Integer> dateToScrollOffsetMap = new HashMap();
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryAnalyticsHelper> diaryAnalyticsHelper;
    protected DiaryDelegate diaryDelegate;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<ExerciseStringService> exerciseStringService;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    @Inject
    Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    @Inject
    Lazy<NutrientDashboardRenderer> nutrientDashboardRenderer;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    /* access modifiers changed from: private */
    public final DialogPositiveListener onMealCreatedViewClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            ((MealAnalyticsHelper) DiaryFragmentBase.this.mealAnalyticsHelper.get()).reportViewSavedMeal();
            DiaryFragmentBase.this.getNavigationHelper().withIntent(((FoodSearchActivityFactory) DiaryFragmentBase.this.foodSearchRouter.get()).getFoodSearchActivityIntent(DiaryFragmentBase.this.getActivity(), new Extras().setMealName(DiaryFragmentBase.this.getSession().getUser().getMealNames().nameForIndex(0)).shouldSelectMealTab(true))).startActivity();
        }
    };
    private OnScrollListener onRecyclerViewScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0) {
                DiaryDay diaryDay = (DiaryDay) recyclerView.getTag();
                if (diaryDay != null) {
                    DiaryFragmentBase.this.dateToScrollOffsetMap.put(Long.valueOf(diaryDay.getDate().getTime()), Integer.valueOf(((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition()));
                }
            }
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<StepService> stepsService;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    protected DiaryViewModelBase viewModel;

    /* access modifiers changed from: protected */
    public abstract void fetchDiaryDay(Calendar calendar);

    /* access modifiers changed from: protected */
    public abstract DiaryViewModelBase getViewModelInstance();

    public boolean isWeekly() {
        return false;
    }

    public void onBannerAdLoaded() {
    }

    public void onBottomRowAddFoodClick(String str) {
    }

    public void onBottomRowShowMoreActionsClick(String str) {
    }

    public void onCompleteEntryClick(String str) {
    }

    public void onEntryClick(DiaryDay diaryDay, DatabaseObject databaseObject, View view) {
    }

    public void onEntryItemCheckChanged(DatabaseObject databaseObject, boolean z) {
    }

    public boolean onEntryLongClick(DatabaseObject databaseObject) {
        return false;
    }

    public void onMealCaloriesClick(SectionHeaderItem sectionHeaderItem) {
    }

    public void onNotesClick() {
    }

    public void onNutritionClick(String str) {
    }

    public void onPromoCallActionClick(PremiumFeature premiumFeature) {
    }

    public void onSectionHeaderCheckChanged(DiaryDay diaryDay, String str, boolean z) {
    }

    public void onTimestampClick(String str, Date date) {
    }

    /* access modifiers changed from: protected */
    public boolean scrollToMostRecentlyAddedFood(DiaryDay diaryDay, DiaryAdapter diaryAdapter, RecyclerView recyclerView) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void setEmptyViewText(TextView textView) {
    }

    /* access modifiers changed from: protected */
    public void updateSelectAllButton(CompoundButton compoundButton) {
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        initializeDiaryDelegate();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.diary_fragment, viewGroup, false);
        inflate.findViewById(R.id.parentLayout).setBackgroundColor(getResources().getColor(R.color.mfp_background));
        addDateView(inflate, layoutInflater);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        initializeViewModel();
        restoreInstanceState(bundle);
        setToolbarPropertiesAndVisibility();
        onContentPagerCreated();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!CREATE_MEAL_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onMealCreatedViewClickListener);
        return true;
    }

    private void setToolbarPropertiesAndVisibility() {
        final boolean isLandscape = this.diaryDelegate.isLandscape();
        if (!isLandscape) {
            MaterialUtils.enableAppBarScrollIfLollipop(getActivity());
        }
        MaterialUtils.removeDefaultToolbarElevation(getActivity());
        final Toolbar toolbar = ((MfpActivity) getActivity()).getToolbar();
        toolbar.post(new Runnable() {
            public void run() {
                ViewUtils.setVisible(!isLandscape, toolbar);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 162 && i2 == -1) {
            ((NutrientDashboardRenderer) this.nutrientDashboardRenderer.get()).reset();
            onContentPagerCreated();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        DiaryViewModelBase diaryViewModelBase = this.viewModel;
        if (diaryViewModelBase != null) {
            diaryViewModelBase.setDiaryDayFetchedListener(null);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        onContentPagerCreated();
    }

    private void initializeViewModel() {
        this.viewModel = (DiaryViewModelBase) getViewModel();
        DiaryViewModelBase diaryViewModelBase = this.viewModel;
        if (diaryViewModelBase == null) {
            this.viewModel = (DiaryViewModelBase) setViewModel(getViewModelInstance());
        } else {
            diaryViewModelBase.setDiaryDayFetchedListener(this);
        }
    }

    private void restoreInstanceState(Bundle bundle) {
        this.diaryDelegate.restoreInstanceState(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.diaryDelegate.onSaveInstanceState(bundle);
    }

    private void initializeDiaryDelegate() {
        FragmentActivity activity = getActivity();
        Lazy<LocalizedStringsUtil> lazy = this.localizedStringsUtil;
        Lazy<UserEnergyService> lazy2 = this.userEnergyService;
        Lazy<ExerciseStringService> lazy3 = this.exerciseStringService;
        Lazy<NavigationHelper> lazy4 = this.navigationHelper;
        Lazy<PremiumService> lazy5 = this.premiumService;
        Lazy<UacfScheduler<SyncType>> lazy6 = this.syncScheduler;
        Lazy<DiaryService> lazy7 = this.diaryService;
        Lazy<DiaryAnalyticsHelper> lazy8 = this.diaryAnalyticsHelper;
        Lazy<ActionTrackingService> lazy9 = this.actionTrackingService;
        Lazy<AppGalleryService> lazy10 = this.appGalleryService;
        Lazy<StepService> lazy11 = this.stepsService;
        Lazy<ConfigService> lazy12 = this.configService;
        DiaryDelegate diaryDelegate2 = r0;
        DiaryDelegate diaryDelegate3 = new DiaryDelegate(activity, this, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, this.session, this.localSettingsService, this.nutrientGoalsUtil, this.newsFeedAnalyticsHelper, showingFriendDiary() ? DiaryType.Friend : DiaryType.Self, this.userApplicationSettingsService, this.dbConnectionManager, ActivityUtils.isInMultiWindow(getActivity()));
        this.diaryDelegate = diaryDelegate2;
    }

    public void onContentPagerCreated() {
        boolean isLandscape = this.diaryDelegate.isLandscape();
        ViewUtils.setVisible(!isLandscape, ((MfpActivity) getActivity()).getToolbar());
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag(Fragments.WATER_DIALOG);
        if (findFragmentByTag != null && isLandscape) {
            ((DialogFragment) findFragmentByTag).dismiss();
        }
        ViewUtils.setVisible(isLandscape, ViewUtils.findById(getView(), R.id.landscape_unit_header));
        if (this.contentPagerAdapter == null) {
            this.contentPagerAdapter = new ContentPagerAdapter();
            this.contentPager.setAdapter(this.contentPagerAdapter);
            this.contentPager.addOnPageChangeListener(this.contentOnPageChangeListener);
            setContentPageToDefaultOrPersisted();
            setDate(((Calendar) this.dateList.get(getCurrentItemIndex())).getTime());
        } else if (this.contentPager != null) {
            int currentItem = this.contentPager.getCurrentItem();
            this.contentPager.setAdapter(this.contentPagerAdapter);
            this.contentPager.setCurrentItem(currentItem);
            this.contentPagerAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void setDate(Date date) {
        loadDateFor(date);
        User user = getSession().getUser();
        user.setActiveDate(date);
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportNewDateDeltaEvent(date, user);
    }

    private void loadDateFor(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        loadDate(instance);
    }

    /* access modifiers changed from: protected */
    public void destroyPageItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyPageItem(viewGroup, i, obj);
        ((RecyclerView) ViewUtils.findById(viewGroup, R.id.diary_recycler_view)).removeOnScrollListener(this.onRecyclerViewScrollListener);
    }

    /* access modifiers changed from: protected */
    public View renderPageView(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.diary_page, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) ViewUtils.findById(inflate, R.id.diary_recycler_view);
        ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(inflate, R.id.diary_summary_container);
        View findById = ViewUtils.findById(inflate, R.id.select_all_include);
        CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(inflate, R.id.select_all);
        View view = getView();
        TextView textView = (TextView) ViewUtils.findById(inflate, R.id.empty_view);
        View findById2 = ViewUtils.findById(view, R.id.date);
        View findById3 = ViewUtils.findById(view, R.id.divider);
        View findById4 = ViewUtils.findById(view, R.id.unit_header_container);
        Calendar calendar = (Calendar) this.dateList.get(i);
        setupRecyclerView(recyclerView);
        updateSelectAllButton(compoundButton);
        setupViewAndFetchNewData(calendar, recyclerView, viewGroup2, textView, i);
        inflate.setTag(getTagForPage(calendar));
        ViewUtils.setVisible(isEditing(), findById);
        ViewUtils.setVisible(!isEditing(), viewGroup2, findById2, findById3);
        if (this.diaryDelegate.isLandscape()) {
            ViewCompat.setElevation(findById2, getResources().getDimension(R.dimen.default_material_elevation));
            setupViewForLandscape(findById4, viewGroup2, findById3);
        }
        return inflate;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(this.onRecyclerViewScrollListener);
    }

    private void setupViewForLandscape(View view, View view2, View view3) {
        setUnitForMacros();
        if (getSession().getUser().shouldDisplayDiaryMealMacros()) {
            ViewUtils.findById(getView(), R.id.landscape_unit_header).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    DiaryFragmentBase.this.onToggleMealMacrosUnitClick();
                }
            });
        }
        Energy fromInt = Energy.fromInt(getSession().getUser().getEnergyUnitPreference());
        ViewUtils.setVisible(view);
        if (fromInt == Energy.KILOJOULES) {
            TextView textView = (TextView) ViewUtils.findById(getView(), R.id.caloriesUnit);
            ((TextView) ViewUtils.findById(getView(), R.id.caloriesTitle)).setText(getString(R.string.kilojoules));
            textView.setText(getString(R.string.kj));
        }
        ViewUtils.setGone(view2);
        ViewUtils.setGone(view3);
    }

    private void setUnitForMacros() {
        String str;
        TextView textView = (TextView) ViewUtils.findById(getView(), R.id.carbsUnit);
        TextView textView2 = (TextView) ViewUtils.findById(getView(), R.id.fatUnit);
        TextView textView3 = (TextView) ViewUtils.findById(getView(), R.id.proteinUnit);
        if (((Session) this.session.get()).getUser().shouldDisplayDiaryMealMacros()) {
            str = getString(((LocalSettingsService) this.localSettingsService.get()).getMealMacrosDisplayUnit().getUnitStringResId());
        } else {
            str = getString(MealMacrosDisplayUnit.Gram.getUnitStringResId());
        }
        textView.setText(str);
        textView2.setText(str);
        textView3.setText(str);
    }

    private void setupViewAndFetchNewData(Calendar calendar, RecyclerView recyclerView, ViewGroup viewGroup, TextView textView, int i) {
        setupDiaryPage(calendar, recyclerView, viewGroup, textView, i);
        fetchDiaryDay(calendar);
    }

    public void onDiaryDayFetched(DiaryDayContext diaryDayContext) {
        if (diaryDayContext != null) {
            setupViewForDiaryInfoCacheEntry(diaryDayContext.getDiaryDay().getDate());
        }
    }

    /* access modifiers changed from: protected */
    public void setupViewForDiaryDay(DiaryDay diaryDay) {
        if (diaryDay != null) {
            setupViewForDiaryInfoCacheEntry(diaryDay.getDate());
        }
    }

    /* access modifiers changed from: protected */
    public void setupViewForDiaryInfoCacheEntry(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        setupDiaryPage(instance, getRecyclerViewForCalendarDate(instance), getDashboardContainerForCalendarDate(instance), getEmptyViewForCalendarDate(instance), getIndexForCalendarDate(instance));
    }

    private void setupDiaryPage(Calendar calendar, RecyclerView recyclerView, ViewGroup viewGroup, TextView textView, int i) {
        if (recyclerView != null && this.viewModel.isDiaryInfoCacheEntryReady(calendar.getTime())) {
            setupDiaryAdapter(recyclerView, viewGroup, textView, calendar, i);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter] */
    /* JADX WARNING: type inference failed for: r2v3, types: [com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter] */
    /* JADX WARNING: type inference failed for: r4v5, types: [com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.support.v7.widget.RecyclerView$Adapter] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupDiaryAdapter(android.support.v7.widget.RecyclerView r20, android.view.ViewGroup r21, android.widget.TextView r22, java.util.Calendar r23, int r24) {
        /*
            r19 = this;
            r15 = r19
            r14 = r20
            r0 = r22
            r1 = 0
            android.view.View[] r2 = new android.view.View[r1]
            com.uacf.core.util.ViewUtils.setVisible(r1, r2)
            java.util.Date r2 = r23.getTime()
            com.myfitnesspal.feature.diary.model.DiaryViewModelBase r3 = r15.viewModel
            com.myfitnesspal.shared.model.v1.DiaryDay r13 = r3.getDiaryDayForDate(r2)
            com.myfitnesspal.feature.diary.model.DiaryViewModelBase r3 = r15.viewModel
            com.myfitnesspal.shared.model.v2.MfpNutrientGoal r2 = r3.getNutrientGoalForDate(r2)
            r3 = r21
            r4 = r23
            r15.refreshNutrientDashboard(r3, r4, r13)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            boolean r11 = r19.showingFriendDiary()
            com.myfitnesspal.feature.diary.util.DiaryDelegate r4 = r15.diaryDelegate
            r5 = r13
            r6 = r2
            r7 = r3
            r8 = r10
            r9 = r11
            r4.setupDiaryAdapterListAndMap(r5, r6, r7, r8, r9)
            com.myfitnesspal.feature.diary.util.DiaryDelegate r4 = r15.diaryDelegate
            java.util.ArrayList r8 = r4.getFoodInsights()
            com.myfitnesspal.feature.diary.util.DiaryDelegate r4 = r15.diaryDelegate
            r4.addFooterToList(r3)
            boolean r4 = com.uacf.core.util.CollectionUtils.isEmpty(r3)
            r5 = 1
            android.view.View[] r5 = new android.view.View[r5]
            r5[r1] = r0
            com.uacf.core.util.ViewUtils.setVisible(r4, r5)
            r15.setEmptyViewText(r0)
            com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r0 = r19.getDiaryAdapterFromRecyclerView(r20)
            if (r0 != 0) goto L_0x00ca
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
            com.myfitnesspal.feature.diary.util.DiaryDelegate r0 = r15.diaryDelegate
            java.util.ArrayList r6 = r0.getDiaryItemsChecked()
            boolean r9 = r19.isEditing()
            com.myfitnesspal.feature.diary.util.DiaryDelegate r0 = r15.diaryDelegate
            boolean r0 = r0.isLandscape()
            if (r0 == 0) goto L_0x00a4
            com.myfitnesspal.feature.diary.ui.adapter.DiaryLandscapeAdapter r16 = new com.myfitnesspal.feature.diary.ui.adapter.DiaryLandscapeAdapter
            android.support.v4.app.FragmentActivity r1 = r19.getActivity()
            com.myfitnesspal.feature.diary.util.DiaryDelegate r7 = r15.diaryDelegate
            if (r2 != 0) goto L_0x0080
            r0 = 0
            r17 = r0
            goto L_0x0086
        L_0x0080:
            com.myfitnesspal.shared.model.v2.MfpDailyGoal r0 = r2.getDefaultGoal()
            r17 = r0
        L_0x0086:
            dagger.Lazy<com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper> r12 = r15.newsFeedAnalyticsHelper
            r0 = r16
            r2 = r4
            r3 = r10
            r4 = r13
            r5 = r8
            r8 = r9
            r9 = r11
            r10 = r19
            r11 = r24
            r18 = r12
            r12 = r19
            r21 = r13
            r13 = r17
            r14 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r1 = r20
            goto L_0x00c3
        L_0x00a4:
            r21 = r13
            com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r14 = new com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter
            android.support.v4.app.FragmentActivity r1 = r19.getActivity()
            com.myfitnesspal.feature.diary.util.DiaryDelegate r7 = r15.diaryDelegate
            dagger.Lazy<com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper> r13 = r15.newsFeedAnalyticsHelper
            r0 = r14
            r2 = r4
            r3 = r10
            r4 = r21
            r5 = r8
            r8 = r9
            r9 = r11
            r10 = r19
            r11 = r24
            r12 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r1 = r20
        L_0x00c3:
            r1.setAdapter(r0)
            r2 = r0
            r0 = r21
            goto L_0x00dc
        L_0x00ca:
            r21 = r13
            r1 = r14
            boolean r9 = r19.isEditing()
            r4 = r0
            r5 = r3
            r6 = r10
            r7 = r21
            r4.resetParamsAndNotify(r5, r6, r7, r8, r9)
            r2 = r0
            r0 = r21
        L_0x00dc:
            r1.setTag(r0)
            r15.scrollToSavedPosition(r0, r1, r2)
            r19.invalidateOptionsMenu()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.ui.fragment.DiaryFragmentBase.setupDiaryAdapter(android.support.v7.widget.RecyclerView, android.view.ViewGroup, android.widget.TextView, java.util.Calendar, int):void");
    }

    private void addDateView(View view, LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) ViewUtils.findById(view, R.id.date);
        boolean isInMultiWindow = ActivityUtils.isInMultiWindow(getActivity());
        boolean isDefaultPortrait = ActivityUtils.isDefaultPortrait(getActivity());
        int i = (isInMultiWindow || !isDefaultPortrait) ? R.layout.date_bar : (!isDefaultPortrait || isInMultiWindow) ? 0 : R.layout.material_diary_date_bar;
        viewGroup.addView(layoutInflater.inflate(i, viewGroup, false));
    }

    private boolean scrollToSavedPosition(DiaryDay diaryDay, RecyclerView recyclerView, DiaryAdapter diaryAdapter) {
        if (scrollToMostRecentlyAddedFood(diaryDay, diaryAdapter, recyclerView)) {
            return true;
        }
        if (diaryDay != null) {
            Integer num = (Integer) this.dateToScrollOffsetMap.get(Long.valueOf(diaryDay.getDate().getTime()));
            if (num != null) {
                recyclerView.scrollToPosition(num.intValue());
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public MfpNutrientGoal getNutrientGoalForDate(Date date) {
        return this.viewModel.getNutrientGoalForDate(date);
    }

    /* access modifiers changed from: protected */
    public void refreshNutrientDashboard(ViewGroup viewGroup, Calendar calendar, DiaryDay diaryDay) {
        ((NutrientDashboardRenderer) this.nutrientDashboardRenderer.get()).render((MfpActivity) getActivity(), Type.Diary, calendar, viewGroup, diaryDay, showingFriendDiary() ? DashboardUserType.Friend : DashboardUserType.Self);
    }

    /* access modifiers changed from: protected */
    public void loadDate(Calendar calendar) {
        this.date.setText(DateUtil.getMainDate(getActivity(), calendar));
        invalidateOptionsMenu();
    }

    public int getCurrentItemIndex() {
        return this.contentPager.getCurrentItem();
    }

    /* access modifiers changed from: protected */
    public DiaryDay getCurrentDiaryDay() {
        RecyclerView currentRecyclerView = getCurrentRecyclerView();
        if (currentRecyclerView == null) {
            return null;
        }
        Object tag = currentRecyclerView.getTag();
        if (tag == null) {
            return null;
        }
        return (DiaryDay) tag;
    }

    /* access modifiers changed from: protected */
    public void navigateToCreateMealForMealName(String str, @NonNull String str2) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            getNavigationHelper().withExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, (Serializable) new ArrayList(currentDiaryDay.getFoodEntriesForMealName(str))).withIntent(FoodEditorActivity.newMealItemEditorIntent(getActivity(), null, str, null, str2)).startActivity(RequestCodes.FOOD_EDITOR);
        }
    }

    /* access modifiers changed from: protected */
    public DiaryAdapter getDiaryAdapterForCalendarDate(Calendar calendar) {
        return getDiaryAdapterFromRecyclerView(getRecyclerViewForCalendarDate(calendar));
    }

    /* access modifiers changed from: protected */
    public DiaryAdapter getDiaryAdapterFromRecyclerView(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return null;
        }
        Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return null;
        }
        return (DiaryAdapter) adapter;
    }

    /* access modifiers changed from: protected */
    public RecyclerView getCurrentRecyclerView() {
        return getRecyclerViewForCalendarDate(getSelectedDate());
    }

    /* access modifiers changed from: protected */
    public Calendar getSelectedDate() {
        return (Calendar) this.dateList.get(getCurrentItemIndex());
    }

    /* access modifiers changed from: protected */
    public void showCreateMealDialog(Intent intent) {
        String stringExtra = intent.getStringExtra("operation");
        final String stringExtra2 = intent.getStringExtra("meal_food");
        intent.removeExtra("operation");
        final boolean equals = Strings.equals(stringExtra, "create");
        if ((equals || Strings.equals(stringExtra, MealEditorMixin.EXTRA_REPLACE)) && Strings.notEmpty(stringExtra2)) {
            new Handler().post(new Runnable() {
                public void run() {
                    DiaryFragmentBase.this.showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(equals ? R.string.meal_created : R.string.meal_updated)).setMessage(DiaryFragmentBase.this.getString(equals ? R.string.meal_has_been_added : R.string.meal_has_been_updated, stringExtra2))).setPositiveText(R.string.view, DiaryFragmentBase.this.onMealCreatedViewClickListener)).setNegativeText(R.string.dismiss, null), DiaryFragmentBase.CREATE_MEAL_DIALOG_TAG);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public RecyclerView getRecyclerViewForCalendarDate(Calendar calendar) {
        return (RecyclerView) getViewForCalendarDate(calendar, R.id.diary_recycler_view);
    }

    private ViewGroup getDashboardContainerForCalendarDate(Calendar calendar) {
        return (ViewGroup) getViewForCalendarDate(calendar, R.id.diary_summary_container);
    }

    private TextView getEmptyViewForCalendarDate(Calendar calendar) {
        return (TextView) getViewForCalendarDate(calendar, R.id.empty_view);
    }

    private int getIndexForCalendarDate(Calendar calendar) {
        for (int i = 0; i < CollectionUtils.size((Collection<?>) this.dateList); i++) {
            if (DateUtil.areDatesEqualIgnoreTime((Calendar) this.dateList.get(i), calendar)) {
                return i;
            }
        }
        return -1;
    }

    private boolean showingFriendDiary() {
        return this instanceof FriendDiaryFragment;
    }

    /* access modifiers changed from: protected */
    public <T extends View> T getViewForCalendarDate(Calendar calendar, int i) {
        if (this.contentPager == null) {
            return null;
        }
        return ViewUtils.findById(this.contentPager.findViewWithTag(getTagForPage(calendar)), i);
    }

    /* access modifiers changed from: protected */
    public String getTagForPage(Calendar calendar) {
        StringBuilder sb = new StringBuilder();
        sb.append(VIEW_TAG_PREFIX);
        sb.append(DateTimeUtils.getDateStringFromCalendarDate(calendar));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public boolean isEditing() {
        return this.diaryDelegate.isEditing();
    }

    public void onToggleMealMacrosUnitClick() {
        boolean z = ((LocalSettingsService) this.localSettingsService.get()).getMealMacrosDisplayUnit() == MealMacrosDisplayUnit.Gram;
        ((LocalSettingsService) this.localSettingsService.get()).setMealMacrosDisplayUnit(z ? MealMacrosDisplayUnit.Percent : MealMacrosDisplayUnit.Gram);
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportPremiumMealMacroUnitEvent(z ? VALUE_GRAM : "percent");
        if (((LocalSettingsService) this.localSettingsService.get()).showMealMacrosTip()) {
            onMealHeaderTipCloseClick(MealHeaderTip.Macros);
        } else {
            refreshInstantiatedRecyclerViewAdapters();
        }
        setUnitForMacros();
    }

    public void onMealHeaderTipCloseClick(MealHeaderTip mealHeaderTip) {
        List<DiaryAdapter> instantiatedAdapters = getInstantiatedAdapters();
        if (!CollectionUtils.isEmpty((Collection<?>) instantiatedAdapters)) {
            if (mealHeaderTip == MealHeaderTip.Goals) {
                ((LocalSettingsService) this.localSettingsService.get()).setShowMealGoalsTip(false);
            } else if (mealHeaderTip == MealHeaderTip.Macros) {
                ((LocalSettingsService) this.localSettingsService.get()).setShowMealMacrosTip(false);
            }
            for (DiaryAdapter diaryAdapter : instantiatedAdapters) {
                diaryAdapter.resetIndexForMealHeaderTip();
                diaryAdapter.notifyDataSetChanged();
            }
        }
    }

    private List<DiaryAdapter> getInstantiatedAdapters() {
        int size = this.dateList.size();
        int currentItemIndex = getCurrentItemIndex();
        int i = currentItemIndex == 0 ? 0 : currentItemIndex - 1;
        if (currentItemIndex != size - 1) {
            currentItemIndex++;
        }
        List<Calendar> subList = this.dateList.subList(i, currentItemIndex);
        ArrayList arrayList = new ArrayList(subList.size());
        for (Calendar diaryAdapterForCalendarDate : subList) {
            DiaryAdapter diaryAdapterForCalendarDate2 = getDiaryAdapterForCalendarDate(diaryAdapterForCalendarDate);
            if (diaryAdapterForCalendarDate2 != null) {
                arrayList.add(diaryAdapterForCalendarDate2);
            }
        }
        return arrayList;
    }

    private void refreshInstantiatedRecyclerViewAdapters() {
        for (DiaryAdapter notifyDataSetChangedToDiaryAdapter : getInstantiatedAdapters()) {
            notifyDataSetChangedToDiaryAdapter(notifyDataSetChangedToDiaryAdapter);
        }
    }

    /* access modifiers changed from: private */
    public void notifyDataSetChangedToRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            notifyDataSetChangedToDiaryAdapter(getDiaryAdapterFromRecyclerView(recyclerView));
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDataSetChangedToDiaryAdapter(DiaryAdapter diaryAdapter) {
        if (diaryAdapter != null) {
            diaryAdapter.notifyDataSetChanged();
        }
    }
}
