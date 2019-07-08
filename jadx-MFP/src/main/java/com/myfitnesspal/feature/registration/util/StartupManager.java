package com.myfitnesspal.feature.registration.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.task.ConsentsTask;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.settings.ui.fragment.WeeklyNutritionSettingsListFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.StockDbSQLiteOpenHelper;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;

public abstract class StartupManager {
    private static boolean hasRunStartupTasks;
    protected final Lazy<AnalyticsService> analyticsService;
    private final Lazy<AppRatingService> appRatingService;
    private final Lazy<AppSettings> appSettings;
    protected final Lazy<ConfigService> configService;
    private final Lazy<ConsentsService> consentsService;
    protected final Context context;
    private final Lazy<CountryService> countryService;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<UacfEmailVerificationManager> emailVerificationManager;
    private final Lazy<GlobalAppPreferenceMigrationUtil> globalAppPreferenceMigrationUtil;
    protected final Lazy<InstallManager> installManager;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<LocationService> locationService;
    private final Lazy<PushNotificationManager> pushNotificationManager;
    protected final Lazy<Session> session;
    private final Lazy<StepService> stepService;
    private final Lazy<UacfScheduler<SyncType>> syncScheduler;
    protected final Lazy<SyncUtil> syncUtil;
    protected final Lazy<MfpAnalyticsService> v2Analytics;

    /* access modifiers changed from: protected */
    public abstract void initApp();

    public StartupManager(Context context2, Lazy<ConfigService> lazy, Lazy<AnalyticsService> lazy2, Lazy<MfpAnalyticsService> lazy3, Lazy<InstallManager> lazy4, Lazy<Session> lazy5, Lazy<LocalSettingsService> lazy6, Lazy<SyncUtil> lazy7, Lazy<UacfScheduler<SyncType>> lazy8, Lazy<StepService> lazy9, Lazy<LocationService> lazy10, Lazy<PushNotificationManager> lazy11, Lazy<GlobalAppPreferenceMigrationUtil> lazy12, Lazy<AppSettings> lazy13, Lazy<AppRatingService> lazy14, Lazy<DbConnectionManager> lazy15, Lazy<CountryService> lazy16, Lazy<ConsentsService> lazy17, Lazy<UacfEmailVerificationManager> lazy18) {
        this.context = context2;
        this.configService = lazy;
        this.analyticsService = lazy2;
        this.v2Analytics = lazy3;
        this.installManager = lazy4;
        this.session = lazy5;
        this.syncUtil = lazy7;
        this.localSettingsService = lazy6;
        this.syncScheduler = lazy8;
        this.stepService = lazy9;
        this.locationService = lazy10;
        this.pushNotificationManager = lazy11;
        this.globalAppPreferenceMigrationUtil = lazy12;
        this.appSettings = lazy13;
        this.appRatingService = lazy14;
        this.dbConnectionManager = lazy15;
        this.countryService = lazy16;
        this.consentsService = lazy17;
        this.emailVerificationManager = lazy18;
    }

    public void doStartupTasksIfNecessary(Activity activity) {
        if ((activity.isTaskRoot() && (activity instanceof MfpActivity)) || checkReferrerNeedConsentInterruption(activity)) {
            new ConsentsTask((Session) this.session.get(), (ConsentsService) this.consentsService.get(), (CountryService) this.countryService.get()).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(((MfpActivity) activity).getRunner());
        }
        if (hasRunStartupTasks) {
            Ln.d("STARTUP: doStartupTasksIfNecessary, already ran tasks, bail out", new Object[0]);
            return;
        }
        ((GlobalAppPreferenceMigrationUtil) this.globalAppPreferenceMigrationUtil.get()).migrateGlobalPreferencesToLocalSettings();
        MPFAppWidgetProvider.update(activity);
        ((AppRatingService) this.appRatingService.get()).runUpdateCheck();
        User user = ((Session) this.session.get()).getUser();
        Ln.d("STARTUP: doStartupTasksIfNecessary, running tasks now", new Object[0]);
        hasRunStartupTasks = true;
        ((UacfEmailVerificationManager) this.emailVerificationManager.get()).incrementAppLaunchCount();
        ((LocationService) this.locationService.get()).updateUserLocation();
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        analyticsService2.initialize(activity);
        installOrUpdateStockDatabase();
        ((SyncUtil) this.syncUtil.get()).migrateDataForSyncV2();
        ((SyncUtil) this.syncUtil.get()).migrateRemindersData();
        ((UacfScheduler) this.syncScheduler.get()).debounceSyncTypes(SyncType.FindAndCorrectFoodsWithMissingInfo, SyncType.Config);
        ((LocalSettingsService) this.localSettingsService.get()).migrateSharedPreferencesToUserApplicationSettingsIfNeeded();
        ((InstallManager) this.installManager.get()).trackInstallOrUpdate();
        reportStepSource(analyticsService2);
        reportStartOfWeekDay();
        ((ConfigService) this.configService.get()).prefetchAsync(new Function0(analyticsService2) {
            private final /* synthetic */ AnalyticsService f$1;

            {
                this.f$1 = r2;
            }

            public final void execute() {
                StartupManager.lambda$doStartupTasksIfNecessary$0(StartupManager.this, this.f$1);
            }
        });
        if (PushNotificationManager.pushNotificationsEnabled() && user.isLoggedIn()) {
            ((PushNotificationManager) this.pushNotificationManager.get()).registerUserForFCM();
        }
    }

    public static /* synthetic */ void lambda$doStartupTasksIfNecessary$0(StartupManager startupManager, AnalyticsService analyticsService2) throws RuntimeException {
        startupManager.initApp();
        analyticsService2.reportSessionStart();
    }

    private void installOrUpdateStockDatabase() {
        new StockDbSQLiteOpenHelper(this.context, this.appSettings).attachDatabase();
        if (DbConnectionManager.getDb(this.context).getVersion() >= 3) {
            Food.createQuickAddedCaloriesFoodIfNeeded((DbConnectionManager) this.dbConnectionManager.get());
        }
    }

    private void reportStepSource(AnalyticsService analyticsService2) {
        String str;
        MfpStepSource primaryStepSource = ((StepService) this.stepService.get()).getPrimaryStepSource();
        if (primaryStepSource == null) {
            str = "none";
        } else {
            str = primaryStepSource.getClientId();
        }
        analyticsService2.reportEvent(Events.STEP_SOURCE, MapUtil.createMap("source", str));
    }

    private void reportStartOfWeekDay() {
        int weeklyStartDay = ((LocalSettingsService) this.localSettingsService.get()).getWeeklyStartDay();
        ((MfpAnalyticsService) this.v2Analytics.get()).reportEvent(Events.WEEK_STARTS_ON, MapUtil.createMap(Attributes.DAY_OF_THE_WEEK, WeeklyNutritionSettingsListFragment.getAnalyticsValueForDay(weeklyStartDay)));
    }

    private boolean checkReferrerNeedConsentInterruption(@NonNull Activity activity) {
        return MPFAppWidgetProvider.WIDGET_REFERRER_ID.equals(ExtrasUtils.getString(activity.getIntent(), "referrer"));
    }
}
