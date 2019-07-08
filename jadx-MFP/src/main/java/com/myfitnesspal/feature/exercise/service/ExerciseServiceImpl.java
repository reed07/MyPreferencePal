package com.myfitnesspal.feature.exercise.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.exception.DuplicateResourceException;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.StockDbSQLiteOpenHelper;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.db.table.StockExercisesTable;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v2.MfpDatabaseObjectV2;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExercise.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpIdAndVersion;
import com.myfitnesspal.shared.model.v2.MfpVersionedDatabaseObjectV2;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.syncv2.SyncV2ServiceBase;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import com.uacf.sync.provider.sdk.model.SyncItem;
import com.uacf.sync.provider.sdk.model.SyncItem.Action;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.inject.Provider;

public class ExerciseServiceImpl extends SyncV2ServiceBase<MfpExercise> implements ExerciseService {
    private static final Comparator<MfpExercise> DESCRIPTION_COMPARATOR = new Comparator<MfpExercise>() {
        public int compare(MfpExercise mfpExercise, MfpExercise mfpExercise2) {
            if (mfpExercise == mfpExercise2) {
                return 0;
            }
            if (mfpExercise == null) {
                return -1;
            }
            if (mfpExercise2 == null) {
                return 1;
            }
            return Strings.toString(mfpExercise.getDescription()).compareToIgnoreCase(Strings.toString(mfpExercise2.getDescription()));
        }
    };
    private static final String EXERCISE_INVALID_FOR_DELETE = "exercises/client_error";
    private static final String EXERCISE_INVALID_FOR_UPDATE = "exercises/client_error";
    private static final int IGNORE_SYNC_FLAG = Integer.MIN_VALUE;
    private static final int MAX_THREADS = 2;
    private static final String TAG = "ExerciseServiceImpl";
    private final Provider<MfpV2Api> apiV2;
    private final DeletedItemsTable deletedItemsTable;
    private final Lazy<ExerciseMapper> exerciseMapper;
    private final ExercisesTable exercisesTable;
    private final Lazy<Session> session;
    private final StockExercisesTable stockExercisesTable;
    private final SyncUtil syncUtil;

