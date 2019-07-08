package com.myfitnesspal.feature.search.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.repository.LocalFoodSearchRepository;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2.Extras;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModelExtKt;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 X2\u00020\u0001:\u0001XBK\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r¢\u0006\u0002\u0010\u0011J\u0016\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020<J\u0018\u0010H\u001a\u00020E2\u0006\u0010I\u001a\u00020B2\b\b\u0002\u0010J\u001a\u00020%J\u0016\u0010K\u001a\u00020E2\u0006\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020<J\b\u0010L\u001a\u00020EH\u0002J\u001e\u0010M\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00132\u0006\u0010;\u001a\u00020<H\u0002J\u0010\u0010N\u001a\u00020E2\u0006\u00103\u001a\u00020-H\u0002J\u0006\u0010O\u001a\u00020EJ\u0018\u0010P\u001a\u00020%2\u0006\u0010F\u001a\u00020\u00152\u0006\u00103\u001a\u00020-H\u0002J\u0006\u0010Q\u001a\u00020EJ\b\u0010R\u001a\u00020EH\u0014J\u000e\u0010S\u001a\u00020E2\u0006\u0010F\u001a\u00020\u0015J\u0016\u0010T\u001a\u00020E2\u0006\u0010U\u001a\u00020V2\u0006\u0010G\u001a\u00020<J\u0006\u0010W\u001a\u00020ERN\u0010\u0012\u001aB\u0012\u001a\b\u0001\u0012\u0016\u0012\u0004\u0012\u00020\u0015 \u0016*\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00140\u0014 \u0016* \u0012\u001a\b\u0001\u0012\u0016\u0012\u0004\u0012\u00020\u0015 \u0016*\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00140\u0014\u0018\u00010\u00130\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R(\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010 \u001a\u0004\u0018\u00010!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b$\u0010&R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010,\u001a\u0004\u0018\u00010-8F¢\u0006\u0006\u001a\u0004\b.\u0010/R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148F¢\u0006\u0006\u001a\u0004\b1\u00102R$\u00103\u001a\u00020-2\u0006\u0010\u0019\u001a\u00020-8F@FX\u000e¢\u0006\f\u001a\u0004\b4\u0010/\"\u0004\b5\u00106R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020-0(¢\u0006\b\n\u0000\u001a\u0004\b8\u0010+R\u001d\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140(¢\u0006\b\n\u0000\u001a\u0004\b:\u0010+R\u000e\u0010;\u001a\u00020<X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020%0(¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u0011\u0010?\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b@\u0010&R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020B0(¢\u0006\b\n\u0000\u001a\u0004\bC\u0010+R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0004¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/viewmodel/LocalFoodSearchViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "appContext", "Landroid/app/Application;", "searchRepo", "Lcom/myfitnesspal/feature/search/repository/LocalFoodSearchRepository;", "sortOrderHelper", "Lcom/myfitnesspal/feature/search/util/SortOrderHelper;", "multiAddFoodHelper", "Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "foodSearchAnalyticsHelper", "Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;", "userEnergyService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "(Landroid/app/Application;Lcom/myfitnesspal/feature/search/repository/LocalFoodSearchRepository;Lcom/myfitnesspal/feature/search/util/SortOrderHelper;Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;Ldagger/Lazy;Ldagger/Lazy;)V", "dbCacheSingle", "Lio/reactivex/Single;", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "kotlin.jvm.PlatformType", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "value", "Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$Extras;", "extras", "getExtras", "()Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$Extras;", "setExtras", "(Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$Extras;)V", "foodSearchTab", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "getFoodSearchTab", "()Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "isResultEmpty", "", "()Z", "itemImages", "Landroid/arch/lifecycle/MutableLiveData;", "Lcom/myfitnesspal/shared/model/FoodImages;", "getItemImages", "()Landroid/arch/lifecycle/MutableLiveData;", "mealName", "", "getMealName", "()Ljava/lang/String;", "multiAddItems", "getMultiAddItems", "()Ljava/util/List;", "query", "getQuery", "setQuery", "(Ljava/lang/String;)V", "queryLiveData", "getQueryLiveData", "searchItems", "getSearchItems", "searchLimit", "", "shouldFilterAllMeals", "getShouldFilterAllMeals", "shouldShowSortAndFilter", "getShouldShowSortAndFilter", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "getSortOrder", "addItemToMultiAdd", "", "item", "position", "changeSortOrder", "newSort", "filterAllMeals", "deleteItemFromRecents", "fetchImagesIfNecessary", "fetchItems", "filterResults", "invalidateData", "itemMatchesQuery", "loadNextPage", "onCleared", "removeItemFromMultiAdd", "reportFoodLookupEvent", "food", "Lcom/myfitnesspal/shared/model/v1/Food;", "reportSortAndFilterOptionsClicked", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchViewModel.kt */
public final class LocalFoodSearchViewModel extends AndroidViewModel {
    public static final Companion Companion = new Companion(null);
    private static final int INITIAL_SEARCH_LIMIT = 200;
    private Single<? extends List<? extends DiaryEntryCellModel>> dbCacheSingle;
    private final CompositeDisposable disposable;
    @Nullable
    private Extras extras;
    private final FoodSearchAnalyticsHelper foodSearchAnalyticsHelper;
    @NotNull
    private final MutableLiveData<FoodImages> itemImages = new MutableLiveData<>();
    private final Lazy<LocalSettingsService> localSettingsService;
    private final MultiAddFoodHelper multiAddFoodHelper;
    @NotNull
    private final MutableLiveData<String> queryLiveData = new MutableLiveData<>();
    @NotNull
    private final MutableLiveData<List<DiaryEntryCellModel>> searchItems = new MutableLiveData<>();
    private int searchLimit;
    private final LocalFoodSearchRepository searchRepo;
    @NotNull
    private final MutableLiveData<Boolean> shouldFilterAllMeals;
    @NotNull
    private final MutableLiveData<SortOrder> sortOrder = new MutableLiveData<>();
    private final SortOrderHelper sortOrderHelper;
    private final Lazy<UserEnergyService> userEnergyService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/viewmodel/LocalFoodSearchViewModel$Companion;", "", "()V", "INITIAL_SEARCH_LIMIT", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LocalFoodSearchViewModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public LocalFoodSearchViewModel(@NotNull Application application, @NotNull LocalFoodSearchRepository localFoodSearchRepository, @NotNull SortOrderHelper sortOrderHelper2, @NotNull MultiAddFoodHelper multiAddFoodHelper2, @NotNull FoodSearchAnalyticsHelper foodSearchAnalyticsHelper2, @NotNull Lazy<UserEnergyService> lazy, @NotNull Lazy<LocalSettingsService> lazy2) {
        Intrinsics.checkParameterIsNotNull(application, "appContext");
        Intrinsics.checkParameterIsNotNull(localFoodSearchRepository, "searchRepo");
        Intrinsics.checkParameterIsNotNull(sortOrderHelper2, "sortOrderHelper");
        Intrinsics.checkParameterIsNotNull(multiAddFoodHelper2, "multiAddFoodHelper");
        Intrinsics.checkParameterIsNotNull(foodSearchAnalyticsHelper2, "foodSearchAnalyticsHelper");
        Intrinsics.checkParameterIsNotNull(lazy, "userEnergyService");
        Intrinsics.checkParameterIsNotNull(lazy2, "localSettingsService");
        super(application);
        this.searchRepo = localFoodSearchRepository;
        this.sortOrderHelper = sortOrderHelper2;
        this.multiAddFoodHelper = multiAddFoodHelper2;
        this.foodSearchAnalyticsHelper = foodSearchAnalyticsHelper2;
        this.userEnergyService = lazy;
        this.localSettingsService = lazy2;
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Boolean.valueOf(((LocalSettingsService) this.localSettingsService.get()).shouldShowAllMeals()));
        this.shouldFilterAllMeals = mutableLiveData;
        this.disposable = new CompositeDisposable();
        this.searchLimit = 200;
        this.dbCacheSingle = fetchItems(this.searchLimit).cache();
    }

    @NotNull
    public final List<DiaryEntryCellModel> getMultiAddItems() {
        List<DiaryEntryCellModel> allSelectedItems = this.multiAddFoodHelper.getAllSelectedItems();
        Intrinsics.checkExpressionValueIsNotNull(allSelectedItems, "multiAddFoodHelper.allSelectedItems");
        return allSelectedItems;
    }

    public final boolean getShouldShowSortAndFilter() {
        return getFoodSearchTab() == FoodSearchTab.ALL;
    }

    @Nullable
    public final String getMealName() {
        Extras extras2 = this.extras;
        if (extras2 != null) {
            return extras2.getMealName();
        }
        return null;
    }

    @Nullable
    public final FoodSearchTab getFoodSearchTab() {
        Extras extras2 = this.extras;
        if (extras2 != null) {
            return extras2.getFoodSearchTab();
        }
        return null;
    }

    public final boolean isResultEmpty() {
        List list = (List) this.searchItems.getValue();
        return list != null && list.isEmpty();
    }

    @NotNull
    public final MutableLiveData<List<DiaryEntryCellModel>> getSearchItems() {
        return this.searchItems;
    }

    @NotNull
    public final MutableLiveData<FoodImages> getItemImages() {
        return this.itemImages;
    }

    @NotNull
    public final MutableLiveData<SortOrder> getSortOrder() {
        return this.sortOrder;
    }

    @NotNull
    public final MutableLiveData<String> getQueryLiveData() {
        return this.queryLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShouldFilterAllMeals() {
        return this.shouldFilterAllMeals;
    }

    @NotNull
    public final String getQuery() {
        String str = (String) this.queryLiveData.getValue();
        return str != null ? str : "";
    }

    public final void setQuery(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        this.queryLiveData.setValue(str);
        filterResults(str);
    }

    @Nullable
    public final Extras getExtras() {
        return this.extras;
    }

    public final void setExtras(@Nullable Extras extras2) {
        SortOrder sortOrder2;
        this.extras = extras2;
        Extras extras3 = this.extras;
        FoodSearchTab foodSearchTab = extras3 != null ? extras3.getFoodSearchTab() : null;
        MutableLiveData<SortOrder> mutableLiveData = this.sortOrder;
        if (foodSearchTab != null) {
            sortOrder2 = this.sortOrderHelper.getCurrentSortOrderForTab(foodSearchTab.getTabId());
        } else {
            sortOrder2 = SortOrder.RECENTLY_USED;
        }
        mutableLiveData.setValue(sortOrder2);
        invalidateData();
        fetchImagesIfNecessary();
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.disposable.dispose();
    }

    public final void loadNextPage() {
        this.searchLimit += 50;
        invalidateData();
        fetchImagesIfNecessary();
    }

    public static /* synthetic */ void changeSortOrder$default(LocalFoodSearchViewModel localFoodSearchViewModel, SortOrder sortOrder2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = Intrinsics.areEqual((Object) (Boolean) localFoodSearchViewModel.shouldFilterAllMeals.getValue(), (Object) Boolean.valueOf(true));
        }
        localFoodSearchViewModel.changeSortOrder(sortOrder2, z);
    }

    public final void changeSortOrder(@NotNull SortOrder sortOrder2, boolean z) {
        boolean z2;
        Intrinsics.checkParameterIsNotNull(sortOrder2, "newSort");
        if (!Intrinsics.areEqual((Object) Boolean.valueOf(z), (Object) (Boolean) this.shouldFilterAllMeals.getValue())) {
            this.shouldFilterAllMeals.setValue(Boolean.valueOf(z));
            ((LocalSettingsService) this.localSettingsService.get()).setShouldShowAllMeals(z);
            this.foodSearchAnalyticsHelper.reportMealFilterChanged(getFoodSearchTab(), getMealName(), !z);
            z2 = true;
        } else {
            z2 = false;
        }
        if (sortOrder2 != ((SortOrder) this.sortOrder.getValue())) {
            FoodSearchTab foodSearchTab = getFoodSearchTab();
            if (foodSearchTab != null) {
                this.sortOrderHelper.setCurrentSortOrderForSelectorButton(foodSearchTab.getTabId(), sortOrder2);
            }
            this.sortOrder.setValue(sortOrder2);
            this.foodSearchAnalyticsHelper.reportSortOrderChanged(getFoodSearchTab(), getMealName(), sortOrder2);
            z2 = true;
        }
        if (z2) {
            invalidateData();
        }
    }

    public final void invalidateData() {
        this.dbCacheSingle = fetchItems(this.searchLimit).cache();
        filterResults(getQuery());
    }

    public final void addItemToMultiAdd(@NotNull DiaryEntryCellModel diaryEntryCellModel, int i) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        if (this.multiAddFoodHelper.isMultiAddModeOn()) {
            MultiAddFoodHelper multiAddFoodHelper2 = this.multiAddFoodHelper;
            Builder fromFood = Builder.Companion.fromFood(DiaryEntryCellModelExtKt.getFood(diaryEntryCellModel));
            fromFood.setSearchTerm(getQuery());
            fromFood.setIndex(i);
            FoodSearchTab foodSearchTab = getFoodSearchTab();
            fromFood.setListType(foodSearchTab != null ? foodSearchTab.getAnalyticsTabName() : null);
            multiAddFoodHelper2.addAndLogItem(diaryEntryCellModel, fromFood.build());
        }
    }

    public final void removeItemFromMultiAdd(@NotNull DiaryEntryCellModel diaryEntryCellModel) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        if (this.multiAddFoodHelper.isMultiAddModeOn()) {
            this.multiAddFoodHelper.removeItemAndLog(diaryEntryCellModel, Builder.Companion.fromFood(DiaryEntryCellModelExtKt.getFood(diaryEntryCellModel)).build());
        }
    }

    public final void deleteItemFromRecents(@NotNull DiaryEntryCellModel diaryEntryCellModel, int i) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        Food food = DiaryEntryCellModelExtKt.getFood(diaryEntryCellModel);
        ((LocalSettingsService) this.localSettingsService.get()).addRecentsDeletedFoodOriginalUid(food.getOriginalUid());
        this.foodSearchAnalyticsHelper.reportDeletedRecentsEvent(food, i);
        Object value = this.searchItems.getValue();
        if (!TypeIntrinsics.isMutableList(value)) {
            value = null;
        }
        List list = (List) value;
        if (list != null) {
            list.remove(diaryEntryCellModel);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000f, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void reportFoodLookupEvent(@org.jetbrains.annotations.NotNull com.myfitnesspal.shared.model.v1.Food r8, int r9) {
        /*
            r7 = this;
            java.lang.String r0 = "food"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper r1 = r7.foodSearchAnalyticsHelper
            com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2$Extras r0 = r7.extras
            if (r0 == 0) goto L_0x0012
            java.lang.String r0 = r0.getMealFoodCreationFlowId()
            if (r0 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            java.lang.String r0 = ""
        L_0x0014:
            r3 = r0
            java.lang.String r4 = r7.getQuery()
            com.myfitnesspal.feature.search.model.SearchSource r6 = com.myfitnesspal.feature.search.model.SearchSource.LOCAL
            r2 = r8
            r5 = r9
            r1.reportFoodLookupEvent(r2, r3, r4, r5, r6)
            com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper r8 = r7.foodSearchAnalyticsHelper
            r9 = 0
            r8.reportSearchEvent(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel.reportFoodLookupEvent(com.myfitnesspal.shared.model.v1.Food, int):void");
    }

    public final void reportSortAndFilterOptionsClicked() {
        SortOrder sortOrder2 = (SortOrder) this.sortOrder.getValue();
        if (sortOrder2 != null) {
            FoodSearchAnalyticsHelper foodSearchAnalyticsHelper2 = this.foodSearchAnalyticsHelper;
            FoodSearchTab foodSearchTab = getFoodSearchTab();
            String mealName = getMealName();
            boolean areEqual = Intrinsics.areEqual((Object) (Boolean) this.shouldFilterAllMeals.getValue(), (Object) Boolean.valueOf(false));
            Intrinsics.checkExpressionValueIsNotNull(sortOrder2, "it");
            foodSearchAnalyticsHelper2.reportDisplayOptionsClicked(foodSearchTab, mealName, areEqual, sortOrder2);
        }
    }

    private final void filterResults(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            this.disposable.add(this.dbCacheSingle.toObservable().concatMapIterable(LocalFoodSearchViewModel$filterResults$1.INSTANCE).filter(new LocalFoodSearchViewModel$filterResults$2(this, lowerCase)).toList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError(LocalFoodSearchViewModel$filterResults$3.INSTANCE).subscribe((Consumer<? super T>) new LocalFoodSearchViewModel$filterResults$4<Object>(this)));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final Single<? extends List<DiaryEntryCellModel>> fetchItems(int i) {
        LocalFoodSearchRepository localFoodSearchRepository = this.searchRepo;
        FoodSearchTab foodSearchTab = getFoodSearchTab();
        if (foodSearchTab != null) {
            switch (foodSearchTab) {
                case MY_FOODS:
                    return localFoodSearchRepository.fetchUserFoods((SortOrder) this.sortOrder.getValue(), i);
                case MEALS:
                    return localFoodSearchRepository.fetchUserMeals((SortOrder) this.sortOrder.getValue(), i, getMealName());
                case RECIPES:
                    return localFoodSearchRepository.fetchUserRecipes((SortOrder) this.sortOrder.getValue(), i);
            }
        }
        return localFoodSearchRepository.fetchHistoryItems((SortOrder) this.sortOrder.getValue(), i, getMealName());
    }

    private final void fetchImagesIfNecessary() {
        Extras extras2 = this.extras;
        if ((extras2 != null ? extras2.getFoodSearchTab() : null) == FoodSearchTab.MEALS) {
            this.disposable.add(this.searchRepo.fetchImagesForFoodOfType(3).subscribeOn(Schedulers.io()).subscribe((Consumer<? super T>) new LocalFoodSearchViewModel$fetchImagesIfNecessary$1<Object>(this)));
        }
    }

    /* JADX INFO: used method not loaded: kotlin.text.StringsKt__StringsKt.contains$default(java.lang.CharSequence, java.lang.CharSequence, boolean, int, java.lang.Object):null, types can be incorrect */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ae, code lost:
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, false, 2, (java.lang.Object) null) == true) goto L_0x00ba;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean itemMatchesQuery(com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r6, java.lang.String r7) {
        /*
            r5 = this;
            boolean r0 = r6.isMealEntries()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            java.lang.String r0 = r6.summaryLine1()
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            boolean r0 = r6.isMealEntries()
            r2 = 2
            r3 = 0
            if (r0 == 0) goto L_0x0044
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r0 = r5.userEnergyService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.service.userdata.UserEnergyService r0 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r0
            java.lang.String r0 = r0.getMealEntriesTitle(r6)
            java.lang.String r4 = "userEnergyService.get().getMealEntriesTitle(item)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            if (r0 == 0) goto L_0x003c
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r4 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r4 = r7
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r4, r1, r2, r3)
            goto L_0x0061
        L_0x003c:
            kotlin.TypeCastException r6 = new kotlin.TypeCastException
            java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
            r6.<init>(r7)
            throw r6
        L_0x0044:
            java.lang.String r0 = r6.summaryLine1()
            java.lang.String r4 = "item.summaryLine1()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            if (r0 == 0) goto L_0x00bb
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r4 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r4 = r7
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r4, r1, r2, r3)
        L_0x0061:
            r4 = 1
            if (r0 == 0) goto L_0x0065
            goto L_0x00ba
        L_0x0065:
            boolean r0 = r6.isFood()
            if (r0 == 0) goto L_0x007c
            if (r6 == 0) goto L_0x0074
            com.myfitnesspal.shared.model.v1.Food r6 = (com.myfitnesspal.shared.model.v1.Food) r6
            java.lang.String r6 = r6.getBrand()
            goto L_0x0091
        L_0x0074:
            kotlin.TypeCastException r6 = new kotlin.TypeCastException
            java.lang.String r7 = "null cannot be cast to non-null type com.myfitnesspal.shared.model.v1.Food"
            r6.<init>(r7)
            throw r6
        L_0x007c:
            boolean r0 = r6 instanceof com.myfitnesspal.shared.model.v1.FoodEntry
            if (r0 == 0) goto L_0x0090
            com.myfitnesspal.shared.model.v1.FoodEntry r6 = (com.myfitnesspal.shared.model.v1.FoodEntry) r6
            com.myfitnesspal.shared.model.v1.Food r6 = r6.getFood()
            java.lang.String r0 = "item.food"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            java.lang.String r6 = r6.getBrand()
            goto L_0x0091
        L_0x0090:
            r6 = r3
        L_0x0091:
            boolean r0 = com.uacf.core.util.Strings.notEmpty(r6)
            if (r0 == 0) goto L_0x00b9
            if (r6 == 0) goto L_0x00b9
            if (r6 == 0) goto L_0x00b1
            java.lang.String r6 = r6.toLowerCase()
            java.lang.String r0 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            if (r6 == 0) goto L_0x00b9
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r6 = kotlin.text.StringsKt.contains$default(r6, r7, r1, r2, r3)
            if (r6 != r4) goto L_0x00b9
            goto L_0x00ba
        L_0x00b1:
            kotlin.TypeCastException r6 = new kotlin.TypeCastException
            java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
            r6.<init>(r7)
            throw r6
        L_0x00b9:
            r4 = 0
        L_0x00ba:
            return r4
        L_0x00bb:
            kotlin.TypeCastException r6 = new kotlin.TypeCastException
            java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel.itemMatchesQuery(com.myfitnesspal.shared.model.v1.DiaryEntryCellModel, java.lang.String):boolean");
    }
}
