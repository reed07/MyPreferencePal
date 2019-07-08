package com.myfitnesspal.feature.search.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.ListView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.event.CreateNewRecipeEvent;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.search.event.MultiAddModeToggledEvent;
import com.myfitnesspal.feature.search.event.NavigateToQuickAddEvent;
import com.myfitnesspal.feature.search.event.SearchItemClickedEvent;
import com.myfitnesspal.feature.search.event.SelectedFoodSearchTabChangedEvent;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.model.FoodSearchModel;
import com.myfitnesspal.feature.search.ui.task.LocalFoodSearchTask;
import com.myfitnesspal.feature.search.ui.task.LocalFoodSearchTask.CompletedEvent;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.FoodSearch;
import com.myfitnesspal.shared.constants.Constants.Performance;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.event.UpdateMultiAddStatusEvent;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResImageItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.util.FragmentUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class LocalFoodSearchFragment extends MfpFragment {
    private static final String BUNDLE_EXTRA_IS_SHOWING_ONLINE_SEARCH = "is_showing_online_search";
    private static final String BUNDLE_EXTRA_QUERY = "query";
    private static final int PAGINATION_TRIGGER = 5;
    private static final String TAG_FRAGMENT_ONLINE_SEARCH = "onlineSearchFragmentTag";
    @Nullable
    @BindView(2131362231)
    Button createNewButton;
    @Nullable
    @BindView(2131362232)
    View createNewContainer;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @BindView(2131362461)
    EmptyStateView emptyStateView;
    /* access modifiers changed from: private */
    public boolean fetchingDataFromDB;
    /* access modifiers changed from: private */
    public FoodSearchAdapter foodSearchAdapter;
    @Inject
    Lazy<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelper;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    private boolean hideSearchFragmentsOnResume;
    @Inject
    Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public boolean isMealFoodCreationFlow;
    private boolean isOnlineFragmentShown = false;
    @BindView(2131362925)
    ListView listView;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<MealUtil> mealHelperUtil;
    /* access modifiers changed from: private */
    public String mealName;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int headerViewsCount = i - LocalFoodSearchFragment.this.listView.getHeaderViewsCount();
            if (headerViewsCount >= 0) {
                DiaryEntryCellModel access$1200 = LocalFoodSearchFragment.this.getDiaryEntryCellModelFromPos(headerViewsCount);
                if (access$1200 != null) {
                    Food access$1300 = LocalFoodSearchFragment.this.getFoodFromDiaryEntryCellModel(access$1200);
                    if (access$1300.getFoodType() == -1) {
                        handleDummyEntryClick(access$1300);
                    } else {
                        handleOtherEntriesClick(access$1200, access$1300, headerViewsCount);
                    }
                }
            }
        }

        private void handleDummyEntryClick(Food food) {
            String description = food.getDescription();
            if (Strings.equals(description, FoodSearch.CREATE_NEW)) {
                if (LocalFoodSearchFragment.this.tabType != FoodSearchTab.RECIPES) {
                    LocalFoodSearchFragment.this.switchToAddNewFood();
                } else {
                    LocalFoodSearchFragment.this.switchToAddNewRecipe();
                }
            } else if (Strings.equalsIgnoreCase(description, FoodSearch.FOOTER_FOOD_ITEM)) {
                LocalFoodSearchFragment.this.postEvent(new SearchItemClickedEvent());
            }
        }

        private void handleOtherEntriesClick(DiaryEntryCellModel diaryEntryCellModel, Food food, int i) {
            int itemType = diaryEntryCellModel.itemType();
            if (!(itemType == 1 || itemType == 11)) {
                switch (itemType) {
                    case 3:
                        break;
                    case 4:
                        FoodEntry foodEntry = (FoodEntry) diaryEntryCellModel;
                        if (foodEntry.getFood().isMeal() && (food instanceof MealFood)) {
                            LocalFoodSearchFragment.this.switchToAddMealViewForFood((MealFood) food, i);
                            break;
                        } else {
                            LocalFoodSearchFragment.this.switchToFoodSummaryViewForFood(food, foodEntry.getFoodPortion(), foodEntry.getQuantity(), i);
                            break;
                        }
                        break;
                }
            }
            if (food instanceof MealFood) {
                LocalFoodSearchFragment.this.switchToAddMealViewForFood((MealFood) food, i);
            } else {
                LocalFoodSearchFragment.this.switchToFoodSummaryViewForFood(food, null, 1.0f, i);
            }
            LocalFoodSearchFragment.this.reportFoodSearchItemClick(i);
        }
    };
    private OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
            if (LocalFoodSearchFragment.this.tabType == FoodSearchTab.RECENT) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DialogListTextResImageItem(R.string.deleteEntry, R.drawable.ic_delete_black_24dp));
                MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(LocalFoodSearchFragment.this.getActivity());
                mfpAlertDialogBuilder.setTitle((int) R.string.recent_foods);
                mfpAlertDialogBuilder.setDismissOnItemClick(true);
                mfpAlertDialogBuilder.setItems(arrayList, new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        DiaryEntryCellModel access$1200 = LocalFoodSearchFragment.this.getDiaryEntryCellModelFromPos(i);
                        if (access$1200 != null) {
                            Food access$1300 = LocalFoodSearchFragment.this.getFoodFromDiaryEntryCellModel(access$1200);
                            if (access$1300 != null) {
                                ((LocalSettingsService) LocalFoodSearchFragment.this.localSettingsService.get()).addRecentsDeletedFoodOriginalUid(access$1300.getOriginalUid());
                                LocalFoodSearchFragment.this.resultsModel.items.remove(access$1200);
                                LocalFoodSearchFragment.this.foodSearchAdapter.remove(access$1200);
                                ((FoodSearchAnalyticsHelper) LocalFoodSearchFragment.this.foodSearchAnalyticsHelper.get()).reportDeletedRecentsEvent(access$1300, i);
                            }
                        }
                    }
                });
                mfpAlertDialogBuilder.create().show();
            }
            return true;
        }
    };
    private OnScrollListener onScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 0) {
                LocalFoodSearchFragment.this.getImmHelper().hideSoftInput();
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!LocalFoodSearchFragment.this.fetchingDataFromDB && !LocalFoodSearchFragment.this.reachedEndOfList && LocalFoodSearchFragment.this.pagesFetched != 0 && !Strings.notEmpty(LocalFoodSearchFragment.this.query) && i3 - (i + i2) <= 5) {
                LocalFoodSearchFragment localFoodSearchFragment = LocalFoodSearchFragment.this;
                localFoodSearchFragment.fetchDBList(localFoodSearchFragment.pagesFetched + 1);
            }
        }
    };
    /* access modifiers changed from: private */
    public int pagesFetched;
    @Inject
    Lazy<PremiumService> premiumService;
    /* access modifiers changed from: private */
    public String query;
    /* access modifiers changed from: private */
    public boolean reachedEndOfList;
    /* access modifiers changed from: private */
    public FoodSearchModel resultsModel;
    private String searchFlowId;
    private DBSearchResultsFilter searchResultsFilter;
    @Inject
    Lazy<SearchService> searchService;
    /* access modifiers changed from: private */
    public boolean shouldIgnoreFilter = false;
    /* access modifiers changed from: private */
    public FoodSearchTab tabType;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private class DBSearchResultsFilter extends Filter {
        private DBSearchResultsFilter() {
        }

        /* access modifiers changed from: protected */
        public FilterResults performFiltering(CharSequence charSequence) {
            if (LocalFoodSearchFragment.this.resultsModel == null || LocalFoodSearchFragment.this.resultsModel.items == null) {
                return null;
            }
            String lowerCase = Strings.trimmed((Object) charSequence).toLowerCase();
            ArrayList arrayList = new ArrayList();
            if (Strings.isEmpty(lowerCase)) {
                arrayList.addAll(LocalFoodSearchFragment.this.resultsModel.items);
            } else {
                for (DiaryEntryCellModel diaryEntryCellModel : LocalFoodSearchFragment.this.resultsModel.items) {
                    if (diaryEntryCellModel != null && !LocalFoodSearchFragment.isDummyType(diaryEntryCellModel)) {
                        if (diaryEntryCellModel.isMealEntries() || diaryEntryCellModel.summaryLine1() != null) {
                            boolean z = true;
                            if (!diaryEntryCellModel.isMealEntries() ? !diaryEntryCellModel.summaryLine1().toLowerCase().contains(lowerCase) : !((UserEnergyService) LocalFoodSearchFragment.this.userEnergyService.get()).getMealEntriesTitle(diaryEntryCellModel).toLowerCase().contains(lowerCase)) {
                                String str = diaryEntryCellModel.isFood() ? ((Food) diaryEntryCellModel).getBrand() : diaryEntryCellModel instanceof FoodEntry ? ((FoodEntry) diaryEntryCellModel).getFood().getBrand() : null;
                                if (!Strings.notEmpty(str) || !str.toLowerCase().contains(lowerCase)) {
                                    z = false;
                                }
                            }
                            if (z) {
                                arrayList.add(diaryEntryCellModel);
                            }
                        }
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.count = CollectionUtils.size((Collection<?>) arrayList);
            filterResults.values = arrayList;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (LocalFoodSearchFragment.this.isEnabledAndActivityHasResumed() && filterResults != null && !LocalFoodSearchFragment.this.shouldIgnoreFilter) {
                List list = (List) filterResults.values;
                if (list == null) {
                    list = new ArrayList();
                }
                LocalFoodSearchFragment.this.makeCompleteList(list);
            }
        }
    }

    public static class Extras implements Parcelable {
        public static final Creator<Extras> CREATOR = new Creator<Extras>() {
            public Extras createFromParcel(Parcel parcel) {
                return new Extras(parcel);
            }

            public Extras[] newArray(int i) {
                return new Extras[i];
            }
        };
        private FoodSearchTab foodSearchTab;
        private boolean isMealFoodCreationFlow;
        private String mealFoodCreationFlowId;
        private String mealName;

        public int describeContents() {
            return 0;
        }

        public Extras() {
            this.foodSearchTab = null;
            this.mealName = null;
            this.isMealFoodCreationFlow = false;
            this.mealFoodCreationFlowId = null;
        }

        private Extras(Parcel parcel) {
            this.foodSearchTab = null;
            this.mealName = null;
            boolean z = false;
            this.isMealFoodCreationFlow = false;
            this.mealFoodCreationFlowId = null;
            this.foodSearchTab = (FoodSearchTab) parcel.readSerializable();
            this.mealName = parcel.readString();
            if (parcel.readByte() != 0) {
                z = true;
            }
            this.isMealFoodCreationFlow = z;
            this.mealFoodCreationFlowId = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeSerializable(this.foodSearchTab);
            parcel.writeString(this.mealName);
            parcel.writeByte(this.isMealFoodCreationFlow ? (byte) 1 : 0);
            parcel.writeString(this.mealFoodCreationFlowId);
        }

        public FoodSearchTab getFoodSearchTab() {
            return this.foodSearchTab;
        }

        public Extras setFoodSearchTab(FoodSearchTab foodSearchTab2) {
            this.foodSearchTab = foodSearchTab2;
            return this;
        }

        public String getMealName() {
            return this.mealName;
        }

        public Extras setMealName(String str) {
            this.mealName = str;
            return this;
        }

        public boolean isMealFoodCreationFlow() {
            return this.isMealFoodCreationFlow;
        }

        public Extras isMealFoodCreationFlow(boolean z) {
            this.isMealFoodCreationFlow = z;
            return this;
        }

        public String getMealFoodCreationFlowId() {
            return this.mealFoodCreationFlowId;
        }

        public Extras setMealFoodCreationFlowId(String str) {
            this.mealFoodCreationFlowId = str;
            return this;
        }
    }

    private static <T extends DiaryEntryCellModel> T getNonNullItem(DiaryEntryCellModel diaryEntryCellModel, DiaryEntryCellModel diaryEntryCellModel2) {
        return diaryEntryCellModel != null ? diaryEntryCellModel : diaryEntryCellModel2;
    }

    public static LocalFoodSearchFragment newInstance(Extras extras) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("extras", extras);
        LocalFoodSearchFragment localFoodSearchFragment = new LocalFoodSearchFragment();
        localFoodSearchFragment.setArguments(bundle);
        return localFoodSearchFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Extras extras = (Extras) arguments.getParcelable("extras");
            if (extras != null) {
                this.tabType = extras.getFoodSearchTab();
                this.mealName = extras.getMealName();
                this.isMealFoodCreationFlow = extras.isMealFoodCreationFlow();
                this.searchFlowId = extras.getMealFoodCreationFlowId();
            }
        }
        this.searchResultsFilter = new DBSearchResultsFilter();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.new_food_search_list, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.query = bundle.getString("query");
            this.isOnlineFragmentShown = bundle.getBoolean(BUNDLE_EXTRA_IS_SHOWING_ONLINE_SEARCH);
        }
        setupListView();
        MaterialUtils.enableAppBarScrollIfLollipop(getActivity(), this.listView);
        addCreateNewButtonIfNeeded();
    }

    public void onResume() {
        super.onResume();
        if (this.hideSearchFragmentsOnResume) {
            removeSearchFragmentsAndRefreshLocalList();
            this.hideSearchFragmentsOnResume = false;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (!forwardActivityResultToOnlineSearchFragmentIfVisible(findOnlineSearchFragment(), i, i2, intent)) {
            if ((i == 52 || i == 54 || i == 65) && i2 == -1) {
                if (i != 54 || ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn()) {
                    ListViewUtils.notifyDataSetChanged((Adapter) this.foodSearchAdapter);
                    postEventAfterResume(new UpdateMultiAddStatusEvent());
                } else {
                    getActivity().finish();
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("query", this.query);
        bundle.putBoolean(BUNDLE_EXTRA_IS_SHOWING_ONLINE_SEARCH, isOnlineFragmentVisible());
        super.onSaveInstanceState(bundle);
    }

    public void onSearchClicked() {
        getImmHelper().hideSoftInput();
        getFoodSearchAnalyticsHelper().sendSearchAnalytics(this.searchFlowId);
        OnlineFoodSearchFragment showOnlineSearchAndHideLocalList = showOnlineSearchAndHideLocalList();
        if (showOnlineSearchAndHideLocalList != null) {
            showOnlineSearchAndHideLocalList.performSearch(this.query);
        } else {
            addSearchFragment(OnlineFoodSearchFragment.newInstance(new com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras().setQuery(this.query).setMealName(this.mealName).setInMealFoodCreationFlow(this.isMealFoodCreationFlow).setFlowId(this.searchFlowId).setMealFoodCreationFlowId(this.searchFlowId).setSource(SearchSource.ONLINE).setListType(this.tabType.getListType())), TAG_FRAGMENT_ONLINE_SEARCH);
        }
    }

    public void updateQuery(String str) {
        if (!str.equals(this.query) || !this.isOnlineFragmentShown) {
            this.shouldIgnoreFilter = false;
            this.query = str;
            FoodSearchAdapter foodSearchAdapter2 = this.foodSearchAdapter;
            if (foodSearchAdapter2 != null) {
                foodSearchAdapter2.setQueryString(str);
            }
            DBSearchResultsFilter dBSearchResultsFilter = this.searchResultsFilter;
            if (dBSearchResultsFilter != null) {
                dBSearchResultsFilter.filter(this.query);
            }
            return;
        }
        this.shouldIgnoreFilter = true;
    }

    public void fetchDBList(boolean z) {
        if (z) {
            this.reachedEndOfList = false;
        }
        fetchDBList(4);
    }

    public boolean isSearchFragmentVisible() {
        return FragmentUtil.isFragmentVisible(findOnlineSearchFragment());
    }

    @Subscribe
    public void onMultiAddModeToggleEvent(MultiAddModeToggledEvent multiAddModeToggledEvent) {
        if (multiAddModeToggledEvent.isModeOn()) {
            ListViewUtils.notifyDataSetChanged((Adapter) this.foodSearchAdapter);
        } else {
            fetchDBList();
        }
    }

    @Subscribe
    public void onSelectedFoodSearchTabChangedEvent(SelectedFoodSearchTabChangedEvent selectedFoodSearchTabChangedEvent) {
        if (this.tabType != selectedFoodSearchTabChangedEvent.getFoodSearchTab()) {
            if (!hasResumed()) {
                this.hideSearchFragmentsOnResume = true;
            } else {
                removeSearchFragmentsAndRefreshLocalList();
            }
        }
    }

    @Subscribe
    public void onTabSearchCompleted(CompletedEvent completedEvent) {
        if (completedEvent.successful() && completedEvent.isFrom(getRunner())) {
            onQueryCompleted((FoodSearchModel) completedEvent.getResult());
        }
    }

    private boolean forwardActivityResultToOnlineSearchFragmentIfVisible(Fragment fragment, int i, int i2, Intent intent) {
        if (!FragmentUtil.isFragmentVisible(fragment)) {
            return false;
        }
        fragment.onActivityResult(i, i2, intent);
        return true;
    }

    private void setupListView() {
        if (this.foodSearchAdapter == null) {
            this.foodSearchAdapter = getFoodSearchAdapter();
        }
        this.listView.setAdapter(this.foodSearchAdapter);
        this.listView.setOnItemClickListener(this.onItemClickListener);
        this.listView.setOnItemLongClickListener(this.onItemLongClickListener);
        this.listView.setOnScrollListener(this.onScrollListener);
        fetchDBList();
    }

    private void fetchDBList() {
        fetchDBList(false);
    }

    /* access modifiers changed from: private */
    public void fetchDBList(int i) {
        if (!this.fetchingDataFromDB) {
            if (this.reachedEndOfList) {
                filterResultsIfNeeded();
                return;
            }
            this.fetchingDataFromDB = true;
            queryFoods(i * 50);
        }
    }

    private void onQueryCompleted(FoodSearchModel foodSearchModel) {
        this.resultsModel = foodSearchModel;
        this.fetchingDataFromDB = false;
        setupSearchResults(foodSearchModel.limit / 50, CollectionUtils.size((Collection<?>) this.resultsModel.items));
    }

    private void setupSearchResults(int i, int i2) {
        setupListForAdapter();
        this.pagesFetched = i;
        this.reachedEndOfList = i2 == CollectionUtils.size((Collection<?>) this.resultsModel.items);
    }

    private MultiAddFoodHelper getMultiAddFoodHelper() {
        return (MultiAddFoodHelper) this.multiAddFoodHelper.get();
    }

    private FoodSearchAnalyticsHelper getFoodSearchAnalyticsHelper() {
        return (FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get();
    }

    /* access modifiers changed from: private */
    public void reportFoodSearchItemClick(int i) {
        getFoodSearchAnalyticsHelper().reportFoodSearchItemClick(this.tabType.getAnalyticsTabName(), i);
    }

    /* access modifiers changed from: private */
    public void switchToAddMealViewForFood(MealFood mealFood, int i) {
        addFoodLoggingExtrasToNavigationHelper(i);
        MealFoodLoggedInfo mealFoodLoggedInfo = new MealFoodLoggedInfo(this.query, i, mealFood.getUid(), Attributes.LOCAL_SEARCH, this.tabType.getAnalyticsTabName());
        getNavigationHelper().withExtra(MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW, this.isMealFoodCreationFlow).withExtra(MealEditorMixin.EXTRA_MEAL_FOOD_LOGGED_INFO, (Parcelable) mealFoodLoggedInfo).withIntent(FoodEditorActivity.newMealItemEditorIntent(getActivity(), null, this.mealName, mealFood, "food_search")).startActivity(RequestCodes.FOOD_EDITOR);
    }

    /* access modifiers changed from: private */
    public void switchToAddNewFood() {
        getNavigationHelper().withIntent(AddFood.newStartIntent(getActivity(), this.mealName, true)).startActivity(53);
    }

    /* access modifiers changed from: private */
    public void switchToAddNewRecipe() {
        postEvent(new CreateNewRecipeEvent(this.mealName, getSession().getUser().getActiveDate(), StartScreen.FoodSearch));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void switchToFoodSummaryViewForFood(com.myfitnesspal.shared.model.v1.Food r22, com.myfitnesspal.shared.model.v1.FoodPortion r23, float r24, int r25) {
        /*
            r21 = this;
            r0 = r21
            r7 = r22
            r8 = r25
            if (r23 != 0) goto L_0x001c
            com.myfitnesspal.shared.model.v1.FoodPortion[] r1 = r22.getFoodPortions()
            if (r1 == 0) goto L_0x001c
            int r1 = r1.length
            if (r1 <= 0) goto L_0x001c
            com.myfitnesspal.shared.model.v1.FoodPortion[] r1 = r22.getFoodPortions()
            r2 = 0
            r1 = r1[r2]
            r9 = r1
            r1 = r24
            goto L_0x0020
        L_0x001c:
            r9 = r23
            r1 = r24
        L_0x0020:
            double r2 = (double) r1
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x002c
            r1 = 1065353216(0x3f800000, float:1.0)
            r10 = 1065353216(0x3f800000, float:1.0)
            goto L_0x002d
        L_0x002c:
            r10 = r1
        L_0x002d:
            com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper r1 = r21.getFoodSearchAnalyticsHelper()
            java.lang.String r3 = r0.searchFlowId
            java.lang.String r4 = r0.query
            com.myfitnesspal.feature.search.model.SearchSource r6 = com.myfitnesspal.feature.search.model.SearchSource.LOCAL
            r2 = r22
            r5 = r25
            r1.reportFoodLookupEvent(r2, r3, r4, r5, r6)
            com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper r1 = r21.getFoodSearchAnalyticsHelper()
            boolean r2 = r21.isSearchFragmentVisible()
            r1.reportSearchEvent(r2)
            r0.addFoodLoggingExtrasToNavigationHelper(r8)
            com.myfitnesspal.feature.search.event.UpdateFoodSearchBreadcrumbEvent r1 = new com.myfitnesspal.feature.search.event.UpdateFoodSearchBreadcrumbEvent
            java.lang.String r2 = "add_view"
            r1.<init>(r2)
            r0.postEvent(r1)
            boolean r1 = r7 instanceof com.myfitnesspal.shared.model.v1.RecipeFood
            com.myfitnesspal.shared.service.config.ConfigService r2 = r21.getConfigService()
            boolean r2 = com.myfitnesspal.shared.util.ConfigUtils.isNewAddFoodFlowOn(r2)
            r3 = 54
            r4 = 2131886269(0x7f1200bd, float:1.9407112E38)
            r5 = 2131886231(0x7f120097, float:1.9407035E38)
            if (r2 == 0) goto L_0x0126
            com.myfitnesspal.shared.model.v2.MfpFood r13 = com.myfitnesspal.shared.util.FoodMapperUtil.mapV1FoodToMfpFood(r22)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r2 = new com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras
            r2.<init>()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras r6 = new com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras
            r6.<init>()
            r13.setSelectedServingAmount(r10)
            int r7 = r0.getFoodPortionIndex(r7, r9)
            r13.setSelectedServingSizeIndex(r7)
            java.lang.String r7 = r0.query
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras r7 = r6.setSearchQuery(r7)
            com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r9 = r0.tabType
            java.lang.String r9 = r9.getAnalyticsTabName()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras r7 = r7.setListType(r9)
            r7.setResultsListPosition(r8)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras$ActionType r7 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType.Create
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r7 = r2.setActionType(r7)
            r8 = 1
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r7 = r7.setSupportPairedFoods(r8)
            java.lang.String r8 = r0.mealName
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r7 = r7.setMealName(r8)
            com.myfitnesspal.shared.service.session.Session r8 = r21.getSession()
            com.myfitnesspal.shared.model.User r8 = r8.getUser()
            java.util.Date r8 = r8.getActiveDate()
            long r8 = r8.getTime()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r7 = r7.setDate(r8)
            dagger.Lazy<com.myfitnesspal.shared.util.MultiAddFoodHelper> r8 = r0.multiAddFoodHelper
            java.lang.Object r8 = r8.get()
            com.myfitnesspal.shared.util.MultiAddFoodHelper r8 = (com.myfitnesspal.shared.util.MultiAddFoodHelper) r8
            boolean r8 = r8.isMultiAddModeOn()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r7 = r7.setMultiAddOn(r8)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r7 = r7.setForRecipe(r1)
            if (r1 == 0) goto L_0x00d1
            goto L_0x00d4
        L_0x00d1:
            r4 = 2131886231(0x7f120097, float:1.9407035E38)
        L_0x00d4:
            java.lang.String r1 = r0.getString(r4)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r7.setScreenTitle(r1)
            r1.setFoodEditorAnalyticsExtras(r6)
            boolean r1 = r0.isMealFoodCreationFlow
            if (r1 == 0) goto L_0x00e5
            r1 = 0
            goto L_0x00ef
        L_0x00e5:
            android.content.Context r1 = r21.getContext()
            java.lang.String r4 = "just_logged"
            android.content.Intent r1 = com.myfitnesspal.feature.diary.ui.activity.Diary.newStartIntentWithReferrerAndForceHomeOnBack(r1, r4)
        L_0x00ef:
            r12 = r1
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r21.getNavigationHelper()
            android.content.Context r11 = r21.getContext()
            com.myfitnesspal.shared.service.session.Session r4 = r21.getSession()
            com.myfitnesspal.shared.model.User r4 = r4.getUser()
            java.util.Date r14 = r4.getActiveDate()
            java.lang.String r15 = r0.mealName
            r16 = 0
            r17 = 0
            java.lang.String r18 = "local_food_search_fragment"
            boolean r4 = r0.isMealFoodCreationFlow
            r19 = r4
            r20 = r2
            android.content.Intent r4 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity.newDiaryFoodItemEditorIntent(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r1.withIntent(r4)
            boolean r2 = r2.isMultiAddOn()
            if (r2 == 0) goto L_0x0122
            r3 = 196(0xc4, float:2.75E-43)
        L_0x0122:
            r1.startActivity(r3)
            goto L_0x019e
        L_0x0126:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r2 = r21.getNavigationHelper()
            android.support.v4.app.FragmentActivity r6 = r21.getActivity()
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r11 = new com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras
            r11.<init>()
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r7 = r11.setFood(r7)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r7 = r7.setFoodPortion(r9)
            com.myfitnesspal.shared.service.session.Session r9 = r21.getSession()
            com.myfitnesspal.shared.model.User r9 = r9.getUser()
            java.util.Date r9 = r9.getActiveDate()
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r7 = r7.setDate(r9)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r7 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r7
            java.lang.String r9 = r0.mealName
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r7 = r7.setMealName(r9)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r7 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r7
            com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r9 = r0.tabType
            java.lang.String r9 = r9.getAnalyticsTabName()
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r7 = r7.setListType(r9)
            if (r1 == 0) goto L_0x0162
            goto L_0x0165
        L_0x0162:
            r4 = 2131886231(0x7f120097, float:1.9407035E38)
        L_0x0165:
            java.lang.String r1 = r0.getString(r4)
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r7.setTitle(r1)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            java.lang.String r4 = r0.query
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setQuery(r4)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setServings(r10)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setPosition(r8)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            boolean r4 = r0.isMealFoodCreationFlow
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setMealFoodCreationFlow(r4)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            java.lang.String r4 = "local_search"
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setSource(r4)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            android.content.Intent r1 = com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.newStartIntent(r6, r1)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r2.withIntent(r1)
            r1.startActivity(r3)
        L_0x019e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragment.switchToFoodSummaryViewForFood(com.myfitnesspal.shared.model.v1.Food, com.myfitnesspal.shared.model.v1.FoodPortion, float, int):void");
    }

    private int getFoodPortionIndex(Food food, FoodPortion foodPortion) {
        FoodPortion[] foodPortions = food.getFoodPortions();
        for (int i = 0; i < foodPortions.length; i++) {
            if (foodPortion.equals(foodPortions[i])) {
                return i;
            }
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public boolean isEnabledAndActivityHasResumed() {
        return isEnabled() && ((MfpActivity) getActivity()).hasResumed();
    }

    private FoodSearchAdapter getFoodSearchAdapter() {
        FoodSearchAdapter foodSearchAdapter2 = new FoodSearchAdapter(getActivity(), this.tabType, this.imageService, this.userEnergyService, this.mealHelperUtil, this.localizedStringsUtil, this.multiAddFoodHelper, getMessageBus(), ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.QuickAddMacros), this.tabType == FoodSearchTab.MEALS, this.query);
        return foodSearchAdapter2;
    }

    private void queryFoods(int i) {
        String.format(Performance.NEW_FOOD_SEARCH_FETCH_DATA, new Object[]{this.tabType.name()});
        LocalFoodSearchTask localFoodSearchTask = new LocalFoodSearchTask(this.searchService, this.mealName, this.tabType.getTabId(), i, this.dbConnectionManager);
        localFoodSearchTask.setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
    }

    private void setupListForAdapter() {
        this.searchResultsFilter.filter(this.query);
    }

    private void filterResultsIfNeeded() {
        this.searchResultsFilter.filter(this.query);
    }

    /* access modifiers changed from: private */
    public void navigateToQuickAdd() {
        postEvent(new NavigateToQuickAddEvent(NavigateToQuickAddEvent.SOURCE_FIXED_FOOTER));
    }

    private OnlineFoodSearchFragment findOnlineSearchFragment() {
        return (OnlineFoodSearchFragment) getChildFragmentManager().findFragmentByTag(TAG_FRAGMENT_ONLINE_SEARCH);
    }

    private boolean isOnlineFragmentVisible() {
        return FragmentUtil.isFragmentVisible(findOnlineSearchFragment());
    }

    private void hideOnlineSearchFragmentAndShowLocalList() {
        hideSearchFragmentAndShowLocalList(findOnlineSearchFragment());
    }

    private OnlineFoodSearchFragment showOnlineSearchAndHideLocalList() {
        this.shouldIgnoreFilter = true;
        OnlineFoodSearchFragment findOnlineSearchFragment = findOnlineSearchFragment();
        showSearchFragmentAndHideLocalList(findOnlineSearchFragment);
        return findOnlineSearchFragment;
    }

    private void addSearchFragment(Fragment fragment, String str) {
        getChildFragmentManager().beginTransaction().add(R.id.search_fragment_container, fragment, str).commit();
    }

    private void removeSearchFragmentAndShowLocalList(Fragment fragment) {
        this.shouldIgnoreFilter = false;
        toggleLocalListVisibility(true);
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    private void hideSearchFragmentAndShowLocalList(Fragment fragment) {
        this.shouldIgnoreFilter = false;
        toggleLocalListVisibility(true);
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().hide(fragment).commit();
        }
    }

    private void showSearchFragmentAndHideLocalList(Fragment fragment) {
        toggleLocalListVisibility(false);
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().show(fragment).commit();
        }
    }

    private void toggleLocalListVisibility(boolean z) {
        ViewUtils.setVisible(z, this.listView);
    }

    /* access modifiers changed from: private */
    public void makeCompleteList(List<DiaryEntryCellModel> list) {
        String.format(Performance.NEW_FOOD_SEARCH_BUILD_RESULTS, new Object[]{this.tabType.name()});
        if (this.foodSearchAdapter != null) {
            boolean z = CollectionUtils.isEmpty((Collection<?>) list) && Strings.isEmpty(this.query);
            ViewUtils.setVisible(z, this.emptyStateView);
            ViewUtils.setVisible(!z, this.listView);
            ViewUtils.setVisible(!z, this.createNewButton);
            if (z) {
                showEmptyState();
            } else {
                addExtraElements(list);
                addItemsToAdapter(list);
            }
        }
    }

    private void addItemsToAdapter(List<DiaryEntryCellModel> list) {
        if (this.foodSearchAdapter != null) {
            hideOnlineSearchFragmentAndShowLocalList();
            this.foodSearchAdapter.setNotifyOnChange(false);
            this.foodSearchAdapter.clear();
            this.foodSearchAdapter.setNotifyOnChange(true);
            this.foodSearchAdapter.addAll(list);
            FoodSearchModel foodSearchModel = this.resultsModel;
            if (foodSearchModel != null) {
                this.foodSearchAdapter.setImages(foodSearchModel.images);
            }
        }
    }

    private void removeSearchFragmentsAndRefreshLocalList() {
        removeSearchFragmentAndShowLocalList(findOnlineSearchFragment());
        setupListForAdapter();
    }

    private void addExtraElements(List<DiaryEntryCellModel> list) {
        boolean isEmpty = CollectionUtils.isEmpty((Collection<?>) list);
        if (isEmpty) {
            list.add(Food.initAsDummy(FoodSearch.EMPTY));
        }
        if (Strings.notEmpty(this.query) && !isEmpty) {
            list.add(Food.initAsDummy(FoodSearch.FOOTER_FOOD_ITEM));
        }
    }

    /* access modifiers changed from: protected */
    public void showEmptyState() {
        OnClickListener onClickListener;
        Type type;
        switch (this.tabType) {
            case ALL:
            case FREQUENT:
                type = Type.Frequent;
                onClickListener = null;
                break;
            case MEALS:
                type = Type.Meal;
                onClickListener = new OnClickListener() {
                    public void onClick(View view) {
                        LocalFoodSearchFragment.this.navigateToCreateMealFood();
                    }
                };
                break;
            case MY_FOODS:
                type = Type.Food;
                onClickListener = new OnClickListener() {
                    public void onClick(View view) {
                        LocalFoodSearchFragment.this.navigateToCreateFood();
                    }
                };
                break;
            case RECENT:
                type = Type.Recent;
                onClickListener = null;
                break;
            case RECIPES:
                type = Type.Recipe;
                onClickListener = new OnClickListener() {
                    public void onClick(View view) {
                        LocalFoodSearchFragment localFoodSearchFragment = LocalFoodSearchFragment.this;
                        localFoodSearchFragment.postEvent(new CreateNewRecipeEvent(localFoodSearchFragment.mealName, LocalFoodSearchFragment.this.getSession().getUser().getActiveDate(), StartScreen.FoodSearch));
                    }
                };
                break;
            default:
                type = null;
                onClickListener = null;
                break;
        }
        boolean z = !this.isMealFoodCreationFlow;
        EmptyStateView emptyStateView2 = this.emptyStateView;
        if (!z) {
            onClickListener = null;
        }
        emptyStateView2.initializeForType(type, onClickListener, null);
    }

    /* access modifiers changed from: private */
    public void navigateToCreateMealFood() {
        getNavigationHelper().withExtra(MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW, this.isMealFoodCreationFlow).withIntent(FoodEditorActivity.newMealItemEditorIntent(getActivity(), ((FoodSearchActivityFactory) this.foodSearchRouter.get()).getFoodSearchActivityIntent(getActivity(), new com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras().setMealCreated(true)), this.mealName, null, "food_search")).startActivity(RequestCodes.FOOD_EDITOR);
    }

    /* access modifiers changed from: private */
    public void navigateToCreateFood() {
        getNavigationHelper().withIntent(AddFood.newStartIntent(getActivity(), this.mealName, true)).startActivity(53);
    }

    /* access modifiers changed from: private */
    public static boolean isDummyType(DiaryEntryCellModel diaryEntryCellModel) {
        int i;
        if (diaryEntryCellModel.isMealEntries()) {
            i = diaryEntryCellModel.itemType();
        } else if (diaryEntryCellModel.isFood()) {
            i = ((Food) diaryEntryCellModel).getFoodType();
        } else {
            i = ((FoodEntry) diaryEntryCellModel).getFood().getFoodType();
        }
        return i == -1;
    }

    private void addCreateNewButtonIfNeeded() {
        int i;
        if (!this.isMealFoodCreationFlow) {
            ViewUtils.setVisible(true, this.createNewContainer);
            switch (this.tabType) {
                case ALL:
                case FREQUENT:
                case RECENT:
                    i = R.string.quick_add;
                    break;
                case MEALS:
                    i = R.string.create_new_meal;
                    break;
                case MY_FOODS:
                    i = R.string.new_food;
                    break;
                case RECIPES:
                    i = R.string.new_recipe;
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled tab: ");
                    sb.append(this.tabType);
                    throw new IllegalStateException(sb.toString());
            }
            this.createNewButton.setText(i);
            this.createNewButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    switch (AnonymousClass8.$SwitchMap$com$myfitnesspal$feature$search$ui$constants$FoodSearchTab[LocalFoodSearchFragment.this.tabType.ordinal()]) {
                        case 2:
                        case 5:
                            LocalFoodSearchFragment.this.navigateToQuickAdd();
                            return;
                        case 3:
                            FragmentActivity activity = LocalFoodSearchFragment.this.getActivity();
                            if (activity != null) {
                                LocalFoodSearchFragment.this.getNavigationHelper().withExtra(MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW, LocalFoodSearchFragment.this.isMealFoodCreationFlow).withIntent(FoodEditorActivity.newMealItemEditorIntent(activity, ((FoodSearchActivityFactory) LocalFoodSearchFragment.this.foodSearchRouter.get()).getFoodSearchActivityIntent(activity, com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras.fromIntent(activity.getIntent()).setMealCreated(true)), LocalFoodSearchFragment.this.mealName, null, "food_search")).startActivity(RequestCodes.FOOD_EDITOR);
                                return;
                            }
                            return;
                        case 4:
                            LocalFoodSearchFragment.this.getNavigationHelper().withIntent(AddFood.newStartIntent(LocalFoodSearchFragment.this.getActivity(), LocalFoodSearchFragment.this.mealName, true)).startActivity(53);
                            return;
                        case 6:
                            LocalFoodSearchFragment localFoodSearchFragment = LocalFoodSearchFragment.this;
                            localFoodSearchFragment.postEvent(new CreateNewRecipeEvent(localFoodSearchFragment.mealName, LocalFoodSearchFragment.this.getSession().getUser().getActiveDate(), StartScreen.FoodSearch));
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public DiaryEntryCellModel getDiaryEntryCellModelFromPos(int i) {
        FoodSearchAdapter foodSearchAdapter2 = this.foodSearchAdapter;
        if (foodSearchAdapter2 == null) {
            return null;
        }
        DiaryEntryCellModel item = foodSearchAdapter2.getItem(i);
        return getNonNullItem(getMultiAddFoodHelper().getItemWithSameId(item), item);
    }

    /* access modifiers changed from: private */
    public Food getFoodFromDiaryEntryCellModel(DiaryEntryCellModel diaryEntryCellModel) {
        if (diaryEntryCellModel == null) {
            return null;
        }
        return diaryEntryCellModel.isFood() ? (Food) diaryEntryCellModel : ((FoodEntry) diaryEntryCellModel).getFood();
    }

    private void addFoodLoggingExtrasToNavigationHelper(int i) {
        getNavigationHelper().withExtra("index", i).withExtra(Attributes.ITEM_COUNT, 1).withExtra("list_type", this.tabType.getListType()).withExtra("foods", new ApiJsonMapper().reverseMap((Object) ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).getAllSelectedItems())).withExtra("source", (Serializable) isSearchFragmentVisible() ? SearchSource.ONLINE : SearchSource.LOCAL).withExtra("flow_id", this.searchFlowId).withExtra("meal", this.mealName);
    }
}