    /* renamed from: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public String getSyncResourceName() {
        return "exercise";
    }

    public ExerciseServiceImpl(SyncUtil syncUtil2, Lazy<Session> lazy, Provider<MfpV2Api> provider, Lazy<ExerciseMapper> lazy2, Lazy<SQLiteDatabaseWrapper> lazy3, Lazy<SQLiteDatabaseWrapper> lazy4) {
        this.exercisesTable = new ExercisesTable((SQLiteDatabaseWrapper) lazy3.get());
        this.deletedItemsTable = new DeletedItemsTable((SQLiteDatabaseWrapper) lazy3.get());
        this.stockExercisesTable = new StockExercisesTable((SQLiteDatabaseWrapper) lazy4.get());
        this.session = lazy;
        this.apiV2 = provider;
        this.exerciseMapper = lazy2;
        this.syncUtil = syncUtil2;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public Exercise getV1ExerciseLocally(long j) {
        return getV1ExerciseLocally(j, 0);
    }

    public Exercise getV1ExerciseLocallyFromMasterId(long j) {
        return getV1ExerciseLocally(0, j);
    }

    public MfpExercise getExerciseLocally(long j) {
        return getExerciseLocally(j, 0);
    }

    public Exercise getNonDeletedExerciseV1WithDescription(String str) {
        return mapV2ExerciseToV1Exercise(getNonDeletedExerciseWithDescription(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v2.MfpExercise getNonDeletedExerciseWithDescription(java.lang.String r11) {
        /*
            r10 = this;
            boolean r0 = com.uacf.core.util.Strings.isEmpty(r11)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r0 = "*"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r0 = 2
            java.lang.String[] r0 = new java.lang.String[r0]
            r2 = 0
            r0[r2] = r11
            r11 = 1
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0[r11] = r2
            r2 = 0
            java.lang.String r3 = "exercises"
            java.lang.String r5 = "description = ? AND deleted = ?"
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r11 = android.database.sqlite.SQLiteQueryBuilder.buildQueryString(r2, r3, r4, r5, r6, r7, r8, r9)
            com.myfitnesspal.shared.db.table.ExercisesTable r2 = r10.exercisesTable     // Catch:{ all -> 0x0046 }
            android.database.Cursor r11 = r2.rawQuery(r11, r0)     // Catch:{ all -> 0x0046 }
            boolean r0 = r11.moveToFirst()     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x003e
            com.myfitnesspal.shared.model.v2.MfpExercise r1 = new com.myfitnesspal.shared.model.v2.MfpExercise     // Catch:{ all -> 0x0044 }
            com.uacf.core.database.CursorMapper r0 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0044 }
            r0.<init>(r11)     // Catch:{ all -> 0x0044 }
            r1.<init>(r0)     // Catch:{ all -> 0x0044 }
        L_0x003e:
            if (r11 == 0) goto L_0x0043
            r11.close()
        L_0x0043:
            return r1
        L_0x0044:
            r0 = move-exception
            goto L_0x0048
        L_0x0046:
            r0 = move-exception
            r11 = r1
        L_0x0048:
            if (r11 == 0) goto L_0x004d
            r11.close()
        L_0x004d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.getNonDeletedExerciseWithDescription(java.lang.String):com.myfitnesspal.shared.model.v2.MfpExercise");
    }

    private Exercise getV1ExerciseLocally(long j, long j2) {
        return mapV2ExerciseToV1Exercise(getExerciseLocally(j, j2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v2.MfpExercise getExerciseLocally(long r8, long r10) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x000c
            int r4 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r4 > 0) goto L_0x000c
            return r0
        L_0x000c:
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0030
            int r6 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r6 > 0) goto L_0x0015
            goto L_0x0030
        L_0x0015:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r1[r4] = r8
            java.lang.Long r8 = java.lang.Long.valueOf(r10)
            r1[r5] = r8
            java.lang.String r8 = "Cannot have localId > 0 (%s) AND masterId > 0 (%s)"
            java.lang.String r8 = java.lang.String.format(r8, r1)
            r0.<init>(r8)
            throw r0
        L_0x0030:
            java.lang.String r1 = "SELECT * FROM exercises WHERE %s = ?"
            java.lang.Object[] r2 = new java.lang.Object[r5]
            if (r3 <= 0) goto L_0x0039
            java.lang.String r6 = "id"
            goto L_0x003b
        L_0x0039:
            java.lang.String r6 = "master_id"
        L_0x003b:
            r2[r4] = r6
            java.lang.String r1 = java.lang.String.format(r1, r2)
            com.myfitnesspal.shared.db.table.ExercisesTable r2 = r7.exercisesTable     // Catch:{ all -> 0x006b }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x006b }
            if (r3 <= 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r8 = r10
        L_0x0049:
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x006b }
            r5[r4] = r8     // Catch:{ all -> 0x006b }
            android.database.Cursor r8 = r2.rawQuery(r1, r5)     // Catch:{ all -> 0x006b }
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0069 }
            if (r9 == 0) goto L_0x0063
            com.myfitnesspal.shared.model.v2.MfpExercise r0 = new com.myfitnesspal.shared.model.v2.MfpExercise     // Catch:{ all -> 0x0069 }
            com.uacf.core.database.CursorMapper r9 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0069 }
            r9.<init>(r8)     // Catch:{ all -> 0x0069 }
            r0.<init>(r9)     // Catch:{ all -> 0x0069 }
        L_0x0063:
            if (r8 == 0) goto L_0x0068
            r8.close()
        L_0x0068:
            return r0
        L_0x0069:
            r9 = move-exception
            goto L_0x006d
        L_0x006b:
            r9 = move-exception
            r8 = r0
        L_0x006d:
            if (r8 == 0) goto L_0x0072
            r8.close()
        L_0x0072:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.getExerciseLocally(long, long):com.myfitnesspal.shared.model.v2.MfpExercise");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v0, types: [com.myfitnesspal.shared.model.v2.MfpExercise, android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [com.myfitnesspal.shared.model.v2.MfpExercise, android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, com.myfitnesspal.shared.model.v2.MfpExercise]
  mth insns count: 25
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
    public com.myfitnesspal.shared.model.v2.MfpExercise getExerciseLocallyWhetherUserOrCustom(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = com.uacf.core.util.Strings.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            com.myfitnesspal.shared.db.table.ExercisesTable r0 = r5.exercisesTable     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "SELECT * FROM exercises WHERE uid = ? OR original_uid = ?"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0039 }
            r4 = 0
            r3[r4] = r6     // Catch:{ all -> 0x0039 }
            r4 = 1
            r3[r4] = r6     // Catch:{ all -> 0x0039 }
            android.database.Cursor r1 = r0.rawQuery(r2, r3)     // Catch:{ all -> 0x0039 }
            boolean r0 = r1.moveToFirst()     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x002f
            com.myfitnesspal.shared.model.v2.MfpExercise r6 = new com.myfitnesspal.shared.model.v2.MfpExercise     // Catch:{ all -> 0x0039 }
            com.uacf.core.database.CursorMapper r0 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0039 }
            r0.<init>(r1)     // Catch:{ all -> 0x0039 }
            r6.<init>(r0)     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x002e
            r1.close()
        L_0x002e:
            return r6
        L_0x002f:
            if (r1 == 0) goto L_0x0034
            r1.close()
        L_0x0034:
            com.myfitnesspal.shared.model.v2.MfpExercise r6 = r5.getPublicExerciseById(r6)
            return r6
        L_0x0039:
            r6 = move-exception
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.getExerciseLocallyWhetherUserOrCustom(java.lang.String):com.myfitnesspal.shared.model.v2.MfpExercise");
    }

    public Exercise createNewV1ExerciseLocallyIfMissing(Exercise exercise) throws DuplicateResourceException {
        return mapV2ExerciseToV1Exercise(createNewExerciseLocallyIfMissing(mapV1ExerciseToV2Exercise(exercise)));
    }

    public MfpExercise createNewExerciseLocallyIfMissing(MfpExercise mfpExercise) throws DuplicateResourceException {
        if (!isExerciseValidForCreation(mfpExercise)) {
            throw new DuplicateResourceException("exercises/client_error");
        } else if (doesExerciseExistInLocalDatabase(mfpExercise)) {
            return mfpExercise;
        } else {
            return createNewExerciseLocally(mfpExercise);
        }
    }

    public MfpExercise createNewExerciseLocally(MfpExercise mfpExercise) {
        return insertOrUpdateExerciseLocally(mfpExercise, 1);
    }

    public Exercise insertOrUpdateV1ExerciseLocally(Exercise exercise) {
        return mapV2ExerciseToV1Exercise(insertOrUpdateExerciseLocally(mapV1ExerciseToV2Exercise(exercise)));
    }

    public MfpExercise insertExerciseIfNotExists(MfpExercise mfpExercise) {
        return !doesExerciseExistInLocalDatabase(mfpExercise) ? insertOrUpdateExerciseLocally(mfpExercise) : mfpExercise;
    }

    /* access modifiers changed from: private */
    public MfpExercise insertOrUpdateExerciseLocally(MfpExercise mfpExercise) {
        return insertOrUpdateExerciseLocally(mfpExercise, Integer.MIN_VALUE);
    }

