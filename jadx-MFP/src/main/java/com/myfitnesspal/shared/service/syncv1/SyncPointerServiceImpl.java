package com.myfitnesspal.shared.service.syncv1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import com.myfitnesspal.shared.constants.SyncResourceName;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTable;
import com.myfitnesspal.shared.db.table.SyncPointersTable;
import com.myfitnesspal.shared.db.table.SyncPointersTable.Columns;
import com.myfitnesspal.shared.model.v15.SyncPointer;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.syncv2.ops.MfpSyncV2Modes;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class SyncPointerServiceImpl implements SyncPointerService {
    private static final String ITEM_TYPE_WHERE_CLAUSE = "user_id = ? AND item_type_name = ?";
    private static final boolean VERBOSE_LOGGING = false;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;
    private final Lazy<SyncPointersTable> syncPointersTable;
    /* access modifiers changed from: private */
    public final Lazy<SyncUtil> syncUtil;

    /* access modifiers changed from: private */
    public static void debug(String str, Object... objArr) {
    }

    public SyncPointerServiceImpl(Lazy<SyncPointersTable> lazy, Lazy<Session> lazy2, Lazy<SyncUtil> lazy3) {
        this.syncPointersTable = lazy;
        this.session = lazy2;
        this.syncUtil = lazy3;
    }

    public List<SyncPointer> getLastSyncPointers() {
        updateV1Exclusions();
        ArrayList arrayList = new ArrayList();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ");
        sb.append(Strings.toString(Long.valueOf(((Session) this.session.get()).getUser().getLocalId())));
        sQLiteQueryBuilder.appendWhere(sb.toString());
        sQLiteQueryBuilder.setTables(SyncPointersTable.TABLE_NAME);
        Cursor query = sQLiteQueryBuilder.query(((SyncPointersTable) this.syncPointersTable.get()).getDatabase().getDb(), new String[]{Columns.ITEM_TYPE_NAME, Columns.LAST_SYNC_POINTER, Columns.CUTOFF_ID}, null, null, null, null, null);
        if (query != null) {
            try {
                int columnIndex = query.getColumnIndex(Columns.ITEM_TYPE_NAME);
                int columnIndex2 = query.getColumnIndex(Columns.LAST_SYNC_POINTER);
                int columnIndex3 = query.getColumnIndex(Columns.CUTOFF_ID);
                while (query.moveToNext()) {
                    arrayList.add(new SyncPointer(query.getString(columnIndex), query.getString(columnIndex2), query.getLong(columnIndex3)));
                }
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    public void setLastSyncPointers(final List<SyncPointer> list) {
        final SyncPointersTable syncPointersTable2 = (SyncPointersTable) this.syncPointersTable.get();
        DatabaseUtil.ensureDatabaseTransaction(syncPointersTable2.getDatabase(), new Function0() {
            public void execute() {
                long localId = ((Session) SyncPointerServiceImpl.this.session.get()).getUser().getLocalId();
                syncPointersTable2.deleteData("user_id = ? AND item_type_name != ?", Long.valueOf(localId), SyncResourceName.EXCLUDE);
                for (SyncPointer access$100 : list) {
                    SyncPointerServiceImpl.this.updateSyncPointerRow(localId, access$100);
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("user_id", Long.valueOf(localId));
                syncPointersTable2.updateData(contentValues, "user_id = ?", Integer.valueOf(0));
            }
        });
    }

    /* JADX INFO: finally extract failed */
    private SyncPointer getV1Exclusions() {
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        String str = "";
        Cursor cursor = null;
        try {
            Cursor queryData = ((SyncPointersTable) this.syncPointersTable.get()).queryData(new String[]{Columns.LAST_SYNC_POINTER}, ITEM_TYPE_WHERE_CLAUSE, Long.valueOf(localId), SyncResourceName.EXCLUDE);
            if (queryData.moveToFirst()) {
                str = CursorUtils.getString(queryData, Columns.LAST_SYNC_POINTER);
            }
            if (queryData != null) {
                queryData.close();
            }
            return new SyncPointer(SyncResourceName.EXCLUDE.toString(), str, 0);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void updateV1Exclusions() {
        List<String> split = Strings.split(getV1Exclusions().getLastSyncTime(), ",");
        Collection<String> syncV2ResourcesWhoseRolloutsAreOn = ((SyncUtil) this.syncUtil.get()).getSyncV2ResourcesWhoseRolloutsAreOn();
        final HashSet hashSet = new HashSet(split);
        Collection resourcesWithFinalState = ((SyncUtil) this.syncUtil.get()).getResourcesWithFinalState(1);
        final Collection resourcesWithFinalState2 = ((SyncUtil) this.syncUtil.get()).getResourcesWithFinalState(2);
        HashSet hashSet2 = new HashSet();
        debug("SYNCV2a: =======================================================================", new Object[0]);
        debug("SYNCV2a: current exclusions = (%s)", split);
        debug("SYNCV2a: newExclusionsBasedOnRollout = (%s)", syncV2ResourcesWhoseRolloutsAreOn);
        debug("SYNCV2a: before processing, pending = (%s)", resourcesWithFinalState);
        debug("SYNCV2a: before processing, exclusionsToWriteToDb = (%s)", hashSet);
        for (String str : syncV2ResourcesWhoseRolloutsAreOn) {
            debug("SYNCV2a: check NEW resource name = %s", str);
            if (split.contains(str)) {
                debug("SYNCV2a: no change for %s, continue", str);
            } else if (resourcesWithFinalState.contains(str)) {
                debug("SYNCV2a: %s is already pending, continue", str);
            } else if (resourcesWithFinalState2.contains(str)) {
                debug("SYNCV2a: %s is justCompleted, continue", str);
                hashSet.add(str);
            } else {
                debug("SYNCV2a: %s is a change, needs to be marked pending", str);
                hashSet2.add(str);
            }
        }
        for (String str2 : split) {
            debug("SYNCV2a: check CURRENT resource name = %s", str2);
            if (syncV2ResourcesWhoseRolloutsAreOn.contains(str2)) {
                debug("SYNCV2a: no change for %s, continue", str2);
            } else if (resourcesWithFinalState.contains(str2)) {
                debug("SYNCV2a: %s is already pending, continue", str2);
            } else if (resourcesWithFinalState2.contains(str2)) {
                debug("SYNCV2a: %s is just completed, remove from exclusions list", str2);
                hashSet.remove(str2);
            } else {
                debug("SYNCV2a: %s is a change, needs to be marked pending", str2);
                hashSet2.add(str2);
            }
        }
        debug("SYNCV2a: after processing, exclusionsToWriteToDb = (%s)", hashSet);
        debug("SYNCV2a: after processing, newPendingItems = (%s)", hashSet2);
        ((SyncUtil) this.syncUtil.get()).setFinalSyncStateForResources(hashSet2, 1);
        Collection differences = CollectionUtils.getDifferences(split, hashSet);
        final boolean notEmpty = CollectionUtils.notEmpty(differences);
        boolean notEmpty2 = CollectionUtils.notEmpty(resourcesWithFinalState2);
        debug("SYNCV2a: exclusion diffs = (%s), hasExclusionsToWrite = %s, hasJustCompletedItems = %s", differences, Boolean.valueOf(notEmpty), Boolean.valueOf(notEmpty2));
        if (notEmpty || notEmpty2) {
            SQLiteDatabaseWrapper database = ((MfpDatabaseTable) this.syncPointersTable.get()).getDatabase();
            final boolean z = notEmpty2;
            AnonymousClass2 r2 = new Function0() {
                public void execute() {
                    long localId = ((Session) SyncPointerServiceImpl.this.session.get()).getUser().getLocalId();
                    if (notEmpty) {
                        SyncPointerServiceImpl.debug("SYNCV2a: hasExclusionsToWrite = true, writing new exclusions to DB: (%s)", hashSet);
                        SyncPointerServiceImpl.this.updateSyncPointerRow(localId, SyncResourceName.EXCLUDE, Strings.join(",", hashSet), 0);
                    }
                    if (z) {
                        SyncPointerServiceImpl.debug("SYNCV2a: hasJustCompletedItems = true, purging tables for just completed resources (%s)", resourcesWithFinalState2);
                        SyncPointerServiceImpl.this.purgeDatabaseForResourcesBecauseExclusionsChanged(localId, resourcesWithFinalState2);
                        SyncPointerServiceImpl.debug("SYNCV2a: hasJustCompletedItems = true, removing final sync state for just completed resources", new Object[0]);
                        ((SyncUtil) SyncPointerServiceImpl.this.syncUtil.get()).removeFinalSyncStateForResources(resourcesWithFinalState2);
                    }
                }
            };
            DatabaseUtil.ensureDatabaseTransaction(database, r2);
        }
    }

    /* access modifiers changed from: private */
    public void purgeDatabaseForResourcesBecauseExclusionsChanged(long j, Collection<String> collection) {
        SQLiteDatabaseWrapper database = ((SyncPointersTable) this.syncPointersTable.get()).getDatabase();
        if (collection.contains("exercise")) {
            new ExercisesTable(database).deleteData("owner_user_id= ?", Long.valueOf(j));
            ((SyncUtil) this.syncUtil.get()).purgeStateForV2ImportMode(MfpSyncV2Modes.ImportExercisesAndExerciseEntries);
        }
        if (collection.contains("exercise_entry")) {
            new ExerciseEntriesTable(database).deleteData("user_id= ?", Long.valueOf(j));
            ((SyncUtil) this.syncUtil.get()).purgeStateForV2ImportMode(MfpSyncV2Modes.ImportExercisesAndExerciseEntries);
        }
        for (String deleteSyncPointerRow : collection) {
            deleteSyncPointerRow(j, deleteSyncPointerRow);
        }
    }

    private void deleteSyncPointerRow(long j, String str) {
        ((SyncPointersTable) this.syncPointersTable.get()).deleteData(ITEM_TYPE_WHERE_CLAUSE, Long.valueOf(j), str);
    }

    /* access modifiers changed from: private */
    public void updateSyncPointerRow(long j, SyncPointer syncPointer) {
        String key = syncPointer.getKey();
        if (Strings.isEmpty(key)) {
            Ln.e("unknown sync pointer type detected!! %s", syncPointer.getKey());
            return;
        }
        updateSyncPointerRow(j, key, syncPointer.getLastSyncTime(), syncPointer.getCutoffId());
    }

    /* access modifiers changed from: private */
    public void updateSyncPointerRow(long j, String str, String str2, long j2) {
        if (!Strings.isEmpty(str)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_id", Long.valueOf(j));
            contentValues.put(Columns.ITEM_TYPE_NAME, str);
            contentValues.put(Columns.LAST_SYNC_POINTER, str2);
            contentValues.put(Columns.CUTOFF_ID, Long.valueOf(j2));
            ((SyncPointersTable) this.syncPointersTable.get()).insertOrUpdateData(contentValues, ITEM_TYPE_WHERE_CLAUSE, Long.valueOf(j), str);
        }
    }
}
