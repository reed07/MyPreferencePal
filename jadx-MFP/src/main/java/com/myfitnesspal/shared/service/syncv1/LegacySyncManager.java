package com.myfitnesspal.shared.service.syncv1;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpServiceImpl;
import com.myfitnesspal.feature.registration.util.PasswordResetHelper;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.v1.MfpSyncApi;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.UserPropertiesDBAdapter;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DatabaseObjectReference;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v1.TrackedNutrient;
import com.myfitnesspal.shared.model.v1.UserImage;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v1.UserV1NutrientGoals;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.model.v15.SyncPointer;
import com.myfitnesspal.shared.model.v2.MfpDatabaseObjectV2;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncExceptions.PasswordResetRequiredException;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import dagger.Lazy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

@Deprecated
public class LegacySyncManager {
    private static final int CONNECTION_TIME_OUT = 30000;
    private static final int MAX_PACKETS_PER_SYNC = 2000;
    private static final int READ_TIME_OUT = 120000;
    final Lazy<AnalyticsService> analyticsService;
    final Lazy<ApiUrlProvider> apiUrlProvider;
    final Context context;
    final Lazy<SQLiteDatabaseWrapper> database;
    final Lazy<DbConnectionManager> dbConnectionManager;
    private int deletedItemIdCutoff;
    final Lazy<DiaryService> diaryService;
    final Lazy<ExerciseMapper> exerciseMapper;
    final Lazy<ExerciseService> exerciseService;
    final Lazy<ExerciseStringService> exerciseStringService;
    final Lazy<FoodPermissionsService> foodPermissionsService;
    private boolean hasPostedNotification;
    private List<SyncPointer> lastSyncPointers;
    final Lazy<LocalSettingsService> localSettingsService;
    final Lazy<LoginModel> loginModel;
    final Lazy<MeasurementsService> measurementsService;
    private SyncMode mode;
    final Lazy<PasswordResetHelper> passwordResetHelper;
    private ProgressHandler progressHandler = new DefaultProgressHandler();
    final Lazy<RemindersService> remindersService;
    private Collection<String> resourcesToSyncWithV2;
    /* access modifiers changed from: private */
    public byte[] response;
    final Lazy<Session> session;
    final Lazy<SignUpModel> signUpModel;
    final Provider<MfpSyncApi> syncApiProvider;
    final Lazy<SyncPointerService> syncPointerService;
    private SynchronizationRequest syncRequest;
    /* access modifiers changed from: private */
    public SynchronizationResponse syncResponse;
    final Lazy<SyncUtil> syncUtil;
    final Lazy<UserImageService> userImageService;

    private static final class DefaultProgressHandler implements ProgressHandler {
        public void onProgress(String str) {
        }

        private DefaultProgressHandler() {
        }
    }

    public interface ProgressHandler {
        void onProgress(String str);
    }

    public enum ResultCode {
        Finished,
        HasMoreData,
        Failed
    }

    public enum SyncMode {
        Import,
        Normal,
        BackgroundNormal,
        Registration
    }

    /* access modifiers changed from: 0000 */
    public int syncRequestFlags() {
        return 4;
    }

