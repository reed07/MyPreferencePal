package com.myfitnesspal.feature.search.service;

import com.myfitnesspal.feature.addentry.util.FoodComparator;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.v1.MfpSearchApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Http;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.db.adapter.GenericAdapter;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodOrExercise;
import com.myfitnesspal.shared.model.v1.MealEntries;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v15.SearchRequestObject;
import com.myfitnesspal.shared.model.v15.SearchResult;
import com.myfitnesspal.shared.model.v15.SearchResultFactory;
import com.myfitnesspal.shared.model.v2.CategoryMappingResponse;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFood.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.ExerciseSearchRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.SearchRequestPacket;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.Tuple3;
import dagger.Lazy;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

public class SearchServiceImpl extends SimpleAsyncServiceBase implements SearchService {
    private static final String HTTP_HEADER_REQUEST_ID = "mfp-request-id";
    private static final int MAX_SEARCH_RESULTS_PER_PAGE = 25;
    private static final String URL_PARAM_VERSION = "version";
    private static final String URL_QUERY_PARAM_FOOD_AD_SHOWN = "client_food_ad_shown";
    private static final String URL_QUERY_PARAM_SCOPE = "scope";
    private static final String URL_QUERY_PARAM_TERM = "term";
    private static final String URL_QUERY_VALUE_TRUE = "true";
    private static final String URL_QUERY_VALUE_VERIFIED = "verified";
    private final Lazy<ActionTrackingService> actionTrackingService;
    private final Provider<MfpSearchApi> api;
    private final Provider<MfpV2Api> apiProvider;
    private final Lazy<CountryService> countryService;
    /* access modifiers changed from: private */
    public final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<MealUtil> mealUtil;
    private final Lazy<Session> session;
    private final Lazy<SortOrderHelper> sortOrderHelper;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "SearchServiceImpl";
    }

    public SearchServiceImpl(Provider<MfpSearchApi> provider, Provider<MfpV2Api> provider2, Lazy<ActionTrackingService> lazy, Lazy<SortOrderHelper> lazy2, Lazy<MealUtil> lazy3, Lazy<Session> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<CountryService> lazy6, Lazy<DbConnectionManager> lazy7) {
        this.api = provider;
        this.apiProvider = provider2;
        this.actionTrackingService = lazy;
        this.sortOrderHelper = lazy2;
        this.mealUtil = lazy3;
        this.session = lazy4;
        this.localSettingsService = lazy5;
        this.countryService = lazy6;
        this.dbConnectionManager = lazy7;
    }

    private static Object[] getSearchV2QueryParams(String str) {
        return getSearchV2QueryParams(str, false, false, false);
    }

    /* access modifiers changed from: private */
    public static Object[] getSearchV2QueryParams(String str, boolean z) {
        return getSearchV2QueryParams(str, z, false, false);
    }

    private static Object[] getSearchV2QueryParams(String str, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Http.Q);
        arrayList.add(str);
        arrayList.add("scope");
        arrayList.add(z3 ? URL_QUERY_VALUE_VERIFIED : "all");
        arrayList.add("max_items");
        arrayList.add(Integer.valueOf(25));
        arrayList.add(Http.RESOURCE_TYPE_ARRAY);
        arrayList.add("foods");
        arrayList.add("fields[]");
        arrayList.add("id");
        arrayList.add("fields[]");
        arrayList.add("nutritional_contents");
        arrayList.add("fields[]");
        arrayList.add(Http.SERVING_SIZES);
        arrayList.add("fields[]");
        arrayList.add("version");
        arrayList.add("fields[]");
        arrayList.add("brand_name");
        arrayList.add("fields[]");
        arrayList.add("description");
        if (z) {
            arrayList.add(Http.RESOURCE_TYPE_ARRAY);
            arrayList.add("venues");
        }
        if (z2) {
            arrayList.add(URL_QUERY_PARAM_FOOD_AD_SHOWN);
            arrayList.add("true");
        }
        return arrayList.toArray(new Object[0]);
    }

    public Tuple2<ApiResponse<MfpFoodSearchResult>, String> searchForFoodV2(String str, String str2) throws ApiException {
        return searchFoodV2Internal(Uri.SEARCH_NUTRITION, str2, getSearchV2QueryParams(str));
    }

    public Single<Tuple3<ApiResponse<MfpFoodSearchResult>, String, String>> searchForFoodV2(String str, String str2, boolean z, boolean z2, boolean z3) {
        $$Lambda$SearchServiceImpl$IRFyOLtTIn3Dqd71VHKyWtUTaA r0 = new SingleOnSubscribe(str2, str, z, z2, z3) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ boolean f$3;
            private final /* synthetic */ boolean f$4;
            private final /* synthetic */ boolean f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            public final void subscribe(SingleEmitter singleEmitter) {
                SearchServiceImpl.lambda$searchForFoodV2$0(SearchServiceImpl.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, singleEmitter);
            }
        };
        return Single.create(r0);
    }

    public static /* synthetic */ void lambda$searchForFoodV2$0(SearchServiceImpl searchServiceImpl, String str, String str2, boolean z, boolean z2, boolean z3, SingleEmitter singleEmitter) throws Exception {
        Tuple3 searchFoodV2Internal = searchServiceImpl.searchFoodV2Internal(Uri.SEARCH_NUTRITION, str, getSearchV2QueryParams(str2, z, z2, z3));
        if (!singleEmitter.isDisposed()) {
            singleEmitter.onSuccess(searchFoodV2Internal);
        }
    }

    private void searchV2Async(String str, String str2, boolean z, Function2<ApiResponse<MfpFoodSearchResult>, String> function2, MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        final String str3 = str2;
        final String str4 = str;
        final boolean z2 = z;
        final Function2<ApiResponse<MfpFoodSearchResult>, String> function22 = function2;
        final MfpV2ApiErrorCallback mfpV2ApiErrorCallback2 = mfpV2ApiErrorCallback;
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                try {
                    Tuple3 access$100 = SearchServiceImpl.this.searchFoodV2Internal(Uri.SEARCH_NUTRITION, str3, SearchServiceImpl.getSearchV2QueryParams(str4, z2));
                    SearchServiceImpl.this.invokeOnMainThread(function22, access$100.getItem1(), access$100.getItem2());
                } catch (ApiException e) {
                    SearchServiceImpl.this.invokeOnMainThread(mfpV2ApiErrorCallback2, MfpApiUtil.mapException(e));
                }
            }
        };
        async(r0);
    }

    public Tuple2<ApiResponse<MfpFoodSearchResult>, String> getMoreResultsV2(String str, String str2) throws ApiException {
        return searchFoodV2Internal(str, str2, new Object[0]);
    }

    public List<SearchResult> searchForExercise(int i, String str, int i2) {
        return searchForFoodOrExercise(new ExerciseSearchRequestPacket(i, str, i2));
    }

    public MfpFood findByIdAndVersion(String str, String str2) throws ApiException {
        ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(String.format("/v2/foods/%1$s", new Object[]{str}), Strings.notEmpty(str2) ? new Object[]{"version", str2} : null);
        if (apiResponse != null && apiResponse.getItem() != null) {
            return (MfpFood) apiResponse.getItem();
        }
        throw new ApiException("valid response, but no items!", 200);
    }

    public void fetchPairedFoods(long j, int i, String str, Function1<List<DiaryEntryCellModel>> function1) {
        final long j2 = j;
        final int i2 = i;
        final String str2 = str;
        final Function1<List<DiaryEntryCellModel>> function12 = function1;
        AnonymousClass2 r0 = new Runnable() {
            public void run() {
                SearchServiceImpl.this.postToMainThread(function12, ((DbConnectionManager) SearchServiceImpl.this.dbConnectionManager.get()).foodEntriesDbAdapter().fetchPairedFoods(j2, (long) i2, str2));
            }
        };
        auto(r0);
    }

    @Deprecated
    public List<DiaryEntryCellModel> fetchResultsFromDBSync(String str, int i, int i2) {
        return fetchResultsFromDBSync(str, ((SortOrderHelper) this.sortOrderHelper.get()).getCurrentSortOrderForTab(i), i, i2);
    }

    @Deprecated
    public List<DiaryEntryCellModel> fetchResultsFromDBSync(String str, SortOrder sortOrder, int i, int i2) {
        List<DiaryEntryCellModel> list;
        String str2 = str;
        SortOrder sortOrder2 = sortOrder;
        int i3 = i;
        User user = ((Session) this.session.get()).getUser();
        long localId = user.getLocalId();
        int mealIdForName = ((LocalSettingsService) this.localSettingsService.get()).shouldShowAllMeals() ? 0 : user.getMealNames().mealIdForName(str2);
        FoodDBAdapter foodDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter();
        FoodEntriesDBAdapter foodEntriesDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).foodEntriesDbAdapter();
        MealFood mealFood = null;
        if (i3 != 6008) {
            switch (i3) {
                case SearchTabs.TAB_FREQUENT_FOODS /*6000*/:
                    list = foodEntriesDbAdapter.fetchFrequentFoodsForUserId(localId, mealIdForName, i2 + 1);
                    break;
                case 6001:
                    break;
                case 6002:
                    list = foodDbAdapter.fetchOwnedFoodsOfType(1, sortOrder2, i2 + 1, 0);
                    break;
                case 6003:
                    List fetchOwnedFoodsOfType = foodDbAdapter.fetchOwnedFoodsOfType(3, sortOrder2, i2 + 1, 0);
                    if (fetchOwnedFoodsOfType == null) {
                        fetchOwnedFoodsOfType = new ArrayList();
                    }
                    MealEntries latestPreviousFoodEntriesForMealName = latestPreviousFoodEntriesForMealName(str2, user);
                    if (latestPreviousFoodEntriesForMealName == null) {
                        list = fetchOwnedFoodsOfType;
                        break;
                    } else {
                        mealFood = ((MealUtil) this.mealUtil.get()).getMealFoodFromMealEntries(latestPreviousFoodEntriesForMealName);
                        list = fetchOwnedFoodsOfType;
                        break;
                    }
                case 6004:
                    list = Enumerable.select((Collection<T>) ((DbConnectionManager) this.dbConnectionManager.get()).recipeBoxItemsDBAdapter().fetchRecipeBoxItemsWithSortOrder(sortOrder2, i2 + 1, 0), (ReturningFunction1<U, T>) new ReturningFunction1() {
                        public final Object execute(Object obj) {
                            return ((RecipeBoxItem) obj).recipeFood((DbConnectionManager) SearchServiceImpl.this.dbConnectionManager.get());
                        }
                    });
                    break;
                default:
                    list = null;
                    break;
            }
        }
        if (!((CountryService) this.countryService.get()).getCurrentLanguage().equalsIgnoreCase("en")) {
            list = foodEntriesDbAdapter.fetchRecentFrequentAndOwnedFoodsForUserId(1, localId, mealIdForName, i2 + 1);
        } else {
            list = foodEntriesDbAdapter.fetchRecentlyUsedFoodsForUserId(localId, (long) mealIdForName, i2 + 1);
        }
        Set recentsDeletedFoodOriginalUids = ((LocalSettingsService) this.localSettingsService.get()).getRecentsDeletedFoodOriginalUids();
        ArrayList arrayList = new ArrayList();
        for (DiaryEntryCellModel diaryEntryCellModel : list) {
            if (recentsDeletedFoodOriginalUids.contains((diaryEntryCellModel.isFood() ? (Food) diaryEntryCellModel : ((FoodEntry) diaryEntryCellModel).getFood()).getOriginalUid())) {
                arrayList.add(diaryEntryCellModel);
            }
        }
        list.removeAll(arrayList);
        if (list != null && (sortOrder2 == SortOrder.ALPHABETICAL_ASCENDING || sortOrder2 == SortOrder.ALPHABETICAL_DESCENDING)) {
            Collections.sort(list, new FoodComparator(sortOrder2));
        }
        if (mealFood != null) {
            list.add(0, mealFood);
        }
        if (i3 == 6001 || i3 == 6000) {
            HashSet hashSet = new HashSet();
            Iterator it = list.iterator();
            long localId2 = ((Session) this.session.get()).getUser().getLocalId();
            while (it.hasNext()) {
                DiaryEntryCellModel diaryEntryCellModel2 = (DiaryEntryCellModel) it.next();
                if (diaryEntryCellModel2 instanceof Food) {
                    Food food = (Food) diaryEntryCellModel2;
                    if (food.isDeleted() && !food.isPublic() && localId2 == food.getOwnerUserId()) {
                        it.remove();
                    } else if (food.hasOriginalUid()) {
                        String originalUid = food.getOriginalUid();
                        if (hashSet.contains(originalUid)) {
                            it.remove();
                        } else {
                            hashSet.add(originalUid);
                        }
                    }
                }
            }
        }
        return foodEntriesDbAdapter.replaceFoodsWithCorrespondingRecentFoodEntries(list);
    }

    public Single<String> mapQueryToAdCategory(String str) {
        return Single.create(new SingleOnSubscribe(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(SingleEmitter singleEmitter) {
                SearchServiceImpl.lambda$mapQueryToAdCategory$2(SearchServiceImpl.this, this.f$1, singleEmitter);
            }
        });
    }

    public static /* synthetic */ void lambda$mapQueryToAdCategory$2(SearchServiceImpl searchServiceImpl, String str, SingleEmitter singleEmitter) throws Exception {
        MfpV2Api mfpV2Api = (MfpV2Api) searchServiceImpl.apiProvider.get();
        ArrayList arrayList = new ArrayList();
        arrayList.add("term");
        arrayList.add(str);
        CategoryMappingResponse categoryMappingResponse = (CategoryMappingResponse) ((MfpV2Api) mfpV2Api.withOutputType(CategoryMappingResponse.class)).get(Uri.SEARCH_FOOD_SEARCH_MAPPING, arrayList.toArray(new Object[0]));
        if (!singleEmitter.isDisposed()) {
            singleEmitter.onSuccess(categoryMappingResponse.getCategory());
        }
    }

    private List<SearchResult> searchForFoodOrExercise(SearchRequestPacket searchRequestPacket) {
        return performOnlineWithOfflineFallbackSearch(searchRequestPacket);
    }

    private List<SearchResult> performOnlineWithOfflineFallbackSearch(SearchRequestPacket searchRequestPacket) {
        try {
            return (List) ((MfpSearchApi) ((MfpSearchApi) ((MfpSearchApi) this.api.get()).addPacket(searchRequestPacket)).withFlowId(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent(Attributes.SEARCH_EVENT, Attributes.SEARCH_FLOWID))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(19), new Object[0]);
        } catch (ApiException e) {
            Ln.e(e);
            return performOfflineSearch(searchRequestPacket);
        }
    }

    private List<SearchResult> performOfflineSearch(SearchRequestPacket searchRequestPacket) {
        ArrayList arrayList;
        SearchRequestObject searchRequestObject = searchRequestPacket.getSearchRequestObject();
        GenericAdapter genericDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).genericDbAdapter();
        String searchTerm = searchRequestObject.getSearchTerm();
        int maxResults = searchRequestObject.getMaxResults();
        User user = ((Session) this.session.get()).getUser();
        long masterDatabaseId = user.getMasterDatabaseId();
        long localId = user.getLocalId();
        if (searchRequestObject.getType() == 2) {
            arrayList = genericDbAdapter.searchExercises(searchRequestObject.getSubType(), searchTerm, maxResults, localId, masterDatabaseId);
        } else {
            arrayList = genericDbAdapter.searchFoods(searchTerm, maxResults, localId, masterDatabaseId);
        }
        return Enumerable.select((Collection<T>) arrayList, (ReturningFunction1<U, T>) new ReturningFunction1<SearchResult, FoodOrExercise>() {
            public SearchResult execute(FoodOrExercise foodOrExercise) {
                return SearchResultFactory.createSearchResult(foodOrExercise, SearchServiceImpl.this.dbConnectionManager);
            }
        });
    }

    /* access modifiers changed from: private */
    public Tuple3<ApiResponse<MfpFoodSearchResult>, String, String> searchFoodV2Internal(String str, String str2, Object... objArr) throws ApiException {
        MfpV2Api mfpV2Api = (MfpV2Api) this.apiProvider.get();
        ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) mfpV2Api.withOutputType(MfpFoodSearchResult.API_RESPONSE_MAPPER.class)).withFlowId(str2)).get(str, objArr);
        Map responseHeaders = mfpV2Api.getResponseHeaders();
        List list = (List) responseHeaders.get("link");
        List list2 = (List) responseHeaders.get(HTTP_HEADER_REQUEST_ID);
        return Tuple.create(apiResponse, Strings.extractUrl((list == null || list.size() <= 0) ? "" : MfpApiUtil.getNextUrlFromLink((String) list.get(0))), (list2 == null || list2.isEmpty()) ? "" : (String) list2.get(0));
    }

    public MealEntries latestPreviousFoodEntriesForMealName(String str, User user) {
        MealEntries mealEntries = null;
        if (Strings.isEmpty(str)) {
            return null;
        }
        int mealIdForName = user.getMealNames().mealIdForName(str);
        ArrayList fetchLatestPreviousFoodEntriesForMealId = ((DbConnectionManager) this.dbConnectionManager.get()).foodEntriesDbAdapter().fetchLatestPreviousFoodEntriesForMealId(mealIdForName);
        if (fetchLatestPreviousFoodEntriesForMealId != null) {
            mealEntries = new MealEntries(((FoodEntry) fetchLatestPreviousFoodEntriesForMealId.get(fetchLatestPreviousFoodEntriesForMealId.size() - 1)).getDate(), mealIdForName, fetchLatestPreviousFoodEntriesForMealId, this.session);
        }
        return mealEntries;
    }
}
