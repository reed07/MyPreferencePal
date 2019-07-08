package com.myfitnesspal.feature.externalsync.impl.shealth.service;

import android.database.Cursor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.Permission;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.InsertRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.InsertRequest.Builder;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadResult;
import com.samsung.android.sdk.healthdata.HealthDataResolver.SortOrder;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class SHealthUserService extends SHealthServiceBase implements ExternalUserService {
    private static final Gson GSON = new Gson();
    private static final String PREFS_KEY_RECENT_WEIGHTS = "recent_weight_updates";
    private static final Set<Permission> READ_PERMISSIONS = new HashSet();
    private static final Set<Permission> WRITE_PERMISSIONS = new HashSet();
    private final Lazy<MeasurementsService> measurementsService;
    private final KeyedSharedPreferences prefs;
    private Map<String, DateAndWeight> recentMfpWeightUpdates = new HashMap();
    private final Session session;

    private static class DateAndWeight {
        @Expose
        public Date updatedTime;
        @Expose
        public float weightInPounds;

        public DateAndWeight(Date date, float f) {
            this.updatedTime = date;
            this.weightInPounds = f;
        }

        public boolean updatedAfter(DateAndWeight dateAndWeight) {
            return this.updatedTime.after(dateAndWeight.updatedTime);
        }

        public boolean sameWeight(DateAndWeight dateAndWeight) {
            return NumberUtils.areEffectivelyEqual(this.weightInPounds, dateAndWeight.weightInPounds);
        }

        public float getKilograms() {
            return (float) LocalizedWeight.fromPounds((double) this.weightInPounds).getValue(Weight.KILOGRAMS);
        }
    }

    static {
        READ_PERMISSIONS.add(Permission.ReadWeight);
        WRITE_PERMISSIONS.add(Permission.WriteWeight);
    }

    public SHealthUserService(SHealthConnection sHealthConnection, Session session2, KeyedSharedPreferences keyedSharedPreferences, Lazy<ConfigService> lazy, Lazy<MeasurementsService> lazy2, Lazy<AppGalleryService> lazy3) {
        super(sHealthConnection, lazy, lazy3);
        this.session = session2;
        this.prefs = keyedSharedPreferences;
        this.measurementsService = lazy2;
        loadMfpWeightUpdates();
    }

    private static String key(Date date) {
        return Database.encodeDate(date);
    }

    public void onWeightUpdated(Date date, float f, String str) {
        if (isWithinSyncDateRange(date)) {
            checkFlagWeightForExport(date, f);
        }
    }

    /* access modifiers changed from: protected */
    public void syncRead() {
        if (enabled()) {
            for (int i = -2; i <= 0; i++) {
                try {
                    checkImportWeightFromSHealth(getDateForDayWithOffset(i));
                } catch (Exception e) {
                    Ln.e(e, "failed to import weight from SHealth", new Object[0]);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void syncWrite() {
        if (enabled()) {
            for (int i = -2; i <= 0; i++) {
                try {
                    checkExportWeightToSHealth(getDateForDayWithOffset(i));
                } catch (Exception e) {
                    Ln.e(e, "failed to export weight to SHealth", new Object[0]);
                }
            }
        }
    }

    private synchronized void checkFlagWeightForExport(Date date, float f) {
        if (f > BitmapDescriptorFactory.HUE_RED) {
            Date date2 = new Date();
            DateAndWeight dateAndWeight = (DateAndWeight) this.recentMfpWeightUpdates.get(key(date));
            if (dateAndWeight == null || dateAndWeight.weightInPounds != f) {
                this.recentMfpWeightUpdates.put(Database.encodeDate(date), new DateAndWeight(date2, f));
                saveMfpWeightUpdates();
            }
        }
    }

    private void checkExportWeightToSHealth(Date date) {
        DateAndWeight dateAndWeight;
        DateAndWeight readWeightValueAndTimeFromSHealth = readWeightValueAndTimeFromSHealth(date);
        synchronized (this) {
            String key = key(date);
            dateAndWeight = (DateAndWeight) this.recentMfpWeightUpdates.get(key);
            if (readWeightValueAndTimeFromSHealth == null && dateAndWeight == null) {
                checkFlagWeightForExport(date, ((MeasurementsService) this.measurementsService.get()).getMeasurementValueForDate(this.session.getUser().getLocalId(), ((MeasurementsService) this.measurementsService.get()).getMeasurementTypeIdFromMeasurementTypeName(Measurement.WEIGHT), date));
                dateAndWeight = (DateAndWeight) this.recentMfpWeightUpdates.get(key);
            }
        }
        if (dateAndWeight != null) {
            if (readWeightValueAndTimeFromSHealth == null || (dateAndWeight.updatedAfter(readWeightValueAndTimeFromSHealth) && !dateAndWeight.sameWeight(readWeightValueAndTimeFromSHealth))) {
                removeMfpResourcesFromSHealthOnDate(HealthConstants.Weight.HEALTH_DATA_TYPE, date);
                HealthDataStore dataStore = getDataStore();
                if (dataStore != null) {
                    InsertRequest build = new Builder().setDataType(HealthConstants.Weight.HEALTH_DATA_TYPE).build();
                    if (DateTimeUtils.isDateToday(date)) {
                        date = dateAndWeight.updatedTime;
                    }
                    long time = date.getTime();
                    long offset = (long) TimeZone.getDefault().getOffset(time);
                    HealthData healthData = new HealthData();
                    healthData.setSourceDevice(getDeviceUuid());
                    healthData.putString("deviceuuid", getDeviceUuid());
                    healthData.putLong("start_time", time);
                    healthData.putLong("time_offset", offset);
                    healthData.putFloat("weight", dateAndWeight.getKilograms());
                    build.addHealthData(healthData);
                    if (new HealthDataResolver(dataStore, getHandler()).insert(build).await().getCount() == 0) {
                        Ln.e("failed to sync mfp weight measurement to SHealth!", new Object[0]);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkImportWeightFromSHealth(java.util.Date r7) {
        /*
            r6 = this;
            java.lang.String r0 = key(r7)
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService$DateAndWeight r1 = r6.readWeightValueAndTimeFromSHealth(r7)
            if (r1 == 0) goto L_0x0056
            monitor-enter(r6)
            java.util.Map<java.lang.String, com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService$DateAndWeight> r2 = r6.recentMfpWeightUpdates     // Catch:{ all -> 0x0053 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0053 }
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService$DateAndWeight r2 = (com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService.DateAndWeight) r2     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x0024
            boolean r3 = r1.updatedAfter(r2)     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0022
            boolean r2 = r2.sameWeight(r1)     // Catch:{ all -> 0x0053 }
            if (r2 != 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            r2 = 0
            goto L_0x0025
        L_0x0024:
            r2 = 1
        L_0x0025:
            if (r2 == 0) goto L_0x0051
            float r2 = r1.weightInPounds     // Catch:{ all -> 0x0053 }
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0051
            java.util.Calendar r2 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x0053 }
            r2.setTime(r7)     // Catch:{ all -> 0x0053 }
            dagger.Lazy<com.myfitnesspal.shared.service.measurements.MeasurementsService> r7 = r6.measurementsService     // Catch:{ all -> 0x0053 }
            java.lang.Object r7 = r7.get()     // Catch:{ all -> 0x0053 }
            com.myfitnesspal.shared.service.measurements.MeasurementsService r7 = (com.myfitnesspal.shared.service.measurements.MeasurementsService) r7     // Catch:{ all -> 0x0053 }
            java.lang.String r3 = "Weight"
            float r4 = r1.weightInPounds     // Catch:{ all -> 0x0053 }
            r5 = 0
            long r2 = r7.insertOrUpdateMeasurement(r3, r2, r4, r5)     // Catch:{ all -> 0x0053 }
            r4 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x0051
            java.util.Map<java.lang.String, com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService$DateAndWeight> r7 = r6.recentMfpWeightUpdates     // Catch:{ all -> 0x0053 }
            r7.put(r0, r1)     // Catch:{ all -> 0x0053 }
        L_0x0051:
            monitor-exit(r6)     // Catch:{ all -> 0x0053 }
            goto L_0x0056
        L_0x0053:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0053 }
            throw r7
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService.checkImportWeightFromSHealth(java.util.Date):void");
    }

    private DateAndWeight readWeightValueAndTimeFromSHealth(Date date) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            long time = date.getTime();
            long j = 86400000 + time;
            ReadResult readResult = (ReadResult) new HealthDataResolver(dataStore, getHandler()).read(new ReadRequest.Builder().setDataType(HealthConstants.Weight.HEALTH_DATA_TYPE).setFilter(Filter.and(Filter.greaterThanEquals("start_time", Long.valueOf(time)), Filter.lessThan("start_time", Long.valueOf(j)))).setSort("start_time", SortOrder.DESC).build()).await();
            if (readResult != null) {
                Cursor resultCursor = readResult.getResultCursor();
                if (resultCursor != null) {
                    int columnIndexOrThrow = resultCursor.getColumnIndexOrThrow("weight");
                    int columnIndexOrThrow2 = resultCursor.getColumnIndexOrThrow("update_time");
                    try {
                        if (resultCursor.moveToFirst()) {
                            return new DateAndWeight(new Date(resultCursor.getLong(columnIndexOrThrow2)), (float) LocalizedWeight.fromKilograms((double) resultCursor.getFloat(columnIndexOrThrow)).getValue(Weight.POUNDS));
                        }
                        resultCursor.close();
                    } finally {
                        resultCursor.close();
                    }
                }
            }
        }
        return null;
    }

    private synchronized void loadMfpWeightUpdates() {
        String string = this.prefs.getString(PREFS_KEY_RECENT_WEIGHTS, "");
        if (Strings.notEmpty(string)) {
            try {
                Map<String, DateAndWeight> map = (Map) GSON.fromJson(string, new TypeToken<Map<String, DateAndWeight>>() {
                }.getType());
                if (map != null) {
                    this.recentMfpWeightUpdates = map;
                    removeOldMfpWeightUpdates();
                }
            } catch (Exception unused) {
            }
        }
    }

    private synchronized void saveMfpWeightUpdates() {
        this.prefs.edit().putString(PREFS_KEY_RECENT_WEIGHTS, GSON.toJson((Object) this.recentMfpWeightUpdates)).apply();
    }

    private synchronized void removeOldMfpWeightUpdates() {
        for (String str : new HashSet(this.recentMfpWeightUpdates.keySet())) {
            if (!isWithinSyncDateRange(Database.decodeDateString(str))) {
                this.recentMfpWeightUpdates.remove(str);
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
