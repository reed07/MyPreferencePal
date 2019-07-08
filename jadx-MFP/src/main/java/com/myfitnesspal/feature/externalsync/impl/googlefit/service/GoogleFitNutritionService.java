package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataReadRequest.Builder;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.DateTime;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.GoogleFit;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.SyncScopes;
import com.myfitnesspal.feature.externalsync.impl.googlefit.db.GoogleFitDbAdapter;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitNutritionEntry;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitDateTimeUtils;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.ReportAnalyticsDebouncer;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.shared.constants.Constants.ABTest.GoogleFitNutrition201511;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class GoogleFitNutritionService extends SimpleAsyncServiceBase implements GoogleFitServiceBase, ExternalNutritionService {
    private static final int MAX_THREADS = 1;
    private static final String TAG = "GoogleFitNutritionService";
    private final String applicationPackage;
    private final Lazy<ConfigService> configService;
    private final Lazy<GoogleFitClient> googleFitClient;
    /* access modifiers changed from: private */
    public final GoogleFitDbAdapter googleFitDbAdapter;
    /* access modifiers changed from: private */
    public final ReportAnalyticsDebouncer mealWriteDebouncer;
    private final Lazy<Session> session;
    private final Lazy<SyncService> syncService;

    private int convertMealIdFromGoogleFitId(int i) {
        switch (i) {
            case 0:
                return GoogleFitNutritionEntry.MEAL_TYPE_UNKNOWN;
            case 1:
                return GoogleFitNutritionEntry.MEAL_TYPE_BREAKFAST;
            case 2:
                return GoogleFitNutritionEntry.MEAL_TYPE_LUNCH;
            case 3:
                return GoogleFitNutritionEntry.MEAL_TYPE_DINNER;
            case 4:
                return GoogleFitNutritionEntry.MEAL_TYPE_SNACK;
            default:
                return GoogleFitNutritionEntry.MEAL_TYPE_UNKNOWN;
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    public GoogleFitNutritionService(Context context, Lazy<GoogleFitClient> lazy, Lazy<Session> lazy2, Lazy<ConfigService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<SyncService> lazy5) {
        this.applicationPackage = context.getApplicationContext().getPackageName();
        this.googleFitDbAdapter = new GoogleFitDbAdapter(context);
        this.googleFitClient = lazy;
        this.session = lazy2;
        this.configService = lazy3;
        this.syncService = lazy5;
        this.mealWriteDebouncer = new ReportAnalyticsDebouncer(lazy4, Attributes.MEAL_WRITE);
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public void onFoodEntryInserted(final FoodEntry foodEntry, final String str) {
        if (isGoogleFitEnabled()) {
            auto(new Runnable() {
                public void run() {
                    GoogleFitNutritionService.this.googleFitDbAdapter.insertFitNutrientEntry(GoogleFitNutritionEntry.fromFoodEntry(foodEntry, 0), str);
                    GoogleFitNutritionService.this.mealWriteDebouncer.call(Extras.MYFITNESSPAL_CLIENT_ID);
                    GoogleFitNutritionService.this.enqueueNutrientSync();
                }
            });
        }
    }

    public void onFoodEntryDeleted(final FoodEntry foodEntry, final String str) {
        if (isGoogleFitEnabled()) {
            auto(new Runnable() {
                public void run() {
                    GoogleFitNutritionService.this.googleFitDbAdapter.insertFitNutrientEntry(GoogleFitNutritionEntry.fromFoodEntry(foodEntry, 1), str);
                    GoogleFitNutritionService.this.enqueueNutrientSync();
                }
            });
        }
    }

    public boolean enabled() {
        return ((GoogleFitClient) this.googleFitClient.get()).isEnabledForSync();
    }

    public void sync() {
        if (enabled()) {
            if (((GoogleFitClient) this.googleFitClient.get()).connectAndWait()) {
                SyncScope syncScope = getSyncScope();
                if (syncScope != null && syncScope.getScope().equals(SyncScopes.FIT_NUTRIENT_WRITE)) {
                    syncDataWithFitCloud(((GoogleFitClient) this.googleFitClient.get()).getGoogleApiClient(), ((Session) this.session.get()).getUser().getUserId());
                }
            } else {
                Ln.e("GoogleFitClient.connectAndWait() failed!", new Object[0]);
            }
        }
    }

    private boolean isGoogleFitEnabled() {
        return ((GoogleFitClient) this.googleFitClient.get()).isEnabled();
    }

    private void syncDataWithFitCloud(GoogleApiClient googleApiClient, String str) {
        try {
            TreeMap fetchNutrientEntry = this.googleFitDbAdapter.fetchNutrientEntry(str);
            if (CollectionUtils.notEmpty((Map<?, ?>) fetchNutrientEntry)) {
                long time = GoogleFitDateTimeUtils.getStartOfDay(new Date(((Long) fetchNutrientEntry.firstKey()).longValue())).getTime();
                long time2 = GoogleFitDateTimeUtils.getEndOfDay(new Date(((Long) fetchNutrientEntry.lastKey()).longValue())).getTime();
                Ln.d(TAG, "GoogleFit: GoogleFit: Read Nutrient Data from local table");
                readEntriesFromGoogleFit(googleApiClient, time, time2, str, fetchNutrientEntry);
            }
        } catch (Exception e) {
            Ln.e(TAG, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void enqueueNutrientSync() {
        ((SyncService) this.syncService.get()).enqueue(SyncType.External);
    }

    private SyncScope getSyncScope() {
        if (((ConfigService) this.configService.get()).isVariantEnabled(GoogleFitNutrition201511.NAME, "on")) {
            return new SyncScope(SyncScopes.FIT_NUTRIENT_WRITE);
        }
        return null;
    }

    private DataSet aggregateEntries(Map<Long, List<GoogleFitNutritionEntry>> map, Map<Long, List<GoogleFitNutritionEntry>> map2) {
        if (!CollectionUtils.notEmpty(map)) {
            return sumEntries(map2);
        }
        for (Entry entry : map.entrySet()) {
            long longValue = ((Long) entry.getKey()).longValue();
            List list = (List) entry.getValue();
            if (map2.containsKey(Long.valueOf(longValue)) && CollectionUtils.notEmpty((Collection<?>) list)) {
                ((List) map2.get(Long.valueOf(longValue))).addAll(list);
            } else if (CollectionUtils.notEmpty((Collection<?>) list)) {
                map2.put(Long.valueOf(longValue), list);
            }
        }
        return sumEntries(map2);
    }

    private void readEntriesFromGoogleFit(GoogleApiClient googleApiClient, long j, long j2, String str, Map<Long, List<GoogleFitNutritionEntry>> map) {
        try {
            long j3 = j;
            long j4 = j2;
            GoogleApiClient googleApiClient2 = googleApiClient;
            DataReadResult dataReadResult = (DataReadResult) Fitness.HistoryApi.readData(googleApiClient, new Builder().read(new DataSource.Builder().setAppPackageName(this.applicationPackage).setDataType(DataType.TYPE_NUTRITION).setName(GoogleFit.DATA_SOURCE_NAME).setType(0).build()).read(DataType.TYPE_NUTRITION).setTimeRange(j3, j4, TimeUnit.MILLISECONDS).build()).await(30, AWAIT_TIME_UNIT);
            Ln.d(TAG, dataReadResult);
            if (dataReadResult.getStatus().isSuccess()) {
                onFitDataReadSuccess(googleApiClient, j, j2, str, convertDataSet(dataReadResult.getDataSet(DataType.TYPE_NUTRITION)), map);
            }
        } catch (Exception e) {
            Ln.e(TAG, "GoogleFit: Failed to Read From Google Fit", e);
        }
    }

    private void onFitDataReadSuccess(GoogleApiClient googleApiClient, long j, long j2, String str, Map<Long, List<GoogleFitNutritionEntry>> map, Map<Long, List<GoogleFitNutritionEntry>> map2) {
        updateFitCloud(googleApiClient, j, j2, aggregateEntries(map, map2), str);
    }

    private void updateFitCloud(GoogleApiClient googleApiClient, long j, long j2, DataSet dataSet, String str) {
        DataSet dataSet2 = dataSet;
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("GoogleFit: Update start to google fit: ");
        sb.append(Strings.toString(dataSet));
        Ln.d(str2, sb.toString());
        if (dataSet2 != null) {
            try {
                GoogleApiClient googleApiClient2 = googleApiClient;
                Status status = (Status) Fitness.HistoryApi.updateData(googleApiClient, new DataUpdateRequest.Builder().setDataSet(dataSet2).setTimeInterval(j, j2, TimeUnit.MILLISECONDS).build()).await(30, AWAIT_TIME_UNIT);
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("GoogleFit: finished update to google fit with status: ");
                sb2.append(Strings.toString(status));
                Ln.d(str3, sb2.toString());
                if (status.isSuccess()) {
                    Ln.d(TAG, "GoogleFit: Success update nutrients into cloud.");
                    try {
                        this.googleFitDbAdapter.clearUploadedNutrientData(str);
                    } catch (Exception e) {
                        e = e;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                Ln.e(TAG, "GoogleFit: There was a problem updating nutrients into cloud.", e);
            }
        }
    }

    private TreeMap<Long, List<GoogleFitNutritionEntry>> convertDataSet(DataSet dataSet) {
        Ln.d(TAG, "GoogleFit: start convert date read from google fit");
        TreeMap<Long, List<GoogleFitNutritionEntry>> treeMap = new TreeMap<>();
        if (dataSet == null) {
            return treeMap;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
        for (DataPoint dataPoint : dataSet.getDataPoints()) {
            Value value = dataPoint.getValue(Field.FIELD_NUTRIENTS);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("\tGoogleFit: Start: ");
            sb.append(simpleDateFormat.format(Long.valueOf(dataPoint.getStartTime(TimeUnit.MILLISECONDS))));
            Ln.d(str, sb.toString());
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\tGoogleFit: End: ");
            sb2.append(simpleDateFormat.format(Long.valueOf(dataPoint.getEndTime(TimeUnit.MILLISECONDS))));
            Ln.d(str2, sb2.toString());
            String str3 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("\tGoogleFit: Calories: ");
            sb3.append(value.getKeyValue("calories"));
            Ln.d(str3, sb3.toString());
            HashMap hashMap = new HashMap();
            int convertMealIdFromGoogleFitId = convertMealIdFromGoogleFitId(dataPoint.getValue(Field.FIELD_MEAL_TYPE).asInt());
            long startTime = dataPoint.getStartTime(TimeUnit.MILLISECONDS);
            long endTime = dataPoint.getEndTime(TimeUnit.MILLISECONDS);
            hashMap.put("calories", value.getKeyValue("calories"));
            hashMap.put("fat.total", value.getKeyValue("fat.total"));
            hashMap.put("fat.saturated", value.getKeyValue("fat.saturated"));
            hashMap.put("fat.polyunsaturated", value.getKeyValue("fat.polyunsaturated"));
            hashMap.put("fat.monounsaturated", value.getKeyValue("fat.monounsaturated"));
            hashMap.put("fat.trans", value.getKeyValue("fat.trans"));
            hashMap.put("cholesterol", value.getKeyValue("cholesterol"));
            hashMap.put("sodium", value.getKeyValue("sodium"));
            hashMap.put("potassium", value.getKeyValue("potassium"));
            hashMap.put("carbs.total", value.getKeyValue("carbs.total"));
            hashMap.put("dietary_fiber", value.getKeyValue("dietary_fiber"));
            hashMap.put("sugar", value.getKeyValue("sugar"));
            hashMap.put("protein", value.getKeyValue("protein"));
            hashMap.put("vitamin_c", value.getKeyValue("vitamin_c"));
            hashMap.put("calcium", value.getKeyValue("calcium"));
            hashMap.put("iron", value.getKeyValue("iron"));
            GoogleFitNutritionEntry googleFitNutritionEntry = new GoogleFitNutritionEntry();
            googleFitNutritionEntry.setEntryTime(startTime);
            googleFitNutritionEntry.setStartTime(startTime);
            googleFitNutritionEntry.setEndTime(endTime);
            googleFitNutritionEntry.setMeal(convertMealIdFromGoogleFitId);
            googleFitNutritionEntry.setNutrients(hashMap);
            googleFitNutritionEntry.setStatusFlag(2);
            if (treeMap.containsKey(Long.valueOf(startTime))) {
                List list = (List) treeMap.get(Long.valueOf(startTime));
                if (list != null) {
                    list.add(googleFitNutritionEntry);
                }
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(googleFitNutritionEntry);
                treeMap.put(Long.valueOf(startTime), arrayList);
            }
        }
        Ln.d(TAG, "GoogleFit: finish convert date read from google fit");
        return treeMap;
    }

    private DataSet sumEntries(Map<Long, List<GoogleFitNutritionEntry>> map) {
        float f;
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("GoogleFit: Creating a new nutrients data insert request from: ");
        sb.append(map);
        objArr[0] = sb.toString() != null ? Strings.toString(map.entrySet()) : "";
        Ln.d(str, objArr);
        DataSource build = new DataSource.Builder().setAppPackageName(this.applicationPackage).setDataType(DataType.TYPE_NUTRITION).setName(GoogleFit.DATA_SOURCE_NAME).setType(0).build();
        if (!CollectionUtils.notEmpty(map)) {
            return null;
        }
        DataSet create = DataSet.create(build);
        for (Entry entry : map.entrySet()) {
            long longValue = ((Long) entry.getKey()).longValue();
            List<GoogleFitNutritionEntry> list = (List) entry.getValue();
            if (CollectionUtils.notEmpty((Collection<?>) list)) {
                HashMap hashMap = new HashMap();
                for (GoogleFitNutritionEntry googleFitNutritionEntry : list) {
                    boolean z = googleFitNutritionEntry.getStatusFlag() == 1;
                    googleFitNutritionEntry.setStatusFlag(0);
                    for (Entry entry2 : googleFitNutritionEntry.getNutrients().entrySet()) {
                        if (entry2 != null) {
                            String str2 = (String) entry2.getKey();
                            Float f2 = (Float) entry2.getValue();
                            if (f2 != null) {
                                f = f2.floatValue() * ((float) (z ? -1 : 1));
                            } else {
                                f = BitmapDescriptorFactory.HUE_RED;
                            }
                            hashMap.put(str2, Float.valueOf((hashMap.containsKey(str2) ? ((Float) hashMap.get(str2)).floatValue() : BitmapDescriptorFactory.HUE_RED) + f));
                        }
                    }
                }
                DataPoint timeInterval = create.createDataPoint().setTimeInterval(GoogleFitDateTimeUtils.getStartOfDay(new Date(longValue)).getTime(), GoogleFitDateTimeUtils.getEndOfDay(new Date(longValue)).getTime(), TimeUnit.MILLISECONDS);
                timeInterval.getValue(Field.FIELD_MEAL_TYPE).setInt(0);
                timeInterval.getValue(Field.FIELD_FOOD_ITEM).setString(null);
                if (CollectionUtils.notEmpty((Map<?, ?>) hashMap)) {
                    for (String str3 : hashMap.keySet()) {
                        float floatValue = ((Float) hashMap.get(str3)).floatValue();
                        Value value = timeInterval.getValue(Field.FIELD_NUTRIENTS);
                        String convertNutrientNameToGoogleFitName = convertNutrientNameToGoogleFitName(str3);
                        if (floatValue <= BitmapDescriptorFactory.HUE_RED) {
                            floatValue = BitmapDescriptorFactory.HUE_RED;
                        }
                        value.setKeyValue(convertNutrientNameToGoogleFitName, floatValue);
                    }
                }
                create.add(timeInterval);
            }
        }
        return create;
    }

    private String convertNutrientNameToGoogleFitName(String str) {
        if (str == null) {
            return "";
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1930010315:
                if (str.equals("potassium")) {
                    c = 8;
                    break;
                }
                break;
            case -1762597548:
                if (str.equals("vitamin_a")) {
                    c = 13;
                    break;
                }
                break;
            case -1762597546:
                if (str.equals("vitamin_c")) {
                    c = 14;
                    break;
                }
                break;
            case -1734926706:
                if (str.equals("cholesterol")) {
                    c = 6;
                    break;
                }
                break;
            case -1124148177:
                if (str.equals("fat.total")) {
                    c = 1;
                    break;
                }
                break;
            case -1124076653:
                if (str.equals("fat.trans")) {
                    c = 5;
                    break;
                }
                break;
            case -984531717:
                if (str.equals("fat.polyunsaturated")) {
                    c = 3;
                    break;
                }
                break;
            case -897020359:
                if (str.equals("sodium")) {
                    c = 7;
                    break;
                }
                break;
            case -612488479:
                if (str.equals("dietary_fiber")) {
                    c = 10;
                    break;
                }
                break;
            case -309012605:
                if (str.equals("protein")) {
                    c = 12;
                    break;
                }
                break;
            case -168965370:
                if (str.equals("calories")) {
                    c = 0;
                    break;
                }
                break;
            case 3241160:
                if (str.equals("iron")) {
                    c = 16;
                    break;
                }
                break;
            case 109792566:
                if (str.equals("sugar")) {
                    c = 11;
                    break;
                }
                break;
            case 125042491:
                if (str.equals("carbs.total")) {
                    c = 9;
                    break;
                }
                break;
            case 215325440:
                if (str.equals("fat.saturated")) {
                    c = 2;
                    break;
                }
                break;
            case 548373068:
                if (str.equals("calcium")) {
                    c = 15;
                    break;
                }
                break;
            case 1205114244:
                if (str.equals("fat.monounsaturated")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "calories";
            case 1:
                return "fat.total";
            case 2:
                return "fat.saturated";
            case 3:
                return "fat.polyunsaturated";
            case 4:
                return "fat.monounsaturated";
            case 5:
                return "fat.trans";
            case 6:
                return "cholesterol";
            case 7:
                return "sodium";
            case 8:
                return "potassium";
            case 9:
                return "carbs.total";
            case 10:
                return "dietary_fiber";
            case 11:
                return "sugar";
            case 12:
                return "protein";
            case 13:
                return "vitamin_a";
            case 14:
                return "vitamin_c";
            case 15:
                return "calcium";
            case 16:
                return "iron";
            default:
                return "";
        }
    }
}
