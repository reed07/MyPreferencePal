package com.myfitnesspal.feature.diary.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.diary.model.FriendDiaryRequestData;
import com.myfitnesspal.feature.diary.service.DiaryDayCache.DiaryInfoCacheEntry;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.exception.DuplicateResourceException;
import com.myfitnesspal.shared.api.exception.ResourceValidationException;
import com.myfitnesspal.shared.api.request.DiaryLogRequest;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Flows;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.CompleteDiaryDayResultObject;
import com.myfitnesspal.shared.model.v15.DiaryDaySummaryObject;
import com.myfitnesspal.shared.model.v2.MfpDatabaseObjectV2;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpLogEntry;
import com.myfitnesspal.shared.model.v2.MfpLogEntry.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.model.v2.MfpStepsEntryV2;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.DeletedItemsProvider;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadListExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.CompleteDiaryDayRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveDiaryDayForOtherUserRequestPacket;
import com.myfitnesspal.shared.service.syncv2.SyncV2ServiceBase;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.MultiAddExerciseSelection;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.sync.provider.sdk.model.SyncItem;
import com.uacf.sync.provider.sdk.model.SyncItem.Action;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import org.jetbrains.annotations.NotNull;

public class DiaryServiceImpl extends SyncV2ServiceBase<MfpExerciseEntry> implements DiaryService, DeletedItemsProvider {
    private static final String DELETE_NOT_FOUND = "not-found";
    private static final String FAILED_VALIDATION_EXCEPTION = "failed-validation";
    private static final int MAX_THREADS = 2;
    private static final String TAG = "DiaryServiceImpl";
    private final Lazy<ActionTrackingService> actionTrackingService;
    private final Lazy<AnalyticsService> analyticsService;
    private final Provider<MfpActionApi> api;
    private final Provider<MfpV2Api> apiV2;
    private final Lazy<SQLiteDatabaseWrapper> database;
    private final Lazy<DiaryDayCache> diaryDayCache;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<ExerciseEntryMapper> exerciseEntryMapper;
    private final Lazy<ExerciseService> exerciseService;
    private final Lazy<ExternalExerciseService> externalExerciseService;
    private final Lazy<NutrientGoalService> nutrientGoalService;
    private final Lazy<Session> session;

    /* renamed from: com.myfitnesspal.feature.diary.service.DiaryServiceImpl$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action = new int[Action.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.uacf.sync.provider.sdk.model.SyncItem$Action[] r0 = com.uacf.sync.provider.sdk.model.SyncItem.Action.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action = r0
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Delete     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x001f }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Create     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x002a }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Update     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.AnonymousClass2.<clinit>():void");
        }
    }

    private static class StepsPostRequest {
        @Expose
        String appId;
        @Expose
        String clientId;
        @Expose
        List<MfpStepsEntryV2> items;

        public StepsPostRequest(List<MfpStepsEntryV2> list, MfpStepSource mfpStepSource) {
            this.items = list;
            this.clientId = mfpStepSource.getClientId();
            this.appId = mfpStepSource.getAppId();
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public String getSyncResourceName() {
        return "exercise_entry";
    }

    public DiaryServiceImpl(Provider<MfpActionApi> provider, Provider<MfpV2Api> provider2, Lazy<DiaryDayCache> lazy, Lazy<Session> lazy2, Lazy<ExternalExerciseService> lazy3, Lazy<ActionTrackingService> lazy4, Lazy<AnalyticsService> lazy5, Lazy<SQLiteDatabaseWrapper> lazy6, Lazy<ExerciseEntryMapper> lazy7, Lazy<ExerciseService> lazy8, Lazy<DiaryService> lazy9, Lazy<NutrientGoalService> lazy10) {
        this.api = provider;
        this.apiV2 = provider2;
        this.diaryDayCache = lazy;
        this.session = lazy2;
        this.externalExerciseService = lazy3;
        this.actionTrackingService = lazy4;
        this.analyticsService = lazy5;
        this.database = lazy6;
        this.exerciseEntryMapper = lazy7;
        this.exerciseService = lazy8;
        this.diaryService = lazy9;
        this.nutrientGoalService = lazy10;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public CompleteDiaryDayResultObject completeDiaryDayFor(Date date) throws ApiException {
        return (CompleteDiaryDayResultObject) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new CompleteDiaryDayRequestPacket(date))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(126), new Object[0]);
    }

    public DiaryDay retrieveDiaryDayForOtherUser(FriendDiaryRequestData friendDiaryRequestData) throws ApiException {
        List list = (List) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new RetrieveDiaryDayForOtherUserRequestPacket(friendDiaryRequestData.getUserMasterId(), friendDiaryRequestData.getDate(), friendDiaryRequestData.getDiaryToken()))).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(Arrays.asList(new Integer[]{Integer.valueOf(PacketTypes.DiaryDaySummary), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(16), Integer.valueOf(23)})), new Object[0]);
        DiaryDay diaryDay = new DiaryDay();
        diaryDay.setAllUserMealNames(((DiaryService) this.diaryService.get()).extractMealNamesFromSummaryObject(list));
        diaryDay.initFromListOfObjects(list);
        return diaryDay;
    }

    public List<String> extractMealNamesFromSummaryObject(List<BinaryApiSerializable> list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BinaryApiSerializable binaryApiSerializable = (BinaryApiSerializable) it.next();
            if (binaryApiSerializable instanceof DiaryDaySummaryObject) {
                Map summaryValues = ((DiaryDaySummaryObject) binaryApiSerializable).getSummaryValues();
                int i = 0;
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("meal_");
                    int i2 = i + 1;
                    sb.append(String.valueOf(i));
                    String sb2 = sb.toString();
                    if (!summaryValues.containsKey(sb2)) {
                        break;
                    }
                    arrayList.add(summaryValues.get(sb2));
                    i = i2;
                }
            }
        }
        return arrayList;
    }

    public void logEntriesAsync(DiaryLogRequest diaryLogRequest, Function1<MfpLogEntry> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(diaryLogRequest)).postAsync(Uri.DIARY, (Function1<T>) new Function1() {
            public final void execute(Object obj) {
                FunctionUtils.invokeIfValid(Function1.this, ((ApiResponse) obj).getItem());
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public final void execute(Object obj) {
                DiaryServiceImpl.lambda$logEntriesAsync$1(MfpV2ApiErrorCallback.this, (ApiException) obj);
            }
        }, new Object[0]);
    }

    static /* synthetic */ void lambda$logEntriesAsync$1(MfpV2ApiErrorCallback mfpV2ApiErrorCallback, ApiException apiException) throws RuntimeException {
        Ln.e(apiException);
        FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
    }

