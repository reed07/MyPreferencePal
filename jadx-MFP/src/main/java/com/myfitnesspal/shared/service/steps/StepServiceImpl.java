package com.myfitnesspal.shared.service.steps;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.Nullable;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthConstants;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.StepsTable;
import com.myfitnesspal.shared.db.table.StepsTable.Columns;
import com.myfitnesspal.shared.model.v15.StepsEntryObject;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.UUID;

public class StepServiceImpl extends SimpleAsyncServiceBase implements StepService {
    private static final Set<String> CORRUPTABLE_CLIENT_IDS = new HashSet();
    /* access modifiers changed from: private */
    public final Lazy<AnalyticsService> analyticsService;
    private final Lazy<AppGalleryService> appGalleryService;
    private final UUID deviceId;
    private final Lazy<ExerciseEntriesTable> exerciseEntriesTable;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;
    private final Lazy<StepsTable> stepsTable;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "StepServiceImpl";
    }

    static {
        CORRUPTABLE_CLIENT_IDS.add(SHealthConstants.getClientId());
    }

    public StepServiceImpl(Lazy<StepsTable> lazy, Lazy<ExerciseEntriesTable> lazy2, Lazy<AnalyticsService> lazy3, Lazy<AppGalleryService> lazy4, Lazy<Session> lazy5, UUID uuid) {
        this.stepsTable = lazy;
        this.exerciseEntriesTable = lazy2;
        this.analyticsService = lazy3;
        this.appGalleryService = lazy4;
        this.session = lazy5;
        this.deviceId = uuid;
    }

    public List<MfpStepSource> getStepSources() {
        return ((Session) this.session.get()).getUser().getStepSources();
    }

    public boolean shouldTrackSteps() {
        return Enumerable.firstOrDefault(getStepSources(), new ReturningFunction1<Boolean, MfpStepSource>() {
            public Boolean execute(MfpStepSource mfpStepSource) {
                return Boolean.valueOf(mfpStepSource.isPrimary());
            }
        }) != null;
    }

    public MfpExerciseMetadataForSteps fetchStepsEntry(Date date, MfpStepSource mfpStepSource) {
        MfpExerciseEntry mfpExerciseEntry;
        MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps;
        if (mfpStepSource != null) {
            mfpExerciseEntry = fetchStepsExerciseEntriesByDateAndId(date, mfpStepSource.getClientId(), mfpStepSource.getDeviceId());
        } else {
            mfpExerciseEntry = fetchStepsExerciseEntriesByDate(date);
        }
        if (mfpExerciseEntry == null || mfpExerciseEntry.getStepsData() == null) {
            if (mfpStepSource != null) {
                mfpExerciseMetadataForSteps = fetchStepsMetadataByDateAndId(date, mfpStepSource.getClientId(), mfpStepSource.getDeviceId());
            } else {
                mfpExerciseMetadataForSteps = fetchStepsMetadataByDate(date);
            }
            return mfpExerciseMetadataForSteps;
        }
        MfpExerciseMetadataForSteps stepsData = mfpExerciseEntry.getStepsData();
        stepsData.setDate(mfpExerciseEntry.getDate());
        return stepsData;
    }

    public List<MfpExerciseMetadataForSteps> fetchStepsEntriesByDateRange(Date date, Date date2) {
        List<MfpExerciseEntry> fetchStepsExerciseEntriesByDateRange = fetchStepsExerciseEntriesByDateRange(date, date2);
        ArrayList arrayList = new ArrayList();
        if (!CollectionUtils.notEmpty((Collection<?>) fetchStepsExerciseEntriesByDateRange)) {
            return fetchStepsMetadataByDateRange(date, date2);
        }
        for (MfpExerciseEntry mfpExerciseEntry : fetchStepsExerciseEntriesByDateRange) {
            if (mfpExerciseEntry != null) {
                MfpExerciseMetadataForSteps stepsData = mfpExerciseEntry.getStepsData();
                stepsData.setDate(mfpExerciseEntry.getDate());
                arrayList.add(stepsData);
            }
        }
        return arrayList;
    }

    public void setPrimaryStepSource(Function1<List<MfpStepSource>> function1, Function1<List<Exception>> function12, MfpStepSource mfpStepSource) {
        if (isSameStepSource(mfpStepSource, getPrimaryStepSource())) {
            postToMainThread(function1, getStepSources());
            return;
        }
        final String clientId = mfpStepSource != null ? mfpStepSource.getClientId() : "none";
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.PRIMARY_STEP_SOURCE_SELECTED, new Builder().put(Attributes.CLIENT_ID, clientId).build());
        final MfpStepSource mfpStepSource2 = mfpStepSource;
        final Function1<List<MfpStepSource>> function13 = function1;
        final Function1<List<Exception>> function14 = function12;
        AnonymousClass2 r1 = new Runnable() {
            public void run() {
                StepServiceImpl stepServiceImpl = StepServiceImpl.this;
                ((Session) StepServiceImpl.this.session.get()).getUser().updateStepSourcesAsync(new Function1<List<MfpStepSource>>() {
                    public void execute(List<MfpStepSource> list) throws RuntimeException {
                        if (CollectionUtils.notEmpty((Collection<?>) list)) {
                            ((AnalyticsService) StepServiceImpl.this.analyticsService.get()).reportEvent(Events.PRIMARY_STEP_SOURCE_SELECTED, new Builder().put(Attributes.CLIENT_ID, clientId).build());
                            StepServiceImpl.this.postToMainThread(function13, list);
                        }
                    }
                }, new Function1<List<Exception>>() {
                    public void execute(List<Exception> list) throws RuntimeException {
                        StepServiceImpl.this.postToMainThread(function14, list);
                    }
                }, stepServiceImpl.getStepSourceListForPost(mfpStepSource2, stepServiceImpl.getStepSources()));
            }
        };
        auto(r1);
    }

    public MfpStepSource getPrimaryStepSource() {
        return (MfpStepSource) Enumerable.firstOrDefault(getStepSources(), new ReturningFunction1<Boolean, MfpStepSource>() {
            public Boolean execute(MfpStepSource mfpStepSource) {
                return Boolean.valueOf(mfpStepSource.isPrimary());
            }
        });
    }

    public List<MfpStepSource> registerClientSideStepSource(MfpStepSource mfpStepSource) throws ApiException {
        if (userHasStepSource(mfpStepSource)) {
            return checkAndUpdateDeviceIdForClientSideStepSources(Arrays.asList(new MfpStepSource[]{mfpStepSource}));
        }
        mfpStepSource.setDeviceId(Strings.toString(this.deviceId));
        return getActualStepSources(addStepSourceToList(mfpStepSource, getStepSources()));
    }

    public List<MfpStepSource> removeClientSideStepSourceFromList(MfpStepSource mfpStepSource, List<MfpStepSource> list) {
        return removeStepSourceFromList(mfpStepSource.getClientId(), list);
    }

    public List<MfpStepSource> unregisterClientSideStepSource(MfpStepSource mfpStepSource) throws ApiException {
        String clientId = mfpStepSource.getClientId();
        if (!userHasStepSource(mfpStepSource)) {
            return getStepSources();
        }
        return getActualStepSources(removeStepSourceFromList(clientId, getStepSources()));
    }

    private List<MfpStepSource> getActualStepSources(@Nullable List<MfpStepSource> list) throws ApiException {
        List<MfpStepSource> stepSources = ((Session) this.session.get()).getUser().getStepSources();
        List removeDuplicateStepSources = MfpStepSource.removeDuplicateStepSources(list);
        if (MfpStepSource.isStepsSourcesSame(stepSources, removeDuplicateStepSources)) {
            return stepSources;
        }
        return ((Session) this.session.get()).getUser().updateStepSources(removeDuplicateStepSources);
    }

    public List<MfpStepSource> checkAndUpdateDeviceIdForClientSideStepSources(List<MfpStepSource> list) throws ApiException {
        boolean z;
        boolean z2;
        List<MfpStepSource> stepSources = getStepSources();
        String uuid = this.deviceId.toString();
        boolean z3 = false;
        for (MfpStepSource mfpStepSource : list) {
            int i = 0;
            while (true) {
                if (i >= stepSources.size()) {
                    z = z3;
                    z2 = true;
                    break;
                }
                MfpStepSource mfpStepSource2 = (MfpStepSource) stepSources.get(i);
                if (!Strings.equals(mfpStepSource.getClientId(), mfpStepSource2.getClientId()) || !Strings.equals(mfpStepSource.getAppId(), mfpStepSource2.getAppId())) {
                    i++;
                } else {
                    if (!Strings.equals(mfpStepSource2.getDeviceId(), uuid)) {
                        mfpStepSource2.setDeviceId(uuid);
                        z3 = true;
                    }
                    z = z3;
                    z2 = false;
                }
            }
            if (z2) {
                mfpStepSource.setDeviceId(uuid);
                stepSources.add(mfpStepSource);
                z3 = true;
            } else {
                z3 = z;
            }
        }
        if (!z3) {
            return stepSources;
        }
        return ((Session) this.session.get()).getUser().updateStepSources(getStepSourceListForPost(getPrimaryStepSource(), stepSources));
    }

    public void save(StepsEntryObject stepsEntryObject) {
        boolean z = fetchByExerciseEntryMasterIdOrUid(stepsEntryObject.getMasterId(), stepsEntryObject.getUid()) != null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("master_id", Long.valueOf(stepsEntryObject.getMasterId()));
        contentValues.put("user_id", Long.valueOf(getUserLocalId()));
        contentValues.put("entry_date", Database.encodeDate(stepsEntryObject.getDate()));
        contentValues.put(Columns.EXERCISE_ENTRY_MASTER_ID, Long.valueOf(stepsEntryObject.getAssociatedCalorieAdjustmentEntryMasterId()));
        contentValues.put("steps", Integer.valueOf(stepsEntryObject.getSteps()));
        contentValues.put("calories", Float.valueOf(stepsEntryObject.getCalories()));
        contentValues.put("client_id", Strings.toString(stepsEntryObject.getClientId()));
        contentValues.put("device_id", stepsEntryObject.getDeviceId());
        contentValues.put(Columns.STEP_GOAL, Integer.valueOf(stepsEntryObject.getStepGoal()));
        contentValues.put(Columns.IS_PRIMARY_STEP_SOURCE, Boolean.valueOf(stepsEntryObject.isPrimary()));
        if (z) {
            ((StepsTable) this.stepsTable.get()).updateData(contentValues, "master_id = ?", Long.valueOf(stepsEntryObject.getMasterId()));
        } else {
            ((StepsTable) this.stepsTable.get()).insertData(contentValues);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v15.StepsEntryObject fetchByExerciseEntryMasterIdOrUid(long r8, java.lang.String r10) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x000e
            boolean r1 = com.uacf.core.util.Strings.isEmpty(r10)
            if (r1 == 0) goto L_0x000e
            return r0
        L_0x000e:
            java.lang.String r1 = "(%1$s = 0 AND %2$s = ?) OR (%1$s = ?)"
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x0058 }
            java.lang.String r4 = "exercise_entry_master_id"
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0058 }
            java.lang.String r4 = "exercise_entry_uid"
            r6 = 1
            r3[r6] = r4     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = java.lang.String.format(r1, r3)     // Catch:{ all -> 0x0058 }
            dagger.Lazy<com.myfitnesspal.shared.db.table.StepsTable> r3 = r7.stepsTable     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0058 }
            com.myfitnesspal.shared.db.table.StepsTable r3 = (com.myfitnesspal.shared.db.table.StepsTable) r3     // Catch:{ all -> 0x0058 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0058 }
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)     // Catch:{ all -> 0x0058 }
            r2[r5] = r10     // Catch:{ all -> 0x0058 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0058 }
            java.lang.String r8 = com.uacf.core.util.Strings.toString(r8)     // Catch:{ all -> 0x0058 }
            r2[r6] = r8     // Catch:{ all -> 0x0058 }
            android.database.Cursor r8 = r3.queryData(r0, r1, r2)     // Catch:{ all -> 0x0058 }
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0055 }
            if (r9 == 0) goto L_0x004f
            com.myfitnesspal.shared.model.v15.StepsEntryObject r9 = getStepsEntryFromCursor(r8)     // Catch:{ all -> 0x0055 }
            if (r8 == 0) goto L_0x004e
            r8.close()
        L_0x004e:
            return r9
        L_0x004f:
            if (r8 == 0) goto L_0x0054
            r8.close()
        L_0x0054:
            return r0
        L_0x0055:
            r9 = move-exception
            r0 = r8
            goto L_0x0059
        L_0x0058:
            r9 = move-exception
        L_0x0059:
            if (r0 == 0) goto L_0x005e
            r0.close()
        L_0x005e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchByExerciseEntryMasterIdOrUid(long, java.lang.String):com.myfitnesspal.shared.model.v15.StepsEntryObject");
    }

    public MfpStepSource getStepSource(final String str) {
        if (Strings.isEmpty(str)) {
            return null;
        }
        return (MfpStepSource) Enumerable.firstOrDefault(getStepSources(), new ReturningFunction1<Boolean, MfpStepSource>() {
            public Boolean execute(MfpStepSource mfpStepSource) {
                return Boolean.valueOf(Strings.equals(mfpStepSource.getClientId(), str));
            }
        });
    }

    private long getUserLocalId() {
        return ((Session) this.session.get()).getUser().getLocalId();
    }

    /* access modifiers changed from: private */
    public List<MfpStepSource> getStepSourceListForPost(MfpStepSource mfpStepSource, List<MfpStepSource> list) {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (MfpStepSource mfpStepSource2 : list) {
            String appId = mfpStepSource2.getAppId();
            boolean z = false;
            if (CORRUPTABLE_CLIENT_IDS.contains(mfpStepSource2.getClientId())) {
                if (Strings.isEmpty(appId)) {
                    MfpPlatformApp findByClientId = ((AppGalleryService) this.appGalleryService.get()).findByClientId(mfpStepSource2.getClientId());
                    if (findByClientId != null) {
                        appId = findByClientId.getAppId();
                        mfpStepSource2.setAppId(appId);
                    }
                }
                if (Strings.isEmpty(appId) || hashSet.contains(appId)) {
                    z = true;
                }
            }
            if (!z) {
                MfpStepSource mfpStepSource3 = new MfpStepSource(mfpStepSource2);
                mfpStepSource3.setPrimary(isSameStepSource(mfpStepSource, mfpStepSource2));
                arrayList.add(new MfpStepSource(mfpStepSource3));
                hashSet.add(appId);
            }
        }
        return arrayList;
    }

    private List<MfpStepSource> addStepSourceToList(MfpStepSource mfpStepSource, List<MfpStepSource> list) {
        ArrayList arrayList = new ArrayList(CollectionUtils.size((Collection<?>) list));
        for (MfpStepSource mfpStepSource2 : list) {
            if (!arrayList.contains(mfpStepSource2)) {
                arrayList.add(new MfpStepSource(new MfpStepSource(mfpStepSource2)));
            }
        }
        if (mfpStepSource != null && !arrayList.contains(mfpStepSource)) {
            arrayList.add(new MfpStepSource(mfpStepSource));
        }
        return arrayList;
    }

    private List<MfpStepSource> removeStepSourceFromList(String str, List<MfpStepSource> list) {
        ArrayList arrayList = new ArrayList(list);
        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            MfpStepSource mfpStepSource = (MfpStepSource) listIterator.next();
            if (Strings.equalsIgnoreCase(mfpStepSource.getClientId(), str) && Strings.equalsIgnoreCase(mfpStepSource.getDeviceId(), this.deviceId)) {
                listIterator.remove();
            }
        }
        return arrayList;
    }

    private static boolean isSameStepSource(MfpStepSource mfpStepSource, MfpStepSource mfpStepSource2) {
        boolean z = false;
        if (mfpStepSource2 == null || mfpStepSource == null) {
            return false;
        }
        if (Strings.equals(mfpStepSource.getAppId(), mfpStepSource2.getAppId()) && Strings.equals(mfpStepSource.getClientId(), mfpStepSource2.getClientId()) && Strings.equals(mfpStepSource.getDeviceId(), mfpStepSource2.getDeviceId()) && Strings.equals(mfpStepSource.getDeviceType(), mfpStepSource2.getDeviceType())) {
            z = true;
        }
        return z;
    }

    private boolean userHasStepSource(final MfpStepSource mfpStepSource) {
        return Enumerable.any(getStepSources(), new ReturningFunction1<Boolean, MfpStepSource>() {
            public Boolean execute(MfpStepSource mfpStepSource) {
                return Boolean.valueOf(Strings.equalsIgnoreCase(mfpStepSource.getClientId(), mfpStepSource.getClientId()) && Strings.equalsIgnoreCase(mfpStepSource.getAppId(), mfpStepSource.getAppId()));
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v2.MfpExerciseEntry fetchStepsExerciseEntriesByDateAndId(java.util.Date r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            r0 = 0
            dagger.Lazy<com.myfitnesspal.shared.db.table.ExerciseEntriesTable> r1 = r7.exerciseEntriesTable     // Catch:{ all -> 0x0049 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0049 }
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r1 = (com.myfitnesspal.shared.db.table.ExerciseEntriesTable) r1     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "user_id= ? AND entry_date= ? AND client_id= ? AND device_id= ?"
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0049 }
            r4 = 0
            long r5 = r7.getUserLocalId()     // Catch:{ all -> 0x0049 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0049 }
            r3[r4] = r5     // Catch:{ all -> 0x0049 }
            r4 = 1
            java.lang.String r8 = com.myfitnesspal.shared.util.Database.encodeDate(r8)     // Catch:{ all -> 0x0049 }
            r3[r4] = r8     // Catch:{ all -> 0x0049 }
            r8 = 2
            r3[r8] = r9     // Catch:{ all -> 0x0049 }
            r8 = 3
            r3[r8] = r10     // Catch:{ all -> 0x0049 }
            android.database.Cursor r8 = r1.queryData(r0, r2, r3)     // Catch:{ all -> 0x0049 }
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0046 }
            if (r9 == 0) goto L_0x0040
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r9 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x0046 }
            com.uacf.core.database.CursorMapper r10 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0046 }
            r10.<init>(r8)     // Catch:{ all -> 0x0046 }
            r9.<init>(r10)     // Catch:{ all -> 0x0046 }
            if (r8 == 0) goto L_0x003f
            r8.close()
        L_0x003f:
            return r9
        L_0x0040:
            if (r8 == 0) goto L_0x0045
            r8.close()
        L_0x0045:
            return r0
        L_0x0046:
            r9 = move-exception
            r0 = r8
            goto L_0x004a
        L_0x0049:
            r9 = move-exception
        L_0x004a:
            if (r0 == 0) goto L_0x004f
            r0.close()
        L_0x004f:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchStepsExerciseEntriesByDateAndId(java.util.Date, java.lang.String, java.lang.String):com.myfitnesspal.shared.model.v2.MfpExerciseEntry");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v2.MfpExerciseEntry fetchStepsExerciseEntriesByDate(java.util.Date r9) {
        /*
            r8 = this;
            r0 = 0
            dagger.Lazy<com.myfitnesspal.shared.db.table.ExerciseEntriesTable> r1 = r8.exerciseEntriesTable     // Catch:{ all -> 0x0045 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0045 }
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r1 = (com.myfitnesspal.shared.db.table.ExerciseEntriesTable) r1     // Catch:{ all -> 0x0045 }
            java.lang.String r2 = "user_id= ? AND entry_date= ?"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0045 }
            r4 = 0
            long r5 = r8.getUserLocalId()     // Catch:{ all -> 0x0045 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0045 }
            r3[r4] = r5     // Catch:{ all -> 0x0045 }
            r4 = 1
            java.lang.String r9 = com.myfitnesspal.shared.util.Database.encodeDate(r9)     // Catch:{ all -> 0x0045 }
            r3[r4] = r9     // Catch:{ all -> 0x0045 }
            android.database.Cursor r9 = r1.queryData(r0, r2, r3)     // Catch:{ all -> 0x0045 }
            boolean r1 = r9.moveToFirst()     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x003a
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r0 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x0040 }
            com.uacf.core.database.CursorMapper r1 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0040 }
            r1.<init>(r9)     // Catch:{ all -> 0x0040 }
            r0.<init>(r1)     // Catch:{ all -> 0x0040 }
            if (r9 == 0) goto L_0x0039
            r9.close()
        L_0x0039:
            return r0
        L_0x003a:
            if (r9 == 0) goto L_0x003f
            r9.close()
        L_0x003f:
            return r0
        L_0x0040:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L_0x0046
        L_0x0045:
            r9 = move-exception
        L_0x0046:
            if (r0 == 0) goto L_0x004b
            r0.close()
        L_0x004b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchStepsExerciseEntriesByDate(java.util.Date):com.myfitnesspal.shared.model.v2.MfpExerciseEntry");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 27
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.myfitnesspal.shared.model.v2.MfpExerciseEntry> fetchStepsExerciseEntriesByDateRange(java.util.Date r9, java.util.Date r10) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            dagger.Lazy<com.myfitnesspal.shared.db.table.ExerciseEntriesTable> r2 = r8.exerciseEntriesTable     // Catch:{ all -> 0x004a }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x004a }
            com.myfitnesspal.shared.db.table.ExerciseEntriesTable r2 = (com.myfitnesspal.shared.db.table.ExerciseEntriesTable) r2     // Catch:{ all -> 0x004a }
            java.lang.String r3 = "user_id= ? AND steps > 0 AND entry_date BETWEEN ? AND ?"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x004a }
            r5 = 0
            long r6 = r8.getUserLocalId()     // Catch:{ all -> 0x004a }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x004a }
            r4[r5] = r6     // Catch:{ all -> 0x004a }
            r5 = 1
            java.lang.String r9 = com.myfitnesspal.shared.util.Database.encodeDate(r9)     // Catch:{ all -> 0x004a }
            r4[r5] = r9     // Catch:{ all -> 0x004a }
            r9 = 2
            java.lang.String r10 = com.myfitnesspal.shared.util.Database.encodeDate(r10)     // Catch:{ all -> 0x004a }
            r4[r9] = r10     // Catch:{ all -> 0x004a }
            android.database.Cursor r1 = r2.queryData(r1, r3, r4)     // Catch:{ all -> 0x004a }
        L_0x0030:
            boolean r9 = r1.moveToNext()     // Catch:{ all -> 0x004a }
            if (r9 == 0) goto L_0x0044
            com.myfitnesspal.shared.model.v2.MfpExerciseEntry r9 = new com.myfitnesspal.shared.model.v2.MfpExerciseEntry     // Catch:{ all -> 0x004a }
            com.uacf.core.database.CursorMapper r10 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x004a }
            r10.<init>(r1)     // Catch:{ all -> 0x004a }
            r9.<init>(r10)     // Catch:{ all -> 0x004a }
            r0.add(r9)     // Catch:{ all -> 0x004a }
            goto L_0x0030
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.close()
        L_0x0049:
            return r0
        L_0x004a:
            r9 = move-exception
            if (r1 == 0) goto L_0x0050
            r1.close()
        L_0x0050:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchStepsExerciseEntriesByDateRange(java.util.Date, java.util.Date):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps fetchStepsMetadataByDateAndId(java.util.Date r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            r0 = 0
            dagger.Lazy<com.myfitnesspal.shared.db.table.StepsTable> r1 = r7.stepsTable     // Catch:{ all -> 0x0043 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0043 }
            com.myfitnesspal.shared.db.table.StepsTable r1 = (com.myfitnesspal.shared.db.table.StepsTable) r1     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = "user_id= ? AND entry_date= ? AND client_id= ? AND device_id= ?"
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0043 }
            r4 = 0
            long r5 = r7.getUserLocalId()     // Catch:{ all -> 0x0043 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0043 }
            r3[r4] = r5     // Catch:{ all -> 0x0043 }
            r4 = 1
            java.lang.String r8 = com.myfitnesspal.shared.util.Database.encodeDate(r8)     // Catch:{ all -> 0x0043 }
            r3[r4] = r8     // Catch:{ all -> 0x0043 }
            r8 = 2
            r3[r8] = r9     // Catch:{ all -> 0x0043 }
            r8 = 3
            r3[r8] = r10     // Catch:{ all -> 0x0043 }
            android.database.Cursor r8 = r1.queryData(r0, r2, r3)     // Catch:{ all -> 0x0043 }
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0040 }
            if (r9 == 0) goto L_0x003a
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps r9 = getExerciseMetadataForStepsFromCursor(r8)     // Catch:{ all -> 0x0040 }
            if (r8 == 0) goto L_0x0039
            r8.close()
        L_0x0039:
            return r9
        L_0x003a:
            if (r8 == 0) goto L_0x003f
            r8.close()
        L_0x003f:
            return r0
        L_0x0040:
            r9 = move-exception
            r0 = r8
            goto L_0x0044
        L_0x0043:
            r9 = move-exception
        L_0x0044:
            if (r0 == 0) goto L_0x0049
            r0.close()
        L_0x0049:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchStepsMetadataByDateAndId(java.util.Date, java.lang.String, java.lang.String):com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps fetchStepsMetadataByDate(java.util.Date r9) {
        /*
            r8 = this;
            r0 = 0
            dagger.Lazy<com.myfitnesspal.shared.db.table.StepsTable> r1 = r8.stepsTable     // Catch:{ all -> 0x003f }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x003f }
            com.myfitnesspal.shared.db.table.StepsTable r1 = (com.myfitnesspal.shared.db.table.StepsTable) r1     // Catch:{ all -> 0x003f }
            java.lang.String r2 = "user_id= ? AND entry_date= ?"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x003f }
            r4 = 0
            long r5 = r8.getUserLocalId()     // Catch:{ all -> 0x003f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x003f }
            r3[r4] = r5     // Catch:{ all -> 0x003f }
            r4 = 1
            java.lang.String r9 = com.myfitnesspal.shared.util.Database.encodeDate(r9)     // Catch:{ all -> 0x003f }
            r3[r4] = r9     // Catch:{ all -> 0x003f }
            android.database.Cursor r9 = r1.queryData(r0, r2, r3)     // Catch:{ all -> 0x003f }
            boolean r1 = r9.moveToFirst()     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x0034
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps r0 = getExerciseMetadataForStepsFromCursor(r9)     // Catch:{ all -> 0x003a }
            if (r9 == 0) goto L_0x0033
            r9.close()
        L_0x0033:
            return r0
        L_0x0034:
            if (r9 == 0) goto L_0x0039
            r9.close()
        L_0x0039:
            return r0
        L_0x003a:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L_0x0040
        L_0x003f:
            r9 = move-exception
        L_0x0040:
            if (r0 == 0) goto L_0x0045
            r0.close()
        L_0x0045:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchStepsMetadataByDate(java.util.Date):com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 26
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps> fetchStepsMetadataByDateRange(java.util.Date r9, java.util.Date r10) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            dagger.Lazy<com.myfitnesspal.shared.db.table.StepsTable> r2 = r8.stepsTable     // Catch:{ all -> 0x0044 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0044 }
            com.myfitnesspal.shared.db.table.StepsTable r2 = (com.myfitnesspal.shared.db.table.StepsTable) r2     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "user_id= ? AND steps > 0 AND entry_date BETWEEN ? AND ?"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0044 }
            r5 = 0
            long r6 = r8.getUserLocalId()     // Catch:{ all -> 0x0044 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0044 }
            r4[r5] = r6     // Catch:{ all -> 0x0044 }
            r5 = 1
            java.lang.String r9 = com.myfitnesspal.shared.util.Database.encodeDate(r9)     // Catch:{ all -> 0x0044 }
            r4[r5] = r9     // Catch:{ all -> 0x0044 }
            r9 = 2
            java.lang.String r10 = com.myfitnesspal.shared.util.Database.encodeDate(r10)     // Catch:{ all -> 0x0044 }
            r4[r9] = r10     // Catch:{ all -> 0x0044 }
            android.database.Cursor r1 = r2.queryData(r1, r3, r4)     // Catch:{ all -> 0x0044 }
        L_0x0030:
            boolean r9 = r1.moveToNext()     // Catch:{ all -> 0x0044 }
            if (r9 == 0) goto L_0x003e
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps r9 = getExerciseMetadataForStepsFromCursor(r1)     // Catch:{ all -> 0x0044 }
            r0.add(r9)     // Catch:{ all -> 0x0044 }
            goto L_0x0030
        L_0x003e:
            if (r1 == 0) goto L_0x0043
            r1.close()
        L_0x0043:
            return r0
        L_0x0044:
            r9 = move-exception
            if (r1 == 0) goto L_0x004a
            r1.close()
        L_0x004a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.steps.StepServiceImpl.fetchStepsMetadataByDateRange(java.util.Date, java.util.Date):java.util.List");
    }

    private static StepsEntryObject getStepsEntryFromCursor(Cursor cursor) {
        StepsEntryObject stepsEntryObject = new StepsEntryObject();
        stepsEntryObject.setMasterId(cursor.getLong(cursor.getColumnIndex("master_id")));
        stepsEntryObject.setUid(cursor.getString(cursor.getColumnIndex("uid")));
        stepsEntryObject.setDate(Database.decodeDateString(cursor.getString(cursor.getColumnIndex("entry_date"))));
        stepsEntryObject.setAssociatedCalorieAdjustmentEntryMasterId(cursor.getLong(cursor.getColumnIndex(Columns.EXERCISE_ENTRY_MASTER_ID)));
        stepsEntryObject.setAssociatedCalorieAdjustmentEntryUid(cursor.getString(cursor.getColumnIndex(Columns.EXERCISE_ENTRY_UID)));
        stepsEntryObject.setSteps((int) cursor.getLong(cursor.getColumnIndex("steps")));
        stepsEntryObject.setCalories((float) cursor.getLong(cursor.getColumnIndex("calories")));
        stepsEntryObject.setClientId(Strings.toString(cursor.getString(cursor.getColumnIndex("client_id"))));
        stepsEntryObject.setDeviceId(Strings.toString(cursor.getString(cursor.getColumnIndex("device_id"))));
        stepsEntryObject.setStepGoal((int) cursor.getLong(cursor.getColumnIndex(Columns.STEP_GOAL)));
        stepsEntryObject.setStepSource((int) cursor.getLong(cursor.getColumnIndex(Columns.IS_PRIMARY_STEP_SOURCE)));
        return stepsEntryObject;
    }

    private static MfpExerciseMetadataForSteps getExerciseMetadataForStepsFromCursor(Cursor cursor) {
        MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps = new MfpExerciseMetadataForSteps();
        mfpExerciseMetadataForSteps.setSteps((int) cursor.getLong(cursor.getColumnIndex("steps")));
        mfpExerciseMetadataForSteps.setClientId(Strings.toString(cursor.getString(cursor.getColumnIndex("client_id"))));
        mfpExerciseMetadataForSteps.setDeviceId(Strings.toString(cursor.getString(cursor.getColumnIndex("device_id"))));
        mfpExerciseMetadataForSteps.setDate(Database.decodeDateString(cursor.getString(cursor.getColumnIndex("entry_date"))));
        return mfpExerciseMetadataForSteps;
    }
}
