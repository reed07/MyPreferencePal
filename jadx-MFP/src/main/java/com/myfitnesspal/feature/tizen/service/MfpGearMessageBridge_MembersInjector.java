package com.myfitnesspal.feature.tizen.service;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.api.v2.DeviceActivationApi;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpGearMessageBridge_MembersInjector implements MembersInjector<MfpGearMessageBridge> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<DeviceActivationApi> apiProvider;
    private final Provider<Bus> busProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalService> nutrientGoalsServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MfpGearMessageBridge_MembersInjector(Provider<NutrientGoalService> provider, Provider<NutrientGoalsUtil> provider2, Provider<UserEnergyService> provider3, Provider<StepService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<PremiumService> provider6, Provider<ConfigService> provider7, Provider<AnalyticsService> provider8, Provider<DeviceActivationApi> provider9, Provider<Bus> provider10, Provider<Session> provider11, Provider<DbConnectionManager> provider12) {
        this.nutrientGoalsServiceProvider = provider;
        this.nutrientGoalsUtilProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.stepServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.premiumServiceProvider = provider6;
        this.configServiceProvider = provider7;
        this.analyticsProvider = provider8;
        this.apiProvider = provider9;
        this.busProvider = provider10;
        this.sessionProvider = provider11;
        this.dbConnectionManagerProvider = provider12;
    }

    public static MembersInjector<MfpGearMessageBridge> create(Provider<NutrientGoalService> provider, Provider<NutrientGoalsUtil> provider2, Provider<UserEnergyService> provider3, Provider<StepService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<PremiumService> provider6, Provider<ConfigService> provider7, Provider<AnalyticsService> provider8, Provider<DeviceActivationApi> provider9, Provider<Bus> provider10, Provider<Session> provider11, Provider<DbConnectionManager> provider12) {
        MfpGearMessageBridge_MembersInjector mfpGearMessageBridge_MembersInjector = new MfpGearMessageBridge_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
        return mfpGearMessageBridge_MembersInjector;
    }

    public void injectMembers(MfpGearMessageBridge mfpGearMessageBridge) {
        injectNutrientGoalsService(mfpGearMessageBridge, DoubleCheck.lazy(this.nutrientGoalsServiceProvider));
        injectNutrientGoalsUtil(mfpGearMessageBridge, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectUserEnergyService(mfpGearMessageBridge, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectStepService(mfpGearMessageBridge, DoubleCheck.lazy(this.stepServiceProvider));
        injectLocalizedStringsUtil(mfpGearMessageBridge, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectPremiumService(mfpGearMessageBridge, DoubleCheck.lazy(this.premiumServiceProvider));
        injectConfigService(mfpGearMessageBridge, DoubleCheck.lazy(this.configServiceProvider));
        injectAnalytics(mfpGearMessageBridge, DoubleCheck.lazy(this.analyticsProvider));
        injectApi(mfpGearMessageBridge, this.apiProvider);
        injectBus(mfpGearMessageBridge, (Bus) this.busProvider.get());
        injectSession(mfpGearMessageBridge, (Session) this.sessionProvider.get());
        injectDbConnectionManager(mfpGearMessageBridge, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectNutrientGoalsService(MfpGearMessageBridge mfpGearMessageBridge, Lazy<NutrientGoalService> lazy) {
        mfpGearMessageBridge.nutrientGoalsService = lazy;
    }

    public static void injectNutrientGoalsUtil(MfpGearMessageBridge mfpGearMessageBridge, Lazy<NutrientGoalsUtil> lazy) {
        mfpGearMessageBridge.nutrientGoalsUtil = lazy;
    }

    public static void injectUserEnergyService(MfpGearMessageBridge mfpGearMessageBridge, Lazy<UserEnergyService> lazy) {
        mfpGearMessageBridge.userEnergyService = lazy;
    }

    public static void injectStepService(MfpGearMessageBridge mfpGearMessageBridge, Lazy<StepService> lazy) {
        mfpGearMessageBridge.stepService = lazy;
    }

    public static void injectLocalizedStringsUtil(MfpGearMessageBridge mfpGearMessageBridge, Lazy<LocalizedStringsUtil> lazy) {
        mfpGearMessageBridge.localizedStringsUtil = lazy;
    }

    public static void injectPremiumService(MfpGearMessageBridge mfpGearMessageBridge, Lazy<PremiumService> lazy) {
        mfpGearMessageBridge.premiumService = lazy;
    }

    public static void injectConfigService(MfpGearMessageBridge mfpGearMessageBridge, Lazy<ConfigService> lazy) {
        mfpGearMessageBridge.configService = lazy;
    }

    public static void injectAnalytics(MfpGearMessageBridge mfpGearMessageBridge, Lazy<AnalyticsService> lazy) {
        mfpGearMessageBridge.analytics = lazy;
    }

    public static void injectApi(MfpGearMessageBridge mfpGearMessageBridge, Provider<DeviceActivationApi> provider) {
        mfpGearMessageBridge.api = provider;
    }

    public static void injectBus(MfpGearMessageBridge mfpGearMessageBridge, Bus bus) {
        mfpGearMessageBridge.bus = bus;
    }

    public static void injectSession(MfpGearMessageBridge mfpGearMessageBridge, Session session) {
        mfpGearMessageBridge.session = session;
    }

    public static void injectDbConnectionManager(MfpGearMessageBridge mfpGearMessageBridge, Lazy<DbConnectionManager> lazy) {
        mfpGearMessageBridge.dbConnectionManager = lazy;
    }
}
