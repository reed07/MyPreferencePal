package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import android.content.SharedPreferences;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest.Builder;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.logging.type.LogSeverity;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.DateTime;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.SyncScopes;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitStepsEntry;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitDateTimeUtils;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import com.myfitnesspal.shared.constants.Constants.ABTest.GoogleFitSteps201511;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.model.v2.MfpStepsEntryV2;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleFitStepsService implements GoogleFitServiceBase, ExternalStepsService {
    private static String API_DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private static String API_DATE_TIME_FORMAT = Format.newDatabaseDateTimeFormat().toPattern();
    private final Lazy<ConfigService> configService;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<SharedPreferences> fitClientStore;
    private final Lazy<GoogleFitClient> googleFitClient;
    private final Lazy<KeyedSharedPreferences> googleFitStore;
    private final Lazy<Session> session;
    private final Lazy<StepService> stepService;

    private static class MfpFitStepsContainer {
        final List<GoogleFitStepsEntry> entries = new ArrayList();
        int totalSteps = 0;
        int totalStepsToday = 0;

        MfpFitStepsContainer() {
        }

        public void addEntry(GoogleFitStepsEntry googleFitStepsEntry) {
            this.entries.add(googleFitStepsEntry);
            this.totalSteps += googleFitStepsEntry.getSteps();
            if (DateTimeUtils.isDateToday(googleFitStepsEntry.getStartTime())) {
                this.totalStepsToday += googleFitStepsEntry.getSteps();
            }
        }

        public void addContainer(MfpFitStepsContainer mfpFitStepsContainer) {
            for (GoogleFitStepsEntry addEntry : mfpFitStepsContainer.entries) {
                addEntry(addEntry);
            }
        }
    }

    public GoogleFitStepsService(Lazy<Session> lazy, Lazy<SharedPreferences> lazy2, Lazy<KeyedSharedPreferences> lazy3, Lazy<GoogleFitClient> lazy4, Lazy<ConfigService> lazy5, Lazy<DiaryService> lazy6, Lazy<StepService> lazy7) {
        this.session = lazy;
        this.fitClientStore = lazy2;
        this.googleFitStore = lazy3;
        this.googleFitClient = lazy4;
        this.configService = lazy5;
        this.diaryService = lazy6;
        this.stepService = lazy7;
        clearStoredGoogleFitStepsFromPrefs();
    }

    public boolean enabled() {
        return ((GoogleFitClient) this.googleFitClient.get()).isEnabledForSync();
    }

    public void sync() {
        if (enabled()) {
            if (((GoogleFitClient) this.googleFitClient.get()).connectAndWait()) {
                SyncScope syncScope = getSyncScope();
                if (syncScope != null && syncScope.getScope().equals(SyncScopes.FIT_STEPS_READ)) {
                    syncDataWithFitCloud(((GoogleFitClient) this.googleFitClient.get()).getGoogleApiClient());
                }
            } else {
                Ln.e("GoogleFitClient.connectAndWait() failed!", new Object[0]);
            }
        }
    }

    private MfpFitStepsContainer getMfpFitStepsContainer(GoogleApiClient googleApiClient, long j, long j2) {
        long j3 = j;
        long j4 = j2;
        try {
            DataReadResult dataReadResult = (DataReadResult) Fitness.HistoryApi.readData(googleApiClient, new Builder().aggregate(new DataSource.Builder().setDataType(DataType.TYPE_STEP_COUNT_DELTA).setType(1).setStreamName("estimated_steps").setAppPackageName("com.google.android.gms").build(), DataType.AGGREGATE_STEP_COUNT_DELTA).bucketByTime(5, TimeUnit.MINUTES).setTimeRange(j3, j4, TimeUnit.MILLISECONDS).build()).await(30, AWAIT_TIME_UNIT);
            if (dataReadResult.getStatus().isSuccess()) {
                return convertResultToMfpResult(dataReadResult);
            }
        } catch (Exception e) {
            Ln.e(e.getMessage(), new Object[0]);
        }
        return null;
    }

    private MfpFitStepsContainer getMfpFitStepsContainerDelta(GoogleApiClient googleApiClient, long j, long j2) {
        long j3 = j;
        long j4 = j2;
        try {
            DataReadResult dataReadResult = (DataReadResult) Fitness.HistoryApi.readData(googleApiClient, new Builder().read(new DataSource.Builder().setDataType(DataType.TYPE_STEP_COUNT_DELTA).setType(1).setStreamName("estimated_steps").setAppPackageName("com.google.android.gms").build()).setTimeRange(j3, j4, TimeUnit.MILLISECONDS).build()).await(30, AWAIT_TIME_UNIT);
            if (dataReadResult.getStatus().isSuccess()) {
                return convertResultToMfpResult(dataReadResult);
            }
        } catch (Exception e) {
            Ln.e(e.getMessage(), new Object[0]);
        }
        return null;
    }

    private void syncDataWithFitCloud(GoogleApiClient googleApiClient) {
        int i;
        if (GoogleFitStepsUtils.isGoogleFitStepSource(((StepService) this.stepService.get()).getPrimaryStepSource())) {
            Calendar instance = Calendar.getInstance();
            long lastSyncStepsTimeMidnight = getLastSyncStepsTimeMidnight();
            MfpFitStepsContainer mfpFitStepsContainer = new MfpFitStepsContainer();
            while (true) {
                Calendar instance2 = Calendar.getInstance();
                instance2.setTimeInMillis(lastSyncStepsTimeMidnight);
                instance2.add(10, 24);
                long timeInMillis = instance2.getTimeInMillis();
                MfpFitStepsContainer mfpFitStepsContainer2 = getMfpFitStepsContainer(googleApiClient, lastSyncStepsTimeMidnight, timeInMillis);
                if (mfpFitStepsContainer2 != null) {
                    mfpFitStepsContainer.addContainer(mfpFitStepsContainer2);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
                    Ln.d("Steps Read\n\t\t%s - %s\n\t\tTotal: %d, Today: %d", simpleDateFormat.format(Long.valueOf(lastSyncStepsTimeMidnight)), simpleDateFormat.format(Long.valueOf(timeInMillis)), Integer.valueOf(mfpFitStepsContainer2.totalSteps), Integer.valueOf(mfpFitStepsContainer2.totalStepsToday));
                }
                if (instance2.after(instance)) {
                    break;
                }
                lastSyncStepsTimeMidnight = timeInMillis;
            }
            Ln.d("Total Steps Read\n\t\tTotal: %d, Today: %d", Integer.valueOf(mfpFitStepsContainer.totalSteps), Integer.valueOf(mfpFitStepsContainer.totalStepsToday));
            long lastSyncStepsCount = (long) getLastSyncStepsCount();
            if (mfpFitStepsContainer.totalSteps > 0 && lastSyncStepsCount != ((long) mfpFitStepsContainer.totalSteps)) {
                storeMfpFitStepsResult(mfpFitStepsContainer, instance.getTimeInMillis());
            }
            DailyTotalResult dailyTotalResult = (DailyTotalResult) Fitness.HistoryApi.readDailyTotal(googleApiClient, DataType.TYPE_STEP_COUNT_DELTA).await(30, AWAIT_TIME_UNIT);
            if (dailyTotalResult.getStatus().isSuccess()) {
                DataSet total = dailyTotalResult.getTotal();
                i = (total == null || total.isEmpty()) ? 0 : ((DataPoint) total.getDataPoints().get(0)).getValue(Field.FIELD_STEPS).asInt();
                setLastSyncTodaysStepsCount(i);
            } else {
                Ln.e("Failed to readDailyTotal steps", new Object[0]);
                i = 0;
            }
            Ln.d("readDailyTotal: %d", Integer.valueOf(i));
        }
    }

    private SyncScope getSyncScope() {
        if (((ConfigService) this.configService.get()).isVariantEnabled(GoogleFitSteps201511.NAME, "on")) {
            return new SyncScope(SyncScopes.FIT_STEPS_READ);
        }
        return null;
    }

    private MfpFitStepsContainer convertResultToMfpResult(DataReadResult dataReadResult) {
        MfpFitStepsContainer mfpFitStepsContainer = new MfpFitStepsContainer();
        if (dataReadResult != null) {
            List<Bucket> buckets = dataReadResult.getBuckets();
            if (buckets != null && buckets.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Number of returned buckets of DataSets is: ");
                sb.append(buckets.size());
                Ln.d(sb.toString(), new Object[0]);
                for (Bucket dataSets : buckets) {
                    for (DataSet convertDataSet : dataSets.getDataSets()) {
                        mfpFitStepsContainer.addContainer(convertDataSet(convertDataSet));
                    }
                }
            } else if (dataReadResult.getDataSets() != null && dataReadResult.getDataSets().size() > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Number of returned DataSets is: ");
                sb2.append(dataReadResult.getDataSets().size());
                Ln.d(sb2.toString(), new Object[0]);
                for (DataSet convertDataSet2 : dataReadResult.getDataSets()) {
                    mfpFitStepsContainer.addContainer(convertDataSet(convertDataSet2));
                }
            }
        }
        return mfpFitStepsContainer;
    }

    private MfpFitStepsContainer convertDataSet(DataSet dataSet) {
        MfpFitStepsContainer mfpFitStepsContainer = new MfpFitStepsContainer();
        if (dataSet != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                Ln.d("Data point:", new Object[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("\tStart: ");
                sb.append(simpleDateFormat.format(Long.valueOf(dataPoint.getStartTime(TimeUnit.MILLISECONDS))));
                Ln.d(sb.toString(), new Object[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\tEnd: ");
                sb2.append(simpleDateFormat.format(Long.valueOf(dataPoint.getEndTime(TimeUnit.MILLISECONDS))));
                Ln.d(sb2.toString(), new Object[0]);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("\tSteps: ");
                sb3.append(dataPoint.getValue(Field.FIELD_STEPS).asInt());
                Ln.d(sb3.toString(), new Object[0]);
                GoogleFitStepsEntry googleFitStepsEntry = new GoogleFitStepsEntry();
                googleFitStepsEntry.setStartTime(dataPoint.getStartTime(TimeUnit.MILLISECONDS));
                googleFitStepsEntry.setEndTime(dataPoint.getEndTime(TimeUnit.MILLISECONDS));
                googleFitStepsEntry.setSteps(dataPoint.getValue(Field.FIELD_STEPS).asInt());
                googleFitStepsEntry.setSource(dataPoint.getOriginalDataSource() != null ? dataPoint.getOriginalDataSource().getAppPackageName() : "google_fit");
                mfpFitStepsContainer.addEntry(googleFitStepsEntry);
            }
        }
        return mfpFitStepsContainer;
    }

    private MfpStepsEntryV2 convertGoogleFitStepsIntoMfpSteps(GoogleFitStepsEntry googleFitStepsEntry) {
        UserProfile profile = ((Session) this.session.get()).getUser().getProfile();
        if (profile == null || googleFitStepsEntry == null) {
            return null;
        }
        try {
            float pounds = profile.getCurrentWeight().getPounds();
            float inches = profile.getHeight().getInches();
            double ageInYears = (((((double) (10.0f * pounds)) * 0.45359237d) + ((((double) inches) * 6.25d) * 2.54d)) - ((double) (DateTimeUtils.getAgeInYears(profile.getDateOfBirth()) * 5))) + ((double) (profile.isFemale().booleanValue() ? -161 : 5));
            double d = ((double) (inches / 12.0f)) * (profile.isFemale().booleanValue() ? 0.413d : 0.415d);
            double d2 = 0.0d;
            if (d != 0.0d) {
                d2 = ((double) googleFitStepsEntry.getSteps()) / (5280.0d / d);
            }
            double d3 = (((double) pounds) * (12.0d * d2 < 5.0d ? 0.3d : 0.63d) * d2) + (ageInYears / 288.0d);
            MfpStepsEntryV2 mfpStepsEntryV2 = new MfpStepsEntryV2();
            mfpStepsEntryV2.setDate(DateTimeUtils.format(API_DATE_FORMAT, new Date(googleFitStepsEntry.getStartTime())));
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(googleFitStepsEntry.getStartTime());
            instance.set(13, 0);
            instance.set(14, 0);
            mfpStepsEntryV2.setStartTime(DateTimeUtils.format(API_DATE_TIME_FORMAT, instance.getTime()));
            mfpStepsEntryV2.setDuration(LogSeverity.NOTICE_VALUE);
            mfpStepsEntryV2.setEnergy(new MfpMeasuredValue("calories", (float) d3));
            mfpStepsEntryV2.setSteps(googleFitStepsEntry.getSteps());
            mfpStepsEntryV2.setType("steps");
            return mfpStepsEntryV2;
        } catch (Exception unused) {
            return null;
        }
    }

    private void storeMfpFitStepsResult(MfpFitStepsContainer mfpFitStepsContainer, long j) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty((Collection<?>) mfpFitStepsContainer.entries)) {
            for (GoogleFitStepsEntry convertGoogleFitStepsIntoMfpSteps : mfpFitStepsContainer.entries) {
                MfpStepsEntryV2 convertGoogleFitStepsIntoMfpSteps2 = convertGoogleFitStepsIntoMfpSteps(convertGoogleFitStepsIntoMfpSteps);
                if (convertGoogleFitStepsIntoMfpSteps2 != null) {
                    arrayList.add(convertGoogleFitStepsIntoMfpSteps2);
                }
            }
        }
        if (GoogleFitStepsUtils.isGoogleFitStepSource(((StepService) this.stepService.get()).getPrimaryStepSource()) && CollectionUtils.notEmpty((Collection<?>) arrayList)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Writing fit steps to our backend : ");
            sb.append(Strings.toString(arrayList));
            Ln.d(sb.toString(), new Object[0]);
            DiaryService diaryService2 = (DiaryService) this.diaryService.get();
            MfpStepSource primaryStepSource = ((StepService) this.stepService.get()).getPrimaryStepSource();
            final ArrayList arrayList2 = arrayList;
            final long j2 = j;
            final MfpFitStepsContainer mfpFitStepsContainer2 = mfpFitStepsContainer;
            AnonymousClass1 r0 = new Function1<Boolean>() {
                public void execute(Boolean bool) {
                    if (bool.booleanValue()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Success writing fit steps to our backend : ");
                        sb.append(Strings.toString(arrayList2));
                        Ln.d(sb.toString(), new Object[0]);
                        GoogleFitStepsService.this.setLastSyncStepsTime(j2);
                        GoogleFitStepsService.this.setLastSyncStepsCount(mfpFitStepsContainer2.totalSteps);
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Fail writing fit steps to our backend : ");
                    sb2.append(Strings.toString(arrayList2));
                    Ln.d(sb2.toString(), new Object[0]);
                }
            };
            diaryService2.postStepsAsync(arrayList, primaryStepSource, r0);
        }
    }

    public void clearStoredGoogleFitStepsFromPrefs() {
        ((KeyedSharedPreferences) this.googleFitStore.get()).edit().remove(Preference.FIT_STEPS).apply();
    }

    /* access modifiers changed from: private */
    public void setLastSyncStepsTime(long j) {
        ((SharedPreferences) this.fitClientStore.get()).edit().putLong(GoogleFitConstants.SharedPreferences.LAST_SYNC_TIME_STEPS, j).apply();
    }

    private long getLastSyncStepsTime() {
        long j = ((SharedPreferences) this.fitClientStore.get()).getLong(GoogleFitConstants.SharedPreferences.LAST_SYNC_TIME_STEPS, GoogleFitDateTimeUtils.getStartOfDay(new Date()).getTime());
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j);
        return DateTimeUtils.getNumberOfDaysBetween(instance2, instance) > 7 ? DateTimeUtils.offsetDate(instance.getTime(), -7).getTime() : j;
    }

    private long getLastSyncStepsTimeMidnight() {
        return GoogleFitDateTimeUtils.getStartOfDay(new Date(getLastSyncStepsTime())).getTime();
    }

    /* access modifiers changed from: private */
    public void setLastSyncStepsCount(int i) {
        ((SharedPreferences) this.fitClientStore.get()).edit().putInt(GoogleFitConstants.SharedPreferences.LAST_SYNC_STEP_COUNT, i).apply();
    }

    private int getLastSyncStepsCount() {
        return ((SharedPreferences) this.fitClientStore.get()).getInt(GoogleFitConstants.SharedPreferences.LAST_SYNC_STEP_COUNT, 0);
    }

    private void setLastSyncTodaysStepsCount(int i) {
        ((SharedPreferences) this.fitClientStore.get()).edit().putInt(GoogleFitConstants.SharedPreferences.LAST_SYNC_TODAYS_STEP_COUNT, i).apply();
    }
}
