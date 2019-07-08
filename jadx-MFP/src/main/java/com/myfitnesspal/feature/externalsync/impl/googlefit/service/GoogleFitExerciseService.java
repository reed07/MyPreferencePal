package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest.Builder;
import com.google.android.gms.fitness.result.DataReadResult;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitActivity;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.DateTime;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.SyncScopes;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitActivityEntry;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.SyncScope;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitDateTimeUtils;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.ReportAnalyticsDebouncer;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.shared.constants.Constants.ABTest.GoogleFitActivity201511;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GoogleFitExerciseService implements GoogleFitServiceBase, ExternalExerciseService {
    private static final Map<Integer, String> GOOGLE_FIT_MAPPING = new HashMap();
    private static final int MIN_TIME_BETWEEN_ANOTHER_SYNC = 60000;
    private static final String TAG = "GoogleFitExerciseService";
    private final Lazy<AppGalleryService> appGalleryService;
    private final Lazy<ConfigService> configService;
    private final Lazy<DiaryService> diaryService;
    private final ReportAnalyticsDebouncer exerciseReadDebouncer;
    private final Lazy<GoogleFitClient> googleFitClient;
    private final Lazy<KeyedSharedPreferences> googleFitPrefs;

    public void onExerciseEntryDeleted(MfpExerciseEntry mfpExerciseEntry, String str) {
    }

    public void onExerciseEntryInserted(MfpExerciseEntry mfpExerciseEntry, String str) {
    }

    public void onExerciseEntryUpdated(MfpExerciseEntry mfpExerciseEntry, String str) {
    }

    public GoogleFitExerciseService(Lazy<KeyedSharedPreferences> lazy, Lazy<GoogleFitClient> lazy2, Lazy<ConfigService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<DiaryService> lazy5, Lazy<AppGalleryService> lazy6) {
        this.googleFitPrefs = lazy;
        this.googleFitClient = lazy2;
        this.configService = lazy3;
        this.diaryService = lazy5;
        this.appGalleryService = lazy6;
        this.exerciseReadDebouncer = new ReportAnalyticsDebouncer(lazy4, Attributes.EXERCISE_READ);
    }

    public boolean enabled() {
        return ((GoogleFitClient) this.googleFitClient.get()).isEnabledForSync();
    }

    public void sync() {
        if (enabled() && isTimerUpToSyncExercises()) {
            if (((GoogleFitClient) this.googleFitClient.get()).connectAndWait()) {
                SyncScope syncScope = getSyncScope();
                if (syncScope != null && syncScope.getScope().equals(SyncScopes.FIT_ACTIVITY_READ)) {
                    syncDataWithFitCloud(((GoogleFitClient) this.googleFitClient.get()).getGoogleApiClient(), getStartTime());
                }
            } else {
                Ln.e("GoogleFitClient.connectAndWait() failed!", new Object[0]);
            }
        }
    }

    private void syncDataWithFitCloud(GoogleApiClient googleApiClient, long j) {
        if (j != Long.MIN_VALUE) {
            long time = GoogleFitDateTimeUtils.getEndOfDay(new Date()).getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Range Start: ");
            sb.append(simpleDateFormat.format(Long.valueOf(j)));
            Ln.d(str, sb.toString());
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Range End: ");
            sb2.append(simpleDateFormat.format(Long.valueOf(time)));
            Ln.d(str2, sb2.toString());
            try {
                DataReadResult dataReadResult = (DataReadResult) Fitness.HistoryApi.readData(googleApiClient, new Builder().aggregate(DataType.TYPE_ACTIVITY_SEGMENT, DataType.AGGREGATE_ACTIVITY_SUMMARY).bucketByActivitySegment(1, TimeUnit.SECONDS).setTimeRange(j, time, TimeUnit.MILLISECONDS).build()).await(30, AWAIT_TIME_UNIT);
                Ln.d(TAG, dataReadResult);
                if (dataReadResult.getStatus().isSuccess()) {
                    storeMfpFitActivityResult(convertResultToMfpResult(dataReadResult), j);
                }
            } catch (Exception e) {
                Ln.e(e);
            }
        }
    }

    private long getStartTime() {
        return GoogleFitDateTimeUtils.getStartOfDay(DateTimeUtils.offsetDate(Calendar.getInstance().getTime(), -2)).getTime();
    }

    private boolean isTimerUpToSyncExercises() {
        long j = ((KeyedSharedPreferences) this.googleFitPrefs.get()).getLong(Preference.FIT_EXERCISES_SYNC_TIME_POINT, Long.MIN_VALUE);
        return j == Long.MIN_VALUE || Math.abs(Calendar.getInstance().getTimeInMillis() - j) > DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS;
    }

    private SyncScope getSyncScope() {
        if (((ConfigService) this.configService.get()).isVariantEnabled(GoogleFitActivity201511.NAME, "on")) {
            return new SyncScope(SyncScopes.FIT_ACTIVITY_READ);
        }
        return null;
    }

    private List<GoogleFitActivityEntry> convertResultToMfpResult(DataReadResult dataReadResult) {
        ArrayList arrayList = new ArrayList();
        if (dataReadResult == null) {
            return arrayList;
        }
        List<Bucket> buckets = dataReadResult.getBuckets();
        if (buckets != null && buckets.size() > 0) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Number of returned buckets of DataSets is: ");
            sb.append(buckets.size());
            Ln.d(str, sb.toString());
            for (Bucket dataSets : buckets) {
                for (DataSet convertDataSet : dataSets.getDataSets()) {
                    List convertDataSet2 = convertDataSet(convertDataSet);
                    if (convertDataSet2 != null) {
                        arrayList.addAll(convertDataSet2);
                    }
                }
            }
        } else if (dataReadResult.getDataSets() != null && dataReadResult.getDataSets().size() > 0) {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Number of returned DataSets is: ");
            sb2.append(dataReadResult.getDataSets().size());
            Ln.d(str2, sb2.toString());
            for (DataSet convertDataSet3 : dataReadResult.getDataSets()) {
                List convertDataSet4 = convertDataSet(convertDataSet3);
                if (convertDataSet4 != null) {
                    arrayList.addAll(convertDataSet4);
                }
            }
        }
        return arrayList;
    }

    private List<GoogleFitActivityEntry> convertDataSet(DataSet dataSet) {
        if (dataSet == null) {
            return null;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Data returned for Data type: ");
        sb.append(dataSet.getDataType().getName());
        Ln.d(str, sb.toString());
        ArrayList arrayList = new ArrayList();
        for (DataPoint dataPoint : dataSet.getDataPoints()) {
            int asInt = dataPoint.getValue(Field.FIELD_ACTIVITY).asInt();
            if (GoogleFitActivity.containsId(asInt)) {
                GoogleFitActivityEntry googleFitActivityEntry = new GoogleFitActivityEntry();
                googleFitActivityEntry.setActivityId(asInt);
                googleFitActivityEntry.setStartTime(dataPoint.getStartTime(TimeUnit.MILLISECONDS));
                googleFitActivityEntry.setEndTime(dataPoint.getEndTime(TimeUnit.MILLISECONDS));
                googleFitActivityEntry.setActivityId(asInt);
                googleFitActivityEntry.setDuration((long) dataPoint.getValue(Field.FIELD_DURATION).asInt());
                googleFitActivityEntry.setNumberOfSegments(dataPoint.getValue(Field.FIELD_NUM_SEGMENTS).asInt());
                googleFitActivityEntry.setSource(dataPoint.getOriginalDataSource() != null ? dataPoint.getOriginalDataSource().getAppPackageName() : "google_fit");
                arrayList.add(googleFitActivityEntry);
            }
        }
        return arrayList;
    }

    private void storeMfpFitActivityResult(List<GoogleFitActivityEntry> list, long j) {
        HashMap hashMap = new HashMap();
        for (GoogleFitActivityEntry googleFitActivityEntry : list) {
            int activityId = googleFitActivityEntry.getActivityId();
            if (GOOGLE_FIT_MAPPING.containsKey(Integer.valueOf(activityId)) && !isConnectedPartner(googleFitActivityEntry.getSource())) {
                MfpExerciseEntry buildExerciseEntryForExternalSync = ((DiaryService) this.diaryService.get()).buildExerciseEntryForExternalSync((String) GOOGLE_FIT_MAPPING.get(Integer.valueOf(activityId)), googleFitActivityEntry.getStartTime(), googleFitActivityEntry.getDuration(), "google_fit");
                if (buildExerciseEntryForExternalSync != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Strings.toString(buildExerciseEntryForExternalSync.getExercise().getId()));
                    sb.append(":");
                    sb.append(Strings.toString(Database.encodeDateAndTime(buildExerciseEntryForExternalSync.getStartTime())));
                    hashMap.put(sb.toString(), buildExerciseEntryForExternalSync);
                }
                this.exerciseReadDebouncer.call(googleFitActivityEntry.getSource());
            }
        }
        if (writeExerciseEntriesFromExternalSync(hashMap, "google_fit", j)) {
            setTimePointerForSyncExercises();
        } else {
            resetTimePointerForSyncExercises();
        }
    }

    private boolean writeExerciseEntriesFromExternalSync(Map<String, MfpExerciseEntry> map, String str, long j) {
        Map exerciseEntriesOnOrAfter = ((DiaryService) this.diaryService.get()).getExerciseEntriesOnOrAfter(str, null, j);
        if (!CollectionUtils.notEmpty(map) && !CollectionUtils.notEmpty(exerciseEntriesOnOrAfter)) {
            return false;
        }
        Iterator it = exerciseEntriesOnOrAfter.keySet().iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            MfpExerciseEntry mfpExerciseEntry = (MfpExerciseEntry) exerciseEntriesOnOrAfter.get(str2);
            if (!map.containsKey(str2) && !mfpExerciseEntry.isCalorieAdjustment().booleanValue()) {
                ((DiaryService) this.diaryService.get()).deleteExerciseEntryLocally((MfpExerciseEntry) exerciseEntriesOnOrAfter.get(str2));
                it.remove();
            }
        }
        for (String str3 : map.keySet()) {
            MfpExerciseEntry mfpExerciseEntry2 = (MfpExerciseEntry) map.get(str3);
            if (exerciseEntriesOnOrAfter.containsKey(str3)) {
                MfpExerciseEntry mfpExerciseEntry3 = (MfpExerciseEntry) exerciseEntriesOnOrAfter.get(str3);
                if (mfpExerciseEntry3.getDuration() != mfpExerciseEntry2.getDuration()) {
                    mfpExerciseEntry3.setDuration(mfpExerciseEntry2.getDuration());
                    mfpExerciseEntry3.setEnergy(mfpExerciseEntry2.getEnergy());
                    ((DiaryService) this.diaryService.get()).updateExerciseEntryLocally(mfpExerciseEntry3);
                }
            } else {
                ((DiaryService) this.diaryService.get()).createNewExerciseEntryLocally(mfpExerciseEntry2);
            }
        }
        return true;
    }

    private boolean isConnectedPartner(String str) {
        return ((AppGalleryService) this.appGalleryService.get()).isAppConnected(str);
    }

    private void setTimePointerForSyncExercises() {
        ((KeyedSharedPreferences) this.googleFitPrefs.get()).edit().putLong(Preference.FIT_EXERCISES_SYNC_TIME_POINT, Calendar.getInstance().getTimeInMillis()).apply();
    }

    private void resetTimePointerForSyncExercises() {
        ((KeyedSharedPreferences) this.googleFitPrefs.get()).edit().putLong(Preference.FIT_EXERCISES_SYNC_TIME_POINT, Long.MIN_VALUE).apply();
    }

    static {
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.AEROBICS.getId()), "134026256871277");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BADMINTON.getId()), "133476501090285");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BASEBALL.getId()), "133751223799101");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BASKETBALL.getId()), "134026256904173");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIATHLON.getId()), "134028412743149");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING.getId()), "134028404355053");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING_HAND.getId()), "133478656929645");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING_MOUNTAIN.getId()), "133478656929773");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING_ROAD.getId()), "133478656929645");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING_SPINNING.getId()), "133753379622333");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING_STATIONARY.getId()), "134028404387693");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BIKING_UTILITY.getId()), "133478656929645");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.BOXING.getId()), "134028412776429");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.CALISTHENICS.getId()), "134026252677101");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.CIRCUIT_TRAINING.getId()), "134026252709869");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.CRICKET.getId()), "133478644346733");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.CURLING.getId()), "133478644346861");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.DANCING.getId()), "133478652735469");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.DIVING.getId()), "133478644379501");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ELLIPTICAL.getId()), "133476467519469");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ERGOMETER.getId()), "134026252709229");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.FENCING.getId()), "134028400193389");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.FOOTBALL_AMERICAN.getId()), "133476467502957");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.FOOTBALL_AUSTRALIAN.getId()), "134028366638957");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.FOOTBALL_SOCCER.getId()), "134026261065069");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.FRISBEE_DISC.getId()), "133476467503085");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.GARDENING.getId()), "133476475891565");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.GOLF.getId()), "133476475891693");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.GYMNASTICS.getId()), "134026223349613");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.HANDBALL.getId()), "134026223349741");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.HIKING.getId()), "134026231738221");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.HOCKEY.getId()), "133476475924461");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.HORSEBACK_RIDING.getId()), "134028370800493");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.HOUSEWORK.getId()), "133478623408109");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ICE_SKATING.getId()), "134026231738349");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.JUMP_ROPE.getId()), "134028366606317");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.KAYAKING.getId()), "134028379189229");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.KICKBOXING.getId()), "133201434430909");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.MARTIAL_ARTS.getId()), "133201442819517");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.MARTIAL_ARTS_MIXED.getId()), "133201442819517");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ON_FOOT.getId()), "133476467502445");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.PILATES.getId()), "133201476341181");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.POLO.getId()), "134026219155309");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.RACQUETBALL.getId()), "133476471730029");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ROCK_CLIMBING.getId()), "134026227544045");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ROWING.getId()), "133478619181037");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.ROWING_MACHINE.getId()), "133478619181037");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.RUGBY.getId()), "134028366638957");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.RUNNING.getId()), "134026265259501");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.RUNNING_JOGGING.getId()), "133478619213677");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.RUNNING_SAND.getId()), "134026265259501");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.RUNNING_TREADMILL.getId()), "134026252709229");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SAILING.getId()), "133476501089645");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SCUBA_DIVING.getId()), "133478644379501");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKATEBOARDING.getId()), "134026256903661");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKATING.getId()), "134026265292141");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKATING_CROSS.getId()), "133476509478381");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKATING_INDOOR.getId()), "133476509478253");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKATING_INLINE.getId()), "134026265292141");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKIING.getId()), "134028404387309");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKIING_BACK_COUNTRY.getId()), "133478656929133");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKIING_CROSS_COUNTRY.getId()), "134028404354541");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKIING_DOWNHILL.getId()), "134028404387181");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKIING_KITE.getId()), "133478656961901");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SKIING_ROLLER.getId()), "134028404387181");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SLEDDING.getId()), "133476496862573");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SNOWBOARDING.getId()), "134028404387309");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SNOWMOBILE.getId()), "134026252676589");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SNOWSHOEING.getId()), "133476496862701");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SQUASH.getId()), "133476496895341");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.STAIR_CLIMBING.getId()), "134026252709229");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.STAIR_CLIMBING_MACHINE.getId()), "134026252709229");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.STANDUP_PADDLEBOARDING.getId()), "133478614986221");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SURFING.getId()), "133476501089645");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SWIMMING.getId()), "134028400160109");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SWIMMING_POOL.getId()), "134028408548717");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.SWIMMING_OPEN_POOL.getId()), "133476505283949");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.TABLE_TENNIS.getId()), "133478652734957");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.TENNINS.getId()), "133478644379117");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.VOLLEYBALL.getId()), "134028408581613");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.VOLLEYBALL_BEACH.getId()), "134028408581485");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.VOLLEYBALL_INDOOR.getId()), "133478652767725");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.WALKING_FITNESS.getId()), "134026223316461");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.WALKING_NORDIC.getId()), "134026223316461");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.WALKING_TREADMILL.getId()), "133476467535213");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.WATER_POLO.getId()), "133476475923949");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.WINDSURFING.getId()), "133476501089645");
        GOOGLE_FIT_MAPPING.put(Integer.valueOf(GoogleFitActivity.YOGA.getId()), "133751232154941");
    }
}
