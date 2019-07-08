package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Http;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFood.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.FoodMigrationAndCorrectionHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Provider;

public class FindAndCorrectFoodsWithMissingInfoOp extends UacfScheduleOpBase {
    private static final String ATTRIBUTE_FAIL_COUNT = "fail_count";
    private static final String ATTRIBUTE_INVALID_COUNT = "invalid_count";
    private static final String ATTRIBUTE_SUCCESSFUL_COUNT = "successful_count";
    private static final String EVENT_FOODS_CORRECTED = "foods_corrected";
    public static final String FOOD_IDS_FORMAT = "%s,%s";
    private static final int MAX_EXCEPTION_NUMBER_TO_BAIL = 5;
    private static final int MAX_ITEMS_TO_REQUEST = 20;
    private static final String V2_FOOD_REQUEST_HEADER_ID = "%1s@%2s";
    private final Lazy<AnalyticsService> analyticsService;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<FoodMapper> foodMapper;
    private final Lazy<FoodMigrationAndCorrectionHelper> foodMigrationAndCorrectionHelper;
    private final Provider<MfpV2Api> mfpJsonV2Api;
    private final Lazy<Session> session;

    public FindAndCorrectFoodsWithMissingInfoOp(Provider<MfpV2Api> provider, Lazy<FoodMapper> lazy, Lazy<Session> lazy2, Lazy<FoodMigrationAndCorrectionHelper> lazy3, Lazy<AnalyticsService> lazy4, Lazy<DbConnectionManager> lazy5) {
        this.mfpJsonV2Api = provider;
        this.foodMapper = lazy;
        this.session = lazy2;
        this.foodMigrationAndCorrectionHelper = lazy3;
        this.analyticsService = lazy4;
        this.dbConnectionManager = lazy5;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        ApiResponse apiResponse;
        Ln.d("Initiating FindAndCorrectFoodsWithMissingInfoOp", new Object[0]);
        ((FoodMigrationAndCorrectionHelper) this.foodMigrationAndCorrectionHelper.get()).fixFoodNutritionInfoInDB();
        ((FoodMigrationAndCorrectionHelper) this.foodMigrationAndCorrectionHelper.get()).correctFoodOriginalIdInDB();
        Set migrateAndReturnFoodIdsWithMissingInfo = ((FoodMigrationAndCorrectionHelper) this.foodMigrationAndCorrectionHelper.get()).migrateAndReturnFoodIdsWithMissingInfo();
        if (ConnectivityUtil.isOffline().booleanValue()) {
            Ln.d("FindAndCorrectFoodsWithMissingInfoOp: user is offline. Will retry later", new Object[0]);
            return Result.retry(null);
        } else if (CollectionUtils.isEmpty((Collection<?>) migrateAndReturnFoodIdsWithMissingInfo)) {
            Ln.d("FindAndCorrectFoodsWithMissingInfoOp: no foods to correct", new Object[0]);
            return Result.completed();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("FindAndCorrectFoodsWithMissingInfoOp: number of foods to correct: ");
            sb.append(CollectionUtils.size((Collection<?>) migrateAndReturnFoodIdsWithMissingInfo));
            Ln.d(sb.toString(), new Object[0]);
            int size = migrateAndReturnFoodIdsWithMissingInfo.size();
            int removeInvalidIds = removeInvalidIds(migrateAndReturnFoodIdsWithMissingInfo);
            HashSet hashSet = new HashSet();
            int i = 0;
            while (!CollectionUtils.isEmpty((Collection<?>) migrateAndReturnFoodIdsWithMissingInfo)) {
                if (ConnectivityUtil.isOffline().booleanValue() || i == 5) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("FindAndCorrectFoodsWithMissingInfoOp: Exiting the process because - user is offline? ");
                    sb2.append(ConnectivityUtil.isOffline());
                    sb2.append(" OR reached max exception count? ");
                    sb2.append(i == 5);
                    Ln.d(sb2.toString(), new Object[0]);
                    return Result.retry(null);
                }
                ArrayList arrayList = new ArrayList(migrateAndReturnFoodIdsWithMissingInfo);
                int min = Math.min(CollectionUtils.size((Collection<?>) arrayList), 20);
                HashSet hashSet2 = new HashSet(min);
                String[] strArr = new String[(min * 2)];
                for (int i2 = 0; i2 < min; i2++) {
                    String str = (String) arrayList.get(i2);
                    hashSet2.add(str);
                    String[] splitFoodIdsAndVersion = splitFoodIdsAndVersion(str);
                    int i3 = i2 * 2;
                    strArr[i3] = Http.IDS;
                    strArr[i3 + 1] = String.format(V2_FOOD_REQUEST_HEADER_ID, new Object[]{splitFoodIdsAndVersion[0], splitFoodIdsAndVersion[1]});
                }
                try {
                    apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) this.mfpJsonV2Api.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.FOODS_V2, strArr);
                } catch (ApiException e) {
                    Ln.e(e, "Exception when correcting foods", new Object[0]);
                    i++;
                    apiResponse = null;
                }
                if (apiResponse != null) {
                    migrateAndReturnFoodIdsWithMissingInfo.removeAll(hashSet2);
                    for (MfpFood mapFromMfpFood : apiResponse.getItems()) {
                        Food mapFromMfpFood2 = ((FoodMapper) this.foodMapper.get()).mapFromMfpFood(mapFromMfpFood, ((Session) this.session.get()).getUser());
                        ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().updateFoodInfo(mapFromMfpFood2);
                        hashSet2.remove(String.format(FOOD_IDS_FORMAT, new Object[]{mapFromMfpFood2.getOriginalUid(), mapFromMfpFood2.getUid()}));
                    }
                    hashSet.addAll(hashSet2);
                }
            }
            setDefaultFoodPortionsOfUnavailableFoods(hashSet);
            int size2 = hashSet.size();
            int i4 = (size - size2) - removeInvalidIds;
            ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FOODS_CORRECTED, MapUtil.createMap(ATTRIBUTE_SUCCESSFUL_COUNT, Integer.toString(i4), ATTRIBUTE_FAIL_COUNT, Integer.toString(size2), ATTRIBUTE_INVALID_COUNT, Integer.toString(removeInvalidIds)));
            StringBuilder sb3 = new StringBuilder();
            sb3.append("FindAndCorrectFoodsWithMissingInfoOp: completed process. Success = ");
            sb3.append(i4);
            sb3.append(", Fail = ");
            sb3.append(size2);
            sb3.append(", Invalid = ");
            sb3.append(removeInvalidIds);
            Ln.e(new Throwable(sb3.toString()).fillInStackTrace());
            return Result.completed();
        }
    }

    private void setDefaultFoodPortionsOfUnavailableFoods(Set<String> set) {
        if (!set.isEmpty()) {
            for (String splitFoodIdsAndVersion : set) {
                String[] splitFoodIdsAndVersion2 = splitFoodIdsAndVersion(splitFoodIdsAndVersion);
                long foodIdForOriginalUidAndUid = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().getFoodIdForOriginalUidAndUid(splitFoodIdsAndVersion2[0], splitFoodIdsAndVersion2[1]);
                if (foodIdForOriginalUidAndUid != 0) {
                    ((DbConnectionManager) this.dbConnectionManager.get()).foodPortionsDBAdapter().insertDefaultFoodPortion(foodIdForOriginalUidAndUid);
                }
            }
        }
    }

    private int removeInvalidIds(Set<String> set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            String[] splitFoodIdsAndVersion = splitFoodIdsAndVersion((String) it.next());
            if (splitFoodIdsAndVersion[0] == null || splitFoodIdsAndVersion[1] == null) {
                it.remove();
                i++;
            }
        }
        return i;
    }

    private String[] splitFoodIdsAndVersion(String str) {
        return str.split(",");
    }
}
