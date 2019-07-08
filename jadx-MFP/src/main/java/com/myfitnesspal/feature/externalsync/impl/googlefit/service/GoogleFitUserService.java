package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest.Builder;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.DateTime;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.GoogleFit;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.SyncScopes;
import com.myfitnesspal.feature.externalsync.impl.googlefit.db.GoogleFitDbAdapter;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitWeight;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitDateTimeUtils;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.ReportAnalyticsDebouncer;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class GoogleFitUserService extends SimpleAsyncServiceBase implements GoogleFitServiceBase, ExternalUserService {
    private static final float KILOGRAMS_FROM_POUNDS = 0.453592f;
    private static final int MAX_THREADS = 2;
    private static final float POUNDS_FROM_KILOGRAMS = 2.20462f;
    private static final String TAG = "GoogleFitUserService";
    private final Lazy<AppGalleryService> appGalleryService;
    private final String applicationPackage;
    private final Lazy<ConfigService> configService;
    private final Lazy<GoogleFitClient> googleFitClient;
    /* access modifiers changed from: private */
    public final GoogleFitDbAdapter googleFitDbAdapter;
    private final Lazy<KeyedSharedPreferences> prefs;
    private final Lazy<Session> session;
    private final Lazy<SharedPreferences> sharedPreferences;
    private final Lazy<UserWeightService> userWeightService;
    private final ReportAnalyticsDebouncer weightReadDebouncer;
    /* access modifiers changed from: private */
    public final ReportAnalyticsDebouncer weightWriteDebouncer;

    private float getKilogramsFromPounds(float f) {
        return f >= BitmapDescriptorFactory.HUE_RED ? KILOGRAMS_FROM_POUNDS * f : BitmapDescriptorFactory.HUE_RED;
    }

    private float getPoundsFromKilograms(float f) {
        return f >= BitmapDescriptorFactory.HUE_RED ? POUNDS_FROM_KILOGRAMS * f : BitmapDescriptorFactory.HUE_RED;
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public GoogleFitUserService(Context context, Lazy<SharedPreferences> lazy, Lazy<GoogleFitClient> lazy2, Lazy<Session> lazy3, Lazy<ConfigService> lazy4, Lazy<UserWeightService> lazy5, Lazy<AppGalleryService> lazy6, Lazy<AnalyticsService> lazy7, Lazy<KeyedSharedPreferences> lazy8) {
        this.applicationPackage = context.getApplicationContext().getPackageName();
        this.googleFitDbAdapter = new GoogleFitDbAdapter(context);
        this.sharedPreferences = lazy;
        this.googleFitClient = lazy2;
        this.session = lazy3;
        this.configService = lazy4;
        this.userWeightService = lazy5;
        this.appGalleryService = lazy6;
        this.prefs = lazy8;
        this.weightReadDebouncer = new ReportAnalyticsDebouncer(lazy7, Attributes.WEIGHT_READ);
        this.weightWriteDebouncer = new ReportAnalyticsDebouncer(lazy7, Attributes.WEIGHT_WRITE);
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public void onWeightUpdated(final Date date, final float f, final String str) {
        if (((GoogleFitClient) this.googleFitClient.get()).isEnabled()) {
            auto(new Runnable() {
                public void run() {
                    GoogleFitUserService.this.googleFitDbAdapter.insertOrUpdateFitUserWeight(date, f, str);
                    GoogleFitUserService.this.weightWriteDebouncer.call(Extras.MYFITNESSPAL_CLIENT_ID);
                }
            });
        }
    }

    public boolean enabled() {
        return ((GoogleFitClient) this.googleFitClient.get()).isEnabledForSync();
    }

    public void sync() {
        if (enabled()) {
            SyncScope scopeFromWeightConfigVariant = getScopeFromWeightConfigVariant();
            if (scopeFromWeightConfigVariant != null) {
                if (((GoogleFitClient) this.googleFitClient.get()).connectAndWait()) {
                    GoogleApiClient googleApiClient = ((GoogleFitClient) this.googleFitClient.get()).getGoogleApiClient();
                    String userId = ((Session) this.session.get()).getUser().getUserId();
                    boolean shouldRead = shouldRead(scopeFromWeightConfigVariant);
                    if (shouldWrite(scopeFromWeightConfigVariant)) {
                        writeWeight(googleApiClient, userId, shouldRead);
                    } else if (shouldRead) {
                        readWeight(googleApiClient);
                    }
                } else {
                    Ln.e("GoogleFitClient.connectAndWait() failed!", new Object[0]);
                }
            }
        }
    }

    private boolean shouldRead(SyncScope syncScope) {
        String scope = syncScope.getScope();
        return Strings.equals(scope, SyncScopes.FIT_USER_WEIGHT_READ) || Strings.equals(scope, SyncScopes.FIT_USER_WEIGHT_READ_WRITE);
    }

    private boolean shouldWrite(SyncScope syncScope) {
        String scope = syncScope.getScope();
        return Strings.equals(scope, SyncScopes.FIT_USER_WEIGHT_WRITE) || Strings.equals(scope, SyncScopes.FIT_USER_WEIGHT_READ_WRITE);
    }

    private void writeWeight(GoogleApiClient googleApiClient, String str, boolean z) {
        Map userWeights = this.googleFitDbAdapter.getUserWeights(str);
        if (userWeights != null && userWeights.size() > 0) {
            Status status = (Status) Fitness.HistoryApi.insertData(googleApiClient, createWeightFitnessData(userWeights)).await(30, AWAIT_TIME_UNIT);
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Inserting the User dataset in the History API. Status: ");
            sb.append(status);
            Ln.d(str2, sb.toString());
            if (status.isSuccess()) {
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Success inserting user dataset: ");
                sb2.append(status.getStatusCode());
                Ln.d(str3, sb2.toString());
                this.googleFitDbAdapter.clearUserData();
                if (z) {
                    readWeight(googleApiClient);
                }
            }
        } else if (z) {
            readWeight(googleApiClient);
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope getScopeFromWeightConfigVariant() {
        /*
            r3 = this;
            dagger.Lazy<com.myfitnesspal.shared.service.config.ConfigService> r0 = r3.configService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.service.config.ConfigService r0 = (com.myfitnesspal.shared.service.config.ConfigService) r0
            java.lang.String r1 = "google-fit-weight-android-2015-11"
            java.lang.String r2 = "control"
            java.lang.String r0 = r0.getVariant(r1, r2)
            int r1 = r0.hashCode()
            switch(r1) {
                case -1249574820: goto L_0x002c;
                case -1249574819: goto L_0x0022;
                case -1249574818: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0036
        L_0x0018:
            java.lang.String r1 = "variantC"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0036
            r0 = 2
            goto L_0x0037
        L_0x0022:
            java.lang.String r1 = "variantB"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x002c:
            java.lang.String r1 = "variantA"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0036
            r0 = 0
            goto L_0x0037
        L_0x0036:
            r0 = -1
        L_0x0037:
            switch(r0) {
                case 0: goto L_0x004c;
                case 1: goto L_0x0044;
                case 2: goto L_0x003c;
                default: goto L_0x003a;
            }
        L_0x003a:
            r0 = 0
            return r0
        L_0x003c:
            com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope r0 = new com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope
            java.lang.String r1 = "mfp_fit_user_weight_read_write"
            r0.<init>(r1)
            return r0
        L_0x0044:
            com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope r0 = new com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope
            java.lang.String r1 = "mfp_fit_user_weight_write"
            r0.<init>(r1)
            return r0
        L_0x004c:
            com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope r0 = new com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope
            java.lang.String r1 = "mfp_fit_user_weight_read"
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitUserService.getScopeFromWeightConfigVariant():com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope");
    }

    private void readWeight(GoogleApiClient googleApiClient) {
        long lastSyncTime = getLastSyncTime();
        if (lastSyncTime <= 0) {
            lastSyncTime = GoogleFitDateTimeUtils.getStartOfDay(new Date()).getTime();
        }
        long time = GoogleFitDateTimeUtils.getEndOfDay(new Date()).getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Read Range Start: ");
        sb.append(simpleDateFormat.format(Long.valueOf(lastSyncTime)));
        Ln.d(str, sb.toString());
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Read Range End: ");
        sb2.append(simpleDateFormat.format(Long.valueOf(time)));
        Ln.d(str2, sb2.toString());
        DataReadResult dataReadResult = (DataReadResult) Fitness.HistoryApi.readData(googleApiClient, new Builder().aggregate(DataType.TYPE_WEIGHT, DataType.AGGREGATE_WEIGHT_SUMMARY).bucketByTime(1, TimeUnit.DAYS).setLimit(1).setTimeRange(lastSyncTime, time, TimeUnit.MILLISECONDS).build()).await(30, AWAIT_TIME_UNIT);
        Ln.d(TAG, dataReadResult);
        if (dataReadResult.getStatus().isSuccess()) {
            ArrayList arrayList = new ArrayList();
            List<Bucket> buckets = dataReadResult.getBuckets();
            if (buckets != null && buckets.size() > 0) {
                String str3 = TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Number of returned buckets of DataSets is: ");
                sb3.append(buckets.size());
                Ln.d(str3, sb3.toString());
                for (Bucket dataSets : buckets) {
                    for (DataSet convertDataSet : dataSets.getDataSets()) {
                        GoogleFitWeight convertDataSet2 = convertDataSet(convertDataSet, lastSyncTime);
                        if (convertDataSet2 != null) {
                            arrayList.add(convertDataSet2);
                        }
                    }
                }
            } else if (dataReadResult.getDataSets() != null && dataReadResult.getDataSets().size() > 0) {
                String str4 = TAG;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Number of returned DataSets is: ");
                sb4.append(dataReadResult.getDataSets().size());
                Ln.d(str4, sb4.toString());
                for (DataSet convertDataSet3 : dataReadResult.getDataSets()) {
                    GoogleFitWeight convertDataSet4 = convertDataSet(convertDataSet3, lastSyncTime);
                    if (convertDataSet4 != null) {
                        arrayList.add(convertDataSet4);
                    }
                }
            }
            storeMfpFitWeightResult(arrayList);
        }
    }

    private DataSet createWeightFitnessData(Map<Long, Float> map) {
        Ln.d(TAG, "Creating a new data insert request");
        long lastSyncTime = getLastSyncTime();
        DataSet create = DataSet.create(new DataSource.Builder().setAppPackageName(this.applicationPackage).setDataType(DataType.TYPE_WEIGHT).setName(GoogleFit.DATA_SOURCE_NAME).setType(0).build());
        if (CollectionUtils.notEmpty(map)) {
            for (Long l : map.keySet()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("\tInserting weight for : ");
                sb.append(simpleDateFormat.format(l));
                Ln.d(str, sb.toString());
                DataPoint timeInterval = create.createDataPoint().setTimeInterval(l.longValue(), l.longValue(), TimeUnit.MILLISECONDS);
                timeInterval.getValue(Field.FIELD_WEIGHT).setFloat(getKilogramsFromPounds(((Float) map.get(l)).floatValue()));
                create.add(timeInterval);
                if (lastSyncTime < l.longValue()) {
                    lastSyncTime = l.longValue();
                }
            }
        }
        setLastSyncActivity(lastSyncTime);
        return create;
    }

    private GoogleFitWeight convertDataSet(DataSet dataSet, long j) {
        GoogleFitWeight googleFitWeight = null;
        if (dataSet == null) {
            return null;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Data returned for Data type: ");
        sb.append(dataSet.getDataType().getName());
        Ln.d(str, sb.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
        long j2 = j;
        for (DataPoint dataPoint : dataSet.getDataPoints()) {
            Ln.d(TAG, "Data point:");
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\tType: ");
            sb2.append(dataPoint.getDataType().getName());
            Ln.d(str2, sb2.toString());
            String str3 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("\tStart: ");
            sb3.append(simpleDateFormat.format(Long.valueOf(dataPoint.getStartTime(TimeUnit.MILLISECONDS))));
            Ln.d(str3, sb3.toString());
            String str4 = TAG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("\tEnd: ");
            sb4.append(simpleDateFormat.format(Long.valueOf(dataPoint.getEndTime(TimeUnit.MILLISECONDS))));
            Ln.d(str4, sb4.toString());
            String str5 = TAG;
            StringBuilder sb5 = new StringBuilder();
            sb5.append("\tEnd: ");
            sb5.append(dataPoint.getValue(Field.FIELD_AVERAGE));
            Ln.d(str5, sb5.toString());
            if (!dataPoint.getDataSource().getAppPackageName().equals(this.applicationPackage)) {
                long timestamp = dataPoint.getTimestamp(TimeUnit.MILLISECONDS);
                String str6 = TAG;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("\tTimeStamp: ");
                sb6.append(simpleDateFormat.format(Long.valueOf(timestamp)));
                Ln.d(str6, sb6.toString());
                String str7 = TAG;
                StringBuilder sb7 = new StringBuilder();
                sb7.append("\tEndTimeStamp: ");
                sb7.append(simpleDateFormat.format(Long.valueOf(j)));
                Ln.d(str7, sb7.toString());
                if (timestamp > j) {
                    float asFloat = dataPoint.getValue(Field.FIELD_AVERAGE).asFloat();
                    GoogleFitWeight googleFitWeight2 = new GoogleFitWeight();
                    googleFitWeight2.setEntryTime(timestamp);
                    googleFitWeight2.setWeight(getPoundsFromKilograms(asFloat));
                    googleFitWeight2.setSource(dataPoint.getDataSource().getAppPackageName());
                    googleFitWeight = googleFitWeight2;
                    j2 = timestamp;
                }
            }
        }
        setLastSyncActivity(j2);
        return googleFitWeight;
    }

    private void storeMfpFitWeightResult(List<GoogleFitWeight> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (GoogleFitWeight googleFitWeight : list) {
                if (!isConnectedPartner(googleFitWeight.getSource())) {
                    ((UserWeightService) this.userWeightService.get()).writeCurrentWeight(googleFitWeight.getEntryTime(), googleFitWeight.getWeight(), "google_fit");
                    this.weightReadDebouncer.call(googleFitWeight.getSource());
                }
            }
        }
    }

    private boolean isConnectedPartner(String str) {
        return ((AppGalleryService) this.appGalleryService.get()).isAppConnected(str);
    }

    private void setLastSyncActivity(long j) {
        ((SharedPreferences) this.sharedPreferences.get()).edit().putLong(GoogleFitConstants.SharedPreferences.LAST_SYNC_TIME_WEIGHT, j).apply();
    }

    private long getLastSyncTime() {
        return ((SharedPreferences) this.sharedPreferences.get()).getLong(GoogleFitConstants.SharedPreferences.LAST_SYNC_TIME_WEIGHT, -1);
    }
}