    public void postStepsAsync(List<MfpStepsEntryV2> list, MfpStepSource mfpStepSource, Function1<Boolean> function1) {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withOutputType(ApiResponse.API_RESPONSE_MAPPER.class)).withJsonBody(new StepsPostRequest(list, mfpStepSource))).postAsync(Uri.DIARY, (Function1<T>) new Function1() {
            public final void execute(Object obj) {
                FunctionUtils.invokeIfValid(Function1.this, Boolean.valueOf(true));
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public final void execute(Object obj) {
                DiaryServiceImpl.lambda$postStepsAsync$3(Function1.this, (ApiException) obj);
            }
        }, new Object[0]);
    }

    static /* synthetic */ void lambda$postStepsAsync$3(Function1 function1, ApiException apiException) throws RuntimeException {
        Ln.e(apiException);
        FunctionUtils.invokeIfValid(function1, Boolean.valueOf(false));
    }

    public void postSteps(List<MfpStepsEntryV2> list, MfpStepSource mfpStepSource) throws ApiException {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withOutputType(ApiResponse.API_RESPONSE_MAPPER.class)).withJsonBody(new StepsPostRequest(list, mfpStepSource))).post(Uri.DIARY, new Object[0]);
    }

    public MfpExerciseEntry buildExerciseEntryForExternalSync(String str, long j, long j2, String str2) {
        return buildExerciseEntryForExternalSync(str, j, j2, -1, str2, null);
    }

    public MfpExerciseEntry buildExerciseEntryForExternalSync(String str, long j, long j2, int i, String str2, String str3) {
        MfpExercise publicExerciseById = ((ExerciseService) this.exerciseService.get()).getPublicExerciseById(str);
        User user = ((Session) this.session.get()).getUser();
        if (j2 <= 0 || publicExerciseById == null) {
            return null;
        }
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(j2);
        if (i == -1) {
            i = MfpExercise.cardioCaloriesBurnedForHours(publicExerciseById, user.getProfile().getCurrentWeight().getKilograms(), minutes);
        }
        int i2 = 0;
        publicExerciseById.setIsCalorieAdjustment(false);
        publicExerciseById.setDeleted(true);
        MfpExerciseEntry mfpExerciseEntry = new MfpExerciseEntry();
        mfpExerciseEntry.setExercise(publicExerciseById);
        if (MultiAddExerciseSelection.current() != null) {
            i2 = MultiAddExerciseSelection.current().getMinutesPerformedForSelectedExercises();
        }
        int minutesOfCardioExercises = 1440 - (((int) ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().minutesOfCardioExercises()) + i2);
        if (minutes <= minutesOfCardioExercises) {
            minutesOfCardioExercises = minutes;
        }
        mfpExerciseEntry.setDuration(minutesOfCardioExercises * 60);
        mfpExerciseEntry.setEnergy(new MfpMeasuredValue("calories", (float) i));
        mfpExerciseEntry.setDate(new Date(j));
        mfpExerciseEntry.setStartTimeString(DateTimeUtils.formatAsHoursAndMinutes(new Date(j)));
        if (Strings.notEmpty(str2)) {
            mfpExerciseEntry.setSource(str2);
        }
        if (Strings.notEmpty(str3)) {
            mfpExerciseEntry.setAppId(str3);
        }
        return mfpExerciseEntry;
    }

    public ExerciseEntry getV1ExerciseEntry(long j) {
        return mapV2ToV1(getExerciseEntryLocally(j));
    }

    /* JADX INFO: finally extract failed */
    public List<MfpExerciseEntry> getV2ExerciseEntriesForDate(Date date) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).rawQuery("SELECT * FROM exercise_entries WHERE user_id = ? AND entry_date = ? AND sync_flags != 3", Long.valueOf(((Session) this.session.get()).getUser().getLocalId()), Database.encodeDate(date));
            while (rawQuery.moveToNext()) {
                arrayList.add(new MfpExerciseEntry(new CursorMapper(rawQuery)));
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public List<ExerciseEntry> getV1ExerciseEntriesForDate(Date date) {
        List<MfpExerciseEntry> v2ExerciseEntriesForDate = getV2ExerciseEntriesForDate(date);
        ArrayList arrayList = new ArrayList();
        for (MfpExerciseEntry reverseMap : v2ExerciseEntriesForDate) {
            arrayList.add(((ExerciseEntryMapper) this.exerciseEntryMapper.get()).reverseMap(reverseMap));
        }
        return arrayList;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v2.MfpExerciseEntry getExerciseEntryLocally(long r6) {
        /*
            r5 = this;
            r0 = 0
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r1 = new com.myfitnesspal.shared.db.table.ExerciseEntriesTable     // Catch:{ all -> 0x0037 }
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r2 = r5.database     // Catch:{ all -> 0x0037 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0037 }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = (com.uacf.core.database.SQLiteDatabaseWrapper) r2     // Catch:{ all -> 0x0037 }
            r1.<init>(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "SELECT * FROM exercise_entries WHERE id = ?"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0037 }
            r4 = 0
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0037 }
            r3[r4] = r6     // Catch:{ all -> 0x0037 }
            android.database.Cursor r6 = r1.rawQuery(r2, r3)     // Catch:{ all -> 0x0037 }
            boolean r7 = r6.moveToFirst()     // Catch:{ all -> 0x0034 }
            if (r7 == 0) goto L_0x002e
            com.uacf.core.database.CursorMapper r7 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0034 }
            r7.<init>(r6)     // Catch:{ all -> 0x0034 }
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r0 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x0034 }
            r0.<init>(r7)     // Catch:{ all -> 0x0034 }
        L_0x002e:
            if (r6 == 0) goto L_0x0033
            r6.close()
        L_0x0033:
            return r0
        L_0x0034:
            r7 = move-exception
            r0 = r6
            goto L_0x0038
        L_0x0037:
            r7 = move-exception
        L_0x0038:
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.getExerciseEntryLocally(long):com.myfitnesspal.shared.model.v2.MfpExerciseEntry");
    }

    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r5v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r12v5, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a5  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v2.MfpExerciseEntry getLatestExerciseEntryForExercise(java.lang.String r12, long r13) {
        /*
            r11 = this;
            boolean r0 = com.uacf.core.util.Strings.notEmpty(r12)
            r1 = 1
            r2 = 0
            r3 = 0
            int r5 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x000e
            r3 = 1
            goto L_0x000f
        L_0x000e:
            r3 = 0
        L_0x000f:
            if (r0 == 0) goto L_0x0015
            if (r3 == 0) goto L_0x0015
            r4 = 1
            goto L_0x0016
        L_0x0015:
            r4 = 0
        L_0x0016:
            r5 = 0
            if (r0 != 0) goto L_0x001c
            if (r3 != 0) goto L_0x001c
            return r5
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            java.lang.String r6 = "exercise_version = ?"
            goto L_0x0023
        L_0x0021:
            java.lang.String r6 = ""
        L_0x0023:
            if (r3 == 0) goto L_0x0028
            java.lang.String r7 = "exercise_master_id = ?"
            goto L_0x002a
        L_0x0028:
            java.lang.String r7 = ""
        L_0x002a:
            java.lang.String r8 = "SELECT * FROM exercise_entries WHERE user_id = ? AND (%s%s%s) ORDER BY id DESC"
            r9 = 3
            java.lang.Object[] r10 = new java.lang.Object[r9]
            if (r0 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            java.lang.String r6 = ""
        L_0x0034:
            r10[r2] = r6
            if (r4 == 0) goto L_0x003b
            java.lang.String r6 = " OR "
            goto L_0x003d
        L_0x003b:
            java.lang.String r6 = ""
        L_0x003d:
            r10[r1] = r6
            if (r3 == 0) goto L_0x0042
            goto L_0x0044
        L_0x0042:
            java.lang.String r7 = ""
        L_0x0044:
            r3 = 2
            r10[r3] = r7
            java.lang.String r6 = java.lang.String.format(r8, r10)
            if (r4 == 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r9 = 2
        L_0x004f:
            java.lang.Object[] r7 = new java.lang.Object[r9]
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r8 = r11.session
            java.lang.Object r8 = r8.get()
            com.myfitnesspal.shared.service.session.Session r8 = (com.myfitnesspal.shared.service.session.Session) r8
            com.myfitnesspal.shared.model.User r8 = r8.getUser()
            long r8 = r8.getLocalId()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r7[r2] = r8
            if (r0 == 0) goto L_0x006a
            goto L_0x006e
        L_0x006a:
            java.lang.Long r12 = java.lang.Long.valueOf(r13)
        L_0x006e:
            r7[r1] = r12
            if (r4 == 0) goto L_0x0078
            java.lang.Long r12 = java.lang.Long.valueOf(r13)
            r7[r3] = r12
        L_0x0078:
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r12 = new com.myfitnesspal.shared.db.table.ExerciseEntriesTable     // Catch:{ all -> 0x00a2 }
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r13 = r11.database     // Catch:{ all -> 0x00a2 }
            java.lang.Object r13 = r13.get()     // Catch:{ all -> 0x00a2 }
            com.uacf.core.database.SQLiteDatabaseWrapper r13 = (com.uacf.core.database.SQLiteDatabaseWrapper) r13     // Catch:{ all -> 0x00a2 }
            r12.<init>(r13)     // Catch:{ all -> 0x00a2 }
            android.database.Cursor r12 = r12.rawQuery(r6, r7)     // Catch:{ all -> 0x00a2 }
            boolean r13 = r12.moveToFirst()     // Catch:{ all -> 0x009f }
            if (r13 == 0) goto L_0x0099
            com.uacf.core.database.CursorMapper r13 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x009f }
            r13.<init>(r12)     // Catch:{ all -> 0x009f }
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r5 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x009f }
            r5.<init>(r13)     // Catch:{ all -> 0x009f }
        L_0x0099:
            if (r12 == 0) goto L_0x009e
            r12.close()
        L_0x009e:
            return r5
        L_0x009f:
            r13 = move-exception
            r5 = r12
            goto L_0x00a3
        L_0x00a2:
            r13 = move-exception
        L_0x00a3:
            if (r5 == 0) goto L_0x00a8
            r5.close()
        L_0x00a8:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.getLatestExerciseEntryForExercise(java.lang.String, long):com.myfitnesspal.shared.model.v2.MfpExerciseEntry");
    }

    public ExerciseEntry createNewV1ExerciseEntryLocally(ExerciseEntry exerciseEntry) {
        return mapV2ToV1(createNewExerciseEntryLocally(mapV1ToV2(exerciseEntry)));
    }

    public MfpExerciseEntry createNewExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry) {
        MfpExerciseEntry mfpExerciseEntry2 = (MfpExerciseEntry) ParcelableUtil.clone(mfpExerciseEntry, MfpExerciseEntry.CREATOR);
        mfpExerciseEntry2.setMasterId(0);
        mfpExerciseEntry2.setId(null);
        mfpExerciseEntry2.setSyncFlags(1);
        return insertOrUpdateExerciseEntryLocally(mfpExerciseEntry2);
    }

    public MfpExerciseEntry insertOrUpdateExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry) {
        if (!NumberUtils.getBooleanValue(mfpExerciseEntry.isCalorieAdjustment()) || deleteAnyCalorieAdjustmentExercisesOlderThanAdjustmentForEntry(mfpExerciseEntry)) {
            long masterId = mfpExerciseEntry.getMasterId();
            int i = (masterId > 0 ? 1 : (masterId == 0 ? 0 : -1));
            ContentValues contentValues = new ContentValuesMapper().clear().exclude("id").exclude("master_id", i == 0).put(mfpExerciseEntry).put("user_id", Long.valueOf(((Session) this.session.get()).getUser().getLocalId())).get();
            String uid = ((Session) this.session.get()).getUser().getUid();
            ExerciseEntriesTable exerciseEntriesTable = new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get());
            String id = mfpExerciseEntry.getId();
            if (Strings.notEmpty(id)) {
                if (exerciseEntriesTable.updateData(contentValues, "uid = ?", id) > 0) {
                    if (writeToExternalService()) {
                        ((ExternalExerciseService) this.externalExerciseService.get()).onExerciseEntryUpdated(mfpExerciseEntry, uid);
                    }
                    return mfpExerciseEntry;
                }
            }
            if (i > 0) {
                if (exerciseEntriesTable.updateData(contentValues, "master_id = ?", Long.valueOf(masterId)) > 0) {
                    if (writeToExternalService()) {
                        ((ExternalExerciseService) this.externalExerciseService.get()).onExerciseEntryUpdated(mfpExerciseEntry, uid);
                    }
                    return mfpExerciseEntry;
                }
            }
            mfpExerciseEntry.setLocalId(exerciseEntriesTable.insertData(contentValues));
            if (writeToExternalService() && !hasExerciseAlreadyStored(mfpExerciseEntry)) {
                ((ExternalExerciseService) this.externalExerciseService.get()).onExerciseEntryInserted(mfpExerciseEntry, uid);
            }
            return mfpExerciseEntry;
        }
        Ln.d("SYNCV2: calorie adjustment exercise should NOT be inserted", new Object[0]);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00ad, code lost:
        if (r1 == null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00b0, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x009f, code lost:
        if (r1 != null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x00a1, code lost:
        r1.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasExerciseAlreadyStored(@org.jetbrains.annotations.NotNull com.myfitnesspal.shared.model.v2.MfpExerciseEntry r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            java.lang.String r2 = "SELECT * FROM exercise_entries WHERE exercise_version=? AND "
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.<init>()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = "entry_date=? AND "
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.<init>()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = "start_time=? AND "
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.<init>()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = "calories=? AND "
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.<init>()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = "duration_in_seconds=?"
            r3.append(r2)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r3 = new com.myfitnesspal.shared.db.table.ExerciseEntriesTable     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r4 = r8.database     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.Object r4 = r4.get()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            com.uacf.core.database.SQLiteDatabaseWrapper r4 = (com.uacf.core.database.SQLiteDatabaseWrapper) r4     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r4 = 5
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            com.myfitnesspal.shared.model.v2.MfpExercise r5 = r9.getExercise()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r5 = r5.getVersion()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r4[r0] = r5     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.util.Date r5 = r9.getDate()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r5 = com.myfitnesspal.shared.util.Database.encodeDate(r5)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r6 = 1
            r4[r6] = r5     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r5 = 2
            java.util.Date r7 = r9.getStartTime()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.String r7 = com.myfitnesspal.shared.util.Database.encodeTime(r7)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r4[r5] = r7     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r5 = 3
            com.myfitnesspal.shared.model.v2.MfpMeasuredValue r7 = r9.getEnergy()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            float r7 = r7.getValue()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r4[r5] = r7     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r5 = 4
            int r9 = r9.getDuration()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            r4[r5] = r9     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            android.database.Cursor r1 = r3.rawQuery(r2, r4)     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            if (r1 == 0) goto L_0x009f
            int r9 = r1.getCount()     // Catch:{ Exception -> 0x00ac, all -> 0x00a5 }
            if (r9 <= 0) goto L_0x009f
            r0 = 1
        L_0x009f:
            if (r1 == 0) goto L_0x00b0
        L_0x00a1:
            r1.close()
            goto L_0x00b0
        L_0x00a5:
            r9 = move-exception
            if (r1 == 0) goto L_0x00ab
            r1.close()
        L_0x00ab:
            throw r9
        L_0x00ac:
            if (r1 == 0) goto L_0x00b0
            goto L_0x00a1
        L_0x00b0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.hasExerciseAlreadyStored(com.myfitnesspal.shared.model.v2.MfpExerciseEntry):boolean");
    }

    public ExerciseEntry insertOrUpdateV1ExerciseEntryLocally(ExerciseEntry exerciseEntry) {
        return mapV2ToV1(insertOrUpdateExerciseEntryLocally(mapV1ToV2(exerciseEntry)));
    }

    public ExerciseEntry updateV1ExerciseEntryLocally(ExerciseEntry exerciseEntry) {
        return mapV2ToV1(updateExerciseEntryLocally(mapV1ToV2(exerciseEntry)));
    }

    public MfpExerciseEntry updateExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry) {
        deleteExerciseEntryLocally(mfpExerciseEntry);
        return createNewExerciseEntryLocally(mfpExerciseEntry);
    }

    public ExerciseEntry createNewExerciseEntryFromExisting(ExerciseEntry exerciseEntry, Date date) {
        Exercise exercise;
        boolean z;
        ExerciseEntry exerciseEntry2 = new ExerciseEntry();
        exerciseEntry2.setUid(null);
        exerciseEntry2.setDate(date);
        exerciseEntry2.setLocalId(0);
        exerciseEntry2.setMasterDatabaseId(0);
        exerciseEntry2.setQuantity(exerciseEntry.getQuantity());
        exerciseEntry2.setSets(exerciseEntry.getSets());
        exerciseEntry2.setWeight(exerciseEntry.getWeight());
        exerciseEntry2.setCalories(exerciseEntry.getCalories());
        Exercise exercise2 = exerciseEntry.getExercise();
        if (exercise2.isPrivateItemOfAnotherUser(((Session) this.session.get()).getUser().getMasterDatabaseId())) {
            Exercise nonDeletedExerciseV1WithDescription = ((ExerciseService) this.exerciseService.get()).getNonDeletedExerciseV1WithDescription(exercise2.getDescription());
            if (nonDeletedExerciseV1WithDescription == null) {
                z = true;
                exercise = (Exercise) ParcelableUtil.clone(exercise2, Exercise.CREATOR);
                exercise.setLocalId(0);
                exercise.setMasterDatabaseId(0);
                exercise.setUid(null);
                exercise.setOriginalUid(null);
                exercise.setMets(exerciseEntry2.calculateMetsForUser(((Session) this.session.get()).getUser()));
            } else {
                exercise = nonDeletedExerciseV1WithDescription;
                z = false;
            }
        } else {
            Exercise exercise3 = (Exercise) ParcelableUtil.clone(exercise2, Exercise.CREATOR);
            if (exercise2.isPublic()) {
                exercise3.setOwnerUserId(0);
                exercise3.setOwnerUserMasterId(0);
            }
            exercise = exercise3;
            z = false;
        }
        if (z) {
            try {
                exercise = ((ExerciseService) this.exerciseService.get()).createNewV1ExerciseLocallyIfMissing(exercise);
            } catch (DuplicateResourceException e) {
                Ln.e(e);
                exercise = null;
            }
        }
        if (exercise == null) {
            return null;
        }
        exerciseEntry2.setExercise(exercise);
        if (((Session) this.session.get()).getUser().getProfile().getIsLinkedWithFitbit()) {
            exerciseEntry2.setExtraPropertyNamed("start_time", exerciseEntry.startTime());
            if (exerciseEntry2.startTime().equals("")) {
                exerciseEntry2.setExtraPropertyNamed("start_time", DateTimeUtils.formatAsHoursAndMinutes(Calendar.getInstance().get(11), 0));
            }
        }
        return createNewV1ExerciseEntryLocally(exerciseEntry2);
    }

    public boolean deleteExerciseEntryLocally(ExerciseEntry exerciseEntry) {
        return deleteExerciseEntryLocally(mapV1ToV2(exerciseEntry));
    }

    public boolean deleteExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry) {
        long localId = mfpExerciseEntry.getLocalId();
        String userId = ((Session) this.session.get()).getUser().getUserId();
        boolean z = false;
        if ((mfpExerciseEntry.hasId() && mfpExerciseEntry.getSyncFlags() == 0) || mfpExerciseEntry.hasMasterId()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(3));
            int updateDataInExerciseEntriesTable = updateDataInExerciseEntriesTable(localId, contentValues);
            if (updateDataInExerciseEntriesTable > 0 && writeToExternalService()) {
                ((ExternalExerciseService) this.externalExerciseService.get()).onExerciseEntryDeleted(mfpExerciseEntry, userId);
            }
            if (updateDataInExerciseEntriesTable > 0) {
                z = true;
            }
            return z;
        } else if (localId > 0) {
            Ln.d("SYNCV2: public deleteExerciseEntryLocally local id = %s", Long.valueOf(mfpExerciseEntry.getLocalId()));
            int deleteData = new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).deleteData("id = ?", Long.valueOf(localId));
            if (deleteData > 0 && writeToExternalService()) {
                ((ExternalExerciseService) this.externalExerciseService.get()).onExerciseEntryDeleted(mfpExerciseEntry, userId);
            }
            if (deleteData > 0) {
                z = true;
            }
            return z;
        } else {
            boolean deleteExerciseEntryLocally = deleteExerciseEntryLocally(mfpExerciseEntry.getId());
            if (deleteExerciseEntryLocally && writeToExternalService()) {
                ((ExternalExerciseService) this.externalExerciseService.get()).onExerciseEntryDeleted(mfpExerciseEntry, userId);
            }
            return deleteExerciseEntryLocally;
        }
    }

    private boolean writeToExternalService() {
        return ((Session) this.session.get()).getUser().isLoggedIn() && ((ExternalExerciseService) this.externalExerciseService.get()).enabled();
    }

    private boolean deleteExerciseEntryLocally(String str) {
        boolean z = false;
        if (Strings.isEmpty(str)) {
            return false;
        }
        if (new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).deleteData("uid = ?", str) > 0) {
            z = true;
        }
        return z;
    }

    public int eraseAllEntriesForExerciseId(long j) {
        return new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).deleteData("exercise_id = ?", Long.valueOf(j));
    }

    public List<MfpDatabaseObjectV2> getDeletedItems(String str) {
        ArrayList arrayList = new ArrayList();
        if (((str.hashCode() == -1171412149 && str.equals("exercise_entry")) ? (char) 0 : 65535) == 0) {
            arrayList.addAll(getDeletedExerciseEntries());
        }
        return arrayList;
    }

    public void clearDeletedItems(String str) {
        if (((str.hashCode() == -1171412149 && str.equals("exercise_entry")) ? (char) 0 : 65535) == 0) {
            new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).deleteData("user_id = ? AND sync_flags = 3", Long.valueOf(((Session) this.session.get()).getUser().getLocalId()));
        }
    }

    private void setEntrySyncFlag(MfpExerciseEntry mfpExerciseEntry, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(i));
        updateDataInExerciseEntriesTable(mfpExerciseEntry.getLocalId(), contentValues);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 66
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void publishUnsyncedItems(com.uacf.core.util.Function2<java.lang.Integer, java.lang.Integer> r8) throws com.uacf.sync.engine.UacfScheduleException {
        /*
            r7 = this;
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r0 = 0
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r1 = new com.myfitnesspal.shared.db.table.ExerciseEntriesTable     // Catch:{ all -> 0x00af }
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r2 = r7.database     // Catch:{ all -> 0x00af }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x00af }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = (com.uacf.core.database.SQLiteDatabaseWrapper) r2     // Catch:{ all -> 0x00af }
            r1.<init>(r2)     // Catch:{ all -> 0x00af }
            java.lang.String r2 = "user_id = ? AND sync_flags != 0"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00af }
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r5 = r7.session     // Catch:{ all -> 0x00af }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x00af }
            com.myfitnesspal.shared.service.session.Session r5 = (com.myfitnesspal.shared.service.session.Session) r5     // Catch:{ all -> 0x00af }
            com.myfitnesspal.shared.model.User r5 = r5.getUser()     // Catch:{ all -> 0x00af }
            long r5 = r5.getLocalId()     // Catch:{ all -> 0x00af }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x00af }
            r6 = 0
            r4[r6] = r5     // Catch:{ all -> 0x00af }
            android.database.Cursor r0 = r1.queryData(r0, r2, r4)     // Catch:{ all -> 0x00af }
        L_0x0034:
            boolean r1 = r0.moveToNext()     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0046
            long r1 = r0.getLong(r6)     // Catch:{ all -> 0x00af }
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r1 = r7.getExerciseEntryLocally(r1)     // Catch:{ all -> 0x00af }
            r8.add(r1)     // Catch:{ all -> 0x00af }
            goto L_0x0034
        L_0x0046:
            if (r0 == 0) goto L_0x004b
            r0.close()
        L_0x004b:
            java.util.Iterator r8 = r8.iterator()
        L_0x004f:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00ae
            java.lang.Object r0 = r8.next()
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r0 = (com.myfitnesspal.shared.model.v2.MfpExerciseEntry) r0
            int r1 = r0.getSyncFlags()     // Catch:{ DuplicateResourceException -> 0x0089, ResourceValidationException -> 0x0076, ApiException -> 0x006f }
            switch(r1) {
                case 1: goto L_0x006b;
                case 2: goto L_0x0067;
                case 3: goto L_0x0063;
                default: goto L_0x0062;
            }     // Catch:{ DuplicateResourceException -> 0x0089, ResourceValidationException -> 0x0076, ApiException -> 0x006f }
        L_0x0062:
            goto L_0x009a
        L_0x0063:
            r7.deleteExerciseEntryRemotely(r0)     // Catch:{ DuplicateResourceException -> 0x0089, ResourceValidationException -> 0x0076, ApiException -> 0x006f }
            goto L_0x009a
        L_0x0067:
            r7.updateExerciseEntryRemotely(r0)     // Catch:{ DuplicateResourceException -> 0x0089, ResourceValidationException -> 0x0076, ApiException -> 0x006f }
            goto L_0x009a
        L_0x006b:
            r7.createNewExerciseEntryRemotely(r0)     // Catch:{ DuplicateResourceException -> 0x0089, ResourceValidationException -> 0x0076, ApiException -> 0x006f }
            goto L_0x009a
        L_0x006f:
            r8 = move-exception
            com.myfitnesspal.shared.service.syncv2.SyncExceptions$ApiSyncException r0 = new com.myfitnesspal.shared.service.syncv2.SyncExceptions$ApiSyncException
            r0.<init>(r8)
            throw r0
        L_0x0076:
            java.lang.String r1 = "SYNCV2: publishUnsyncedItems validation failed = %s"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.String r4 = r0.getId()
            r2[r6] = r4
            com.uacf.core.util.Ln.d(r1, r2)
            r1 = 3
            r7.setEntrySyncFlag(r0, r1)
            r1 = 0
            goto L_0x009b
        L_0x0089:
            r1 = move-exception
            java.lang.String r2 = "SYNCV2: publishUnsyncedItems duplicate entry id = %s"
            java.lang.Object[] r4 = new java.lang.Object[r3]
            java.lang.String r5 = r0.getId()
            r4[r6] = r5
            com.uacf.core.util.Ln.d(r2, r4)
            com.uacf.core.util.Ln.e(r1)
        L_0x009a:
            r1 = 1
        L_0x009b:
            if (r1 == 0) goto L_0x004f
            java.lang.String r1 = "SYNCV2: publishUnsyncedItems mark as synced entry id = %s"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.String r4 = r0.getId()
            r2[r6] = r4
            com.uacf.core.util.Ln.d(r1, r2)
            r7.setEntrySyncFlag(r0, r6)
            goto L_0x004f
        L_0x00ae:
            return
        L_0x00af:
            r8 = move-exception
            if (r0 == 0) goto L_0x00b5
            r0.close()
        L_0x00b5:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.publishUnsyncedItems(com.uacf.core.util.Function2):void");
    }

    public MfpExerciseEntry createNewExerciseEntryRemotely(MfpExerciseEntry mfpExerciseEntry) throws ApiException {
        MfpExerciseEntry sanitizedExerciseEntryForPost = getSanitizedExerciseEntryForPost(mfpExerciseEntry);
        assignIdIfNecessary(sanitizedExerciseEntryForPost);
        try {
            return (MfpExerciseEntry) Enumerable.firstOrDefault(((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withJsonBody(new ApiRequest(Collections.singletonList(sanitizedExerciseEntryForPost)))).withOutputType(MfpExerciseEntry.API_RESPONSE_MAPPER.class)).post(Uri.DIARY_EXERCISES, new Object[0])).getItems());
        } catch (ApiException e) {
            if (FAILED_VALIDATION_EXCEPTION.equals(e.getApiError().getError())) {
                throw new ResourceValidationException(e, e.getStatusCode());
            }
            throw e;
        }
    }

    public void updateExerciseEntryRemotely(MfpExerciseEntry mfpExerciseEntry) throws ApiException {
        MfpExerciseEntry sanitizedExerciseEntryForPost = getSanitizedExerciseEntryForPost(mfpExerciseEntry);
        assignIdIfNecessary(sanitizedExerciseEntryForPost);
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withJsonBody(new ApiRequest(sanitizedExerciseEntryForPost))).withOutputType(ApiResponseBase.class)).put(String.format(Uri.DIARY_SINGLE_EXERCISE, new Object[]{mfpExerciseEntry.getId()}));
    }

    public void deleteExerciseEntryRemotely(MfpExerciseEntry mfpExerciseEntry) throws ApiException {
        try {
            ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withOutputType(ApiResponseBase.class)).delete(String.format(Uri.DIARY_SINGLE_EXERCISE, new Object[]{mfpExerciseEntry.getId()}));
        } catch (ApiException e) {
            if (!DELETE_NOT_FOUND.equals(e.getApiError().getError())) {
                throw e;
            }
        }
        deleteExerciseEntryLocally(mfpExerciseEntry);
    }

    public List<MfpExerciseEntry> getFavoriteExercisesOfType(int i, int i2, @NotNull SortOrder sortOrder) {
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        int i3 = i;
        int i4 = i2;
        List<Tuple2> favoriteExercises = getFavoriteExercises(localId, i3, i4, ExerciseEntriesTable.Columns.EXERCISE_VERSION, "exercise_version NOT NULL");
        favoriteExercises.addAll(getFavoriteExercises(localId, i3, i4, ExerciseEntriesTable.Columns.EXERCISE_MASTER_ID, "exercise_version IS NULL AND exercise_master_id > 0"));
        Collections.sort(favoriteExercises, new Comparator() {
            public final int compare(Object obj, Object obj2) {
                return DiaryServiceImpl.lambda$getFavoriteExercisesOfType$4(SortOrder.this, (Tuple2) obj, (Tuple2) obj2);
            }
        });
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Tuple2 item1 : favoriteExercises) {
            MfpExerciseEntry mfpExerciseEntry = (MfpExerciseEntry) item1.getItem1();
            MfpExercise exercise = mfpExerciseEntry.getExercise();
            long masterId = exercise.getMasterId();
            if (masterId > 0) {
                if (!hashSet.contains(Long.valueOf(masterId))) {
                    hashSet.add(Long.valueOf(masterId));
                }
            }
            String version = exercise.getVersion();
            if (Strings.notEmpty(version)) {
                if (!hashSet2.contains(version)) {
                    hashSet2.add(version);
                }
            }
            arrayList.add(mfpExerciseEntry);
        }
        return arrayList;
    }

    static /* synthetic */ int lambda$getFavoriteExercisesOfType$4(@NotNull SortOrder sortOrder, Tuple2 tuple2, Tuple2 tuple22) {
        MfpExerciseEntry mfpExerciseEntry = (MfpExerciseEntry) tuple2.getItem1();
        MfpExerciseEntry mfpExerciseEntry2 = (MfpExerciseEntry) tuple22.getItem1();
        if (sortOrder == SortOrder.RECENTLY_USED) {
            int i = -DateTimeUtils.YMD_COMPARATOR.compare(mfpExerciseEntry.getDate(), mfpExerciseEntry2.getDate());
            if (i == 0) {
                i = -Integer.compare(((Integer) tuple2.getItem2()).intValue(), ((Integer) tuple22.getItem2()).intValue());
            }
            if (i != 0) {
                return i;
            }
        }
        int i2 = sortOrder == SortOrder.ALPHABETICAL_DESCENDING ? -1 : 1;
        MfpExercise exercise = mfpExerciseEntry.getExercise();
        MfpExercise exercise2 = mfpExerciseEntry2.getExercise();
        if (exercise == exercise2) {
            return 0;
        }
        if (exercise == null) {
            return i2;
        }
        return exercise2 == null ? -i2 : i2 * Strings.toString(exercise.getDescription()).compareToIgnoreCase(Strings.toString(exercise2.getDescription()));
    }

    /* JADX INFO: finally extract failed */
    private List<Tuple2<MfpExerciseEntry, Integer>> getFavoriteExercises(long j, int i, int i2, String str, String str2) {
        Cursor cursor = null;
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT *, COUNT(*) as frequency FROM exercise_entries WHERE user_id = ? AND exercise_type = ? AND ");
            sb.append(str2);
            sb.append(" GROUP BY ");
            sb.append(str);
            sb.append(" ORDER BY entry_date DESC, frequency DESC LIMIT ?");
            String sb2 = sb.toString();
            Cursor rawQuery = new ExercisesTable((SQLiteDatabaseWrapper) this.database.get()).rawQuery(sb2, Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2));
            while (rawQuery.moveToNext()) {
                MfpExerciseEntry mfpExerciseEntry = new MfpExerciseEntry(new CursorMapper(rawQuery));
                if (!NumberUtils.getBooleanValue(mfpExerciseEntry.isCalorieAdjustment())) {
                    arrayList.add(Tuple.create(mfpExerciseEntry, Integer.valueOf(CursorUtils.getInt(rawQuery, "frequency"))));
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void startLoggingFlow(String str) {
        ((ActionTrackingService) this.actionTrackingService.get()).registerEvent(Flows.LOGGING, MapUtil.createMap("channel", Strings.toString(str), "start_time", Strings.toString(Long.valueOf(System.currentTimeMillis()))));
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("channel", "referrer", str);
    }

    public void startLoggingFlowIfNecessary(String str) {
        if (NumberUtils.tryParseLong(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent(Flows.LOGGING, "start_time")) == 0) {
            startLoggingFlow(str);
        }
    }

    public void endFoodLoggingFlow(Map<String, String> map) {
        String str;
        Map onFoodOrExerciseLogged = onFoodOrExerciseLogged();
        if (CollectionUtils.notEmpty(map)) {
            onFoodOrExerciseLogged.putAll(map);
        }
        String strings = Strings.toString(onFoodOrExerciseLogged.get("flow_id"));
        if (Strings.isEmpty(strings)) {
            strings = UUID.randomUUID().toString();
        }
        Builder put = new Builder().put("list_type", Strings.toString(onFoodOrExerciseLogged.get("list_type"))).put("meal", Strings.toString(onFoodOrExerciseLogged.get("meal"))).put("locale", Strings.toString(onFoodOrExerciseLogged.get("locale"))).put("channel", Strings.toString(onFoodOrExerciseLogged.get("channel"))).put("flow_id", Strings.toString(strings)).put("foods", Strings.toString(onFoodOrExerciseLogged.get("foods"))).put(Attributes.ITEM_COUNT, Strings.toString(Integer.valueOf(NumberUtils.tryParseInt((String) onFoodOrExerciseLogged.get(Attributes.ITEM_COUNT), 1)))).put(Attributes.TIME_TO_LOG, Strings.toString(Float.valueOf(((float) NumberUtils.tryParseLong((String) onFoodOrExerciseLogged.get("duration"), -1)) / 1000.0f))).put("version", Strings.toString(onFoodOrExerciseLogged.get("version")));
        String str2 = Attributes.DIARY_DATE;
        if (onFoodOrExerciseLogged.containsKey(Attributes.DIARY_DATE)) {
            str = (String) onFoodOrExerciseLogged.get(Attributes.DIARY_DATE);
        } else {
            str = DateTimeUtils.diaryDateAnalyticsFormat(((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getDate());
        }
        Builder put2 = put.put(str2, str);
        if (onFoodOrExerciseLogged.containsKey(Attributes.MEAL_COUNT)) {
            put2.put(Attributes.MEAL_COUNT, onFoodOrExerciseLogged.get(Attributes.MEAL_COUNT));
        }
        if (onFoodOrExerciseLogged.containsKey(Attributes.RECIPE_COUNT)) {
            put2.put(Attributes.RECIPE_COUNT, onFoodOrExerciseLogged.get(Attributes.RECIPE_COUNT));
        }
        if (onFoodOrExerciseLogged.containsKey(Attributes.CONTAINS_FOOD_AD)) {
            put2.put(Attributes.CONTAINS_FOOD_AD, onFoodOrExerciseLogged.get(Attributes.CONTAINS_FOOD_AD));
        }
        if (onFoodOrExerciseLogged.containsKey(TimestampAnalyticsHelper.ATTR_TIME)) {
            put2.put(TimestampAnalyticsHelper.ATTR_TIME, onFoodOrExerciseLogged.get(TimestampAnalyticsHelper.ATTR_TIME));
        }
        put2.put(Attributes.CORRECTED, onFoodOrExerciseLogged.get(Attributes.CORRECTED));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.FOOD_LOGGED, put2.build());
        ((ActionTrackingService) this.actionTrackingService.get()).deleteEvent(Flows.LOGGING);
    }

    public void endExerciseLoggingFlow(String str, int i) {
        Map onFoodOrExerciseLogged = onFoodOrExerciseLogged();
        ((AnalyticsService) this.analyticsService.get()).reportExerciseLogged(Strings.toString(str), NumberUtils.tryParseInt((String) onFoodOrExerciseLogged.get("index"), -1), Strings.toString(onFoodOrExerciseLogged.get("list_type")), NumberUtils.tryParseInt((String) onFoodOrExerciseLogged.get(Attributes.ITEM_COUNT), 1), Strings.toString(onFoodOrExerciseLogged.get("channel")), i);
        ((ActionTrackingService) this.actionTrackingService.get()).deleteEvent(Flows.LOGGING);
    }

    public Class<? extends Parcelable> getSyncItemClass() {
        return MfpExerciseEntry.class;
    }

    public void consumeSyncItems(List<SyncItem<MfpExerciseEntry>> list) {
        Enumerable.forEach((Collection<T>) list, (Function1<T>) new Function1<SyncItem<MfpExerciseEntry>>() {
            public void execute(SyncItem<MfpExerciseEntry> syncItem) {
                MfpExerciseEntry mfpExerciseEntry = (MfpExerciseEntry) syncItem.getItem();
                Action action = syncItem.getAction();
                if (action != null) {
                    switch (AnonymousClass2.$SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action[action.ordinal()]) {
                        case 1:
                            DiaryServiceImpl.this.eraseEntry(mfpExerciseEntry);
                            break;
                        case 2:
                        case 3:
                            DiaryServiceImpl.this.insertOrUpdateExerciseEntryLocally(mfpExerciseEntry);
                            break;
                    }
                }
            }
        });
    }

    private Map<String, String> onFoodOrExerciseLogged() {
        long currentTimeMillis = System.currentTimeMillis();
        Ln.d("TTL: DiaryService onFoodOrExerciseLogged, endTime = %s", Long.valueOf(currentTimeMillis));
        Map<String, String> trackingEvents = ((ActionTrackingService) this.actionTrackingService.get()).getTrackingEvents(Flows.LOGGING);
        if (trackingEvents == null) {
            return new HashMap();
        }
        long tryParseLong = NumberUtils.tryParseLong((String) trackingEvents.get("start_time"));
        long j = currentTimeMillis - tryParseLong;
        trackingEvents.put("duration", Strings.toString(Long.valueOf(j)));
        Ln.d("TTL: DiaryService onFoodOrExerciseLogged, startTime = %s, duration = %s", Long.valueOf(tryParseLong), Long.valueOf(j));
        if (Strings.isEmpty((String) trackingEvents.get("channel"))) {
            String trackingDataForEvent = ((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("channel", "referrer");
            Ln.d("TTL: DiaryService onFoodOrExerciseLogged, channel = %s", trackingDataForEvent);
            trackingEvents.put("channel", Strings.toString(trackingDataForEvent));
        }
        return trackingEvents;
    }

    public void markDiaryDayCacheStale() {
        ((DiaryDayCache) this.diaryDayCache.get()).markAllEntriesAsStale();
    }

    public void markDiaryDayCacheEntryStaleForDate(Date date) {
        ((DiaryDayCache) this.diaryDayCache.get()).markDiaryDayCacheEntryStaleForDate(date);
    }

    public DiaryDay getDiaryDayForActiveDateSync() {
        return getDiaryDayForDateSync(((Session) this.session.get()).getUser().getActiveDate());
    }

    public DiaryDay getDiaryDayForDateSync(Date date) {
        return getDiaryDayCacheEntry(date).getDiaryDay();
    }

    public void getDiaryDayForDate(Date date, Function1<DiaryDay> function1) {
        async(new Runnable(function1, date) {
            private final /* synthetic */ Function1 f$1;
            private final /* synthetic */ Date f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                DiaryServiceImpl.this.invokeOnMainThread(this.f$1, DiaryServiceImpl.this.getDiaryDayCacheEntry(this.f$2).getDiaryDay());
            }
        });
    }

    public void clearDiaryDayCache() {
        ((DiaryDayCache) this.diaryDayCache.get()).clearCache();
    }

    public void clearCachedInsights() {
        ((DiaryDayCache) this.diaryDayCache.get()).clearCachedInsights();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.myfitnesspal.feature.diary.service.DiaryDayCache.DiaryInfoCacheEntry getDiaryDayCacheEntry(java.util.Date r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            dagger.Lazy<com.myfitnesspal.feature.diary.service.DiaryDayCache> r0 = r8.diaryDayCache     // Catch:{ all -> 0x0054 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0054 }
            com.myfitnesspal.feature.diary.service.DiaryDayCache r0 = (com.myfitnesspal.feature.diary.service.DiaryDayCache) r0     // Catch:{ all -> 0x0054 }
            com.myfitnesspal.feature.diary.service.DiaryDayCache$DiaryInfoCacheEntry r1 = r0.getDiaryDayCacheEntry(r9)     // Catch:{ all -> 0x0054 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0013
            r4 = 1
            goto L_0x0014
        L_0x0013:
            r4 = 0
        L_0x0014:
            if (r1 == 0) goto L_0x0019
            r1.markDataAsSameAsBefore()     // Catch:{ all -> 0x0054 }
        L_0x0019:
            if (r1 == 0) goto L_0x0026
            if (r1 == 0) goto L_0x0024
            boolean r5 = r1.isValid()     // Catch:{ all -> 0x0054 }
            if (r5 != 0) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r5 = 0
            goto L_0x0027
        L_0x0026:
            r5 = 1
        L_0x0027:
            java.lang.String r6 = "getDiaryDayCacheEntry: date=%s, cacheHit=%s, needsDatabaseFetch=%s"
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0054 }
            r7[r3] = r9     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0054 }
            r7[r2] = r3     // Catch:{ all -> 0x0054 }
            r2 = 2
            java.lang.String r3 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0054 }
            r7[r2] = r3     // Catch:{ all -> 0x0054 }
            com.uacf.core.util.Ln.d(r6, r7)     // Catch:{ all -> 0x0054 }
            if (r5 == 0) goto L_0x0052
            com.myfitnesspal.shared.model.v1.DiaryDay r1 = r8.fetchDiaryDayData(r9)     // Catch:{ all -> 0x0054 }
            com.myfitnesspal.shared.model.v2.MfpNutrientGoal r2 = r8.fetchNutrientGoalData(r9)     // Catch:{ all -> 0x0054 }
            com.myfitnesspal.feature.diary.service.DiaryDayCache$DiaryInfoCacheEntry r1 = r0.addDiaryDayCacheEntry(r9, r1, r2)     // Catch:{ all -> 0x0054 }
            r1.markAsValid()     // Catch:{ all -> 0x0054 }
            r1.markDataAsUpdated()     // Catch:{ all -> 0x0054 }
        L_0x0052:
            monitor-exit(r8)
            return r1
        L_0x0054:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.getDiaryDayCacheEntry(java.util.Date):com.myfitnesspal.feature.diary.service.DiaryDayCache$DiaryInfoCacheEntry");
    }

    public Map<String, DiaryInfoCacheEntry> getCachedDiaryDays() {
        return ((DiaryDayCache) this.diaryDayCache.get()).getCachedDiaryDays();
    }

    private DiaryDay fetchDiaryDayData(Date date) {
        return new DiaryDay().initFromDatabaseForDate(date);
    }

    private MfpNutrientGoal fetchNutrientGoalData(Date date) {
        try {
            return new MfpNutrientGoal().clone(((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(date));
        } catch (Exception unused) {
            return null;
        }
    }

    private MfpExerciseEntry getSanitizedExerciseEntryForPost(MfpExerciseEntry mfpExerciseEntry) {
        MfpExerciseEntry mfpExerciseEntry2 = (MfpExerciseEntry) ParcelableUtil.clone(mfpExerciseEntry, MfpExerciseEntry.CREATOR);
        mfpExerciseEntry2.setCreatedAt(null);
        mfpExerciseEntry2.setContents(null);
        mfpExerciseEntry2.setIsCalorieAdjustment(null);
        MfpExercise exercise = mfpExerciseEntry2.getExercise();
        exercise.setIsPublic(null);
        exercise.setDescription(null);
        exercise.setMets(null);
        exercise.setMetsDouble(null);
        exercise.setType(null);
        return mfpExerciseEntry2;
    }

    private int updateDataInExerciseEntriesTable(long j, ContentValues contentValues) {
        return new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).updateData(contentValues, "id = ?", Long.valueOf(j));
    }

    private ExerciseEntry mapV2ToV1(MfpExerciseEntry mfpExerciseEntry) {
        if (mfpExerciseEntry != null) {
            return (ExerciseEntry) ((ExerciseEntryMapper) this.exerciseEntryMapper.get()).reverseMap(mfpExerciseEntry);
        }
        return null;
    }

    private MfpExerciseEntry mapV1ToV2(ExerciseEntry exerciseEntry) {
        if (exerciseEntry != null) {
            return (MfpExerciseEntry) ((ExerciseEntryMapper) this.exerciseEntryMapper.get()).tryMapFrom(exerciseEntry);
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    private List<MfpDatabaseObjectV2> getDeletedExerciseEntries() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = ((SQLiteDatabaseWrapper) this.database.get()).rawQuery("SELECT * FROM exercise_entries WHERE user_id = ? AND sync_flags = 3", new String[]{Strings.toString(Long.valueOf(((Session) this.session.get()).getUser().getLocalId()))});
            while (rawQuery.moveToNext()) {
                arrayList.add(new MfpExerciseEntry(new CursorMapper(rawQuery)));
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void assignIdIfNecessary(MfpExerciseEntry mfpExerciseEntry) {
        if (Strings.isEmpty(mfpExerciseEntry.getId())) {
            mfpExerciseEntry.assignExternalId();
            updateDataInExerciseEntriesTable(mfpExerciseEntry.getLocalId(), new ContentValuesMapper().put("uid", mfpExerciseEntry.getId()).get());
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 61
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean deleteAnyCalorieAdjustmentExercisesOlderThanAdjustmentForEntry(com.myfitnesspal.shared.model.v2.MfpExerciseEntry r10) {
        /*
            r9 = this;
            java.lang.Boolean r0 = r10.isCalorieAdjustment()
            boolean r0 = r0.booleanValue()
            r1 = 1
            if (r0 != 0) goto L_0x000c
            return r1
        L_0x000c:
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForCalorieAdjustment r0 = r10.getCalorieAdjustmentData()
            if (r0 != 0) goto L_0x0013
            return r1
        L_0x0013:
            com.myfitnesspal.shared.model.date.MfpIso8601Date r0 = r0.getProjectionTimestamp()
            if (r0 != 0) goto L_0x001a
            return r1
        L_0x001a:
            r2 = 0
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r3 = new com.myfitnesspal.shared.db.table.ExerciseEntriesTable     // Catch:{ all -> 0x00a3 }
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r4 = r9.database     // Catch:{ all -> 0x00a3 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x00a3 }
            com.uacf.core.database.SQLiteDatabaseWrapper r4 = (com.uacf.core.database.SQLiteDatabaseWrapper) r4     // Catch:{ all -> 0x00a3 }
            r3.<init>(r4)     // Catch:{ all -> 0x00a3 }
            java.lang.String r4 = " AND "
            java.lang.String r5 = "is_calorie_adjustment = 1"
            java.lang.String r6 = "entry_date = ?"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}     // Catch:{ all -> 0x00a3 }
            java.lang.String r4 = com.uacf.core.util.Strings.join(r4, (T[]) r5)     // Catch:{ all -> 0x00a3 }
            java.util.Date r10 = r10.getDate()     // Catch:{ all -> 0x00a3 }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ all -> 0x00a3 }
            java.lang.String r10 = com.myfitnesspal.shared.util.Database.encodeDate(r10)     // Catch:{ all -> 0x00a3 }
            r6 = 0
            r5[r6] = r10     // Catch:{ all -> 0x00a3 }
            android.database.Cursor r2 = r3.queryData(r2, r4, r5)     // Catch:{ all -> 0x00a3 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x00a3 }
            r10.<init>()     // Catch:{ all -> 0x00a3 }
            com.uacf.core.database.CursorMapper r4 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x00a3 }
            r4.<init>(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0051:
            boolean r5 = r4.moveToNext()     // Catch:{ all -> 0x00a3 }
            if (r5 == 0) goto L_0x007f
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r5 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x00a3 }
            r5.<init>(r4)     // Catch:{ all -> 0x00a3 }
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForCalorieAdjustment r7 = r5.getCalorieAdjustmentData()     // Catch:{ all -> 0x00a3 }
            if (r7 != 0) goto L_0x0063
            goto L_0x0051
        L_0x0063:
            com.myfitnesspal.shared.model.date.MfpIso8601Date r7 = r7.getProjectionTimestamp()     // Catch:{ all -> 0x00a3 }
            if (r7 == 0) goto L_0x0051
            int r7 = r7.compareTo(r0)     // Catch:{ all -> 0x00a3 }
            if (r7 >= 0) goto L_0x007b
            long r7 = r5.getLocalId()     // Catch:{ all -> 0x00a3 }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00a3 }
            r10.add(r5)     // Catch:{ all -> 0x00a3 }
            goto L_0x0051
        L_0x007b:
            if (r7 <= 0) goto L_0x0051
            r1 = 0
            goto L_0x0051
        L_0x007f:
            boolean r0 = com.uacf.core.util.CollectionUtils.notEmpty(r10)     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x009d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r4 = "id IN "
            r0.append(r4)     // Catch:{ all -> 0x00a3 }
            java.lang.String r10 = com.uacf.core.database.DatabaseUtil.getArgsForList(r10)     // Catch:{ all -> 0x00a3 }
            r0.append(r10)     // Catch:{ all -> 0x00a3 }
            java.lang.String r10 = r0.toString()     // Catch:{ all -> 0x00a3 }
            r3.deleteData(r10)     // Catch:{ all -> 0x00a3 }
        L_0x009d:
            if (r2 == 0) goto L_0x00a2
            r2.close()
        L_0x00a2:
            return r1
        L_0x00a3:
            r10 = move-exception
            if (r2 == 0) goto L_0x00a9
            r2.close()
        L_0x00a9:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.deleteAnyCalorieAdjustmentExercisesOlderThanAdjustmentForEntry(com.myfitnesspal.shared.model.v2.MfpExerciseEntry):boolean");
    }

    /* access modifiers changed from: private */
    public void eraseEntry(MfpExerciseEntry mfpExerciseEntry) {
        if (mfpExerciseEntry != null && !Strings.isEmpty(mfpExerciseEntry.getId())) {
            new ExerciseEntriesTable((SQLiteDatabaseWrapper) this.database.get()).deleteData("user_id= ? AND uid= ?", Long.valueOf(((Session) this.session.get()).getUser().getLocalId()), mfpExerciseEntry.getId());
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.util.Map<java.lang.String, com.myfitnesspal.shared.model.v2.MfpExerciseEntry>, android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v0, types: [java.util.Map<java.lang.String, com.myfitnesspal.shared.model.v2.MfpExerciseEntry>, android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [java.util.Map<java.lang.String, com.myfitnesspal.shared.model.v2.MfpExerciseEntry>, ?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor]
  mth insns count: 76
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, com.myfitnesspal.shared.model.v2.MfpExerciseEntry> getExerciseEntriesOnOrAfter(java.lang.String r17, java.lang.String r18, long r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r19
            r4 = 0
            r5 = 0
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f3
            boolean r0 = com.uacf.core.util.Strings.isEmpty(r17)
            if (r0 == 0) goto L_0x0013
            goto L_0x00f3
        L_0x0013:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r5 = "user_id = ? AND source = ? AND entry_date >= ? "
            boolean r6 = com.uacf.core.util.Strings.notEmpty(r18)
            r7 = 2
            r8 = 1
            r9 = 0
            r10 = 3
            if (r6 == 0) goto L_0x0064
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = " AND app_id = ? "
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r6 = 4
            java.lang.String[] r6 = new java.lang.String[r6]
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r11 = r1.session
            java.lang.Object r11 = r11.get()
            com.myfitnesspal.shared.service.session.Session r11 = (com.myfitnesspal.shared.service.session.Session) r11
            com.myfitnesspal.shared.model.User r11 = r11.getUser()
            long r11 = r11.getLocalId()
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            java.lang.String r11 = com.uacf.core.util.Strings.toString(r11)
            r6[r9] = r11
            r6[r8] = r17
            java.util.Date r8 = new java.util.Date
            r8.<init>(r2)
            java.lang.String r2 = com.myfitnesspal.shared.util.Database.encodeDate(r8)
            r6[r7] = r2
            r6[r10] = r18
            r11 = r5
            goto L_0x008e
        L_0x0064:
            java.lang.String[] r6 = new java.lang.String[r10]
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r10 = r1.session
            java.lang.Object r10 = r10.get()
            com.myfitnesspal.shared.service.session.Session r10 = (com.myfitnesspal.shared.service.session.Session) r10
            com.myfitnesspal.shared.model.User r10 = r10.getUser()
            long r10 = r10.getLocalId()
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)
            r6[r9] = r10
            r6[r8] = r17
            java.util.Date r8 = new java.util.Date
            r8.<init>(r2)
            java.lang.String r2 = com.myfitnesspal.shared.util.Database.encodeDate(r8)
            r6[r7] = r2
            r11 = r5
        L_0x008e:
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r2 = r1.database     // Catch:{ all -> 0x00ec }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x00ec }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = (com.uacf.core.database.SQLiteDatabaseWrapper) r2     // Catch:{ all -> 0x00ec }
            r8 = 0
            java.lang.String r9 = "exercise_entries"
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            java.lang.String r3 = android.database.sqlite.SQLiteQueryBuilder.buildQueryString(r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x00ec }
            android.database.Cursor r4 = r2.rawQuery(r3, r6)     // Catch:{ all -> 0x00ec }
        L_0x00a6:
            boolean r2 = r4.moveToNext()     // Catch:{ all -> 0x00ec }
            if (r2 == 0) goto L_0x00e6
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r2 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x00ec }
            com.uacf.core.database.CursorMapper r3 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x00ec }
            r3.<init>(r4)     // Catch:{ all -> 0x00ec }
            r2.<init>(r3)     // Catch:{ all -> 0x00ec }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ec }
            r3.<init>()     // Catch:{ all -> 0x00ec }
            com.myfitnesspal.shared.model.v2.MfpExercise r5 = r2.getExercise()     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = r5.getId()     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)     // Catch:{ all -> 0x00ec }
            r3.append(r5)     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = ":"
            r3.append(r5)     // Catch:{ all -> 0x00ec }
            java.util.Date r5 = r2.getStartTime()     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = com.myfitnesspal.shared.util.Database.encodeDateAndTime(r5)     // Catch:{ all -> 0x00ec }
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)     // Catch:{ all -> 0x00ec }
            r3.append(r5)     // Catch:{ all -> 0x00ec }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ec }
            r0.put(r3, r2)     // Catch:{ all -> 0x00ec }
            goto L_0x00a6
        L_0x00e6:
            if (r4 == 0) goto L_0x00eb
            r4.close()
        L_0x00eb:
            return r0
        L_0x00ec:
            r0 = move-exception
            if (r4 == 0) goto L_0x00f2
            r4.close()
        L_0x00f2:
            throw r0
        L_0x00f3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.service.DiaryServiceImpl.getExerciseEntriesOnOrAfter(java.lang.String, java.lang.String, long):java.util.Map");
    }
}
