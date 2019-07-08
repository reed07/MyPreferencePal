package com.myfitnesspal.feature.meals.service;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.meals.service.MealService.CreateMode;
import com.myfitnesspal.feature.meals.service.MealService.ImageMode;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.MealIngredientsDBAdapter;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Provider;

public class MealServiceImpl implements MealService {
    private static final String MEAL = "meal";
    private static final int MEAL_BROWSE_PAGE_SIZE = 20;
    private static final String REQUEST_FIELD_LEGACY = "legacy";
    private final Provider<MfpV2Api> api;
    private final Context context;
    private final Lazy<CountryService> countryService;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<FoodPermissionsTable> foodPermissionsTable;
    private final Lazy<ImageAssociationService> imageAssociationService;
    private final Lazy<Session> session;
    private final Lazy<SyncService> syncService;

    public MealServiceImpl(Context context2, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<DiaryService> lazy2, Lazy<ImageAssociationService> lazy3, Lazy<SyncService> lazy4, Lazy<FoodPermissionsTable> lazy5, Lazy<DbConnectionManager> lazy6, Lazy<CountryService> lazy7) {
        this.context = context2.getApplicationContext();
        this.api = provider;
        this.session = lazy;
        this.diaryService = lazy2;
        this.imageAssociationService = lazy3;
        this.syncService = lazy4;
        this.foodPermissionsTable = lazy5;
        this.dbConnectionManager = lazy6;
        this.countryService = lazy7;
    }