    public LegacySyncManager(Context context2, Lazy<ApiUrlProvider> lazy, Lazy<AnalyticsService> lazy2, Lazy<PasswordResetHelper> lazy3, Lazy<SyncPointerService> lazy4, Provider<MfpSyncApi> provider, Lazy<Session> lazy5, Lazy<SyncUtil> lazy6, Lazy<DiaryService> lazy7, Lazy<RemindersService> lazy8, Lazy<UserImageService> lazy9, Lazy<ExerciseService> lazy10, Lazy<SQLiteDatabaseWrapper> lazy11, Lazy<ExerciseStringService> lazy12, Lazy<MeasurementsService> lazy13, Lazy<ExerciseMapper> lazy14, Lazy<LocalSettingsService> lazy15, Lazy<LoginModel> lazy16, Lazy<SignUpModel> lazy17, Lazy<FoodPermissionsService> lazy18, Lazy<DbConnectionManager> lazy19) {
        this.context = context2;
        this.apiUrlProvider = lazy;
        this.analyticsService = lazy2;
        this.passwordResetHelper = lazy3;
        this.syncPointerService = lazy4;
        this.syncApiProvider = provider;
        this.session = lazy5;
        this.syncUtil = lazy6;
        this.diaryService = lazy7;
        this.remindersService = lazy8;
        this.userImageService = lazy9;
        this.exerciseService = lazy10;
        this.database = lazy11;
        this.exerciseStringService = lazy12;
        this.measurementsService = lazy13;
        this.exerciseMapper = lazy14;
        this.localSettingsService = lazy15;
        this.loginModel = lazy16;
        this.signUpModel = lazy17;
        this.foodPermissionsService = lazy18;
        this.dbConnectionManager = lazy19;
    }

    public synchronized ResultCode sync(SyncMode syncMode, UserV1 userV1) throws ApiException, UacfScheduleException {
        if (userV1 != null) {
        } else {
            throw new IllegalArgumentException("user cannot be null!");
        }
        return startSyncInternal(userV1, syncMode);
    }

    public int getResponseStatusCode() {
        SynchronizationResponse synchronizationResponse = this.syncResponse;
        if (synchronizationResponse != null) {
            return synchronizationResponse.getStatusCode();
        }
        return 557;
    }

    public String getResponseStatusMessage() {
        SynchronizationResponse synchronizationResponse = this.syncResponse;
        return synchronizationResponse != null ? synchronizationResponse.getErrorMessage() : "";
    }

    public void setProgressHandler(ProgressHandler progressHandler2) {
        if (progressHandler2 == null) {
            progressHandler2 = new DefaultProgressHandler();
        }
        this.progressHandler = progressHandler2;
    }

    /* access modifiers changed from: 0000 */
    public SyncMode getMode() {
        return this.mode;
    }

    /* access modifiers changed from: 0000 */
    public void setLastSyncPointers(List<SyncPointer> list) {
        this.lastSyncPointers = list;
    }

    /* access modifiers changed from: 0000 */
    public void postProgress() {
        this.progressHandler.onProgress(getProgressStatusText());
    }

    private ResultCode startSyncInternal(UserV1 userV1, SyncMode syncMode) throws ApiException, UacfScheduleException {
        UacfScheduleException uacfScheduleException;
        ResultCode resultCode = ResultCode.Failed;
        UacfScheduleException uacfScheduleException2 = null;
        this.resourcesToSyncWithV2 = null;
        this.mode = syncMode;
        try {
            ((SyncUtil) this.syncUtil.get()).migrateDataForSyncV2();
        } catch (Exception e) {
            try {
                Ln.e(e);
            } catch (Exception e2) {
                uacfScheduleException = new UacfScheduleException(e2);
                throw uacfScheduleException;
            } catch (Throwable th) {
                th = th;
                uacfScheduleException2 = uacfScheduleException;
            }
        }
        this.deletedItemIdCutoff = 0;
        this.hasPostedNotification = false;
        Ln.d("startSyncInternal() entered", new Object[0]);
        BinaryEncoder.startEncoding();
        buildRequest(userV1);
        buildResponse(userV1);
        BinaryEncoder.finishEncoding();
        if (((long) this.syncRequest.getEncoder().getBuffer().capacity()) != this.syncRequest.getRequestLengthInBytes()) {
            Ln.d("startSyncInternal() buffer capacity %s does not match the sync request length %s; bail", Integer.valueOf(this.syncRequest.getEncoder().getBuffer().capacity()), Long.valueOf(this.syncRequest.getRequestLengthInBytes()));
        } else {
            Ln.d("startSyncInternal() makeRequestAndProcessResult", new Object[0]);
            makeRequestAndProcessResult(this.syncRequest.getEncoder().getBuffer());
            resultCode = this.syncResponse.moreDataToSync().booleanValue() ? ResultCode.HasMoreData : ResultCode.Finished;
        }
        if (!finalizeSyncAndRaiseErrorIfNecessary(userV1)) {
            resultCode = ResultCode.Failed;
        }
        Ln.d("sync() returning with result=%s", resultCode);
        return resultCode;
        if (uacfScheduleException2 == null) {
            if (!finalizeSyncAndRaiseErrorIfNecessary(userV1)) {
                ResultCode resultCode2 = ResultCode.Failed;
            }
        } else if (!finalizeSync(userV1)) {
            ResultCode resultCode3 = ResultCode.Failed;
        }
        throw th;
    }

