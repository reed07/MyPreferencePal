package com.myfitnesspal.shared.service.foods;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiPostData;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.request.FoodAnalyzerRequestPostData;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.api.request.FoodAnalyzerUserRequestPostData;
import com.myfitnesspal.shared.api.request.FoodEntryRequestPostData;
import com.myfitnesspal.shared.api.request.FoodPatchRequest;
import com.myfitnesspal.shared.api.request.FoodQuestionRequestPostData;
import com.myfitnesspal.shared.api.request.FoodRequestPostData;
import com.myfitnesspal.shared.api.request.InsightRequestPostData;
import com.myfitnesspal.shared.api.request.UnitsPreferenceRequestPostData;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.DeletedItemsDBAdapter;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.DeletedMostUsedFoodsTable;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.FoodsTable;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.MealIngredientsContainer;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadListExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveFoodRequestPacket;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;

public class FoodServiceImpl extends SimpleAsyncServiceBase implements FoodService {
    private static final AtomicInteger NEXT_INSIGHTS_REQUEST_ID = new AtomicInteger();
    private static final String QUERY_PARAM_VERSION = "version";
    /* access modifiers changed from: private */
    public final Lazy<ActionTrackingService> actionTrackingService;
    private final Provider<MfpActionApi> api;
    /* access modifiers changed from: private */
    public final Lazy<AuthTokenProvider> authTokens;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<DeletedItemsDBAdapter> deletedItemsAdapter;
    private final DeletedItemsTable deletedItemsTable;
    private final DeletedMostUsedFoodsTable deletedMostUsedFoodsTable;
    private final Lazy<FoodMapper> foodMapper;
    private final Lazy<FoodNotesTable> foodNotesTable;
    private final FoodsTable foodsTable;
    /* access modifiers changed from: private */
    public final Provider<MfpV2Api> foodsV2Api;
    private final Lazy<Session> session;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "FoodServiceImpl";
    }

    public FoodServiceImpl(DeletedMostUsedFoodsTable deletedMostUsedFoodsTable2, Lazy<Session> lazy, FoodsTable foodsTable2, DeletedItemsTable deletedItemsTable2, Lazy<AuthTokenProvider> lazy2, Provider<MfpV2Api> provider, Lazy<ActionTrackingService> lazy3, Provider<MfpActionApi> provider2, Lazy<FoodMapper> lazy4, Lazy<FoodNotesTable> lazy5, Lazy<DeletedItemsDBAdapter> lazy6, Lazy<DbConnectionManager> lazy7) {
        this.deletedMostUsedFoodsTable = deletedMostUsedFoodsTable2;
        this.session = lazy;
        this.foodsTable = foodsTable2;
        this.deletedItemsTable = deletedItemsTable2;
        this.authTokens = lazy2;
        this.foodsV2Api = provider;
        this.actionTrackingService = lazy3;
        this.api = provider2;
        this.foodMapper = lazy4;
        this.dbConnectionManager = lazy7;
        this.foodNotesTable = lazy5;
        this.deletedItemsAdapter = lazy6;
    }

    public void deleteFood(long j, boolean z, long j2, String str, long j3, String str2, boolean z2, boolean z3) {
        long j4 = j;
        boolean z4 = z2;
        if (z && z3) {
            try {
                long localId = ((Session) this.session.get()).getUser().getLocalId();
                if (j2 != j3 || !z4) {
                    this.foodsTable.deleteFood(j4, z4);
                    this.deletedItemsTable.recordDeletedItemForUserId(localId, 1, j2, str, false);
                }
                if (z4) {
                    this.foodsTable.deleteFood(j4, true);
                    this.deletedItemsTable.recordDeletedItemForUserId(localId, 1, j3, str2, true);
                }
            } catch (Exception e) {
                Ln.e(e, "FoodServiceImpl.deleteFood", new Object[0]);
            }
        }
    }

    public void hideFood(long j, String str, long j2, long j3, long j4, String str2, long j5) {
        if (j3 == 0) {
            j3 = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().lookupFoodLocalIdFromMasterId(j4);
        }
        if (j3 == 0) {
            Ln.d("SYNCV2: unable to find food with master ID %s", Long.valueOf(j4));
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("master_id", Long.valueOf(j));
            contentValues.put("uid", str);
            contentValues.put("user_id", Long.valueOf(((Session) this.session.get()).getUser().getLocalId()));
            contentValues.put("original_food_id", Long.valueOf(j3));
            contentValues.put("original_food_master_id", Long.valueOf(j4));
            contentValues.put("original_uid", str2);
            contentValues.put("meal_id", Long.valueOf(j5));
            this.deletedMostUsedFoodsTable.insertData(contentValues);
        } catch (SQLiteConstraintException e) {
            Ln.e(e);
        }
    }

    public int getFoodInsightAsync(NutritionalValues nutritionalValues, List<FoodEntry> list, FoodEntry foodEntry, UserEnergyService userEnergyService, UserWeightService userWeightService, UserDistanceService userDistanceService, float f, Function2<List<FoodAnalyzerResponseData>, Integer> function2) {
        int andIncrement = NEXT_INSIGHTS_REQUEST_ID.getAndIncrement();
        final List<FoodEntry> list2 = list;
        final FoodEntry foodEntry2 = foodEntry;
        final float f2 = f;
        final UserEnergyService userEnergyService2 = userEnergyService;
        final UserWeightService userWeightService2 = userWeightService;
        final UserDistanceService userDistanceService2 = userDistanceService;
        final NutritionalValues nutritionalValues2 = nutritionalValues;
        final Function2<List<FoodAnalyzerResponseData>, Integer> function22 = function2;
        final int i = andIncrement;
        AnonymousClass1 r1 = new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList();
                for (FoodEntry foodEntry : list2) {
                    Food food = foodEntry.getFood();
                    FoodRequestPostData foodRequestPostData = new FoodRequestPostData(Long.toString(food.getMasterDatabaseId()), food.getFoodType(), new MfpNutritionalValues(food.getNutritionalValues().getValues()), food.getGrams(), food.getBrand());
                    FoodEntryRequestPostData foodEntryRequestPostData = new FoodEntryRequestPostData(foodEntry.getDate(), foodRequestPostData, foodEntry.getMealName(), foodEntry.getQuantity(), foodEntry.getFoodPortion(), foodEntry.isFraction(), foodEntry.getWeightIndex(), foodEntry.getDescription());
                    arrayList.add(foodEntryRequestPostData);
                }
                Food food2 = foodEntry2.getFood();
                FoodRequestPostData foodRequestPostData2 = new FoodRequestPostData(Long.toString(food2.getMasterDatabaseId()), food2.getFoodType(), new MfpNutritionalValues(food2.getNutritionalValues().getValues()), food2.getGrams(), food2.getBrand());
                FoodEntryRequestPostData foodEntryRequestPostData2 = new FoodEntryRequestPostData(foodEntry2.getDate(), foodRequestPostData2, foodEntry2.getMealName(), foodEntry2.getQuantity(), foodEntry2.getFoodPortion(), foodEntry2.isFraction(), foodEntry2.getWeightIndex(), foodEntry2.getDescription());
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new FoodAnalyzerRequestPostData(arrayList, foodEntryRequestPostData2, f2, ""));
                try {
                    FoodServiceImpl.this.postToMainThread(function22, (List) Enumerable.where((Collection<T>) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) FoodServiceImpl.this.foodsV2Api.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new ApiPostData(new InsightRequestPostData(new FoodAnalyzerUserRequestPostData(((AuthTokenProvider) FoodServiceImpl.this.authTokens.get()).getDomainUserId(), new UnitsPreferenceRequestPostData(userEnergyService2.getUserCurrentEnergyUnit().name(), userWeightService2.getCurrentWeightUnitStringWithoutStone(), userDistanceService2.getCurrentDistanceUnitString()), new MfpNutritionalValues(nutritionalValues2.getValues())), arrayList2)))).post(Uri.INSIGHTS, new Object[0])).getItems(), (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, FoodAnalyzerResponseData>() {
                        public Boolean execute(FoodAnalyzerResponseData foodAnalyzerResponseData) {
                            foodAnalyzerResponseData.setAssociatedFoodEntryLocalId(foodEntry2.getLocalId());
                            return Boolean.valueOf((foodAnalyzerResponseData.getFoodInsight() == null && foodAnalyzerResponseData.getFoodQuestion() == null) ? false : true);
                        }
                    }), Integer.valueOf(i));
                } catch (ApiException e) {
                    Ln.e(e);
                    FoodServiceImpl.this.postToMainThread(function22, null, Integer.valueOf(i));
                }
            }
        };
        auto(r1);
        return andIncrement;
    }

    public void postFoodQuestionAnswer(Context context, FoodEntry foodEntry, int i, boolean z, final Function0 function0) {
        FoodQuestionRequestPostData foodQuestionRequestPostData = new FoodQuestionRequestPostData(((AuthTokenProvider) this.authTokens.get()).getDomainUserId(), foodEntry.getDate(), foodEntry.getMealName(), Long.toString(foodEntry.getFood().getMasterDatabaseId()), i, Boolean.toString(z));
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.foodsV2Api.get()).withOutputType(ApiResponse.API_RESPONSE_MAPPER.class)).withJsonBody(new ApiPostData(foodQuestionRequestPostData))).putAsync(Uri.INSIGHTS, (Function1<T>) new Function1<ApiResponse>() {
            public void execute(ApiResponse apiResponse) {
                FunctionUtils.invokeIfValid(function0);
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                Ln.e(apiException);
                FunctionUtils.invokeIfValid(function0);
            }
        });
    }

    public void getSuggestedServingsAsync(final String str, final String str2, final Function1<ApiResponse<MfpServingSize>> function1) {
        auto(new Runnable() {
            public void run() {
                try {
                    FoodServiceImpl.this.postToMainThread(function1, (ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) FoodServiceImpl.this.foodsV2Api.get()).withOutputType(MfpServingSize.API_RESPONSE_MAPPER.class)).withFlowId(((ActionTrackingService) FoodServiceImpl.this.actionTrackingService.get()).getTrackingDataForEvent(Events.SERVING_SIZE_LOOKUP, "flow_id"))).get(String.format(Uri.SUGGESTED_SERVINGS, new Object[]{Strings.toString(str)}), "version", str2));
                } catch (ApiException e) {
                    e.printStackTrace();
                    FoodServiceImpl.this.postToMainThread(function1, null);
                }
            }
        });
    }

    public ApiResponse<MfpServingSize> getSuggestedServings(String str, String str2) {
        try {
            return (ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.foodsV2Api.get()).withOutputType(MfpServingSize.API_RESPONSE_MAPPER.class)).withFlowId(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent(Events.SERVING_SIZE_LOOKUP, "flow_id"))).get(String.format(Uri.SUGGESTED_SERVINGS, new Object[]{Strings.toString(str)}), "version", str2);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Single<List<MfpServingSize>> fetchSuggestedServings(@NonNull String str, @NonNull String str2) {
        return Single.fromCallable(new Callable(str, str2) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return FoodServiceImpl.lambda$fetchSuggestedServings$0(FoodServiceImpl.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ List lambda$fetchSuggestedServings$0(@NonNull FoodServiceImpl foodServiceImpl, @NonNull String str, String str2) throws Exception {
        ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) foodServiceImpl.foodsV2Api.get()).withOutputType(MfpServingSize.API_RESPONSE_MAPPER.class)).withFlowId(((ActionTrackingService) foodServiceImpl.actionTrackingService.get()).getTrackingDataForEvent(Events.SERVING_SIZE_LOOKUP, "flow_id"))).get(String.format(Uri.SUGGESTED_SERVINGS, new Object[]{Strings.toString(str)}), "version", str2);
        if (apiResponse != null && apiResponse.getItems() != null && !apiResponse.getItems().isEmpty()) {
            return apiResponse.getItems();
        }
        throw new IllegalArgumentException("Serving sizes are empty");
    }

    public void patchFoodServingsAsync(List<MfpServingSize> list, String str, String str2, Function1<ApiResponse<MfpFood>> function1) {
        final List<MfpServingSize> list2 = list;
        final String str3 = str;
        final String str4 = str2;
        final Function1<ApiResponse<MfpFood>> function12 = function1;
        AnonymousClass5 r0 = new Runnable() {
            public void run() {
                try {
                    FoodServiceImpl.this.postToMainThread(function12, (ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) FoodServiceImpl.this.foodsV2Api.get()).withOutputType(MfpFood.API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(new FoodPatchRequest(list2)))).patch(String.format("/v2/foods/%1$s", new Object[]{str3}), "version", str4));
                } catch (ApiException e) {
                    Ln.e(e);
                    FoodServiceImpl.this.postToMainThread(function12, null);
                }
            }
        };
        auto(r0);
    }

    public MfpFood patchFoodServings(List<MfpServingSize> list, String str, String str2) {
        try {
            return (MfpFood) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.foodsV2Api.get()).withOutputType(MfpFood.API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(new FoodPatchRequest(list)))).patch(String.format("/v2/foods/%1$s", new Object[]{str}), "version", str2)).getItem();
        } catch (ApiException e) {
            Ln.e(e);
            return null;
        }
    }

    public Tuple2<MealFood, FoodNotes> getMealFoodForId(String str, boolean z) throws ApiException {
        if (z) {
            MealFood mealFoodForUid = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().getMealFoodForUid(str);
            return Tuple2.create(mealFoodForUid, ((FoodNotesTable) this.foodNotesTable.get()).findByFood(mealFoodForUid));
        }
        return processPacketsAndReturnCompleteMealFood(str, (List) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new RetrieveFoodRequestPacket(0, str, false))).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(Arrays.asList(new Integer[]{Integer.valueOf(3), Integer.valueOf(11), Integer.valueOf(44)})), new Object[0]));
    }

    public FoodNotes saveFoodNotes(String str, Food food) {
        if (str.isEmpty()) {
            FoodNotes findByFood = ((FoodNotesTable) this.foodNotesTable.get()).findByFood(food);
            if (findByFood != null) {
                if (findByFood.getMasterId() > 0) {
                    ((DeletedItemsDBAdapter) this.deletedItemsAdapter.get()).recordDeletedItemForUserId(((Session) this.session.get()).getUser().getLocalId(), 27, findByFood.getMasterId(), false);
                }
                ((FoodNotesTable) this.foodNotesTable.get()).deleteByLocalId(findByFood.getLocalId());
            }
            return null;
        }
        ((FoodNotesTable) this.foodNotesTable.get()).deleteByFood(food);
        FoodNotes foodNotes = new FoodNotes();
        foodNotes.setFoodLocalId(food.getLocalId());
        foodNotes.setFoodMasterId(food.getMasterDatabaseId());
        foodNotes.setFoodUid(food.getUid());
        foodNotes.setOriginalFoodMasterId(food.getOriginalMasterId());
        foodNotes.setOriginalFoodUid(food.getOriginalUid());
        foodNotes.setNotes(str);
        foodNotes.setUserId(((Session) this.session.get()).getUser().getLocalId());
        foodNotes.setLocalId(((FoodNotesTable) this.foodNotesTable.get()).save(foodNotes));
        return foodNotes;
    }

    private Tuple2<MealFood, FoodNotes> processPacketsAndReturnCompleteMealFood(String str, List<BinaryApiSerializable> list) {
        ArrayList<Food> arrayList = new ArrayList<>();
        MealFood mealFood = null;
        MealIngredientsContainer mealIngredientsContainer = null;
        Object obj = null;
        for (BinaryApiSerializable binaryApiSerializable : list) {
            if (binaryApiSerializable instanceof FoodObject) {
                Food food = (Food) ((FoodMapper) this.foodMapper.get()).reverseMap((FoodObject) binaryApiSerializable);
                if (mealFood != null || !(food instanceof MealFood)) {
                    arrayList.add(food);
                } else {
                    mealFood = (MealFood) food;
                }
            } else if (binaryApiSerializable instanceof FoodNotes) {
                obj = (FoodNotes) binaryApiSerializable;
            } else if (binaryApiSerializable instanceof MealIngredientsContainer) {
                mealIngredientsContainer = (MealIngredientsContainer) binaryApiSerializable;
            }
        }
        logAndThrowIfInvalid(str, mealFood, mealIngredientsContainer, arrayList);
        Map foodMasterIdToIngredientMap = mealIngredientsContainer.getFoodMasterIdToIngredientMap();
        ArrayList arrayList2 = new ArrayList(foodMasterIdToIngredientMap.size());
        for (Food food2 : arrayList) {
            MealIngredient mealIngredient = (MealIngredient) foodMasterIdToIngredientMap.get(Long.valueOf(food2.getMasterDatabaseId()));
            mealIngredient.setIngredientFood(food2);
            mealIngredient.setFoodPortion(food2.foodPortionWithIndex(mealIngredient.getWeightIndex()));
            arrayList2.add(mealIngredient);
        }
        mealFood.setIngredients(arrayList2);
        return Tuple2.create(mealFood, obj);
    }

    private void logAndThrowIfInvalid(String str, MealFood mealFood, MealIngredientsContainer mealIngredientsContainer, List<Food> list) {
        String str2 = mealFood == null ? "MealFood is null" : mealIngredientsContainer == null ? "No Meal Ingredients" : CollectionUtils.isEmpty((Collection<?>) list) ? "No Ingredient Foods" : null;
        if (str2 != null) {
            String format = String.format("Packet 159 Error - FoodId: %s Reason: %s", new Object[]{Strings.toString(str), str2});
            Ln.e(format, new Object[0]);
            throw new IllegalStateException(format);
        }
    }
}
