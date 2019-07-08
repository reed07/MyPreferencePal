package com.myfitnesspal.feature.externalsync.impl.shealth.service;

import android.database.Cursor;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthConstants;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthExerciseType;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.Permission;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.Database;
import com.samsung.android.sdk.healthdata.HealthConstants.Exercise;
import com.samsung.android.sdk.healthdata.HealthConstants.SessionMeasurement;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataResolver.InsertRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.InsertRequest.Builder;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadResult;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class SHealthExerciseService extends SHealthServiceBase implements ExternalExerciseService {
    private static final Set<Permission> READ_PERMISSIONS = new HashSet();
    private static final Set<Permission> WRITE_PERMISSIONS = new HashSet();
    private Lazy<DiaryService> diaryService;
    private Lazy<ExerciseStringService> exerciseStringService;
    private Map<String, Boolean> pendingUpdates = new HashMap();

    static {
        READ_PERMISSIONS.add(Permission.ReadExercise);
        WRITE_PERMISSIONS.add(Permission.WriteExercise);
    }

    public SHealthExerciseService(SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<DiaryService> lazy2, Lazy<AppGalleryService> lazy3, Lazy<ExerciseStringService> lazy4) {
        super(sHealthConnection, lazy, lazy3);
        this.diaryService = lazy2;
        this.exerciseStringService = lazy4;
    }

    private boolean needsSync(Date date) {
        Boolean bool = (Boolean) this.pendingUpdates.get(Database.encodeDate(date));
        return bool == null || bool.booleanValue();
    }

    private void markPending(MfpExerciseEntry mfpExerciseEntry) {
        Date date = mfpExerciseEntry.getDate();
        if (isWithinSyncDateRange(date)) {
            this.pendingUpdates.put(Database.encodeDate(date), Boolean.valueOf(true));
        }
    }

    private void markCompleted(Date date) {
        this.pendingUpdates.put(Database.encodeDate(date), Boolean.valueOf(false));
    }

    public void onExerciseEntryDeleted(MfpExerciseEntry mfpExerciseEntry, String str) {
        markPending(mfpExerciseEntry);
    }

    public void onExerciseEntryUpdated(MfpExerciseEntry mfpExerciseEntry, String str) {
        markPending(mfpExerciseEntry);
    }

    public void onExerciseEntryInserted(MfpExerciseEntry mfpExerciseEntry, String str) {
        markPending(mfpExerciseEntry);
    }

    /* access modifiers changed from: protected */
    public void syncRead() {
        if (enabled()) {
            for (int i = -2; i <= 0; i++) {
                final Date dateForDayWithOffset = getDateForDayWithOffset(i);
                DatabaseUtil.ensureDatabaseTransaction(getDatabase(), new Function0() {
                    public void execute() throws RuntimeException {
                        SHealthExerciseService.this.writeSHealthExercisesToMfpOnDate(dateForDayWithOffset);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void syncWrite() {
        if (enabled()) {
            for (int i = -2; i <= 0; i++) {
                Date dateForDayWithOffset = getDateForDayWithOffset(i);
                if (needsSync(dateForDayWithOffset)) {
                    writeMfpExercisesToSHealthOnDate(dateForDayWithOffset);
                    markCompleted(dateForDayWithOffset);
                }
            }
        }
    }

    private void writeMfpExercisesToSHealthOnDate(Date date) {
        removeMfpResourcesFromSHealthOnDate(Exercise.HEALTH_DATA_TYPE, date);
        for (MfpExerciseEntry mfpExerciseEntry : ((DiaryService) this.diaryService.get()).getV2ExerciseEntriesForDate(date)) {
            if (mfpExerciseValidForWrite(mfpExerciseEntry)) {
                try {
                    writeMfpExerciseEntryToSHealth(mfpExerciseEntry);
                } catch (Exception e) {
                    Ln.e(e, "failed to write exercise entry to SHealth", new Object[0]);
                }
            }
        }
    }

    private void removeSHealthResourcesFromMfpOnDate(Date date) {
        for (MfpExerciseEntry mfpExerciseEntry : ((DiaryService) this.diaryService.get()).getExerciseEntriesOnOrAfter(SHealthConstants.getClientId(), SHealthConstants.getAppId(), date.getTime()).values()) {
            if (!mfpExerciseEntry.isCalorieAdjustment().booleanValue()) {
                ((DiaryService) this.diaryService.get()).deleteExerciseEntryLocally(mfpExerciseEntry);
            }
        }
    }

    private boolean mfpExerciseValidForWrite(MfpExerciseEntry mfpExerciseEntry) {
        if (!(mfpExerciseEntry == null || mfpExerciseEntry.getExercise() == null || mfpExerciseEntry.isCalorieAdjustment().booleanValue())) {
            if (!(SHealthConstants.getAppId().equals(mfpExerciseEntry.getAppId()) && SHealthConstants.getClientId().equals(mfpExerciseEntry.getSource()))) {
                return "cardio".equals(mfpExerciseEntry.getExercise().getType());
            }
        }
        return false;
    }

    private void writeMfpExerciseEntryToSHealth(MfpExerciseEntry mfpExerciseEntry) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            InsertRequest build = new Builder().setDataType(Exercise.HEALTH_DATA_TYPE).build();
            Date startTime = mfpExerciseEntry.getStartTime();
            if (startTime == null) {
                startTime = mfpExerciseEntry.getDate();
            }
            long time = startTime.getTime();
            long duration = (long) (mfpExerciseEntry.getDuration() * 1000);
            long j = time + duration;
            float value = mfpExerciseEntry.getEnergy().getValue();
            long offset = (long) TimeZone.getDefault().getOffset(time);
            int uidToSamsungExerciseId = SHealthExerciseType.uidToSamsungExerciseId(mfpExerciseEntry.getExercise().getId());
            HealthData healthData = new HealthData();
            healthData.setSourceDevice(getDeviceUuid());
            healthData.putLong("start_time", time);
            healthData.putLong(SessionMeasurement.END_TIME, j);
            healthData.putLong("time_offset", offset);
            healthData.putFloat("calorie", value);
            healthData.putLong("duration", duration);
            healthData.putLong("exercise_type", (long) uidToSamsungExerciseId);
            if (uidToSamsungExerciseId == 0) {
                healthData.putString(Exercise.EXERCISE_CUSTOM_TYPE, ((ExerciseStringService) this.exerciseStringService.get()).getDescriptionName(mfpExerciseEntry.getExercise()));
            }
            build.addHealthData(healthData);
            if (new HealthDataResolver(dataStore, getHandler()).insert(build).await().getCount() == 0) {
                Ln.e("failed to sync MfpExerciseEntry to SHealth!", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public void writeSHealthExercisesToMfpOnDate(Date date) {
        removeSHealthResourcesFromMfpOnDate(date);
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            ReadResult readResult = (ReadResult) new HealthDataResolver(dataStore, getHandler()).read(new ReadRequest.Builder().setDataType(Exercise.HEALTH_DATA_TYPE).setTimeAfter(date.getTime() - 1).build()).await();
            if (readResult != null) {
                Cursor resultCursor = readResult.getResultCursor();
                if (resultCursor != null) {
                    try {
                        int columnIndexOrThrow = resultCursor.getColumnIndexOrThrow("exercise_type");
                        int columnIndexOrThrow2 = resultCursor.getColumnIndexOrThrow("start_time");
                        int columnIndexOrThrow3 = resultCursor.getColumnIndexOrThrow("duration");
                        int columnIndexOrThrow4 = resultCursor.getColumnIndexOrThrow("pkg_name");
                        int columnIndexOrThrow5 = resultCursor.getColumnIndexOrThrow("calorie");
                        while (resultCursor.moveToNext()) {
                            String string = resultCursor.getString(columnIndexOrThrow4);
                            if (!getPackageName().equals(string)) {
                                if (!isAppConnected(string)) {
                                    String samsungExerciseIdToUid = SHealthExerciseType.samsungExerciseIdToUid(resultCursor.getInt(columnIndexOrThrow));
                                    if (Strings.notEmpty(samsungExerciseIdToUid)) {
                                        MfpExerciseEntry buildExerciseEntryForExternalSync = ((DiaryService) this.diaryService.get()).buildExerciseEntryForExternalSync(samsungExerciseIdToUid, resultCursor.getLong(columnIndexOrThrow2), resultCursor.getLong(columnIndexOrThrow3), resultCursor.getInt(columnIndexOrThrow5), SHealthConstants.getClientId(), SHealthConstants.getAppId());
                                        if (buildExerciseEntryForExternalSync != null) {
                                            ((DiaryService) this.diaryService.get()).createNewExerciseEntryLocally(buildExerciseEntryForExternalSync);
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        resultCursor.close();
                    }
                }
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
