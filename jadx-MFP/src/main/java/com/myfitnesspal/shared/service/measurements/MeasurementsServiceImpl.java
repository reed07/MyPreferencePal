package com.myfitnesspal.shared.service.measurements;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.db.SqlOp;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.MeasurementTypesTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable.Columns;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.model.v1.MfpMeasurementValue;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MeasurementsServiceImpl implements MeasurementsService {
    private static final String ENTRY_DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private final SQLiteDatabaseWrapper db;
    private final DeletedItemsTable deletedItemsTable;
    private final Lazy<ExternalUserService> externalUserService;
    private final Lazy<ImageAssociationService> imageAssociationService;
    private final MeasurementTypesTable measurementTypesTable;
    private final MeasurementsTable measurementsTable;
    private final Lazy<Session> session;

    public MeasurementsServiceImpl(Lazy<Session> lazy, Lazy<ExternalUserService> lazy2, Lazy<ImageAssociationService> lazy3, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, MeasurementsTable measurementsTable2, MeasurementTypesTable measurementTypesTable2, DeletedItemsTable deletedItemsTable2) {
        this.session = lazy;
        this.externalUserService = lazy2;
        this.imageAssociationService = lazy3;
        this.db = sQLiteDatabaseWrapper;
        this.measurementsTable = measurementsTable2;
        this.measurementTypesTable = measurementTypesTable2;
        this.deletedItemsTable = deletedItemsTable2;
    }

    public long insertOrUpdateMeasurementForToday(String str, float f) {
        return insertOrUpdateMeasurement(str, Calendar.getInstance(), f, null);
    }

    public long insertOrUpdateMeasurement(String str, Calendar calendar, float f, String str2) {
        Measurement measurement = new Measurement();
        measurement.setMasterDatabaseId(0);
        measurement.setUid(null);
        measurement.setMeasurementTypeName(str);
        measurement.setValue(Float.valueOf(f));
        measurement.setEntryDate(calendar);
        measurement.setSourceClientId(null);
        return insertOrUpdateMeasurement(measurement, str2);
    }

    public long insertOrUpdateMeasurement(Measurement measurement) {
        return insertOrUpdateMeasurement(measurement, null);
    }

    private long insertOrUpdateMeasurement(Measurement measurement, String str) {
        long updateMeasurementEntry = updateMeasurementEntry(measurement, str);
        if (Strings.equalsIgnoreCase(measurement.getMeasurementTypeName(), Constants.Measurement.WEIGHT)) {
            ((ExternalUserService) this.externalUserService.get()).onWeightUpdated(measurement.getEntryDate().getTime(), measurement.getValue().floatValue(), ((Session) this.session.get()).getUser().getUserId());
        }
        return updateMeasurementEntry;
    }

    public void insertOrUpdateThirdPartyMeasurement(String str, float f, long j, String str2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Measurement measurement = new Measurement();
        measurement.setMasterDatabaseId(0);
        measurement.setUid(null);
        measurement.setMeasurementTypeName(str);
        measurement.setValue(Float.valueOf(f));
        measurement.setEntryDate(instance);
        measurement.setSourceClientId(str2);
        updateMeasurementEntry(measurement, null);
    }

    private static List<Tuple2<Long, String>> extractIdsAndUids(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("id");
                    int columnIndex2 = cursor.getColumnIndex("uid");
                    if (columnIndex >= 0 && columnIndex2 >= 0) {
                        arrayList.add(Tuple2.create(Long.valueOf(cursor.getLong(columnIndex)), cursor.getString(columnIndex2)));
                    }
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private long updateMeasurementEntry(Measurement measurement, String str) {
        boolean z;
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        String encodeDate = Database.encodeDate(measurement.getEntryDate().getTime());
        Long valueOf = Long.valueOf(getMeasurementTypeIdFromMeasurementTypeName(measurement.getMeasurementTypeName()));
        ArrayList<MfpImageAssociation> arrayList = new ArrayList<>();
        this.db.beginTransaction();
        try {
            List<Tuple2> extractIdsAndUids = extractIdsAndUids(this.measurementsTable.queryData(true, new String[]{"id", "uid"}, "user_id=? AND measurement_type_id=? AND entry_date=?", Long.valueOf(localId), valueOf, encodeDate));
            if (CollectionUtils.notEmpty((Collection<?>) extractIdsAndUids)) {
                z = false;
                for (Tuple2 tuple2 : extractIdsAndUids) {
                    arrayList.addAll(((ImageAssociationService) this.imageAssociationService.get()).findAssociationsForResource(((Long) tuple2.getItem1()).longValue(), (String) tuple2.getItem2()));
                    z |= Strings.equals(measurement.getUid(), (String) tuple2.getItem2());
                }
            } else {
                z = false;
            }
            if (Strings.notEmpty(str)) {
                for (MfpImageAssociation mfpImageAssociation : arrayList) {
                    ((ImageAssociationService) this.imageAssociationService.get()).markForRemoteDisassociation(mfpImageAssociation.getLocalResourceId(), mfpImageAssociation.getResourceId(), mfpImageAssociation.getResourceType());
                }
            }
            boolean z2 = false;
            this.measurementsTable.deleteData("user_id=? AND measurement_type_id=? AND entry_date=?", Long.valueOf(localId), valueOf, encodeDate);
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_id", Long.valueOf(localId));
            contentValues.put(Columns.MEASUREMENT_TYPE_ID, valueOf);
            contentValues.put("value", measurement.getValue());
            contentValues.put("entry_date", encodeDate);
            contentValues.put(Columns.SOURCE_CLIENT_ID, Strings.toString(measurement.getSourceClientId()));
            if (measurement.hasMasterDatabaseId()) {
                contentValues.put("master_id", Long.valueOf(measurement.getMasterDatabaseId()));
            }
            if (measurement.hasUid()) {
                contentValues.put("uid", measurement.getUid());
            }
            long insertData = this.measurementsTable.insertData(contentValues);
            measurement.setLocalId(insertData);
            if (insertData != -1) {
                if (Strings.notEmpty(str)) {
                    ((ImageAssociationService) this.imageAssociationService.get()).associate(str, "measurement", insertData, null);
                } else if (z) {
                    for (MfpImageAssociation mfpImageAssociation2 : arrayList) {
                        for (Tuple2 tuple22 : extractIdsAndUids) {
                            if (mfpImageAssociation2.getLocalResourceId() != -1 && mfpImageAssociation2.getLocalResourceId() == ((Long) tuple22.getItem1()).longValue()) {
                                ((ImageAssociationService) this.imageAssociationService.get()).updateResourceIds(((Long) tuple22.getItem1()).longValue(), insertData, measurement.getUid());
                            }
                        }
                    }
                } else if (CollectionUtils.notEmpty((Collection<?>) arrayList)) {
                    for (MfpImageAssociation mfpImageAssociation3 : arrayList) {
                        if (mfpImageAssociation3.getSyncFlags() == 2) {
                            ((ImageAssociationService) this.imageAssociationService.get()).updateResourceIds(mfpImageAssociation3.getLocalResourceId(), insertData, measurement.getUid());
                            z2 = true;
                        } else {
                            ((ImageAssociationService) this.imageAssociationService.get()).markForRemoteDisassociation(mfpImageAssociation3);
                        }
                    }
                    if (!z2) {
                        MfpImageAssociation mfpImageAssociation4 = (MfpImageAssociation) arrayList.get(arrayList.size() - 1);
                        ((ImageAssociationService) this.imageAssociationService.get()).associateWithExistingImage(mfpImageAssociation4.getLocalImageId(), mfpImageAssociation4.getImageId(), "measurement", insertData, measurement.getUid());
                    }
                }
            }
            this.db.setTransactionSuccessful();
            return insertData;
        } finally {
            this.db.endTransaction();
        }
    }

    public void localDeleteByMasterId(long j) {
        localDeleteById("master_id", String.valueOf(j));
    }

    public void localDeleteById(String str, String str2) {
        Measurement measurementById = getMeasurementById(str, str2);
        if (measurementById != null) {
            ((ImageAssociationService) this.imageAssociationService.get()).markForRemoteDisassociation(measurementById.getLocalId(), measurementById.getUid(), "measurement");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("=?");
        String sb2 = sb.toString();
        this.measurementsTable.deleteData(sb2, str2);
    }

    public void markForRemoteDeletion(long j) {
        Measurement measurementByLocalId = getMeasurementByLocalId(j);
        if (measurementByLocalId != null) {
            if (measurementByLocalId.hasMasterDatabaseId()) {
                new DeletedItemsTable(this.db).recordDeletedItemForUserId(((Session) this.session.get()).getUser().getUserV1().getLocalId(), 8, measurementByLocalId.getMasterDatabaseId(), measurementByLocalId.getUid(), false);
            }
            localDeleteById("id", String.valueOf(j));
        }
    }

    public long getMeasurementTypeIdFromMeasurementTypeName(String str) {
        Cursor query = this.db.query(MeasurementTypesTable.TABLE_NAME, new String[]{"id"}, "user_id=? AND description=?", new String[]{String.valueOf(((Session) this.session.get()).getUser().getLocalId()), str}, null, null, null);
        try {
            if (query.getCount() > 0) {
                query.moveToFirst();
                return query.getLong(query.getColumnIndex("id"));
            }
            query.close();
            return -1;
        } finally {
            query.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r4 = new java.lang.StringBuilder();
        r4.append("Unable to retrieve most recent measurement value for");
        r4.append(com.uacf.core.util.Strings.toString(r1));
        com.uacf.core.util.Ln.e(r4.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float getMostRecentMeasurementValueBeforeDate(java.util.Date r1, long r2, long r4) {
        /*
            r0 = this;
            android.database.Cursor r2 = r0.getMostRecentMeasurementBeforeDate(r1, r2, r4)
            if (r2 == 0) goto L_0x0033
            r3 = 0
            float r1 = r2.getFloat(r3)     // Catch:{ Exception -> 0x0011 }
            r2.close()
            goto L_0x0034
        L_0x000f:
            r1 = move-exception
            goto L_0x002f
        L_0x0011:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x000f }
            r4.<init>()     // Catch:{ all -> 0x000f }
            java.lang.String r5 = "Unable to retrieve most recent measurement value for"
            r4.append(r5)     // Catch:{ all -> 0x000f }
            java.lang.String r1 = com.uacf.core.util.Strings.toString(r1)     // Catch:{ all -> 0x000f }
            r4.append(r1)     // Catch:{ all -> 0x000f }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x000f }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x000f }
            com.uacf.core.util.Ln.e(r1, r3)     // Catch:{ all -> 0x000f }
            r2.close()
            goto L_0x0033
        L_0x002f:
            r2.close()
            throw r1
        L_0x0033:
            r1 = 0
        L_0x0034:
            r2 = -943501440(0xffffffffc7c34f80, float:-99999.0)
            r3 = 1203982208(0x47c34f80, float:99999.0)
            float r1 = com.uacf.core.util.NumberUtils.clamp(r1, r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.measurements.MeasurementsServiceImpl.getMostRecentMeasurementValueBeforeDate(java.util.Date, long, long):float");
    }

    public Cursor getMostRecentMeasurementBeforeDate(Date date, long j, long j2) {
        String[] strArr = {"value", "entry_date", "id"};
        Cursor query = this.db.query(MeasurementsTable.TABLE_NAME, strArr, "user_id=? AND measurement_type_id=? AND entry_date<= ?", new String[]{String.valueOf(j), String.valueOf(j2), Database.encodeDate(date)}, null, null, "entry_date desc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (!NumberUtils.isEffectivelyZero((double) (query.moveToFirst() ? query.getFloat(0) : BitmapDescriptorFactory.HUE_RED))) {
            return query;
        }
        query.close();
        Cursor query2 = this.db.query(MeasurementsTable.TABLE_NAME, strArr, "user_id=? AND measurement_type_id=?", new String[]{String.valueOf(j), String.valueOf(j2)}, null, null, "entry_date asc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (query2.moveToFirst()) {
            return query2;
        }
        query2.close();
        return null;
    }

    public float getMeasurementValueForDate(long j, long j2, Date date) {
        return CursorUtils.readFloatAndClose(this.db.query(MeasurementsTable.TABLE_NAME, new String[]{"value"}, "user_id=? AND measurement_type_id=? AND entry_date= ?", new String[]{String.valueOf(j), String.valueOf(j2), Database.encodeDate(date)}, null, null, null, null), BitmapDescriptorFactory.HUE_RED);
    }

    public float getLeastRecentMeasurementValue(long j, long j2) {
        return getMostOrLeastRecentMeasurementValue(j, j2, "ASC");
    }

    public float getMostRecentMeasurementValue(long j, long j2) {
        return getMostOrLeastRecentMeasurementValue(j, j2, "DESC");
    }

    private float getMostOrLeastRecentMeasurementValue(long j, long j2, String str) {
        String[] strArr = {"value"};
        String[] strArr2 = {String.valueOf(j), String.valueOf(j2)};
        StringBuilder sb = new StringBuilder();
        sb.append("entry_date ");
        sb.append(str);
        return CursorUtils.readFloatAndClose(this.db.query(MeasurementsTable.TABLE_NAME, strArr, "user_id=? AND measurement_type_id=?", strArr2, null, null, sb.toString(), AppEventsConstants.EVENT_PARAM_VALUE_YES), BitmapDescriptorFactory.HUE_RED);
    }

    public Measurement getMeasurementByLocalId(long j) {
        return getMeasurementById("id", String.valueOf(j));
    }

    public Measurement getMeasurementByUid(String str) {
        return getMeasurementById("uid", str);
    }

    public Measurement getMeasurementByDate(Date date, String str) {
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        Long valueOf = Long.valueOf(getMeasurementTypeIdFromMeasurementTypeName(str));
        String encodeDate = Database.encodeDate(date);
        String col = SqlOp.col(MeasurementsTable.TABLE_NAME, "user_id");
        StringBuilder sb = new StringBuilder();
        sb.append(col);
        sb.append("=? AND ");
        sb.append(Columns.MEASUREMENT_TYPE_ID);
        sb.append("=? AND ");
        sb.append("entry_date");
        sb.append("=?");
        return findMeasurement(sb.toString(), String.valueOf(localId), String.valueOf(valueOf), encodeDate);
    }

    public MfpMeasurementValue getInitialMeasurementOfType(String str) {
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        long idForType = getIdForType(localId, str);
        if (idForType > 0) {
            return getInitialMeasurement(localId, idForType);
        }
        return null;
    }

    public void setInitialMeasurementOfType(String str, float f) {
        try {
            this.db.beginTransaction();
            long localId = ((Session) this.session.get()).getUser().getLocalId();
            long idForType = getIdForType(localId, str);
            MfpMeasurementValue initialMeasurement = getInitialMeasurement(localId, idForType);
            if (initialMeasurement != null) {
                this.measurementsTable.deleteData("master_id = ?", Long.valueOf(initialMeasurement.getDatabaseId()));
                this.deletedItemsTable.recordDeletedItemForUserId(localId, 8, initialMeasurement.getDatabaseId(), initialMeasurement.getUid(), true);
                if (idForType > 0) {
                    initialMeasurement.setDatabaseId(0);
                    initialMeasurement.setValue(f);
                    insertMeasurement(localId, idForType, initialMeasurement);
                }
            }
            this.db.setTransactionSuccessful();
        } finally {
            this.db.endTransaction();
        }
    }

    /* JADX INFO: finally extract failed */
    private long getIdForType(long j, String str) {
        Cursor cursor = null;
        try {
            Cursor queryData = this.measurementTypesTable.queryData(new String[]{"id"}, String.format("%s=? AND %s=?", new Object[]{"user_id", "description"}), Long.valueOf(j), str);
            long j2 = queryData.moveToFirst() ? queryData.getLong(queryData.getColumnIndex("id")) : 0;
            if (queryData != null) {
                queryData.close();
            }
            return j2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v1.MfpMeasurementValue getInitialMeasurement(long r16, long r18) {
        /*
            r15 = this;
            java.lang.String r0 = "%s = ? and %s = ?"
            r1 = 2
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "user_id"
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "measurement_type_id"
            r5 = 1
            r2[r5] = r3
            java.lang.String r9 = java.lang.String.format(r0, r2)
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.Long r1 = java.lang.Long.valueOf(r16)
            r0[r4] = r1
            java.lang.Long r1 = java.lang.Long.valueOf(r18)
            r0[r5] = r1
            r1 = 0
            r2 = r15
            com.uacf.core.database.SQLiteDatabaseWrapper r6 = r2.db     // Catch:{ all -> 0x0096 }
            java.lang.String r7 = "measurements"
            java.lang.String[] r10 = com.uacf.core.util.Strings.toStringArray(r0)     // Catch:{ all -> 0x0096 }
            r11 = 0
            r12 = 0
            java.lang.String r13 = "entry_date asc"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0096 }
            java.lang.String r14 = com.uacf.core.util.Strings.toString(r0)     // Catch:{ all -> 0x0096 }
            r8 = 0
            android.database.Cursor r3 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0096 }
            boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x008e
            com.myfitnesspal.shared.model.v1.MfpMeasurementValue r1 = new com.myfitnesspal.shared.model.v1.MfpMeasurementValue     // Catch:{ all -> 0x0094 }
            r1.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = "master_id"
            int r0 = r3.getColumnIndex(r0)     // Catch:{ all -> 0x0094 }
            long r4 = r3.getLong(r0)     // Catch:{ all -> 0x0094 }
            r1.setDatabaseId(r4)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = "uid"
            int r0 = r3.getColumnIndex(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x0094 }
            r1.setUid(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = ENTRY_DATE_FORMAT     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = "entry_date"
            int r4 = r3.getColumnIndex(r4)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = r3.getString(r4)     // Catch:{ all -> 0x0094 }
            java.util.Date r0 = com.myfitnesspal.shared.util.DateTimeUtils.parse(r0, r4)     // Catch:{ all -> 0x0094 }
            r1.setDate(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = "value"
            int r0 = r3.getColumnIndex(r0)     // Catch:{ all -> 0x0094 }
            float r0 = r3.getFloat(r0)     // Catch:{ all -> 0x0094 }
            r1.setValue(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = "source_client_id"
            int r0 = r3.getColumnIndex(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x0094 }
            r1.setSourceClientId(r0)     // Catch:{ all -> 0x0094 }
        L_0x008e:
            if (r3 == 0) goto L_0x0093
            r3.close()
        L_0x0093:
            return r1
        L_0x0094:
            r0 = move-exception
            goto L_0x0098
        L_0x0096:
            r0 = move-exception
            r3 = r1
        L_0x0098:
            if (r3 == 0) goto L_0x009d
            r3.close()
        L_0x009d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.measurements.MeasurementsServiceImpl.getInitialMeasurement(long, long):com.myfitnesspal.shared.model.v1.MfpMeasurementValue");
    }

    private void insertMeasurement(long j, long j2, MfpMeasurementValue mfpMeasurementValue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", Long.valueOf(j));
        contentValues.put(Columns.MEASUREMENT_TYPE_ID, Long.valueOf(j2));
        contentValues.put("entry_date", DateTimeUtils.format(ENTRY_DATE_FORMAT, mfpMeasurementValue.getDate()));
        contentValues.put("value", Float.valueOf(mfpMeasurementValue.getValue()));
        contentValues.put(Columns.SOURCE_CLIENT_ID, mfpMeasurementValue.getSourceClientId());
        this.measurementsTable.insertData(contentValues);
    }

    private Measurement getMeasurementById(String str, String str2) {
        return findMeasurement(String.format("%s=?", new Object[]{SqlOp.col(MeasurementsTable.TABLE_NAME, str)}), str2);
    }

    private Measurement findMeasurement(String str, String... strArr) {
        Cursor rawQuery = this.db.rawQuery(String.format("SELECT %s FROM %s WHERE %s", new Object[]{Strings.join(",", (T[]) new String[]{SqlOp.col(MeasurementsTable.TABLE_NAME, "id"), SqlOp.col(MeasurementsTable.TABLE_NAME, "master_id"), SqlOp.col(MeasurementsTable.TABLE_NAME, "user_id"), SqlOp.col(MeasurementsTable.TABLE_NAME, Columns.MEASUREMENT_TYPE_ID), SqlOp.col(MeasurementsTable.TABLE_NAME, "value"), SqlOp.col(MeasurementsTable.TABLE_NAME, "entry_date"), SqlOp.col(MeasurementsTable.TABLE_NAME, Columns.SOURCE_CLIENT_ID), SqlOp.col(MeasurementTypesTable.TABLE_NAME, "description"), SqlOp.col(MeasurementsTable.TABLE_NAME, "uid")}), String.format("%s LEFT JOIN %s ON %s=%s", new Object[]{MeasurementsTable.TABLE_NAME, MeasurementTypesTable.TABLE_NAME, SqlOp.col(MeasurementTypesTable.TABLE_NAME, "id"), SqlOp.col(MeasurementsTable.TABLE_NAME, Columns.MEASUREMENT_TYPE_ID)}), str}), strArr);
        try {
            if (rawQuery.moveToFirst()) {
                Measurement measurement = new Measurement();
                measurement.setLocalId(rawQuery.getLong(0));
                measurement.setMasterDatabaseId(rawQuery.getLong(1));
                measurement.setValue(Float.valueOf(NumberUtils.clamp(rawQuery.getFloat(4), -99999.0f, 99999.0f)));
                Calendar instance = Calendar.getInstance();
                instance.setTime(Database.decodeDateString(rawQuery.getString(5)));
                measurement.setEntryDate(instance);
                measurement.setSourceClientId(rawQuery.getString(6));
                measurement.setMeasurementTypeName(rawQuery.getString(7));
                measurement.setUid(rawQuery.getString(8));
                return measurement;
            }
            rawQuery.close();
            return null;
        } finally {
            rawQuery.close();
        }
    }
}