    private MfpExercise insertOrUpdateExerciseLocally(MfpExercise mfpExercise, int i) {
        MfpExercise mfpExercise2 = mfpExercise;
        assignIdAndVersionIfNecessary(mfpExercise);
        long masterId = mfpExercise.getMasterId();
        int i2 = (masterId > 0 ? 1 : (masterId == 0 ? 0 : -1));
        ContentValuesMapper put = new ContentValuesMapper().exclude("id").exclude("master_id", i2 == 0).put(mfpExercise2);
        boolean z = i != Integer.MIN_VALUE;
        if (z) {
            put.put(Columns.SYNC_FLAGS, Integer.valueOf(i));
        } else {
            put.exclude(Columns.SYNC_FLAGS);
        }
        boolean booleanValue = NumberUtils.getBooleanValue(mfpExercise.isPublic());
        Long l = null;
        if (booleanValue || mfpExercise.getOwnerUserLocalId() == 0) {
            put.put("owner_user_id", !booleanValue ? Long.valueOf(((Session) this.session.get()).getUser().getLocalId()) : null);
        }
        if (booleanValue || mfpExercise.getOwnerUserMasterId() == 0) {
            String str = "owner_user_master_id";
            if (!booleanValue) {
                l = Long.valueOf(((Session) this.session.get()).getUser().getMasterDatabaseId());
            }
            put.put(str, l);
        }
        String id = mfpExercise.getId();
        String version = mfpExercise.getVersion();
        ContentValues contentValues = put.get();
        int updateData = (!Strings.notEmpty(id) || !Strings.notEmpty(version)) ? 0 : this.exercisesTable.updateData(contentValues, "original_uid = ? AND uid = ?", id, version);
        if (updateData == 0 && i2 > 0) {
            updateData = this.exercisesTable.updateData(contentValues, "master_id = ?", Long.valueOf(masterId));
        }
        if (updateData == 0) {
            mfpExercise2.setLocalId(this.exercisesTable.insertData(contentValues));
        }
        if (z) {
            mfpExercise.setSyncFlags(i);
        }
        return mfpExercise2;
    }

    public Exercise insertOrUpdateV1ExerciseLocallyForSync(Exercise exercise) {
        return mapV2ExerciseToV1Exercise(insertOrUpdateExerciseLocallyForSync(mapV1ExerciseToV2Exercise(exercise)));
    }

    public MfpExercise insertOrUpdateExerciseLocallyForSync(MfpExercise mfpExercise) {
        return doesExerciseExistInLocalDatabase(mfpExercise) ? updateExerciseLocally(mfpExercise) : createNewExerciseLocally(mfpExercise);
    }

    private MfpExercise updateExerciseLocally(MfpExercise mfpExercise) {
        if (!isSyncV2EnabledForExercises()) {
            deleteExerciseLocally(mfpExercise, false);
        }
        return insertOrUpdateExerciseLocally(mfpExercise, 2);
    }

    public boolean deleteExerciseLocally(Exercise exercise, boolean z) {
        return deleteExerciseLocally(mapV1ExerciseToV2Exercise(exercise), z);
    }

