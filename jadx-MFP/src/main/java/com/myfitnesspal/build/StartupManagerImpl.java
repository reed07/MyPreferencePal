package com.myfitnesspal.build;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.notification.PushNotificationManager;
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
import com.myfitnesspal.shared.util.CrashlyticsUtil;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005\u0012\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0005\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0005\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0005\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0005\u0012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0005\u0012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0005\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0005\u0012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0005\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0005\u0012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u0005\u0012\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0005¢\u0006\u0002\u0010*J\b\u0010+\u001a\u00020,H\u0014¨\u0006-"}, d2 = {"Lcom/myfitnesspal/build/StartupManagerImpl;", "Lcom/myfitnesspal/feature/registration/util/StartupManager;", "context", "Landroid/content/Context;", "configService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "analyticsService", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "v2Analytics", "Lcom/myfitnesspal/shared/service/analytics/MfpAnalyticsService;", "installManager", "Lcom/myfitnesspal/feature/registration/service/InstallManager;", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "syncUtil", "Lcom/myfitnesspal/shared/service/syncv2/SyncUtil;", "syncScheduler", "Lcom/uacf/sync/engine/UacfScheduler;", "Lcom/myfitnesspal/shared/service/syncv2/SyncType;", "stepService", "Lcom/myfitnesspal/shared/service/steps/StepService;", "locationService", "Lcom/myfitnesspal/shared/service/location/LocationService;", "pushNotificationManager", "Lcom/myfitnesspal/shared/notification/PushNotificationManager;", "globalAppPreferenceMigrationUtil", "Lcom/myfitnesspal/shared/util/GlobalAppPreferenceMigrationUtil;", "appSettings", "Lcom/myfitnesspal/feature/settings/model/AppSettings;", "appRatingService", "Lcom/myfitnesspal/feature/home/service/AppRatingService;", "dbConnectionManager", "Lcom/myfitnesspal/shared/db/DbConnectionManager;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "consentsService", "Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "emailVerificationManager", "Lcom/myfitnesspal/shared/uacf/UacfEmailVerificationManager;", "(Landroid/content/Context;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;)V", "initApp", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: StartupManagerImpl.kt */
public final class StartupManagerImpl extends StartupManager {
    public StartupManagerImpl(@NotNull Context context, @NotNull Lazy<ConfigService> lazy, @NotNull Lazy<AnalyticsService> lazy2, @NotNull Lazy<MfpAnalyticsService> lazy3, @NotNull Lazy<InstallManager> lazy4, @NotNull Lazy<Session> lazy5, @NotNull Lazy<LocalSettingsService> lazy6, @NotNull Lazy<SyncUtil> lazy7, @NotNull Lazy<UacfScheduler<SyncType>> lazy8, @NotNull Lazy<StepService> lazy9, @NotNull Lazy<LocationService> lazy10, @NotNull Lazy<PushNotificationManager> lazy11, @NotNull Lazy<GlobalAppPreferenceMigrationUtil> lazy12, @NotNull Lazy<AppSettings> lazy13, @NotNull Lazy<AppRatingService> lazy14, @NotNull Lazy<DbConnectionManager> lazy15, @NotNull Lazy<CountryService> lazy16, @NotNull Lazy<ConsentsService> lazy17, @NotNull Lazy<UacfEmailVerificationManager> lazy18) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(lazy, "configService");
        Intrinsics.checkParameterIsNotNull(lazy2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(lazy3, "v2Analytics");
        Intrinsics.checkParameterIsNotNull(lazy4, "installManager");
        Intrinsics.checkParameterIsNotNull(lazy5, "session");
        Intrinsics.checkParameterIsNotNull(lazy6, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy7, "syncUtil");
        Intrinsics.checkParameterIsNotNull(lazy8, "syncScheduler");
        Intrinsics.checkParameterIsNotNull(lazy9, "stepService");
        Intrinsics.checkParameterIsNotNull(lazy10, "locationService");
        Intrinsics.checkParameterIsNotNull(lazy11, "pushNotificationManager");
        Intrinsics.checkParameterIsNotNull(lazy12, "globalAppPreferenceMigrationUtil");
        Intrinsics.checkParameterIsNotNull(lazy13, "appSettings");
        Intrinsics.checkParameterIsNotNull(lazy14, "appRatingService");
        Intrinsics.checkParameterIsNotNull(lazy15, "dbConnectionManager");
        Intrinsics.checkParameterIsNotNull(lazy16, "countryService");
        Intrinsics.checkParameterIsNotNull(lazy17, "consentsService");
        Intrinsics.checkParameterIsNotNull(lazy18, "emailVerificationManager");
        super(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18);
    }

    /* access modifiers changed from: protected */
    public void initApp() {
        CrashlyticsUtil.startIfEnabled(this.context);
        Crashlytics.setString(Http.LANG, Locale.getDefault().toString());
    }
}