    public MealFood createCustomMeal(CreateMode createMode, String str, List<FoodEntry> list, MealFood mealFood, Permission permission, ImageMode imageMode, String str2, long j, String str3) {
        if (list == null) {
            return null;
        }
        User user = ((Session) this.session.get()).getUser();
        MealFood mealFood2 = new MealFood();
        mealFood2.setOwnerUserId(user.getLocalId());
        mealFood2.setOwnerUserMasterId(user.getMasterDatabaseId());
        mealFood2.setDescription(str);
        mealFood2.setBrand("");
        mealFood2.setPromotedFromMasterId(j);
        mealFood2.setPromotedFromUid(str3);
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setGramWeight(1.0f);
        foodPortion.setWeightIndex(0);
        foodPortion.setAmount(1.0f);
        foodPortion.setDescription("meal");
        mealFood2.setFoodPortions(new FoodPortion[]{foodPortion});
        NutritionalValues initAsBlank = new NutritionalValues().initAsBlank();
        for (FoodEntry foodEntry : list) {
            initAsBlank.addNutritionalValues(foodEntry.getFood().getNutritionalValues(), foodEntry.getFood().nutrientMultiplierForFoodPortion(foodEntry.getFoodPortion()) * foodEntry.getQuantity());
        }
        mealFood2.setNutritionalValues(initAsBlank);
        FoodDBAdapter foodDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter();
        MealIngredientsDBAdapter mealIngredientsDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).mealIngredientsDbAdapter();
        if (createMode == CreateMode.Create || createMode == CreateMode.Update) {
            foodDbAdapter.insertFood(mealFood2, mealFood, (DbConnectionManager) this.dbConnectionManager.get());
        } else {
            foodDbAdapter.insertFood(mealFood2, null, (DbConnectionManager) this.dbConnectionManager.get());
        }
        if (imageMode == ImageMode.Associate) {
            associateMealImages(mealFood2, mealFood, str2);
        } else if (imageMode == ImageMode.Disassociate) {
            disassociateMealImage(mealFood2);
        }
        for (MealIngredient mealIngredient : getMealIngredientsFromFoodEntries(list, mealFood2.getLocalId())) {
            mealFood2.addIngredient(mealIngredient);
            mealIngredientsDbAdapter.insertMealIngredient(mealIngredient);
        }
        return mealFood2;
    }

    public void associateMealImages(MealFood mealFood, MealFood mealFood2, String str) {
        if (mealFood.getLocalId() > 0) {
            boolean z = false;
            if (Strings.notEmpty(str)) {
                try {
                    String copyFileToUploadDirectory = ImageUploadUtil.copyFileToUploadDirectory(this.context, str);
                    if (Strings.notEmpty(copyFileToUploadDirectory)) {
                        disassociateMealImage(mealFood);
                        z = ((ImageAssociationService) this.imageAssociationService.get()).associate(copyFileToUploadDirectory, "food_entry", mealFood.getLocalId(), mealFood.getUid());
                    }
                } catch (IOException e) {
                    Ln.e(e, "failed to copy image to upload directory", new Object[0]);
                }
            }
            if (!z && mealFood2 != null) {
                ((ImageAssociationService) this.imageAssociationService.get()).copyAssociationsToResource("food_entry", mealFood2.getLocalId(), mealFood2.getUid(), "food_entry", mealFood.getLocalId(), mealFood.getUid());
            }
        }
    }

    public void disassociateMealImage(MealFood mealFood) {
        ((ImageAssociationService) this.imageAssociationService.get()).markForRemoteDisassociation(mealFood.getLocalId(), mealFood.getUid(), "food_entry");
    }

    public void saveMealFoodToDiary(List<FoodEntry> list, String str, MealFoodLoggedInfo mealFoodLoggedInfo, MealFood mealFood, TimeValue timeValue, int i) {
        ArrayList arrayList = new ArrayList();
        User user = ((Session) this.session.get()).getUser();
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        for (FoodEntry foodEntry : list) {
            foodEntry.setDate(user.getActiveDate());
            foodEntry.setMealName(str);
            foodEntry.setMealFood(mealFood);
            diaryDayForActiveDateSync.addFoodEntry(foodEntry);
            addFoodToLoggedFoodList(foodEntry, arrayList, mealFoodLoggedInfo);
        }
        HashMap hashMap = new HashMap();
        if (mealFoodLoggedInfo != null) {
            hashMap.put(Attributes.MEAL_COUNT, Strings.toString(Integer.valueOf(1)));
            hashMap.put("source", Strings.toString(mealFoodLoggedInfo.getSource()));
            hashMap.put("list_type", mealFoodLoggedInfo.getListType());
        }
        hashMap.put("locale", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2());
        hashMap.put("meal", Strings.toString(str).toLowerCase());
        hashMap.put(Attributes.DIARY_DATE, DateTimeUtils.diaryDateAnalyticsFormat(diaryDayForActiveDateSync.getDate()));
        hashMap.put("foods", new ApiJsonMapper().reverseMap((Object) arrayList));
        hashMap.put(Attributes.CONTAINS_FOOD_AD, Strings.toString(Boolean.valueOf(FoodV2Logging.listContainsAdFood(arrayList))));
        hashMap.put("version", Strings.toString(Integer.valueOf(i)));
        if (timeValue != null) {
            hashMap.put(TimestampAnalyticsHelper.ATTR_TIME, timeValue.getAnalyticsName());
        }
        ((DiaryService) this.diaryService.get()).endFoodLoggingFlow(hashMap);
    }

    public MealFood getExistingMealFoodWithDescription(String str) {
        return ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().getExistingMealFoodWithDescription(str, ((Session) this.session.get()).getUser().getLocalId());
    }

    public void deleteMealFood(MealFood mealFood) {
        ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().deleteFood(mealFood, false, true);
    }

    private void addFoodToLoggedFoodList(FoodEntry foodEntry, List<FoodV2Logging> list, MealFoodLoggedInfo mealFoodLoggedInfo) {
        int i;
        Food food = foodEntry.getFood();
        String str = "";
        String str2 = "";
        if (mealFoodLoggedInfo != null) {
            str = mealFoodLoggedInfo.getSearchQuery();
            int resultsListPosition = mealFoodLoggedInfo.getResultsListPosition();
            str2 = mealFoodLoggedInfo.getSource();
            i = resultsListPosition;
        } else {
            i = 0;
        }
        if (food != null) {
            list.add(Builder.fromFood(food).searchTerm(str).index(i).servingSizeIndex(foodEntry.getWeightIndex()).source(str2).build());
        }
    }

    public List<MealIngredient> getMealIngredientsFromFoodEntries(List<FoodEntry> list, long j) {
        FoodDBAdapter foodDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter();
        ArrayList arrayList = new ArrayList();
        for (FoodEntry foodEntry : list) {
            MealIngredient mealIngredient = new MealIngredient();
            mealIngredient.setCreatorUserId(((Session) this.session.get()).getUser().getLocalId());
            mealIngredient.setMealFoodId(j);
            Food food = foodEntry.getFood();
            long masterDatabaseId = ((Session) this.session.get()).getUser().getMasterDatabaseId();
            if (!food.isPublic() && food.getOwnerUserMasterId() != masterDatabaseId) {
                food.setMasterDatabaseId(0);
                food.setLocalId(0);
                food.setOwnerUserMasterId(masterDatabaseId);
            }
            Food insertFoodIfMissing = foodDbAdapter.insertFoodIfMissing(food, (DbConnectionManager) this.dbConnectionManager.get());
            mealIngredient.setIngredientFoodId(insertFoodIfMissing.getLocalId());
            mealIngredient.setIngredientFood(insertFoodIfMissing);
            mealIngredient.setFoodPortion(insertFoodIfMissing.foodPortionWithIndex(foodEntry.getWeightIndex()));
            mealIngredient.setQuantity(foodEntry.getQuantity());
            mealIngredient.setWeightIndex(foodEntry.getWeightIndex());
            mealIngredient.setIsFraction(foodEntry.isFraction());
            arrayList.add(mealIngredient);
        }
        return arrayList;
    }

    public void syncAndUpdateIdsIfNeeded(MealFood mealFood) {
        if (mealFood.hasMasterDatabaseId() && mealFood.getFoodPermission().hasMasterId()) {
            return;
        }
        if (((SyncService) this.syncService.get()).enqueueAndWait(SyncType.Incremental).isSuccessful()) {
            ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().setMasterIdAndUidToFood(mealFood);
            ((FoodPermissionsTable) this.foodPermissionsTable.get()).setMasterIdToPermission(mealFood.getFoodPermission());
            return;
        }
        throw new RuntimeException("Unable to perform sync. Throwing exception");
    }
}
