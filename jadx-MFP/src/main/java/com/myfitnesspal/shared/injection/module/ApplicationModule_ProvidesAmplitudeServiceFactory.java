package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesAmplitudeServiceFactory implements Factory<AmplitudeService> {
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<String> carrierNameProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DbConnectionManager> currentAndDbConnectionManagerProvider;
    private final Provider<UUID> deviceIdProvider;
    private final Provider<DiarySharingSettingsManager> diarySharingSettingsManagerProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<InsightSettings> insightSettingsProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final ApplicationModule module;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<RemindersService> remindersServiceProvider;
    private final Provider<String> sessionIdProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SubscriptionService> subscriptionServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;

    public ApplicationModule_ProvidesAmplitudeServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<String> provider3, Provider<String> provider4, Provider<UUID> provider5, Provider<Session> provider6, Provider<FriendService> provider7, Provider<AppGalleryService> provider8, Provider<ConfigService> provider9, Provider<SubscriptionService> provider10, Provider<SharedPreferences> provider11, Provider<InsightSettings> provider12, Provider<LocalSettingsService> provider13, Provider<DiarySharingSettingsManager> provider14, Provider<DbConnectionManager> provider15, Provider<LoginModel> provider16, Provider<NutrientGoalService> provider17, Provider<NutrientGoalsUtil> provider18, Provider<RemindersService> provider19, Provider<UserApplicationSettingsService> provider20) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.sessionIdProvider = provider3;
        this.carrierNameProvider = provider4;
        this.deviceIdProvider = provider5;
        this.sessionProvider = provider6;
        this.friendServiceProvider = provider7;
        this.appGalleryServiceProvider = provider8;
        this.configServiceProvider = provider9;
        this.subscriptionServiceProvider = provider10;
        this.sharedPreferencesProvider = provider11;
        this.insightSettingsProvider = provider12;
        this.localSettingsServiceProvider = provider13;
        this.diarySharingSettingsManagerProvider = provider14;
        this.currentAndDbConnectionManagerProvider = provider15;
        this.loginModelProvider = provider16;
        this.nutrientGoalServiceProvider = provider17;
        this.nutrientGoalsUtilProvider = provider18;
        this.remindersServiceProvider = provider19;
        this.userApplicationSettingsServiceProvider = provider20;
    }

    public AmplitudeService get() {
        ApplicationModule applicationModule = this.module;
        return provideInstance(applicationModule, this.contextProvider, this.appSettingsProvider, this.sessionIdProvider, this.carrierNameProvider, this.deviceIdProvider, this.sessionProvider, this.friendServiceProvider, this.appGalleryServiceProvider, this.configServiceProvider, this.subscriptionServiceProvider, this.sharedPreferencesProvider, this.insightSettingsProvider, this.localSettingsServiceProvider, this.diarySharingSettingsManagerProvider, this.currentAndDbConnectionManagerProvider, this.loginModelProvider, this.nutrientGoalServiceProvider, this.nutrientGoalsUtilProvider, this.remindersServiceProvider, this.userApplicationSettingsServiceProvider);
    }

    public static AmplitudeService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<String> provider3, Provider<String> provider4, Provider<UUID> provider5, Provider<Session> provider6, Provider<FriendService> provider7, Provider<AppGalleryService> provider8, Provider<ConfigService> provider9, Provider<SubscriptionService> provider10, Provider<SharedPreferences> provider11, Provider<InsightSettings> provider12, Provider<LocalSettingsService> provider13, Provider<DiarySharingSettingsManager> provider14, Provider<DbConnectionManager> provider15, Provider<LoginModel> provider16, Provider<NutrientGoalService> provider17, Provider<NutrientGoalsUtil> provider18, Provider<RemindersService> provider19, Provider<UserApplicationSettingsService> provider20) {
        return proxyProvidesAmplitudeService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), (String) provider3.get(), (String) provider4.get(), (UUID) provider5.get(), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), (SharedPreferences) provider11.get(), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), (DbConnectionManager) provider15.get(), (LoginModel) provider16.get(), DoubleCheck.lazy(provider17), DoubleCheck.lazy(provider18), DoubleCheck.lazy(provider19), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider20));
    }

    public static ApplicationModule_ProvidesAmplitudeServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<String> provider3, Provider<String> provider4, Provider<UUID> provider5, Provider<Session> provider6, Provider<FriendService> provider7, Provider<AppGalleryService> provider8, Provider<ConfigService> provider9, Provider<SubscriptionService> provider10, Provider<SharedPreferences> provider11, Provider<InsightSettings> provider12, Provider<LocalSettingsService> provider13, Provider<DiarySharingSettingsManager> provider14, Provider<DbConnectionManager> provider15, Provider<LoginModel> provider16, Provider<NutrientGoalService> provider17, Provider<NutrientGoalsUtil> provider18, Provider<RemindersService> provider19, Provider<UserApplicationSettingsService> provider20) {
        ApplicationModule_ProvidesAmplitudeServiceFactory applicationModule_ProvidesAmplitudeServiceFactory = new ApplicationModule_ProvidesAmplitudeServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20);
        return applicationModule_ProvidesAmplitudeServiceFactory;
    }

    public static AmplitudeService proxyProvidesAmplitudeService(ApplicationModule applicationModule, Context context, Lazy<AppSettings> lazy, String str, String str2, UUID uuid, Lazy<Session> lazy2, Lazy<FriendService> lazy3, Lazy<AppGalleryService> lazy4, Lazy<ConfigService> lazy5, Lazy<SubscriptionService> lazy6, SharedPreferences sharedPreferences, Lazy<InsightSettings> lazy7, Lazy<LocalSettingsService> lazy8, Lazy<DiarySharingSettingsManager> lazy9, DbConnectionManager dbConnectionManager, LoginModel loginModel, Lazy<NutrientGoalService> lazy10, Lazy<NutrientGoalsUtil> lazy11, Lazy<RemindersService> lazy12, Lazy<DbConnectionManager> lazy13, Lazy<UserApplicationSettingsService> lazy14) {
        return (AmplitudeService) Preconditions.checkNotNull(applicationModule.providesAmplitudeService(context, lazy, str, str2, uuid, lazy2, lazy3, lazy4, lazy5, lazy6, sharedPreferences, lazy7, lazy8, lazy9, dbConnectionManager, loginModel, lazy10, lazy11, lazy12, lazy13, lazy14), "Cannot return null from a non-@Nullable @Provides method");
    }
}