    private void makeRequestAndProcessResult(ByteBuffer byteBuffer) throws ApiException {
        Ln.d("makeRequestAndProcessResult() urlString = %s", ((ApiUrlProvider) this.apiUrlProvider.get()).getSyncUrl());
        Ln.d("makeRequestAndProcessResult() buffer has %s bytes", Integer.valueOf(byteBuffer.capacity()));
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Ln.d("makeRequestAndProcessResult() making backend request", new Object[0]);
            this.response = (byte[]) ((MfpSyncApi) ((MfpSyncApi) ((MfpSyncApi) this.syncApiProvider.get()).withBinaryData(byteBuffer)).withTimeouts(30000, READ_TIME_OUT)).post(new Object[0]);
            Ln.d("makeRequestAndProcessResult() received sync response (%s bytes) %s milliseconds after sending request", Integer.valueOf(this.response.length), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            onRequestFinished();
        } catch (ApiException e) {
            if (this.mode == SyncMode.Registration) {
                SignUpServiceImpl.reportFailureExceptions("LegacySyncManager", (SignUpModel) this.signUpModel.get());
            }
            Ln.e(e, "makeRequestAndProcessResult with %s", e);
            onRequestFailed();
            throw e;
        }
    }

    private void raiseSpecificErrorEventIfNecessary() throws UacfScheduleException {
        SynchronizationResponse synchronizationResponse = this.syncResponse;
        if (synchronizationResponse != null) {
            boolean wasSuccessful = synchronizationResponse.wasSuccessful();
            if (wasSuccessful) {
                ((LocalSettingsService) this.localSettingsService.get()).setLastLoginDayNumber(currentDayNumber());
            }
            if (!this.hasPostedNotification) {
                this.hasPostedNotification = true;
                if (!wasSuccessful) {
                    int statusCode = this.syncResponse.getStatusCode();
                    if (statusCode == 2) {
                        throw new UacfScheduleException(getResponseStatusCode(), this.syncResponse.hasErrorMessage().booleanValue() ? this.syncResponse.getErrorMessage() : "Your password is invalid.  If you changed it on the website, please log in again with your new password.");
                    } else if (statusCode != 7) {
                        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.SYNC_V1_FAILED, new Builder().put(Attributes.SYNC_V1_ERROR_CODE, Strings.toString(Integer.valueOf(getResponseStatusCode()))).put(Attributes.SYNC_V1_ERROR_MESSAGE, Strings.toString(this.syncResponse.hasErrorMessage().booleanValue() ? this.syncResponse.getErrorMessage() : "Unknown sync error")).put(Attributes.SYNC_V1_TYPE, this.mode.toString()).build());
                    } else {
                        throw new PasswordResetRequiredException(((PasswordResetHelper) this.passwordResetHelper.get()).createDataFromJson(this.syncResponse.getErrorMessage()), getResponseStatusCode(), getResponseStatusMessage());
                    }
                }
            }
        } else {
            throw new UacfScheduleException(557, "unknown error");
        }
    }

    private boolean finalizeSyncAndRaiseErrorIfNecessary(UserV1 userV1) throws UacfScheduleException {
        boolean finalizeSync = finalizeSync(userV1);
        raiseSpecificErrorEventIfNecessary();
        return finalizeSync;
    }

    private boolean finalizeSync(UserV1 userV1) throws UacfScheduleException {
        boolean z;
        try {
            Ln.d("finalizeSync() entered", new Object[0]);
            if (this.syncResponse == null || this.syncResponse.statusCode != 0 || this.syncResponse.isBorked().booleanValue() || userV1 == null) {
                if (this.mode == SyncMode.Import) {
                    Ln.d("sync failed during SignIn mode. resetting user...", new Object[0]);
                    ((Session) this.session.get()).getUser().setUserV1(null);
                }
                z = false;
            } else {
                Ln.d("finalizeSync(), status code == OK, response is not borked, have a valid user", new Object[0]);
                ((SyncPointerService) this.syncPointerService.get()).setLastSyncPointers(this.lastSyncPointers);
                DbConnectionManager dbConnectionManager2 = (DbConnectionManager) this.dbConnectionManager.get();
                dbConnectionManager2.userPropertiesDbAdapter().updateUserPropertiesLastSyncAtTimestamps(userV1.getLocalId());
                if (this.deletedItemIdCutoff > 0) {
                    dbConnectionManager2.deletedItemsDbAdapter().purgeDeletedItemsRowsUpToId((long) this.deletedItemIdCutoff);
                }
                userV1.updateCurrentWeightFromMeasurements(this.context);
                dbConnectionManager2.usersDbAdapter().updateUserLastSyncAt(userV1.getLocalId());
                if (shouldSyncWithV1("exercise")) {
                    ((ExerciseService) this.exerciseService.get()).clearDeletedItems("exercise");
                }
                if (shouldSyncWithV1("exercise_entry")) {
                    ((DiaryService) this.diaryService.get()).clearDeletedItems("exercise_entry");
                }
                z = true;
            }
            DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
            if (z && diaryDayForActiveDateSync != null) {
                Ln.d("finalizeSync() reloaded current day from database", new Object[0]);
                diaryDayForActiveDateSync.loadFromDatabase();
            }
            int responseStatusCode = getResponseStatusCode();
            if (responseStatusCode != 4 && responseStatusCode != 5) {
                return z;
            }
            Ln.d("finalizeSync() status code is %s, which is not successful", Integer.valueOf(responseStatusCode));
            throw new UacfScheduleException(getResponseStatusCode(), getResponseStatusMessage());
        } finally {
            ((SyncUtil) this.syncUtil.get()).migrateStepsData();
        }
    }

    private void buildSyncDeletions(UserV1 userV1, int i) {
        String str;
        int i2;
        Iterator it = ((DbConnectionManager) this.dbConnectionManager.get()).deletedItemsDbAdapter().fetchUnsyncedDeletedItemsForUserId(userV1.getLocalId(), i).iterator();
        while (it.hasNext()) {
            DatabaseObjectReference databaseObjectReference = (DatabaseObjectReference) it.next();
            Ln.d("SYNCV2: deleted item: type = %s, id = %s", Integer.valueOf(databaseObjectReference.getItemType()), Long.valueOf(databaseObjectReference.getLocalId()));
            this.syncRequest.addDeleteItemPacket(databaseObjectReference);
            this.deletedItemIdCutoff = (int) databaseObjectReference.getReferenceId();
        }
        ArrayList<MfpDatabaseObjectV2> arrayList = new ArrayList<>();
        if (shouldSyncWithV1("exercise")) {
            List deletedItems = ((ExerciseService) this.exerciseService.get()).getDeletedItems("exercise");
            Ln.d("SYNCV2: found deleted exercises: (%s)", Strings.toString(deletedItems));
            arrayList.addAll(deletedItems);
        }
        if (shouldSyncWithV1("exercise_entry")) {
            List deletedItems2 = ((DiaryService) this.diaryService.get()).getDeletedItems("exercise_entry");
            Ln.d("SYNCV2: found deleted exercise entries: (%s)", Strings.toString(deletedItems2));
            arrayList.addAll(deletedItems2);
        }
        for (MfpDatabaseObjectV2 mfpDatabaseObjectV2 : arrayList) {
            if (mfpDatabaseObjectV2 instanceof MfpExercise) {
                str = mfpDatabaseObjectV2.getId();
                i2 = 2;
            } else if (mfpDatabaseObjectV2 instanceof MfpExerciseEntry) {
                str = mfpDatabaseObjectV2.getId();
                i2 = 5;
            } else {
                str = null;
                i2 = 0;
            }
            if (i2 > 0) {
                Ln.d("SYNCV2: adding delete item packet: type = %s, masterId = %s, uid = %s", Integer.valueOf(i2), Long.valueOf(mfpDatabaseObjectV2.getMasterId()), str);
                this.syncRequest.addDeleteItemPacket(i2, mfpDatabaseObjectV2.getMasterId(), str, true);
            }
        }
    }

    private int currentDayNumber() {
        return Calendar.getInstance().get(6);
    }

    private void buildResponse(UserV1 userV1) {
        this.syncResponse = new SynchronizationResponse(userV1, this);
    }

    private void buildRequest(UserV1 userV1) {
        this.syncRequest = new SynchronizationRequest(this.context, this);
        switch (this.mode) {
            case BackgroundNormal:
                buildNextNormalSyncRequest(userV1, 0);
                return;
            case Normal:
                buildNextNormalSyncRequest(userV1, 1);
                return;
            case Registration:
                buildUserRegistrationRequest(userV1);
                return;
            case Import:
                buildImportRequest();
                return;
            default:
                return;
        }
    }

    private void buildImportRequest() {
        this.syncRequest.addSynchronizationRequestPacketForImportUsername();
        this.syncRequest.addRequestPendingItemTalliesPacket();
    }

    private void buildUserRegistrationRequest(UserV1 userV1) {
        this.syncRequest.addSynchronizationRequestPacketForUserRegistration();
        this.syncRequest.addUserRegistrationRequestPacketForUser(userV1);
        this.syncRequest.addUserPropertyUpdatePacket(userV1.allProperties());
        this.syncRequest.addSetMeasurementTypesPacket(userV1.getProfile().getMeasurementTypes());
    }

    private void buildNextNormalSyncRequest(UserV1 userV1, int i) {
        this.syncRequest.addSynchronizationRequestPacketForUser(i);
        buildUserProperties(userV1);
        buildSyncDeletions(userV1, 2000 - this.syncRequest.getPacketCount());
        buildSyncCustomItems(userV1, 2000 - this.syncRequest.getPacketCount());
        buildSyncEntries(userV1, 2000 - this.syncRequest.getPacketCount());
    }

    private void buildUserProperties(UserV1 userV1) {
        UserPropertiesDBAdapter userPropertiesDbAdapter = ((DbConnectionManager) this.dbConnectionManager.get()).userPropertiesDbAdapter();
        userPropertiesDbAdapter.saveUserProperties(userV1, (LoginModel) this.loginModel.get());
        Map fetchUnsyncedUserProperties = userPropertiesDbAdapter.fetchUnsyncedUserProperties(userV1.getLocalId());
        if (CollectionUtils.notEmpty(fetchUnsyncedUserProperties)) {
            if (this.mode != SyncMode.Registration) {
                UserV1NutrientGoals.removeNutrientGoals(fetchUnsyncedUserProperties);
            }
            this.syncRequest.addUserPropertyUpdatePacket(fetchUnsyncedUserProperties);
        }
    }

    private boolean buildExerciseEntryPacket(long j) {
        Ln.d("SYNCV2: found unsynced exercise entry with id %s", Long.valueOf(j));
        ExerciseEntry v1ExerciseEntry = ((DiaryService) this.diaryService.get()).getV1ExerciseEntry(j);
        if (v1ExerciseEntry == null) {
            return false;
        }
        Exercise exercise = v1ExerciseEntry.getExercise();
        MfpExercise mfpExercise = null;
        if (exercise != null) {
            mfpExercise = ((ExerciseService) this.exerciseService.get()).getExerciseLocallyWhetherUserOrCustom(exercise.getUid());
            if (mfpExercise == null) {
                mfpExercise = ((ExerciseService) this.exerciseService.get()).getExerciseWithDescriptionForSyncV1(exercise.getDescription());
                if (mfpExercise == null) {
                    String nonLocalizedDescription = ((ExerciseStringService) this.exerciseStringService.get()).getNonLocalizedDescription(exercise.getDescription());
                    if (Strings.notEmpty(nonLocalizedDescription)) {
                        mfpExercise = ((ExerciseService) this.exerciseService.get()).getExerciseWithDescriptionForSyncV1(nonLocalizedDescription);
                    }
                }
            }
            if (mfpExercise == null) {
                mfpExercise = (MfpExercise) ((ExerciseMapper) this.exerciseMapper.get()).tryMapFrom(exercise);
                if (mfpExercise != null) {
                    ((ExerciseService) this.exerciseService.get()).insertExerciseIfNotExists(mfpExercise);
                }
            }
        }
        if (mfpExercise == null) {
            ((DiaryService) this.diaryService.get()).deleteExerciseEntryLocally(v1ExerciseEntry);
            Ln.e("detected exercise entry with no associated exercise", new Object[0]);
            return false;
        }
        exercise.setLocalId(mfpExercise.getLocalId());
        exercise.setMasterDatabaseId(mfpExercise.getMasterId());
        this.syncRequest.addExerciseEntryPacket(v1ExerciseEntry);
        return true;
    }

    private void buildSyncEntries(UserV1 userV1, int i) {
        DbConnectionManager dbConnectionManager2 = (DbConnectionManager) this.dbConnectionManager.get();
        int i2 = 0;
        for (Long longValue : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(4, userV1.getLocalId(), i + 0)) {
            FoodEntry fetchFoodEntryById = dbConnectionManager2.foodEntriesDbAdapter().fetchFoodEntryById(longValue.longValue());
            if (fetchFoodEntryById != null) {
                this.syncRequest.addFoodEntryPacket(fetchFoodEntryById);
                i2++;
            }
        }
        if (i2 < i) {
            if (shouldSyncWithV1("exercise_entry")) {
                Ln.d("SYNCV2: sync'ing exercise entries with v1", new Object[0]);
                for (Long longValue2 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(5, userV1.getLocalId(), i - i2)) {
                    if (buildExerciseEntryPacket(longValue2.longValue())) {
                        i2++;
                    }
                }
                if (i2 >= i) {
                    return;
                }
            }
            for (Long longValue3 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(7, userV1.getLocalId(), i - i2)) {
                WaterEntry fetchWaterEntryById = dbConnectionManager2.waterEntriesDbAdapter().fetchWaterEntryById(longValue3.longValue());
                if (fetchWaterEntryById != null) {
                    this.syncRequest.addSetWaterEntryPacket(fetchWaterEntryById);
                    i2++;
                }
            }
            if (i2 < i) {
                for (Long longValue4 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(10, userV1.getLocalId(), i - i2)) {
                    DiaryNote fetchDiaryNoteById = dbConnectionManager2.diaryNoteDbAdapter().fetchDiaryNoteById(longValue4.longValue());
                    if (fetchDiaryNoteById != null) {
                        this.syncRequest.addSetDiaryNotePacket(fetchDiaryNoteById);
                        i2++;
                    }
                }
                if (i2 < i) {
                    for (Long longValue5 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(8, userV1.getLocalId(), i - i2)) {
                        Measurement measurementByLocalId = ((MeasurementsService) this.measurementsService.get()).getMeasurementByLocalId(longValue5.longValue());
                        if (measurementByLocalId != null) {
                            this.syncRequest.addSetMeasurementPacket(measurementByLocalId);
                            i2++;
                        }
                    }
                    if (i2 < i) {
                        for (Long longValue6 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(15, userV1.getLocalId(), i - i2)) {
                            UserImage userImageWithLocalId = ((UserImageService) this.userImageService.get()).getUserImageWithLocalId(longValue6.longValue());
                            if (userImageWithLocalId != null) {
                                userImageWithLocalId.setImageData(((UserImageService) this.userImageService.get()).getImageDataForImageId(userImageWithLocalId.localId, false));
                                this.syncRequest.addAddUserImagePacket(userImageWithLocalId);
                                i2++;
                            }
                        }
                        if (i2 < i) {
                            for (Long longValue7 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(20, userV1.getLocalId(), i - i2)) {
                                ReminderObject reminderForId = ((RemindersService) this.remindersService.get()).getReminderForId(longValue7.longValue());
                                if (!(reminderForId == null || reminderForId.getStatusFlag() == StatusFlag.DELETED)) {
                                    this.syncRequest.AddReminderPacket(reminderForId);
                                    i2++;
                                    ((RemindersService) this.remindersService.get()).updateStatusFlag(reminderForId.getLocalId(), StatusFlag.UPLOADED);
                                }
                            }
                            if (i2 < i) {
                                for (Long longValue8 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(21, userV1.getLocalId(), i - i2)) {
                                    TrackedNutrient fetchTrackedNutrientById = dbConnectionManager2.trackedNutrientDbAdapter().fetchTrackedNutrientById(longValue8.longValue());
                                    if (fetchTrackedNutrientById != null) {
                                        this.syncRequest.addAddTrackedNutrientPacket(fetchTrackedNutrientById);
                                        i2++;
                                    }
                                }
                                if (i2 < i) {
                                    for (Long longValue9 : dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(26, userV1.getLocalId(), i - i2)) {
                                        FoodPermission fromLocalId = ((FoodPermissionsService) this.foodPermissionsService.get()).getFromLocalId(longValue9.longValue());
                                        if (fromLocalId != null) {
                                            this.syncRequest.addSetFoodPermissionsPacket(fromLocalId);
                                            i2++;
                                        }
                                    }
                                    Long[] fetchUnsyncedEntryItemIdsOfType = dbConnectionManager2.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(27, userV1.getLocalId(), i - i2);
                                    FoodNotesTable foodNotesTable = new FoodNotesTable((SQLiteDatabaseWrapper) this.database.get());
                                    for (Long longValue10 : fetchUnsyncedEntryItemIdsOfType) {
                                        FoodNotes findByLocalId = foodNotesTable.findByLocalId(longValue10.longValue());
                                        if (findByLocalId != null) {
                                            this.syncRequest.addSetFoodNotesPacket(findByLocalId);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void buildSyncCustomItems(UserV1 userV1, int i) {
        DatabaseObject[] fetchUnsyncedOwnedItemsForUserId;
        for (DatabaseObject databaseObject : ((DbConnectionManager) this.dbConnectionManager.get()).genericDbAdapter().fetchUnsyncedOwnedItemsForUserId(userV1.getLocalId(), i)) {
            int itemType = databaseObject.itemType();
            switch (itemType) {
                case 1:
                    this.syncRequest.addFoodPacket((Food) databaseObject);
                    break;
                case 2:
                    if (!shouldSyncWithV1("exercise")) {
                        break;
                    } else {
                        this.syncRequest.addExercisePacket((Exercise) databaseObject);
                        break;
                    }
                case 3:
                    this.syncRequest.addFoodPacket((Food) databaseObject);
                    MealFood mealFood = (MealFood) databaseObject;
                    mealFood.loadIngredientsIfNeeded((DbConnectionManager) this.dbConnectionManager.get());
                    this.syncRequest.addSetMealIngredientsPacketForMealFood(mealFood);
                    break;
                default:
                    switch (itemType) {
                        case 11:
                            this.syncRequest.addFoodPacket((Food) databaseObject);
                            RecipeFood recipeFood = (RecipeFood) databaseObject;
                            recipeFood.loadIngredientsAndPropertiesIfNeeded((DbConnectionManager) this.dbConnectionManager.get());
                            this.syncRequest.addSetRecipePropertiesPacketForRecipeFood(recipeFood);
                            break;
                        case 12:
                            RecipeFood recipeFood2 = ((RecipeBoxItem) databaseObject).recipeFood((DbConnectionManager) this.dbConnectionManager.get());
                            if (recipeFood2 == null) {
                                break;
                            } else {
                                this.syncRequest.addAddRecipeBoxItemPacketForRecipeFood(recipeFood2, databaseObject.localId);
                                break;
                            }
                    }
            }
        }
    }

    private void onRequestFinished() {
        DatabaseUtil.ensureDatabaseTransaction((SQLiteDatabaseWrapper) this.database.get(), new Function0() {
            public void execute() {
                if (!LegacySyncManager.this.syncResponse.isBorked().booleanValue()) {
                    LegacySyncManager.this.syncResponse.decoder.appendDataBuffer(LegacySyncManager.this.response);
                    LegacySyncManager.this.syncResponse.processAvailablePackets();
                }
            }
        });
    }

    private void onRequestFailed() {
        Ln.d("onRequestFailed() entered!", new Object[0]);
        this.syncResponse.setState(4);
        this.syncResponse.setErrorMessage("Network error");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        return r2.context.getResources().getString(com.myfitnesspal.android.R.string.foods);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        return r2.context.getResources().getString(com.myfitnesspal.android.R.string.diary_entries);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getProgressStatusText() {
        /*
            r2 = this;
            com.myfitnesspal.shared.service.syncv1.SynchronizationResponse r0 = r2.syncResponse
            int r0 = r0.getLastProcessedItemType()
            r1 = 15
            if (r0 == r1) goto L_0x0056
            switch(r0) {
                case 1: goto L_0x0048;
                case 2: goto L_0x003a;
                case 3: goto L_0x0048;
                case 4: goto L_0x002c;
                case 5: goto L_0x002c;
                default: goto L_0x000d;
            }
        L_0x000d:
            switch(r0) {
                case 7: goto L_0x002c;
                case 8: goto L_0x001e;
                case 9: goto L_0x0048;
                default: goto L_0x0010;
            }
        L_0x0010:
            android.content.Context r0 = r2.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131886966(0x7f120376, float:1.9408526E38)
            java.lang.String r0 = r0.getString(r1)
            return r0
        L_0x001e:
            android.content.Context r0 = r2.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131888328(0x7f1208c8, float:1.9411288E38)
            java.lang.String r0 = r0.getString(r1)
            return r0
        L_0x002c:
            android.content.Context r0 = r2.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131887037(0x7f1203bd, float:1.940867E38)
            java.lang.String r0 = r0.getString(r1)
            return r0
        L_0x003a:
            android.content.Context r0 = r2.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131887753(0x7f120689, float:1.9410122E38)
            java.lang.String r0 = r0.getString(r1)
            return r0
        L_0x0048:
            android.content.Context r0 = r2.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131887927(0x7f120737, float:1.9410475E38)
            java.lang.String r0 = r0.getString(r1)
            return r0
        L_0x0056:
            android.content.Context r0 = r2.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131889502(0x7f120d5e, float:1.941367E38)
            java.lang.String r0 = r0.getString(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv1.LegacySyncManager.getProgressStatusText():java.lang.String");
    }

    private boolean shouldSyncWithV1(String str) {
        if (this.resourcesToSyncWithV2 == null) {
            this.resourcesToSyncWithV2 = ((SyncUtil) this.syncUtil.get()).getAllSyncV2EnabledResources();
        }
        return !this.resourcesToSyncWithV2.contains(str);
    }
}
