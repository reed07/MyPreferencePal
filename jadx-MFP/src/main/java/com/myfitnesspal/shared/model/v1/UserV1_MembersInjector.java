package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.squareup.otto.Bus;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class UserV1_MembersInjector implements MembersInjector<UserV1> {
    private final Provider<Bus> busProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<UserV1GoalPreferences> goalPreferencesProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelperProvider;
    private final Provider<UserImageService> userImageServiceProvider;

    public UserV1_MembersInjector(Provider<CountryService> provider, Provider<MeasurementsService> provider2, Provider<UacfScheduler<SyncType>> provider3, Provider<DiaryService> provider4, Provider<UserV1GoalPreferences> provider5, Provider<UserImageService> provider6, Provider<LocalSettingsService> provider7, Provider<UpdatedTermsAnalyticsHelper> provider8, Provider<Bus> provider9, Provider<DbConnectionManager> provider10) {
        this.countryServiceProvider = provider;
        this.measurementsServiceProvider = provider2;
        this.syncSchedulerProvider = provider3;
        this.diaryServiceProvider = provider4;
        this.goalPreferencesProvider = provider5;
        this.userImageServiceProvider = provider6;
        this.localSettingsServiceProvider = provider7;
        this.updatedTermsAnalyticsHelperProvider = provider8;
        this.busProvider = provider9;
        this.dbConnectionManagerProvider = provider10;
    }

    public static MembersInjector<UserV1> create(Provider<CountryService> provider, Provider<MeasurementsService> provider2, Provider<UacfScheduler<SyncType>> provider3, Provider<DiaryService> provider4, Provider<UserV1GoalPreferences> provider5, Provider<UserImageService> provider6, Provider<LocalSettingsService> provider7, Provider<UpdatedTermsAnalyticsHelper> provider8, Provider<Bus> provider9, Provider<DbConnectionManager> provider10) {
        UserV1_MembersInjector userV1_MembersInjector = new UserV1_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return userV1_MembersInjector;
    }

    public void injectMembers(UserV1 userV1) {
        injectCountryService(userV1, DoubleCheck.lazy(this.countryServiceProvider));
        injectMeasurementsService(userV1, DoubleCheck.lazy(this.measurementsServiceProvider));
        injectSyncScheduler(userV1, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectDiaryService(userV1, DoubleCheck.lazy(this.diaryServiceProvider));
        injectGoalPreferences(userV1, DoubleCheck.lazy(this.goalPreferencesProvider));
        injectUserImageService(userV1, DoubleCheck.lazy(this.userImageServiceProvider));
        injectLocalSettingsService(userV1, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectUpdatedTermsAnalyticsHelper(userV1, DoubleCheck.lazy(this.updatedTermsAnalyticsHelperProvider));
        injectBus(userV1, DoubleCheck.lazy(this.busProvider));
        injectDbConnectionManager(userV1, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectCountryService(UserV1 userV1, Lazy<CountryService> lazy) {
        userV1.countryService = lazy;
    }

    public static void injectMeasurementsService(UserV1 userV1, Lazy<MeasurementsService> lazy) {
        userV1.measurementsService = lazy;
    }

    public static void injectSyncScheduler(UserV1 userV1, Lazy<UacfScheduler<SyncType>> lazy) {
        userV1.syncScheduler = lazy;
    }

    public static void injectDiaryService(UserV1 userV1, Lazy<DiaryService> lazy) {
        userV1.diaryService = lazy;
    }

    public static void injectGoalPreferences(UserV1 userV1, Lazy<UserV1GoalPreferences> lazy) {
        userV1.goalPreferences = lazy;
    }

    public static void injectUserImageService(UserV1 userV1, Lazy<UserImageService> lazy) {
        userV1.userImageService = lazy;
    }

    public static void injectLocalSettingsService(UserV1 userV1, Lazy<LocalSettingsService> lazy) {
        userV1.localSettingsService = lazy;
    }

    public static void injectUpdatedTermsAnalyticsHelper(UserV1 userV1, Lazy<UpdatedTermsAnalyticsHelper> lazy) {
        userV1.updatedTermsAnalyticsHelper = lazy;
    }

    public static void injectBus(UserV1 userV1, Lazy<Bus> lazy) {
        userV1.bus = lazy;
    }

    public static void injectDbConnectionManager(UserV1 userV1, Lazy<DbConnectionManager> lazy) {
        userV1.dbConnectionManager = lazy;
    }
}