    public boolean deleteExerciseLocally(MfpExercise mfpExercise, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", Integer.valueOf(1));
        contentValues.put("destroyed", Integer.valueOf(z ? 1 : 0));
        if (!isSyncV2EnabledForExercises()) {
            this.deletedItemsTable.recordDeletedItemForUserId(((Session) this.session.get()).getUser().getLocalId(), 2, mfpExercise.getMasterId(), mfpExercise.getId(), z);
        } else {
            contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(3));
        }
        if (updateDataInExercisesTable(mfpExercise.getLocalId(), contentValues) > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean markExerciseAsDeletedLocally(String str) {
        Tuple2 idAndVersionFromCompoundId = MfpVersionedDatabaseObjectV2.getIdAndVersionFromCompoundId(str);
        String str2 = (String) idAndVersionFromCompoundId.getItem1();
        String str3 = (String) idAndVersionFromCompoundId.getItem2();
        boolean z = false;
        if (Strings.isEmpty(str2)) {
            return false;
        }
        String str4 = "original_uid = ?";
        Object[] objArr = {str2};
        if (Strings.notEmpty(str3)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            sb.append(" AND uid = ?");
            str4 = sb.toString();
            objArr = new Object[]{str2, str3};
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", Integer.valueOf(1));
        if (this.exercisesTable.updateData(contentValues, str4, objArr) > 0) {
            z = true;
        }
        return z;
    }

    public boolean markExerciseAsDeletedLocally(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", Integer.valueOf(1));
        return this.exercisesTable.updateData(contentValues, "id = ?", Long.valueOf(j)) > 0;
    }

    /* JADX INFO: finally extract failed */
    public List<MfpDatabaseObjectV2> getDeletedItems(String str) {
        ArrayList arrayList = new ArrayList();
        if (((str.hashCode() == 2056323544 && str.equals("exercise")) ? (char) 0 : 65535) == 0) {
            Cursor cursor = null;
            try {
                Cursor rawQuery = this.exercisesTable.rawQuery("SELECT * FROM exercises WHERE owner_user_id = ? AND sync_flags = 3", Long.valueOf(((Session) this.session.get()).getUser().getLocalId()));
                while (rawQuery.moveToNext()) {
                    arrayList.add(new MfpExercise(new CursorMapper(rawQuery)));
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return arrayList;
    }

    public void clearDeletedItems(String str) {
        if (((str.hashCode() == 2056323544 && str.equals("exercise")) ? (char) 0 : 65535) == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(0));
            this.exercisesTable.updateData(contentValues, "owner_user_id = ? AND sync_flags = 3", Long.valueOf(((Session) this.session.get()).getUser().getLocalId()));
        }
    }

    private void markItemAsSynced(MfpExercise mfpExercise) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(0));
        updateDataInExercisesTable(mfpExercise.getLocalId(), contentValues);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 49
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
    public void publishUnsyncedItems(com.uacf.core.util.Function2<java.lang.Integer, java.lang.Integer> r7) throws com.uacf.sync.engine.UacfScheduleException {
        /*
            r6 = this;
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r0 = 0
            com.myfitnesspal.shared.db.table.ExercisesTable r1 = r6.exercisesTable     // Catch:{ all -> 0x0084 }
            java.lang.String r2 = "owner_user_id = ? AND sync_flags != 0"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0084 }
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r4 = r6.session     // Catch:{ all -> 0x0084 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0084 }
            com.myfitnesspal.shared.service.session.Session r4 = (com.myfitnesspal.shared.service.session.Session) r4     // Catch:{ all -> 0x0084 }
            com.myfitnesspal.shared.model.User r4 = r4.getUser()     // Catch:{ all -> 0x0084 }
            long r4 = r4.getLocalId()     // Catch:{ all -> 0x0084 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0084 }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0084 }
            android.database.Cursor r0 = r1.queryData(r0, r2, r3)     // Catch:{ all -> 0x0084 }
        L_0x0028:
            boolean r1 = r0.moveToNext()     // Catch:{ all -> 0x0084 }
            if (r1 == 0) goto L_0x003a
            long r1 = r0.getLong(r5)     // Catch:{ all -> 0x0084 }
            com.myfitnesspal.shared.model.v2.MfpExercise r1 = r6.getExerciseLocally(r1)     // Catch:{ all -> 0x0084 }
            r7.add(r1)     // Catch:{ all -> 0x0084 }
            goto L_0x0028
        L_0x003a:
            if (r0 == 0) goto L_0x003f
            r0.close()
        L_0x003f:
            java.util.Iterator r7 = r7.iterator()
        L_0x0043:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x0083
            java.lang.Object r0 = r7.next()
            com.myfitnesspal.shared.model.v2.MfpExercise r0 = (com.myfitnesspal.shared.model.v2.MfpExercise) r0
            int r1 = r0.getSyncFlags()     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            switch(r1) {
                case 1: goto L_0x0063;
                case 2: goto L_0x005f;
                case 3: goto L_0x0057;
                default: goto L_0x0056;
            }     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
        L_0x0056:
            goto L_0x007f
        L_0x0057:
            java.lang.String r1 = r0.getId()     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            r6.deleteExerciseRemotely(r1)     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            goto L_0x007f
        L_0x005f:
            r6.updateExerciseRemotely(r0)     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            goto L_0x007f
        L_0x0063:
            r6.createNewExerciseRemotely(r0)     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            boolean r1 = r0.isDeleted()     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            if (r1 == 0) goto L_0x007f
            java.lang.String r1 = r0.getId()     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            r6.deleteExerciseRemotely(r1)     // Catch:{ DuplicateResourceException -> 0x007b, ApiException -> 0x0074 }
            goto L_0x007f
        L_0x0074:
            r7 = move-exception
            com.myfitnesspal.shared.service.syncv2.SyncExceptions$ApiSyncException r0 = new com.myfitnesspal.shared.service.syncv2.SyncExceptions$ApiSyncException
            r0.<init>(r7)
            throw r0
        L_0x007b:
            r1 = move-exception
            com.uacf.core.util.Ln.e(r1)
        L_0x007f:
            r6.markItemAsSynced(r0)
            goto L_0x0043
        L_0x0083:
            return
        L_0x0084:
            r7 = move-exception
            if (r0 == 0) goto L_0x008a
            r0.close()
        L_0x008a:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.publishUnsyncedItems(com.uacf.core.util.Function2):void");
    }

    public MfpExercise getExerciseRemotely(String str) throws ApiException {
        return (MfpExercise) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(String.format(Uri.SINGLE_EXERCISE, new Object[]{str}))).getItem();
    }

    public MfpExercise createNewExerciseRemotely(MfpExercise mfpExercise) throws ApiException {
        MfpExercise sanitizedExerciseForPost = getSanitizedExerciseForPost(mfpExercise);
        assignIdAndVersionIfNecessary(sanitizedExerciseForPost);
        return (MfpExercise) Enumerable.firstOrDefault(((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withJsonBody(new ApiRequest(Collections.singletonList(sanitizedExerciseForPost)))).withOutputType(API_RESPONSE_MAPPER.class)).post(Uri.EXERCISES, new Object[0])).getItems());
    }

    public void updateExerciseRemotely(MfpExercise mfpExercise) throws ApiException {
        try {
            MfpIdAndVersion mfpIdAndVersion = (MfpIdAndVersion) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withJsonBody(new ApiRequest(getSanitizedExerciseForPut(mfpExercise)))).withOutputType(MfpIdAndVersion.API_RESPONSE_MAPPER.class)).put(String.format(Uri.SINGLE_EXERCISE, new Object[]{mfpExercise.getId()}))).getItem();
            if (mfpIdAndVersion != null && mfpExercise.getLocalId() > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("original_uid", mfpIdAndVersion.getId());
                contentValues.put("uid", mfpIdAndVersion.getVersion());
                updateDataInExercisesTable(mfpExercise.getLocalId(), contentValues);
            }
        } catch (ApiException e) {
            if ("exercises/client_error".equals(e.getApiError().getError())) {
                deleteExerciseLocally(mfpExercise, false);
            }
        }
    }

    public void deleteExerciseRemotely(String str) throws ApiException {
        try {
            ((MfpV2Api) ((MfpV2Api) this.apiV2.get()).withOutputType(ApiResponseBase.class)).delete(String.format(Uri.SINGLE_EXERCISE, new Object[]{str}));
        } catch (ApiException e) {
            if (!"exercises/client_error".equals(e.getApiError().getError())) {
                throw e;
            }
        }
        markExerciseAsDeletedLocally(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.myfitnesspal.shared.model.v2.MfpExercise> getOwnedExercisesOfType(int r4, @org.jetbrains.annotations.NotNull com.myfitnesspal.feature.search.model.SortOrder r5) {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.Cursor r4 = r3.getCursorForOwnedExercisesOfType(r4, r5)     // Catch:{ all -> 0x0024 }
        L_0x0009:
            boolean r5 = r4.moveToNext()     // Catch:{ all -> 0x0022 }
            if (r5 == 0) goto L_0x001c
            r5 = 0
            long r1 = r4.getLong(r5)     // Catch:{ all -> 0x0022 }
            com.myfitnesspal.shared.model.v2.MfpExercise r5 = r3.getExerciseLocally(r1)     // Catch:{ all -> 0x0022 }
            r0.add(r5)     // Catch:{ all -> 0x0022 }
            goto L_0x0009
        L_0x001c:
            if (r4 == 0) goto L_0x0021
            r4.close()
        L_0x0021:
            return r0
        L_0x0022:
            r5 = move-exception
            goto L_0x0026
        L_0x0024:
            r5 = move-exception
            r4 = 0
        L_0x0026:
            if (r4 == 0) goto L_0x002b
            r4.close()
        L_0x002b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.getOwnedExercisesOfType(int, com.myfitnesspal.feature.search.model.SortOrder):java.util.List");
    }

    public boolean updateOwnedExerciseOwnerLocalIdFromOwnerMasterId() {
        User user = ((Session) this.session.get()).getUser();
        ContentValues contentValues = new ContentValues();
        contentValues.put("owner_user_id", Long.valueOf(user.getLocalId()));
        return this.exercisesTable.updateData(contentValues, "owner_user_master_id= ?", Long.valueOf(user.getMasterDatabaseId())) > 0;
    }

    public MfpExercise getExerciseWithDescriptionForSyncV1(String str) {
        String[] strArr = {str};
        MfpExercise publicExerciseFromCursorAndClose = (this.stockExercisesTable.getDatabase() == null || !StockDbSQLiteOpenHelper.doesStockDatabaseFileExist(MyFitnessPalApp.getInstance())) ? null : getPublicExerciseFromCursorAndClose(this.stockExercisesTable.rawQuery("SELECT *, 1 AS is_public FROM stock_exercises WHERE description = ?", strArr));
        return publicExerciseFromCursorAndClose == null ? getPublicExerciseFromCursorAndClose(this.exercisesTable.rawQuery("SELECT * FROM exercises WHERE description = ?", strArr)) : publicExerciseFromCursorAndClose;
    }

    private MfpExercise getPublicExerciseFromCursorAndClose(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.moveToNext()) {
                    return readExerciseFromCursor(cursor);
                }
                cursor.close();
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    public List<MfpExercise> getAllPublicExercisesOfType(int i) {
        HashMap hashMap = new HashMap();
        MyFitnessPalApp instance = MyFitnessPalApp.getInstance();
        if (this.stockExercisesTable.getDatabase() != null && StockDbSQLiteOpenHelper.doesStockDatabaseFileExist(instance)) {
            Cursor cursor = null;
            try {
                Cursor rawQuery = this.stockExercisesTable.rawQuery("SELECT *, 1 AS is_public FROM stock_exercises WHERE searchable=1 AND exercise_type = ?", Integer.valueOf(i));
                while (rawQuery.moveToNext()) {
                    MfpExercise readExerciseFromCursor = readExerciseFromCursor(rawQuery);
                    hashMap.put(readExerciseFromCursor.getId(), readExerciseFromCursor);
                }
                rawQuery.close();
                Cursor rawQuery2 = this.exercisesTable.rawQuery("SELECT * FROM exercises WHERE exercise_type = ? AND is_public = 1", Integer.valueOf(i));
                while (rawQuery2.moveToNext()) {
                    MfpExercise readExerciseFromCursor2 = readExerciseFromCursor(rawQuery2);
                    hashMap.put(readExerciseFromCursor2.getId(), readExerciseFromCursor2);
                }
                if (rawQuery2 != null) {
                    rawQuery2.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.values());
        Collections.sort(arrayList, DESCRIPTION_COMPARATOR);
        return arrayList;
    }

    private MfpExercise readExerciseFromCursor(Cursor cursor) {
        return new MfpExercise(new CursorMapper(cursor));
    }

    public MfpExercise getPublicExerciseById(String str) {
        return getPublicExercise("uid", str);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v2.MfpExercise getPublicExercise(java.lang.String r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = com.uacf.core.util.Strings.isEmpty(r8)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SELECT *, 1 AS is_public FROM stock_exercises WHERE "
            r0.append(r2)
            r0.append(r7)
            java.lang.String r2 = " = ?"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.myfitnesspal.shared.db.table.StockExercisesTable r2 = r6.stockExercisesTable     // Catch:{ all -> 0x007f }
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x007f }
            r5 = 0
            r4[r5] = r8     // Catch:{ all -> 0x007f }
            android.database.Cursor r0 = r2.rawQuery(r0, r4)     // Catch:{ all -> 0x007f }
            boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x007c }
            if (r2 == 0) goto L_0x003a
            com.myfitnesspal.shared.model.v2.MfpExercise r7 = r6.readExerciseFromCursor(r0)     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x0039
            r0.close()
        L_0x0039:
            return r7
        L_0x003a:
            if (r0 == 0) goto L_0x003f
            r0.close()
        L_0x003f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "SELECT * FROM exercises WHERE is_public = 1 AND "
            r2.append(r4)
            r2.append(r7)
            java.lang.String r7 = " = ?"
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.myfitnesspal.shared.db.table.ExercisesTable r2 = r6.exercisesTable     // Catch:{ all -> 0x0075 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0075 }
            r3[r5] = r8     // Catch:{ all -> 0x0075 }
            android.database.Cursor r0 = r2.rawQuery(r7, r3)     // Catch:{ all -> 0x0075 }
            boolean r7 = r0.moveToFirst()     // Catch:{ all -> 0x0075 }
            if (r7 == 0) goto L_0x006f
            com.myfitnesspal.shared.model.v2.MfpExercise r7 = r6.readExerciseFromCursor(r0)     // Catch:{ all -> 0x0075 }
            if (r0 == 0) goto L_0x006e
            r0.close()
        L_0x006e:
            return r7
        L_0x006f:
            if (r0 == 0) goto L_0x0074
            r0.close()
        L_0x0074:
            return r1
        L_0x0075:
            r7 = move-exception
            if (r0 == 0) goto L_0x007b
            r0.close()
        L_0x007b:
            throw r7
        L_0x007c:
            r7 = move-exception
            r1 = r0
            goto L_0x0080
        L_0x007f:
            r7 = move-exception
        L_0x0080:
            if (r1 == 0) goto L_0x0085
            r1.close()
        L_0x0085:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.getPublicExercise(java.lang.String, java.lang.Object):com.myfitnesspal.shared.model.v2.MfpExercise");
    }

    public int getCountForOwnedExercisesOfType(int i) {
        String str;
        String[] strArr = {"COUNT(id)"};
        String str2 = "owner_user_id = ? AND deleted = 0 %s";
        Object[] objArr = new Object[1];
        if (i == 2) {
            str = "";
        } else {
            str = String.format("AND exercise_type = %s", new Object[]{Integer.valueOf(i)});
        }
        objArr[0] = str;
        String format = String.format(str2, objArr);
        Cursor queryData = this.exercisesTable.queryData(strArr, format, Long.valueOf(((Session) this.session.get()).getUser().getLocalId()));
        if (queryData != null) {
            try {
                if (queryData.moveToFirst()) {
                    return queryData.getInt(0);
                }
                queryData.close();
            } finally {
                queryData.close();
            }
        }
        return 0;
    }

    private Cursor getCursorForOwnedExercisesOfType(int i, SortOrder sortOrder) {
        String str;
        String str2 = "owner_user_id = ? AND deleted = 0 %s ORDER BY description %s";
        Object[] objArr = new Object[2];
        if (i == 2) {
            str = "";
        } else {
            str = String.format("AND exercise_type = %s", new Object[]{Integer.valueOf(i)});
        }
        objArr[0] = str;
        objArr[1] = sortOrder == SortOrder.ALPHABETICAL_DESCENDING ? "DESC" : "ASC";
        return this.exercisesTable.queryData(new String[]{"id"}, String.format(str2, objArr), Long.valueOf(((Session) this.session.get()).getUser().getLocalId()));
    }

    private MfpExercise getSanitizedExerciseForPost(MfpExercise mfpExercise) {
        MfpExercise mfpExercise2 = (MfpExercise) ParcelableUtil.clone(mfpExercise, MfpExercise.CREATOR);
        mfpExercise2.setMasterId(0);
        return mfpExercise2;
    }

    private MfpExercise getSanitizedExerciseForPut(MfpExercise mfpExercise) {
        MfpExercise mfpExercise2 = (MfpExercise) ParcelableUtil.clone(mfpExercise, MfpExercise.CREATOR);
        mfpExercise2.setMasterId(0);
        mfpExercise2.setId(null);
        mfpExercise2.setVersion(null);
        return mfpExercise2;
    }

    private int updateDataInExercisesTable(long j, ContentValues contentValues) {
        return updateDataInExercisesTable(j, null, null, contentValues);
    }

    private int updateDataInExercisesTable(long j, String str, String str2, ContentValues contentValues) {
        if (j > 0) {
            return this.exercisesTable.updateData(contentValues, "id = ?", Long.valueOf(j));
        } else if (!Strings.notEmpty(str) || !Strings.notEmpty(str2)) {
            return 0;
        } else {
            return this.exercisesTable.updateData(contentValues, "original_uid = ? AND uid = ? ", str, str2);
        }
    }

    private Exercise mapV2ExerciseToV1Exercise(MfpExercise mfpExercise) {
        if (mfpExercise != null) {
            return (Exercise) ((ExerciseMapper) this.exerciseMapper.get()).reverseMap(mfpExercise);
        }
        return null;
    }

    private MfpExercise mapV1ExerciseToV2Exercise(Exercise exercise) {
        if (exercise != null) {
            return (MfpExercise) ((ExerciseMapper) this.exerciseMapper.get()).tryMapFrom(exercise);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doesExerciseExistInLocalDatabase(com.myfitnesspal.shared.model.v2.MfpExercise r11) {
        /*
            r10 = this;
            java.lang.Boolean r0 = r11.isPublic()
            boolean r0 = com.uacf.core.util.NumberUtils.getBooleanValue(r0)
            r1 = 0
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x0027
            long r5 = r11.getLocalId()
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x0027
            com.myfitnesspal.shared.db.table.ExercisesTable r7 = r10.exercisesTable
            java.lang.String r8 = "id = ?"
            java.lang.Object[] r9 = new java.lang.Object[r3]
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            r9[r4] = r5
            boolean r5 = r7.any(r8, r9)
            goto L_0x0028
        L_0x0027:
            r5 = 0
        L_0x0028:
            if (r5 != 0) goto L_0x005d
            java.lang.String r6 = r11.getId()
            boolean r7 = com.uacf.core.util.Strings.notEmpty(r6)
            if (r7 == 0) goto L_0x005d
            if (r0 == 0) goto L_0x0042
            com.myfitnesspal.shared.db.table.StockExercisesTable r5 = r10.stockExercisesTable
            java.lang.String r7 = "uid = ?"
            java.lang.Object[] r8 = new java.lang.Object[r3]
            r8[r4] = r6
            boolean r5 = r5.any(r7, r8)
        L_0x0042:
            if (r5 != 0) goto L_0x005d
            java.lang.String r7 = r11.getVersion()
            boolean r8 = com.uacf.core.util.Strings.notEmpty(r7)
            if (r8 == 0) goto L_0x005d
            com.myfitnesspal.shared.db.table.ExercisesTable r5 = r10.exercisesTable
            java.lang.String r8 = "original_uid = ? AND uid = ?"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r9[r4] = r6
            r9[r3] = r7
            boolean r5 = r5.any(r8, r9)
        L_0x005d:
            if (r5 != 0) goto L_0x008b
            long r6 = r11.getMasterId()
            int r11 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r11 <= 0) goto L_0x008b
            if (r0 == 0) goto L_0x0079
            com.myfitnesspal.shared.db.table.StockExercisesTable r11 = r10.stockExercisesTable
            java.lang.String r0 = "master_id = ?"
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Long r2 = java.lang.Long.valueOf(r6)
            r1[r4] = r2
            boolean r5 = r11.any(r0, r1)
        L_0x0079:
            if (r5 != 0) goto L_0x008b
            com.myfitnesspal.shared.db.table.ExercisesTable r11 = r10.exercisesTable
            java.lang.String r0 = "master_id = ?"
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Long r2 = java.lang.Long.valueOf(r6)
            r1[r4] = r2
            boolean r5 = r11.any(r0, r1)
        L_0x008b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl.doesExerciseExistInLocalDatabase(com.myfitnesspal.shared.model.v2.MfpExercise):boolean");
    }

    public Class<? extends Parcelable> getSyncItemClass() {
        return MfpExercise.class;
    }

    public void consumeSyncItems(List<SyncItem<MfpExercise>> list) {
        Enumerable.forEach((Collection<T>) list, (Function1<T>) new Function1<SyncItem<MfpExercise>>() {
            public void execute(SyncItem<MfpExercise> syncItem) {
                MfpExercise mfpExercise = (MfpExercise) syncItem.getItem();
                if (syncItem.getAction() == null) {
                    Ln.e("SYNCV2: ExerciseServiceImpl#consumeSyncItems action is null!", new Object[0]);
                    return;
                }
                switch (AnonymousClass3.$SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action[syncItem.getAction().ordinal()]) {
                    case 1:
                        ExerciseServiceImpl.this.markExerciseAsDeletedLocally(syncItem.getId());
                        break;
                    case 2:
                    case 3:
                        ExerciseServiceImpl.this.insertOrUpdateExerciseLocally(mfpExercise);
                        break;
                }
            }
        });
    }

    private void assignIdAndVersionIfNecessary(MfpExercise mfpExercise) {
        boolean isEmpty = Strings.isEmpty(mfpExercise.getId());
        boolean isEmpty2 = Strings.isEmpty(mfpExercise.getVersion());
        boolean z = isEmpty || isEmpty2;
        if (isEmpty) {
            mfpExercise.assignExternalId();
            mfpExercise.setVersion(mfpExercise.getId());
        }
        if (isEmpty2) {
            mfpExercise.assignExternalVersion();
        }
        if (z) {
            ContentValues contentValues = new ContentValuesMapper().put("original_uid", mfpExercise.getId()).put("uid", mfpExercise.getVersion()).put(Columns.SYNC_FLAGS, Integer.valueOf(1)).get();
            mfpExercise.setSyncFlags(1);
            updateDataInExercisesTable(mfpExercise.getLocalId(), contentValues);
        }
    }

    private boolean isExerciseValidForCreation(MfpExercise mfpExercise) {
        if (Strings.notEmpty(mfpExercise.getDescription())) {
            if (!this.exercisesTable.any("description = ? AND deleted = ?", mfpExercise.getDescription(), Integer.valueOf(0))) {
                return true;
            }
        }
        return false;
    }

    private boolean isSyncV2EnabledForExercises() {
        return this.syncUtil.getSyncV2ResourcesWhoseRolloutsAreOn().contains("exercise");
    }
}
