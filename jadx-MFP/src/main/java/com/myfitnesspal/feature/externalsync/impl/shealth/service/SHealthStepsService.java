package com.myfitnesspal.feature.externalsync.impl.shealth.service;

import android.database.Cursor;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthConstants.StepDailyTrend;
import com.myfitnesspal.feature.externalsync.impl.shealth.model.SHealthDailySteps;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.Permission;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.IoUtil;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest.Builder;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadResult;
import com.samsung.android.sdk.healthdata.HealthDataResolver.SortOrder;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class SHealthStepsService extends SHealthServiceBase implements ExternalStepsService {
    private static final Tuple2<Date, Integer> LAST_RECORDED_STEP_COUNT = Tuple.create(null, null);
    private static Set<Permission> READ_PERMISSIONS = new HashSet();
    private static Set<Permission> WRITE_PERMISSIONS = new HashSet();
    private Map<String, Integer> dayToStepCount = new HashMap();
    private final Lazy<DiaryService> diaryService;
    private final Lazy<StepService> stepService;
    private MfpStepSource stepSource;
    private final Lazy<SyncService> syncService;

    /* access modifiers changed from: protected */
    public void syncWrite() {
    }

    static {
        READ_PERMISSIONS.add(Permission.ReadStepsTrend);
        WRITE_PERMISSIONS.add(Permission.ReadSteps);
        WRITE_PERMISSIONS.add(Permission.WriteSteps);
    }

    public SHealthStepsService(SHealthConnection sHealthConnection, Session session, Lazy<SyncService> lazy, Lazy<DiaryService> lazy2, Lazy<StepService> lazy3, Lazy<ConfigService> lazy4, Lazy<AppGalleryService> lazy5) {
        super(sHealthConnection, lazy4, lazy5);
        this.syncService = lazy;
        this.stepService = lazy3;
        this.diaryService = lazy2;
        this.stepSource = SHealthUtil.createStepSource(session);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getStepCountForToday() {
        /*
            com.uacf.core.util.Tuple2<java.util.Date, java.lang.Integer> r0 = LAST_RECORDED_STEP_COUNT
            monitor-enter(r0)
            com.uacf.core.util.Tuple2<java.util.Date, java.lang.Integer> r1 = LAST_RECORDED_STEP_COUNT     // Catch:{ all -> 0x002a }
            java.lang.Object r1 = r1.getItem1()     // Catch:{ all -> 0x002a }
            java.util.Date r1 = (java.util.Date) r1     // Catch:{ all -> 0x002a }
            com.uacf.core.util.Tuple2<java.util.Date, java.lang.Integer> r2 = LAST_RECORDED_STEP_COUNT     // Catch:{ all -> 0x002a }
            java.lang.Object r2 = r2.getItem2()     // Catch:{ all -> 0x002a }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ all -> 0x002a }
            boolean r1 = com.myfitnesspal.shared.util.DateTimeUtils.isDateToday(r1)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0027
            if (r2 == 0) goto L_0x0027
            int r1 = r2.intValue()     // Catch:{ all -> 0x002a }
            if (r1 <= 0) goto L_0x0027
            int r1 = r2.intValue()     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r1
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            r0 = 0
            return r0
        L_0x002a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthStepsService.getStepCountForToday():int");
    }

    /* access modifiers changed from: protected */
    public void syncRead() {
        if (enabled()) {
            boolean z = false;
            if (SHealthUtil.isSHealthStepsSource(((StepService) this.stepService.get()).getPrimaryStepSource())) {
                for (int i = -2; i <= 0; i++) {
                    z |= readTotalStepsForDate(getDateForDayWithOffset(i));
                }
            }
            if (z) {
                ((SyncService) this.syncService.get()).enqueue(SyncType.Incremental);
            }
        }
    }

    private boolean readTotalStepsForDate(Date date) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            long time = date.getTime() + ((long) TimeZone.getDefault().getOffset(date.getTime()));
            ReadResult readResult = (ReadResult) new HealthDataResolver(dataStore, getHandler()).read(new Builder().setDataType(StepDailyTrend.HEALTH_DATA_TYPE).setSort(StepDailyTrend.DAY_TIME, SortOrder.DESC).setFilter(Filter.and(Filter.eq(StepDailyTrend.SOURCE_TYPE, Integer.valueOf(-2)), Filter.eq(StepDailyTrend.DAY_TIME, Long.valueOf(time)))).build()).await();
            if (readResult != null) {
                Cursor resultCursor = readResult.getResultCursor();
                if (resultCursor != null) {
                    try {
                        int columnIndexOrThrow = resultCursor.getColumnIndexOrThrow(StepDailyTrend.DAY_TIME);
                        int columnIndexOrThrow2 = resultCursor.getColumnIndexOrThrow(StepDailyTrend.BINNING_DATA);
                        while (resultCursor.moveToNext()) {
                            if (resultCursor.getLong(columnIndexOrThrow) == time) {
                                return syncStepsForDay(date, resultCursor.getBlob(columnIndexOrThrow2));
                            }
                        }
                        resultCursor.close();
                    } finally {
                        resultCursor.close();
                    }
                }
            }
        }
        return false;
    }

    private boolean syncStepsForDay(Date date, byte[] bArr) {
        SHealthDailySteps fromJson = SHealthDailySteps.fromJson(IoUtil.gunzip(bArr));
        if (fromJson != null) {
            int totalStepCount = fromJson.getTotalStepCount();
            if (needsSync(date, totalStepCount)) {
                try {
                    ((DiaryService) this.diaryService.get()).postSteps(fromJson.toMfpStepsEntryList(date), this.stepSource);
                    update(date, totalStepCount);
                    return true;
                } catch (ApiException e) {
                    Ln.e(e, "failed to sync steps!", new Object[0]);
                }
            }
        }
        return false;
    }

    private boolean needsSync(Date date, int i) {
        Integer num = (Integer) this.dayToStepCount.get(Database.encodeDate(date));
        return num == null || num.intValue() != i;
    }

    private void update(Date date, int i) {
        this.dayToStepCount.put(Database.encodeDate(date), Integer.valueOf(i));
        if (DateTimeUtils.isDateToday(date)) {
            synchronized (LAST_RECORDED_STEP_COUNT) {
                LAST_RECORDED_STEP_COUNT.setItem1(date);
                LAST_RECORDED_STEP_COUNT.setItem2(Integer.valueOf(i));
            }
        }
    }

    /* access modifiers changed from: protected */
    public Set<Permission> getWritePermissions() {
        return WRITE_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    public Set<Permission> getReadPermissions() {
        return READ_PERMISSIONS;
    }
}
